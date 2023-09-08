package com.ddquin.tetrisdd.ui;
import java.awt.*;

public class UIButton extends UIObject{

    private static final int ARC_SCALING_FACTOR = 5;

    private static final int FONT_X_SCALING_FACTOR = 5;

    private static final int FONT_Y_SCALING_FACTOR = 4;

    private ClickListener clicker;

    private int stroke; // How thick is outside line

    private String text; // what button says

    private int fontSize;

    private Color insideColor; // color inside button

    private Color outsideColor; // color outside button

    public UIButton(float x, float y, int width, int height, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.stroke = 5;
        this.insideColor = new Color(3, 3, 211);
        this.outsideColor = Color.ORANGE;
        this.text = "Quit";
        this.fontSize = 20;

    }

    public UIButton(float x, float y, int width, int height, int stroke, Color insideColor, Color outsideColor, String text, int fontSize, ClickListener clicker) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.stroke = stroke;
        this.insideColor = insideColor;
        this.outsideColor = outsideColor;
        this.text = text;
        this.fontSize = fontSize;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.setColor(hovering ? outsideColor.darker() : outsideColor);
        g.fillRoundRect((int) x, (int) y, width, height, width / ARC_SCALING_FACTOR, width / ARC_SCALING_FACTOR);

        g.setColor(hovering ? insideColor.darker() : insideColor);
        g.fillRoundRect((int) x + stroke / 2, (int) y + stroke / 2, width - stroke, height - stroke, width / 5, width / 5);

        g.setFont(new Font("Segoe UI Semibold", Font.PLAIN, fontSize));
        g.setColor(hovering ? outsideColor.darker() : outsideColor);
        g.drawString(text, (int) x + width / 2 - text.length() * (fontSize / FONT_X_SCALING_FACTOR), (int) y + height / 2 + fontSize / FONT_Y_SCALING_FACTOR);
    }

    @Override
    public boolean onClick() {
       return clicker.onClick();
    }



}
