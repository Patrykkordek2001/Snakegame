import org.junit.jupiter.api.Test;
import snake.src.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SnakeGameTests {


    @Test
    void testMoveRight() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_RIGHT);
            robot.keyRelease(KeyEvent.VK_RIGHT);
            //boolean check
            //if()

        } catch (AWTException e) {
            e.printStackTrace();
        }


    }

    void testSnakeMoves(){
        if()
    }



}
