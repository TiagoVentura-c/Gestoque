package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Venda {

    
 
    private int id;
    private String nomeCliente;
    private float valorTotal;
    private Date data;

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
        
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public String getDataFormatada(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data);
    }
    
    public String getHoraFormatada(){
        return new SimpleDateFormat("HH:mm").format(data);
    }
    
    public String getAnoEMes(){
        return new SimpleDateFormat("yyyy/MM").format(data);
    }
    
    public String getAno(){
        return new SimpleDateFormat("yyyy").format(data);
    }
    
    public String getDataStringSql(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(data);
    }
   
    
    public void setDataString(String data) throws ParseException{
        this.data = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(data);
    }
    
}
