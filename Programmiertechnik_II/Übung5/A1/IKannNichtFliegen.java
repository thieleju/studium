package A1;

public interface IKannNichtFliegen extends IKannFliegen {

  default boolean kannFliegen() {
    return false;
  }

}
