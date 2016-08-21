package com.ChessBoard;

/**
 * Created by EsaulovEA on 09.08.2016.
 */
public class Point {
    private int x;
    private char y;

    public Point(int x, char y){
        this.x = x; this.y = y;
    }
    public int getX(){
        return x;
    }
    public char getY(){
        return y;
    }
    public int getIntY(){
        return (int)y-(int)'a' + 1;
    }
    public boolean equals(Point p) {
        return p.x == x && p.y == y;
    }
    public void printPoint(){System.out.print(this.y +""+ this.x);
    }
}
