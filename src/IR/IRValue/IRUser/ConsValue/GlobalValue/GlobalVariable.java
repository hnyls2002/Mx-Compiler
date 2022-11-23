package IR.IRValue.IRUser.ConsValue.GlobalValue;

import Frontend.Util.TypeName;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.IRValue.IRUser.Inst.GEPInst;
import IR.Util.Transfer;

// global variable is address!!!!

public class GlobalVariable extends BaseGlobalValue {
    public String varNameString;
    public boolean isInit = false;
    public IRBaseValue initData = null;

    // for organizer
    public IRType derefType = null;
    public IRBaseValue initValue = null;

    // varType in , addr type built
    public GlobalVariable(String varNameString, TypeName gVarTypeName) { // from typename
        super(new IRPtType(Transfer.typeTransfer(gVarTypeName), 1));
        this.varNameString = varNameString;
    }

    public GlobalVariable(String varNameString, IRType gVarType) { // from ir type
        super(new IRPtType(gVarType, 1));
        this.varNameString = varNameString;
    }

    @Override
    public String defToString() {
        if (initValue instanceof GEPInst)
            return initValue.defToString();
        return initValue.useToString();
    }

    @Override
    public String getName() {
        return varNameString == null ? super.getName() : "@" + varNameString;
    }
}
