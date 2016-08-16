package logic;

import com.sun.istack.internal.Nullable;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import logic.enums.CellState;
import logic.enums.Direction;
import logic.enums.ShipType;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Илья on 30.03.2016.
 */

public class FieldModel implements Serializable{
    private HashMap<ShipType, Integer> fleet;
    private final int shipsAmount = 10;
    private int curShipsAmount = 0;
    private int oneDeckAmount = 4;
    private int twoDeckAmount = 3;
    private int threeDeckAmount = 2;
    private int fourDeckAmount = 1;

    private String nickName = "chmo";
    private Cell[][] model;
    private Ship[] ships;
    private Ship crutch;
    private Random ran;

    public FieldModel(String nickName){
        this();
        this.nickName = nickName;
    }

    public FieldModel(){
        Random ran = new Random();
        model = new Cell[11][11];
        ships = new Ship[shipsAmount];
        //setHashMap();
        for(int row = 1; row < 11; row++)
            for(int col = 1; col < 11; col++)
                model[row][col] = new Cell(row, col, CellState.NON_STATED, false);
        crutch = new Ship(ShipType.OneDeck, new Cell[] {new Cell(11, 11)});

        fleet = new HashMap<>();
        createFleetTotal();
    }

    public Cell getCell(int row, int col){
        return model[row][col];
    }


    public void setAllShips() {
        int count = 0;
        for (int i = 0; i < Data.maxFourDeckNum; i++) {
            ships[count] = generateShip(ShipType.FourDeck);
            ++count;
        }
        for (int i = 0; i < Data.maxThreeDeckNum; i++) {
            ships[count] = generateShip(ShipType.ThreeDeck);
            ++count;
        }

        for (int i = 0; i < Data.maxDoubleDeckNum; i++) {
            ships[count] = generateShip(ShipType.TwoDeck);
            ++count;
        }

        for (int i = 0; i < Data.maxOneDeckNum; i++) {
            ships[count] = generateShip(ShipType.OneDeck);
            ++count;
            System.out.println("*****Counter is : " + count);
        }

       /*for (int i = 0; i < shipsAmount; i++) {
            System.out.println((i + 1) + " " + ships[i].toString());
            System.out.println();
        }*/
        for(int row = 1; row < 11; row++)
            for(int col = 1; col < 11; col++){
                if(model[row][col].getState() == CellState.NON_STATED)
                    model[row][col].setState(CellState.WATER);
            }
    }


    public Ship generateShip(ShipType type){
        Ship ship;
        Random ran = new Random(3); //Тут стоит зерно аккуратно!!!
        int row = 1;
        int col = 1;
        Vector vector = new Vector(0, 0);
        Direction direction; //the vector's direction
        boolean marker = false; //flag of generation success


        while(!marker){
            //choose the cell which will be the first in new ship
            do {
                row = 1 + ran.nextInt(10);
                col = 1 + ran.nextInt(10);
                //System.out.println("the cell is:( " + row +"; " + col + " )" );
            }while(!this.isValid(row, col));

            int vrow = row;
            int vcol = col;
            int variants = 0;
            int count;

            //choose direction(vector)
            int dir = ran.nextInt(4);
            System.out.println(dir);
            vector = directionSwitch(dir);
            //check cells(first cell and cells created with vector) for validation
            do{
                marker = true;
                count = 0;
                while (marker && count < type.ordinal()) {
                    vrow += vector.getX(); //получаешь Vector.x!!!!(
                    vcol += vector.getY();//получаешь Vector.y!!!!(а мб и не так)000)
                    if(Cell.isExists(vrow, vcol))
                        marker = isValid(vrow, vcol);
                    else
                        marker = false;
                    count++;
                }
                if(!marker) {
                    //change vector's diretion. If no one of 4 vectors is not valid. Choose another cell
                    variants++;
                    dir = (++dir) % 4;
                    System.out.println(dir);
                    vector = directionSwitch(dir);
                    vrow = row;
                    vcol = col;
                }
            }while(!marker && variants < 4);

            //I'm not sure that I need this two rows

            /*
            if(variants == 4)
                marker = true;
              */
        }
        return createShip(row, col, vector, type);
    }


