package logic;

import logic.enums.ShipType;

/**
 * Created by Илья on 10.04.2016.
 */
public interface Fieldable {
    public static final int shipsAmount = 10;

    public void setAllShips();
    public Ship createShip(int row, int col, Vector vector, ShipType type);

}
