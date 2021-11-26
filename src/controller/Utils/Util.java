/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
}
