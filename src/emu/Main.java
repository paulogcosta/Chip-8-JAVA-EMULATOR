/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emu;

import chip.Chip;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gui
 */

public class Main extends Thread{
    
    private Chip chip8;
    private ChipFrame frame;
    
    public Main(){
        chip8 = new Chip();
        chip8.init();
        System.out.println(System.getProperty("user.dir"));
        chip8.loadProgram("./pong2.c8");
        frame = new ChipFrame(chip8);    
    }
    
    public void run(){
        while(true){
            chip8.run();
            if(chip8.needsRedraw()){
                frame.repaint();
                chip8.removeDrawFlag();
            }
            try {
                Thread.sleep(1600);
            } catch (InterruptedException ex) {
                //none
            }
        }
        
    }
    
    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }
    
}
