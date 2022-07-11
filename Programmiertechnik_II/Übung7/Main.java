public class Main {

  public static void main(String[] args) {

    // 1
    // Variablen einer inneren Klasse können nicht in der äußeren Klasse verwendet
    // werden. Innere Klassen können alle Variablen und Funktionen der äußeren
    // Klasse verwendet werden

    // Funktionen einer inneren Klasse können von äußen nicht aufgerufen werden

    // Die Funktion der innneren Klasse kann von der äußeren Klasse aufgerufen
    // werden
    // Huelle h = new Huelle();
    // h.machWasInDerHuelle();
    // h.aussenMethode();

    // Es kann von der äußeren Klasse auch auf private Attribute der inneren Klasse
    // zugegriffen werden

    // 2

    Animation a = new Animation();
    a.startAnimation(2);

  }

}
