import java.io.PrintStream;

public class StringType extends Token{
        private String type;
        private String name;

        public PrintStream writeToTxt;

        public StringType(String type,String name, PrintStream writeToTxt) {
            super(type,name,writeToTxt);
            this.type = type;
            this.name = name;
            this.writeToTxt = writeToTxt;
        }
        public void write(String type, String name) {

            System.out.println(type + " (" + name + ")");
            writeToTxt.println(type + " (" + name + ")");
        }

        public String getType() {

            return type;
        }

    }


