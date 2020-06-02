package com.example.myapplication;

import java.util.ArrayList;

public class GameControllre {

    //シーン===============================
    private enum State
    {
        EnemyMove,
        Game,
        GameClear,
        GameOver,
    }
    private State state;
    //====================================

    // マウス==============================
    private boolean touchDownNow;
    private int touchFlame;
    private Vector2 touchVec;
    private Vector2 touchNowVec;
    private Vector2 upVec;
    //====================================

    //値管理===============================
    private int summonUINumber;
    //=====================================

    //他のクラス===================================
    private BackGround backGround;
    private Player player;
    private GameUI gameUI;
    private ArrayList<YOUKAI> youkais = new ArrayList<YOUKAI>();
    private Enemy enemy;
    //============================================

    //// Initialize ////
    void Init()
    {
        state = State.EnemyMove;

        // マウス===================================
        touchDownNow = false;
        touchFlame = 0;
        touchVec = new Vector2();
        touchNowVec = new Vector2();
        upVec = new Vector2();
        //=========================================
        //値管理====================================
        summonUINumber = 0;
        //=========================================
        //他のクラス=================================
        backGround = new BackGround();
        backGround.Init();
        player = new Player();
        player.Init();
        gameUI = new GameUI();
        gameUI.Init();
        enemy = new Enemy();
        enemy.Init();
        //==========================================
    }

    //// Play ////
    void Play(Pointer p)
    {
        switch(state)
        {
            case EnemyMove:
                if(CallEnemy()){state = State.Game;}
                break;

            case Game:
                Game(p);
                break;

            case GameClear:
                break;

            case GameOver:
                break;
        }
    }

    // EnemyMove=======================================
    private boolean CallEnemy()
    {
        return enemy.Move(300, 300);
    }
    //=================================================

    //// Game //// /////////////////////////////////////////////////////////////////////
    private int Game(Pointer p)
    {
        Touch(p);

        // 移動----------------------------------------
        for(int i = 0;i < youkais.size();i++){
            if(youkais.get(i).IsAlive() && !youkais.get(i).IsSettingNow()){youkais.get(i).TrackingMoving(enemy);}
        }
        // 攻撃----------------------------------------
        for(YOUKAI y:youkais)
        {
            if(y.IsAlive() && !y.IsSettingNow() && y.IsHitEnemy() && !y.GetatkAnim().IsAnimNow()){
                y.Attack(enemy);
            }
        }

        Animation();    // アニメーション
        return 0;
    }

    // タッチ=============================================================================
    private void Touch(Pointer p)
    {
        if(p == null)return;

        // 画面がタッチされたとき=====================================
        OnDown(p);
        //画面をタッチし続けているとき=================================
        DownNow(p);
        //画面から離された or フリックされたとき========================
        OnUp(p);
    }
    //====================================================================

    // タッチされたとき=======================================================
    private void OnDown(Pointer p)
    {
        if(p.OnDown())
        {
            // 妖怪召喚===================================================
            for(int i = 0;i < gameUI.GetSummonUIs().length;i++){    // 召喚するYOUKAIを選択！
                if(gameUI.GetSummonUIs()[i].CheckHit(p) &&
                player.IsCheckSummon(gameUI.GetSummonUIs()[i].GetSummonCost())) {   //召喚構え！！
                    summonUINumber = i;
                    SummonYOUKAI(summonUINumber);
                }
            }
            //===========================================================
            // タッチした座標を取得====================
            touchVec.x = p.GetDownPos().x;
            touchVec.y = p.GetDownPos().y;
            if(!touchDownNow)touchDownNow = true;
            //=====================================
        }
    }
    //====================================================================
    // タッチされている間 ====================================================
    private void DownNow(Pointer p)
    {
        if(!touchDownNow)return;

        // 座標取得===========================
        touchNowVec.x = p.GetNowPos().x;
        touchNowVec.y = p.GetNowPos().y;
        //===================================

        // セット中のYOUKAI処理==============================
        for(int i = 0;i < youkais.size();i++) {
            if(youkais.get(i).IsSettingNow()){
                youkais.get(i).SetPosX(touchNowVec.x);
                youkais.get(i).SetPosY(touchNowVec.y);
                break;
            }
        }
        //================================================
    }
    //====================================================================
    // 離されたときの処理====================================================
    private void OnUp(Pointer p)
    {
        if(p.OnUp())
        {
            // 座標取得=============================
            upVec.x = p.GetUpPos().x;
            upVec.y = p.GetUpPos().y;
            //=====================================

            YOUKAISetOrDelete();   //妖怪

            //タッチ座標Flame初期化処理================
            touchVec.Clear();
            touchNowVec.Clear();
            upVec.Clear();
            touchFlame = 0;
            if(touchDownNow)touchDownNow = false;
            //======================================
        }
    }
    //===========================================================================
    // YOUKAI召喚処理=============================================================
    private void SummonYOUKAI(int youkaiNumber)
    {
        YOUKAI youkai = new YOUKAI();
        youkai.AddInit(youkaiNumber);
        youkais.add(youkai);
    }
    //===========================================================================
    // YOUKAI セット or 削除処理====================================================
    private void YOUKAISetOrDelete()
    {
        if(upVec.y <= gameUI.GetUILine()){                //召喚したYOUKAI発射処理

            for(int i = 0;i < youkais.size();i++) {

                if(youkais.get(i).IsSettingNow()){
                    player.UseYoukaiPower(gameUI.GetSummonYoukaiCost(summonUINumber));
                    youkais.get(i).SetPosX(upVec.x);
                    youkais.get(i).SetPosY(upVec.y);
                    youkais.get(i).SetSettingNow(false);
                }
            }
        }
        else {
            // ここに呼びだしたYOUKAI消去処理を書く！！
            for(int i = 0;i < youkais.size();i++) {

                if(youkais.get(i).IsSettingNow()){youkais.remove(i);}
            }
        }
    }
    //===========================================================================

    // アニメーション===============================================
    private void Animation()
    {
        for(int i = 0;i < youkais.size();i++)
        {
            if(youkais.get(i).IsAlive() && youkais.get(i).GetatkAnim().IsAnimNow()){
                youkais.get(i).GetatkAnim().AnimUpdate();
            }
        }
    }
    //============================================================

    //// Draw //// //////////////////////////////////////////////////////////////////////////////////////////
    void Draw()
    {
        switch(state)
        {
            case EnemyMove://====================
                backGround.Draw();
                enemy.Draw();
                break;     //====================

            case Game://====================================================
                backGround.Draw();                  //背景
                if(enemy.IsAlive())enemy.Draw();    //エネミー
                YOUKAIDraw();                       //YOUKAI
                gameUI.Draw(player);                //ゲームUI
                break;//=====================================================

            case GameClear:
                break;

            case GameOver:
                break;
        }
    }

    // YOUKAI===================================================================
    private void YOUKAIDraw()
    {
        for(YOUKAI youkai:youkais) {
            if(youkai.IsAlive()){
                youkai.Draw();  // キャラ
                if(youkai.GetatkAnim().IsAnimNow())youkai.GetatkAnim().Draw();  //アニメーション
            }
        }
    }
    //==========================================================================
}
