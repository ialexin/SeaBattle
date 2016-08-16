package logic;

import logic.enums.CellState;

/**
 * Created by Илья on 30.03.2016.
 */
public class Cell{
    private CellState state = CellState.NON_STATED;
    private int row;
    private int col;
    private boolean checked;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.state = CellState.NON_STATED;
        this.checked = false;
    }

    public Cell(int row, int col, CellState state){
        this.row = row;
        this.col = col;
        this.state = state;
        this.checked = false;
    }

    public Cell(int row, int col, CellState state, boolean checked){
        this.row = row;
        this.col = col;
        this.state = state;
        this.checked = checked;
    }

    /*
    method defines whether new cell will have neighbors
    @return boolean checker. Returns true if some of cells was already filled by ships
     */
    /*
    public boolean isValid(){
        boolean checker = true;
        int counter = 0;
        while(checker == true && counter < 8) {
            for (int i = this.row - 1; i <= this.row + 1; i++, counter++)
                for (int j = this.col - 1; j <= this.col + 1; j++, counter++)
                    if (isExists(i, j))
                        checker = isClear(i, j);
        }
        return checker;
    }


    public static boolean isValid(int row, int col){
        boolean checker = isExists(row, col);
        int counter = 0;
        while(checker == true && counter < 8) {
            for (int i = row - 1; i <= row + 1; i++, counter++)
                for (int j = col - 1; j <= col + 1; j++, counter++)
                    if (isExists(i, j))
                        checker = isClear(i, j);
        }
        return checker;
    }


    private boolean isClear(int row, int col){
        return FieldModel.getCell(row, col).getState() == CellState.NON_STATED
                || FieldModel.getCell(row, col).getState() == CellState.WATER;
    }
    */

    public static boolean isExists(int row, int col){
        return (row > 0) && (row < 11) && (col > 0) && (col < 11);
    }

    public boolean equals(Cell cell){
        if(this.getRow() == cell.getRow() && this.getCol() == cell.getCol())
            return true;
        else return false;
    }

    public String toString(){
        return "(" + row + "; " + col + ") " + "state: " + this.getState() + "| ";
    }

    public CellState getState() {
        return state;
    }

    public void setState(CellState state) {
        this.state = state;
    }

    public void setChecked(boolean checked){
        this.checked = checked;
    }

    public boolean isChecked(){
        return checked;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
