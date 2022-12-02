package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ASMPrinter {
    String fileName;
    File asm;
    PrintStream ps;

    public ASMPrinter() throws FileNotFoundException {
        asm = new File(fileName);
        ps = new PrintStream(asm);
        System.setOut(ps);
    }
}
