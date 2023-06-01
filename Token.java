import java.io.PrintStream;

public class Token {
    private String type;
    private String name;
    public PrintStream writeToTxt;


    public Token(String type, String name, PrintStream writeToTxt) {
        this.type = type;
        this.name=name;
        this.writeToTxt = writeToTxt;
    }

    public void write(String type, String name) {
        System.out.println(type + " (" + name + ")");
        writeToTxt.println(type + " (" + name + ")");
    }

    public String getType() {

        return type;
    }
    public String getName() {

        return name;
    }

}
