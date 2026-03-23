package factory;

import dice.Dice;
import dice.SixSidedDice;
import game.GameEngine;
import model.Board;
import model.GameConfig;
import model.Player;

import java.util.List;

public class GameFactory {
    private final BoardFactory boardFactory;

    public GameFactory() {
        this.boardFactory = new BoardFactory();
    }

    public GameEngine create(GameConfig config, List<Player> players) {
        Board board = boardFactory.create(config);
        Dice dice = new SixSidedDice();
        return new GameEngine(board, dice, players);
    }
}
