package com.Solution;

import com.ChessBoard.Point;
import java.util.ArrayList;

/**
 * Created by EsaulovEA on 12.08.2016.
 */
public class Solution {
    private ArrayList<Point> sequenceMove = new ArrayList();
    public void printSolution(){
        sequenceMove.forEach(Point::printPoint);
    }
    void add(Point position) {
        sequenceMove.add(position);
    }
    boolean remove(Point position) {
        for(Point i: sequenceMove) if (i.equals(position)) return sequenceMove.remove(i);
        return false;
    }
    public Solution getSolution(){
        Solution tempMove = new Solution();
        sequenceMove.forEach(tempMove::add);
        return tempMove;
    }
    public Point getPointSolution(int i){return sequenceMove.get(i);
    }
    public int getSizeSolution(){return sequenceMove.size();
    }
}
