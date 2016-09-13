/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package factura;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/**
 *
 * @author CarlosEfrain
 */
public class formatoTabla extends DefaultTableCellRenderer{
    public Component getTableCellRendererComponent
(JTable table, Object value, boolean selected, boolean focused, int row, int column)
{
        // SI EN CADA FILA DE LA TABLA LA CELDA 5 ES IGUAL A ACTIVO COLOR AZUL
if(String.valueOf(table.getValueAt(row,5)).equals(""))  setForeground(Color.blue);
                // SI NO ES ACTIVO ENTONCES COLOR ROJO
else 
    setBackground(Color.WHITE);
       

super.getTableCellRendererComponent(table, value, selected, focused, row, column);
return this;
}
}
