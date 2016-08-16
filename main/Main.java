package main;

import gameview.ChoiceFrame;
import gameview.multi.MultiView;
import logic.FieldModel;
import logic.GameModel.Context;
import network.Client;
import network.NClient;
import observer.single.Observer;
import observer.single.SingleObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Илья on 31.03.2016.
 */
public class Main {
    public static void main(String[] args){
        ChoiceFrame choice = new ChoiceFrame();
        Object obj = choice.getValue();

        if (obj.equals("Single")){
            FieldModel player = new FieldModel();
            FieldModel enemy = new FieldModel();
            Context context = new Context(player, enemy);
            context.getView().log.textArea.append("tu tu tu tut");

           EventQueue.invokeLater(new Runnable() {
              public void run() {
                    //SingleView view = new SingleView(player, enemy);
                    Observer observer = new SingleObserver(context.getView().getSubject(), context);
              }
           });
        }
        else{
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    MultiView frame = new MultiView(new FieldModel(), new FieldModel());
                }
           });
        }
    }
}