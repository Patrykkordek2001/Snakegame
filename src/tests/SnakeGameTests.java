import org.junit.jupiter.api.Test;
import snake.src.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;


public class SnakeGameTests {


    @Test
    void testMoveDown() {

            GamePanel gpl = new GamePanel();
            KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN);
            gpl.getKeyListeners()[0].keyPressed(key);
            assertTrue(gpl.down);
            assertFalse(gpl.left);
            assertFalse(gpl.up);
            assertFalse(gpl.right);


    }
    @Test
    void testMoveRight() {

        GamePanel gpl = new GamePanel();
        KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT);
        gpl.getKeyListeners()[0].keyPressed(key);
        assertTrue(gpl.right);
        assertFalse(gpl.left);
        assertFalse(gpl.up);
        assertFalse(gpl.down);


    }
    @Test
    void testMoveUP() {

        GamePanel gpl = new GamePanel();
        KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP);
        gpl.getKeyListeners()[0].keyPressed(key);
        assertTrue(gpl.up);
        assertFalse(gpl.left);
        assertFalse(gpl.down);
        assertFalse(gpl.right);


    }

    @Test
    void testCollidesWithPoint() throws InterruptedException {
        GamePanel gpl = new GamePanel();
        int score_before_point =gpl.score;
        gpl.snakexlength[0]=100;
        gpl.snakexlength[1]=75;
        gpl.snakexlength[2]=50;
        gpl.snakeylength[0]=100;
        gpl.snakeylength[1]=100;
        gpl.snakeylength[2]=100;
        gpl.pointY = 100;
        gpl.pointX = 150;
        KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT);
        gpl.getKeyListeners()[0].keyPressed(key);
        Thread.sleep(300);
        assertEquals(score_before_point + 1,gpl.score);

    }
    /*@Test
    void testCollidesWithBody(){
        GamePanel gpl = new GamePanel();
        KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT);
        gpl.getKeyListeners()[0].keyPressed(key);
        gpl.snakexlength[0]=gpl.snakexlength[1];
        gpl.snakeylength[0]=gpl.snakeylength[1];
        assertTrue(gpl.gameOver);
    }*/









    }



