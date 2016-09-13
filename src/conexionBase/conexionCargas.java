/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CarlosEfrain
 */
public class conexionCargas extends conexionBase{
    public static String fecha;
public conexionCargas(){
    conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/force", "root","123456"); }

//*************************************************************************************************
public void ingresarCrgas(String fch,  String peso,  String idEnt, String idFede,String serie)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into cargas ( fecha, peso, idfede,identrena,serie)" + "values(?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setString(1,fch);
          sentencia_sql2.setString(2, peso);
          sentencia_sql2.setString(3, idEnt);
          sentencia_sql2.setString(4, idFede);
          sentencia_sql2.setString(5, serie);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error datos no ingresados" + e.getMessage());}}
  //******************************************************************************************************

public void consulfehaDisponible(  String  deporti, String identreno)
    {String sql = "SELECT max(fecha) as fecha\n" +
            "FROM cargas, entrenamiento\n" +
            "where entrenamiento.tipo=(select tipo from entrenamiento where idEntreno='"+identreno+"')\n" +
            "and cargas.identrena='"+deporti+"'\n" +
            "and cargas.idfede=entrenamiento.idEntreno";
        fecha=null;
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 fecha=rs.getString("fecha");
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//*****************************************************************************
}
