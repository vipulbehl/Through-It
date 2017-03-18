package com.demonhunts.throughit.helpers;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class FontHelper {

    GlyphLayout layout = new GlyphLayout();

    public int getWidth(String text){
        layout.setText(AssetLoader.font,text);
        return (int)layout.width;
    }

    public int getHeight(String text){
        layout.setText(AssetLoader.font,text);
        return (int)layout.height;
    }

    public int centerAlign(String text){
        layout.setText(AssetLoader.font,text);
        float centerWidth = (136/2)-(layout.width/2);
        return (int)centerWidth;
    }
}
