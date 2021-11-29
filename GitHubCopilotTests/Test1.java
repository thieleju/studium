package GitHubCopilotTests;

public class Test1 {

  // initialize a tic tac toe board which is a 3x3 array of strings
  public static String[][] board = new String[3][3];

  // randomize the input of board
  public static String[][] getRandomizedBoard() {
    // initialize a random number generator
    java.util.Random rand = new java.util.Random();
    // initialize a new board
    String[][] newBoard = new String[3][3];
    // loop through the board
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        // if the current cell is empty
        if (board[i][j] == null) {
          // assign a random number to the current cell
          newBoard[i][j] = rand.nextInt(2) == 0 ? "X" : "O";
        } else {
          // otherwise, assign the current cell to the current value of the board
          newBoard[i][j] = board[i][j];
        }
      }
    }
    // return the new board
    return newBoard;
  }

  // create constructor to this class with a parameter of a board
  public Test1() {
    // initialize the board with getRandomizedBoard
    board = getRandomizedBoard();
    // draw board
    drawBoard(board);
  }

  // initialize a function which adds multiple numbers from an array and
  // returns the sum
  public static int add(int[] numbers) {
    int sum = 0;
    for (int i = 0; i < numbers.length; i++) {
      sum += numbers[i];
    }
    return sum;
  }

  // initialize a function that draws tic tac toe board
  public static void drawBoard(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }
}
