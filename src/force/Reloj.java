/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package force;

/**
 *
 * @author Miguel Angel
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

public class Reloj extends Thread {
    private static String meses[]={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"}; 
    private static String semana[]={"Lunes,","Martes,","Miércoles,","Jueves,","Viernes,","Sabado,","Domingo,"}; 
    private JLabel lbl;
    Calendar calendario = new GregorianCalendar();
    
    public Reloj (JLabel lbl)
    {
        this.lbl=lbl;
    }
    
    public Reloj (){}
    
    public void run(){
        Calendar calendario = new GregorianCalendar();
        String ampm;
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        while(true)
        {
        Date hoy=new Date();
        SimpleDateFormat s=new SimpleDateFormat("hh:mm:ss");
        lbl.setText(s.format(hoy)+" "+ampm);
        try 
        {
        sleep(1000);
        }catch(Exception ex){}
        }
    }   
    
    public void fecha(JLabel lbl_Fecha){
        // FECHA DEL SISTEMA
        int diaL = calendario.getTime().getDay();
        int dia = calendario.get(calendario.DAY_OF_MONTH);
        int mes = calendario.get(calendario.MONTH)+1;
        int año = calendario.get(calendario.YEAR);
        lbl_Fecha.setText(String.valueOf(semana[diaL])+" "+ String.valueOf(dia)+" de "+meses[mes]+" del "+String.valueOf(año));
    }
}
