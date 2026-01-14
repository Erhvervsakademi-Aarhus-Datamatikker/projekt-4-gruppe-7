package gui;

import controller.Controller;
import javafx.application.Application;
import model.Forestilling;
import model.Kunde;
import model.Plads;
import model.PladsType;
import storage.Storage;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        initStorage();
        //testPrint();
        Application.launch(GUI.class);

    }

    public static void testPrint() {
        for (Plads plads : Storage.getPladser()) {
            System.out.println(plads);
        }

        for (Kunde kunde : Storage.getKunder()) {
            System.out.println(kunde);
        }

        for (Forestilling forestilling : Storage.getForestillinger()) {
            System.out.println(forestilling);
        }

    }

    public static void initStorage() {
        Controller.createForestilling("Evita", LocalDate.of(2023, 8, 10), LocalDate.of(2023, 8, 20));
        Controller.createForestilling("Lykke Per", LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 10));
        Controller.createForestilling("Chess", LocalDate.of(2023, 8, 21), LocalDate.of(2023, 8, 30));

        Controller.createKunde("Anders Hansen", "11223344");
        Controller.createKunde("Peter Jensen", "12345678");
        Controller.createKunde("Niels Madsen", "12341234");
//Plads plads = new Plads(række, nr, pris, pladsType);
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 15; j++) {
                int pris = 0;
                PladsType pladsType = PladsType.STANDARD;
                //De grønne pladser
                if (i <= 2 && j <= 5 || i >= 19 && j <= 5 || i >= 3 && i <= 18 && j >= 6 && j <= 10) {
                    pris = 450;
                    if (j == 10 && i >= 8 && i <= 12)
                        pladsType = PladsType.KØRESTOL;
                }
                //De gulepladser
                if (i >= 3 && i <= 18 && j <= 5) {
                    pris = 500;
                }
                //De blå
                if (i <= 2 && j >= 6 || i >= 19 && j >= 6 || j >= 11) {
                    pris = 400;
                    if (j == 11 && i >= 8 && i <= 12)
                        pladsType = PladsType.EKSTRABEN;
                }

                Controller.createPlads(j, i, pris, pladsType);
            }
        }
    }
}
