package com.ChessBoard;

import java.util.HashSet;

/**
 * Created by EsaulovEA on 09.08.2016.
 */
public class ChessBoard {
    private HashSet<Point> myChessBoard = new HashSet();

    public ChessBoard(int n){
        for (int i = 1; i < n + 1; i++){
            for (int j = 0; j < n; j++ )
                myChessBoard.add(new Point(i,(char)(j+(int)('a'))));
        }
    }
    boolean isAvailability(Point p){
        for(Point i: myChessBoard) if (i.equals(p)) return true;
        return false;
    }

    public boolean remove(Point position) {
        for(Point i: myChessBoard) if (i.equals(position)) return myChessBoard.remove(i);
        return false;
    }

    public int size() {return myChessBoard.size();}

    public void add(Point position) {myChessBoard.add(position);}
}
