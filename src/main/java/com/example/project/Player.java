package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) {
        super(x, y);
        this.treasureCount = 0;
        this.numLives = 2;
        this.win = false;
    }
    public String getCoords() {
        return "Player:" + super.getCoords();
    }
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size);
    }
    public int getTreasureCount(){
        return treasureCount;
    }
    public int getLives(){
        return numLives;
    }
    public boolean getWin(){
        return win;
    }

    @Override
public void move(String direction) {
    int newX = getX();
    int newY = getY();

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
    
    public void interact(int size, String direction, int numTreasures, Object obj) { 
        if (obj instanceof Treasure) {
            treasureCount++;
            System.out.println("You have collected " + treasureCount + "/" + numTreasures + " Treasures!");
        } else if (obj instanceof Enemy) { 
            numLives--;
            System.out.println("You have encountered an enemy. You have " + numLives + "/2 lives left.");
            if (numLives <= 0) {
            }
        } else if (obj instanceof Trophy && treasureCount == numTreasures) {
            win = true;
        }
        else if (obj instanceof Trophy) {
            System.out.println("You need to collect all the treasures before you can win!");
        }
    }

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



