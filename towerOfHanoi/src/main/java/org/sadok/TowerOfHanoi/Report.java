package org.sadok.TowerOfHanoi;

import android.content.Context;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Report {
    private Timer reportTimer;
    private TowerOfHanoiActivity menuChoices;

    private int IDReport;
    private static int nb_Reports;
    private String textReport;

    private String nb_ring_choosen;
    private String shape_ring_choosen;
    private String feedback_choosen;
    public String tempsEntreAction = "";
    public String tempsEntreSucces = "";
    public String tempsEntreErreur = "";
    public String tempsEntreSuccesErreur = "";
    public String tempsEntreErreurSucces = "";


    private static Map<Integer, Report> allReports = new HashMap<>();

    public Report(Timer reportTimer, TowerOfHanoiActivity menuChoices){
        //initialise les variables de classe
        this.reportTimer = reportTimer;
        this.menuChoices = menuChoices;
        this.nb_ring_choosen = menuChoices.getSelectedItem();
        this.shape_ring_choosen = menuChoices.getSelectedShapeItem();
        this.feedback_choosen = menuChoices.getSelectedFeedBackItem();

        //créer l'ID du report
        this.IDReport = this.nb_Reports;
        //créer le textReport
        this.createReport();
        //range l'instance courante dans une Map static
        this.allReports.put(this.IDReport, this);
        //incrémente le nombre de rapports
        this.nb_Reports++;


    }

    //Retourne le Timer d'une partie
    public Timer getReportTimer(){
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
        s = s+"FeedBack choisi : "+this.feedback_choosen+"\n";
        s = s+"Forme des palets choisis"+this.shape_ring_choosen+"\n";
        s = s+"Nombre de palets choisis"+this.nb_ring_choosen+"\n";
        s = s+"------------------------------------------------------\n";
        s = s+"PERFORMANCES TEMPS/COUPS\n";
        s = s+"Nombre de coups avant réussite : "+this.reportTimer.getNbClick()+"\n";
        s = s+"Temps total partie : "+this.reportTimer.getTotalTimeGame()+" s\n";
        s = s+"Temps moyen entre 2 actions : "+this.reportTimer.getAverageTimeAction()+" s\n";
        s = s+"Temps moyen entre 2 succès : "+this.reportTimer.getAverageTimeSucess()+" s\n";
        s = s+"Temps moyen entre 2 échecs : "+this.reportTimer.getAverageTimeError()+" s\n";
        s = s+"tems moyen entre succes erreur : \n"+reportTimer.getAverageTimeSucessThenError()+" s\n";
        s = s+"tems moyen entre erreur succes : \n"+this.reportTimer.getAverageTimeErrorThenSucess()+" s\n";

        s = s+"------------------------------------------------------\n";
        s = s+"DETAILS PERFORMANCES TEMPS/COUPS (TABLEAU)\n";
        s = s+"Temps entre chaque action --->  intervalle entre l'action : \n";


        for(int i = 0; i<this.reportTimer.getAllBetweenAction().size(); i++){
                s = s+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenAction().get(i)+" s\n";
        }
        s = s+"Temps entre 2 succès --->  intervalle entre le succès : \n";
        for(int i = 0; i<this.reportTimer.getAllBetweenSucess().size(); i++){
            s = s+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenSucess().get(i)+" s\n";
        }
        s = s+"Temps entre chaque action --->  intervalle entre l'échec : \n";
        for(int i = 0; i<this.reportTimer.getAllBetweenError().size(); i++){
            s = s+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenError().get(i)+" s\n";
        }

        this.textReport = s;

    }

    //permet d'afficher le report
    public void afficheReport(){

        System.out.println(textReport);
    }

    public void addItemToSheet(final Context context) {

        //Temps entre Action
        for(int i = 0; i<this.reportTimer.getAllBetweenAction().size(); i++){
            tempsEntreAction = tempsEntreAction+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenAction().get(i)+" s\n";
        }

        //Temps entre Succes
        for(int i = 0; i<this.reportTimer.getAllBetweenSucess().size(); i++){
            tempsEntreSucces = tempsEntreSucces+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenSucess().get(i)+" s\n";
        }

        //Temps entre Erreur
        for(int i = 0; i<this.reportTimer.getAllBetweenError().size(); i++){
            tempsEntreErreur = tempsEntreErreur+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenError().get(i)+" s\n";
        }

        //Temps entre succes puis erreur
        for(int i = 0; i<this.reportTimer.getAllBetweenSucessThenError().size(); i++){
            tempsEntreSuccesErreur = tempsEntreSuccesErreur+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenSucessThenError().get(i)+" s\n";
        }
        //Temps entre erreur puis succes

        for(int i = 0; i<this.reportTimer.getAllBetweenErrorThenSucess().size(); i++){
            tempsEntreErreurSucces = tempsEntreErreurSucces+""+(i)+" - "+(i+1)+" : "+this.reportTimer.getAllBetweenErrorThenSucess().get(i)+" s\n";
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyr_Fv9QbiCXHeoEEfi9cyI063XH4ohj430joFEZwJVlrhrxyC_/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        final Toast toast = Toast.makeText(context,response,Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parmas = new HashMap<>();
                //here we pass params
                parmas.put("action","addItem");
                parmas.put("feedback",feedback_choosen);
                parmas.put("formePalets",shape_ring_choosen);
                parmas.put("nbPalets",nb_ring_choosen);
                parmas.put("tempsTotal",Long.toString(reportTimer.getTotalTimeGame()));
                parmas.put("tempsMoyenAction",Long.toString(reportTimer.getAverageTimeAction()));
                parmas.put("tempsMoyenSucces",Long.toString(reportTimer.getAverageTimeSucess()));
                parmas.put("tempsMoyenEchecs",Long.toString(reportTimer.getAverageTimeError()));
                parmas.put("tempsEntreAction", tempsEntreAction);
                parmas.put("tempsEntreSucces", tempsEntreSucces);
                parmas.put("tempsEntreErreur", tempsEntreErreur);
                parmas.put("tempsEntreSuccesErreur", tempsEntreSuccesErreur);
                parmas.put("tempsEntreErreurSucces", tempsEntreErreurSucces);
                parmas.put("tempsMoyenEntreSuccesErreur", Long.toString(reportTimer.getAverageTimeSucessThenError()));
                parmas.put("tempsMoyenEntreErreurSucces", Long.toString(reportTimer.getAverageTimeErrorThenSucess()));
                tempsEntreAction = "";
                tempsEntreSucces = "";
                return parmas;
            }
        };

        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(stringRequest);


    }
}

