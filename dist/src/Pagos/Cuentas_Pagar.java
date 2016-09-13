/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Pagos;

import cobros.*;
import conexionBase.conexionBase;
import factura.Factura;
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
public class Cuentas_Pagar extends conexionBase{

 //*****************************************************************************  
    public Cuentas_Pagar(){
    conectar("com.mysql.jdbc.Driver",  "jdbc:mysql://localhost:3306/contable", "root","123456"); }

//*****************************************************************************
//*****************************************************************************
public void consultarDeudasTotal(JTable tabla)
    {   DefaultTableModel encabezado=new DefaultTableModel ();
        encabezado=(DefaultTableModel) tabla.getModel();
        tabla.setModel(encabezado);
          boolean seguir=true,lleno;
       int contF=0, nuFlias=encabezado.getRowCount();
        String[] entrenadorDtos2 = new String[4];
        String[] nada = {""};
        String sql = "select proveedor.ruc, proveedor.nombres, max(FECHA_REGISTRO), sum(VALOR_POR_PAGAR)\n" +
"from proveedor, compra, cuentas_por_pagar\n" +
"where proveedor.ruc=compra.idproveedor and compra.idcompra=cuentas_por_pagar.ID_COMPRA\n" +
"GROUP BY compra.idproveedor";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 seguir=true; 
                 entrenadorDtos2[0]= rs.getString("proveedor.ruc");
                 entrenadorDtos2[1]= rs.getString("proveedor.nombres");
                 entrenadorDtos2[2]= rs.getString("max(FECHA_REGISTRO)");
                 entrenadorDtos2[3]= rs.getString("sum(VALOR_POR_PAGAR)");
                 
             while (seguir){
                      
                      // JOptionPane.showMessageDialog(null, "valor: "+valor);
                      if (tabla.getValueAt(contF, 2)==null){
                        //  JOptionPane.showMessageDialog(null, "entro"+valor);
                             //   modelo.setValueAt(ProductoDtos2[0], contF, 0);
                                encabezado.setValueAt(entrenadorDtos2[0], contF, 0);
                                encabezado.setValueAt(entrenadorDtos2[1], contF, 1);
                                encabezado.setValueAt(entrenadorDtos2[2], contF, 2);
                                encabezado.setValueAt(entrenadorDtos2[3], contF, 3);
                               // Factura.tablaInstan=false;
                                
                                seguir=false;
                      }
                      if (contF==nuFlias)encabezado.addRow(nada);
                      contF++;
                  }
             
             
             
             
             
             }
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos  akk " + e.getMessage());}}
//*****************************************************************************  



public void consultarCuotasTotal(JTable tabla,String idcliente)
    {   DefaultTableModel encabezado=new DefaultTableModel ();
        encabezado=(DefaultTableModel) tabla.getModel();
        tabla.setModel(encabezado);
          boolean seguir=true,lleno;
       int contF=0, nuFlias=encabezado.getRowCount();
       //////////////limpiar tabla
       try{
            for (int i = 0;nuFlias>i; i++) {
                if (i<14){encabezado.setValueAt("", i, 0);
                          encabezado.setValueAt("", i, 1);
                          encabezado.setValueAt("", i, 2);}
            else encabezado.removeRow(i);
            }} 
       catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla."); }
        /////////////////
       nuFlias=encabezado.getRowCount();
        String[] entrenadorDtos2 = new String[4];
        String[] nada = {""};
        String sql = "select cuotas_pagar_detalle.FECHA_EMISION,compra.idcompra,cuotas_pagar_detalle.NUM_CUOTA ,if(IFNULL(PAGO,0)=0,VALOR_CUOTA,\n" +
"if (IFNULL(VALOR_MORA,0)=0,VALOR_MORA-PAGO,VALOR_CUOTA-PAGO)) as Monto\n" +
"from cuotas_pagar_detalle, proveedor, compra, cuentas_por_pagar\n" +
"where proveedor.ruc='"+idcliente+"' and proveedor.ruc=compra.idproveedor and compra.idcompra=cuentas_por_pagar.ID_COMPRA\n" +
"and cuentas_por_pagar.ID_CUENTA_POR_PAGAR=cuotas_pagar_detalle.ID_CUENTA_POR_PAGAR\n" +
"and cuentas_por_pagar.VALOR_POR_PAGAR>0";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 seguir=true; 
                 entrenadorDtos2[0]= rs.getString("cuotas_pagar_detalle.FECHA_EMISION");
                 entrenadorDtos2[1]= "factura Nro: "+rs.getString("compra.idcompra")+" Cuota: "+rs.getString("cuotas_pagar_detalle.NUM_CUOTA");
                 entrenadorDtos2[2]= rs.getString("Monto");
                 
             while (seguir){
                      
                      // JOptionPane.showMessageDialog(null, "valor: "+valor);
                      if (tabla.getValueAt(contF, 2)==null ||tabla.getValueAt(contF, 2).toString().equals("")){
                        //  JOptionPane.showMessageDialog(null, "entro"+valor);
                             //   modelo.setValueAt(ProductoDtos2[0], contF, 0);
                                encabezado.setValueAt(entrenadorDtos2[0], contF, 0);
                                encabezado.setValueAt(entrenadorDtos2[1], contF, 1);
                                encabezado.setValueAt(entrenadorDtos2[2], contF, 2);
                               // Factura.tablaInstan=false;
                                seguir=false;
                      }
                      if (contF==nuFlias)encabezado.addRow(nada);
                      contF++;
                  }}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());} }
