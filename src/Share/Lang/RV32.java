package Share.Lang;

import java.util.ArrayList;
import java.util.Arrays;

public class RV32 {
    public static final int MAX_ARG_NUM = 8;
    public static final int LO_IMM_LIMIT = (1 << 12) - 1;

    public enum BitWidth {
        b, h, w
    }

    public static final ArrayList<String> regName = new ArrayList<>(Arrays.asList(
            "zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1", "a0",
            "a1", "a2", "a3", "a4", "a5", "a6", "a7", "s2", "s3", "s4", "s5",
            "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"));
}
