package org.pjp.cag;

import static org.pjp.cag.directive.AddressDirective.EXECUTE;
import static org.pjp.cag.directive.AddressDirective.STORE;
import static org.pjp.cag.directive.TitleDirective.TITLE;

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
import org.pjp.cag.exception.ParseException;
import org.pjp.cag.exception.StorageException;

public final class Assembler {

    private static final int MODIFIER_GROUP = 5;

    private static final int ADDRESS_GROUP = 4;

    private static final Pattern DIRECTIVE = Pattern.compile("\\(([A-Z]+)( [0-9]+)?\\)");

    private static final Pattern ORDER = Pattern.compile("(Q)?([A-Z]+)( ([0-9]+)(,[0-9]+)?)?");

    private static final Pattern CHARACTER = Pattern.compile("");   // TODO characters

    private static final Pattern NUMBER = Pattern.compile("");      // TODO numbers

    private List<Directive> directives = new ArrayList<>();

    private boolean title;

    private int currentLocation = -1;

    public List<Directive> getDirectives() {
        return directives;
    }

    public void assemble(Path program, Store store) throws IOException {
        Files.lines(program).forEach(l -> {
            System.out.println(l);

            if (title) {
                TitleDirective titleDirective = new TitleDirective(l);
                directives.add(titleDirective);

                System.out.println(titleDirective.getTitle());

                title = false;
            } else {

                Matcher matcher = DIRECTIVE.matcher(l);

                if (matcher.matches()) {
                    String type = matcher.group(1);

                    if (TITLE.equals(type)) {
                        title = true;
                    } else {
                        int address = Integer.parseInt(matcher.group(2).trim());
                        AddressDirective addressDirective = new AddressDirective(type, address);
                        directives.add(addressDirective);

                        System.out.println("    " + addressDirective);

                        switch (addressDirective.getType()) {
                        case EXECUTE:
                            store.setControlAddress(addressDirective.getAddress());
                            break;
                        case STORE:
                            currentLocation = addressDirective.getAddress();
                            break;
                        default:
                            throw new IllegalStateException();
                        }

                    }
                } else {
                    matcher = ORDER.matcher(l);

                    if (matcher.matches()) {
                        // System.out.println("    ORDER -> " + matcher.group(1) + " | " + matcher.group(2) + " | " + matcher.group(3) + " | " + matcher.group(4) + " | " + matcher.group(5));

                        boolean query = "Q".equals(matcher.group(1));
                        String orderNumberStr = matcher.group(2);
                        String addressStr = matcher.group(ADDRESS_GROUP);
                        String modifierStr = matcher.group(MODIFIER_GROUP);

                        if (modifierStr != null) {
                            modifierStr = modifierStr.replaceFirst("\\,", "");
                        }

                        Order order = Order.create(query, orderNumberStr, addressStr, modifierStr);

                        System.out.println("    " + order);

                        if (currentLocation != -1) {
                            store.setLocation(currentLocation, Word.create(order));

                            currentLocation++;

                        } else {
                            throw new StorageException("undefined current location while storing order (missing STORE directive)");
                        }

                    } else {
                        throw new ParseException("failed to match line to order or directive");
                    }
                }
            }
        });
    }
}
