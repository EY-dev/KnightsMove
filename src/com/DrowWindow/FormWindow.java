package com.DrowWindow;

import com.ChessBoard.ChessBoard;
import com.ConcludeSearchTime.ConcludeSearchTime;
import com.Solution.Pathfinder;
import com.Solution.Solution;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by EsaulovEA on 15.08.2016.
 */
public class FormWindow extends JFrame {

    private static JLabel label1 = new JLabel("Время потраченное на поиск решений: ");
    private static JLabel label2 = new JLabel("Колличество найденных решений: ");
    private static JLabel label3 = new JLabel("Введите номер пути: ");
    public static JLabel labelTime = new JLabel("");
    public static JLabel labelCountSolution = new JLabel("");
    private JTextField input = new JTextField("");
    private JPanel panel_window;
    private PaintChessBoard canv;
    private NextPaintPath paintStep;
    private int countPressButton = 0;

    private FormWindow() {
        super("Ход конем");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canv = new PaintChessBoard();
        panel_window.setLayout(new GridLayout(1,2,0,0));
        PaintChessBoard canv = new PaintChessBoard();

        JPanel panel_form = new JPanel();
        panel_form.setLayout(new GridLayout(4,1,0,0));

        JPanel panel_Row1 = new JPanel();
        panel_Row1.setLayout(new BorderLayout());
        JPanel panel_Row2 = new JPanel();
        panel_Row2.setLayout(new BorderLayout());
        JPanel panel_Row3 = new JPanel();
        panel_Row3.setLayout(new BorderLayout());

        panel_Row1.add(label1,BorderLayout.WEST);
        panel_Row1.add(labelTime);
        panel_Row2.add(label2,BorderLayout.WEST);
        panel_Row2.add(labelCountSolution);
        panel_Row3.add(label3,BorderLayout.WEST);
        JPanel panel_Row4 = new JPanel();
        panel_Row4.add(input);

        input.setPreferredSize(new Dimension(150,30));
        input.setLocation(0,100);
        JButton button = new JButton("Построить");
        button.setPreferredSize(new Dimension(150,30));
        ActionListener actionListener = new ButtonEventListener();
        button.addActionListener(actionListener);
        panel_Row4.add(button);

        panel_form.add(panel_Row1);
        panel_form.add(panel_Row2);
        panel_form.add(panel_Row3);
        panel_form.add(panel_Row4);

        panel_window.add(canv);
        panel_window.add(panel_form);

        setContentPane(panel_window);
        setBounds(100,100,740,440);
        setResizable(false);
    }
    public static void main(String[] args) throws InterruptedException {
        ConcludeSearchTime solutions = new ConcludeSearchTime(8);
        solutions.start();
        FormWindow app = new FormWindow();
        app.setVisible(true);

    }
    private class NextPaintPath extends Thread{
        private Solution paintPath;
        NextPaintPath(Solution s){
            paintPath = s.getSolution();
        }
        public void run() {
            System.out.println();
            ChessBoard paintBoard = new ChessBoard(8);
            for(int i=0; i < paintPath.getSizeSolution(); ++i){
                paintPath.getPointSolution(i).printPoint();
                paintBoard.remove(paintPath.getPointSolution(i));
                canv.setSolution(paintPath,i+1, paintBoard);
                getContentPane().repaint();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    private class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            ++countPressButton;
            String size = labelCountSolution.getText();
            String inputText = input.getText();
            if (Integer.valueOf(size)<Integer.valueOf(inputText)){
                String message = "";
                message += "Номера с таким решением не найдено\n";
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
            else {
                canv = new PaintChessBoard();
                Solution path = Pathfinder.getSolution(Integer.valueOf(inputText)-1);
                if (countPressButton == 1){
                    paintStep = new NextPaintPath(path);
                    paintStep.start();
                }
                else{
                    paintStep.stop();
                    paintStep = new NextPaintPath(path);
                    paintStep.start();
                }

            }
        }
    }
}
