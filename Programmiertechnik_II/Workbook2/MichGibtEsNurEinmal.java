package Programmiertechnik_II.Workbook2;

public class MichGibtEsNurEinmal {

  private static MichGibtEsNurEinmal instance = null;

  public static MichGibtEsNurEinmal getInstance() {
    if (instance == null)
      instance = new MichGibtEsNurEinmal();

    return instance;
  }

}
