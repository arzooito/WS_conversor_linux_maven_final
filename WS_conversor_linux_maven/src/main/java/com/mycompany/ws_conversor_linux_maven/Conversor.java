/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ws_conversor_linux_maven;


import com.bcl.easyConverter.EasyConverterWord.PDF2Word;
import com.mycompany.docsimplesample.Docx2Pdf;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Almerimatik
 */
@WebService(serviceName = "Conversor")
public class Conversor {
    
    static long idPDF = 0l;
    static long idDocx = 0l;
    final int MAXIMA = 100;
    String rtemp = "./temp/";
    
    /**
     * Convierte un archivo pdf en un archivo de texto editable
     * @param pdf archivo pdf tranformado a array de bytes
     * @param tipo del archivo de salida. Puede ser "doc","docx" o "rtf" 
     * @param password contraseña del pdf si la hubiera, en caso contrario dejar en blanco "".
     * @param desde página desde la que comienza la conversión. Usar -1 para empezar desde el principio
     * @param hasta página en la que acaba la conversion. Usar -1 para llegar hasta la última
     * @return array de bytes con el archivo transformado
     */
    @WebMethod(operationName = "Pdf2")
    public byte[] convertirPdf2(
            @WebParam(name = "pdf") byte[] pdf,
            @WebParam(name = "tipo")String tipo,
            @WebParam(name = "password")String password,
            @WebParam(name = "desde")int desde,
            @WebParam(name = "hasta")int hasta){
      
        long start = System.currentTimeMillis();
        
        PDF2Word conversor = new PDF2Word(); 
//        int rndm = (int)Math.floor(Math.random()*1000+1);
        byte[] byteArray;
        String rPdf = rtemp+ "pdf" + (idPDF) + ".pdf";
        String rSalida = rtemp+ "s" + (idPDF) + "."+ tipo;
        idPDF++;
        File temp = new File(rtemp);
        File tPdf = new File(rPdf);
        File tSalida = new File(rSalida);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        
        if(!temp.exists()){
            temp.mkdir();
        }
        
        try {
            fos = new FileOutputStream(rPdf);
            fos.write(pdf);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(fos != null){
                    fos.close();
                }else{
                    System.out.print("fis esta a null");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        conversor.jpegQuality = MAXIMA;
        conversor.ConvertToWord(rPdf, rSalida, password, desde, hasta);
        byteArray = new byte[(int)tSalida.length()];
        
        try {
            fis = new FileInputStream(tSalida);
            fis.read(byteArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                    System.out.print("fis esta a null");
                }
        }
        
        tPdf.delete();
        tSalida.delete();
        
        System.err.println("Time taken to Generate pdf  " + (System.currentTimeMillis() - start) + "ms");
          
        return byteArray;
      }
    
    /**
     * Convierte un archivo doc o docx en un archivo pdf
     * @param docx archivo doc o docx tranformado a array de bytes
     * @param tipo tipo de archivo. Los valores pueden ser doc o docx
     * @return array de bytes con el archivo pdf convertido
     */
    @WebMethod(operationName = "docx2")
    public byte[] docx2Pdf(@WebParam(name = "docx") byte[] docx,
                           @WebParam(name = "tipo") String tipo){
        
        long start = System.currentTimeMillis();
        byte[] byteArray;
        
        
        String rDocx = rtemp+ "word" + (idDocx) + "." + tipo;
        String rSalida = rtemp+ "sw" + (idDocx) + ".pdf";
        String rSalida2 = rtemp+ "swf" + (idDocx) + ".pdf";
        idDocx++;
        File temp = new File(rtemp);
        File tDocx = new File(rDocx);
        File tSalida = new File(rSalida);
        File tSalida2 = new File(rSalida2);
        FileOutputStream fos = null;
        FileInputStream fis = null;
        
        if(!temp.exists()){
            temp.mkdir();
        }
        
        try {
            fos = new FileOutputStream(rDocx);
            fos.write(docx);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(fos != null){
                    fos.close();
                }else{
                    System.out.print("fis esta a null");
                }
                
            } catch (IOException ex) {
                Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Docx2Pdf.convert2PDF(rDocx, rSalida);
        Docx2Pdf.clipPDF(rSalida, rSalida2);
        
        byteArray = new byte[(int)tSalida2.length()];
        
        try {
            fis = new FileInputStream(tSalida2);
            fis.read(byteArray);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Conversor.class.getName()).log(Level.SEVERE, null, ex);
                }
                }else{
                    System.out.print("fis esta a null");
                }
        }
        
        tDocx.delete();
        tSalida.delete();
        tSalida2.delete();

        System.err.println("Time taken to Generate pdf  " + (System.currentTimeMillis() - start) + "ms");
        return byteArray;
    }

    
}
