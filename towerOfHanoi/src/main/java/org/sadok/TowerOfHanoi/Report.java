package org.sadok.TowerOfHanoi;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;*/

public class Report {
    private Timer reportTimer;
    private TowerOfHanoiActivity menuChoices;

    private int IDReport;
    private static int nb_Reports;
    private String textReport;

    private boolean perfect_game = false;

    private String nb_ring_choosen;
    private String shape_ring_choosen;
    private String feedback_choosen;
    private String tempsEntreAction = "";
    private String tempsEntreSucces = "";
    private String tempsEntreErreur = "";
    private String tempsEntreSuccesErreur = "";
    private String tempsEntreErreurSucces = "";


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
        //créer le textReport et initialise les attributs
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

    public boolean isPerfectGame(){
        return this.perfect_game;
    }

    //première version console simple du report/créé le rapport utiliser dans le constructeur
    public void createReport(){
        int nbCoupMini = 0;
        String s = "";
        s = s+"INFORMATIONS GENERALES\n";
        s = s+"Numéro de report : "+this.getIDReport()+"\n";
        s = s+"Nom joueur : Joueur "+this.getIDReport()+"\n";
        s = s+"Prénom joueur : "+"\n";
        s = s+"------------------------------------------------------\n";
        s = s+"OPTIONS CHOISIES\n";
        s = s+"FeedBack choisi : "+this.feedback_choosen+"\n";
        s = s+"Forme des palets choisis : "+this.shape_ring_choosen+"\n";
        s = s+"Nombre de palets choisis : "+this.nb_ring_choosen+"\n";
        s = s+"------------------------------------------------------\n";
        s = s+"VUE D'ENSEMBLE DE LA PARTIE\n";
        for(int i : this.reportTimer.getChronologicActionMap().keySet()){
            s = s+i+" -> "+reportTimer.getChronologicActionMap().get(i)+" || ";
        }
        s = s+"\n";
        s = s+"------------------------------------------------------\n";
        s = s+"PERFORMANCES TEMPS/COUPS\n";
        s = s+"Nombre de coups minimum envisageable pour cette partie : ";
        if(this.nb_ring_choosen.equals("3")){
            nbCoupMini = 7;
        } else if(this.nb_ring_choosen.equals("4")){
            nbCoupMini = 15;
        } else if(this.nb_ring_choosen.equals("5")){
            nbCoupMini = 31;
        } else if (this.nb_ring_choosen.equals("6")){
            nbCoupMini = 63;
        } else {
            System.out.println(this.nb_ring_choosen);
        }
        s = s+nbCoupMini+"\n";
        s = s+"Nombre de coups avant réussite : "+this.reportTimer.getNbAction()+"\n";
        s = s+"Nombre de succès : "+this.reportTimer.getNbSucess()+"\n";
        s = s+"Nombre d'echec : "+this.reportTimer.getNbError()+"\n\n";
        s = s+"Rapport de performance (Nombre de coup réel / Nombre de coup minimum) : "+this.reportTimer.getNbAction()+"/"+nbCoupMini+"\n";
        if(this.reportTimer.getNbAction()/nbCoupMini == 1){
            this.perfect_game = true;
        }
        s = s+"Temps total partie (systeme) : "+this.reportTimer.getTotalTimeGame()+" ms\n";
        s = s+"Temps total partie depuis le premier touché (joueur) : "+this.reportTimer.getTotalTimeGameSinceFirstTouch()+" ms\n";
        s = s+"Temps de réflexion initiale du joueur avant le premier touché : "+this.reportTimer.getInitialPlayerThinkingTime()+" ms\n";
        s = s+"Temps moyen entre 2 actions : "+this.reportTimer.getAverageTimeAction()+" ms\n";
        s = s+"Temps moyen entre 2 succès : "+this.reportTimer.getAverageTimeSucess()+" ms\n";
        s = s+"Temps moyen entre 2 échecs : "+this.reportTimer.getAverageTimeError()+" ms\n";
        s = s+"Temps moyen entre succès puis échec : "+this.reportTimer.getAverageTimeSucessThenError()+" ms\n";
        s = s+"Temps moyen entre échec puis succès : "+this.reportTimer.getAverageTimeErrorThenSucess()+" ms\n";
        s = s+"------------------------------------------------------\n";
        s = s+"DETAILS PERFORMANCES TEMPS/COUPS (TABLEAU)\n";
        s = s+"Temps entre chaque action --->  intervalle entre l'action : \n";


        for(int i = 0; i<this.reportTimer.getAllBetweenAction().size(); i++){
                s = s+""+(i)+" - "+(i+1)+" : "+(this.reportTimer.getAllBetweenAction().get(i)/1000)+" s\n";
        }

        s = s+"Temps entre 2 succès --->  intervalle entre le succès : \n";
        for(int i = 0; i<this.reportTimer.getAllBetweenSucess().size(); i++){
            s = s+""+(i+1)+" - "+(i+2)+" : "+(this.reportTimer.getAllBetweenSucess().get(i)/1000)+" s\n";
        }

        s = s+"Temps entre 2 échecs --->  intervalle entre l'échec : \n";
        for(int i = 0; i<this.reportTimer.getAllBetweenError().size(); i++){
            s = s+""+(i+1)+" - "+(i+2)+" : "+(this.reportTimer.getAllBetweenError().get(i)/1000)+" s\n";
        }

        s = s+"Temps entre les succès puis échecs --->  intervalle entre le succès-échec : \n";
        for(int i = 0; i<this.reportTimer.getAllBetweenSucessThenError().size(); i++){
            s = s+""+(i+1)+" - "+(i+2)+" : "+(this.reportTimer.getAllBetweenSucessThenError().get(i)/1000)+" s\n";
        }

        s = s+"Temps entre les échecs puis succès --->  intervalle entre l'échec-succès : \n";
        for(int i = 0; i<this.reportTimer.getAllBetweenErrorThenSucess().size(); i++){
            s = s+""+(i+1)+" - "+(i+2)+" : "+(this.reportTimer.getAllBetweenErrorThenSucess().get(i)/1000)+" s\n";
        }

        s = s+"-------------------------------------------------------------------------\n";
        if(this.perfect_game){
            s = s+"///////////////////////////JEU PARFAIT!////////////////////////////// \n";
        } else {
            s = s+"/////////////////////////PEUT MIEUX FAIRE !///////////////////////// \n";
        }
        this.textReport = s;

    }

