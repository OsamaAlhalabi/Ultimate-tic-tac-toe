package GameImplementation;

public class Controller {
    public void start(char player , int depth){
        GameGrid game = new GameGrid();
        Helper helper = new Helper();
        game.getDepth(depth);
        game.print();
        while (!game.isFinished()) {
            game.updateGameGrid();
            game.HumanPlayer(player);
            game.updateGameGrid();
            System.out.println("*******************************");
            game.print();
            game.computerPlayer(helper.otherPlayer(player));
            //game.computerPlayer(player);
            game.updateGameGrid();
            System.out.println("*******************************");
            game.print();
        }

    }
}
