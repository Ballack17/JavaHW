package Circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private final int MAXBALLS = 1000;  // максимально возможное количество шаров
    private Ball[] Balls = new Ball[MAXBALLS];   //изменил Sprites на конкретно шарики, посколько у меня уже 2 класса наследников от Sprite
    private int currentLength = 10;  // стартовое количество шаров
    private Background backGround = new Background();  // добавление отдельного объекта Бэкграунд!!!

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        initApplication();
        GameCanvas canvas = new GameCanvas(this);
        add(canvas, BorderLayout.CENTER);
        canvas.addMouseListener(new MouseAdapter() {
           // ДОбавил событие нажатия кнопки
           @Override
           public void mousePressed(MouseEvent e) {
               if (e.getButton() == MouseEvent.BUTTON1) {
                   if (currentLength < MAXBALLS) { //где кликнул там и появляется шарик
                       Balls[currentLength] = new Ball(e.getX (), e.getY());
                       currentLength += 1;
                   }
               }
               if (e.getButton() == MouseEvent.BUTTON3) {
                   if(currentLength > 0) //не стал уже писать выводы сообщений типа - нет шаров или уже макс шаров на экране..
                       currentLength -= 1;
               }
           }
        });
        setTitle("Circles");
        setVisible(true);

    }

    private void initApplication() {
        for (int i = 0; i < currentLength; i++) {
            Balls[i] = new Ball();
        }
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime); // obnovlenie // S = v * t
        render(canvas, g); // otrisovka
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < currentLength; i++) {
            Balls[i].update(canvas, deltaTime);
        }
        backGround.update(canvas, deltaTime);
    }

    private void render(GameCanvas canvas, Graphics g) {
        backGround.render(canvas, g);
        for (int i = 0; i < currentLength; i++) {
            Balls[i].render(canvas, g);
        }
    }
}
