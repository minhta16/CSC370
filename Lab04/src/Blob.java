/**
 * Class that solves problem of counting abnormal cells.
 * 
 *  @author 
 *  @date
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
       return 0; 
    }

    
    /** Restore the grid by returning all TEMPORARY cells to ABNORMAL */
    public void restore() {
        grid.recolor(GridColors.TEMPORARY, GridColors.ABNORMAL);
    }
    
}
