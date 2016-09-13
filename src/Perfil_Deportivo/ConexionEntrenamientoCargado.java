/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Perfil_Deportivo;

import CargarEntrenamiento.reporteador;
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
public class ConexionEntrenamientoCargado extends conexionBase {
    public ConexionEntrenamientoCargado()
    { conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }
public void consultarReporteEntreno( String idfederado)
    {
       
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT DISTINCT entrenamiento.nombre\n" +
                    "from entrenamiento, cargas\n" +
                    "where idEntreno=idfede and identrena='"+idfederado+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
         reporteador.comboEntrenamiento.removeAllItems();
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 reporteador.comboEntrenamiento.addItem(rs.getString("entrenamiento.nombre"));
                }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
 ////////////////////////////////////////////////////////////////////////////////////////   
/////////////////////////////////////////////////////////////// 
    public void consultarEntrenamientoTotal( String idfederado)
    {
     
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT DISTINCT entrenamiento.nombre\n" +
                    "from entrenamiento, cargas\n" +
                    "where idEntreno=idfede and identrena='"+idfederado+"'";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
        EntrenamientoCargado.comboxEntrenamiento.removeAllItems();
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 EntrenamientoCargado.comboxEntrenamiento.addItem(rs.getString("entrenamiento.nombre"));
                 }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
 ////////////////////////////////////////////////////////////////////////////////////////
public void consultarCargasTotalEntreno(JTable tabla, String idfederado,String nombrentrena)
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
        String[] entrenadorDtos2 = new String[4];
        String sql = "SELECT cargas.idcargas, cargas.fecha,peso, entrenamiento.tipo\n" +
"from cargas, entrenamiento\n" +
"where cargas.identrena='"+idfederado+"' and entrenamiento.nombre='"+nombrentrena+"' and cargas.idfede=entrenamiento.identreno";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
//id, idfederado, rm, categoria, fecha, observacion
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 entrenadorDtos2[0]= rs.getString("cargas.idcargas");
                 entrenadorDtos2[1]= rs.getString("cargas.fecha");
                 entrenadorDtos2[2]= rs.getString("peso");
                 entrenadorDtos2[3]= rs.getString("entrenamiento.tipo");
                 encabezado.addRow(entrenadorDtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
  //******************************************************************************************************
//*******************************************************************************************************
public void eliminarCargas(String idfederado,String nombrentrena)
    {int n=0;
        String sql;
        PreparedStatement sentencia_sql;
        try {
             sql = "delete cargas\n" +
"from cargas, entrenamiento\n" +
"where cargas.identrena='"+idfederado+"' and entrenamiento.nombre='"+nombrentrena+"' and cargas.idfede=entrenamiento.identreno";

            sentencia_sql = miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {JOptionPane.showMessageDialog(null, "Entrenamiento eliminado Correctamente");
            }}
   catch (SQLException e) {
          JOptionPane.showMessageDialog(null, "Error al eliminar entrenamiento: " + e.getMessage());}}
//******************************************************************************
    
}
