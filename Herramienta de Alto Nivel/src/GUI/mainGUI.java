package GUI;

import Clases.Pintar;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import Clases.Arboles;
import static GUI.CPUConfig.determinaTamano;
import java.awt.Color;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author cesar
 */
public final class mainGUI extends javax.swing.JFrame {

    /**
     * Creates new form mainGUI
     */
    Pintar pintar = new Pintar();
    Arboles arboles = new Arboles(); 
    static String clk = "500000";
    public int inicial = 0;
    public int actualY;
    public int nodo = 0;
    int n=0,nn=0,id,id2;
    
    mainGUI b; //= new mainGUI();
    
    public mainGUI(){
        generaGrafoDependencias();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setVisible(true);

        jButton1.setText("Timer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("PIO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("UART");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("CPU");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("RAM");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(455, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(141, 141, 141));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(0, 102, 102)));
        jPanel1.setAutoscrolls(true);
        jPanel1.setMinimumSize(new java.awt.Dimension(770, 522));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPanel1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPanel1KeyReleased(evt);
            }
        });
        jPanel1.setLayout(null);

        jButton6.setText("Generar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("Abrir");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Guardar");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 692, Short.MAX_VALUE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jInternalFrame1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    public void pintaCPU(){
        arboles.setCordeX(nodo,20);
        arboles.setCordeY(nodo,130);
        nodo ++;
        arboles.setCordeX(nodo,60);
        arboles.setCordeY(nodo,150);
        nodo ++;
        arboles.setCordeX(nodo,100);
        arboles.setCordeY(nodo,170);
        nodo ++;
        Pintar.pintaNombre(jPanel1.getGraphics(), 130, 110, "Procesador");
        Pintar.pintarCirculo( jPanel1.getGraphics(), 20, 130, "CLK", 110, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(), 60, 150, "Maestro", 70, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(), 100, 170, "Reset", 30, 8);
        actualY = 170; 
    }
    
    
    
    public void pintaCLK(){
        arboles.setCordeX(nodo,20);
        arboles.setCordeY(nodo,50);
        nodo ++;
        arboles.setCordeX(nodo,100);
        arboles.setCordeY(nodo,80);
        nodo ++;
        Pintar.pintaNombre(jPanel1.getGraphics(), 130, 30, "Señal de Reloj");
        Pintar.pintarCirculo( jPanel1.getGraphics(), 20, 50, "CLK", 110, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),100, 80, "CLK_reset", 30, 8);
    }
    
     public void pintaTimer(){;
        arboles.setCordeX(nodo,20);
        arboles.setCordeY(nodo, actualY + 50);
        nodo ++;
        arboles.setCordeX(nodo,60);
        arboles.setCordeY(nodo,actualY + 70);
        nodo ++;
        arboles.setCordeX(nodo,100);
        arboles.setCordeY(nodo,actualY + 90);
        nodo ++;
        Pintar.pintaNombre(jPanel1.getGraphics(), 130, actualY + 30, "Temporizador");
        Pintar.pintarCirculo( jPanel1.getGraphics(), 20, actualY + 50, "CLK", 110, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),60, actualY + 70, "Esclavo", 70, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),100, actualY + 90, "Reset", 30, 8);
        actualY = actualY + 100;
         System.out.println(actualY);
    }
    
     public void pintaPIO(){
         arboles.setCordeX(nodo,20);
        arboles.setCordeY(nodo, actualY + 50);
        nodo ++;
        arboles.setCordeX(nodo,60);
        arboles.setCordeY(nodo,actualY + 70);
        nodo ++;
        arboles.setCordeX(nodo,100);
        arboles.setCordeY(nodo,actualY + 90);
        nodo ++;
        Pintar.pintaNombre(jPanel1.getGraphics(), 130, actualY + 30, "PIO");
        Pintar.pintarCirculo( jPanel1.getGraphics(), 20, actualY + 50, "CLK", 110, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),60, actualY + 70, "Esclavo", 70, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),100, actualY + 90, "Reset", 30, 8);
        actualY = actualY + 100;
        System.out.println(actualY);
    }
     
     
    public void pintaUART(){
        arboles.setCordeX(nodo,20);
        arboles.setCordeY(nodo, actualY + 50);
        nodo ++;
        arboles.setCordeX(nodo,60);
        arboles.setCordeY(nodo,actualY + 70);
        nodo ++;
        arboles.setCordeX(nodo,100);
        arboles.setCordeY(nodo,actualY + 90);
        nodo ++;
        Pintar.pintaNombre(jPanel1.getGraphics(), 130, actualY + 30, "UART");
        Pintar.pintarCirculo( jPanel1.getGraphics(), 20, actualY + 50, "CLK", 110, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),60, actualY + 70, "Esclavo", 70, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),100, actualY + 90, "Reset", 30, 8);
        actualY = actualY + 100;
        System.out.println(actualY);
    }
    
     public void pintaRAM(){
        arboles.setCordeX(nodo,20);
        arboles.setCordeY(nodo, actualY + 50);
        nodo ++;
        arboles.setCordeX(nodo,60);
        arboles.setCordeY(nodo,actualY + 70);
        nodo ++;
        arboles.setCordeX(nodo,100);
        arboles.setCordeY(nodo,actualY + 90);
        nodo ++;
        Pintar.pintaNombre(jPanel1.getGraphics(), 130, actualY + 30, "RAM");
        Pintar.pintarCirculo( jPanel1.getGraphics(), 20, actualY + 50, "CLK", 110, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),60, actualY + 70, "Esclavo", 70, 8);
        Pintar.pintarCirculo( jPanel1.getGraphics(),100, actualY + 90, "Reset", 30, 8);
        actualY = actualY + 100;
    }
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (inicial == 0){
            pintaCLK();
            pintaCPU();
            inicial += 1;
        }
        FramePIO view = new FramePIO();
        view.setB(this);
        view.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (inicial == 0){
            pintaCLK();
            pintaCPU();
            inicial += 1;
        }
        FrameTimer view = new FrameTimer();
        view.setB(this);
        view.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (inicial == 0){
            pintaCLK();
            pintaCPU();
            inicial += 1;
        }
        FrameUART view = new FrameUART();
        view.setB(this);
        view.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed
    
    public static void generaGrafoDependencias(){
        File file = new File ("grafo.XML");
        try {
        // A partir del objeto File creamos el fichero físicamente
        if (file.createNewFile())
          System.out.println("El fichero se ha creado correctamente");
        else
          System.out.println("No ha podido ser creado el fichero");
        } catch (IOException ioe) {
        ioe.printStackTrace();
        }
        
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("grafo.XML");
            pw = new PrintWriter(fichero);
            pw.println("<?xml version='1.0'?>");
            pw.println("<soc>");
            pw.println("<module type = 'cpu'>");
            pw.println("</module>");
            pw.println("<module type = 'clk'>");
            pw.println("    <frequency>" + clk + "</frequency>");
            pw.println("</module>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CPUConfig view = new CPUConfig();
        view.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        int xxx, yyy;
        xxx=evt.getX();
        yyy=evt.getY();
        System.out.println(xxx);
        System.out.println(yyy);
        clickDerechoSobreNodo(xxx, yyy);
        if(n==2 ){
             n=0; 
             arboles.setmAdyacencia(id2, id, 1);
             arboles.setmAdyacencia(id, id2, 1);
             //arboles.setmCoeficiente(id2, id,ta );
             //arboles.setmCoeficiente(id, id2, ta);
             Pintar.pintarLinea(jPanel1.getGraphics(),arboles.getCordeX(id), arboles.getCordeY(id), arboles.getCordeX(id2), arboles.getCordeY(id2));
             id=-1;
             id2=-1;
         }
    }//GEN-LAST:event_jPanel1MousePressed

    
    
    public boolean clickDerechoSobreNodo(int xxx,int yyy){ 
        System.out.println("Valores del Click en x");
        System.out.println(xxx);
        System.out.println("Valores del Click en y");
        System.out.println(yyy);
        for (int j = 0; j < nodo; j++) {
             System.out.println(arboles.getCordeY(j));
            if((xxx+2) > arboles.getCordeX(j) && xxx < (arboles.getCordeX(j)+13) && (yyy+2) > arboles.getCordeY(j) && yyy<(arboles.getCordeY(j)+13) ) {
                                      
               if(n==0){
                   id = j;
                   Pintar.clickSobreNodo(jPanel1.getGraphics(), arboles.getCordeX(j), arboles.getCordeY(j), null,Color.orange);
                   n++;                   
               }
               else{ 
                   id2=j;                   
                   n++;
                   Pintar.clickSobreNodo(jPanel1.getGraphics(), arboles.getCordeX(j), arboles.getCordeY(j), null,Color.orange);       
                   if(id==id2){// si id == id2 por q se volvio a dar click sobre el mismos nodo, se cancela el click anterio
                       n=0;
                       
                       id=-1;
                       id2=-1;
                   }
               } 
               nn=0;
               System.out.println("Hay nodo");
                return true;              
            }
         }
     return false;
 }  
    public void verificaNodo(int x, int y){
       
        
    }
    
    
    
  
    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1MouseMoved

    private void jPanel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jPanel1KeyPressed

    private void jPanel1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPanel1KeyReleased

    }//GEN-LAST:event_jPanel1KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("grafo.XML", true);
            pw = new PrintWriter(fichero);
            pw.println("</soc>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        
          try {
            String cmd = "./soc.sh"; 
            Runtime.getRuntime().exec(cmd);
            System.out.println("todo bien");
        } catch (IOException ioe) {
            System.out.println (ioe);
        }  
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (inicial == 0){
            pintaCLK();
            pintaCPU();
            inicial += 1;
        }
        FrameRAM view = new FrameRAM();
        view.setB(this);
        view.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    public static javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
