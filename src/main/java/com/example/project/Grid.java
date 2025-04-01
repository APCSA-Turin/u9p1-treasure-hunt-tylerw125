package com.example.project;

public class Grid {
    private Sprite[][] grid;  // 2D array representing the game grid
    private int size;         // Size of grid

    // Constructor to initialize the grid and place Dot objects on it
    public Grid(int size) {
        this.size = size;
        this.grid = new Sprite[size][size];

        // Populate the grid with Dot objects
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Dot dot = new Dot(j, i);
                grid[i][j] = dot;
            }
        }
    }

    public Sprite[][] getGrid() {
        return grid; // Return the grid array
    }

    // Places a sprite at its current location
    public void placeSprite(Sprite s) {
        int y = s.getY();
        int x = s.getX();
        
        // Bounds check
        if (x >= 0 && x < size && y >= 0 && y < size) {
            grid[size - 1 - y][x] = s; // Place the sprite in the grid
        }
    }

    // Moves a sprite in the specified direction and places it on the grid
    public void placeSprite(Sprite s, String direction) {
        int newX = s.getX();
        int newY = s.getY();
        
        // SPrite position updating based on input
        if (direction.equals("a") && newX + 1 < size) {
            placeSprite(new Dot(newX + 1, newY));
        } else if (direction.equals("d") && newX - 1 >= 0) {
            placeSprite(new Dot(newX - 1, newY));
        } else if (direction.equals("s") && newY + 1 < size) {
            placeSprite(new Dot(newX, newY + 1));
        } else if (direction.equals("w") && newY - 1 >= 0) {
            placeSprite(new Dot(newX, newY - 1));
        }
        
        // Bounds check
        if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
            placeSprite(s);
        }
    }

    // Display right grid for the game
    public void display() {
        for (Sprite[] row : grid) {
            for (Sprite col : row) {
                // Print the correct/appropriate smybols for sprites
                if (col instanceof Trophy) {
                    System.out.print("🌟");
                } else if (col instanceof Player) {
                    System.out.print("🕺");
                } else if (col instanceof Enemy) {
                    System.out.print("🐢");
                } else if (col instanceof Treasure) {
                    System.out.print("🍄");
                } else if (col instanceof Dot) {
                    System.out.print("🟥");
                }
            }
            System.out.println(); // New line after each row
        }
    }

    // Display winning grid pattern
    public void winDisplay() {
        for (Sprite[] row : grid) {
            for (Sprite col : row) {
                if (col instanceof Player) {
                    System.out.print("🕺");
                } else {
                    System.out.print("👑");
                }
            }
            System.out.println();
        }
    }

    // Display losing grid pattern
    public void loseDisplay() {
        for (Sprite[] row : grid) {
            for (Sprite col : row) {
                if (col instanceof Player) {
                    System.out.print("🕺");
                } else {
                    System.out.print("👾");
                }
            }
            System.out.println();
        }
    }

    // Display gameover message only if player loses
    public void gameover() {
        System.out.println("You Lost! GAME OVER!");
    }

    // Vice versa for gameover
    public void win() {
        System.out.println("You Won! GREAT JOB!");
    }
}
