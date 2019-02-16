/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package confusion;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Polgn
 */
public class Confusion {
    private final String file_in;
    private final String file_out;
    private String text_file_original;
    private String text_file_mod;
    private final int key;
    private final int mode;
    
    /*
    * This function encrypt file_in into file_out
    */
    private void encrypt(){
        try{
        byte[] text_file = Files.readAllBytes(Paths.get(this.file_in));
        byte aux_key = (byte) this.key;
        
        for(int i = 0; i<text_file.length;i++){
            text_file[i] = (byte) (text_file[i] + aux_key);
        }
        
        Files.write(Paths.get(this.file_out), text_file);
        
        }catch(Exception e){
            System.err.println("ERROR: can not encrypt.");
        }
    }
    
    /*
    * This function decrypt file_in into file_out
    */
    private void decrypt(){
        try{
        byte[] text_file = Files.readAllBytes(Paths.get(this.file_in));
        byte aux_key = (byte) this.key;
        
        for(int i = 0; i<text_file.length;i++){
            text_file[i] = (byte) (text_file[i] - aux_key);
        }
        
        Files.write(Paths.get(this.file_out), text_file);
        }catch(Exception e){
            
        }
    }
    
    /*
    * This function select encrypt or decrypt.
    */
    private boolean select_mode(){
        if(this.mode < 1 && this.mode > 2){
            return false;
        }
        else{
            if(this.mode == 1){
                this.encrypt();
            }
            else{
                this.decrypt();
            }
            return true;
        }
    }
    
    /*
    * Constructor
    */
    public Confusion(String file_in, String file_out, int key, int mode){
        this.file_in = file_in;
        this.file_out = file_out;
        this.key = key;
        this.mode = mode;
        
        if(!this.select_mode()){
            System.exit(0);
        }
    }
}
