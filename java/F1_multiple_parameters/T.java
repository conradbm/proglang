package main;

public class T {
	String tokenValue;
	String tokenType;
        String tokenParam;
        Param ps;
	public T(String tok, String tokType){
		tokenValue = tok;
		tokenType = tokType;
	}
        public T(String tok, String tokType, String tokParam){
            tokenValue = tok;
            tokenType = tokType;
            tokenParam = tokParam;
        }
}
