/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package imc;

import conexionBase.conexionBase;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CarlosEfrain
 */
public class conexionIMc extends conexionBase {
  public conexionIMc()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

    
public void consultarCargasTotal_IMC(JTable tabla, String idfederado)
    {
       DefaultTableModel encabezado=new DefaultTableModel ();
        encabezado=(DefaultTableModel) tabla.getModel();
        tabla.setModel(encabezado);
       int nuFlias=encabezado.getRowCount()-1;
       //////////////limpiar tabla
        for(int i=nuFlias;i>=0;i--){ 
            encabezado.removeRow(i);
        }
       ////////////////
        String[] entrenadorDtos2 = new String[6];
        String sql = "SELECT * FROM cuerpo where idfederado='"+idfederado+"' ORDER BY fecha";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//cuerpo, idfederado, imc, igc, altura, peso, mcm,fecha
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("fecha");
                 entrenadorDtos2[1]= rs.getString("peso");
                 entrenadorDtos2[2]= rs.getString("imc");
                 entrenadorDtos2[3]= rs.getString("igc");
                 entrenadorDtos2[4]= rs.getString("mcm");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
  //******************************************************************************************************
public void ingresarIMC(String idfederado, double imc, double igc, double altura, double peso, double mcm,String fecha)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        //cuerpo, idfederado, imc, igc, altura, peso, mcm,fecha
        try { sql2 ="insert into cuerpo ( idfederado, imc, igc, altura, peso, mcm,fecha)" + "values(?,?,?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,idfederado);
          sentencia_sql2.setDouble(2, imc);
          sentencia_sql2.setDouble(3, igc);
          sentencia_sql2.setDouble(4, altura);
          sentencia_sql2.setDouble(5, peso);
          sentencia_sql2.setDouble(6, mcm);
          sentencia_sql2.setString(7, fecha);
          n1=sentencia_sql2.executeUpdate();
if( n1>0) {JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados: " + e.getMessage());}}
  //******************************************************************************************************
   //*******************************************************************************************************
public void eliminarIMC(String idfederado,String fecha)
    {int n=0;
        String sql;
        PreparedStatement sentencia_sql;
        try {
             sql = "delete cuerpo\n" +
                    "from cuerpo\n" +
                    "where idfederado='"+idfederado+"' and fecha='"+fecha+"'";

            sentencia_sql = miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "Registro eliminado Correctamente");
            }}
   catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al eliminar registro: " + e.getMessage());}}
//******************************************************************************
public boolean consultarRegistroRepetidoIMC( String idfederado,String fecha)
    {
       
       ////////////////
        String peso = null;
        String sql = "SELECT * from cuerpo where idfederado='"+idfederado+"' and fecha= '"+fecha+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//cuerpo, idfederado, imc, igc, altura, peso, mcm,fecha
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 
                 peso= rs.getString("peso");
                 
                }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
    if(peso==null)return false;
    else return true;
    }


}
