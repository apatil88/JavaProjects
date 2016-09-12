package com.abstractclassexample;

public class MyLinkedList implements NodeList {

	private ListItem root = null;
	
	public MyLinkedList(ListItem root) {
		this.root = root;
	}

	@Override
	public ListItem getRoot() {
		return this.root;
	}

	@Override
	public boolean addItem(ListItem newItem) {
		if(this.root == null){
			//List is empty, the new item becomes the head of the list.
			this.root = newItem;
			return true;
		}
		
		ListItem currentItem = this.root;
		while(currentItem != null){
			int comparison = currentItem.compareTo(newItem);
			if(comparison < 0){
				//The newitem is greater than the current item, move to the right if possible
				if(currentItem.next() != null){
					currentItem = currentItem.next();
				} else {
					//If there is no next item, insert at the end
					currentItem.setNext(newItem);
					newItem.setPrevious(currentItem);
					return true;
				}
			} else if (comparison > 0){
				//new item is less, insert before
				if(currentItem.previous() != null){
					currentItem.previous().setNext(newItem);
					newItem.setPrevious(currentItem.previous());
					newItem.setNext(currentItem);
					currentItem.setPrevious(newItem);
				} else {
					//the node is the first item in the list
					newItem.setNext(this.root);
					this.root.setPrevious(newItem);
					this.root=newItem;
				}
				return true;
			} else {
				//Equal
				System.out.println((String) newItem.getValue() + " already present, not added." );
				return false;
			}
				
		}
		
		return false;
	}

	@Override
	public boolean removeItem(ListItem deletedItem) {
		
		if(deletedItem != null){
			System.out.println("Deleting the item " + deletedItem.getValue() );
		}
		
		ListItem currentItem = this.root;
		
		while(currentItem != null){
			int comparison = currentItem.compareTo(deletedItem);
			if(comparison == 0){
				//If the match is a root item
				if(currentItem == this.root){
					this.root = currentItem.next();
				} else {
					currentItem.previous().setNext(currentItem.next());
					if(currentItem.next() != null){
						currentItem.next().setPrevious(currentItem.previous());
					}
				}
				return true;
			} else if (comparison < 0){
				currentItem = currentItem.next();
			} else {
				//If comparison > 0, we are at an item greater than the one to be deleted
				//so the item is not in the list
				return false;
			}
		}
		
		//We have reached the end of the list without finding the item to be deleted
		return false;
	}

	@Override
	public void traverse(ListItem root) {
		if(root == null){
			System.out.println("The list is empty.");
		} else {
			while(root != null){
				System.out.println(root.getValue());
				root = root.next();
			}
		}
	}

}
