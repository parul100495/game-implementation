public class RowGameApp {
    /**
     * Starts a new game in the GUI.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            controller.PickStrategy pickStrategy = new controller.PickStrategy(new controller.RowGameControllerThreeInARow());
            pickStrategy.startUp();
        }
        String gameStrategy = args[0];
        if (args.length == 1) {
            System.out.println("The rows/columns of the grid must be specified.");
            return;
        }
        int rows = Integer.parseInt(args[1]);
        int columns = Integer.parseInt(args[2]);

        if (rows != columns) {
            System.out.println("Grid must be a square.");
            return;
        } else if (rows < 3) {
            System.out.println("The rows/columns of the grid must be at least 3");
            return;
        }
        if (gameStrategy.toLowerCase().equals("tictactoe")) {
            controller.PickStrategy pickStrategy = new controller.PickStrategy(new controller.RowGameControllerTicTacToe(rows, columns));
            pickStrategy.startUp();
        } else if (gameStrategy.toLowerCase().equals("threeinarow")) {
            controller.PickStrategy pickStrategy = new controller.PickStrategy(new controller.RowGameControllerThreeInARow(rows, columns));
            pickStrategy.startUp();
        } else {
            System.out.println("The game startegy must be either threeinarow or tictactoe.");
        }
    }
}
