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
	
	// fields
	private QueueADT<Note> song;	// an array of notes which represents the song
	private String title;			// song's title
	private String artist;			// song's artist(s) name
	private double length;			// song's length in seconds
	private boolean appended;		// true if the current song array consists of 2 songs
	
	/**
	 * Construct a Song object with the following parameters.
	 * @param title - song's title
	 * @param artist - song's artist(s)
	 * @param song - an ArrayQueue of Notes which represents a song
	 * @throws NullPointerException when song is null
	 */
	public Melody(String title, String artist, QueueADT<Note> song) {
		if (song == null) {
			throw new NullPointerException("Song is null.");
		}
		this.title = title;
		this.artist = artist;
		this.song = song;
		length = 0;
		appended = false;
		
		boolean isRepeating = false;
		for (int i = 0; i < song.size(); i++) {
			// adds up the duration of the song
			Note curNote = song.remove();
			length += curNote.getDuration();
			
			// adds the repeated duration
			if (curNote.isRepeat()) {
				isRepeating = !isRepeating;
			}
								// special case for the last note
			if (isRepeating || (curNote.isRepeat() && !isRepeating)) {
				length += curNote.getDuration();
			}
			
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
		for (int i = 0; i < song.size(); i++) {
			Note curNote = song.remove();
			string += curNote.toString() + "\n";
			song.add(curNote);
		}
		return string;
	}
	
	/**
	 * Changing the duration of all notes in the song by a multiple of tempo. Length is also changed to a multiple of tempo.
	 * @param tempo a ratio for the song(s) to change speed to
	 * @throws IllegalArgumentException when tempo is less than or equal to 0.
	 */
	public void changeTempo(double tempo) {
		if (tempo <= 0) {
			throw new IllegalArgumentException("Tempo is not valid: " + tempo);
		}
		for (int i = 0; i < song.size(); i++) {
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
	 * @throws NullPointerException when other is null
	 */
	public void append(Melody other) {
		if (other == null) {
			throw new NullPointerException("The parameter is null.");
		}
		
		for (int i = 0; i < other.song.size(); i++) {
			Note curNote = other.song.remove();
			song.add(curNote);
			other.song.add(curNote);
		}
		length += other.length;
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
		for (int i = 0; i < song.size(); i++) {
			Note curNote = song.remove();
			curNote.play();
			song.add(curNote);
			
			// repeat the section if needed
			
			if (curNote.isRepeat()) {
				// add to tempQueue the starting note of the section
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
			
			// ends of the repeat section
			
			
			//the notes in between the two repeating notes are added to tempQueue
			if (isRepeating && !curNote.isRepeat()) {
				tempQueue.add(curNote);
			}
			
		}
	}
}
