package com.example.myapplication;

public class BackGround {

    //アニメーション========================
    private int posX;
    private int posY;
    private float sizeX;
    private float sizeY;
    private String backImgName;
    //===================================

    //// Initialize ////
    void Init()
    {
        posX = 1080/3;
        posY = 1794/3;
        sizeX = 4.0f;
        sizeY = 6.0f;
        backImgName = "backGround.png";
    }

    void Draw()
    {
        App.Get().ImageMgr().Draw(backImgName,posX,posY,sizeX,sizeY,0);
    }
}
