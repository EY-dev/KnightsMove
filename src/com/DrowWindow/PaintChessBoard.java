package com.DrowWindow;

import com.ChessBoard.ChessBoard;
import com.ChessBoard.Point;
import com.ChessBoard.StepBord;
import com.Solution.Pathfinder;
import com.Solution.Solution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by EsaulovEA on 12.08.2016.
 */
public class PaintChessBoard extends JComponent {
    private static int solution;
    private static Solution pathPaint;
    private static ChessBoard myBoard;
    private BufferedImage image;
    public PaintChessBoard(){
        try {
            image = ImageIO.read(new File("src/images/black.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        solution = 0;
    }
    public void setSolution(int n){
        solution = n;
    }
    public void setSolution(Solution patch, int index, ChessBoard board){
        solution = index;
        pathPaint = patch.getSolution();
        myBoard = board;
    }
    public void paintComponent(Graphics gr) {
        super.paintComponents(gr);

        Graphics2D g = (Graphics2D) gr;
        paintBoard(g);
        paintAxis(g);
        g.setColor(Color.BLUE);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        if (solution!=0){
            paintPathPoint(g, pathPaint, solution);
        }
    }
    private void paintAxis(Graphics2D g){
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        for (int i = 1; i < 9; i++){
            g.drawString(9-i+"", 20, 40*i+27);
        }
        for (int i = 0; i < 8; i++){
            g.drawString(String.valueOf((char)(i+(int)'a')), 40*(i+1)+15, 390);
        }
    }
    private void paintBoard(Graphics2D g){
        for (int i = 1; i < 9; i++){
            for (int j = 1; j < 9; j++) {
                int colorIndex = ((j%2)+i)%2;
                if (colorIndex>0) colorIndex = 255;
                else colorIndex = 100;
                g.setColor(new Color(colorIndex,colorIndex,colorIndex));
                g.draw(new Rectangle2D.Float(j * 40, i * 40, 40, 40));
                g.fillRect(j * 40, i * 40, 40, 40);
            }
        }
    }
    private void paintPath (Graphics2D g, int n){
        Solution path = Pathfinder.getSolution(n-1);
        g.setColor(Color.BLUE);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        for(int i=0; i < path.getSizeSolution(); ++i){
            Point p = path.getPointSolution(i);
            g.drawString(String.valueOf(i+1), 40*p.getIntY()+15, 40*(9 - p.getX())+27 );
            repaint();
        }
    }
    private void paintPathPoint (Graphics2D g, Solution path, int index){
        for(int i=0; i < index; ++i){
            Point p = path.getPointSolution(i);
            g.drawString(String.valueOf(i+1), 40*p.getIntY()+15, 40*(9 - p.getX())+27 );
        }
        Point p = path.getPointSolution(index-1);
        g.drawImage(image,40*p.getIntY(),40*(9 - p.getX()),40,40,null);
        StepBord nextStep = new StepBord(p,myBoard);
        while (nextStep.size()>0){
            Point print = nextStep.getElement();
            g.setColor(new Color(255,0,0,50));
            g.fillRect(40*print.getIntY(), 40*(9 - print.getX()), 40, 40);
            g.setColor(Color.RED);
            g.draw(new Rectangle2D.Float(40*print.getIntY(), 40*(9 - print.getX()), 40, 40));

        }
    }
}
