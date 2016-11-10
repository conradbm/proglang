package main;

public class Tok {
	String tokenValue;
	String tokenType;
        String tokenParam;
        Param ps;
	public Tok(String tok, String tokType){
		tokenValue = tok;
		tokenType = tokType;
	}
        public Tok(String tok, String tokType, String tokPar){
		tokenValue = tok;
		tokenType = tokType;
                tokenParam = tokPar;
	}
}
