/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buzz;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Valeriano Filipe Calológio
 */
public class SimulationFrame extends javax.swing.JFrame {

    public SimulationFrame() {
        
        initComponents();
        Autocarro.passageiros = TelaConfig.total_passageiros;
        jTextFieldNAutocarro.setText("Número de Autocarros: " +TelaConfig.autocarroLinkedList.size());
        //----------------------------------------------------------------------------------------------
        for (Autocarro var : TelaConfig.autocarroLinkedList){
           getContentPane().add(var.label);
           var.label.setBounds(0, 180, 90, 40);

          var.label.addMouseListener(new java.awt.event.MouseAdapter() {
               public void mouseReleased(java.awt.event.MouseEvent evt) {
                   //Mostra o PopupMenu da label autocarro
                   jPopupMenuBus.show(var.label, evt.getX(), evt.getY());
                   //Define açoes das opçoes do PopupMenu
                   jMenuItemReabastecer.addActionListener(new java.awt.event.ActionListener() {
                       public void actionPerformed(java.awt.event.ActionEvent evt) {
                           //reabastecer autocarro 
                           var.reabastecer();
                       }
                   });
                   jMenuItemManutencao.addActionListener(new java.awt.event.ActionListener() {
                       public void actionPerformed(java.awt.event.ActionEvent evt) {
                           //parar o autocarro por um determinado tempo para sua manutençao 
                           var.manutencao();
                       }
                   });
                   jMenuItemTrocarMotorista.addActionListener(new java.awt.event.ActionListener() {
                       public void actionPerformed(java.awt.event.ActionEvent evt) {
                           //para o autocarro por um curto espaço de tempo 
                           var.trocarMototorista();
                       }
                   });
                   jMenuItemReparar.addActionListener(new java.awt.event.ActionListener() {
                       public void actionPerformed(java.awt.event.ActionEvent evt) {
                           //Tirar o autocarro do modo bloqueado para bom estado
                           var.reparar();
                       }
                   });

                    /* new Thread(){
                       @SuppressWarnings("deprecation")
                       @Override
                       public void run(){*/
                          //while(true){
                               jTextArea1.setText("Autocarro: " + var.getTipo() + "\nCapacidade: "
                               + var.lotacaoMAX+"\nVelocidade: " + var.velocidade+"\nSentido: "+
                               var.getSentido()+ "\nPassageiros no Autocarro: "
                               + var.lotacao+ "\nEstado: " + var.getEstado()+ "\n" + var.getEstadoInfo());
                           //}
                       }
                  // }.start();
               //}
             
           });
           var.start();
          //----------------------------------------------------------------------------------------------
        }
        
        //Thread Avaria
        ThreadAvaria avaria = new ThreadAvaria();
        avaria.start();
       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuBus = new javax.swing.JPopupMenu();
        jMenuItemReabastecer = new javax.swing.JMenuItem();
        jMenuItemTrocarMotorista = new javax.swing.JMenuItem();
        jMenuItemManutencao = new javax.swing.JMenuItem();
        jMenuItemReparar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabelLisboa = new javax.swing.JLabel();
        jLabelCoimbra = new javax.swing.JLabel();
        jLabelPorto = new javax.swing.JLabel();
        jLabelBraga = new javax.swing.JLabel();
        jLabelCascais = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPassageiros = new javax.swing.JTextField();
        jTextFieldNAutocarro = new javax.swing.JTextField();

        jMenuItemReabastecer.setText("Reabastecer");
        jMenuItemReabastecer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReabastecerActionPerformed(evt);
            }
        });
        jPopupMenuBus.add(jMenuItemReabastecer);

        jMenuItemTrocarMotorista.setText("Trocar de Motorista");
        jPopupMenuBus.add(jMenuItemTrocarMotorista);

        jMenuItemManutencao.setText("Fazer Manutençao");
        jMenuItemManutencao.setActionCommand("Manutencao");
        jPopupMenuBus.add(jMenuItemManutencao);

        jMenuItemReparar.setText("Reparar");
        jPopupMenuBus.add(jMenuItemReparar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 1250, 500));
        setMinimumSize(new java.awt.Dimension(1250, 500));
        setName("simulationframe"); // NOI18N
        setSize(new java.awt.Dimension(1210, 500));
        getContentPane().setLayout(null);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 250, 166, 150);

        jLabelLisboa.setText("Lisboa");
        getContentPane().add(jLabelLisboa);
        jLabelLisboa.setBounds(300, 0, 80, 150);

        jLabelCoimbra.setText("Coimbra");
        getContentPane().add(jLabelCoimbra);
        jLabelCoimbra.setBounds(600, 0, 100, 150);

        jLabelPorto.setText("Porto");
        getContentPane().add(jLabelPorto);
        jLabelPorto.setBounds(900, 0, 100, 150);

        jLabelBraga.setText("Braga");
        getContentPane().add(jLabelBraga);
        jLabelBraga.setBounds(1175, 0, 60, 150);

        jLabelCascais.setText("Cascais");
        getContentPane().add(jLabelCascais);
        jLabelCascais.setBounds(0, 0, 80, 150);

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLabel1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 180, 90, 40);

        jTextFieldPassageiros.setEditable(false);
        new Thread(){
            @Override
            public void run(){
                do{
                    jTextFieldPassageiros.setText("Nº Total P: "+Autocarro.passageiros);
                    try{
                        Thread.currentThread().sleep(20);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Autocarro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }while(Autocarro.passageiros > 0);
            }}.start();
            jTextFieldPassageiros.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jTextFieldPassageirosActionPerformed(evt);
                }
            });
            getContentPane().add(jTextFieldPassageiros);
            jTextFieldPassageiros.setBounds(230, 346, 180, 50);

            jTextFieldNAutocarro.setEditable(false);
            getContentPane().add(jTextFieldNAutocarro);
            jTextFieldNAutocarro.setBounds(230, 300, 180, 50);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    public String Registos(){
      String regis = jTextArea1.getText();
      return regis;
    }
    
    private void jTextFieldPassageirosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPassageirosActionPerformed
        
    }//GEN-LAST:event_jTextFieldPassageirosActionPerformed

    private void jLabel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseReleased

    }//GEN-LAST:event_jLabel1MouseReleased

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed

    }//GEN-LAST:event_jLabel1MousePressed

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jMenuItemReabastecerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReabastecerActionPerformed
      
    }//GEN-LAST:event_jMenuItemReabastecerActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SimulationFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SimulationFrame().setVisible(true);
                
            }
        });
        
        SimulationFrame simu = new SimulationFrame();
       // new Thread((Runnable) simu).start();
        simu.setSize(1300, 1200);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBraga;
    private javax.swing.JLabel jLabelCascais;
    private javax.swing.JLabel jLabelCoimbra;
    private javax.swing.JLabel jLabelLisboa;
    private javax.swing.JLabel jLabelPorto;
    private javax.swing.JMenuItem jMenuItemManutencao;
    private javax.swing.JMenuItem jMenuItemReabastecer;
    private javax.swing.JMenuItem jMenuItemReparar;
    private javax.swing.JMenuItem jMenuItemTrocarMotorista;
    private javax.swing.JPopupMenu jPopupMenuBus;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldNAutocarro;
    private javax.swing.JTextField jTextFieldPassageiros;
    // End of variables declaration//GEN-END:variables
}