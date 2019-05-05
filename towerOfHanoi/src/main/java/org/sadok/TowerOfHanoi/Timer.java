package org.sadok.TowerOfHanoi;
import android.os.CountDownTimer;
import android.os.SystemClock;

import java.util.*;

public class Timer {
    private long t_second_game_start;
    private long t_second_game_stop;
    private long t_second_game_total;

    private long triggerSuccess;
    private long triggerError;
    private long triggerAction;

    private long t_second_between_action;
    private long t_second_between_error;
    private long t_second_between_success;
    private long t_second_between_success_then_error;
    private long t_second_between_error_then_sucess;

    private ArrayList<Long> allBetweenSucess;
    private ArrayList<Long> allBetweenError;
    private ArrayList<Long> allBetweenAction;
    private ArrayList<Long> allBetweenSuccessThenError;
    private ArrayList<Long> allBetweenErrorThenSucess;

    private long t_average_second_action;
    private long t_average_second_error;
    private long t_average_second_success;
    private long t_average_second_sucess_then_error;
    private long t_average_second_error_then_sucess;

    ///////////CONSTRUCT
    public Timer(){
        this.allBetweenSucess = new ArrayList<>();
        this.allBetweenError = new ArrayList<>();
        this.allBetweenAction = new ArrayList<>();
        this.allBetweenSuccessThenError = new ArrayList<>();
        this.allBetweenErrorThenSucess = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CHRONO GENERAL
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //LES TRIGGERS (calcul des différences entre action, succès, erreurs, succès-erreur, erreur-succès

    //déclenche en cas d'action de succès
    //déclenche le timer entre 2 succès ou 1 erreur puis succès--> si aucun trigger n'a été initialiser, on l'initialise, sinon,
    //on prend en compte le temps entre le 1er et le 2nd succès et on range ce temps dans un vecteur AllBetweenSucess ou allBetweenErrorThenSucess
    public void clickSuccess(){
        //init
        if(triggerSuccess == 0){
            this.triggerSuccess = System.currentTimeMillis();

        //entre 2 succès
        } else {
            this.t_second_between_success = System.currentTimeMillis() - this.triggerSuccess;
            this.allBetweenSucess.add(t_second_between_success);

            //entre echec puis succès
            if(triggerError != 0){
                this.t_second_between_error_then_sucess = System.currentTimeMillis() - this.triggerError;
                this.allBetweenErrorThenSucess.add(t_second_between_error_then_sucess);
            }
            this.triggerSuccess = System.currentTimeMillis();
        }
    }

    //déclenche en cas d'action d'erreurs
    //même fonctionnement pour les erreurs
    public void clickError(){
        //init
        if(triggerError == 0){
            this.triggerError = System.currentTimeMillis();

        //entre 2 echec
        } else {
            this.t_second_between_error = System.currentTimeMillis() - this.triggerError;
            this.allBetweenError.add(t_second_between_error);

            //entre succès puis erreur
            if(triggerSuccess != 0){
                this.t_second_between_success_then_error = System.currentTimeMillis() - this.triggerSuccess;
                this.allBetweenSuccessThenError.add(t_second_between_success_then_error);
            }
            this.triggerError = System.currentTimeMillis();
        }
    }

    //même fonctionnement entre chaque action du joueur
    public void clickAction(){
        if(triggerAction == 0){
            //entre la permière action et le debut de la partie
            this.t_second_between_action = System.currentTimeMillis() - this.t_second_game_start;
            this.triggerAction = System.currentTimeMillis();
        } else {
            this.t_second_between_action = System.currentTimeMillis() - this.triggerAction;
            this.triggerAction = System.currentTimeMillis();
            this.allBetweenAction.add(t_second_between_action);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////CALCUL DES TEMPS MOYENS AU COURS D'UNE PARTIE

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
        this.t_average_second_success = sum/allBetweenSucess.size();
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeError(){
        long sum = 0;
        for(int i = 0; i <= allBetweenError.size(); i++){
            sum = sum + allBetweenError.get(i);
        }
        this.t_average_second_error = sum/allBetweenError.size();
    }

    public void calculAverageTimeSucessThenError(){
        long sum = 0;
        for(int i = 0; i <= allBetweenSuccessThenError.size(); i++){
            sum = sum + allBetweenSuccessThenError.get(i);
        }
        this.t_average_second_sucess_then_error = sum/allBetweenSuccessThenError.size();
    }

    public void calculAverageTimeErrorThenSucess(){
        long sum = 0;
        for(int i = 0; i <= allBetweenErrorThenSucess.size(); i++){
            sum = sum + allBetweenErrorThenSucess.get(i);
        }
        this.t_average_second_error = sum/allBetweenErrorThenSucess.size();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////LES ACCESSEURS

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

    public ArrayList<Long> getAllBetweenSucessThenError(){
        return this.allBetweenSuccessThenError;
    }

    public ArrayList<Long> getAllBetweenErrorThenSucess(){
        return this.allBetweenErrorThenSucess;
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

    public long getAverageTimeSucessThenError(){
        return this.t_average_second_sucess_then_error;
    }
    public long getAverageTimeErrorThenSucess(){
        return this.t_average_second_error_then_sucess;
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

    public long getTimeBetweenSuccessThenError(){
        return this.t_second_between_success_then_error;
    }
    public long getTimeBetweenErrorThenSucess(){
        return this.t_second_between_error_then_sucess;
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
