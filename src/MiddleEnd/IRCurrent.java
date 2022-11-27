package MiddleEnd;

import java.util.Stack;

import Frontend.Util.Scopes.Scope;
import Frontend.Util.Types.BaseType;
import IR.IRValue.IRBasicBlock;
import IR.IRValue.IRUser.ConsValue.GlobalValue.IRFn;

public class IRCurrent {
    private class LoopInfo {
        public IRBasicBlock breakBlock, continueBlock;

        public LoopInfo(IRBasicBlock breakBlock, IRBasicBlock continueBlock) {
            this.breakBlock = breakBlock;
            this.continueBlock = continueBlock;
        }

        public IRBasicBlock getContinue() {
            return continueBlock;
        }

        public IRBasicBlock getBreak() {
            return breakBlock;
        }
    }

    public IRFn fn = null;
    public IRBasicBlock block = null;
    public Scope scope = null;
    public BaseType whoseMember = null;
    public boolean justNeedAddr = false;
    public Stack<LoopInfo> loopInfoStack = new Stack<>();

    public void pushInfo(IRBasicBlock breakBlock, IRBasicBlock continueBlock) {
        this.loopInfoStack.push(new LoopInfo(breakBlock, continueBlock));
    }

    public void popInfo() {
        this.loopInfoStack.pop();
    }

    public IRBasicBlock getCurBreak() {
        return this.loopInfoStack.peek().getBreak();
    }

    public IRBasicBlock getCurContinue() {
        return this.loopInfoStack.peek().getContinue();
    }

}
