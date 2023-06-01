import java.io.PrintStream;
import java.util.Locale;

public class Keyword extends Token{
    private String type;
    private String name;
    public PrintStream writeToTxt;


    public Keyword(String type,String name, PrintStream writeToTxt) {
        super(type,name,writeToTxt);
        this.type = type;
        this.name = name;
        this.writeToTxt = writeToTxt;
    }
    public void write(String type, String name) {
        String k_type= type.toUpperCase();
        System.out.println(k_type + " (" + name + ")");
        writeToTxt.println(k_type + " (" + name + ")");
    }

    public String KeywordType(String name){
        String str="";
        if(name.equals("define")){
            str= "define";
        }else if(name.equals("let")){
            str= "let";
        }else if(name.equals("cond")){
            str= "cond";
        }else if(name.equals("if")){
            str= "if";
        }else if(name.equals("begin")){
            str= "begin";
        }
        return str;
    }
    public String getType() {

        return type;
    }

    public String getName() {

        return name;
    }
}
