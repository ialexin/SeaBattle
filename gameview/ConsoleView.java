package gameview;

import logic.Cell;
import logic.FieldModel;
import logic.enums.CellState;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by Илья on 28.03.2016.
 */
public class ConsoleView {
    FieldModel player = new FieldModel();
    FieldModel enemy = new FieldModel();

    public ConsoleView(){
        player = new FieldModel("Player");
        enemy = new FieldModel("Enemy");
    }

    public FieldModel getPlayerFieldModel(){
        return player;
    }

    public FieldModel getEnemyFieldModel(){
        return enemy;
    }

    public void showPlayerField(){
        {
            System.out.print("  ");
            for (int i = 1; i < 11; i++)
                System.out.print(i + " ");
            for(int i = 1; i < 11; i++){
                System.out.println();

                System.out.print(i + " ");
                for (int j = 1; j < 11; j++){
                    int flag = 3;
                    if(player.getField()[i][j].getState() == CellState.DECK)
                        flag = 1;
                    else if(player.getField()[i][j].getState() == CellState.WATER)
                        flag = 0;
                    else
                        flag = 2;
                    System.out.print(flag + " ");
                    }
                }
            System.out.println("\n\n");
        }
    }

    public void showEnemyField() {
        System.out.print("  ");
        for (int i = 1; i < 11; i++)
            System.out.print(i + " ");
        for (int i = 1; i < 11; i++) {
            System.out.println();

            System.out.print(i + " ");
            for (int j = 1; j < 11; j++) {
                int flag = 3;
                if (enemy.getCell(i, j).isChecked()) {
                    if (enemy.getField()[i][j].getState() == CellState.KILLED)
                        flag = 1;
                    else
                        flag = 0;
                } else
                    flag = 7;
                //if (player.getField()[i][j].getState() == CellState.WATER)
                System.out.print(flag + " ");
            }
        }
        System.out.println("\n\n");
    }

    public void startGame(){
        Scanner in = new Scanner(System.in);
        char enter = 'a';
        while(enter != 's') {
            enter = in.next().charAt(0);
            if(enter != 's')
                System.out.println("try again");
        }
    }
}
