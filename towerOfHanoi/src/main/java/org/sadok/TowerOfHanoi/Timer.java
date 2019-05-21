package org.sadok.TowerOfHanoi;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Timer {


    private double t_second_game_start;
    private double t_second_game_playerTouchStart;
    private double t_second_game_stop;

    private int nb_action;
    private int nb_sucess;
    private int nb_error;

    private double triggerSuccess;
    private double triggerError;
    private double triggerAction;

    private double t_second_between_action;
    private double t_second_between_error;
    private double t_second_between_success;
    private double t_second_between_success_then_error;
    private double t_second_between_error_then_sucess;

    private ArrayList<Double> allBetweenSucess;
    private ArrayList<Double> allBetweenError;
    private ArrayList<Double> allBetweenAction;
    private ArrayList<Double> allBetweenSuccessThenError;
    private ArrayList<Double> allBetweenErrorThenSucess;

    private double t_average_second_action;
    private double t_average_second_error;
    private double t_average_second_success;
    private double t_average_second_sucess_then_error;
    private double t_average_second_error_then_sucess;

    private Map<Integer, Character> chronologicActionMap;

    ///////////CONSTRUCT
    public Timer(){
        this.nb_action = 0;
        this.nb_sucess = 0;
        this.nb_error = 0;
        this.chronologicActionMap = new LinkedHashMap<Integer, Character>();
        //'D' pour début, en effet l'action de rang 0 n'est ni un succès ni un échec
        this.addToChronologicActionMap('D');

        this.allBetweenSucess = new ArrayList<>();
        this.allBetweenError = new ArrayList<>();
        this.allBetweenAction = new ArrayList<>();
        this.allBetweenSuccessThenError = new ArrayList<>();
        this.allBetweenErrorThenSucess = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //CHRONO GENERAL
    //permet de lancer le chrono en début de partie
    public void start(){
        this.t_second_game_start = System.currentTimeMillis();

    }

    //permet de lancer le chrono depuis le premier touché de l'écran par le joueur
    // la différence trueStart - start équivaut au temps de réflection initial du joueur
    public void playerTouchStart(){
        this.t_second_game_playerTouchStart = System.currentTimeMillis();
    }
    //permet de stoper le chrono à declencher lorsque la partie est finie
    public void stop(){
        this.t_second_game_stop = System.currentTimeMillis();
    }


    //ajoute une map donnant l'indice de l'action jouée en clé et la nature de l'action en valeur (D,S,E pour Debut, Sucess, Error)
    public void addToChronologicActionMap(Character kindOfAction){
            this.chronologicActionMap.put(this.nb_action, kindOfAction);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //LES TRIGGERS (calcul des différences entre action, succès, erreurs, succès-erreur, erreur-succès


    //même fonctionnement entre chaque action du joueur
    public void clickAction(){
        //le click action est déclencher quelquesoit la nature de l'action
        //nb_action est incrémenter quelquesoit le type de l'action (sucess ou error)
        this.nb_action++;
        if(triggerAction == 0){
            //entre la permière action et le debut de la partie
            this.t_second_between_action = System.currentTimeMillis() - this.t_second_game_start;
            this.allBetweenAction.add(t_second_between_action);
            this.triggerAction = System.currentTimeMillis();
        } else {
            this.t_second_between_action = System.currentTimeMillis() - this.triggerAction;
            this.triggerAction = System.currentTimeMillis();
            this.allBetweenAction.add(t_second_between_action);
        }
    }

    //déclenche en cas d'action de succès
    //déclenche le timer entre 2 succès ou 1 erreur puis succès--> si aucun trigger n'a été initialiser, on l'initialise, sinon,
    //on prend en compte le temps entre le 1er et le 2nd succès et on range ce temps dans un vecteur AllBetweenSucess ou allBetweenErrorThenSucess
    public void clickSuccess(){
        this.nb_sucess++;
        //une action est soit un succès soit une erreur
        this.addToChronologicActionMap('S');
        //init
        if(triggerSuccess == 0){
            this.triggerSuccess = System.currentTimeMillis();

        //entre 2 succès
        } else {
            this.t_second_between_success = System.currentTimeMillis() - this.triggerSuccess;
            this.allBetweenSucess.add(t_second_between_success);

            //entre echec puis succès
           //if(triggerError != 0){
            if(nb_action != 0){
                //vérifie l'action précédente effectuée, si cette dernière est un échec alors comptabiliser
                //une erreur puis un succès
                if(this.chronologicActionMap.get(this.nb_action-1) != null && this.chronologicActionMap.get(this.nb_action-1) == 'E'){

                    this.t_second_between_error_then_sucess = System.currentTimeMillis() - this.triggerError;
                    this.allBetweenErrorThenSucess.add(t_second_between_error_then_sucess);
                }
                this.triggerSuccess = System.currentTimeMillis();
            } else {
                System.out.println("ATTENTION PROBLEME DANS LE DECOMPTE DU NB D'ACTIONS !");
            }
        }
    }

    //déclenche en cas d'action d'erreurs
    //même fonctionnement pour les erreurs
    public void clickError(){
        this.nb_error++;
        this.addToChronologicActionMap('E');
        //init
        if(triggerError == 0){
            this.triggerError = System.currentTimeMillis();

        //entre 2 echec
        } else {
            this.t_second_between_error = System.currentTimeMillis() - this.triggerError;
            this.allBetweenError.add(t_second_between_error);

            //entre succès puis erreur
            //if(triggerSuccess != 0){

            if(this.nb_action != 0){
                //vérifie l'action précédente effectuée, si cette dernière et un succès alors comptabiliser
                //un succès puis une erreur
                if(this.chronologicActionMap.get(this.nb_action-1) !=null && this.chronologicActionMap.get(this.nb_action-1) == 'S'){
                    this.t_second_between_success_then_error = System.currentTimeMillis() - this.triggerSuccess;
                    this.allBetweenSuccessThenError.add(t_second_between_success_then_error);
                }
                this.triggerError = System.currentTimeMillis();
            } else {
                System.out.println("ATTENTION PROBLEME DANS LE DECOMPTE DU NB D'ACTIONS !");
            }

        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////CALCUL DES TEMPS MOYENS AU COURS D'UNE PARTIE

    //en fin de game, on récupère le vecteur indiquant tout les lapses-time entre les actions et on en retire la moyenne
    public void calculAverageTimeAction(){

        double sum = 0;
        for(int i = 0; i < allBetweenAction.size(); i++){
            sum = sum + allBetweenAction.get(i);
        }
        if (allBetweenAction.size() != 0) {
            this.t_average_second_action = sum / allBetweenAction.size();
        }
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeSucess(){
        double sum = 0;
        for(int i = 0; i < allBetweenSucess.size(); i++){
            sum = sum + allBetweenSucess.get(i);
        }
        if (allBetweenSucess.size() != 0) {
            this.t_average_second_success = sum / allBetweenSucess.size();
        }
    }

    //fin de game, même fonctionnement que pour le AverageTimeAction()
    public void calculAverageTimeError(){
        double sum = 0;
        for(int i = 0; i < allBetweenError.size(); i++){
            sum = sum + allBetweenError.get(i);
        }
        if (allBetweenError.size() != 0) {
            this.t_average_second_error = sum / allBetweenError.size();
        }
    }

    public void calculAverageTimeSucessThenError(){
        double sum = 0;
        for(int i = 0; i < allBetweenSuccessThenError.size(); i++){
            sum = sum + allBetweenSuccessThenError.get(i);
        }
        if (allBetweenSuccessThenError.size() != 0) {
            this.t_average_second_sucess_then_error = sum / allBetweenSuccessThenError.size();
        }
    }

    public void calculAverageTimeErrorThenSucess(){
        double sum = 0;
        for(int i = 0; i < allBetweenErrorThenSucess.size(); i++){
            sum = sum + allBetweenErrorThenSucess.get(i);
        }
        if (allBetweenErrorThenSucess.size() != 0) {
            this.t_average_second_error_then_sucess = sum / allBetweenErrorThenSucess.size();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////LES ACCESSEURS

    //Les getteurs

    //renvoie le temps total d'une partie
    public double getTotalTimeGame(){
        return  t_second_game_stop - t_second_game_start;
    }

    public double getTotalTimeGameSinceFirstTouch(){
        return t_second_game_stop - t_second_game_playerTouchStart;
    }
    
    //retourne le temps entre le lancement de la partie (system) et le premier touché screen de l'écran
    public double getInitialPlayerThinkingTime(){
        return t_second_game_playerTouchStart - t_second_game_start;
    }

    public double getT_second_game_playerTouchStart(){
        return t_second_game_playerTouchStart;
    }

    public int getNbAction(){
        return this.nb_action;
    }

    public int getNbSucess(){
        return this.nb_sucess;
    }
    public int getNbError(){
        return this.nb_error;
    }

    public Map<Integer, Character> getChronologicActionMap(){
        return this.chronologicActionMap;
    }

    public ArrayList<Double> getAllBetweenAction(){
        return this.allBetweenAction;
    }

    public ArrayList<Double> getAllBetweenSucess(){
        return this.allBetweenSucess;
    }

    public ArrayList<Double> getAllBetweenError(){
        return this.allBetweenError;
    }

    public ArrayList<Double> getAllBetweenSucessThenError(){
        return this.allBetweenSuccessThenError;
    }

    public ArrayList<Double> getAllBetweenErrorThenSucess(){
        return this.allBetweenErrorThenSucess;
    }



    public double getAverageTimeAction(){
        calculAverageTimeAction();
        return this.t_average_second_action;
    }

    public double getAverageTimeSucess(){
        calculAverageTimeSucess();
        return this.t_average_second_success;
    }
    public double getAverageTimeError(){
        calculAverageTimeError();
        return this.t_average_second_error;
    }

    public double getAverageTimeSucessThenError(){
        calculAverageTimeSucessThenError();
        return this.t_average_second_sucess_then_error;
    }
    public double getAverageTimeErrorThenSucess(){
        calculAverageTimeErrorThenSucess();
        return this.t_average_second_error_then_sucess;
    }



    public double getTimeBetweenAction(){
        return this.t_second_between_action;
    }

    public double getTimeBetweenSuccess(){
        return this.t_second_between_success;
    }
    public double getTimeBetweenError(){
        return this.t_second_between_error;
    }

    public double getTimeBetweenSuccessThenError(){
        return this.t_second_between_success_then_error;
    }
    public double getTimeBetweenErrorThenSucess(){
        return this.t_second_between_error_then_sucess;
    }



    public double getTriggerSuccess(){
        return this.triggerSuccess;
    }

    public double getTriggerError(){
        return this.triggerError;
    }
    public double getTriggerAction(){
        return this.triggerAction;
    }



    public double getT_second_game_start(){
        return t_second_game_start;
    }

    public double getT_second_game_stop(){
        return t_second_game_stop;
    }

}
