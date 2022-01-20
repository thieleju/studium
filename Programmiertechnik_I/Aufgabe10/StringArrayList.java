package Aufgabe10;

public class StringArrayList {

  private String[] array;
  private int size = 10;

  // Erzeugt ein String-Array mit der Standardkapazität 10
  public StringArrayList() {
    this.size = 10;
    this.array = new String[size];
  }

  // Erzeugt ein String-Array mit der angegebenen „Kapazität“
  public StringArrayList(int capacity) {
    this.size = capacity;
    this.array = new String[capacity];
  }

  // Gibt die Anzahl der Elemente im Array zurück
  public int getSize() {
    return this.size;
  }

  // Gibt true zurück wenn das Array leer ist, false sonst
  public boolean isEmpty() {
    if (this.size == 0)
      return true;
    else
      return false;
  }

  // Gibt true zurück wenn der übergebene String s im String-Array enthalten ist,
  // false sonst
  public boolean contains(String s) {
    for (int i = 0; i < this.size; i++) {
      if (this.array[i].equals(s))
        return true;
    }
    return false;
  }

  // Fügt den übergebenen String zum String-Array hinzu
  // Ist das Array „voll“, wird die Größe angepasst
  public void add(String aktuellerString) {

    // finde den index der letzten freien Stelle heraus
    int indexLetzteFreieStelle = -1;
    for (int i = 0; i < this.size; i++) {
      if (this.array[i] == null) {
        indexLetzteFreieStelle = i;
        break;
      }
    }
    // prüfe ob voll bzw ob keine letzte Stelle gefunden wurde
    if (indexLetzteFreieStelle == -1) {
      // Größe erweitern und neues Array erzeugen
      this.size += 1;
      String[] neuesArray = new String[this.size];
      // kopieren aller alter Werte in das neue Array
      for (int i = 0; i < this.array.length; i++) {
        neuesArray[i] = this.array[i];
      }
      // neues Array zuweisen
      this.array = neuesArray;
      // neue letzte Stelle
      indexLetzteFreieStelle = this.size - 1;
    }

    // schreibe element an letzte stelle
    this.array[indexLetzteFreieStelle] = aktuellerString;
  }

  // Gibt den String an der Position index zurück
  public String get(int index) {
    return this.array[index];
  }

  // Löscht den String an der Position index
  public void remove(int index) {
    for (int i = index; i < this.size - 1; i++) {
      this.array[i] = this.array[i + 1];
    }
    this.size--;
  }

}
