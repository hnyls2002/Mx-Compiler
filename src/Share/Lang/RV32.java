package Share.Lang;

public class RV32 {
    public static final int MAX_ARG_NUM = 8;
    public static final int LO_IMM_LIMIT = (1 << 12) - 1;

    public enum BitWidth {
        B, H, W
    }
}
