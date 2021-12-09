/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author Tiago Ventura
 */
public class Util {
    
    
    public static String getAno(Date data){
        return new SimpleDateFormat("yyyy").format(data);
    }
    
    public static Date setDataString(String data) throws ParseException{
        Date data1 = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        
        return data1;
    }
    
    public static String getDataStringSql(Date data){
        return new SimpleDateFormat("yyyy-MM-dd").format(data);
    }
    
    public static String getAnoEMes(Date data){
        return new SimpleDateFormat("yyyy/MM").format(data);
    }
    
   public static String obterMesEAnoEmString(LocalDateTime d){
        return d.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
   
   public static String obterMesEAnoEmString(LocalDate d){
        return d.format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
   
   public static String obterAnoMesDiaEmString(LocalDate d){
        return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
   
    public static String obterDataEmString(LocalDateTime d){
        return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    
    public static LocalDateTime formatarStringEmData(String d){
        return LocalDateTime.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
    
    public static LocalDate formatarStringEmDataBarra(String d){
        return LocalDate.parse(d, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    
    
    public static String obterDataEmStringImprimir(LocalDateTime d){
        return d.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH mm ss"));
    }
}
