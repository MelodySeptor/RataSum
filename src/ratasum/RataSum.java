/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratasum;

import aes.Aes;
import confusion.Confusion;
import java.io.File;
import transposing.Transposing;

/**
 *
 * @author Polgn
 */
public class RataSum {
    //private final int MAX_INPUTS = 5;
    private String type;
    private String self_carac;
    private String file_in;
    private String file_out;
    private int mode;
    
    /*
    * This function process all inputs and check if all is correct.
    * @param args take parameters from main.
    */
    private void process_input(String[] args){
        try{
            if(args[0].equals("aes")||args[0].equals("des")||args[0].equals("confusion")||args[0].equals("transposing")){
                this.type = args[0];
                this.self_carac = args[1];
                if(new File(args[2]).exists()){
                    this.file_in = args[2];
                    this.file_out = args[3];
                    this.mode = Integer.parseInt(args[4]);
                }
                else{
                    System.err.println("ERROR: file to encrypt or decrypt doesent exists.");
                    System.exit(0);
                }
            }
            else{
                System.err.println("ERROR: The supported types are: aes, des, confusion (like cesar), transposing");
                System.exit(0);
            }
        }catch(Exception e){
            System.err.println("ERROR: input must be: type self_carac (aes key lenght, confusion key, transposing key, file_in file_out 1(Decrypt) 2(encrypt)");
            System.exit(0);
        }
    }
    
    /*
    * This function create a new object from the @var type to this class.
    */
    private void select_type(){
        switch(this.type){
            case "aes":
            case "des": new Aes(this.self_carac, this.file_in, this.file_out, this.mode, this.type); break;
            case "confusion": new Confusion(this.file_in, this.file_out, Integer.parseInt(this.self_carac), this.mode); break;
            case "transposing": new Transposing(this.file_in, this.file_out, Integer.parseInt(this.self_carac), this.mode);break;
        }
    }
    
    /*
    * Constructor
    * @param args its an arguments like; type, self_carac, file_in, file_out, mode
    */
    public RataSum(String[] args){
        this.process_input(args);
        this.select_type();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new RataSum(args);
    }
    
}
