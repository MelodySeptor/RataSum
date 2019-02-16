/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transposing;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Polgn
 */
public class Transposing {
    private final String file_in;
    private final String file_out;
    private final int key;
    private final int mode;
    
    /*
    * This function encrypt file
    */
    private void encrypt(){
        try{
            byte[] text_file = Files.readAllBytes(Paths.get(this.file_in));
            byte[] aux_text = text_file;
            
            for (int i = 0; i < text_file.length / 2; i++) {
                byte temp = text_file[i];
                text_file[i] = text_file[text_file.length - 1 - i];
                text_file[text_file.length - 1 - i] = temp;
            }

            Files.write(Paths.get(this.file_out), aux_text);
            
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("ERROR: can not encrypt.");
        }
    }
    
    /*
    * This function decrypt file
    */
    private void decrypt(){
        try{
            byte[] text_file = Files.readAllBytes(Paths.get(this.file_in));
            byte[] aux_text = text_file;
            
            for (int i = 0; i < text_file.length / 2; i++) {
                byte temp = text_file[i];
                text_file[i] = text_file[text_file.length - 1 - i];
                text_file[text_file.length - 1 - i] = temp;
            }

            Files.write(Paths.get(this.file_out), aux_text);
            
        }catch(Exception e){
            System.err.println("ERROR: can not encrypt.");
        }
    }
    
    /*
    * This function select mode to user input.
    * @return true if all its okay.
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
    public Transposing(String file_in, String file_out, int key, int mode){
        this.file_in = file_in;
        this.file_out = file_out;
        this.key = key;
        this.mode = mode;
        
        if(!this.select_mode()){
            System.exit(0);
        }
    }
}
