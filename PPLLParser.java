import java.io.PrintStream;


import java.text.ParseException;

import java.util.List;

public class PPLLParser {
    private List<Token> tokens; //It holds the objects we read in the main method.
    private int i;
    public PrintStream writeToTxt;
    private int space = 0;//The global variable we created to navigate the arraylist.
    // We increase this variable as we create an object.
    ;
    public PPLLParser(List<Token> tokens,PrintStream writeToTxt) {
        this.tokens = tokens;
        this.writeToTxt = writeToTxt;
    }

    // Implement the remaining grammar rules as separate methods
    public void parse() {
        try {
            program();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private String updatedSpace(int space) {
        String spaceStr = "";
        for(int i = 0; i<space;i++) {
            spaceStr += " ";
        }
        return spaceStr;
    }

    private void program() throws ParseException {
        System.out.println("<Program>");
        writeToTxt.println("<Program>");
        space++;
        topLevelForm();
        System.out.println("<Program>");
        writeToTxt.println("<Program>");
        System.out.println(" _ ");
        writeToTxt.println(" _ ");
    }

    private void topLevelForm() throws ParseException {
        System.out.println(updatedSpace(space) + "<TopLevelForm>");
        writeToTxt.println(updatedSpace(space) + "<TopLevelForm>");
        space++;
        if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            secondLevelForm();
            space--;
            if (tokens.get(i).getType().equals("RIGHTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("RIGHTPAR", ")");
                i++;
            }
        }

    }

    private void secondLevelForm() throws ParseException {
        System.out.println(updatedSpace(space)+"<SecondLevelForm>");
        writeToTxt.println(updatedSpace(space)+"<SecondLevelForm>");
        space++;
        if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            funCall();
            space--;
            if (tokens.get(i).getType().equals("RIGHTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("RIGHTPAR", ")");
                i++;
            }else{
                System.out.println("SYNTAX ERROR");
                writeToTxt.println("SYNTAX ERROR");
            }
        } else if(tokens.get(i).getType().equals("DEFINE")){
            definition();
            space--;
        }

    }

    private void definition() throws ParseException {
        System.out.println(updatedSpace(space) +"<Definition>");
        writeToTxt.println(updatedSpace(space) +"<Definition>");
        space++;
        if (tokens.get(i).getType().equals("DEFINE")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("DEFINE", "define");
            i++;
            definitionRight();
            space--;
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }
    }

    private void definitionRight() throws ParseException {

        System.out.println(updatedSpace(space) +"<DefinitionRight>");
        writeToTxt.println(updatedSpace(space) +"<DefinitionRight>");
        space++;
        if (tokens.get(i).getType().equals("IDENTIFIER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
            i++;
            expression();
            space--;
        } else if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            if (tokens.get(i).getType().equals("IDENTIFIER")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
                i++;
                argList();
                space--;
                if (tokens.get(i).getType().equals("RIGHTPAR")) {
                    System.out.print(updatedSpace(space));
                    writeToTxt.print(updatedSpace(space));
                    tokens.get(i).write("RIGHTPAR", ")");
                    i++;
                    statements();
                    space--;
                }else{
                    System.out.println("SYNTAX ERROR");
                    writeToTxt.println("SYNTAX ERROR");
                }
            }
        }

    }

