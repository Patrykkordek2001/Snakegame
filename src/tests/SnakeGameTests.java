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
        /** Simulate pressing the down arrow key **/

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
        /** Simulate pressing the right arrow key **/

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
        /** Simulate pressing the up arrow key **/

        GamePanel gpl = new GamePanel();
        KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP);
        gpl.getKeyListeners()[0].keyPressed(key);
        assertTrue(gpl.up);
        assertFalse(gpl.left);
        assertFalse(gpl.down);
        assertFalse(gpl.right);


    }
    @Test
    void testReserSpace() {
        /** Simulate pressing the space key to restart**/

        GamePanel gpl = new GamePanel();
        KeyEvent key = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_SPACE);
        gpl.getKeyListeners()[0].keyPressed(key);
        assertEquals(0,gpl.score);
        assertTrue(gpl.right);
        assertFalse(gpl.gameOver);
        assertEquals(3,gpl.lenghtOfSnake);
        assertEquals(0,gpl.moves);

    }

    @Test
    void testCollidesWithPoint() throws InterruptedException {
        /** Testing score system when snake collides with point **/

        GamePanel gpl = new GamePanel();
        int score_before_point = gpl.score;
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
    @Test
    void testCollidesWithBody() throws InterruptedException {
        /** Testing gameover() method when snake collides with body **/

        GamePanel gpl = new GamePanel();
        gpl.snakexlength[0]=275;
        gpl.snakexlength[1]=250;
        gpl.snakexlength[2]=225;
        gpl.snakexlength[3]=200;
        gpl.snakexlength[4]=175;
        gpl.snakeylength[0]=100;
        gpl.snakeylength[1]=100;
        gpl.snakeylength[2]=100;
        gpl.snakeylength[3]=100;
        gpl.snakeylength[4]=100;
        gpl.lenghtOfSnake = 5;
        KeyEvent key1 = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_DOWN);
        gpl.getKeyListeners()[0].keyPressed(key1);
        Thread.sleep(100);
        KeyEvent key2 = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_LEFT);
        gpl.getKeyListeners()[0].keyPressed(key2);
        Thread.sleep(100);
        KeyEvent key3 = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_UP);
        gpl.getKeyListeners()[0].keyPressed(key3);
        Thread.sleep(100);
        KeyEvent key4 = new KeyEvent(gpl, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0,  KeyEvent.VK_RIGHT);
        Thread.sleep(100);
        assertTrue(gpl.gameOver);
    }


    }



