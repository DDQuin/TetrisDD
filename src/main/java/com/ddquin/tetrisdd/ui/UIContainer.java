package com.ddquin.tetrisdd.ui;

import com.ddquin.tetrisdd.util.Util;

import java.awt.*;
import java.util.ArrayList;


public class UIContainer extends UIObject {

    private static final int ARC_SCALING_FACTOR = 5;


    private int stroke; // How thick is outside line

    private java.util.List<UIObject> objectList; // what button says


    private Color insideColor; // color inside button

    private Color outsideColor; // color outside button


    public UIContainer(float x, float y, int width, int height, int stroke, Color insideColor, Color outsideColor) {
        super(x, y, width, height);
        this.stroke = stroke;
        this.insideColor = insideColor;
        this.outsideColor = outsideColor;
        this.objectList = new ArrayList<>();
    }

    public void addObject(UIObject object) {
        this.objectList.add(object);
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(outsideColor);
        g.fillRoundRect((int) x, (int) y, width, height, width / ARC_SCALING_FACTOR, width / ARC_SCALING_FACTOR);

        g.setColor(insideColor);
        g.fillRoundRect((int) x + stroke / 2, (int) y + stroke / 2, width - stroke, height - stroke, width / 5, width / 5);

        g.setColor(outsideColor);

        for (int i = 0; i < objectList.size(); i++) {
            UIObject object = objectList.get(i);
            int yOffset = i * height/objectList.size() + 40;
            object.setX(x + 40);
            object.setY(y + yOffset);
        }

//        for (int i = 0; i < textList.size(); i++) {
//            String text = textList.get(i);
//            int yOffset = i * height/textList.size() + 40;
//            int oldX = (int) x + width / 2 - text.length() * (fontSize / FONT_X_SCALING_FACTOR);
//            g.drawString(text, (int)x + 20, (int) y + yOffset);
//
//        }

    }

    @Override
    public void onClick() {

    }

    @Override
    public void onClickOutside() {

    }

}
