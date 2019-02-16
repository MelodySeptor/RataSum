/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Polgn
 */
public class Aes {
    private final String num_bytes;
    private final String file_in;
    private final String file_out;
    private final String method;
    private final int mode;
    private byte[] secret_key;
    
    /*
    * This function save the num_bytes on a new file called num_bytes.txt.
    */
    private void save_secret_key(){
        try{
            File new_file = new File ("./key.txt");
            new_file.getParentFile().mkdirs();
            new_file.createNewFile();
            
            FileOutputStream fos = new FileOutputStream("./key.txt");
            fos.write(this.secret_key);
            fos.close();
            
        }catch(Exception e){
            System.out.println("ERROR: can not save key");
        }
    }
    
    /*
    * Take the num_bytes stored on the file and put on the @var secret_key.
    */
    private void asign_secret_key(){
        try{
            Path path = Paths.get("./key.txt");
            this.secret_key = Files.readAllBytes(path);
            
        }catch(IOException e){
            System.out.println("ERROR: can not read the key");
        }
    }
    
    /*
    * This function encrypt text from file
    */
    private void encrypt(){
        try{
            String FileName = this.file_out;

            KeyGenerator KeyGen = KeyGenerator.getInstance(this.method.toUpperCase());
            KeyGen.init(Integer.parseInt(this.num_bytes));//256 best
            
            SecretKey SecKey = KeyGen.generateKey();
            this.secret_key = SecKey.getEncoded();
            this.save_secret_key();
      
            Cipher AesCipher = Cipher.getInstance(this.method.toUpperCase());
            byte[] byteText = Files.readAllBytes(Paths.get(this.file_in));

            AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
            byte[] byteCipherText = AesCipher.doFinal(byteText);
            Files.write(Paths.get(FileName), byteCipherText);
            
        } catch(Exception e){
            System.err.println("ERROR: can not encrypt files");
        }
    }
    
    /*
    * This function decrypt text from file
    */
    private void decrypt(){
        this.asign_secret_key();
        SecretKey key = new SecretKeySpec(this.secret_key, this.method.toUpperCase());
        
        try{
            Cipher AesCipher = Cipher.getInstance(this.method.toUpperCase());
            byte[] cipherText = Files.readAllBytes(Paths.get(this.file_in));

            AesCipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytePlainText = AesCipher.doFinal(cipherText);
            Files.write(Paths.get(this.file_out), bytePlainText);
            
        }catch(Exception e){
            System.err.println("ERROR: can not decrypt");
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
    public Aes(String num_bytes, String file_in, String file_out, int mode, String method){
        this.num_bytes = num_bytes;
        this.file_in = file_in;
        this.file_out = file_out;
        this.mode = mode;
        this.method = method;
        
        if(!this.select_mode()){
            System.err.println("ERROR: Mode only can be 1 or 2");
        }
    }
}
