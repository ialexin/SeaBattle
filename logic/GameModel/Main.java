package logic.GameModel;

import logic.FieldModel;
import observer.single.Observer;
import observer.single.SingleObserver;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Илья on 20.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        Random ran = new Random();
        Scanner in = new Scanner(System.in);
        //мб все FieldModels и SingleView засунуть внутрь Context, чтобы не создавать лищние ссылки на одни и те же объекты

        FieldModel player = new FieldModel();
        FieldModel enemy = new FieldModel();
        Context context = new Context(player, enemy);
        context.getView().log.textArea.append("tu tu tu tut");

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                //SingleView view = new SingleView(player, enemy);
                Observer observer = new SingleObserver(context.getView().getSubject(), context);
        /*
        while(true){
            State state = context.getState();
            state.doAction(context);
            if(player.getCell(1, 2).getState() == CellState.DECK)
                break;
        }*/
                System.out.println("The game is over. Sorry, bro");
            }
        });
    }
}