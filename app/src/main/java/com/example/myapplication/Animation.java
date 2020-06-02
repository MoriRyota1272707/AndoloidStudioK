package com.example.myapplication;

import android.graphics.Rect;

public class Animation {

    //アニメーション========================
    private String[] imgName;
    private Vector2 pos;
    private float rot;
    private int imgSizeX;
    private int imgSizeY;
    private int length;
    private int imgNumber;
    private int AnimUpdateTime;
    private int Time;
    private boolean AnimNow;

    boolean IsAnimNow(){return AnimNow;}
    //====================================


    // Initialize==========================================
    void Init(String s,int l,int sX,int sY)
    {
        //アニメーション=============================
        pos = new Vector2();
        length = l;
        imgName = new String[l];
        for(int i = 0;i < l;i++) {
            imgName[i] = "";
            imgName[i] = s + i + ".png";
        }
        imgSizeX = sX;
        imgSizeY = sY;
        AnimUpdateTime = 2;
        //=========================================
    }
    //=====================================================

    // アニメーションリロード ================
    private void ReLoad(float x,float y) {
        pos.x = x;
        pos.y = y;
        Time = 0;
        imgNumber = 0;
    }
    //====================================

    // アニメーション開始! ====================================
    void StartAnim(float x,float y)
    {
        AnimNow = true;
        ReLoad(x,y);
    }
    //======================================================

    //Update==============================
    boolean AnimUpdate()
    {
        if(imgNumber == length - 1)
        {
            AnimNow = false;
            return true;
        }
        if(Time % AnimUpdateTime == 0)imgNumber++;
        Time++;
        return false;
    }
    //====================================

    //// Draw ////=====================================
    void Draw()
    {
        App.Get().ImageMgr().Draw(imgName[imgNumber],pos.x,pos.y,imgSizeX,imgSizeY,rot);
    }
    //=================================================
}
