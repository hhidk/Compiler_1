public enum  OutPut {
    BEGINSY(1,"Begin"),
    ENDSY(2,"End"),
    FORSY(3,"For"),
    IFSY(4,"If"),
    THENSY(5,"Then"),
    ELSESY(6,"Else"),
    IDSY(7,"Ident()"),
    INTSY(8,"Int()"),
    COLONSY(9,"Colon"),
    PLUSSY(10,"Plus"),
    STARSY(11,"Star"),
    COMMASY(12,"Comma"),
    LPARSY(13,"LParenthesis"),
    RPARSY(14,"RParenthesis"),
    ASSIGNSY(15,"Assign"),
    UNKNOWN(16,"Unknown")
    ;

    int type;
    String output;
    OutPut(int type, String output){
        this.type = type;
        this.output = output;
    }

    public static OutPut getOutPut(int type){
        for(OutPut outPut : OutPut.values()){
            if(outPut.type == type)
                return outPut;
        }
        return null;
    }
}
