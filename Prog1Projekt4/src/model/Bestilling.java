package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Bestilling {
    private LocalDate dato;
    private Forestilling forestilling;
    private Kunde kunde;
    private ArrayList<Plads> bestiltePladser;

    public Bestilling(LocalDate dato, Kunde kunde, Forestilling forestilling) {
        this.dato = dato;
        this.kunde = kunde;
        this.forestilling = forestilling;
        bestiltePladser = new ArrayList<>();
    }

    public ArrayList<Plads> getBestiltePladser() {
        return bestiltePladser;
    }

    public void setDato (LocalDate dato) {
        this.dato = dato;
    }

    public void addBestiltePladser(Plads plads) {
        if (!bestiltePladser.contains(plads)) {
            bestiltePladser.add(plads);
            plads.addBestillingerAfPlads(this);
        }
    }

    public void setKunde(Kunde kunde) {
        if (this.kunde != kunde) {
            Kunde oldKunde = this.kunde;
            if (oldKunde != null) {
                oldKunde.removeBestilling(this);
            }
            this.kunde = kunde;
            if (kunde != null) {
                kunde.addBestilling(this);
            }
        }
    }



    public LocalDate getDato() {
        return dato;
    }
}
