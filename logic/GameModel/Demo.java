package logic.GameModel;

import gameview.ConsoleView;
import logic.FieldModel;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Илья on 10.04.2016.
 */
public class Demo {
        public static void main(String[] args) {
            Random ran = new Random();
            Scanner in = new Scanner(System.in);
            FieldModel player = new FieldModel();
            FieldModel enemy = new FieldModel();
            Context context = new Context(player, enemy);


            ConsoleView game = new ConsoleView();

            StartState startState = new StartState();
            startState.doAction(context);

            System.out.println("Input s to Initialize the field");
            game.startGame();

            InitState initState = new InitState();
            initState.doAction(context);
            initState.doInit(game.getPlayerFieldModel());
            initState.doInit(game.getEnemyFieldModel());

            game.showPlayerField();
            System.out.println("\n\n");
            game.showEnemyField();

            PlayerTurnState playerTurnState = new PlayerTurnState();
            EnemyTurnState enemyTurnState = new EnemyTurnState();

            playerTurnState.doAction(context);

            while (!game.getEnemyFieldModel().fleetIsSunk() && !game.getPlayerFieldModel().fleetIsSunk()) {
                System.out.println("\nChoose the cell to shot. Enter Row and Column");
                while (playerTurnState.doShot(game.getEnemyFieldModel(), in.nextInt(), in.nextInt())) {
                    System.out.println("\n\n");
                    game.showEnemyField();
                }
                game.showEnemyField();

                enemyTurnState.doAction(context);
                while (enemyTurnState.doShot(game.getPlayerFieldModel(), 1 + ran.nextInt(10), 1 + ran.nextInt(10))) {
                    System.out.println("\n\n");
                    game.showPlayerField();
                }
                game.showPlayerField();
            }

            OverState overState = new OverState();
            overState.doAction(context);
            overState.doResult(game.getPlayerFieldModel(), game.getEnemyFieldModel());
            System.out.println("Game is over");
        }
}
