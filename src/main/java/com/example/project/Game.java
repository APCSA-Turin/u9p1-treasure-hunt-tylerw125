package com.example.project;

import java.util.Scanner;

public class Game {
    private Grid grid;        // Grid sytstem
    private Player player;     // Player
    private Enemy[] enemies;   // List of enemies
    private Treasure[] treasures; // List of treasures to be collected before trophy
    private Trophy trophy;     // Trophy to be colelcted at end of game
    private int size;          // Size of grid 

    public Game(int size) {
        this.size = size;  // Sets grid size
        initialize();      // Initialize other game objects
        play();            // Starting loop
    }

    // Creates grid player enemies etc
    public void initialize() {
        grid = new Grid(size);      // Create specified grid size
        player = new Player(0, 0);  // Initialize the player at bottom left
        trophy = new Trophy(9, 9);  // Place the trophy in the opposite of playre

        // Initialize treasures and enemies at specific location
        treasures = new Treasure[2];
        treasures[0] = new Treasure(2, 2);
        treasures[1] = new Treasure(1, 7);
        enemies = new Enemy[2];
        enemies[0] = new Enemy(5, 5);
        enemies[1] = new Enemy(7, 8);

        // Place all objects on grid
        for (Treasure t : treasures) {
            grid.placeSprite(t);
        }
        for (Enemy e : enemies) {
            grid.placeSprite(e);
        }
        grid.placeSprite(player);
        grid.placeSprite(trophy);
    }

    // Starts game loop and then takes input
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            grid.display(); // DIsplay current grid state
            System.out.print("Move (w/a/s/d): ");
            String move = scanner.nextLine().toLowerCase(); // read consistent lowercase input

            // Edge handling
            if (!player.isValid(size, move)) {
                System.out.println("Invalid move!");
                continue;
            }

            // Move, then interact with the various objects
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
            
            // Win check
            if (player.getWin()) {
                grid.win();
                break;
            } else if (player.getLives() <= 0) {
                grid.gameover();
                break;
            }
        }
        scanner.close(); // Stop resource leak
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
        new Game(10); // Start a new game with a 10x10 grid
    }
}
