package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size;

    public Game(int size) {
        this.size = size;
        initialize();
        play();
    }

    public void initialize() {
        grid = new Grid(size);
        player = new Player(0, 0);
        trophy = new Trophy(9, 9); // Place trophy in other cornre

        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(1, 7);
        enemies = new Enemy[2];
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7, 8);


        for (Treasure t : treasures) {
            grid.placeSprite(t);
        }
        for (Enemy e : enemies) {
            grid.placeSprite(e);
        }
        grid.placeSprite(player);
        grid.placeSprite(trophy);
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // clearScreen();
            grid.display();

            System.out.print("Move (w/a/s/d): ");
            String move = scanner.nextLine().toLowerCase();

            if (!player.isValid(size, move)) {
                System.out.println("Invalid move!");
                continue;
            }

            grid.placeSprite(player, move);

            for (Treasure t : treasures) {
                if (player.getX() == t.getX() && player.getY() == t.getY()) {
                    player.interact(size, move, treasures.length, t);
                }
            }

            for (Enemy e : enemies) {
                if (player.getX() == e.getX() && player.getY() == e.getY()) {
                    player.interact(size, move, treasures.length, e);
                }
            }

            if (player.getX() == trophy.getX() && player.getY() == trophy.getY()) {
                player.interact(size, move, treasures.length, trophy);
            }
            
            if (player.getWin()) {
                grid.win();
                break;
            } else if (player.getLives() <= 0) {
                grid.gameover();
                break;
            }
        }
        scanner.close();
        
    }

    public static void clearScreen() {
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Game(10);
    }
}
