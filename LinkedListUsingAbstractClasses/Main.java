package com.abstractclassexample;

public class Main {

	public static void main(String[] args) {
		MyLinkedList myLinkedList = new MyLinkedList(null);
		myLinkedList.traverse(myLinkedList.getRoot());

		//String stringData = "5 7 3 9 8 2 1 0 4 6 ";
		String stringData = "Darwin Brisbane Adelaide Melbourne Canberra Sydney Perth Canberra";
		
		String[] data = stringData.split(" ");
		for(String s : data){
			myLinkedList.addItem(new Node(s));
		}
		
		myLinkedList.traverse(myLinkedList.getRoot());
		myLinkedList.removeItem(new Node("3"));
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(new Node("5"));
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(new Node("0"));
		myLinkedList.removeItem(new Node("4"));
		myLinkedList.removeItem(new Node("2"));
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(new Node("9"));
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(new Node("8"));
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(new Node("6"));
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(myLinkedList.getRoot());
		myLinkedList.traverse(myLinkedList.getRoot());
		
		myLinkedList.removeItem(myLinkedList.getRoot());
		myLinkedList.traverse(myLinkedList.getRoot());
		
		/*SearchTree tree = new SearchTree(null);
		tree.traverse(tree.getRoot());

		String stringData = "5 7 3 9 8 2 1 0 4 6 ";
		//String stringData = "Darwin Brisbane Adelaide Melbourne Canberra Sydney Perth Canberra";
		
		String[] data = stringData.split(" ");
		for(String s : data){
			tree.addItem(new Node(s));
		}
		
		tree.traverse(tree.getRoot());
		tree.removeItem(new Node("3"));
		tree.traverse(tree.getRoot());
		
		tree.removeItem(new Node("5"));
		tree.traverse(tree.getRoot());
		
		tree.removeItem(new Node("0"));
		tree.removeItem(new Node("4"));
		tree.removeItem(new Node("2"));
		tree.traverse(tree.getRoot());
		
		tree.removeItem(new Node("9"));
		tree.traverse(tree.getRoot());
		
		tree.removeItem(new Node("8"));
		tree.traverse(tree.getRoot());
		
		tree.removeItem(new Node("6"));
		tree.traverse(tree.getRoot());
		
		tree.removeItem(tree.getRoot());
		tree.traverse(tree.getRoot());
		
		tree.removeItem(tree.getRoot());
		tree.traverse(tree.getRoot());*/
		
		
	}

}
