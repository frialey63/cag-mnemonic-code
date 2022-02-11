package org.pjp.cag;

import static org.pjp.cag.Store.ZERO;
import static org.pjp.cag.directive.AddressDirective.EXECUTE;
import static org.pjp.cag.directive.AddressDirective.STORE;
import static org.pjp.cag.directive.TitleDirective.TITLE;
import static org.pjp.cag.directive.WaitDirective.WAIT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pjp.cag.directive.AddressDirective;
import org.pjp.cag.directive.Directive;
import org.pjp.cag.directive.TitleDirective;
import org.pjp.cag.exception.AbstractCAGException;
import org.pjp.cag.exception.assembly.ParseException;
import org.pjp.cag.exception.assembly.StorageException;
import org.pjp.cag.exception.assembly.UnknownDirectiveException;
import org.pjp.cag.io.PaperTape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Assembler parses the program text and enters the Order, number or character into the computer store.
 * @author developer
 */
final class Assembler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Assembler.class);

    private static final int QUERY = 5;

    private static final int MODIFIER = 4;

    private static final int ADDRESS = 3;

    private static final int MNEMONIC = 1;

    // TODO maybe improve regexp with optional witespace

    private static final Pattern DIRECTIVE = Pattern.compile("\\(([A-Z]+)( [0-9]+)?\\)");

    private static final Pattern ORDER = Pattern.compile("([A-Z]+)( ([0-9]+)(,[0-9]+)?( Q)?)?");

    private static final Pattern CHARACTER = Pattern.compile("^=[a-zA-Z0-9]");    // TODO more characters for Elliot 903 Telecode

    // https://www.regular-expressions.info/floatingpoint.html
    private static final Pattern NUMBER = Pattern.compile("^[-+][0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");

    private List<Directive> directives = new ArrayList<>();

    private boolean title;

    private int currentLocation = ZERO;

    /**
     * @return The list of Directives
     */
    List<Directive> getDirectives() {
        return directives;
    }

    /**
     * @param program The path for the program text
     * @param store The computer store
     * @return True if assembly was successful
     * @throws IOException
     */
    boolean assemble(Path program, Store store) throws IOException {
        try {
            Files.lines(program).forEach(l -> {
                LOGGER.debug(l);

                if (title) {
                    TitleDirective titleDirective = new TitleDirective(l);
                    directives.add(titleDirective);

                    PaperTape.out.println(titleDirective.getTitle());

                    title = false;
                } else {

                    Matcher matcher = DIRECTIVE.matcher(l);

                    if (matcher.matches()) {
                        String type = matcher.group(1);

                        if (TITLE.equals(type)) {
                            title = true;
                        } else if (WAIT.equals(type)) {
                            // end of assembly process for now
                            return;
                        } else {
                            int address;

                            switch (type) {
                            case EXECUTE:
                                address = Integer.parseInt(matcher.group(2).trim());
                                store.setControlAddress(address);
                                break;
                            case STORE:
                                address = Integer.parseInt(matcher.group(2).trim());
                                currentLocation = address;
                                break;
                            default:
                                throw new UnknownDirectiveException("the directive is unknown: " + type);
                            }

                            AddressDirective addressDirective = new AddressDirective(type, address);
                            directives.add(addressDirective);

                            LOGGER.debug("    " + addressDirective);

                        }
                    } else {
                        matcher = ORDER.matcher(l);

                        if (matcher.matches()) {
                            String orderNumberStr = matcher.group(MNEMONIC);
                            String addressStr = matcher.group(ADDRESS);
                            String modifierStr = matcher.group(MODIFIER);
                            boolean query = " Q".equals(matcher.group(QUERY));

                            if (modifierStr != null) {
                                modifierStr = modifierStr.replaceFirst("\\,", "");
                            }

                            Order order = Order.create(query, orderNumberStr, addressStr, modifierStr);

                            LOGGER.debug("    " + order);

                            storeWord(store, Word.create(order));

                        } else {
                            matcher = NUMBER.matcher(l);

                            if (matcher.matches()) {
                                float number = Float.parseFloat(matcher.group(0));

                                storeWord(store, Word.create(number));
                            } else {
                                matcher = CHARACTER.matcher(l);

                                if (matcher.matches()) {
                                    char character = matcher.group(0).charAt(1);

                                    storeWord(store, Word.create(character));
                                } else {
                                    throw new ParseException("failed to match line to order or directive: " + l);
                                }
                            }
                        }
                    }
                }
            });
        } catch (AbstractCAGException e) {
            System.out.printf("ERR %2d %4d\n", e.getErrorCode(), currentLocation);
            LOGGER.debug(e.getMessage(), e);

            // halt the assembly and prevent execution
            throw e;
        }

        return true;
    }

    private void storeWord(Store store, Word word) {
        if (currentLocation != ZERO) {
            store.setLocation(currentLocation, word);

            currentLocation++;

        } else {
            throw new StorageException("undefined current location while storing word (missing STORE directive)");
        }
    }
}
