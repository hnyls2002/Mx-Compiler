package Share;

public class MyException extends RuntimeException {
    public String msg;

    public MyException(String msg) {
        super();
        this.msg = msg;
        this.msg = "\n" + "~".repeat(100) + "\n" + msg + "\n";
    }

    @Override
    public String toString() {
        return super.toString() + msg;
    }
}
