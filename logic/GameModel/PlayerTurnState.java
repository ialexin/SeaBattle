package logic.GameModel;

import logic.Cell;
import logic.Data;
import logic.FieldModel;
import logic.Ship;
import logic.enums.CellState;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Scanner;

/**
 * Created by Илья on 10.04.2016.
 */
public class PlayerTurnState implements GameState{
    private int ID = 3;

    public void doAction(Context context){
        context.getView().log.textArea.append("State is : " + toString() + "\n");
        context.getView().fieldEN.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = context.getView().convertCol(e.getX());
                int row = context.getView().convertRow(e.getY());
                System.out.println(e.getX());
                System.out.println("The cell is " + col + ", " + row);
                if(!doShot(context.getEnemyField(), row, col)) {
                    context.getView().fieldEN.removeMouseListener(this);
                    context.getView().getSubject().setGameState(new EnemyTurnState());
                }
                else if(context.getEnemyField().fleetIsSunk()){
                    context.getView().fieldEN.removeMouseListener(this);
                    context.getView().getSubject().setGameState(new OverState());
                }
                //context.getView().enemy.getCell(row, col).setState(CellState.DECK);
                //context.getView().enemy.getCell(row, col).setChecked(true);
                context.getView().fieldEN.repaint();
            }
        });
        System.out.println("Your Turn");
        context.setState(this);
    }

    public int getID(){
        return ID;
    }

    public boolean doShot(FieldModel enemy, int row, int col) {
        //Scanner in = new Scanner(System.in);
        //row = in.nextInt();
        //col = in.nextInt();
        if (Cell.isExists(row, col) && !enemy.getCell(row, col).isChecked()) {
            enemy.getCell(row, col).setChecked(true);
            if (enemy.getCell(row, col).getState() == CellState.DECK) {
                enemy.getCell(row, col).setState(CellState.KILLED);
                Ship ship = enemy.getShipByCell(new Cell(row, col));
                if(ship.sink()){
                    Data.curShipsAmount--;
                    ship.getType();
                    enemy.createBoards(ship);
                }
                System.out.println("Nice shot! Choose again");
                return true;
            } else {
                System.out.println("You missed!!!");
                return false;
            }
        } else {
            System.out.println("The cell is not correct. Try again");
            return true;
        }
    }

    public String toString(){
        return "Player's Turn";
    }
}
