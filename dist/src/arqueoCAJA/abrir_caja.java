/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arqueoCAJA;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.sql.Time;
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
public class abrir_caja extends javax.swing.JDialog {
conexionCAja micaja=new conexionCAja();
Calendar calendar = Calendar.getInstance();
DateFormat fechadf =  new SimpleDateFormat("yyyy/MM/dd");
SimpleDateFormat horadf = new SimpleDateFormat("H:m:ss");
double  efectivo=0;
double totalfila;
    JTextField txt=new JTextField();
    String user="1313375980";
    /**
     * Creates new form abrir_caja
     */
    public abrir_caja(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtfecahInici.setText(fechadf.format(calendar.getTime() ));
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

        txtEfectivoInicial = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        txtfecahInici = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcaja = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtEfectivoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEfectivoInicialKeyTyped(evt);
            }
        });

        jToggleButton1.setText("Aceptar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        txtfecahInici.setEditable(false);
        txtfecahInici.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Monto Inicial");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Fecha ");

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEfectivoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtfecahInici)
                                    .addContainerGap()))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEfectivoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfecahInici, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if(micaja.consultarCaja_esta_Abierta(user)){
        Date date=calendar.getTime();
        micaja.ingresarCaja(user, fechadf.format(date ), horadf.format(date), efectivo);
       }
        else JOptionPane.showMessageDialog(null, "la caja esta abierta");
        ///cerrar
        //micaja.cerrarCaja(fechadf.format(date ), horadf.format(date),60, "aa");
        this.dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void txtEfectivoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectivoInicialKeyTyped
        try {
            efectivo=Double.parseDouble(txtEfectivoInicial.getText()+evt.getKeyChar());
        } catch (Exception e) {
            try {
                efectivo=Double.parseDouble(txtEfectivoInicial.getText());
            } catch (Exception ed) {
                efectivo=0;
            }
            evt.consume();
        }
        limpiarcaja();
 // TODO add your handling code here:
    }//GEN-LAST:event_txtEfectivoInicialKeyTyped

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
            java.util.logging.Logger.getLogger(abrir_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(abrir_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(abrir_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(abrir_caja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                abrir_caja dialog = new abrir_caja(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTable tblcaja;
    private javax.swing.JTextField txtEfectivoInicial;
    private javax.swing.JTextField txtfecahInici;
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
    txtEfectivoInicial.setText(String.valueOf(totalmonto));
    efectivo=Double.parseDouble(txtEfectivoInicial.getText());
}
public void limpiarcaja(){
    for (int i = 0; i < tblcaja.getRowCount(); i++) {
        tblcaja.setValueAt(0, i,2);
        tblcaja.setValueAt(null, i,1);
        }}
}
