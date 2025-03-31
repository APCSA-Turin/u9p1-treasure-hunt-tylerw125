package com.example.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class UI {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton easyButton, mediumButton, hardButton;
    private JButton settingsButton;
    
    public UI() {
        frame = new JFrame("Treasure Hunt Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        
        JLabel title = new JLabel("Treasure Hunt Game");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        
        easyButton = new JButton("Easy");
        mediumButton = new JButton("Medium");
        hardButton = new JButton("Hard");
        settingsButton = new JButton(new ImageIcon("settings.png")); // Image should be provided

        easyButton.addActionListener(e -> startGame(10, 2, 3));
        mediumButton.addActionListener(e -> startGame(15, 3, 2));
        hardButton.addActionListener(e -> startGame(20, 5, 1));
        settingsButton.addActionListener(e -> showSettings());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(title, gbc);
        
        gbc.gridy = 1;
        mainPanel.add(easyButton, gbc);
        gbc.gridy = 2;
        mainPanel.add(mediumButton, gbc);
        gbc.gridy = 3;
        mainPanel.add(hardButton, gbc);
        gbc.gridy = 4;
        mainPanel.add(settingsButton, gbc);
        
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
    
    private void startGame(int gridSize, int numEnemies, int lives) {
        frame.getContentPane().removeAll();
        frame.repaint();
        new GameUI(frame, gridSize, numEnemies, lives);
    }
    
    private void showSettings() {
        JOptionPane.showMessageDialog(frame, "Settings menu (to be implemented)", "Settings", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        new UI();
    }
}

class GameUI {
    private JFrame frame;
    private JPanel gridPanel;
    private int gridSize, numEnemies, lives;
    private JButton[][] gridButtons;
    private Point playerPosition;
    private Point[] enemyPositions;
    private Random random;
    
    public GameUI(JFrame frame, int gridSize, int numEnemies, int lives) {
        this.frame = frame;
        this.gridSize = gridSize;
        this.numEnemies = numEnemies;
        this.lives = lives;
        this.gridButtons = new JButton[gridSize][gridSize];
        this.playerPosition = new Point(0, 0);
        this.enemyPositions = new Point[numEnemies];
        this.random = new Random();
        
        frame.setLayout(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(gridSize, gridSize));
        
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                gridButtons[i][j] = new JButton();
                gridButtons[i][j].setEnabled(false);
                gridPanel.add(gridButtons[i][j]);
            }
        }
        
        gridButtons[playerPosition.x][playerPosition.y].setText("P");
        
        for (int i = 0; i < numEnemies; i++) {
            enemyPositions[i] = new Point(random.nextInt(gridSize), random.nextInt(gridSize));
            gridButtons[enemyPositions[i].x][enemyPositions[i].y].setText("E");
        }
        
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
    
    public void movePlayer(int dx, int dy) {
        int newX = playerPosition.x + dx;
        int newY = playerPosition.y + dy;
        
        if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {
            gridButtons[playerPosition.x][playerPosition.y].setText("");
            playerPosition.setLocation(newX, newY);
            gridButtons[newX][newY].setText("P");
            moveEnemies();
        }
    }
    
    private void moveEnemies() {
        for (int i = 0; i < numEnemies; i++) {
            int dx = Integer.compare(playerPosition.x, enemyPositions[i].x);
            int dy = Integer.compare(playerPosition.y, enemyPositions[i].y);
            
            int newX = enemyPositions[i].x + dx;
            int newY = enemyPositions[i].y + dy;
            
            if (newX >= 0 && newX < gridSize && newY >= 0 && newY < gridSize) {
                gridButtons[enemyPositions[i].x][enemyPositions[i].y].setText("");
                enemyPositions[i].setLocation(newX, newY);
                gridButtons[newX][newY].setText("E");
            }
        }
    }
}
