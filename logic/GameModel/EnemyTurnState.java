package logic.GameModel;

import logic.Cell;
import logic.FieldModel;
import logic.enums.CellState;
import logic.enums.State;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Илья on 10.04.2016.
 */
public class EnemyTurnState implements GameState {
    private int ID  = 4;
    State state = State.EN_TURN;

    public void doAction(Context context) {
        context.getView().log.textArea.append("State is : " + toString());
        Random ran = new Random();
        System.out.println("Enemy's turn!");
        if(!doShot(context.getPlayerField(),  ran.nextInt(10) + 1, ran.nextInt(10) + 1))
            context.getView().getSubject().setGameState(new PlayerTurnState());
        else if(context.getPlayerField().fleetIsSunk()){
            context.getView().getSubject().setGameState(new OverState());
        }
        else{
            context.getView().getSubject().setGameState(new EnemyTurnState());
        }
        context.getView().fieldPL.repaint();
    }

    public boolean doShot(FieldModel player, int row, int col) {
        System.out.println("The enemy chose cell: " + row + " " + col);
        if (Cell.isExists(row, col) && !player.getCell(row, col).isChecked()) {
            player.getCell(row, col).setChecked(true);
            if (player.getCell(row, col).getState() == CellState.DECK) {
                player.getCell(row, col).setState(CellState.KILLED);
                System.out.println("Ship is injured");
                return true;
            } else {
                player.getCell(row, col).setState(CellState.MISSED);
                System.out.println("Enemy missed!!!");
                return false;
            }
        } else {
            System.out.println("The cell is not correct");
            return true;
        }
    }

    public int getID(){
        return ID;
    }

    public String toString(){
        return "Enemy's turn";
    }
}

