package com.example.project;

// Class representing the player character, which extends Sprite
public class Player extends Sprite {
    private int treasureCount;  // Number of treasures collected
    private int numLives;       // Number of lives remaining
    private boolean win;        // Whether the player has won the game

    public Player(int x, int y) {
        super(x, y);  // Call the constructor of the parent class (Sprite)
        this.treasureCount = 0;
        this.numLives = 2;
        this.win = false;
    }

    public String getCoords() {
        return "Player:" + super.getCoords();  // Get the player's coordinates
    }

    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);  // Get the player's position in row/col format
    }

    public int getTreasureCount() {
        return treasureCount;  // Return the number of treasures collected
    }

    public int getLives() {
        return numLives;  // Return the number of lives remaining
    }

    public boolean getWin() {
        return win;  // Return whether the player has won the game
    }

    // Override the move method to update the player's position based on the direction
    @Override
    public void move(String direction) {
        int newX = getX();
        int newY = getY();

        // Update position based on the direction
        switch (direction.toLowerCase()) {
            case "w": newY += 1; break;
            case "s": newY -= 1; break;
            case "a": newX -= 1; break;
            case "d": newX += 1; break;
            default: return;
        }

        // Only update position if the move is valid
        if (isValid(10, direction)) {  // Assuming grid size is 10
            setX(newX);
            setY(newY);
        }
    }

    // Method to interact with other game objects (treasures, enemies, trophies)
    public void interact(int size, String direction, int numTreasures, Object obj) {
        if (obj instanceof Treasure) {
            treasureCount++;  // Increase treasure count when player collects a treasure
            System.out.println("You have collected " + treasureCount + "/" + numTreasures + " Treasures!");
        } else if (obj instanceof Enemy) {
            numLives--;  // Decrease lives when player encounters an enemy
            System.out.println("You have encountered an enemy. You have " + numLives + "/2 lives left.");
        } else if (obj instanceof Trophy && treasureCount == numTreasures) {
            win = true;  // Player wins if they collect all treasures and reach the trophy
        } else if (obj instanceof Trophy) {
            System.out.println("You need to collect all the treasures before you can win!");
        }
    }

    // Check if a move is valid (within bounds of the grid)
    public boolean isValid(int size, String direction) {
        int newX = getX();
        int newY = getY();
        switch (direction.toLowerCase()) {
            case "w": newY += 1; break;
            case "s": newY -= 1; break;
            case "a": newX -= 1; break;
            case "d": newX += 1; break;
            default: return false;
        }
        return newX >= 0 && newX < size && newY >= 0 && newY < size;
    }
}
