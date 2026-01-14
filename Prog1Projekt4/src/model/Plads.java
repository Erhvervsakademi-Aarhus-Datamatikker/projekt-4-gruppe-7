package model;

import java.util.ArrayList;

public class Plads {
    private int række;
    private int nr;
    private int pris;
    private PladsType pladsType;
    private ArrayList<Bestilling> bestillingerAfPlads;

    public Plads (int række, int nr, int pris, PladsType pladsType) {
        this.række = række;
        this.nr = nr;
        this.pris = pris;
        this.pladsType = pladsType;
        bestillingerAfPlads = new ArrayList<>();
    }

    public void addBestillingerAfPlads(Bestilling bestilling) {
        if (!bestillingerAfPlads.contains(bestilling)) {
            bestillingerAfPlads.add(bestilling);
        }
    }

    public int getRække() {
        return række;
    }

    public int getNr() {
        return nr;
    }

    public int getPris() {
        return pris;
    }

    public PladsType getPladsType() {
        return pladsType;
    }

    @Override
    public String toString() {
        return "Række: " + getRække() + " Plads: " + getNr() + " Koster: " + getPris() + " har typen " + getPladsType();
    }
}
