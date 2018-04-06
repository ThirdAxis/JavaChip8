package com.tests;


import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    JPanel[][] cells = new JPanel[32][64];

    public Window(){

        this.setLayout(new GridLayout(32,64));


        for (int i = 0; i<32; i++){
            for (int j = 0; j<64; j++){
                cells[i][j] = new JPanel();
                cells[i][j].setBackground(Color.BLACK);
                this.add(cells[i][j]);
            }
        }

        this.setTitle("Emu test");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(576, 288);
        this.setVisible(true);
    }

    public void toggleCell(int x, int y){
        if(cells[x][y].getBackground() == Color.BLACK){
            cells[x][y].setBackground(Color.WHITE);
        }
        else if(cells[x][y].getBackground() == Color.WHITE){
            cells[x][y].setBackground(Color.BLACK);
        }
    }

    public void clear(){
        for (int i = 0; i<32; i++){
            for (int j = 0; j<64; j++){
                cells[i][j].setBackground(Color.BLACK);
            }
        }
    }


}
