package squareRoots;

import java.util.ArrayDeque;
import java.util.Random;

import binaryTree.Node;


public class Main {
	
	private static final int MIN_VALUE = 50;
	private static final int MAX_VALUE = -50;
	private static final int OP_LIST_LENGTH = 4;
	private static Character[] opList = new Character[] {'+', '-', '*', '/'};
	private static Random rand = new Random();
	

	public static void main(String[] args) {
		
		/*
		Node n1 = new Node(null, null, 4);
		Node n2 = new Node(null, null, 5);
		Node n3 = new Node(null, null, 6);
		Node n4 = new Node(null, null, 7);
		Node n5 = new Node(null, null, 8);
		
		Node n6 = new Node(n1, n2, '/');
		Node n7 = new Node(n3, n6, '-');
		Node n8 = new Node(n4, n5, '*');
		
		Node root = new Node(n7, n8, '+');
		*/
		
		Node root = generateTree(10);
		
		float r = calcTree(root);
		
		System.out.println(r);
		
		System.out.println(toString(root));
			
		
		
	}

	
	/*
	 * Calcola l'espressione rappresentata dall'albero binario
	 */
	public static float calcTree(Node root) {
		
		float result = 0;
		
		if(root == null) return result;  // Se la radice passata come argomento e' nulla (probabilemente a casa di un errore) ritorna 0
		
		if(root.getValue() != null) return root.getValue();    // Se root contiene un valore numerico, ritorna quel valore
		
		switch (root.getOperator()) {  // Identifica l'operatore contenuto nel nodo e ritorna il risultato dell'operazione eseguita sui due nodi figli.
									   // E' probabile che un nodo figlio abbia un sottoalbero tutto suo, quindi chiama ricorsivamente questa funzione per calcolare tutti i valori	
			case '+': return result = calcTree(root.getLeftChild()) + calcTree(root.getRightChild());
			case '-': return result = calcTree(root.getLeftChild()) - calcTree(root.getRightChild());
			case '*': return result = calcTree(root.getLeftChild()) * calcTree(root.getRightChild());
			case '/':
				try {
					return result = calcTree(root.getLeftChild()) / (calcTree(root.getRightChild()));					
				}
				catch (Exception e) {
					System.out.println("Math error: " + e.getMessage());
					System.out.println(toString(root));
					System.exit(0);				
				}
			default:
				throw new IllegalArgumentException("Invalid operator: " + root.getOperator());
		}	
			
	}
	
	
	
	
	public static Node generateTree(int operationNumber) {
		
		Node root;
		
		if(operationNumber > 0) {
			root = new Node(null, null, opList[rand.nextInt(OP_LIST_LENGTH)]);
			operationNumber -= 1;
			int opNum1 = rand.nextInt(operationNumber == 0 ? 1 : operationNumber);
			int opNum2 = operationNumber - opNum1;
			root.setLeftChild(generateTree(opNum1));
			root.setRightChild(generateTree(opNum2));
		}
		else {
			/*
			int value = 0;
			while(value == 0) {   // Escludi il numero 0 in modo da evitare casi di / per 0
				value = rand.nextInt() % ((MAX_VALUE + 1 - MIN_VALUE) + MIN_VALUE);
			}
			*/
			root = new Node(null, null, rand.nextInt() % ((MAX_VALUE + 1 - MIN_VALUE) + MIN_VALUE));
		}
		 
		return root;
		
		
	}
	
	
	
	
	
	public static StringBuffer toString(Node root) {
		StringBuffer sb = new StringBuffer();
		ArrayDeque<String> squareBracketsStack = new ArrayDeque<>();
		//ArrayDeque<String> roundBracketsStack = new ArrayDeque<>();
		
		if(root.getLeftChild().getValue() == null) {
			sb.append("[ ");
			squareBracketsStack.push(" ]");
			sb.append(toString(root.getLeftChild()));
			//System.out.println(sb);
			//sb.append(" ]");
			sb.append(" " + root.getOperator() + " ");
			//System.out.println(sb);
		}
		else {
			//roundBracketsStack.push(" )");
			sb.append("( " + root.getLeftChild().getValue() + " " + root.getOperator());
			if(squareBracketsStack.peekFirst() != null) sb.append(squareBracketsStack.pollFirst());
			//System.out.println(sb);
		}
		
		if(root.getRightChild().getValue() == null) {
			//sb.append("[ ");
			sb.append(toString(root.getRightChild()));
			//System.out.println(sb);
			if(squareBracketsStack.peekFirst() != null) sb.append(squareBracketsStack.pollFirst());
			//System.out.println(sb);
		}
		else {
			sb.append(" " + root.getRightChild().getValue() + " )");
			//if(roundBracketsStack.peekFirst() != null) sb.append(roundBracketsStack.pollFirst());
			if(squareBracketsStack.peekFirst() != null) sb.append(squareBracketsStack.pollFirst());
			//System.out.println(sb);
		}
		
		return sb;
		
	}
	
	
	
	
}
