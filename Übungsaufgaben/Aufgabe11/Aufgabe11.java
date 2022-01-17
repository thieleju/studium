package Übungsaufgaben.Aufgabe11;

public class Aufgabe11 {

  public void print() {

    // Aufgabe 11.1
    // Gegeben sei das untere Programm. Was sind jeweils die Ausgaben? Machen Sie
    // sich eine Skizze
    // A m1 und A m2 und C m3
    // A m2 und B m1 und A m2
    // A m3 und A m3 und B m1
    // B m1 und A m2 und C m3

    // Aufgabe 11.2
    // Gehen Sie die Folien nochmals durch und vollziehen Sie die Beispiele nach.
    // Wenn Sie noch unsicher sind, implementieren Sie die Beispiele nach

    // Aufgabe 11.3
    // Angenommen Sie führen eine Anforderungsanalyse für ein Mietportal für
    // Wohnungen und Häuser durch. Was könnten Eigenschaften der Objekte „Wohnung“
    // und „Haus“, die im Portal verwaltet und angezeigt werden, sein?

    // Mietpreis, Wohnungsgröße, Anzahl Zimmer, Anzahl Badezimmer, Anzahl
    // Schlafzimmer

    // Was könnte in einer gemeinsamen Oberklasse „Immobilie“ definiert werden? Hier
    // geht es nicht um eine syntaktisch korrekte Klassendefinition oder
    // UMLDarstellung. Nennen Sie einfach Eigenschaften und Methoden, die in einer
    // der jeweiligen Klassen Sinn machen.

    // Eigenschaften: Mietpreis, Wohnungsgröße, Anzahl Zimmer, Anzahl Badezimmer,
    // Methoden: berechneMiete()

    // 11.4 a)
    // Erstellen Sie eine Oberklasse Obst und eine Unterklasse Apfel. Definieren Sie
    // in der Klasse Obst einen argumentlosen Konstruktor, der einfach die Zeile
    // „Ich mache ein Obst“ ausgibt. Erstellen Sie eine Klasse ObstKonstruktorTest
    // und erzeugen Sie einen Apfel. Was ist die Ausgabe?
    // -> Ich mache ein Obst
    Apfel a = new Apfel();

    // 11.4 b)
    // Erstellen Sie eine Unterklasse Birne (erbt von Obst) und definieren Sie hier
    // einen argumentlosen Konstruktor, der einfach die Zeile „Ich mache eine Birne“
    // ausgibt. Erzeugen Sie in Ihrer ObstKonstruktorTest-Klasse eine Birne. Was
    // wird ausgegeben?
    // -> Ich mache ein Obst
    // -> Ich mache eine Birne
    Birne b = new Birne();

    // 11.4 c)
    // Versuchen Sie super() nach der Ausgabezeile im Konstruktor Birne aufzurufen?
    // Was passiert? Warum?
    // -> Geht nicht, da super nur am Anfang des Konstruktors stehen darf
    // Wie müssen Sie Ihren Konstruktor ändern?
    // -> Er muss in die erste Zeile des Konstruktors
    // Macht es einen Unterschied ob der super()-Ich mache ein Obst → super() Aufruf
    // aus dem Konstruktor Birne

    // 11.5
    // Gegeben sei das nachfolgende Programm. Bestimmen Sie dessen Ausgaben. In
    // welcher Reihenfolge werden Konstruktoren aufgerufen? Was können Sie über den
    // Konstruktionsprozess ableiten?
    // "Mahlzeit", "Abendessen", "Italiener", "Boden", "Tomatensoße", "Belag",
    // "Pizza"

    // 11.6
    // Das Ausgangsbild mit den Referenzen nach Ausführung der Zeile 35 sieht
    // folgendermaßen aus. Welches Bild ergibt sich, wenn in der Zeile 36 jeweils
    // eine der folgenden Zeilen eingefügt wird:
    // a)

    // 11.7
    // 1. Was passiert in den folgenden Anweisungen?
    // Kunde meinKunde;
    // meinKunde = new Kunde();
    // Es wird ein Kunde Objekt deklariert und danach instanziiert
    // 2. Schreiben Sie Konstruktoren für die Klasse Kunde

    // 3. Erweitern Sie die Klasse Kunde um eine Instanzvariable vorherigerKunde,

    // die eine Referenz auf einen weiteren Kunden speichert.

    // 4. Erweitern Sie die Klasse um eine Instanzvariable kundenNummer, die es
    // ermöglicht, jedem Kunden eine eindeutige, ganzzahlige Nummer zuzuweisen

    // 5. Erweitern Sie die Klasse um eine Klassenvariable folgeNummer, die die
    // nächste zu vergebene Nummer enthält

    // 6. Erweitern Sie Ihre bisherigen Konstruktoren um die
    // a. Vergabe der kundenNummer
    // b. Aktualisierung der folgeNummer

    // 7. Stellen Sie einen weiteren Konstruktor bereit, der es neben dem Namen und
    // dem Alter ermöglicht, einen Vorgänger anzugeben.

    // 8. Erweitern Sie Ihre Klasse Kunde um eine Methode istErster, die true
    // liefert falls das Kundenobjekt keinen Vorgänger in der Warteliste hat.

    // 9. Schreiben Sie getter und setter für Ihre Klasse. Welche setter sollten Sie
    // auf gar keinen Fall schreiben? Warum?
    // -> Man sollte keine Setter für vorherigerKunde, kundenNummer und folgeNummer
    // schreiben

    // 10. Erweitern Sie Ihre Klasse Kunde um eine Methode printKunde, die die
    // wichtigsten Infos zum Kunden auf der Konsole ausgibt.

    // 11. Gegeben sei folgende Methode. Was bewirkt die Methode? Warum? Schauen Sie
    // sich die Beispielaufrufe in den Zeilen 05 – 07 an. Laden Sie die Codezeilen
    // in Ihr Programm. Hat sich Ihre Vermutung bestätigt?
    // Vermutung: Es funktioniert

    Kunde kunde1 = new Kunde("Anton", 54);
    Kunde kunde2 = new Kunde("Berta", 32, kunde1);
    Kunde kunde3 = new Kunde("Cäsar", 19, kunde2);

    System.out.println(kunde1.toString());
    System.out.println(kunde2.toString());
    System.out.println(kunde3.toString());

    // Vermutung hat sich bestätigt, war aber nicht, was gefragt war
    // -> Es wird automatisch .toString() von vorherigerKunde aufgerufen, da
    // .toString() standardmäßig bei Objekten aufgerufen wird, wenn sie in einen
    // String geschrieben werden

    // 12. Erläutern Sie den Aufbau ihrer Daten aus Zeile 01 – 03 grafisch analog
    // zur Aufgabe 3

  }
}