//*****************************************************************************  

public void consultarCuotasPagar(JTable tabla,String idcliente)
    {   DefaultTableModel encabezado=new DefaultTableModel ();
        encabezado=(DefaultTableModel) tabla.getModel();
        tabla.setModel(encabezado);
          boolean seguir=true,lleno;
       int contF=0, nuFlias=encabezado.getRowCount();
       //////////////limpiar tabla
       try{
            for (int i = 0;nuFlias>i; i++) {
                if (i<14){encabezado.setValueAt("", i, 0);
                          encabezado.setValueAt("", i, 1);
                          encabezado.setValueAt("", i, 2);}
            else encabezado.removeRow(i);
            }} 
       catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla."); }
        /////////////////
       nuFlias=encabezado.getRowCount();
      ////////////////////////////////////////////////
        String[] entrenadorDtos2 = new String[8];
        String[] nada = {""};
        String sql = "select cuotas_pagar_detalle.CUOTAS_PAGAR_DETALLE AS Nro_Doc,\n" +
"compra.idcompra,cuotas_pagar_detalle.NUM_CUOTA,\n" +
"cuotas_pagar_detalle.FECHA_EMISION , cuotas_pagar_detalle.FECHA_VENCIMIENTO,\n" +
"cuotas_pagar_detalle.VALOR_CUOTA,cuotas_pagar_detalle.VALOR_MORA,\n" +
"if(IFNULL(VALOR_MORA,0)=0,VALOR_CUOTA,VALOR_MORA) AS TOTAL,\n" +
"if(IFNULL(PAGO,0)=0,VALOR_CUOTA,\n" +
"if (IFNULL(VALOR_MORA,0)=0,VALOR_CUOTA-PAGO,VALOR_MORA-PAGO)) as saldo\n" +
"from cuotas_pagar_detalle, proveedor, compra, cuentas_por_pagar\n" +
"where proveedor.ruc='"+idcliente+"' and proveedor.ruc=compra.idproveedor and compra.idcompra=cuentas_por_pagar.ID_compra\n" +
"and cuentas_por_pagar.ID_CUENTA_POR_PAGAR=cuotas_pagar_detalle.ID_CUENTA_POR_PAGAR\n" +
" and cuentas_por_pagar.VALOR_POR_PAGAR>0";
        PreparedStatement sentencia_sql = null;
        ResultSet rs = null;
 try {sentencia_sql =  miConexion.prepareStatement(sql);
             rs =  sentencia_sql.executeQuery(sql);
             while (rs.next()) {
                 seguir=true; 
                 entrenadorDtos2[0]= rs.getString("Nro_Doc");
                 entrenadorDtos2[1]= "factura Nro: "+rs.getString("compra.idcompra")+" Cuota: "+rs.getString("cuotas_pagar_detalle.NUM_CUOTA");
                 entrenadorDtos2[2]= rs.getString("cuotas_pagar_detalle.FECHA_EMISION");
                 entrenadorDtos2[3]= rs.getString("cuotas_pagar_detalle.FECHA_VENCIMIENTO");
                 entrenadorDtos2[4]= rs.getString("cuotas_pagar_detalle.VALOR_CUOTA");
                 entrenadorDtos2[5]= rs.getString("cuotas_pagar_detalle.VALOR_MORA");
                 entrenadorDtos2[6]= rs.getString("TOTAL");
                 entrenadorDtos2[7]= rs.getString("saldo");
                 
             while (seguir){
                      
                      // JOptionPane.showMessageDialog(null, "valor: "+valor);
                      if (tabla.getValueAt(contF, 2)==null ||tabla.getValueAt(contF, 2).toString().equals("")){
                        //  JOptionPane.showMessageDialog(null, "entro"+valor);
                             //   modelo.setValueAt(ProductoDtos2[0], contF, 0);
                                encabezado.setValueAt(entrenadorDtos2[0], contF, 0);
                                encabezado.setValueAt(entrenadorDtos2[1], contF, 1);
                                encabezado.setValueAt(entrenadorDtos2[2], contF, 2);
                                encabezado.setValueAt(entrenadorDtos2[3], contF, 3);
                                encabezado.setValueAt(entrenadorDtos2[4], contF, 4);
                                encabezado.setValueAt(entrenadorDtos2[5], contF, 5);
                                encabezado.setValueAt(entrenadorDtos2[6], contF, 6);
                                encabezado.setValueAt(entrenadorDtos2[7], contF, 7);
                           
                               // Factura.tablaInstan=false;
                                seguir=false;
                      }
                      if (contF==nuFlias)encabezado.addRow(nada);
                      contF++;
                  }}
             rs.close();}
 catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en obtener los datos   " + e.getMessage());} }
//***************************************************************************** 
public void modificarPagos(int num,double pago)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update cuotas_pagar_detalle\n" +
                " set PAGO = IFNULL(PAGO,0)+ "+pago+"\n" +
                "where CUOTAS_PAGAR_Detalle="+num+"";
            sentencia_sql =   miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
               // JOptionPane.showMessageDialog(null, "modificacion exitosa");
            } }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//***********************************************************************************************
