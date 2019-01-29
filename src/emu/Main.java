/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emu;

import chip.Chip;

/**
 *
 * @author Gui
 */
// stop at this part:
//https://youtu.be/LDcJ8_gG4Jc?list=PL5PyurErl12czoLyYD8za68d61T_OZsP2
public class Main {
    public static void main(String[] args){
        Chip c = new Chip();
        c.init();
        //c.run();
        ChipFrame frame = new ChipFrame(c);
    }
    
}
