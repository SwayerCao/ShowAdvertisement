package com.example.showadvertisement.bean;

import android.graphics.drawable.Drawable;

public class SettingTab {

    private final int drawableId;

    private final String textContent;

    public SettingTab(int drawableId, String textContent) {
        this.drawableId = drawableId;
        this.textContent = textContent;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String getTextContent() {
        return textContent;
    }
}