    private void argList() throws ParseException {
        System.out.println(updatedSpace(space) +"<ArgList>");
        writeToTxt.println(updatedSpace(space) +"<ArgList>");
        space++;
        if (tokens.get(i).getType().equals("IDENTIFIER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
            i++;
            argList();
            space--;
        } else if (!tokens.get(i).getType().equals("IDENTIFIER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            System.out.println(" _ ");
            writeToTxt.println(" _ ");
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }

    }

    private void statements() throws ParseException {
        System.out.println(updatedSpace(space) +"<Statements>");
        writeToTxt.println(updatedSpace(space) +"<Statements>");
        space++;
        if (tokens.get(i).getType().equals("IDENTIFIER") || tokens.get(i).getType().equals("NUMBER")
                || tokens.get(i).getType().equals("CHAR") || tokens.get(i).getType().equals("BOOLEAN")
                || tokens.get(i).getType().equals("STRING") || tokens.get(i).getType().equals("LEFTPAR")) {
            expression();
            space--;
        } else if (tokens.get(i).getType().equals("DEFINE")) {
            definition();
            space--;
            statements();
            space--;
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }
    }

    private void expressions() throws ParseException {
        System.out.println(updatedSpace(space) +"<Expressions>");
        writeToTxt.println(updatedSpace(space) +"<Expressions>");
        space++;
        if (!tokens.get(i).getType().equals("IDENTIFIER") && !tokens.get(i).getType().equals("NUMBER")
                && !tokens.get(i).getType().equals("CHAR") && !tokens.get(i).getType().equals("BOOLEAN")
                && !tokens.get(i).getType().equals("STRING") && !tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            System.out.println(" _ ");
            writeToTxt.println(" _ ");
        } else {
            expression();
            space--;
            expressions();
            space--;
        }
    }

    private void expression() throws ParseException {
        System.out.println(updatedSpace(space) +"<Expression>");
        writeToTxt.println(updatedSpace(space) +"<Expression>");
        space++;
        if (tokens.get(i).getType().equals("IDENTIFIER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
            i++;
        } else if (tokens.get(i).getType().equals("NUMBER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("NUMBER", tokens.get(i).getName());
            i++;
        } else if (tokens.get(i).getType().equals("CHAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("CHAR", tokens.get(i).getName());
            i++;
        } else if (tokens.get(i).getType().equals("BOOLEAN")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("BOOLEAN", tokens.get(i).getName());
            i++;
        } else if (tokens.get(i).getType().equals("STRING")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("STRING", tokens.get(i).getName());
            i++;
        } else if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            expr();
            space--;
            if (tokens.get(i).getType().equals("RIGHTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("RIGHTPAR", ")");
                i++;
            }else{
                System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
                writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
            }
        }
    }

    private void expr() throws ParseException {
        System.out.println(updatedSpace(space) +"<Expr>");
        writeToTxt.println(updatedSpace(space) +"<Expr>");
        space++;
        if (tokens.get(i).getType().equals("LET")) {
            letExpression();
            space--;
        } else if (tokens.get(i).getType().equals("COND")) {
            condExpression();
            space--;
        } else if (tokens.get(i).getType().equals("IF")) {
            ifExpression();
            space--;
        } else if (tokens.get(i).getType().equals("BEGIN")) {
            beginExpression();
            space--;
        } else {
            funCall();
            space--;
        }

    }

    private void funCall() throws ParseException {
        System.out.println(updatedSpace(space) +"<FunCall>");
        writeToTxt.println(updatedSpace(space) +"<FunCall>");
        space++;
        if (tokens.get(i).getType().equals("IDENTIFIER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
            i++;
            expressions();
            space--;
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }

    }

    private void letExpression() throws ParseException {
        System.out.println(updatedSpace(space) +"<LetExpression>");
        writeToTxt.println(updatedSpace(space) +"<LetExpression>");
        space++;
        if (tokens.get(i).getType().equals("LET")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LET", "let");
            i++;
            letExpr();
            space--;
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }
    }

    private void letExpr() throws ParseException {
        System.out.println(updatedSpace(space) +"<LetExpr>");
        writeToTxt.println(updatedSpace(space) +"<LetExpr>");
        space++;
        if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            varDefs();
            space--;
            if (tokens.get(i).getType().equals("RIGHTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("RIGHTPAR", ")");
                i++;
                statements();
                space--;
            }else{
                System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
                writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
            }
        }

        else if (tokens.get(i).getType().equals("IDENTIFIER")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
            i++;
            if (tokens.get(i).getType().equals("LEFTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("LEFTPAR", "(");
                i++;
                varDefs();
                space--;
                if (tokens.get(i).getType().equals("RIGHTPAR")) {
                    System.out.print(updatedSpace(space));
                    writeToTxt.print(updatedSpace(space));
                    tokens.get(i).write("RIGHTPAR", ")");
                    i++;
                    statements();
                    space--;
                }else{
                    System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
                    writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
                }
            }
        }
    }

    private void varDefs() throws ParseException {
        System.out.println(updatedSpace(space) +"<VarDefs>");
        writeToTxt.println(updatedSpace(space) +"<VarDefs>");
        space++;
        if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            if (tokens.get(i).getType().equals("IDENTIFIER")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("IDENTIFIER", tokens.get(i).getName());
                i++;
                expression();
                space--;
                if (tokens.get(i).getType().equals("RIGHTPAR")) {
                    System.out.print(updatedSpace(space));
                    writeToTxt.print(updatedSpace(space));
                    tokens.get(i).write("RIGHTPAR", ")");
                    i++;
                    varDef();
                    space--;
                }else{
                    System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
                    writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
                }

            }
        }
    }

    private void varDef() throws ParseException {
        System.out.println(updatedSpace(space) +"<VarDef>");
        writeToTxt.println(updatedSpace(space) +"<VarDef>");
        space++;
        if (!tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            System.out.println(" _ ");
            writeToTxt.println(" _ ");
        } else {
            varDefs();
            space--;
        }

    }

    private void condExpression() throws ParseException {
        System.out.println(updatedSpace(space) +"<CondExpression>");
        writeToTxt.println(updatedSpace(space) +"<CondExpression>");
        space++;
        if (tokens.get(i).getType().equals("COND")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("COND", "cond");
            i++;
            condBranches();
            space--;

        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }

    }

    private void condBranches() throws ParseException {
        System.out.println(updatedSpace(space) +"<CondBranches>");
        writeToTxt.println(updatedSpace(space) +"<CondBranches>");
        space++;
        if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            expression();
            space--;
            statements();
            space--;
            if (tokens.get(i).getType().equals("RIGHTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("RIGHTPAR", ")");
                i++;
                condBranch();
                space--;
            }else{
                System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
                writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
            }
        }
    }

    private void condBranch() throws ParseException {
        System.out.println(updatedSpace(space) +"<CondBranch>");
        writeToTxt.println(updatedSpace(space) +"<CondBranch>");
        space++;
        if (tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("LEFTPAR", "(");
            i++;
            expression();
            space--;
            statements();
            space--;
            if (tokens.get(i).getType().equals("RIGHTPAR")) {
                System.out.print(updatedSpace(space));
                writeToTxt.print(updatedSpace(space));
                tokens.get(i).write("RIGHTPAR", ")");
                i++;
            }else{
                System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
                writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
            }

        } else {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            System.out.println(" _ ");
            writeToTxt.println(" _ ");
        }

    }

    private void ifExpression() throws ParseException {

        System.out.println(updatedSpace(space) +"<IfExpression>");
        writeToTxt.println(updatedSpace(space) +"<IfExpression>");
        space++;
        if (tokens.get(i).getType().equals("IF")){
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("IF", "if");
            i++;
            expression();
            space--;
            expression();
            space--;
            endExpression();
            space--;
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }
    }

    private void endExpression() throws ParseException {
        System.out.println(updatedSpace(space) +"<EndExpression>");
        writeToTxt.println(updatedSpace(space) +"<EndExpression>");
        space++;
        if (!tokens.get(i).getType().equals("IDENTIFIER") && !tokens.get(i).getType().equals("NUMBER")
                && !tokens.get(i).getType().equals("CHAR") && !tokens.get(i).getType().equals("BOOLEAN")
                && !tokens.get(i).getType().equals("STRING") && !tokens.get(i).getType().equals("LEFTPAR")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            System.out.println(" _ ");
            writeToTxt.println(" _ ");
        } else {
            expression();
            space--;
        }

    }

    private void beginExpression() throws ParseException {
        System.out.println(updatedSpace(space) +"<BeginExpression>");
        writeToTxt.println((space) +"<BeginExpression>");
        space++;
        if (tokens.get(i).getType().equals("BEGIN")) {
            System.out.print(updatedSpace(space));
            writeToTxt.print(updatedSpace(space));
            tokens.get(i).write("BEGIN", "begin");
            i++;
            statements();
            space--;
        }else{
            System.out.println("SYNTAX ERROR");//It checks for syntax error and prints error message to console
            writeToTxt.println("SYNTAX ERROR");//It checks for syntax error and prints the error message to the output file
        }
    }

}
