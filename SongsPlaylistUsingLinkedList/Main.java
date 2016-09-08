package com.songsplaylist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	private static ArrayList<Album> albums = new ArrayList<Album>();
	
	public static void main(String[] args) {
		Album album1 = new Album("Stormbringer", "Deep Purple");
		album1.addSong("Stormbringer", 4.6);
		album1.addSong("Love don't mean a thing", 4.22);
		album1.addSong("Holy man", 4.3);
		album1.addSong("Hold on", 5.6);
		album1.addSong("Lady double dealer", 3.21);
		album1.addSong("You can't do it right", 6.23);
		album1.addSong("High ball shooter", 4.27);
		album1.addSong("The gypsy", 3.2);
		album1.addSong("The soldier of fortune", 3.13);
		albums.add(album1);

		Album album2 = new Album("For those about to rock", "AC/DC");
		album2.addSong("For those about to rock", 5.44);
		album2.addSong("I put the finger on you", 3.25);
		album2.addSong("Lets go", 3.45);
		album2.addSong("Inject the venom", 3.33);
		album2.addSong("Snowballed", 4.51);
		album2.addSong("Evil walks", 3.45);
		album2.addSong("C.O.D", 5.25);
		album2.addSong("Breaking the rules", 5.32);
		album2.addSong("Night of the long knives", 5.12);
		albums.add(album2);
		
		LinkedList<Song> playList = new LinkedList<Song>();
		albums.get(0).addToPlaylist("Holy man", playList);
		albums.get(0).addToPlaylist("Hold on", playList);
		albums.get(0).addToPlaylist( 7, playList);
		albums.get(0).addToPlaylist(9, playList);
		
		albums.get(1).addToPlaylist("Lets go", playList);
		albums.get(1).addToPlaylist("C.O.D", playList);
		albums.get(1).addToPlaylist(3, playList);
		albums.get(1).addToPlaylist(4, playList);
		
		play(playList);
	}
	
	private static void play(LinkedList<Song> playList){
		Scanner scanner = new Scanner(System.in);
		boolean quit = false;
		boolean forward = true;
		
		ListIterator<Song> listIterator = playList.listIterator();
		
		if(playList.size() == 0){
			System.out.println("No songs in the playlist");
		} else {
			printMenu();
		}
		
		while(!quit){
			int action = scanner.nextInt();
			scanner.nextLine();
			
			switch(action){
			case 0:
				System.out.println("Playlist complete");
				quit = true;
				break;
				
			case 1:
				if(!forward){
					if(listIterator.hasNext()){
						listIterator.next();
					} 
					forward = true;
				} 
				if(listIterator.hasNext()) {
					System.out.println("Now playing forward : " + listIterator.next().toString());
				}
				else {
					System.out.println("We have reached the end of the playlist");
					forward = false;
				}
				break;
				
			case 2:
				if(forward){
					if(listIterator.hasPrevious()){
						listIterator.previous();
					}
					forward = false;
				}
				if(listIterator.hasPrevious()){
					System.out.println("Now playing backward : " + listIterator.previous().toString());
				} else {
					System.out.println("We have reached the beginning of the playlist");
					forward = true;
				}
				break;
				
			case 3:
				if(forward){
					if(listIterator.hasPrevious()){
						System.out.println("Now replaying : " + listIterator.previous().toString());
						forward = false;
					} else {
						System.out.println("We have reached the beginning of the playlist");
					}
				} else {
					if(listIterator.hasNext()){
						System.out.println("Now replaying : " + listIterator.next().toString());
						forward = true;
					} else {
						System.out.println("We have reached the end of the playlist");
					}
				}
				break;
				
			case 4:
				printList(playList);
				break;
				
			case 5:
				printMenu();
				break;
				
			case 6:
				if(playList.size() > 0){
					listIterator.remove();
					//If we remove a song, the next song in the list will be played automatically.
					if(listIterator.hasNext()){
						System.out.println("Now playing : " + listIterator.next().toString());
					} else if(listIterator.hasPrevious()){   //If we are at the end of the list, the previous song will be played automatically.
						System.out.println("Now Playing : " + listIterator.previous().toString());
					}
				}
				break;
				
			default:
				System.out.println("\nInvalid option");
				quit = true;
				break;
			}
			
		}
		
	}

	private static void printMenu() {
		System.out.println("Available actions:\nPress : \n" 
				+ " 0 - To quit\n"
				+ " 1 - To play the next song\n"
				+ " 2 - To play the previous song\n"
				+ " 3 - To replay a song\n"
				+ " 4 - To print the list of songs\n"
				+ " 5 - Print the list of options available\n"
				+ " 6 - Delete current song from the playlist\n");	
	}

	private static void printList(LinkedList<Song> playList) {
		Iterator<Song> iterator = playList.iterator();
		System.out.println("===========================");
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println("===========================");
		
	}
}
