package com.Solution;


import com.ChessBoard.ChessBoard;
import com.ChessBoard.StepBord;
import com.ChessBoard.Point;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by EsaulovEA on 12.08.2016.
 */

public class Pathfinder extends Thread {
    private static Solution sequenceMove;
    private static ArrayList<Solution> sequenceMoves = new ArrayList();
    private static boolean endWork = false;
    private static Calendar startData;
    private int sizeBoard = 5;

    public Pathfinder(int n){sizeBoard = n;
    }
    public void run() {
        startData  = Calendar.getInstance();
        ChessBoard myChessBoard = new ChessBoard(sizeBoard);
        sequenceMove = new Solution();
        knightStepBoard(new Point(1,'a'),myChessBoard);
        endWork = true;
    }
    public boolean getEndWork(){return endWork;
    }
    public String getTime(){
        String tempTime = "";
        Calendar CurrentData = Calendar.getInstance();
        Calendar calculate = Calendar.getInstance();
        calculate.setTime(new Date(CurrentData.getTime().getTime() - startData.getTime().getTime()));
        if (((calculate.get(Calendar.DAY_OF_YEAR) - 1) * 24) + (calculate.get(Calendar.HOUR_OF_DAY) - 3)>0){ tempTime = ((calculate.get(Calendar.DAY_OF_YEAR) - 1) * 24) + (calculate.get(Calendar.HOUR_OF_DAY) - 3) + "ч ";}
        if (calculate.get(Calendar.MINUTE)>0) {tempTime +=calculate.get(Calendar.MINUTE)+"м ";}
        tempTime += calculate.get(Calendar.SECOND)+"с";
        return tempTime;
    }
    public int getSolutionSize(){return sequenceMoves.size();
    }
    public static Solution getSolution(int n){
        Solution temp = sequenceMoves.get(n);
        //System.out.print(n+1 + " решение: ");
        //temp.printSolution();
        return temp;
    }
    private static boolean knightStepBoard(Point position, ChessBoard myChessBoard){
        sequenceMove.add(position);
        myChessBoard.remove(position);

        if (myChessBoard.size()>0) {
            StepBord nextStep = new StepBord(position, myChessBoard);
            while (nextStep.size()>0) {
                if ((!knightStepBoard(nextStep.nextElement(myChessBoard), myChessBoard)) && nextStep.size()==0) {
                    sequenceMove.remove(position);
                    myChessBoard.add(position);
                }
            }
            sequenceMove.remove(position);
            myChessBoard.add(position);
            return false;
        }
        else {
            //нашли решение, выводим на экран
            Solution temp = sequenceMove.getSolution();
            sequenceMoves.add(temp);
            sequenceMove.remove(position);//для поиска других решений
            myChessBoard.add(position);
            return true;
        }
    }
}
