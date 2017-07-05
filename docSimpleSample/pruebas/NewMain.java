/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.docsimplesample;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfArray;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfRectangle;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpProcessor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;






/**
 *
 * @author Almerimatik
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String entrada = "./pruebas/atestado.docx";
        String salida = entrada.replace(".docx", ".pdf");
        
        //String entrada = "./pruebas/atestado.pdf";
        //String salida = "./pruebas/atestadoFIX.pdf";
        
        //convert2PDF(entrada, salida);
        clipPDF("./pruebas/atestadoFIX.pdf", "./pruebas/atestadoFIX4.pdf");
   
    }
    
    
    public static void convert2PDF(String rutaEntrada, String rutaSalida){
        
        long start = System.currentTimeMillis();
 
        InputStream is = null;
        OutputStream os = null;
        WordprocessingMLPackage wordMLPackage = null;
        
        try {
            is = new FileInputStream(new File(rutaEntrada));
            os = new FileOutputStream(new File(rutaSalida));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            wordMLPackage = WordprocessingMLPackage.load(is);
        } catch (Docx4JException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        try {
            Docx4J.toPDF(wordMLPackage, os);
        } catch (Docx4JException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.err.println("Time taken to Generate pdf  " + (System.currentTimeMillis() - start) + "ms");
    }
    
    public static void tipexPDF(String rutaEntrada, String rutaSalida){
        PdfReader reader = null;
        try {
            reader = new PdfReader(rutaEntrada);
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, new FileOutputStream(rutaSalida));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<PdfCleanUpLocation> cleanUpLocations =
            new ArrayList<>();
        for (int pagina = 1; pagina <= reader.getNumberOfPages() ; pagina++){
            
//            cleanUpLocations.add(new PdfCleanUpLocation(
//                    pagina, new Rectangle(1f, 72f, 8f, 143f), BaseColor.WHITE));

            cleanUpLocations.add(new PdfCleanUpLocation(
                    pagina, new Rectangle(0f, 0f, 8f, 1000f), BaseColor.GREEN));
            cleanUpLocations.add(new PdfCleanUpLocation(
                    pagina, new Rectangle(8f, 0f, 13f, 1000f), BaseColor.RED));
        }
        
        PdfCleanUpProcessor cleaner =
            new PdfCleanUpProcessor(cleanUpLocations, stamper);
        try {
            cleaner.cleanUp();
        } catch (IOException | DocumentException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stamper.close();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        reader.close();
    }
    
    public static void cropPDF(String rutaEntrada, String rutaSalida){
        
        try {
            
            PdfReader reader = null;
            try {
                reader = new PdfReader(rutaEntrada);
            } catch (IOException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            int n = reader.getNumberOfPages();
            PdfDictionary pageDict;
            PdfRectangle rect = new PdfRectangle(8, 0, 1000, 1000);
            for (int i = 1; i <= n; i++) {
                pageDict = reader.getPageN(i);
                pageDict.put(PdfName.CROPBOX, rect);
            }
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(rutaSalida));
            stamper.close();
            reader.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static void clipPDF(String rutaEntrada, String rutaSalida){
        
       PdfReader reader = null;
        try {
            reader = new PdfReader(rutaEntrada);
        } catch (IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        PdfStamper stamper = null;
        try {
            stamper = new PdfStamper(reader, new FileOutputStream(rutaSalida));
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        int n = reader.getNumberOfPages();
        PdfDictionary page;
        PdfArray media;
        for (int p = 1; p <= n; p++) {
            page = reader.getPageN(p);
            media = page.getAsArray(PdfName.CROPBOX);
            if (media == null) {
                media = page.getAsArray(PdfName.MEDIABOX);
            }
            float llx = media.getAsNumber(0).floatValue() + 8;
            float lly = media.getAsNumber(1).floatValue() + 0;
            float w = media.getAsNumber(2).floatValue();
            float h = media.getAsNumber(3).floatValue();
            String command = String.format(Locale.ROOT,
                    "\nq %.2f %.2f %.2f %.2f re W n\nq\n",
                    llx, lly, w, h);
            stamper.getUnderContent(p).setLiteral(command);
            stamper.getOverContent(p).setLiteral("\nQ\nQ\n");
        }
        try {
            stamper.close();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        reader.close();
    }
}
