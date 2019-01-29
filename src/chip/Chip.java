/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chip;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author Gui
 */
public class Chip {
    private char[] memory; //cartridge memory
    private char[] V; //registers
    private char I; //pointer
    private char pc;
    
    private char stack[];
    private int stackPointer;
    
    private int delay_timer;
    private int sound_timer;
    
    private byte[] keys;
    
    private byte[] display;
    
    private boolean needRedraw;
    
    public void init(){
        memory = new char[4096];
        V = new char[16];
        I = 0x0;
        pc = 0x200;
        stack = new char[16];
        stackPointer = 0;
        
        delay_timer = 0;
        sound_timer = 0;
        
        keys = new byte[16];
        display = new byte[64 * 32]; // use 640 x 320
        display[0] = 1;
        display[64] = 1;
        
        needRedraw = false;
    }
    
    public void run(){
        //fetch opcode
        char opcode = (char)((memory[pc]<<8) | memory[pc+1]);
        System.out.println(Integer.toHexString(opcode)+": ");
        //decode
        switch(opcode & 0xF000){
            
            case 0x1000:
                break;

            case 0x2000:
                char address =(char)(opcode & 0x0FFF);
                stack[stackPointer] = pc;
                stackPointer++;
                pc = address;
                break;
                
            case 0x3000:
                break;                

            case 0x6000:
                char vreg = (char)(opcode & 0x0F00);                
                V[vreg>>8] = (char)(opcode & 0x00FF);
                pc+=2;
                break;                   
                
            case 0x7000:
                break;                
                
            case 0x8000:
                switch(opcode & 0x000F){
                    case 0x0000: //8XY0: Sets vx to te value of VY
                        default:
                            System.err.println("Unsupported opcode!");
                            System.exit(0);
                            break;
                }
                break;
            
            case 0xA000:
                I = (char)(opcode & 0x0FFF);
                pc+=2;
                break;
            default:
                System.err.println("Unsupported opcode!");
                System.exit(0);
                break;
        }
        //execute
    }
    public byte[] getDisplay(){
        return display;
    }

    public boolean needsRedraw() {
        return needRedraw;
    }

    public void removeDrawFlag() {
        needRedraw = false;
    }

    public void loadProgram(String file) {
        DataInputStream input = null;
        try{input = new DataInputStream(new FileInputStream(new File(file)));
        
        int offset = 0;
        while(input.available()>0){
            memory[0x200 + offset] = (char)(input.readByte() & 0xFF);
            offset++;
        }
        
        }catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }finally{
            if(input != null){
                try{input.close();
                }catch(IOException ex){
                    
                }
            }
        }
    }
}