    private boolean isClear(int row, int col){
        return model[row][col].getState() == CellState.NON_STATED
                || model[row][col].getState() == CellState.WATER;
    }


    public boolean isValid(int row, int col) {
        boolean checker = true;
        int counter = 0;
        int i = row - 1;
        int j = col - 1;
        //while (checker == true && counter < 8) {
            while(checker == true && i <= row + 1){
                j = col - 1;
                while (checker == true && j <= col + 1) {
                    if (Cell.isExists(i, j)) {
                        //counter++;
                        checker = isClear(i, j);
                        //System.out.println("Cell (" + i + ";" + " " + j + ")" + model[i][j].getState() + " " + "is Clear: " + checker);
                    }
                    else
                        checker = true;
                    j++;
                }
                i++;
        }
        return checker;
    }


//set the direction's vector
    private Vector directionSwitch(int dir){
        Vector vector;
        switch (dir){
            case 0:vector = new Vector(0, 1);
                break;
            case 1: vector = new Vector(1, 0);
                break;
            case 2: vector = new Vector(0, -1);
                break;
            case 3 : vector = new Vector(-1, 0);
                break;
            default: vector = new Vector(0, 0);
                break;
        }
        System.out.println("The Vestor is: " + vector.getX() + "; " + vector.getY());
        return vector;
    }

    public boolean fleetIsSunk(){
        int count = 0;
        boolean flag = true;
        while(flag && count < ships.length){
            flag = ships[count].isSunk();
            count++;
        }
        return flag;
    }

    //create a definite ship
    public Ship createShip(int row, int col, Vector vector, ShipType type){
            Cell[] cells = new Cell[type.ordinal()];
        try {
            for (int i = 0; i < type.ordinal(); i++) {
                System.out.println("Cell was created! " + row + " " + col);
                cells[i] = model[row][col];
                model[row][col].setState(CellState.DECK);
                row += vector.getX();
                col += vector.getY();
                if (row == 0 || col == 0 || row == 11 || col == 11) {
                    System.out.println("Invalid Cell or Vector. Try Again");
                    return null;
                }
            }
            System.out.println(new Ship(type, cells).toString());

            curShipsAmount++;
            return new Ship(type, cells);
        }catch(Exception e){
            System.out.println("WARNING!!! Cell or Vector is not valid");
            return null;
        }
    }

    public Cell[][] getField() {
        return model;
    }

    public Ship getShipByCell(Cell cell){
        Ship ship = new Ship();
        for(int i = 0; i < ships.length; i++){
            if(ships[i].contains(cell)) {
                ship = ships[i];
                break;
            }
            else ship = crutch;
        }
        return ship;
    }

    public void decreaseShipTypeAmount(ShipType type){
        switch (type.ordinal()){
            case 1: oneDeckAmount--;
                break;
            case 2: twoDeckAmount--;
                break;
            case 3: threeDeckAmount--;
                break;
            case 4: fourDeckAmount--;
                break;
            default: System.out.println("trouble in increaseShipTypesAmount");
        }
    }

    public void createBoards(Ship ship){
        Cell[] cells = ship.getCells();
        for(int i = 0; i < cells.length; i++){
            int x = cells[i].getRow();
            int y = cells[i].getCol();
            for(int j = x - 1; j <= x + 1; j++)
                for(int k = y - 1; k <= y + 1; k++){
                    if(Cell.isExists(j, k)){ //&& !ship.contains(new Cell(j, k)) (???)
                        model[j][k].setChecked(true);
                    }
                }
        }
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setShips(Ship[] ships) {
        this.ships = ships;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getCurShipsAmount() {
        return curShipsAmount;
    }

    public HashMap getFleet(){
        return fleet;
    }

    private void createFleetTotal(){
        fleet.put(ShipType.OneDeck, 4);
        fleet.put(ShipType.TwoDeck, 3);
        fleet.put(ShipType.ThreeDeck, 2);
        fleet.put(ShipType.FourDeck, 1);
    }
}
