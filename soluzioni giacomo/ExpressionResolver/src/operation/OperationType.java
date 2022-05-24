package operation;

public enum OperationType {
	SUM('+'),
	SUB('-'),
	MULTY('*'),
	DIV('/');
	
	private char symbol;

	private OperationType(char symbol) {
		this.symbol = symbol;
	}

	public char getSymbol() {
		return symbol;
	}
	
	
}
