/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import chip.Chip;
/**
 *
 * @author Gui
 */
public class ChipFrame extends JFrame{
    
    private ChipPanel panel;
    
    public ChipFrame(Chip c){
        setPreferredSize(new Dimension(640,320));
        pack();
        setPreferredSize(new Dimension(640 + getInsets().left + getInsets().right,320 + getInsets().top + getInsets().bottom));
        panel = new ChipPanel(c);
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("P. G. G. Costa CHIP-8 EMULATOR");
        pack();
        setVisible(true);
        
    }
    
}
