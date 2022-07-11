public class WrapperKlasse2 {

  int i;
  Integer iEingepackt;

  public void machWas() {
    System.out.println(i);
    System.out.println(iEingepackt);
    i = iEingepackt; // does not work because iEingepackt is null
  }

}
