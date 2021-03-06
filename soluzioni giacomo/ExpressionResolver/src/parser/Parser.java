package parser;
import operation.*;
public class Parser {
	
	private static Addendo getAddendo(String toParse) {
		Addendo ad;
		toParse=cutParentheses(toParse);
		try {
			ad= new Numero(Double.parseDouble(toParse));
		}
		catch(NumberFormatException e) {
			ad=buildTree(toParse);
		}	
		return ad;
		
	}
	
	private static OperationType getOperationType(char symbol) {
		for(OperationType operation : OperationType.values()) {
			if (operation.getSymbol()==symbol) return operation;
		}
		return null;
	}
	
	private static String cutParentheses(String string) {
		int cnt=0;
		if(string.startsWith("(") && string.endsWith(")")) {
			for(int i=0;i<string.length();i++) {
				if(string.charAt(i)==')') cnt--;
				else if (string.charAt(i)=='(') cnt++;
				if(cnt==0 && i==(string.length()-1)) {
					string=string.substring(1, string.length()-1);
					break;
				}
				else if(cnt==0 && i!=(string.length()-1)) break;
			}
		}
		return string;
	}
	
	public static Operation buildTree(String expression) {
		Operation operation=null;
		int cnt=0;//conta parentesi
		String ad1_InString="",ad2_InString="";
		for(int i=expression.length()-1;i>=0 ;i--) {
			if(expression.charAt(i)==')') cnt++;
			else if (expression.charAt(i)=='(') cnt--;
			if((expression.charAt(i)=='*' || expression.charAt(i)=='/') && cnt==0 ) {
				ad1_InString=expression.substring(0, i);
				ad2_InString=expression.substring(i+1);
				operation= new Operation(getAddendo(ad1_InString),getAddendo(ad2_InString),getOperationType(expression.charAt(i)));
				break;
			}
		}
		cnt=0;
		for(int i=expression.length()-1;i>=0 ;i--) {
			if(expression.charAt(i)==')') cnt++;
			else if (expression.charAt(i)=='(') cnt--;
			if((expression.charAt(i)=='+' || expression.charAt(i)=='-') && cnt==0 ) {
				ad1_InString=expression.substring(0, i);
				ad2_InString=expression.substring(i+1);
				operation= new Operation(getAddendo(ad1_InString),getAddendo(ad2_InString),getOperationType(expression.charAt(i)));
				break;
			}
		}
		return operation;
	}
	
}
