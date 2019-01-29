/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chip;

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
        
        
    }
    
    public void run(){
        //fetch opcode
        char opcode = (char)((memory[pc]<<8) | memory[pc+1]);
        System.out.println(Integer.toHexString(opcode)+": ");
        //decode
        switch(opcode & 0xF000){
            
            case 0x8000:
                switch(opcode & 0x000F){
                    case 0x0000: //8XY0: Sets vx to te value of VY
                        default:
                            System.err.println("Unsupported opcode!");
                            System.exit(0);
                            break;
                }
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
}
