/**
 /**
 * Program to draw a Fractal HTree. The use enters the order of the 
 * Fractal HTree to be drawn. The tree is drawn centered in a square
 * window.
 * 
 * @author Minh Ta
 * @date 09/28/18
 * @version - CSC370 Lab 4
 */

import java.awt.*;
import java.util.Date;

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

		Date date = new Date();
		double start = date.getTime();
		drawHTree(order, g, x, y, length);
		double totalTime = date.getTime() - start;
		System.err.println("Time elapsed: " + (totalTime * 1000) + " seconds.");
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
		int halfLen = length / 2;
		g.drawLine(x, y - halfLen, x, y + halfLen);
		g.drawLine(x - halfLen, y - halfLen, x + halfLen, y - halfLen);
		g.drawLine(x - halfLen, y + halfLen, x + halfLen, y + halfLen);
		if (order - 1 != 0 && halfLen != 0) {
			drawHTree(order - 1, g, x - halfLen, y - halfLen, halfLen);
			drawHTree(order - 1, g, x - halfLen, y + halfLen, halfLen);
			drawHTree(order - 1, g, x + halfLen, y - halfLen, halfLen);
			drawHTree(order - 1, g, x + halfLen, y + halfLen, halfLen);
		}

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