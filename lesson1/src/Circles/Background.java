package Circles;

import java.awt.*;

public class Background extends Sprite { //не стал фантазировать - цвет генериться сам
    private  Color color = new Color(
            (int)(Math.random() * 255),
            (int)(Math.random() * 255),
            (int)(Math.random() * 255)
    );
    private float deltaTimeBG = 0f; // отсчёт времени до смены

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        // частота смены бэкграунда - задана смена в 1 секунду, можно тоже зарандомить разными способами или другие привязки сделать
        float changeTime = 1f;
        if (this.deltaTimeBG >= changeTime) {
            this.color = new Color((int) (Math.random() * 255),
                    (int) (Math.random() * 255),
                    (int) (Math.random() * 255)
            );
            this.deltaTimeBG = 0;
        } else {this.deltaTimeBG += deltaTime;}
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillRect(canvas.getLeft(), canvas.getTop(), canvas.getWidth(), canvas.getHeight()); //отрисовка по границе окна
    }
}
