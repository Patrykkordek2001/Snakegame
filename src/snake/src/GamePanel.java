package snake.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener, KeyListener {

    public int[] snakexlength = new int[750];
    public int[] snakeylength = new int[750];
    private int lenghtOfSnake = 3;

    private Random random = new Random();
    public int pointX;
    public int pointY;
    public boolean left = false;
    public boolean right = true;
    public boolean up = false;
    public boolean down = false;
    private int moves = 0;
    public int score = 0;
    public boolean gameOver = false;

    private int[] xPos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
    private int[] yPos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};


    private ImageIcon snaketitle = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/snaketitle.jpg")));
    private ImageIcon leftmouth = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/leftmouth1.png")));
    private ImageIcon rightmouth = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/rightmouth1.png")));
    private ImageIcon downmouth = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/downmouth1.png")));
    private ImageIcon upmouth = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/upmouth1.png")));
    private ImageIcon snakeimage = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/snakeimage.png")));
    private ImageIcon enemy = new ImageIcon(Objects.requireNonNull(getClass().getResource("resources/enemy.png")));

    private Timer timer;
    private int delay = 100;

public GamePanel(){

    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(true);
    timer = new Timer(delay,this);
    timer.start();
    newPoint();
}



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.DARK_GRAY);
        g.drawRect(24,10,851,55);
        g.setColor(Color.WHITE);
        g.drawRect(24,74,851,576);
        snaketitle.paintIcon(this, g,25,11);
        g.setColor(Color.BLACK);
        g.fillRect(25,75,850,575);

        if(moves == 0){
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;


            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
        }

        if(left){
            leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if(right){
            rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if(up){
            upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if(down){
            downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }

        for(int i=1;i<lenghtOfSnake;i++){
            snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);

        }
        enemy.paintIcon(this, g, pointX, pointY);

        if(gameOver){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial",Font.BOLD,50));
            g.drawString("Game Over",300,300);
            g.setFont(new Font("Arial",Font.PLAIN,20));
            g.drawString("Press Space to Restart",320,350);
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial",Font.PLAIN,14));
        g.drawString("Score : "+score,30,30);
        g.drawString("Length : "+lenghtOfSnake,30,50);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i=lenghtOfSnake-1;i>0;i--){
            snakexlength[i] = snakexlength[i-1];
            snakeylength[i] = snakeylength[i-1];
        }

        if(left){
            snakexlength[0] = snakexlength[0] - 25;
        }
        if(right){
            snakexlength[0] = snakexlength[0] + 25;
        }
        if(up){
            snakeylength[0] = snakeylength[0] - 25;
        }
        if(down){
            snakeylength[0] = snakeylength[0] + 25;
        }

        if(snakexlength[0]>850) snakexlength[0]=25;
        if(snakexlength[0]<25) snakexlength[0]=850;

        if(snakeylength[0]>625) snakeylength[0]=75;
        if(snakeylength[0]<75) snakeylength[0]=625;

        collidesWithPoint();
        collidesWithBody();
        repaint();

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            restart();
        }

        if(e.getKeyCode()==KeyEvent.VK_LEFT &&(!right)){
            left = true;
            right=false;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)){
            left = false;
            right=true;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && (!down)){
            left = false;
            right=false;
            up=true;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)){
            left = false;
            right=false;
            up=false;
            down=true;
            moves++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void newPoint() {

        pointX=xPos[random.nextInt(34)];
        pointY=yPos[random.nextInt(23)];


        for(int i=lenghtOfSnake - 1;i>=0;i--){
            if(snakexlength[i]==pointX && snakeylength[i]==pointY){
                newPoint();
            }
        }
    }


    private void collidesWithPoint() {

        if(snakexlength[0]==pointX && snakeylength[0]==pointY){
            newPoint();
            lenghtOfSnake++;
            score++;
        }

    }

    private void collidesWithBody() {
        for(int i=lenghtOfSnake-1;i>0;i--)
        {
            if(snakexlength[i]== snakexlength[0] && snakeylength[i]== snakeylength[0]){
                timer.stop();
                gameOver=true;
            }
        }
    }

    private void restart(){

        gameOver=false;
        moves = 0;
        score = 0;
        lenghtOfSnake = 3;
        left = false;
        right = true;
        up = false;
        down = false;
        timer.start();
        repaint();

    }





}
