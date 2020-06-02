package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Paint;

public class GameUI {

    //アニメーション=============================
    private int uifPosX;        //UIフレーム
    private int uifPosY;
    private float uifSizeX;
    private float uifSizeY;
    private String uiFlame;
    private Vector2 ypPos;      //妖力
    private String ypImgName;

    float GetUILine(){return uifPosY - (600 * uifSizeY)/2;}
    //========================================
    //文字===========================================
    private Paint ypPaint;
    //===============================================

    //他のクラス============================================
    private int maxSUI = 4;
    private SummonUI[] summonUI = new SummonUI[maxSUI];

    int GetSummonYoukaiCost(int summonUINumber){return summonUI[summonUINumber].GetSummonCost();}
    //====================================================

    //// Initialize ////
    void Init()
    {
        //アニメーション================================
        uifPosX = 300;                  //UIフレーム
        uifPosY = 1167 - 240;
        uifSizeX = 1.2f;
        uifSizeY = 0.8f;
        uiFlame = "byunn.png";
        ypPos = new Vector2();          //妖力
        ypPos.x = uifPosX - 220;
        ypPos.y = uifPosY + 120 - 50;
        ypImgName = "youkaiPower.png";
        //===========================================
        // 文字======================================
        ypPaint = new Paint();
        ypPaint.setTypeface(App.Get().GetFont());
        ypPaint.setStrokeWidth(3);
        ypPaint.setTextSize(70);
        ypPaint.setColor(Color.rgb(0,0,0));
        //===========================================
        // 他のクラス==================================
        for(int i = 0;i < summonUI.length;i++) {
            summonUI[i] = new SummonUI();
            int x = 70 + 128*i;
            int y = uifPosY - 64;
            summonUI[i].Init("ball.png",x,y);
        }
        //===========================================
    }

    //SummonUI=======================================
    SummonUI[] GetSummonUIs(){return summonUI;}
    //===============================================

    //// Draw ////
    void Draw(Player player)
    {
        // 枠
        App.Get().ImageMgr().Draw(uiFlame,uifPosX,uifPosY,uifSizeX,uifSizeY,0);
        // 召喚ボタン
        for(int i = 0;i < summonUI.length;i++){summonUI[i].Draw();}

        // 妖力
        ypUIDraw(player);
    }

    // 妖力UI===============================================================
    void ypUIDraw(Player player)
    {
        //画像
        App.Get().ImageMgr().Draw(ypImgName,ypPos.x, ypPos.y);
        //数値
        float ypX = ypPos.x + 160;
        float ypY = ypPos.x + 1750;
        App.Get().GetView().GetCanvas().drawText("" + player.GetYoukaiPower(),ypX,ypY,ypPaint);
    }
    //======================================================================
}
