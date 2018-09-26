/**
 /**
 * Program to draw a Fractal HTree. The use enters the order of the 
 * Fractal HTree to be drawn. The tree is drawn centered in a square
 * window.
 * 
 * @author
 * @date
 * @version - CSC370 Lab 4
 */

import java.awt.*;

import javax.swing.JOptionPane;

public class FractalHTree {
	public static final int SIZE = 600;
	public static final int MAX_LEVEL = 15;

	public static void main(String[] args) {
		DrawingPanel panel = new DrawingPanel(SIZE, SIZE);
		Graphics g = panel.getGraphics();

		int halfSize = SIZE / 2;
		int x = halfSize;
		int y = halfSize;
		int length = halfSize;

		int order = 0;
		do {
			try {
				String reply = JOptionPane.showInputDialog("Enter order of H Tree");
				order = Integer.parseInt(reply);
				if (order < 1 || order > MAX_LEVEL) {
					JOptionPane.showMessageDialog(null,
							"The order must be at least 1 " + "and less than or equal to " + MAX_LEVEL);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Enter an integer!");
			}
		} while (order < 1 || order > MAX_LEVEL);

		drawHTree(order, g, x, y, length);
	}

	/**
	 * Draws a fractalH centered at (x,y) with segments of the given length
	 * 
	 * @param order
	 *            - order of the fractalH
	 * @param g
	 *            - facilitates drawing in the DrawingPanel
	 * @param x
	 *            - the x-coordinate of center of the H
	 * @param y
	 *            - the y-coordinate of center of the H
	 * @param length
	 *            - length of the segments of the H
	 * 
	 */
	public static void drawHTree(int order, Graphics g, int x, int y, int length) {


	

	}

	/**
	 * A "pause" function for use in slowing down drawing (so that you can see
	 * the growth). This method shows the current drawing after the pause ends.
	 * 
	 * @param milliseconds
	 *            The number of milliseconds to wait
	 */
	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// ignoring exception at the moment
		}
	}

}