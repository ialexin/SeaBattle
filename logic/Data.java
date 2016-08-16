package logic;

import logic.enums.ShipType;

import java.util.HashMap;

/**
 * Created by Илья on 30.03.2016.
 */
public class Data {
    public final static int maxOneDeckNum = 4;
    public final static int maxDoubleDeckNum = 3;
    public final static int maxThreeDeckNum = 2;
    public final static int maxFourDeckNum = 1;

    public static int curOneDeckAmount = 0;
    public static int curDoubleDeckAmount = 0;
    public static int curThreeDeckAmount = 0;
    public static int curFourDeckAmount = 0;

    public static int curShipsAmount = 0;
    public static ShipType currentType;
    public static int EXIT_CODE = -1;

}
