package org.pjp.cag;

import static org.pjp.cag.cpu.Store.ZERO;
import static org.pjp.cag.order.AddressDirective.EXECUTE;
import static org.pjp.cag.order.AddressDirective.STORE;
import static org.pjp.cag.order.TitleDirective.TITLE;
import static org.pjp.cag.order.WaitDirective.WAIT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pjp.cag.cpu.Store;
import org.pjp.cag.cpu.Word;
import org.pjp.cag.dev.PaperTape;
import org.pjp.cag.exception.TranslationError;
import org.pjp.cag.exception.TranslationException;
import org.pjp.cag.order.AddressDirective;
import org.pjp.cag.order.Directive;
import org.pjp.cag.order.Order;
import org.pjp.cag.order.TitleDirective;
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

    private static final int FUNCTION = 1;

    private static final Pattern DIRECTIVE = Pattern.compile("\\( *([A-Z]+) *([0-9]+)? *\\)");

    private static final Pattern ORDER = Pattern.compile("([A-Z]+) *(([0-9]+) *(, *[0-9]+)? *(Q)?)?");

//    private static final Pattern CHARACTER = Pattern.compile("=[ \\!\"\\½\\$\\%\\&\\'\\(\\)\\*\\+\\,\\-\\.\\/0-9\\:\\;\\<\\=\\>\\`A-Z\\[\\£\\]\\@a-z]");

    // https://www.regular-expressions.info/floatingpoint.html
    private static final Pattern NUMBER = Pattern.compile("[-+][0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");

    private List<Directive> directives = new ArrayList<>();

    private boolean title;

    private int address = ZERO;

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
     */
    boolean assemble(Path program, Store store) {
        boolean result = true;

        try {
            innerAssemble(program, store);
        } catch (TranslationException e) {
            System.out.printf(e.getMessage(), address);

            // halt the assembly and prevent execution
            result = false;
        }

        return result;
    }

    void innerAssemble(Path program, Store store) throws TranslationException {
        try {
            for (String line : Files.readAllLines(program)) {
                assemble(line, store);
            }
        } catch (IOException e) {
            throw new TranslationException(TranslationError.ERR_1);
        }
    }

    void assemble(String line, Store store) throws TranslationException {
        LOGGER.debug(line);

        line = line.trim();

        if (title) {
            TitleDirective titleDirective = new TitleDirective(line);
            directives.add(titleDirective);

            PaperTape.out.println(line);

            title = false;
        } else {
            Matcher matcher = DIRECTIVE.matcher(line);

            if (matcher.matches()) {
                String type = matcher.group(1);

                if (TITLE.equals(type)) {
                    if (matcher.group(2) != null) {
                        throw new TranslationException(TranslationError.ERR_6);
                    }

                    title = true;

                } else if (WAIT.equals(type)) {
                    throw new UnsupportedOperationException("unsupported WAIT directive, legacy multi-part programs must be presented in a single text file");
                } else {
                    int directiveAddress;

                    switch (type) {
                    case EXECUTE:
                        directiveAddress = Integer.parseInt(matcher.group(2).trim());    // will parse because matched to number in the regex
                        store.controlRegister().setAddress(directiveAddress);
                        break;
                    case STORE:
                        directiveAddress = Integer.parseInt(matcher.group(2).trim());    // will parse because matched to number in the regex
                        address = directiveAddress;
                        break;
                    default:
                        throw new TranslationException(TranslationError.ERR_5);
                    }

                    AddressDirective addressDirective = new AddressDirective(type, directiveAddress);
                    directives.add(addressDirective);

                    LOGGER.debug("    " + addressDirective);

                }
            } else {
                matcher = ORDER.matcher(line);

                if (matcher.matches()) {
                    String functionStr = matcher.group(FUNCTION);
                    String addressStr = matcher.group(ADDRESS);
                    String modifierStr = matcher.group(MODIFIER);
                    boolean query = "Q".equals(matcher.group(QUERY));

                    if (modifierStr != null) {
                        modifierStr = modifierStr.replaceFirst("\\,", "").trim();
                    }

                    Order order = Order.create(query, functionStr, addressStr, modifierStr);

                    LOGGER.debug("    " + order);

                    storeWord(store, Word.create(order));

                } else {
                    matcher = NUMBER.matcher(line);

                    if (matcher.matches()) {
                        try {
                            long integer = Long.parseLong(line);

                            if (Math.abs(integer) > CAGMnemonicCode.MAX_INT) {
                                throw new TranslationException(TranslationError.ERR_3);
                            }

                            storeWord(store, Word.create(integer));

                        } catch (NumberFormatException e) {
                            float number = Float.parseFloat(line);

                            storeWord(store, Word.create(number));
                        }
                    } else {
                        throw new TranslationException(TranslationError.ERR_2);
                    }
                }
            }
        }
    }

    private void storeWord(Store store, Word word) throws TranslationException {
        if (address != ZERO) {
            store.setLocation(address, word);

            address++;

        } else {
            throw new TranslationException(TranslationError.ERR_4);
        }
    }
}
