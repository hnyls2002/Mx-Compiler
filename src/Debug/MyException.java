package Debug;

public class MyException extends RuntimeException {
    public String msg;

    public MyException(String msg) {
        super();
        this.msg = msg;
        this.msg = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" + "in IR phase\n" + msg + "\n";
    }

    @Override
    public String toString() {
        return super.toString() + msg;
    }
}
