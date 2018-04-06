package com.tests;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class Cpu {

    final int memsize = 4096;
    final int startaddr = 512;
    final int vitessecpu = 4;
    final int fps = 16;


    //{index, masque}
    int[][] myopid = new int[][]{
            {0x0FFF, 0x0000},
            {0x00E0, 0xFFFF},
            {0x00EE, 0xFFFF},
            {0x1000, 0xF000},
            {0x2000, 0xF000},
            {0x3000, 0xF000},
            {0x5000, 0xF00F},
            {0x6000, 0xF000},
            {0x7000, 0xF000},
            {0x8000, 0xF00F},
            {0x8001, 0xF00F},
            {0x8002, 0xF00F},
            {0x8003, 0xF00F},
            {0x8004, 0xF00F},
            {0x8005, 0xF00F},
            {0x8006, 0xF00F},
            {0x8007, 0xF00F},
            {0x800E, 0xF00F},
            {0x9000, 0xF00F},
            {0xA000, 0xF000},
            {0xB000, 0xF000},
            {0xC000, 0xF000},
            {0xD000, 0xF000},
            {0xE09E, 0xF0FF},
            {0xE0A1, 0xF0FF},
            {0xF007, 0xF0FF},
            {0xF00A, 0xF0FF},
            {0xF015, 0xF0FF},
            {0xF018, 0xF0FF},
            {0xF01E, 0xF0FF},
            {0xF029, 0xF0FF},
            {0xF033, 0xF0FF},
            {0xF055, 0xF0FF},
            {0xF065, 0xF0FF}
    };



    boolean continuer;
    int[] memoire = new int[memsize];
    int[] V = new int[16];
    int I;
    int pile[] = new int[16];
    int nbrsaut;
    int compteurSon;
    int compteurJeu;
    int pc;
    Window win = new Window();

    public Cpu() {
        this.I = 0;
        this.nbrsaut = 0;
        this.compteurSon = 0;
        this.compteurJeu = 0;
        this.pc = startaddr;
        this.continuer = true;

        for(int i = 0; i<memsize; i++){
            this.memoire[i] = 0;
        }
        for(int i = 0; i<16; i++){
            this.V[i] = 0;
            this.pile[i] = 0;
        }

        cycle();
    }

    public void decompter(){
        if(compteurJeu > 0){
            compteurJeu--;
        }
        if(compteurSon > 0){
            compteurSon--;
        }
    }

    public void cycle(){
        while(continuer){
            win.toggleCell(30,30);

            try {
                TimeUnit.MILLISECONDS.sleep(16);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    public int lireOpcode(){
        return (memoire[pc]<<8)+memoire[pc+1];
    }
    public void interpreterOpcode(int opcode){
        int b4 = getAction(opcode);
        int X=(opcode&(0x0F00))>>8;  //X
        int Y=(opcode&(0x00F0))>>4;  //Y
        int b1=(opcode&(0x000F));

        switch (b4){
            case 0:
                //0x0FFF
            case 1:
                //0x00E0
                win.clear();
            case 2:
                //0x00EE
            case 3:
                //0x1000
            case 4:
                //0x2000
            case 5:
                //0x3000
            case 6:
                //0x4000
            case 7:
                //0x5000
            case 8:
                //0x6000
            case 9:
                //0x7000
            case 10:
                //0x8000
            case 11:
                //0x8001
                V[X] = V[X]|V[Y];
            case 12:
                //0x8002
                V[X] = V[X]&V[Y];
            case 13:
                //0x8003
                V[X] = V[X]^V[Y];
            case 14:
                //0x8004
            case 15:
                //0x8005
            case 16:
                //0x8006
            case 17:
                //0x8007
            case 18:
                //0x800E
            case 19:
                //0x9000
            case 20:
                //0xA000
            case 21:
                //0xB000
            case 22:
                //0xC000
            case 23:
                //0xD000
            case 24:
                //0xE09E
            case 25:
                //0xE0A1
            case 26:
                //0xF007
            case 27:
                //0xF00A
            case 28:
                //0xF015
            case 29:
                //0xF018
            case 30:
                //0xF01E
            case 31:
                //0xF029
            case 32:
                //0xF033
            case 33:
                //0xF055
            case 34:
                //0xF065
        }
    }

    public int getAction(int opcode) {
        int action = 0;
        for (int i = 0; i < myopid.length; i++) {
            int resultat = myopid[i][1] & opcode;
            if (resultat == myopid[i][0]) {
                action = i;
                break;
            }
        }
        return action;
    }

    public void dessiner(){

    }
}
