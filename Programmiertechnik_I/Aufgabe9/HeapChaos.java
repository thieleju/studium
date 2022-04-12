package Programmiertechnik_I.Aufgabe9;

public class HeapChaos {
  int id = 0;

  public static void main(String[] args) {
    HeapChaos[] hc = new HeapChaos[5];
    for (int i = 0; i < 3; i++) {
      hc[i] = new HeapChaos();
      hc[i].id = i;
    }

    hc[3] = hc[1];
    hc[4] = hc[1];
    hc[3] = null;
    hc[4] = hc[0];
    hc[0] = hc[3];
    hc[3] = hc[2];

    // hc[0] -> null
    // hc[1] -> 1
    // hc[2] -> 2
    // hc[3] -> 2
    // hc[4] -> 0
  }
}
