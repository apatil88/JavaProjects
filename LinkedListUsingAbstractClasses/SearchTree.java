package com.abstractclassexample;

/*
 * Implementation of Binary Search Tree.
 * Operations on Binary Search Tree: Adding a node, removing a node
 */

public class SearchTree implements NodeList{

	private ListItem root = null;
	
	public SearchTree(ListItem root) {
		this.root = root;
	}

	@Override
	public ListItem getRoot() {
		return this.root;
	}

	@Override
	public boolean addItem(ListItem newItem) {
		
		//the tree was empty, so our newItem becomes the head of the tree
		if(this.root == null){
			this.root = newItem;
			return true;
		}
		
		//otherwise start comparing from the head of the tree
		ListItem currentItem = this.root;
		while(currentItem != null){
			int comparison = currentItem.compareTo(newItem);
			
			if(comparison < 0){
				//newItem is greater, move to the right if possible
				if(currentItem.next() != null){
					currentItem = currentItem.next();
				} else {
					//there is no node to the right, add at this point
					currentItem.setNext(newItem);
					return true;
				}
			} else if (comparison > 0){
				//newItem is less, move to the left if possible
				if(currentItem.previous() != null){
					currentItem = currentItem.previous();
				} else {
					//there is no node to the left, add at this point
					currentItem.setPrevious(newItem);
					return true;
				}
			} else {
				//equal
				System.out.println(newItem.getValue() + " already present, not added.");
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeItem(ListItem deletedItem) {
		if(deletedItem != null){
			System.out.println("Deleting item " + deletedItem.getValue());
		}
		
		ListItem currentItem = this.root;
		ListItem parentItem = currentItem;
		
		while(currentItem != null){
			int comparison = currentItem.compareTo(deletedItem);
			if(comparison < 0){
				//deletedItem is greater, move to right
				parentItem = currentItem;
				currentItem = currentItem.next();
			} else if (comparison > 0){
				//deletedItem is smaller, move to left
				parentItem = currentItem;
				currentItem = currentItem.previous();
			} else {
				//Equal, we found the item to be deleted
				performRemoval(parentItem, currentItem);
				return true;
			}
		}
		return false;
	}

	private void performRemoval(ListItem parent, ListItem item) {
		//remove item from the tree
		if(item.next() == null){
			//no right tree, make parent point to left tree (may be null)
			if(parent.next() == item){
				//item is right child of its parent
				parent.setNext(item.previous());
			} else if (parent.previous() == item){
				//item is left child of its parent
				parent.setPrevious(item.previous());
			} else {
				//parent must be currentItem, which means we are looking at root of the tree
				this.root = item.previous();
			}
		} else if (item.previous() == null){
			//no left tree, make parent point to right tree (may be null)
			if(parent.next() == item){
				//item is right child of its parent
				parent.setNext(item.next());
			} else if (parent.previous() == item){
				//item is left child of its parent
				parent.setPrevious(item.next());
			} else {
				//Again, we are deleting the root
				this.root = item.next();
			}
		} else {
			//neither left or right is null
			//From the right sub-tree, find the smallest value (i.e. the leftmost)
			ListItem current = item.next();
			ListItem leftmostParent = item;
			
			while(current.previous() != null){
				leftmostParent = current;
				current = current.previous();
			}
			
			//Now put the smallest value into our node to be deleted
			item.setValue(current.getValue());
			
			//and delete the smallest
			if(leftmostParent == item){
				//there was no leftmost node, so 'current' points to the smallest node (the one that must now be deleted)
				item.setNext(current.next());
			} else {
				//set the smallest node's parent to point to the smallest node's right child (which may be null)
				leftmostParent.setPrevious(current.next());
			}
		}
		
	}

	@Override
	public void traverse(ListItem root) {
		if(root != null){
			traverse(root.previous());
			System.out.println(root.getValue());
			traverse(root.next());
		}
		
	}

}
