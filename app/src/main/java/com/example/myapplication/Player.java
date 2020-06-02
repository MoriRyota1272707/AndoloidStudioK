package com.example.myapplication;

public class Player {

    // ステータス===========================
    private int level;
    private int youkaiPower;
    private int initialYoukaiPower;
    private int levelUpYoukaiPower;
    private int maxYoukaiPower;

    int Level(){return level;}
    int GetYoukaiPower(){return youkaiPower;}
    //====================================

    //// Initialize ////============================
    void Init()
    {
        initialYoukaiPower = 1000;
        levelUpYoukaiPower = 20;
        level = 0;
        maxYoukaiPower = 9999;
        ReLoadYoukaiPower();
    }
    //==============================================
    // 妖力リロード==============================================================================
    void ReLoadYoukaiPower(){youkaiPower = initialYoukaiPower + (levelUpYoukaiPower * level);}
    //=========================================================================================

    // 召喚チェック========================================
    boolean IsCheckSummon(int SPCost)
    {
        return youkaiPower - SPCost >= 0;
    }
    //==================================================
    // 妖力使用==========================================
    void UseYoukaiPower(int YC)
    {
        youkaiPower -= YC;
    }
    //==================================================
    // 妖力回復==========================================
    void RecoveryYoukaiPower(int RYP)
    {
        youkaiPower += RYP;
        if(youkaiPower > maxYoukaiPower)youkaiPower = maxYoukaiPower;
    }
    //==================================================
}
