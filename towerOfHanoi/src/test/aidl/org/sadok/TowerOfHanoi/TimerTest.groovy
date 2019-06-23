import static org.junit.Assert.fail
package org.sadok.TowerOfHanoi
class TimerTest extends GroovyTestCase {
    private Timer timeT;
    void setUp() {
        timeT = new Timer();
    }

    void tearDown() {
    }

    void testClickAction() {
        timeT.t_second_game_start = System.currentTimeMillis();

        Thread.sleep(1000);
        timeT.clickAction();
        if(timeT.t_second_between_action != 1000){
            fail("Mauvaise initialisation du temps entre action !");
        }
        if(timeT.allBetweenAction.size() != 1){
            fail("Erreur mauvaise sauvegarde de l'action en vecteur !");
        }


        Thread.sleep(200);
        timeT.clickAction();
        if(timeT.t_second_between_action != 200){
            fail("Mauvaise initialisation du temps entre action !");
        }
        if(timeT.allBetweenAction.size() != 2){
            fail("Erreur mauvaise sauvegarde de l'action en vecteur !");
        }


        Thread.sleep(4000);
        timeT.clickAction();
        if(timeT.t_second_between_action != 4000){
            fail("Mauvaise initialisation du temps entre action !");
        }
        if(timeT.allBetweenAction.size() != 3){
            fail("Erreur mauvaise sauvegarde de l'action en vecteur !");
        }

        if(timeT.allBetweenAction.get(0) != 1000 || timeT.allBetweenAction.get(1) != 200 || timeT.allBetweenAction.get(2) != 4000){
            fail("Erreur mauvaise valeurs sauvéesz dans le vecteur !");
        }


    }

    void testClickSuccess() {

        timeT.t_second_game_start = System.currentTimeMillis();

        Thread.sleep(2000);
        timeT.clickSuccess();
        if(timeT.t_second_between_success != 2000){
            fail("Mauvaise initialisation du temps entre succès !");
        }
        if(timeT.allBetweenSucess.size() != 0){
            fail("Erreur mauvaise sauvegarde des succès en vecteur !");
        }


        Thread.sleep(200);
        timeT.clickSuccess();
        if(timeT.t_second_between_success != 200){
            fail("Mauvaise initialisation du temps entre succès !");
        }
        if(timeT.allBetweenSucess.size() != 1){
            fail("Erreur mauvaise sauvegarde des succès en vecteur !");
        }


        Thread.sleep(4000);
        timeT.clickSuccess();
        if(timeT.t_second_between_success != 4000){
            fail("Mauvaise initialisation du temps entre succès !");
        }
        if(timeT.allBetweenSucess.size() != 2){
            fail("Erreur mauvaise sauvegarde des succès en vecteur !");
        }

        if(timeT.allBetweenSucess.get(0) != 200 || timeT.allBetweenSucess.get(1) != 4000){
            fail("Erreur mauvaise valeurs sauvéesz dans le vecteur !");
        }


    }



    void testClickError() {

        timeT.t_second_game_start = System.currentTimeMillis();

        Thread.sleep(1500);
        timeT.clickError();
        if(timeT.t_second_between_error != 1500){
            fail("Mauvaise initialisation du temps entre erreurs !");
        }
        if(timeT.allBetweenError.size() != 0){
            fail("Erreur mauvaise sauvegarde des erreurs en vecteur !");
        }


        Thread.sleep(300);
        timeT.clickError();
        if(timeT.t_second_between_error != 200){
            fail("Mauvaise initialisation du temps entre succès !");
        }
        if(timeT.allBetweenSucess.size() != 1){
            fail("Erreur mauvaise sauvegarde des succès en vecteur !");
        }


        Thread.sleep(4000);
        timeT.clickError();
        if(timeT.t_second_between_error != 4000){
            fail("Mauvaise initialisation du temps entre succès !");
        }
        if(timeT.allBetweenError.size() != 2){
            fail("Erreur mauvaise sauvegarde des succès en vecteur !");
        }

        if(timeT.allBetweenError.get(0) != 200 || timeT.allBetweenError.get(1) != 4000){
            fail("Erreur mauvaise valeurs sauvéesz dans le vecteur !");
        }
    }

