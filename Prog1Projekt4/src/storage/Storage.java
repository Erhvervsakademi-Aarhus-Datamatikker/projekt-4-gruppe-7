package storage;

import model.Forestilling;
import model.Kunde;
import model.Plads;

import java.util.ArrayList;

public class Storage {
    private static ArrayList<Forestilling> forestillinger = new ArrayList<>();
    private static ArrayList<Kunde> kunder = new ArrayList<>();
    private static ArrayList<Plads> pladser = new ArrayList<>();

    public static void addForestilling(Forestilling forestilling) {
        if (!forestillinger.contains(forestilling))
            forestillinger.add(forestilling);
    }

    public static ArrayList<Forestilling> getForestillinger() {
        return new ArrayList<Forestilling>(forestillinger);
    }

    public static void addKunde(Kunde kunde) {
        if (!kunder.contains(kunde))
            kunder.add(kunde);
    }

    public static ArrayList<Kunde> getKunder() {
        return new ArrayList<Kunde>(kunder);
    }

    public static void addPlads(Plads plads) {
        if (!pladser.contains(plads))
            pladser.add(plads);
    }

    public static ArrayList<Plads> getPladser() {
        return new ArrayList<Plads>(pladser);
    }
}
