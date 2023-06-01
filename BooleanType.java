import java.io.PrintStream;

public class BooleanType extends Token{
    private String type;

    private String name;
    public PrintStream writeToTxt;

    public BooleanType(String type, String name, PrintStream writeToTxt) {
        super(type,name,writeToTxt);
        this.type = type;
        this.writeToTxt = writeToTxt;
    }

    public void write(String type) {
        System.out.println(type);
        writeToTxt.println(type);
    }

    public String getType() {
        return type;
    }


}
