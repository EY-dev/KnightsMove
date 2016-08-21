package com.ChessBoard;

import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * Created by EsaulovEA on 09.08.2016.
 */
public class StepBord {

    private HashSet<Point> myStepBoard = new HashSet();

    public StepBord(Point p, ChessBoard myChessBoard){
        HashSet<Point> tempStepBoard = new HashSet();
        for (int i = 1; i<3; i++){
            tempStepBoard.add(new Point(p.getX()-i, (char)((int)p.getY()-(3-i))));
            tempStepBoard.add(new Point(p.getX()-i, (char)((int)p.getY()+(3-i))));
            tempStepBoard.add(new Point(p.getX()+i, (char)((int)p.getY()-(3-i))));
            tempStepBoard.add(new Point(p.getX()+i, (char)((int)p.getY()+(3-i))));
        }
        myStepBoard.addAll(tempStepBoard.stream().filter(i -> myChessBoard.isAvailability(i)).collect(Collectors.toList()));
    }

    public int size() {return myStepBoard.size();}

    public Point nextElement(ChessBoard myChessBoard) {
        int count = 9;
        Point temp = null;
        for(Point i: myStepBoard){
            StepBord nextStep = new StepBord(i, myChessBoard);
            if (nextStep.size()<count){
                count = nextStep.size();
                temp = i;
            }
        }
        myStepBoard.remove(temp);
        return temp;
    }
    public Point getElement() {
        Point temp = null;
        for(Point i: myStepBoard){
            temp = i;
        }
        myStepBoard.remove(temp);
        return temp;
    }
}
