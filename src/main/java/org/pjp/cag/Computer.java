package org.pjp.cag;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class Computer {

    // TODO slf4j

    private static final String USAGE = "usage: org.pjp.cag.Computer <filename>";

    public static void main(String[] args) {
        if (args.length == 1) {
            Path path = Paths.get(args[0]);

            Store store = new Store();

            try {
                Assembler assembler = new Assembler();
                assembler.assemble(path, store);

                System.out.println("----------------------------------------");

                store.dump();

                System.out.println("----------------------------------------");

                Interpreter interpreter = new Interpreter();
                interpreter.interpret(store);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.err.println(USAGE);
        }
    }

    private Computer() {
        // prevent instantiation
    }

}
