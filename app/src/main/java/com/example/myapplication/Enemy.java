package com.example.myapplication;

import android.graphics.Rect;

public class Enemy {

    //ステータス========================
    private float maxHp;
    private float hp;
    private int attack;
    private boolean alive;

    boolean IsAlive(){return alive;}
    void Damage(int damage){
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            alive = false;
        }
    }
    //================================

    //アニメーション=====================
    private int posX;
    private int posY;
    private float rot;
    private Rect rec;
    private int hitSize;
    private String charaImgName;

    int GetPosX(){return posX;}
    int GetPosY(){return posY;}
    int GetHitSize(){return hitSize;}
    //================================

    // HPバー==========================
    private Vector2 hpPos;
    private String hpImgName;
    private int hpImgSizeX;
    private Vector2 hpFlamePos;
    private String hpFlameImgName;
    //=================================

    //// Initialize ////
    void Init()
    {
        // ステータス========================
        maxHp = 600;
        hp = maxHp;
        attack = 10;
        alive = true;
        //=================================

        //アニメーション======================
        posX = 300;
        posY = -100;
        rot = 0;
        hitSize = 85;
        charaImgName = "Enemy_2.png";
        //==================================

        // Hpバー============================
        hpPos = new Vector2();
        hpImgSizeX = 564;
        hpPos.x = 300;
        hpPos.y = 24/2;
        hpImgName = "enemyHp.png";
        hpFlamePos = new Vector2();
        hpFlamePos.x = 300;
        hpFlamePos.y = 24/2;
        hpFlameImgName = "enemyHpBar.png";
        //===================================
    }

    //// ムーブ ////
    boolean Move(int x,int y)
    {
        boolean CheckPoint = true;

        if(posX != x){
            if(posX < x){posX += 2;}
            else {posX -= 2;}
            CheckPoint = false;
        }
        if(posY != y) {
            if(posY < y){posY += 2;}
            else {posY -= 2;}
            CheckPoint = false;
        }
        return CheckPoint;
    }

    //// Draw ////
    void Draw()
    {
        // キャラ
        App.Get().ImageMgr().Draw(charaImgName,posX,posY,rot);
        // hp
        float sizeX = hp/maxHp;
        float EditposX = hpPos.x + hpImgSizeX/2 - (hpImgSizeX - hpImgSizeX * (hp / maxHp)/2);
        App.Get().ImageMgr().Draw(hpImgName,EditposX,hpPos.y,sizeX,1,0);
        // hpバー
        App.Get().ImageMgr().Draw(hpFlameImgName,hpFlamePos.x,hpFlamePos.y);
    }
}
