package Share.Lang;

public class LLVMIR {

    public static enum BOP {
        add, sub, mul, sdiv, srem, shl, ashr, and, or, xor;

        @Override
        public String toString() {
            return name();
        }
    }

    public static enum ICMPOP {
        eq, ne, sgt, sge, slt, sle;

        @Override
        public String toString() {
            return name();
        }
    }

    public enum CastType {
        bitcast, sext, zext, trunc;

        @Override
        public String toString() {
            return name();
        }
    };
}
