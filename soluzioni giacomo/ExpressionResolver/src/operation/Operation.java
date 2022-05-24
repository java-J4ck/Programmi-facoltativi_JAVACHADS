package operation;

public class Operation implements Addendo{
	private Addendo ad1;
	private Addendo ad2;
	private OperationType operationType;
	
	
	public Operation(Addendo ad1, Addendo ad2, OperationType operationType) {
		this.ad1 = ad1;
		this.ad2 = ad2;
		this.operationType = operationType;
	}


	public double getResult() {
		double result=0;
		switch(operationType) {
			case SUM :
				result= ad1.getResult()+ad2.getResult();
				break;
			case SUB :
				result= ad1.getResult()-ad2.getResult();
				break;
			case DIV :
				result= ad1.getResult()/ad2.getResult();
				break;
			case MULTY :
				result= ad1.getResult()*ad2.getResult();
				break;
		
		}
		return result;
	}
	

}
