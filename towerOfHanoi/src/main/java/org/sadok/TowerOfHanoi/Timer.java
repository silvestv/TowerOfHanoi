package org.sadok.TowerOfHanoi;
import android.os.CountDownTimer;
import android.os.SystemClock;

import java.util.*;

public class Timer {
    private float t_second_game_start;
    private float t_second_game_stop;
    private float t_second_game__total;

    private float triggerSuccess;
    private float triggerError;
    private float triggerAction;

    private float t_second_between_action;
    private float t_second_between_error;
    private float t_second_between_success;

    private ArrayList<Float> allBetweenSucess;
    private ArrayList<Float> allBetweenError;
    private ArrayList<Float> allBetweenAction;

    private float t_average_second_action;
    private float t_average_second_error;
    private float t_average_second_success;

    public Timer(){
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
    public float getTotalTimeGame(){
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
            this.t_second_between_action = System.currentTimeMillis() - this.triggerSuccess;
            this.triggerAction = System.currentTimeMillis();
            this.allBetweenAction.add(t_second_between_action);
        }
    }

    //en fin de game, on récupère le vecteur indiquant tout les lapses-time entre les actions et on en retire la moyenne
    public void calculAverageTimeAction(){

        float sum = 0;
        for(int i = 0; i <= allBetweenAction.size(); i++){
            sum = sum + allBetweenAction.get(i);
        }
        this.t_average_second_action = sum/allBetweenAction.size();
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeSucess(){
        float sum = 0;
        for(int i = 0; i <= allBetweenSucess.size(); i++){
            sum = sum + allBetweenSucess.get(i);
        }
        this.t_average_second_success = sum/allBetweenAction.size();
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeError(){
        float sum = 0;
        for(int i = 0; i <= allBetweenError.size(); i++){
            sum = sum + allBetweenError.get(i);
        }
        this.t_average_second_success = sum/allBetweenAction.size();
    }

    //Les getteurs
    public ArrayList<Float> getAllBetweenAction(){
        return this.allBetweenAction;
    }

    public ArrayList<Float> getAllBetweenSucess(){
        return this.allBetweenSucess;
    }

    public ArrayList<Float> getAllBetweenError(){
        return this.allBetweenError;
    }

    public float getAverageTimeAction(){
        return this.t_average_second_action;
    }

    public float getAverageTimeSucess(){
        return this.t_average_second_success;
    }
    public float getAverageTimeError(){
        return this.t_second_between_error;
    }





}
