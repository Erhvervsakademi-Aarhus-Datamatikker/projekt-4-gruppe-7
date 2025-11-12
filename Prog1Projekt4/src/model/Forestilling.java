package model;

import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

public class Forestilling {
    private String navn;
    private LocalDate startDato;
    private LocalDate slutDato;
    private ArrayList<Bestilling> bestillinger;

    public Forestilling(String navn, LocalDate startDato, LocalDate slutDato) {
        this.navn = navn;
        this.startDato = startDato;
        this.slutDato = slutDato;
        bestillinger = new ArrayList<>();
    }

    public Bestilling createBestilling(LocalDate dato, Kunde kunde, Forestilling forestilling) {
        Bestilling bestilling = new Bestilling(dato, kunde, this);
        bestillinger.add(bestilling);
        return bestilling;
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public boolean erPladsLedig(int række, int nr, LocalDate dato) {
        for (Bestilling bestilling : bestillinger) {
            for (Plads plads : bestilling.getBestiltePladser()) {
                if (plads.getRække() != række || plads.getNr() != nr || bestilling.getDato() != dato) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return getNavn() + " spiller fra: " + getStartDato() + " til " + getSlutDato();
    }
}
