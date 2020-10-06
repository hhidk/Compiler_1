import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {

    static char c = ' ';
    static String token;
    static int num;
    static OutPut symbol;
    static boolean flag = true;
    static boolean EOF = false;

    static String filePath;
    static BufferedReader br;

    static void getChar() throws Exception{
        int a;
        br.mark(1);
        a = br.read();
        if(a != -1)
            c = (char) a;
        else{
            flag = false;
            EOF = true;
            c = '!';
        }
    }

    static void clearToken(){
        token = "";
    }

    static boolean isSpace(){
        return c==' ' || c=='\t' || c=='\r' || c=='\n';
    }

    static boolean isLetter(){
        return (c>='a' && c<='z')||(c>='A' && c<='Z');
    }

    static boolean isDigit(){
        return (c>='0' && c<='9');
    }

    static boolean isComma(){
        return c==',';
    }

    static boolean isColon(){
        return c==':';
    }

    static boolean isEqu(){
        return c=='=';
    }

    static boolean isPlus(){
        return c=='+';
    }

    static boolean isStar(){
        return c=='*';
    }

    static boolean isLPar(){
        return c=='(';
    }

    static boolean isRPar(){
        return c==')';
    }

    static void catToken(){
        StringBuffer s = new StringBuffer(token).append(c);
        token = s.toString();
    }

    static void retract() throws Exception{
        br.reset();
    }

    static int reserver(){
        if(token.equals("BEGIN"))
            return OutPut.BEGINSY.type;
        if(token.equals("END"))
            return OutPut.ENDSY.type;
        if(token.equals("FOR"))
            return OutPut.FORSY.type;
        if(token.equals("IF"))
            return OutPut.IFSY.type;
        if(token.equals("THEN"))
            return OutPut.THENSY.type;
        if(token.equals("ELSE"))
            return OutPut.ELSESY.type;
        return 0;
    }

    static int transNum() throws Exception{
        return Integer.parseInt(token);
    }

    static int getsym() throws Exception{
        clearToken();
        do{
            getChar();
        } while(isSpace());

        if(isLetter()){
            while(isLetter() || isDigit()){
                catToken();
                getChar();
            }
            retract();
            int resultValue = reserver();
            if(resultValue == 0)
                symbol = OutPut.IDSY;
            else
                symbol = OutPut.getOutPut(resultValue);
        }
        else if(isDigit()){
            while(isDigit()){
                catToken();
                getChar();
            }
            retract();
            num = transNum();
            symbol = OutPut.INTSY;
        }
        else if(isColon()){
            getChar();
            if(isEqu())
                symbol = OutPut.ASSIGNSY;
            else {
                symbol = OutPut.COLONSY;
                retract();
            }
        }
        else if(isPlus())
            symbol = OutPut.PLUSSY;
        else if(isStar())
            symbol = OutPut.STARSY;
        else if(isComma())
            symbol = OutPut.COMMASY;
        else if(isLPar())
            symbol = OutPut.LPARSY;
        else if(isRPar())
            symbol = OutPut.RPARSY;
        else{
            symbol = OutPut.UNKNOWN;
            flag = false;
        }
        return 0;
    }

    public static void main(String[] args) throws Exception{
        filePath = args[0];
        br=new BufferedReader(new InputStreamReader
                (new FileInputStream(filePath),"UTF-8"));
        while(flag){
            getsym();
            if(EOF) break;
            else if(symbol.equals(OutPut.IDSY))
                System.out.println("Ident(" + token + ")");
            else if(symbol.equals(OutPut.INTSY))
                System.out.println("Int(" + num + ")");
            else
                System.out.println(symbol.output);
        }
    }
}
