/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package calendario;

import conexionBase.conexionBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CarlosEfrain
 */
public class conexionCalendario extends conexionBase{
    public conexionCalendario(){
    conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456");}
    /*SELECT federado.nombres,cargas.idfede,entrenamiento.idEntreno, entrenamiento.tipo, cargas.fecha, cargas.peso
from federado, cargas, entrenamiento
where federado.idfederado=cargas.identrena and entrenamiento.idEntreno=cargas.idfede and entrenamiento.tipo="Press banca" and cargas.fecha="2014/11/24"
*/
    public void calendario4Dias(JTable tabla,String categoria, String f1,String f2,String f3,String f4)
    {String [] columna ={"Cedula","Nombres",f1,f2,f3,f4};
    String [] pesos=new String [4];
    String [] fechas=new String [4];
    fechas[0]=f1;
    fechas[1]=f2;
    fechas[2]=f3;
    fechas[3]=f4;
    
    int filas;
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from federado ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("idfederado");
                 entrenadorDtos2[1]= rs.getString("nombres")+""+rs.getString("apellidos");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
   
 /// llenar con peso
 filas=encabezado.getRowCount();
    String cedula,peso;
        for (int i = 0; i < filas; i++) {
         cedula=String.valueOf(encabezado.getValueAt(i, 0));
            for (int j = 0; j <= 3; j++) {
 sql = "SELECT cargas.peso, cargas.serie "+
       "from federado, cargas, entrenamiento" +
  " where federado.idfederado=cargas.identrena and entrenamiento.idEntreno=cargas.idfede and cargas.fecha='"+fechas[j]+"' and federado.idfederado='"+cedula+"' and entrenamiento.tipo='"+categoria+"'";
      
  PreparedStatement sentencia_sql2 = null;
        ResultSet rs2 = null;
 try {sentencia_sql2 =  miConexion.prepareStatement(sql);
             rs2 =  sentencia_sql2.executeQuery(sql);
             while (rs2.next()) {
                peso= rs2.getString("peso")+" "+rs2.getString("serie");
                 encabezado.setValueAt(peso, i, j+2);
             }
             rs2.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}      
            }      
        }} 
   
//*****************************************************************************
    public void calendarioSemana(JTable tabla,String categoria, String f1,String f2,String f3,String f4,String f5,String f6,String f7)
    {String [] columna ={"Cedula","Nombres",f1,f2,f3,f4,f5,f6,f7};
    String [] pesos=new String [7];
    String [] fechas=new String [7];
    fechas[0]=f1;
    fechas[1]=f2;
    fechas[2]=f3;
    fechas[3]=f4;
    fechas[4]=f5;
    fechas[5]=f6;
    fechas[6]=f7;
    
    int filas;
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
        tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from federado ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("idfederado");
                 entrenadorDtos2[1]= rs.getString("nombres")+""+rs.getString("apellidos");
                 encabezado.addRow(entrenadorDtos2);
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
   
 /// llenar con peso
 filas=encabezado.getRowCount();
    String cedula,peso;
        for (int i = 0; i < filas; i++) {
         cedula=String.valueOf(encabezado.getValueAt(i, 0));
            for (int j = 0; j <= 6; j++) {
 sql = "SELECT cargas.peso,cargas.serie "+
       "from federado, cargas, entrenamiento" +
  " where federado.idfederado=cargas.identrena and entrenamiento.idEntreno=cargas.idfede and cargas.fecha='"+fechas[j]+"' and federado.idfederado='"+cedula+"' and entrenamiento.tipo='"+categoria+"'";
      
  PreparedStatement sentencia_sql2 = null;
        ResultSet rs2 = null;
 try {sentencia_sql2 =  miConexion.prepareStatement(sql);
             rs2 =  sentencia_sql2.executeQuery(sql);
             while (rs2.next()) {
                peso= rs2.getString("peso")+" "+rs2.getString("serie");
                encabezado.setValueAt(peso, i, j+2);
             }
             rs2.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}      
            }      
        }} 

  public void calendarioDias(JTable tabla,String categoria, String f1)
    {String [] columna ={"Cedula","Nombres",f1};
    String [] pesos=new String [1];
    String [] fechas=new String [1];
    fechas[0]=f1;
    
    int filas;
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      
       tabla.setModel(encabezado);
        String[] entrenadorDtos2 = new String[3];
        String sql = "select * from federado ";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("idfederado");
                 entrenadorDtos2[1]= rs.getString("nombres")+""+rs.getString("apellidos");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
   
 /// llenar con peso
 filas=encabezado.getRowCount();
    String cedula,peso;
        for (int i = 0; i < filas; i++) {
         cedula=String.valueOf(encabezado.getValueAt(i, 0));
           
 sql = "SELECT cargas.peso,cargas.serie "+
       "from federado, cargas, entrenamiento" +
  " where federado.idfederado=cargas.identrena and entrenamiento.idEntreno=cargas.idfede and cargas.fecha='"+f1+"' and federado.idfederado='"+cedula+"' and entrenamiento.tipo='"+categoria+"'";
      
  PreparedStatement sentencia_sql2 = null;
        ResultSet rs2 = null;
 try {sentencia_sql2 =  miConexion.prepareStatement(sql);
             rs2 =  sentencia_sql2.executeQuery(sql);
             while (rs2.next()) {
                peso= rs2.getString("peso")+" "+rs2.getString("serie");
                 encabezado.setValueAt(peso, i, 2);
             }
             rs2.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}      
                 
        }} 
   
//**











}
