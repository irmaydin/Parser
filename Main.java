
// 150120013 Irem Aydin
// 150120009 Hatice Yavuzsan
// 150121013 Irem Kiranmezar


import java.io.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main{

    // This method returns whether the string data is identifier or not.
    public static boolean isIdentifier(String str) {
        boolean valid = false;

        String allowedChars = "!*\":<=>?abcdefghijklmnopqrstuvyzwx";
        String allowedChars2 = "+-.0123456789abcdefghijklmnopqrstuvyzwx";
        if (!isBoo(str) && !isKeyword(str)) {
            if (str.length() == 1) {
                if (str.charAt(0) == '.' || str.charAt(0) == '+' || str.charAt(0) == '-') {
                    valid = true;
                }

                else if (allowedChars.indexOf(str.charAt(0)) == -1) {
                    return false;
                } else
                    valid = true;

            } else if(str.length()>1){
                if (str.charAt(0) == '.' || str.charAt(0) == '+' || str.charAt(0) == '-') {
                    for (int i = 1; i < str.length(); i++) {
                        if (allowedChars2.indexOf(str.charAt(i)) == -1) {
                            return false;
                        } else
                            valid = true;
                    }
                } else {
                    if (allowedChars.indexOf(str.charAt(0)) == -1) {
                        return false;
                    } else {
                        for (int i = 1; i < str.length(); i++) {
                            if (allowedChars2.indexOf(str.charAt(i)) == -1) {
                                return false;
                            } else
                                valid = true;
                        }
                    }

                }

            }
        }

        return valid;
    }


    // This method returns whether the string data is boolean or not.
    public static boolean isBoo(String str) {
        boolean valid = false;

        if (str.equals("true") || str.equals("false")) {
            valid = true;
        } else
            return false;

        return valid;
    }

    public static void error(int r, String c){
        System.out.println("LEXICAL ERROR [" + r +":"+ (c.indexOf(0)) + "]: Invalid token " + "'" + c + "'" );
    }

    // This method returns whether the string data is number or not.
    public static boolean isNumber(String str) {
        boolean valid = false;
        boolean dot = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '.') {
                if (dot) {
                    dot = false;
                    break;
                }
                dot = true;
            }
        }

        boolean e = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'e' || str.charAt(i) == 'E') {
                if (e) {
                    e = false;
                    break;
                }
                e = true;
            }
        }
        if (str.length() >= 2) {
            if (str.charAt(0) == '0' && str.charAt(1) == 'x') {
                String allowedChars = "0123456789abcdefABCDEF";
                for (int i = 2; i < str.length(); i++) {
                    if (allowedChars.indexOf(str.charAt(i)) == -1) {
                        return false;
                    } else
                        valid = true;
                }
            } else if (str.charAt(0) == '0' && str.charAt(1) == 'b') {
                for (int i = 2; i < str.length(); i++) {
                    if (str.charAt(i) == '1' || str.charAt(i) == '0') {
                        valid = true;
                    } else
                        return false;
                }
            }

            else if (str.charAt(0) == '-' || str.charAt(0) == '+') {
                String allowedChars = "0123456789";
                if (str.contains(".") && dot) {
                    String[] array = str.split("\\.");

                    if ((str.contains("e")) && e) {
                        if (str.charAt(1) == '.') {
                            String[] afterDot = array[1].split("e");

                            for (int i = 0; i < afterDot[0].length(); i++) {
                                if (allowedChars.indexOf(afterDot[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }

                            if (afterDot[1].charAt(0) == '-' || afterDot[1].charAt(0) == '+') {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }

                            } else if (Character.isDigit(str.charAt(0))) {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }
                            }

                        } else {

                            for (int i = 1; i < array[0].length(); i++) {
                                if (allowedChars.indexOf(array[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                            String[] afterDot = array[1].split("e");
                            for (int i = 0; i < afterDot[0].length(); i++) {
                                if (allowedChars.indexOf(afterDot[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }

                            if (afterDot[1].charAt(0) == '-' || afterDot[1].charAt(0) == '+') {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }

                            } else if (Character.isDigit(afterDot[1].charAt(0))) {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }
                            }
                        }
                    } else {
                        if (str.charAt(1) == '.') {

                            for (int i = 0; i < str.length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        } else {

                            for (int i = 1; i < str.length(); i++) {
                                if (allowedChars.indexOf(array[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                            for (int i = 0; i < str.length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }

                        }
                    }
                } else if (!str.contains(".") && (str.contains("e")) && e) {
                    String[] array = str.split("e");

                    if (str.charAt(1) == 'e') {
                        if (array[1].charAt(0) == '-' || array[1].charAt(0) == '+') {
                            for (int i = 1; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        } else {
                            for (int i = 0; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        }
                    } else {
                        for (int i = 1; i < array[0].length(); i++) {
                            if (allowedChars.indexOf(array[0].charAt(i)) == -1) {
                                return false;
                            } else
                                valid = true;
                        }
                        if (array[1].charAt(0) == '-' || array[1].charAt(0) == '+') {
                            for (int i = 1; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        } else {
                            for (int i = 0; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        }

                    }
                } else {
                    for (int i = 1; i < str.length(); i++) {
                        if (allowedChars.indexOf(str.charAt(i)) == -1) {
                            return false;
                        } else
                            valid = true;
                    }
                }
            } else if (Character.isDigit(str.charAt(0)) || str.charAt(0) == '.' || str.charAt(0) == 'e') {
                String allowedChars = "0123456789";
                if (str.contains(".") && dot) {
                    String[] array = str.split("\\.");


                    if ((str.contains("e")) && e) {
                        if (str.charAt(0) == '.') {
                            String[] afterDot = array[1].split("e");

                            for (int i = 0; i < afterDot[0].length(); i++) {
                                if (allowedChars.indexOf(afterDot[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }

                            if (afterDot[1].charAt(0) == '-' || afterDot[1].charAt(0) == '+') {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }

                            } else if (Character.isDigit(str.charAt(0))) {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }
                            }

                        } else {

                            for (int i = 0; i < array[0].length(); i++) {
                                if (allowedChars.indexOf(array[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                            String[] afterDot = array[1].split("e");
                            for (int i = 0; i < afterDot[0].length(); i++) {
                                if (allowedChars.indexOf(afterDot[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }

                            if (afterDot[1].charAt(0) == '-' || afterDot[1].charAt(0) == '+') {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }

                            } else if (Character.isDigit(afterDot[1].charAt(0))) {
                                for (int i = 1; i < afterDot[1].length(); i++) {
                                    if (allowedChars.indexOf(afterDot[1].charAt(i)) == -1) {
                                        return false;
                                    } else
                                        valid = true;
                                }
                            }
                        }
                    } else {
                        if (str.charAt(0) == '.') {

                            for (int i = 0; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        } else {

                            for (int i = 1; i < array[0].length(); i++) {
                                if (allowedChars.indexOf(array[0].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                            for (int i = 0; i < str.length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }

                        }
                    }
                } else if (!str.contains(".") && (str.contains("e")) && e) {
                    String[] array = str.split("e");

                    if (str.charAt(0) == 'e') {

                        if (array[1].charAt(0) == '-' || array[1].charAt(0) == '+') {
                            for (int i = 1; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        } else {
                            for (int i = 0; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        }
                    } else {
                        for (int i = 0; i < array[0].length(); i++) {
                            if (allowedChars.indexOf(array[0].charAt(i)) == -1) {
                                return false;
                            } else
                                valid = true;
                        }
                        if (array[1].charAt(0) == '-' || array[1].charAt(0) == '+') {
                            for (int i = 1; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        } else {
                            for (int i = 0; i < array[1].length(); i++) {
                                if (allowedChars.indexOf(array[1].charAt(i)) == -1) {
                                    return false;
                                } else
                                    valid = true;
                            }
                        }

                    }
                }
            }
        } else {
            if (Character.isDigit(str.charAt(0))) {
                valid = true;
            }
        }
        return valid;
    }


    // This method returns whether the string data is keyword or not.
    public static boolean isKeyword(String str) {
        boolean valid = false;

        if (str.equals("define") || str.equals("let") || str.equals("cond") || str.equals("if")
                || str.equals("begin")) {
            valid = true;
        }

        return valid;
    }



    // This method returns keyword type.
    public static String keyword(String str) {

        String keywordType = "";

        if (str.equals("define")) {
            keywordType = "DEFINE";
        } else if (str.equals("let")) {
            keywordType = "LET";
        } else if (str.equals("cond")) {
            keywordType = "COND";
        } else if (str.equals("if")) {
            keywordType = "IF";
        } else if (str.equals("begin")) {
            keywordType = "BEGIN";
        }

        return keywordType;
    }


    // This method returns whether the string data is char or not.
    public static boolean isChar(String str) {
        boolean valid = false;

        if (str.length() == 4 || str.length() == 3) {
            if (str.length() == 4) {
                if (str.charAt(0) == '\'' && str.charAt(3) == '\'') {
                    if (str.charAt(1) == '\\') {
                        if (str.charAt(2) == '\\') {
                            valid = true;
                        } else if (str.charAt(2) == '\'') {
                            valid = true;
                        }else{
                            return false;
                        }
                    }

                }
            } else {
                if (str.charAt(0) == '\'' && str.charAt(2) == '\'') {
                    if (str.charAt(1) != ' ') {
                        valid = true;
                    }else {
                        return false;
                    }
                }
            }
        } else
            return false;
        return valid;
    }

    public static String str(String[] str, int num) {
        String newStr=str[num];

        if(str.length>0) {
            for (int j = num + 1; j < str.length; j++) {
                //System.out.println(j+":"+str[j]+" length:"+str[j].length());
                if(str[j].length()>2){
                    if(str[j].charAt(str[j].length()-1)!='"' && str[j].charAt(str[j].length()-2)!='"' && str[j].charAt(str[j].length()-3)!='\\'){
                        newStr+=" "+str[j];
                        //rp++;
                    }else if(str[j].charAt(str[j].length()-1)=='"' && str[j].charAt(str[j].length()-2)!='"' ){
                        newStr+=" "+str[j];
                        //rp++;

                    }else{
                        newStr+=" "+str[j];
                        //rp++;
                        break;
                    }

                }else if(str[j].length()==2){
                    if(str[j].charAt(1)=='"' && str[j].charAt(0)!='\\'){
                        newStr+=" "+str[j];
                        //rp++;
                        break;
                    }else{
                        newStr+=" "+str[j];
                        //rp++;
                    }

                }else{
                    if(str[j].charAt(0)!='"' || str[j].charAt(0)!='\\'){
                        newStr+=" "+str[j];
                        //rp++;
                    }else{
                        newStr+=" "+str[j];
                        //rp++;
                        break;
                    }
                }


            }
        }



        return newStr;
    }
    // This method returns whether the string data is string or not.
    public static boolean isStr(String[] str, int num) {
        String newStr=str[num];

        if(str.length>0) {
            for (int j = num + 1; j < str.length; j++) {
                //System.out.println(j+":"+str[j]+" length:"+str[j].length());
                if(str[j].length()>2){
                    if(str[j].charAt(str[j].length()-1)!='"' && str[j].charAt(str[j].length()-2)!='"' && str[j].charAt(str[j].length()-3)!='\\'){
                        newStr+=" "+str[j];
                        //rp++;
                    }else if(str[j].charAt(str[j].length()-1)=='"' && str[j].charAt(str[j].length()-2)!='"' ){
                        newStr+=" "+str[j];
                        //rp++;

                    }else{
                        newStr+=" "+str[j];
                        //rp++;
                        break;
                    }

                }else if(str[j].length()==2){
                    if(str[j].charAt(1)=='"' && str[j].charAt(0)!='\\'){
                        newStr+=" "+str[j];
                        //rp++;
                        break;
                    }else{
                        newStr+=" "+str[j];
                        //rp++;
                    }

                }else{
                    if(str[j].charAt(0)!='"' || str[j].charAt(0)!='\\'){
                        newStr+=" "+str[j];
                        //rp++;
                    }else{
                        newStr+=" "+str[j];
                        //rp++;
                        break;
                    }
                }


            }
        }
        //String[] rStr=newStr.split(" ");
        if(newStr.charAt(0)=='"' && newStr.charAt(newStr.length()-1)=='"'){
            return true;

        }

        return false;
    }


    public static void main(String[] args) throws ParseException {


        Scanner input = new Scanner(System.in);

        System.out.print("Input file name : ");
        String inputFile = input.next();

        ArrayList<Token> tokens = new ArrayList<Token>();

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFile)))) {
            PrintStream writeToTxt = new PrintStream(new FileOutputStream("output.txt", false));
            int row = 1;

            while (scanner.hasNextLine()) {
                String str = scanner.nextLine();

                String sp = str.trim();
                String[] stringArray = sp.split(" "); //

                String newStr = sp.replaceAll("[\\[\\]\\{\\}\\(\\)]", "");
                String[] arr = newStr.split(" ");


                for (int j = 0; j < stringArray.length; j++) {
                    for (int i = 0; i < stringArray[j].length(); i++) {

                        if (stringArray[j].charAt(i) == '(') {
                            Bracket brackets = new Bracket("LEFTPAR", "(",writeToTxt);
                            tokens.add(brackets);


                        } else if (stringArray[j].charAt(i) == ')') {
                            Bracket brackets = new Bracket("RIGHTPAR", ")",writeToTxt);
                            tokens.add(brackets);

                        } else if (isKeyword(arr[j]) && keyword(arr[j]).equals("DEFINE") && stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            Keyword keywords = new Keyword("DEFINE", "define" ,writeToTxt);
                            tokens.add(keywords);

                        }
                        else if (isKeyword(arr[j]) && keyword(arr[j]).equals("LET") && stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            Keyword keywords = new Keyword("LET", "let",writeToTxt);
                            tokens.add(keywords);

                        }
                        else if (isKeyword(arr[j]) && keyword(arr[j]).equals("COND") &&  stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            Keyword keywords = new Keyword("COND", "cond",writeToTxt);
                            tokens.add(keywords);

                        }
                        else if (isKeyword(arr[j]) &&keyword(arr[j]).equals("BEGIN") &&  stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            Keyword keywords = new Keyword("BEGIN", "begin",writeToTxt);
                            tokens.add(keywords);

                        }else if (isKeyword(arr[j]) && keyword(arr[j]).equals("IF") && stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            Keyword keywords = new Keyword("IF","if",writeToTxt);
                            tokens.add(keywords);

                        }else if (isNumber(arr[j].toLowerCase()) && stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            Number numbers = new Number("NUMBER", arr[j],writeToTxt);
                            tokens.add(numbers);


                        } else if (isBoo(arr[j])) {
                            BooleanType booleans = new BooleanType("BOOLEAN", arr[j],writeToTxt);
                            tokens.add(booleans);


                        }else if (isStr(arr,j) && stringArray[j].charAt(i) == '"') {
                            StringType strings = new StringType("STRING", arr[j],writeToTxt);
                            tokens.add(strings);


                        }else if (isIdentifier(arr[j]) && stringArray[j].charAt(i) == arr[j].charAt(0)) {

                            //if(!(str(arr,j).contains(arr[j]))){

                            Identifier identifiers = new Identifier("IDENTIFIER", arr[j],writeToTxt);
                            tokens.add(identifiers);


                            //}

                        }else if (isChar(arr[j]) && stringArray[j].charAt(i) == arr[j].charAt(0)) {
                            CharType chars = new CharType("CHAR", arr[j],writeToTxt);
                            tokens.add(chars);

                        }else{
                                /*column=str.indexOf(arr[j]) + 1;
                                System.out.println("LEXICAL ERROR [" + row +":"+ (column) + "]: Invalid token " + "'" + arr[j] + "'" );
                                System.exit(0);*/
                        }

                    }
                }
                row++;

            }

            input.close();
            PPLLParser parser = new PPLLParser(tokens,writeToTxt);
            parser.parse();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}