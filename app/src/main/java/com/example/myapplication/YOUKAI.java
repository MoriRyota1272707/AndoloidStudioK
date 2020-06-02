package com.example.myapplication;

import android.graphics.Rect;

public class YOUKAI {

    //ステータス=======================================
    private int hp;
    private int attack;
    private boolean alive;
    private boolean SettingNow;
    private boolean shooting;
    private boolean hitEnemy;

    void SetAlive(boolean b){alive = b;}
    boolean IsAlive(){return alive;}
    void SetSettingNow(boolean s){SettingNow = s;}
    boolean IsSettingNow(){return SettingNow;}
    void SetShooting(boolean shoot){shooting = shoot;}
    boolean IsShooting(){return shooting;}
    void SetHitEnemy(boolean b){hitEnemy = b;}
    boolean IsHitEnemy(){return hitEnemy;}
    //==============================================

    //アニメーション===================================
    private Vector2 pos;
    private float rot;
    private float hitSize;
    private String charaImgName;
    private float imgSize;      // 仮！！
    private float shootingRot;  //飛ばされているときの 回転 サイズ スピード
    private float shootingSize;
    private float shootingFloat;
    private float shootingSpeed;

    void SetPosX(float x){pos.x = x;}
    void SetPosY(float y){pos.y = y;}
    //==============================================

    // 他のクラス=======================================
    private Animation atkAnim;
    Animation GetatkAnim(){return atkAnim;}
    //================================================

    //// Initialize ////===================================================================
    void AddInit(int YoukaiNumber)
    {
        //ここで受け取った YoukaiNumberから設定する画像とステータスを決める処理を作る！！

        //ステータス===========
        hp = 20;
        attack = 2;
        alive = true;
        SettingNow = true;
        shooting = false;
        hitEnemy = false;
        //===================
        //アニメーション======================
        pos = new Vector2();
        charaImgName = "ball.png";
        imgSize = 0.6f;
        hitSize = 64 * imgSize / 2.0f;
        //=================================
        // 他のクラス==================================
        atkAnim = new Animation();
        atkAnim.Init("nomalAttack0_",18,1,1);
        //============================================
    }
    //=====================================================================================

    //// 敵追尾 ////==========================================================
    void TrackingMoving(Enemy enem)
    {
        if(!enem.IsAlive())return;  //敵の生死判定
        if(shooting)return;         //飛ばされれいるときは動けない
        float ix = pos.x;
        float iy = pos.y;

        if(!CheckHit(enem.GetPosX(),enem.GetPosX(),enem.GetHitSize()))
        {
            int speed = 2;
            double angle = GetAngle(enem.GetPosX(),enem.GetPosY());
            pos.x += Math.cos(angle) * speed;
            pos.y += Math.sin(angle) * speed;

            if(CheckHit(enem.GetPosX(),enem.GetPosX(),enem.GetHitSize())){
                pos.x = ix;
                pos.y = iy;
                hitEnemy = true;
            }else {hitEnemy = false;}
        }
    }
    //============================================================================

    // 当たり判定======================================================================
    boolean CheckHit(int x,int y,float hitsize)
    {
        // 範囲内に入っているか
        float distance = GetDis(x,y);
        float hantei = hitSize + hitsize;
        return distance <= hantei;
    }
    private float GetDis(int x,int y){return (float)Math.sqrt((pos.x - x)*(pos.x - x) + (pos.y - y)*(pos.y - y));}
    private double GetAngle(int x,int y){return (Math.atan2(y - pos.y,x - pos.x)) * 180/Math.PI;}
    //================================================================================

    // 攻撃===========================================================
    void Attack(Enemy enem)
    {
        enem.Damage(attack);
        //アニメーション
        float angle = (float)GetAngle(enem.GetPosX(),enem.GetPosY());
        atkAnim.StartAnim(pos.x + (float)Math.cos(angle) * 32,pos.y + (float)Math.sin(angle) * 32);
    }
    //================================================================

    // 飛ばす==========================================================
    void Shooting()
    {
        pos.x += Math.cos(shootingRot) * shootingSpeed;
        pos.y += Math.sin(shootingRot) * shootingSpeed;
    }
    void SettingStooting(float sRot,float sSpeed)
    {
        shootingRot = sRot;
        shootingSpeed = sSpeed;
        shooting = true;
    }
    //================================================================

    //// Draw /////////////////////////////////////////////////////////////////////////
    void Draw()
    {
        // 仮！！
        App.Get().ImageMgr().Draw(charaImgName,pos.x,pos.y,imgSize,imgSize,rot);
    }
}
