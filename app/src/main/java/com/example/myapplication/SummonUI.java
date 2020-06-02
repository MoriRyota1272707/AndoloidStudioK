package com.example.myapplication;

import android.graphics.Rect;

public class SummonUI {

    // ステータス=====================================
    private int SummonCost;

    int GetSummonCost(){return SummonCost;}
    //==============================================

    // アニメーション==================================
    private int posX;
    private int posY;
    private Rect rec;
    private String uiImgName;

    String GetuiImgName(){return uiImgName;}
    //==============================================

    //// Initialize ////
    void Init(String ImgName,int x,int y)
    {
        //ステータス============================
        SummonCost = 10;
        //====================================
        //アニメーション=========================
        posX = x;
        posY = y;
        int uiImgHalfSizeX = 32;
        int uiImgHalfSizeY = 32;
        rec = new Rect();
        rec.left = posX - uiImgHalfSizeX;
        rec.top = posY - uiImgHalfSizeY;
        rec.right = posX + uiImgHalfSizeX;
        rec.bottom = posY + uiImgHalfSizeY;
        uiImgName = ImgName;
        //====================================
    }

    //// RectUpdate ////
    boolean CheckHit(Pointer p)
    {
        if(rec.contains(p.GetDownPos().x,p.GetDownPos().y)){return true;}
        else {return false;}
    }

    //// Draw ////
    void Draw()
    {
        App.Get().ImageMgr().Draw(uiImgName,posX,posY);
    }
}