    //permet d'afficher le report
    public void afficheReport(){

        System.out.println(textReport);
    }

    public void createTextFileReport(Context context){
       try {
            File path = context.getExternalFilesDir(Environment.DIRECTORY_DCIM);
            System.out.println("LAAAAA  :  "+path);
            File reportTextFile = new File(path, "reportTest.txt");
            FileWriter filewriter = new FileWriter(reportTextFile,true);
            filewriter.write("coucou les gars !");
            filewriter.close();
            System.out.println("fichier ok créer");
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


   /* public void addItemToSheet(final Context context) {

        //Temps entre Action
        for(int i = 0; i<this.reportTimer.getAllBetweenAction().size(); i++){
            tempsEntreAction = tempsEntreAction+""+(i)+" - "+(i+1)+" : "+(this.reportTimer.getAllBetweenAction().get(i)/1000)+" s\n";
        }

        //Temps entre Succes
        for(int i = 0; i<this.reportTimer.getAllBetweenSucess().size(); i++){
            tempsEntreSucces = tempsEntreSucces+""+(i)+" - "+(i+1)+" : "+(this.reportTimer.getAllBetweenSucess().get(i)/1000)+" s\n";
        }

        //Temps entre Erreur
        for(int i = 0; i<this.reportTimer.getAllBetweenError().size(); i++){
            tempsEntreErreur = tempsEntreErreur+""+(i)+" - "+(i+1)+" : "+(this.reportTimer.getAllBetweenError().get(i)/1000)+" s\n";
        }

        //Temps entre succes puis erreur
        for(int i = 0; i<this.reportTimer.getAllBetweenSucessThenError().size(); i++){
            tempsEntreSuccesErreur = tempsEntreSuccesErreur+""+(i)+" - "+(i+1)+" : "+(this.reportTimer.getAllBetweenSucessThenError().get(i)/1000)+" s\n";
        }
        //Temps entre erreur puis succes

        for(int i = 0; i<this.reportTimer.getAllBetweenErrorThenSucess().size(); i++){
            tempsEntreErreurSucces = tempsEntreErreurSucces+""+(i)+" - "+(i+1)+" : "+(this.reportTimer.getAllBetweenErrorThenSucess().get(i)/1000)+" s\n";
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
                parmas.put("tempsTotal",Double.toString(reportTimer.getTotalTimeGame()/1000));
                parmas.put("tempsMoyenAction",Double.toString(reportTimer.getAverageTimeAction()/1000));
                parmas.put("tempsMoyenSucces",Double.toString(reportTimer.getAverageTimeSucess()/1000));
                parmas.put("tempsMoyenEchecs",Double.toString(reportTimer.getAverageTimeError()/1000));
                parmas.put("tempsEntreAction", tempsEntreAction);
                parmas.put("tempsEntreSucces", tempsEntreSucces);
                parmas.put("tempsEntreErreur", tempsEntreErreur);
                parmas.put("tempsEntreSuccesErreur", tempsEntreSuccesErreur);
                parmas.put("tempsEntreErreurSucces", tempsEntreErreurSucces);
                parmas.put("tempsMoyenEntreSuccesErreur", Double.toString(reportTimer.getAverageTimeSucessThenError()/1000));
                parmas.put("tempsMoyenEntreErreurSucces", Double.toString(reportTimer.getAverageTimeErrorThenSucess()/1000));

                return parmas;
            }
        };
        int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(context);

        queue.add(stringRequest);


    }*/
}

