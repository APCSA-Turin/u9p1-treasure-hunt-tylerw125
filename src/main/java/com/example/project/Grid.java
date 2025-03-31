package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size = size;
        this.grid = new Sprite[size][size];

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                Dot dot = new Dot(j, i);
                grid[i][j] = dot;
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        int y = s.getY();
        int x = s.getX();
        
        // Check if coordinates are within bounds
        if (x >= 0 && x < size && y >= 0 && y < size) {
            grid[size - 1 - y][x] = s;
        }
    }
    
    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        int newX = s.getX();
        int newY = s.getY();
        
        // Calculate new position based on direction
        if(direction.equals("a") && newX + 1 < size) {
            placeSprite(new Dot(newX + 1, newY));
        } else if(direction.equals("d") && newX - 1 >= 0) {
            placeSprite(new Dot(newX - 1, newY));
        } else if(direction.equals("s") && newY + 1 < size) {
            placeSprite(new Dot(newX, newY + 1));
        } else if(direction.equals("w") && newY - 1 >= 0) {
            placeSprite(new Dot(newX, newY - 1));
        }
        
        // Place the sprite only if it's within bounds
        if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
            placeSprite(s);
        }
    }


    public void display() { //print out the current grid to the screen 
        for(Sprite[] row : grid) {
            for(Sprite col : row) {
                if(col instanceof Trophy) {
                    System.out.print("🌟");
                } else if(col instanceof Player) {
                    System.out.print("🕺");
                } else if(col instanceof Enemy) {
                    System.out.print("🐢");
                } else if(col instanceof Treasure) {
                    System.out.print("🍄");
                } else if(col instanceof Dot) {
                    System.out.print("🟥");
                }
            }
            System.out.println();
        }
        
    }

    public void winDisplay() {
        for(Sprite[] row : grid) {
            for(Sprite col : row) {
                if(col instanceof Player) {
                    System.out.print("🕺");
                } else {
                    System.out.print("👑");
                }
            }
            System.out.println();
        }
    }

    public void loseDisplay() {
        for(Sprite[] row : grid) {
            for(Sprite col : row) {
                if(col instanceof Player) {
                    System.out.print("🕺");
                } else {
                    System.out.print("👾");
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        System.out.println("You Lost! GAME OVER!");
    }

    public void win(){ //use this method to display a win 
        System.out.println("You Won! GREAT JOB!");
    }


}