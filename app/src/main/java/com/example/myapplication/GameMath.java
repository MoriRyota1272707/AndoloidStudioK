package com.example.myapplication;

public class GameMath {

    public float GetDist(Vector2 p1,Vector2 p2){return (float)Math.sqrt((p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y));}
    //public double GetAngle(Vector2 p1,Vector2 p2){return (Math.atan2(y - pos.y,x - pos.x)) * 180/Math.PI;}
}
