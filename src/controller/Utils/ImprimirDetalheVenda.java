/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiago Ventura
 */
public class ImprimirDetalheVenda {
    //System.getProperty("user.name")
    private String FILE = "C:\\Users\\"+System.getProperty("user.name")+"\\Documents\\Venda ";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
    private static final Font subFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.HELVETICA, 12, Font.UNDERLINE);

    
    
    public void imprimirDetalheVenda(String titlo, String nomeCliente, String data, String valorTotal, List<List<String>> compras) throws FileNotFoundException, DocumentException{
        LocalDateTime dateTime = LocalDateTime.now();
        
        FILE += Util.obterDataEmStringImprimir(dateTime) +".pdf";
        Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            document.setPageSize(PageSize.A4);
            
            Paragraph preface = new Paragraph();
            preface.add(new Paragraph(titlo, subFont));
            preface.add(new Paragraph("\n"));          
            preface.add(new Phrase("Nome Cliente:", smallBold)); preface.add(" "+nomeCliente);
            
            preface.add(new Paragraph("\n"));          
            preface.add(new Phrase("Data de venda:", smallBold)); preface.add(" "+data);
            
            preface.add(new Paragraph("\n"));          
            preface.add(new Phrase("Produtos vendidos:", smallBold));
            
            preface.add(new Paragraph("\n"));
            preface.add(new Paragraph("\n"));
            
            PdfPTable table = new PdfPTable(compras.get(0).size());
            
            //Titlo das colunas
            for(String c: compras.get(0)){
                PdfPCell c1 = new PdfPCell(new Phrase(c));
                c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(c1);
            }
            
            compras.remove(0);
            
            table.setHeaderRows(1);
            
            for(List<String> listaCompras: compras){
                for(String c: listaCompras){
                    table.addCell(c);
                }
            }
                        
            document.add(preface);
            document.add(table);
            
            preface = new Paragraph();
            preface.add(new Paragraph("\n"));          
            preface.add(new Phrase("Valor total vendido:", smallBold)); preface.add(" "+valorTotal +" Kz");
        
            
            document.add(preface);
            document.close();
            
            try {
            Desktop.getDesktop().open(new File(FILE));
            } catch (IOException ex) {
                System.out.println("Error:"+ex);
            }
    }
    
}
