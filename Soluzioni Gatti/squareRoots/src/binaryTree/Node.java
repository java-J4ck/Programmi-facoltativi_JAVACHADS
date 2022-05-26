package binaryTree;

public class Node {

	private Node leftChild;
	private Node rightChild;
	private Node root;
	private Character operator;
	private Integer value;
	
	
	public Node(Node leftChild, Node rightChild, int value) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.value = value;
		this.operator = null;
	}
	
	
	public Node(Node leftChild, Node rightChild, char operator) {
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.operator = operator;
		this.value = null;
	}
	
	
	public Node getLeftChild() {
		return leftChild;
	}
	
	
	public Node getRightChild() {
		return rightChild;
	}
	
	
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
		this.leftChild.setRoot(this);
	}


	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
		this.rightChild.setRoot(this);
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}


	public Integer getValue() {
		return value;
	}
	
	
	public Character getOperator() {
		return operator;
	}
	
	
	
	
	
}
