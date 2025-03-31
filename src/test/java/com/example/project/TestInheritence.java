package com.example.project;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TestInheritence {
    @Test 
    public void testInstanceOf(){
        Dot dot = new Dot(1,1);
        Enemy enemy = new Enemy(1,2);
        Treasure treasure = new Treasure(1,3);
        Player player = new Player(1,4);
        Trophy trophy = new Trophy(1,5);
        assertTrue(dot instanceof Sprite, "Dot does not extend Sprite");
        assertTrue(enemy instanceof Sprite, "Enemy does not extend Sprite");
        assertTrue(treasure instanceof Sprite, "Treasure  does not extend Sprite");
        assertTrue(player instanceof Sprite, "Player does not extend Sprite");
        assertTrue(trophy instanceof Sprite, "Trophy does not extend Treasure");
    }

    @Test 
    public void testGetCoordsSprite(){
        Sprite sprite = new Sprite(1,1);
        assertEquals("(1,1)", sprite.getCoords());

        Player player = new Player(9,3);
        assertEquals("Player:(9,3)",player.getCoords());

        Enemy enemy = new Enemy(2,3);
        assertEquals("Enemy:(2,3)",enemy.getCoords());

    }

    @Test
    public void testGetRowCol(){
        Sprite sprite = new Sprite(4,1);
        assertEquals("[3][4]",sprite.getRowCol(5));

        Player player = new Player(5,6);
        assertEquals("Player:[1][5]",player.getRowCol(8));

        Enemy enemy = new Enemy(5,3);
        assertEquals("Enemy:[5][5]",enemy.getRowCol(9));
    }


}
