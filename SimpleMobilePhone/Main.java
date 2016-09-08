package com.simplemobilephone;

import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static MobilePhone mobilePhone = new MobilePhone("000 000 0000");
	
	public static void main(String[] args) {
		boolean quit = false;
		int choice = 0;
		
		while(!quit){
			System.out.println("\nEnter your choice : 0 to view instructions");
			choice = scanner.nextInt();
			scanner.nextLine();
			
			switch(choice){
				case 0:
					printInstructions();
					break;
				
				case 1:
					mobilePhone.listContacts();;
					break;
				
				case 2:
					addContact();
					break;
				
				case 3:
					modifyContact();
					break;
				
				case 4:
					removeContact();
					break;
					
				case 5:
					searchContact();
					break;
				
				case 6:
					quit = true;
					break;
				
			}
		}
	}
	
	private static void printInstructions() {
		System.out.println("\nAvailable actions : \nPress");
		System.out.println("\t 0 - To print choice options");
		System.out.println("\t 1 - Print contacts list");
		System.out.println("\t 2 - Add a contact to the list");
		System.out.println("\t 3 - Update a contact in the list");
		System.out.println("\t 4 - Remove a contact from the list");
		System.out.println("\t 5 - Query for an existing contact");
		System.out.println("\t 6 - Quit the application");
			
	}
	
	private static void addContact() {
		System.out.println("Enter new contact name : ");
		String name = scanner.nextLine();
		System.out.println("Enter phone number : ");
		String phone = scanner.nextLine();
		Contact contact = Contact.createContact(name, phone);
		if(mobilePhone.addContacts(contact)){
			System.out.println("New contact added : name = " + name + " phone = " + phone);
		} else {
			System.out.println(name + " , cannot be added. Already on file.");
		}
	}

	private static void modifyContact() {
		System.out.println("Enter the existing contact : ");
		String name = scanner.nextLine();
		
		Contact existingContactRecord = mobilePhone.queryContact(name);
		if(existingContactRecord == null){
			System.out.println("Contact record not found");
			return;
		}
		
		System.out.println("Enter new contact name : ");
		String newContactName = scanner.nextLine();
		System.out.println("Enter new contact number : ");
		String newContactNumber = scanner.nextLine();
		Contact newContact = Contact.createContact(newContactName, newContactNumber);
		
		if(mobilePhone.updateContacts(existingContactRecord, newContact)){
			System.out.println("Successfully updated record.");
		} else {
			System.out.println("Error updating contact.");
		}
		
	}

	private static void removeContact() {
		System.out.println("Enter the existing contact to be removed : ");
		String name = scanner.nextLine();
		
		Contact removeRecord = mobilePhone.queryContact(name);
		if(removeRecord == null){
			System.out.println("Contact record not found");
			return;
		}
		
		if(mobilePhone.removeContacts(removeRecord)){
			System.out.println("Successfully deleted contact.");
		} else {
			System.out.println("Error deleting contact.");
		}
	}

	private static void searchContact() {
		System.out.println("Enter the existing contact to be queried : ");
		String name = scanner.nextLine();
		
		Contact queryRecord = mobilePhone.queryContact(name);
		if(queryRecord == null){
			System.out.println("Contact record not found");
			return;
		}
		
		System.out.println("Contact name : " + queryRecord.getName() + " phone number : "+ queryRecord.getPhoneNumber());
	}	

}
