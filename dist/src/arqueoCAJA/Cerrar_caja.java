/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arqueoCAJA;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author CarlosEfrain
 */
public class Cerrar_caja extends javax.swing.JDialog {
double conteo=0;
conexionCAja micaja=new conexionCAja();
Calendar calendar = Calendar.getInstance();
DateFormat fechadf =  new SimpleDateFormat("yyyy/MM/dd");
SimpleDateFormat horadf = new SimpleDateFormat("H:m:ss");
String user="1313375980";  
JTextField txt=new JTextField();
double  efectivo=0;
double totalfila;
   /* Creates new form Cerra_caja
     */
    public Cerrar_caja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
DefaultTableModel        modeloNat=(DefaultTableModel) tblcaja.getModel();
        tblcaja.setModel(modeloNat); 
        TableColumn tc= tblcaja.getColumnModel().getColumn(1);
        TableCellEditor te  =new DefaultCellEditor(txt);
        tc.setCellEditor(te);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtconteoFinal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcaja = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtconteoFinal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtconteoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtconteoFinalKeyTyped(evt);
            }
        });

        jButton1.setText("Aceptar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Conteo de Efectivo ");

        tblcaja.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblcaja.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Billete 100", "", "0"},
                {"Billete 50", "", "0"},
                {"Billete 10", "", "0"},
                {"Billete 5", "", "0"},
                {"Billete 1", "", "0"},
                {"Moneda 1 ", "", "0"},
                {"Moneda 0.50", "", "0"},
                {"Moneda 0.25", "", "0"},
                {"Moneda 0.10", "", "0"},
                {"Moneda 0.5", "", "0"},
                {"Moneda 0.1", "", "0"}
            },
            new String [] {
                "Moneda", "Cantidad", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblcaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tblcajaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tblcaja);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(108, 108, 108))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtconteoFinal)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(txtconteoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtconteoFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtconteoFinalKeyTyped
try {
            conteo=Double.parseDouble(txtconteoFinal.getText()+evt.getKeyChar());
        } catch (Exception e) {
            try {
                conteo=Double.parseDouble(txtconteoFinal.getText());
            } catch (Exception ed) {
                conteo=0;
            }
            evt.consume();
        } 
limpiarcaja();
// TODO add your handling code here:
    }//GEN-LAST:event_txtconteoFinalKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Date date=calendar.getTime();///cerrar
        if(!micaja.consultarCaja_esta_Abierta(user))
            micaja.cerrarCaja(fechadf.format(date ), horadf.format(date),conteo, user);
        else JOptionPane.showMessageDialog(null,"la caja no esta abierta");
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblcajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblcajaKeyTyped
        // totalfila=0;
        try {
            totalfila=Double.parseDouble(txt.getText()+evt.getKeyChar());

        } catch (Exception e) {
            try {
                totalfila=Double.parseDouble(txt.getText());

            } catch (Exception ed) {
                totalfila=0;

            }
            evt.consume();
        }

        System.out.print(totalfila+"\n");
        calcular();

    }//GEN-LAST:event_tblcajaKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cerrar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cerrar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cerrar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cerrar_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Cerrar_caja dialog = new Cerrar_caja(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblcaja;
    private javax.swing.JTextField txtconteoFinal;
    // End of variables declaration//GEN-END:variables
public void calcular(){
    if (tblcaja.getSelectedRow()==0)tblcaja.setValueAt(totalfila*100, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==1)tblcaja.setValueAt(totalfila*50, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==2)tblcaja.setValueAt(totalfila*10, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==3)tblcaja.setValueAt(totalfila*5, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==4)tblcaja.setValueAt(totalfila*1, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==5)tblcaja.setValueAt(totalfila*1, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==6)tblcaja.setValueAt(totalfila*0.5, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==7)tblcaja.setValueAt(totalfila*0.25, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==8)tblcaja.setValueAt(totalfila*0.10, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==9)tblcaja.setValueAt(totalfila*0.05, tblcaja.getSelectedRow(),2);
        else if (tblcaja.getSelectedRow()==10)tblcaja.setValueAt(totalfila*0.01, tblcaja.getSelectedRow(),2);
double totalmonto=0; 
    for (int i = 0; i < tblcaja.getRowCount(); i++) {
        totalmonto=totalmonto+Double.parseDouble(tblcaja.getValueAt(i, 2).toString());
        }
    txtconteoFinal.setText(String.valueOf(totalmonto));
    conteo=Double.parseDouble(txtconteoFinal.getText());
}
public void limpiarcaja(){
    for (int i = 0; i < tblcaja.getRowCount(); i++) {
        tblcaja.setValueAt(0, i,2);
        tblcaja.setValueAt(null,i,1);
        }
}
}
