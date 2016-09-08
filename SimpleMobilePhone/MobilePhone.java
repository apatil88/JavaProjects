package com.simplemobilephone;

import java.util.ArrayList;

public class MobilePhone {
	private String myNumber;
	private ArrayList<Contact> list;
	
	public MobilePhone(String myNumber) {
		this.myNumber = myNumber;
		this.list = new ArrayList<Contact>();
	}

	public void listContacts() {
		System.out.println("Contact List : \n");
		for(int i = 0 ; i < this.list.size(); i++){
			System.out.println((i+1) + ". " + this.list.get(i).getName() + " --->" + this.list.get(i).getPhoneNumber());
		}		
	}
	
	public boolean addContacts(Contact contact){
		if(findContact(contact) >= 0){
			System.out.println("Contact already in the list.");
			return false;
		}
		list.add(contact);
		return true;
	}
	
	public boolean updateContacts(Contact oldContact, Contact newContact){
		int position = findContact(oldContact);
		if(position < 0){
			System.out.println(oldContact.getName() + ", was not found.");
			return false;
		} 
		this.list.set(position, newContact);
		System.out.println(oldContact.getName() + " was replaced by " + newContact.getName());
		return true;
		
	}
	
	public boolean removeContacts(Contact contact){
		int foundPosition = findContact(contact);
		if(foundPosition <= 0){
			System.out.println(contact.getName() + " not found in the list.");
			return false;
		} 
		
		this.list.remove(foundPosition);
		System.out.println(contact.getName() + " was removed from the list.");
		return true;	
	}
	
	private int findContact(Contact contact){
		return this.list.indexOf(contact);
	}

	private int findContact(String contactName){
		for(int i = 0; i < list.size(); i++){
			Contact contact = this.list.get(i);
			if(contact.getName().equals(contactName)){
				return i;
			}
		}
		return -1;
	}
	
	public String queryContact(Contact c){
		if(findContact(c) >= 0){
			return c.getName();
		}
		return null;
	}
	
	public Contact queryContact(String name){
		int position = findContact(name);
		if(position >= 0){
			return this.list.get(position);
		}
		return null;
	}
}
