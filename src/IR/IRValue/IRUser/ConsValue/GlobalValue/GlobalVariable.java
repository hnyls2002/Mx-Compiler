package IR.IRValue.IRUser.ConsValue.GlobalValue;

import AST.Util.TypeName;
import IR.IRType.IRPtType;
import IR.IRType.IRType;
import IR.IRValue.IRBaseValue;
import IR.Util.Transfer;

// global variable is address!!!!

public class GlobalVariable extends BaseGlobalValue {
    public boolean isInit = false;
    public IRBaseValue initValue = null;
    public IRFn initFn = null;

    // for organizer
    public IRType derefType = null;

    // varType in , addr type built
    public GlobalVariable(String varNameString, TypeName gVarTypeName) { // from typename
        super(new IRPtType(Transfer.typeTransfer(gVarTypeName), 1));
        this.derefType = ((IRPtType) valueType).derefType();
        this.constName = varNameString;
    }

    public GlobalVariable(String varNameString, IRType gVarType) { // from ir type
        super(new IRPtType(gVarType, 1));
        this.derefType = ((IRPtType) valueType).derefType();
        this.constName = varNameString;
    }

    @Override
    public String defToString() {
        // null const don't have a type
        return derefType.toString() + ' ' + initValue.useToString();
    }

    @Override
    public String getName() {
        return constName == null ? super.getName() : "@" + constName;
    }
}
