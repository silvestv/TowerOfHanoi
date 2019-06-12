package org.sadok.TowerOfHanoi

class TimerTest extends GroovyTestCase {
    private Timer timeT;
    void setUp() {
       timeT = new Timer();
    }

    void tearDown() {
    }

    void testStart() {

    }

    void testPlayerTouchStart() {
    }

    void testStop() {
    }

    void testAddToChronologicActionMap() {
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


        Thread.sleep(300);
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
        if(timeT.allBetweenSucess.size() != 3){
            fail("Erreur mauvaise sauvegarde des succès en vecteur !");
        }

        if(timeT.allBetweenSucess.get(0) != 1000 || timeT.allBetweenSucess.get(1) != 200 || timeT.allBetweenSucess.get(2) != 4000){
            fail("Erreur mauvaise valeurs sauvéesz dans le vecteur !");
        }


    }
    
    }

    void testClickError() {
    }

    void testCalculAverageTimeAction() {
    }

    void testCalculAverageTimeSucess() {
    }

    void testCalculAverageTimeError() {
    }

    void testCalculAverageTimeSucessThenError() {
    }

    void testCalculAverageTimeErrorThenSucess() {
    }
}
