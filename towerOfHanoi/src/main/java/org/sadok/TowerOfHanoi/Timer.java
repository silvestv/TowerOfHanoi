package org.sadok.TowerOfHanoi;
import android.os.CountDownTimer;
import android.os.SystemClock;

import java.util.*;

public class Timer {
    private long t_second_game_start;
    private long t_second_game_stop;
    private long t_second_game__total;

    private long triggerSuccess;
    private long triggerError;
    private long triggerAction;

    private long t_second_between_action;
    private long t_second_between_error;
    private long t_second_between_success;

    private ArrayList<Long> allBetweenSucess;
    private ArrayList<Long> allBetweenError;
    private ArrayList<Long> allBetweenAction;

    private long t_average_second_action;
    private long t_average_second_error;
    private long t_average_second_success;

    public Timer(){
        this.allBetweenSucess = new ArrayList<>();
        this.allBetweenError = new ArrayList<>();
        this.allBetweenAction = new ArrayList<>();
    }

    //permet de lancer le chrono
    public void start(){
        this.t_second_game_start = System.currentTimeMillis();

    }

    //permet de stoper le chrono à declencher lorsque la partie est finie
    public void stop(){
        this.t_second_game_stop = System.currentTimeMillis();
    }

    //renvoie le temps total d'une partie
    public long getTotalTimeGame(){
        return t_second_game_start - t_second_game_stop;
    }

    //déclenche le timer entre 2 succès --> si aucun trigger n'a été initialiser, on l'initialise, sinon,
    //on prend en compte le temps entre le 1er et le 2nd succès et on range ce temps dans un vecteur AllBetweenSucess
    public void clickSuccess(){
        if(triggerSuccess == 0){
            this.triggerSuccess = System.currentTimeMillis();
        } else {
            this.t_second_between_success = System.currentTimeMillis() - this.triggerSuccess;
            this.triggerSuccess = System.currentTimeMillis();
            this.allBetweenSucess.add(t_second_between_success);

        }
    }

    //même fonctionnement pour les erreurs
    public void clickError(){
        if(triggerError == 0){
            this.triggerError = System.currentTimeMillis();
        } else {
            this.t_second_between_error = System.currentTimeMillis() - this.triggerError;
            this.triggerError = System.currentTimeMillis();
            this.allBetweenError.add(t_second_between_error);
        }
    }

    //même fonctionnement entre chaque action du joueur
    public void clickAction(){
        if(triggerAction == 0){
            this.triggerAction = System.currentTimeMillis();
        } else {
            this.t_second_between_action = System.currentTimeMillis() - this.triggerAction;
            this.triggerAction = System.currentTimeMillis();
            this.allBetweenAction.add(t_second_between_action);
        }
    }

    //en fin de game, on récupère le vecteur indiquant tout les lapses-time entre les actions et on en retire la moyenne
    public void calculAverageTimeAction(){

        long sum = 0;
        for(int i = 0; i <= allBetweenAction.size(); i++){
            sum = sum + allBetweenAction.get(i);
        }
        this.t_average_second_action = sum/allBetweenAction.size();
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeSucess(){
        long sum = 0;
        for(int i = 0; i <= allBetweenSucess.size(); i++){
            sum = sum + allBetweenSucess.get(i);
        }
        this.t_average_second_success = sum/allBetweenAction.size();
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeError(){
        long sum = 0;
        for(int i = 0; i <= allBetweenError.size(); i++){
            sum = sum + allBetweenError.get(i);
        }
        this.t_average_second_success = sum/allBetweenAction.size();
    }

    //Les getteurs
    public ArrayList<Long> getAllBetweenAction(){
        return this.allBetweenAction;
    }

    public ArrayList<Long> getAllBetweenSucess(){
        return this.allBetweenSucess;
    }

    public ArrayList<Long> getAllBetweenError(){
        return this.allBetweenError;
    }

    public long getAverageTimeAction(){
        return this.t_average_second_action;
    }

    public long getAverageTimeSucess(){
        return this.t_average_second_success;
    }
    public long getAverageTimeError(){
        return this.t_average_second_error;
    }

    public long getTimeBetweenAction(){
        return this.t_second_between_action;
    }

    public long getTimeBetweenSuccess(){
        return this.t_second_between_success;
    }
    public long getTimeBetweenError(){
        return this.t_second_between_error;
    }

    public long getTriggerSuccess(){
        return this.triggerSuccess;
    }

    public long getTriggerError(){
        return this.triggerError;
    }
    public long getTriggerAction(){
        return this.triggerAction;
    }

    public long getT_second_game_start(){
        return t_second_game_start;
    }

    public long getT_second_game_stop(){
        return t_second_game_stop;
    }



}
