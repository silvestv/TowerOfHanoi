package org.sadok.TowerOfHanoi

class ReportTest extends GroovyTestCase {
    Report reportTest;
    void setUp() {
        reportTest = new Report();

        reportTest.menuChoices.fragmentEmotion.selectedR1Emo = "Bouche";
        reportTest.menuChoices.fragmentEmotion.selectedR2Emo = "Moustache";
        reportTest.menuChoices.fragmentEmotion.selectedR3Emo = "Nez";
        reportTest.menuChoices.fragmentEmotion.selectedR4Emo = "Yeux";


        reportTest.nb_ring_choosen = "4";
        reportTest.shape_ring_choosen = "Pyramidale";
        reportTest.feedback_choosen = "Partiel";
        reportTest.dimension_choosen = "3D"
        reportTest.background_choosen = "fond d'écran n°3";
        reportTest.emoVersion = true;
        reportTest.reportTimer.nbAction = 15;



    }

    void testIsPerfectGame() {
        reportTest.createReport();
        if(reportTest.perfect_game != true){
            fail("problème dans le calcul d'une partie parfaite");
        }
    }

    void testCheckedVersion(){
        reportTest.checkVersion();
        if(reportTest.version.equals("Classique")){
            fail("Problème initialisation de la version émotionnel ou non dans le report");
        }
    }

    void testSetNormalEmotionStructureExp(){
        reportTest.setNormalEmotionStructureExp();
        if(!(reportTest.normalEmoStructureExp.equals("Ordre normal visage : Bouche-Moustache-Nez-Yeux"))){
            fail("Non reconnaissance du nombre d'anneau pour exprimé l'ordre normal émotionel");
        }
    }

    void testIsNormalEmotionalStructure(){

        reportTest.isNormalEmotionalStructure();
        if(reportTest.normalEmotionalStructure != true){
            fail("problème de détection d'un visage normal");
        }
    }

    void testCreateReport(){
        reportTest.createReport();
        System.out.println(reportTest.textReport);
        //à vérifier en console
    }



}
