/**
 * Class that solves problem of counting abnormal cells.
 * 
 *  @author Minh Ta
 *  @date 09/28/18
 *  @version CSC370-Lab4
 **/
public class Blob {

    /** The grid */
    private TwoDimGrid grid;

    /** Constructors */
    public Blob(TwoDimGrid grid) {
        this.grid = grid;
    }

    /**
     * Finds the number of cells in the blob at (x,y).
     * @pre Abnormal cells are in GridColors.ABNORMAL color;
     *      Other cells are in GridColors.BACKGROUND color.
     * @post All cells in the blob are in the GridColors.TEMPORARY color.
     * @param x The x-coordinate of a blob cell
     * @param y The y-coordinate of a blob cell
     * @return The number of cells in the blob that contains (x, y)
     */
    public int countCells(int x, int y) {
    	if (grid.getColor(x, y).equals(GridColors.ABNORMAL)) {
    		grid.recolor(x, y, GridColors.TEMPORARY);
			int sum = 1;
			for (int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (0 <= i && i < grid.getNCols() && 0 <= j & j < grid.getNRows()) {
	    				if (grid.getColor(i, j).equals(GridColors.ABNORMAL)) {
	        				sum += countCells(i, j);
	    				}
						
					}
				}
			}
			return sum;
    	} else {
    		return 0;
    	}
		
    }
    
    /** Restore the grid by returning all TEMPORARY cells to ABNORMAL */
    public void restore() {
        grid.recolor(GridColors.TEMPORARY, GridColors.ABNORMAL);
    }
    
}
