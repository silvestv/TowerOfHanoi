package org.sadok.TowerOfHanoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private Timer reportTimer;
    private TowerOfHanoiActivity menuChoices;

    private int IDReport;
    private static int nb_Reports;
    private String textReport;

    private static Map<Integer, Report> allReports = new HashMap<>();

    public Report(Timer reportTimer, TowerOfHanoiActivity menuChoices){
        this.reportTimer = reportTimer;
        this.menuChoices = menuChoices;

        //créer l'ID du report
        this.IDReport = this.nb_Reports;
        //créer le textReport
        this.createReport();
        //range l'instance courante dans une Map
        this.allReports.put(this.IDReport, this);
        //incrémente le nombre de rapports
        this.nb_Reports++;


    }

    //Retourne le Timer d'une partie
    public Timer getReportTimer() {
        return this.reportTimer;
    }

    //Retourne l'objet menu d'une partie
    public TowerOfHanoiActivity getMenuChoices() {
        return this.menuChoices;
    }

    //Retourne l'id du report courant
    public int getIDReport() {
        return IDReport;
    }

    //retourne le nombre d'instance de cette classe (soit le nombre de rapport total)
    public static int getNb_Reports() {
        return nb_Reports;
    }

    //retourne tout les reports de toutes les parties
    public static Map<Integer, Report> getAllReports() {
        return allReports;
    }

    public String getTextReport() {
        return textReport;
    }

    //première version console simple du report/créé le rapport utiliser dans le constructeur
    public void createReport(){

        String s = "";
        s = s+"INFORMATIONS GENERALES\n";
        s = s+"Numéro de report : "+this.getIDReport()+"\n";
        s = s+"Nom joueur : Joueur "+this.getIDReport()+"\n";
        s = s+"Prénom joueur : "+"\n";
        s = s+"------------------------------------------------------\n";
        s = s+"OPTIONS CHOISIES\n";
        s = s+"FeedBack choisi : "+getMenuChoices().getSelectedFeedBackItem()+"\n";
        s = s+"Forme des palets choisis"+getMenuChoices().getSelectedShapeItem()+"\n";
        s = s+"Nombre de palets choisis"+getMenuChoices().getSelectedItem()+"\n";
        s = s+"------------------------------------------------------\n";
        s = s+"PERFORMANCES TEMPS/COUPS\n";
        s = s+"Nombre de coups avant réussite : "+getReportTimer().getNbClick()+"\n";
        s = s+"Temps total partie : "+getReportTimer().getTotalTimeGame()/1000+" s\n";
        s = s+"Temps moyen entre 2 actions : "+getReportTimer().getAverageTimeAction()/1000+" s\n";
        s = s+"Temps moyen entre 2 succès : "+getReportTimer().getAverageTimeSucess()/1000+" s\n";
        s = s+"Temps moyen entre 2 échecs : "+getReportTimer().getAverageTimeError()/1000+" s\n";
        s = s+"------------------------------------------------------\n";
        s = s+"DETAILS PERFORMANCES TEMPS/COUPS (TABLEAU)\n";
        s = s+"Temps entre chaque action --->  intervalle entre l'action : \n";
        for(int i = 1; i<getReportTimer().getAllBetweenAction().size(); i++){
                s = s+""+(i-1)+" - "+i+" : "+getReportTimer().getAllBetweenAction().get(i)/1000+" s\n";
        }
        s = s+"Temps entre 2 succès --->  intervalle entre le succès : \n";
        for(int i = 1; i<getReportTimer().getAllBetweenSucess().size(); i++){
            s = s+""+(i-1)+" - "+i+" : "+getReportTimer().getAllBetweenSucess().get(i)/1000+" s\n";
        }
        s = s+"Temps entre chaque action --->  intervalle entre l'échec : \n";
        for(int i = 1; i<getReportTimer().getAllBetweenError().size(); i++){
            s = s+""+(i-1)+" - "+i+" : "+getReportTimer().getAllBetweenError().get(i)/1000+" s\n";
        }

        this.textReport = s;

    }

    //permet d'afficher le report
    public void afficheReport(){

        System.out.println(textReport);
    }
}

