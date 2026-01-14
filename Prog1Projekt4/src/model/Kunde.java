package model;

import java.util.ArrayList;

public class Kunde {
    private String navn;
    private String mobil;
    private ArrayList<Bestilling> bestillinger;

    public Kunde(String navn, String mobil) {
        this.navn = navn;
        this.mobil = mobil;
        bestillinger = new ArrayList<>();
    }

    public void removeBestilling(Bestilling bestilling) {
        if (bestillinger.contains(bestilling)) {
            this.bestillinger.remove(bestilling);
            bestilling.setKunde(null);
        }
    }

    public void addBestilling(Bestilling bestilling) {
        if (!bestillinger.contains(bestilling)) {
            this.bestillinger.add(bestilling);
            bestilling.setKunde(this);
        }
    }

    public String getNavn() {
        return navn;
    }

    public String getMobil() {
        return mobil;
    }

@Override
public String toString() {
        return getNavn() + " har telefonnummeret: " + getMobil();
}
}
