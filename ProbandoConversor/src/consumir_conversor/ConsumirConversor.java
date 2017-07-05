/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumir_conversor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlejandroSA
 */
public class ConsumirConversor {

    final static String NOPASS = "";
    final static int PRIMERA = -1;
    final static int FINAL = -1;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        //convertirPDF("./pruebas/atestado.pdf", "./pruebas/atestadoPRUEBA.docx", "docx");
        convertirDOCX("./pruebas/atestado.doc","./pruebas/atestadoPRUEBADOC.pdf");
    }
    

    private static void convertirPDF(String pdfEntrada, String archivoSalida, String tipo){
          
        File ficheroPdf = new File(pdfEntrada);
        File ficheroDocx = new File(archivoSalida);
        byte[] pdfArray = null;
        byte[] docxArray = null;
        FileOutputStream fos = null;
        
        try {
            pdfArray = Files.readAllBytes(ficheroPdf.toPath());
            docxArray = pdf2(pdfArray, tipo, NOPASS, PRIMERA, FINAL);
            fos = new FileOutputStream(ficheroDocx);
            if(docxArray != null)
                fos.write(docxArray);
            
        } catch (IOException ex) {
            Logger.getLogger(ConsumirConversor.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(fos != null)
                fos.close();
            }catch (IOException | NullPointerException ex) {
                Logger.getLogger(ConsumirConversor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    } 
    
    private static void convertirDOCX(String docxEntrada, String archivoSalida){
        
        File ficheroDocx = new File(docxEntrada);
        File ficheroPdf = new File(archivoSalida);
        byte[] pdfArray = null;
        byte[] docxArray = null;
        FileOutputStream fos = null;
        String tipo = null;
        
        if(docxEntrada.endsWith(".docx")){
            tipo = "docx";
        }else if(docxEntrada.endsWith(".doc")){
            tipo = "doc";
        }
        
        try {
            docxArray = Files.readAllBytes(ficheroDocx.toPath());
            pdfArray = docx2(docxArray,tipo);
            fos = new FileOutputStream(ficheroPdf);
            if(docxArray != null)
                fos.write(pdfArray);
            
        } catch (IOException ex) {
            Logger.getLogger(ConsumirConversor.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                if(fos != null)
                fos.close();
            }catch (IOException | NullPointerException ex) {
                Logger.getLogger(ConsumirConversor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private static byte[] pdf2(byte[] pdf, java.lang.String tipo, java.lang.String password, int desde, int hasta) {
        com.mycompany.ws_conversor_linux_maven.Conversor_Service service = new com.mycompany.ws_conversor_linux_maven.Conversor_Service();
        com.mycompany.ws_conversor_linux_maven.Conversor port = service.getConversorPort();
        return port.pdf2(pdf, tipo, password, desde, hasta);
    }

    private static byte[] docx2(byte[] docx, java.lang.String tipo) {
        com.mycompany.ws_conversor_linux_maven.Conversor_Service service = new com.mycompany.ws_conversor_linux_maven.Conversor_Service();
        com.mycompany.ws_conversor_linux_maven.Conversor port = service.getConversorPort();
        return port.docx2(docx, tipo);
    }

    
    
 
}
