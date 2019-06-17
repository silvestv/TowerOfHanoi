package org.sadok.TowerOfHanoi;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class ReportTest {
    private Timer TimeT;
    private MenuActivity menuT;
    private Context contextT;
    private Report reportT;


    @Before
    public void setUp() throws Exception {
        menuT = new MenuActivity();
        menuT.selectedFeedBackItem = "Totale";
        menuT.selectedItem = "4";
        menuT.selectedShapeItem = "Circulaire";

    }



    @Test
    public void createReport() {
    }

    @Test
    public void afficheReport() {
    }

    @Test
    public void createTextFileReport() {
    }

    @Test
    public void createCSVReport() {
    }

    @Test
    public void dataInCSVFileReport() {
    }
}