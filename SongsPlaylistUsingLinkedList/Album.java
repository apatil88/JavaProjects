package com.songsplaylist;

import java.util.ArrayList;
import java.util.LinkedList;

public class Album {

	private String name;
	private String artist;
	private ArrayList<Song> songs;
	
	public Album(String name, String artist) {
		this.name = name;
		this.artist = artist;
		this.songs = new ArrayList<Song>();
	}
	
	public boolean addSong(String title, double duration){
		if(findSong(title) == null){
			this.songs.add(new Song(title, duration));
			return true;
		}
		return false;
	}
	
	//Method to check if there is duplicate song
	private Song findSong(String title){
		for(Song checkedSong : this.songs){
			if(checkedSong.getTitle().equals(title)){
				return checkedSong;
			}
		}
		return null;
	}
	
	//Method to add song to playlist using track number
	public boolean addToPlaylist(int trackNumber, LinkedList<Song> playList){
		int index = trackNumber - 1;
		
		if((index > 0) && (index <= this.songs.size() - 1)){
			playList.add(this.songs.get(index));      //Add the song to the playList
			return true;
		}
		
		System.out.println("This is album does not have the track " + trackNumber );
		return false;
	}
	
	//Method to add song to playlist using title
	public boolean addToPlaylist(String title, LinkedList<Song> playList){
		Song song = findSong(title);
		if(song != null){
			playList.add(song);
			return true;
		}
		
		System.out.println("The song " + title + " does not have a track.");
		return false;
	}
	
}
