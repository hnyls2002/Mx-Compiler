package Share.Lang;

public class LLVMIR {

    public static enum BOP {
        add, sub, mul, sdiv, srem, shl, ashr, and, or, xor;
    }

    public static enum ICMPOP {
        eq, ne, sgt, sge, slt, sle;
    }

    public enum CastType {
        bitcast, sext, zext, trunc;
    };
}
