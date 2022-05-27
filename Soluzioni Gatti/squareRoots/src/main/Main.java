package main;

import java.util.ArrayDeque;
import java.util.Random;

import binaryTree.Node;


public class Main {
	
	private static final int MAX_OP_NUM = 50;
	private static final int MIN_OP_NUM = 1;
	private static final int MAX_VALUE = 50;
	private static final int MIN_VALUE = -50;
	private static final int OP_LIST_LENGTH = 4;
	private static final String menuTitle = "Seleziona opzione: ";
	private static final String[] menuOptions = {"Calcola espressione","Visualizza espressione"};
	private static Character[] opList = new Character[] {'+', '-', '*', '/'};
	private static Random rand = new Random();
	

	public static void main(String[] args) {
		
		MyMenu menu = new MyMenu(menuTitle, menuOptions);
		
		
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
		
		int operationNumeber = InputDati.leggiInteroRange("Inserisci il numero di operazioni che l'espressione deve avere: ", MIN_OP_NUM, MAX_OP_NUM);
		
		Node root = generateTree(operationNumeber);
		
		int s;
		while((s = menu.scegli()) != 0) {
			switch(s) {
				case 1: 
					System.out.printf("\n%.2f\n", calcTree(root));
					break;
				case 2: 
					System.out.println("\n" + toString(root) + "\n");
					break;
			}
		}
			

		
	}


	
	
	/**
	 * Calcola il valore dell'espressione contenuta nell'albero passato come argomento 
	 * @param root La radice dell'albero
	 * @return Il risultato dell'espressione
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
	
	
	
	
	/**
	 * Genera casualmente un albero contenente una espressione
	 *
	 * @param operationNumber Numero di operazioni dell'espressione 
	 * @return La radice dell'albero
	 */
	public static Node generateTree(int operationNumber) {
		
		Node root;
		
		if(operationNumber > 0) {   //  
			root = new Node(null, null, opList[rand.nextInt(OP_LIST_LENGTH)]);  // Crea un nuovo nodo con un operatore casuale
			operationNumber -= 1;  // Decrementa il numero di operazioni rimanenti
			int opNum1 = rand.nextInt(operationNumber == 0 ? 1 : operationNumber);  // Genera un numero casuale tra 0 e operationNumber
																					// (L'operatore ternario serve perche' il metodo nextInt di rand considera lo zero come argomento non valido)
			int opNum2 = operationNumber - opNum1;
			root.setLeftChild(generateTree(opNum1));  // Genera il sottoalbero sinistro con le operazioni rimanenti
			root.setRightChild(generateTree(opNum2)); // Genera il sottoalbero destro con le operazioni rimanenti
		}
		else { // Se non ci sono operazioni disponibili, crea un nuovo nodo contenete un numero casuale compreso fra due valori predefiniti
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
	
	
	
	
	/**
	 * Genera una stringa rappresentate l'espressione contenuta nell'albero
	 * @param root Radice dell'albero
	 * @return Striga rappresentante l'espressione contenuta nell'albero
	 */
	public static StringBuffer toString(Node root) {
		StringBuffer sb = new StringBuffer();
		ArrayDeque<String> squareBracketsStack = new ArrayDeque<>();  // Stack per tenere traccia delle parentesi tonde
		ArrayDeque<String> roundBracketsStack = new ArrayDeque<>();   // Stack per tenere traccia delle parentesi quadre
		
		if(root.getLeftChild().getValue() == null) {  // Se il figlio sinistro non contiene un valore numerico, chiama toString il sottoalbero del figlio sinistro
			sb.append("[ "); // Apri una parentesi quadra 
			squareBracketsStack.push(" ]");  // Pusha una parentesi nello stack per tenere traccia della parentesi appena aprta
			sb.append(toString(root.getLeftChild()));
			//System.out.println(sb);
			//sb.append(" ]");
			if(roundBracketsStack.peekFirst() != null) sb.append(roundBracketsStack.pollFirst());   // Chiudi eventuali parentesi tonde
			sb.append(" " + root.getOperator() + " ");
			//System.out.println(sb);
		}
		else {    // Se il figlio sinistro contiene un valore, stampa fra parentesi tonde l'operazione
			sb.append("( " + root.getLeftChild().getValue() + " " + root.getOperator() + " ");
			roundBracketsStack.push(" )");  
			if(squareBracketsStack.peekFirst() != null) sb.append(squareBracketsStack.pollFirst());  // Chiudi eventuali parentesi quadre
			//System.out.println(sb);
		}
		
		if(root.getRightChild().getValue() == null) {  // Se il figlio destro non contiene un valore numerico, chiama toString per il sottoalbero del figlio destro
			//sb.append("[ ");
			sb.append(toString(root.getRightChild()));
			//System.out.println(sb);
			if(roundBracketsStack.peekFirst() != null) sb.append(roundBracketsStack.pollFirst());
			if(squareBracketsStack.peekFirst() != null) sb.append(squareBracketsStack.pollFirst());
			//System.out.println(sb);
		}
		else {
			sb.append(root.getRightChild().getValue() + " )");
			//if(roundBracketsStack.peekFirst() != null) sb.append(roundBracketsStack.pollFirst());
			if(squareBracketsStack.peekFirst() != null) sb.append(squareBracketsStack.pollFirst());
			//System.out.println(sb);
		}
		
		return sb;
		
	}

}
