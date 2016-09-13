/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficoComparacion;

import static Perfil_Deportivo.Conexion_PerfilDepor.categoriaGene;
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
public class conexionGraficoCompa extends conexionBase{
graficaCompa migraficacompa=new  graficaCompa();
double[] pesoentreno=new double[1000];
String[] fechaentreno=new String[1000];

public conexionGraficoCompa()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

//*************************************************************************************************+
    
public void consultarFuerzaTotal1( String idfederado, String categoria)
    {
       
       ////////////////
        int contador=0;
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT * FROM fuerza where idfederado='"+idfederado+"' and categoria='"+categoria+"' order by fecha";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 pesoentreno[contador]=Double.parseDouble(rs.getString("rm"));
                 fechaentreno[contador]=rs.getString("fecha");
                }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
 contador=0;  
 for (int i = 0; i < 1000; i++) {
              if (fechaentreno[i]==null ||fechaentreno[i].equals(""))
                  i=1000;
              else contador++;}
 double[] pesoentreno2=new double[contador];
String[] fechaentreno2=new String[contador];
        for (int i = 0; i < contador; i++) {
            pesoentreno2[i]=pesoentreno[i];
            fechaentreno2[i]=fechaentreno[i];
            
        }
    
    }
  //******************************************************************************************************
public void consultarFuerzaTotal( String idfederado, String categoria)
    {
       
       ////////////////
        int contador=0;
        String nombre="";
        boolean encontro=false;
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT * FROM fuerza where idfederado='"+idfederado+"' and categoria='"+categoria+"' order by fecha";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 contador++;
                 encontro=true;
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
 ////////////
sentencia_sql = null;
rs = null;
//id, idfederado, rm, categoria, fecha, observacion
 double[] pesoentreno3=new double[contador];
String[] fechaentreno3=new String[contador];
contador=0;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 pesoentreno3[contador]=Double.parseDouble(rs.getString("rm"));
                 fechaentreno3[contador]=rs.getString("fecha");
                 contador++;
                 }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
 if (!encontro)
     JOptionPane.showMessageDialog(null, "No hay datos que graficar","Advertencia",JOptionPane.WARNING_MESSAGE);
 else 
 migraficacompa.agregarGafica(GraficoComparacion.txtnombredeportista.getText()+"-"+categoria, fechaentreno3, pesoentreno3);
        
    
    }
  //******************************************************************************************************
}
