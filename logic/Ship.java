package logic;

import logic.enums.CellState;
import logic.enums.ShipState;
import logic.enums.ShipType;


/**
 * Created by Илья on 30.03.2016.
 */
public class Ship {
    private ShipState state = ShipState.NON_STATED;
    private ShipType type;
    private Cell[] cells;
    private boolean sunk;

    public Ship(){
        this.type = ShipType.ThreeDeck;
        this.cells = new Cell[3];
        this.sunk = false;
        this.state = ShipState.NON_STATED;
    }

    public Ship(ShipType type){
        this.type = type;
        state = ShipState.INTACT;
        cells = new Cell[type.ordinal()];
    }

    public Ship(ShipType type, Cell[] cells){
        this.type = type;
        state = ShipState.INTACT;
        this.cells = cells;
        this.sunk = false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder("The ship consists of cells: ");
        for (int i = 0; i < cells.length; i++){
            str.append(cells[i].toString() + " ");
        }
        return str.toString();
    }


    //TODO: example list
    //FIXME: niceJob
    public boolean sink(){
        int count = 0;
        boolean flag = true;
        while(flag && count < cells.length){
            if(cells[count].getState() == CellState.KILLED){
                count++;
            }
            else flag = false;
        }
        if (count == cells.length && flag) {
            Data.curShipsAmount--;
            this.setState(ShipState.DESTROYED);
            setSunk(true);
            return flag;
        }
        else return false;
    }

    public boolean contains(Cell cell){
        boolean flag = false;
        for(int i = 0; i < this.cells.length; i++){
            if(cells[i].equals(cell)){
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public ShipState getState() {
        return state;
    }

    public void setState(ShipState state) {
        this.state = state;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }
}
