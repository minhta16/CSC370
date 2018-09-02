/**
 * @author Minh Ta
 * @date 09/01/2018
 * @version CSC370 Program 2, Fall 2018
 * 
 * Description: A class storing a song and its title and artist.
 * 
 * 
 * Cite Assistance (who and what):
 */

import melody.audio.*;

public class Melody {
	private ArrayQueue<Note> song;
	private String title;
	private String artist;
	private double length;
	private int noteSize;
	
	public Melody(String title, String artist, QueueADT<Note> song) {
		this.title = title;
		this.artist = artist;
		this.song = (ArrayQueue<Note>) song;
		length = 0;
		noteSize = song.size();
		
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
	
	public String getTitle() {
		return title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public double getTotalDuration() {
		return length;
	}
	
	public String toString() {
		String string = title + "\n" + artist + "\n";
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			string += curNote.toString() + "\n";
			song.add(curNote);
		}
		return string;
	}
	
	public void changeTempo(double tempo) {
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			curNote.setDuration(curNote.getDuration() * tempo);
			song.add(curNote);
		}
		length *= tempo;
	}
	
	public void reverse() {
		StackADT<Note> stack = new ArrayStack<Note>();
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			stack.push(curNote);
		}
		for (int i = 0; i < noteSize; i++) {
			Note curNote = stack.pop();
			song.add(curNote);
		}
	}
	
	public void append(Melody other) {
		for (int i = 0; i < other.noteSize; i++) {
			Note curNote = other.song.remove();
			song.add(curNote);
			other.song.add(curNote);
		}
		length += other.length;
		noteSize += other.noteSize;
	}
	
	public void play() {
		boolean isRepeating = false;
		QueueADT<Note> tempQueue = new ArrayQueue<Note>();
		for (int i = 0; i < noteSize; i++) {
			Note curNote = song.remove();
			curNote.play();
			song.add(curNote);
			if (curNote.isRepeat()) {
				tempQueue.add(curNote);
				if (isRepeating) {
					while (!tempQueue.isEmpty()) {
						tempQueue.remove().play();
					}
				}
				isRepeating = !isRepeating;
			}
			
			if (isRepeating && !curNote.isRepeat()) {
				tempQueue.add(curNote);
			}
		}
	}
}
