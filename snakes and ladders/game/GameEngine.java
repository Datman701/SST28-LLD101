package game;

import dice.Dice;
import model.Board;
import model.Jump;
import model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameEngine {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> activePlayers;
    private final List<Player> winners;

    public GameEngine(Board board, Dice dice, List<Player> players) {
        if (players == null || players.size() < 2) {
            throw new IllegalArgumentException("At least 2 players are required.");
        }
        this.board = board;
        this.dice = dice;
        this.activePlayers = new LinkedList<>(players);
        this.winners = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public boolean isGameOver() {
        return activePlayers.size() < 2;
    }

    public List<Player> winnerOrder() {
        return Collections.unmodifiableList(winners);
    }

    public List<Player> remainingPlayers() {
        return new ArrayList<>(activePlayers);
    }

    public String playTurn() {
        if (isGameOver()) {
            return "Game is already over.";
        }

        Player current = activePlayers.poll();
        int oldPosition = current.getPosition();
        int roll = dice.roll();
        int maxCell = board.getMaxCell();

        int tentative = oldPosition + roll;
        int finalPosition = oldPosition;
        StringBuilder message = new StringBuilder();

        message.append(current.getName())
                .append(" rolled ")
                .append(roll)
                .append(" from ")
                .append(oldPosition);

        if (tentative > maxCell) {
            message.append(" and stays at ").append(oldPosition).append(" (overshoot). ");
        } else {
            finalPosition = board.resolvePosition(tentative);
            current.moveTo(finalPosition);
            message.append(" and moved to ").append(tentative);

            if (finalPosition != tentative) {
                Jump jump = board.getJumpAt(tentative);
                if (jump != null) {
                    message.append(" then took a ")
                            .append(jump.type().toLowerCase())
                            .append(" to ")
                            .append(finalPosition);
                }
            }
            message.append(". ");
        }

        if (current.getPosition() == maxCell) {
            current.markFinished();
            winners.add(current);
            message.append(current.getName()).append(" reached ").append(maxCell).append(" and won.");
        } else {
            activePlayers.offer(current);
        }

        return message.toString();
    }
}
