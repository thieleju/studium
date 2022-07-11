
public class InvalidDogAgeException extends RuntimeException {

  public InvalidDogAgeException() {
    super();
  }

  public InvalidDogAgeException(int alter) {
    super("Invalid dog age: " + alter);
  }

}
