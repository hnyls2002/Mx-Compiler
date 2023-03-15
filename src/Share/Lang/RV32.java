package Share.Lang;

import java.util.ArrayList;
import java.util.Arrays;

public class RV32 {
    public static final int MAX_ARG_NUM = 8;
    public static final int LO_IMM_LIMIT = (1 << 12) - 1;

    public enum BitWidth {
        b, h, w
    }

    public enum SPLabel {
        spilledReg, putSpilledArg, getSpilledArg, ra
    }

    public interface ASMOp {
        public String format();
    }

    public enum BinaryRegOp implements ASMOp {
        add, sub, mul, div, rem, sll, sra, and, or, xor, slt;

        @Override
        public String format() {
            return name();
        }
    }

    public enum BinaryImOp implements ASMOp {
        addi, slli, srai, andi, ori, xori, slti;

        @Override
        public String format() {
            return name();
        }
    }

    public enum BinaryZeroOp implements ASMOp {
        seqz, snez, sltz, sgtz;

        @Override
        public String format() {
            return name();
        }
    }

    public static final ArrayList<String> regList = new ArrayList<>(
            Arrays.asList("zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
                    "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5",
                    "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));

}
