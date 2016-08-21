package com.ConcludeSearchTime;

import com.DrowWindow.FormWindow;
import com.Solution.Pathfinder;

/**
 * Created by EsaulovEA on 12.08.2016.
 */
public class ConcludeSearchTime extends Thread {
    private int sizeBoard = 5;
    public ConcludeSearchTime(int n){sizeBoard = n;
    }
    public void run() {
        Pathfinder p = new Pathfinder(sizeBoard);
        p.start();
        while (!p.getEndWork()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FormWindow.labelTime.setText(String.valueOf(p.getTime()));
            FormWindow.labelCountSolution.setText(String.valueOf(p.getSolutionSize()));
        }
    }
}