public void modificarCuentaPago(int num,double pago, String cod)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update cuotas_pagar_detalle , proveedor, compra, cuentas_por_pagar\n" +
" set cuentas_por_pagar.VALOR_PAGADO = IFNULL(cuentas_por_pagar.VALOR_PAGADO,0)+"+pago+" ,\n" +
"cuentas_por_pagar.VALOR_POR_PAGAR = cuentas_por_pagar.VALOR_POR_PAGAR-(cuentas_por_pagar.VALOR_PAGADO)\n" +
"where proveedor.ruc='"+cod+"' and proveedor.ruc=compra.idproveedor and\n" +
"compra.idcompra=cuentas_por_pagar.ID_COMPRA\n" +
"and\n" +
"cuentas_por_pagar.ID_CUENTA_POR_PAGAR=cuotas_pagar_detalle.ID_CUENTA_POR_PAGAR\n" +
"and cuentas_por_pagar.VALOR_PAGADO<cuentas_por_pagar.VALOR_POR_PAGAR\n" +
"and cuotas_pagar_detalle.CUOTAS_PAGAR_Detalle="+num+"";
            sentencia_sql =   miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
               // JOptionPane.showMessageDialog(null, "modificacion exitosa");
            } }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//***********************************************************************************************

public void modificarCuentaValorPagar(int num,double pago, String cod)
    {int n=0;
     String sql;
     PreparedStatement sentencia_sql;
     try { sql ="update cuotas_pagar_detalle , proveedor, compra, cuentas_por_pagar\n" +
" set cuentas_por_pagar.VALOR_POR_PAGAR = cuentas_por_pagar.VALOR_POR_PAGAR-(cuentas_por_pagar.VALOR_PAGADO)\n" +
"where proveedor.ruc='"+cod+"' and proveedor.ruc=compra.idproveedor and\n" +
"compra.idcompra=cuentas_por_pagar.ID_COMPRA\n" +
"and\n" +
"cuentas_por_pagar.ID_CUENTA_POR_PAGAR=cuotas_pagar_detalle.ID_CUENTA_POR_PAGAR\n" +
"and cuentas_por_pagar.VALOR_PAGADO<cuentas_por_pagar.VALOR_POR_PAGAR\n" +
"and cuotas_pagar_detalle.CUOTAS_PAGAR_Detalle="+num+"";
            sentencia_sql =   miConexion.prepareStatement(sql);
            n= sentencia_sql.executeUpdate();
            if(n>0)
            {
               // JOptionPane.showMessageDialog(null, "modificacion exitosa");
            } }
catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de modificacion " + e.getMessage());}}
//*************************************************************************************************

//idpagos, valor, descripcion, observacion, fecha, idcliente
public void ingresarPago( double valor,String descripcion,String observacion,String fecha,String idcliente)
    {   int n1=0;
        String sql2;
        PreparedStatement sentencia_sql2;
        try { sql2 ="insert into pagos_deuda (  valor, descripcion, observacion, fecha, idproveedor)" + "values(?,?,?,?,?)";
          sentencia_sql2 =  miConexion.prepareStatement(sql2);
          sentencia_sql2.setDouble(1,valor);
          sentencia_sql2.setString(2, descripcion);
          sentencia_sql2.setString(3, observacion);
          sentencia_sql2.setString(4, fecha);
          sentencia_sql2.setString(5, idcliente);
          n1=sentencia_sql2.executeUpdate();
if( n1>0)
            {//JOptionPane.showMessageDialog(null, "datos ingresados correctamente");
            }} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error pago no ingresados" + e.getMessage());}}
  //******************************************************************************************************




}
