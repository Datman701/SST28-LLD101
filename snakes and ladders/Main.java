import factory.GameFactory;
import game.GameEngine;
import model.GameConfig;
import model.Jump;
import model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter board size n (board will be n x n): ");
            int n = scanner.nextInt();

            System.out.print("Enter number of players: ");
            int playerCount = scanner.nextInt();
            scanner.nextLine();

            if (playerCount < 2) {
                System.out.println("At least 2 players are required.");
                return;
            }

            List<Player> players = new ArrayList<>();
            for (int i = 1; i <= playerCount; i++) {
                System.out.print("Enter name for player " + i + ": ");
                String name = scanner.nextLine().trim();
                if (name.isEmpty()) {
                    name = "Player-" + i;
                }
                players.add(new Player("P" + i, name));
            }

            GameConfig config = new GameConfig(n, n, n);
            GameEngine game = new GameFactory().create(config, players);

            System.out.println();
            System.out.println("Board created with max cell: " + config.maxCell());
            printJumps(game.getBoard().getAllJumps());
            System.out.println();

            while (!game.isGameOver()) {
                System.out.println(game.playTurn());
            }

            System.out.println();
            System.out.println("Game ended (less than 2 players still playing).");

            List<Player> winners = game.winnerOrder();
            if (!winners.isEmpty()) {
                System.out.println("Winners in order:");
                for (int i = 0; i < winners.size(); i++) {
                    System.out.println((i + 1) + ". " + winners.get(i).getName());
                }
            } else {
                System.out.println("No winner recorded.");
            }

            List<Player> remaining = game.remainingPlayers();
            if (!remaining.isEmpty()) {
                System.out.println("Still playing at end:");
                for (Player p : remaining) {
                    System.out.println("- " + p.getName() + " at " + p.getPosition());
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to start game: " + e.getMessage());
        }
    }

    private static void printJumps(Map<Integer, Jump> jumps) {
        if (jumps.isEmpty()) {
            System.out.println("No snakes or ladders on the board.");
            return;
        }

        System.out.println("Snakes and Ladders:");
        jumps.entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getKey))
                .forEach(entry -> {
                    Jump jump = entry.getValue();
                    System.out.println("- " + jump.type() + ": " + jump.getStart() + " -> " + jump.getEnd());
                });
    }
}
