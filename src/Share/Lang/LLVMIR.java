package Share.Lang;

import java.util.Arrays;
import java.util.HashSet;

public class LLVMIR {

    interface IROp {
    }

    public static enum BOP implements IROp {
        add, sub, mul, sdiv, srem, shl, ashr, and, or, xor;
    }

    public static enum ICMPOP implements IROp {
        eq, ne, sgt, sge, slt, sle;
    }

    public static final HashSet<IROp> commutative = new HashSet<>(
            Arrays.asList(BOP.add, BOP.mul, BOP.and, BOP.or, BOP.xor, ICMPOP.eq, ICMPOP.ne));

    public enum CastType {
        bitcast, sext, zext, trunc;
    };
}
