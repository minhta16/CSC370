/**
 * @author Minh Ta
 * @date 09/01/2018
 * @version CSC370 Program 2, Fall 2018
 * 
 * Description: A class storing a song and its title and artist.
 * 
 * 
 * Cite Assistance (who and what): myself
 */

import melody.audio.*;

public class Melody {
	private QueueADT<Note> song;
	private String title;
	private String artist;
	private double length;
	private int noteSize;
	private boolean appended;
	
	/**
	 * Construct a Song object
	 * @param title - song's title
	 * @param artist - song's artist(s)
	 * @param song - an ArrayQueue of Notes which represents a song
	 */
	public Melody(String title, String artist, QueueADT<Note> song) {
		this.title = title;
		this.artist = artist;
		this.song = song;
		length = 0;
		noteSize = song.size();
		appended = false;
		
		boolean isRepeating = false;
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			if (curNote.isRepeat()) {
				isRepeating = !isRepeating;
			}
			if (isRepeating) {
				length += curNote.getDuration();
			}
			length += curNote.getDuration();
			song.add(curNote);
		}
	}
	
	/**
	 * @return title of song(s)
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @return artist(s) name
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * 
	 * @return duration of the song
	 */
	public double getTotalDuration() {
		return length;
	}
	
	/**
	 * 
	 * @return a text representation of the song(s)
	 */
	public String toString() {
		String string = title + "\n" + artist + "\n";
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			string += curNote.toString() + "\n";
			song.add(curNote);
		}
		return string;
	}
	
	/**
	 * Changing the duration of all notes in the song by a multiple of tempo. Length is also changed to a multiple of tempo.
	 * @param tempo a ratio for the song(s) to change speed to
	 */
	public void changeTempo(double tempo) {
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			curNote.setDuration(curNote.getDuration() * tempo);
			song.add(curNote);
		}
		length *= tempo;
	}
	
	/**
	 * Reverse the order of all notes in the song.
	 */
	public void reverse() {
		StackADT<Note> stack = new ArrayStack<Note>();
		while(!song.isEmpty()) {
			Note curNote = song.remove();
			stack.push(curNote);
		}
		while(!stack.isEmpty()) {
			Note curNote = stack.pop();
			song.add(curNote);
		}
	}
	
	/**
	 * Add all notes of another song to the end of the current song.
	 * @param other - another song
	 */
	public void append(Melody other) {
		for (int i = 0; i < other.noteSize; i++) {
			Note curNote = other.song.remove();
			song.add(curNote);
			other.song.add(curNote);
		}
		length += other.length;
		noteSize += other.noteSize;
		if (appended) {
			title += ", " + other.title;
			artist += ", " + other.artist;
		} else {
			appended = true;
			title += " and " + other.title;
			artist += " ft. " + other.artist;
		}
	}
	
	/**
	 * Play the song(s).
	 */
	public void play() {
		boolean isRepeating = false;
		QueueADT<Note> tempQueue = new ArrayQueue<Note>();
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			curNote.play();
			song.add(curNote);
			
			//repeat the section if needed
			
			//add to tempQueue the starting note of the section
			if (curNote.isRepeat()) {
				tempQueue.add(curNote);
				
				// this checks the end of the repeating sequence
				if (isRepeating) {
					// plays tempQueue at the end of the repeating sequence
					while (!tempQueue.isEmpty()) {
						tempQueue.remove().play();
					}
				}
				isRepeating = !isRepeating;
			}
			
			//the notes in between the two repeating notes are added to tempQueue
			if (isRepeating && !curNote.isRepeat()) {
				tempQueue.add(curNote);
			}
			
		}
	}
}
