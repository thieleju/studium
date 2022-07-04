package Programmiertechnik_II.Workbook6;

public class Huelle {
  private String textHuellenKlasse = "Außen hui";

  public void machWasInDerHuelle() {
    InnenPfui i = new InnenPfui();
    System.out.println("1." + textHuellenKlasse);
    i.machWasInDerInnerenKlasse();
    System.out.println(i.textInnereKlasse);
  }

  public void aussenMethode() {
    InnenPfui i = new InnenPfui();
    System.out.println("Ich bin eine Hüllenmethode");
    i.innenMethode();
  }

  public class InnenPfui {
    private String textInnereKlasse = "Innen pfui";

    public void machWasInDerInnerenKlasse() {
      System.out.println("2." + textHuellenKlasse);
      System.out.println("3." + textInnereKlasse);
    }

    public void innenMethode() {
      Huelle h = new Huelle();
      h.machWasInDerHuelle();
      System.out.println("Ich bin eine Innenmethode");
    }

  }
}