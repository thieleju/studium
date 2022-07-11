import java.util.Arrays;

import A1.*;
import A5.*;
import A6.*;
import A8.*;

public class Main {

  public static void main(String[] args) {

    Hummel h = new Hummel();
    System.out.println(h.kannFliegen());

    // Konkrete Klasse | Abstrakte Klasse | Finale Klasse | Schnittstelle
    // -----------------------------------
    // Konkrete Methode | ja ja ja nein
    // Abstrakte Methode | nein ja nein ja
    // Statische Methode | ja ja ja ja
    // Default Methode | nein nein nein ja

    // 4a Finale Methoden können nicht überschrieben werden
    // 4b Finale Konstanten können nicht verändert werden
    // 4c Finale Objekte können nicht geerbt werden

    // 5 Die Methode aus der abstraken Klasse wird verwendet
    Buch b = new Buch(12.99, 0.3);
    System.out.println(b.getLieferstatus());

    // 6
    int[] intArray = { 13, 35, 13, 0, 12, -9 };
    Arrays.sort(intArray);
    System.out.println(Arrays.toString(intArray));

    // 6b Die Funktion compareTo wird aus der Schnittstelle Comparable implementiert
    // und überschrieben

    // 6c Hier werden die Monster objekte sortiert

    // 6d
    Paket[] pakete = {
        new Paket("Absender 1", 23.0, 1.0),
        new Paket("Absender 2", 4.0, 2.0),
        new Paket("Absender 3", 3.0, 3.0),
        new Paket("Absender 4", 9.0, 3.0),
        new Paket("Absender 5", 104.0, 3.0)
    };
    Arrays.sort(pakete);
    System.out.println(Arrays.toString(pakete));

    // 6e
    Rezept[] rezepte = {
        new Rezept("C Rezept"),
        new Rezept("A Rezept"),
        new Rezept("D Rezept"),
        new Rezept("B Rezept"),
        new Rezept("Z Rezept"),
        new Rezept("Y Rezept")
    };
    Arrays.sort(rezepte);
    System.out.println(Arrays.toString(rezepte));

    // 7
    KuchenKitchenAid k = new KuchenKitchenAid();
    k.kuchenBacken();
    KuchenThermoMix k2 = new KuchenThermoMix();
    k2.kuchenBacken();
  }
}
