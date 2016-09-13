/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kardex;

import compra.Compra;
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
public class conexionkardex extends conexionBase{
    public conexionkardex(){
             conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/contable", "root","123456"); }
    //*****************************************************************************
public void consultarProductoTotal(JTable tabla, String codigoPro )
    {//String [] columna ={"Codigo","Descripcion ","Precio","cantidad"};
       DefaultTableModel encabezado=(DefaultTableModel) tabla.getModel();
      //poner la validacion 
       tabla.setModel(encabezado);
        String[] Dtos2 = new String[10];
        String sql =  "SELECT venta.fechaemision as fecha, venta.idventa as id, venta.tipo,clientes.nombres, detalleventa.cantidad, detalleventa.precio, detalleventa.totalproducto\n" +
"from venta, detalleventa,clientes\n" +
"where venta.idventa=detalleventa.idventa and detalleventa.idproducto='"+codigoPro+"'and venta.idcliente=clientes.ruc\n" +
"union all\n" +
"SELECT compra.fechaemision as fecha, compra.idcompra as id, compra.tipo,proveedor.nombres, detallecompra.cantidad, detallecompra.precio, detallecompra.totalproducto\n" +
"from compra, detallecompra, proveedor\n" +
"where compra.idcompra=detallecompra.idcompra and detallecompra.idproducto='"+codigoPro+"' and compra.idproveedor=proveedor.ruc\n" +
"order by  fecha";
      
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 Dtos2[0]= rs.getString("fecha");
                 Dtos2[1]= rs.getString("id"); 
                 Dtos2[2]= rs.getString("tipo");
                 Dtos2[3]= rs.getString("nombres");
                 Dtos2[4]= rs.getString("cantidad");
                 Dtos2[5]= rs.getString("precio");
                 Dtos2[6]= rs.getString("totalproducto");
                 encabezado.addRow(Dtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//**********************************************************************************************
public void consultarProducto(String codigo){
    
        String sql =  " SELECT * FROM producto where  codigo_producto='"+codigo+"'";
      
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 kardex.txtnombrekardex.setText(rs.getString("codigo_producto"));
                 kardex.txtstockkardex.setText(rs.getString("stock"));
                }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}
}
public void consultarProductoTotal(JTable tabla)
    {String [] columna ={"Codigo","Descripcion ","Precio","cantidad"};
       DefaultTableModel encabezado=new DefaultTableModel (null,columna);
      //poner la validacion 
       tabla.setModel(encabezado);
        String[] Dtos2 = new String[4];
        String sql =  " SELECT * FROM producto ";
      
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 Dtos2[0]= String.valueOf(rs.getInt("codigo_producto"));
                 Dtos2[1]= rs.getString("descripcion");
                 Dtos2[2]= String.valueOf(rs.getDouble("precio"));
                 Dtos2[3]="1";
                 encabezado.addRow(Dtos2);}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());}}
//ak esta el pinche select 
/*SELECT venta.fechaemision as fecha, venta.idventa as id, venta.tipo, detalleventa.cantidad, detalleventa.precio, detalleventa.totalproducto
from venta, detalleventa
where venta.idventa=detalleventa.idventa
union all
SELECT compra.fechaemision as fecha, compra.idcompra as id, compra.tipo, detallecompra.cantidad, detallecompra.precio, detallecompra.totalproducto
from compra, detallecompra
where compra.idcompra=detallecompra.idcompra
order by  fecha
*/

    
    
    
}