    void testCalculAverageTimeAction() {

        timeT.allBetweenAction.removeAll();
        timeT.allBetweenAction.add(6000);
        timeT.allBetweenAction.add(1000);
        timeT.allBetweenAction.add(2000);
        timeT.allBetweenAction.add(0);
        timeT.calculAverageTimeAction();

        if(timeT.t_average_second_action != 3000){
            fail("Erreur calcul du temps moyen action : vecteur non vide")
        }


        timeT.allBetweenAction.removeAll();
        timeT.calculAverageTimeAction();

        if(timeT.t_average_second_action != 0 && timeT.t_average_second_action != null){
            fail("Erreur calcul du temps moyen action : vecteur vide")
        }


    }

    void testCalculAverageTimeSucess() {
        timeT.allBetweenSucess.removeAll();
        timeT.allBetweenSucess.add(6000);
        timeT.allBetweenSucess.add(1000);
        timeT.allBetweenSucess.add(2000);
        timeT.allBetweenSucess.add(0);
        timeT.calculAverageTimeSucess();

        if(timeT.t_average_second_sucess != 3000){
            fail("Erreur calcul du temps moyen succes : vecteur non vide")
        }


        timeT.allBetweenSucess.removeAll();
        timeT.calculAverageTimeSucess();

        if(timeT.t_average_second_sucess != 0 && timeT.t_average_second_success != null){
            fail("Erreur calcul du temps moyen succes : vecteur vide")
        }
    }

    void testCalculAverageTimeError() {

        timeT.allBetweenError.removeAll();
        timeT.allBetweenError.add(6000);
        timeT.allBetweenError.add(1000);
        timeT.allBetweenError.add(2000);
        timeT.allBetweenError.add(0);
        timeT.calculAverageTimeError();

        if(timeT.t_average_second_error != 3000){
            fail("Erreur calcul du temps moyen erreur : vecteur non vide")
        }


        timeT.allBetweenError.removeAll();
        timeT.calculAverageTimeError();

        if(timeT.t_average_second_error != 0 && timeT.t_average_second_error != null){
            fail("Erreur calcul du temps moyen erreur : vecteur vide")
        }

    }

    void testCalculAverageTimeSucessThenError() {

        timeT.allBetweenSucessThenError.removeAll();
        timeT.allBetweenSucessThenError.add(6000);
        timeT.allBetweenSucessThenError.add(1000);
        timeT.allBetweenSucessThenError.add(2000);
        timeT.allBetweenSucessThenError.add(0);
        timeT.calculAverageTimeSucessThenError();

        if(timeT.t_average_second_action != 3000){
            fail("Erreur calcul du temps moyen succes echec : vecteur non vide")
        }


        timeT.allBetweenSucessThenError.removeAll();
        timeT.calculAverageTimeSucessThenError();

        if(timeT.t_average_second_sucess_then_error != 0 && timeT.t_average_second_sucess_then_error != null){
            fail("Erreur calcul du temps moyen succes echec : vecteur vide")
        }

    }

    void testCalculAverageTimeErrorThenSucess() {

        timeT.allBetweenErrorThenSucess.removeAll();
        timeT.allBetweenErrorThenSucess.add(6000);
        timeT.allBetweenErrorThenSucess.add(1000);
        timeT.allBetweenErrorThenSucess.add(2000);
        timeT.allBetweenErrorThenSucess.add(0);
        timeT.calculAverageTimeErrorThenSucess();

        if(timeT.t_average_second_error_then_sucess != 3000){
            fail("Erreur calcul du temps moyen erreur puis succès : vecteur non vide")
        }


        timeT.allBetweenErrorThenSucess.removeAll();
        timeT.calculAverageTimeErrorThenSucess();

        if(timeT.t_average_second_error_then_sucess != 0 && timeT.t_average_second_error_then_sucess != null){
            fail("Erreur calcul du temps moyen erreur puis succès : vecteur vide")
        }

    }
}
