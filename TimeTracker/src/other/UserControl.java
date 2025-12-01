package other;

import dto.Usuario;
import bo.UsuarioBO;
import dto.TipoUsuario;
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import desempenho.Desempenho;
import java.awt.BorderLayout;
import org.jfree.data.category.CategoryDataset;

public class UserControl extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserControl.class.getName());

    public UserControl() {
        initComponents();
        pnGraficoContainer.removeAll();
    
        JPanel graficoTempoDia = criarGraficoBarras(Desempenho.getTempoTotalPorDia(), "Tempo por dia", "dia", "tempo(s)");
        JPanel graficoDificuldadeDia = criarGraficoBarras(Desempenho.getDificuldadeMediaPorDia(), "Dificuldade por dia", "dia", "Dificuldade");
        JPanel graficoDificuldadeTipo = criarGraficoBarras(Desempenho.getDificuldadeMediaPorTipoAtividade(), "Dificuldade por tipo", "tipo", "dificuldade");
        JPanel graficoTempoAtividade = criarGraficoBarras(Desempenho.getTempoTotalPorTipoAtividade(), "Tempo por TipoAtividade", "tipoAtividade", "Tempo");
        
        pnGraficoContainer.add(graficoTempoDia, BorderLayout.CENTER);
        pnGraficoContainer.validate();
        pnGraficoContainer.repaint();
        pnGrafico2.add(graficoDificuldadeDia, BorderLayout.CENTER);
        pnGrafico2.validate();
        pnGrafico2.repaint();
        pnGrafico3.add(graficoDificuldadeTipo, BorderLayout.CENTER);
        pnGrafico3.validate();
        pnGrafico3.repaint();
        pnGrafico4.add(graficoTempoAtividade, BorderLayout.CENTER);
        pnGrafico4.validate();
        pnGrafico4.repaint();
    }
    
    public JPanel criarGraficoBarras(DefaultCategoryDataset dataset, String titulo, String baixo, String lado) {
    
        JFreeChart grafico = ChartFactory.createBarChart(
                titulo,  
                baixo,            
                lado,    
                dataset,                    
                PlotOrientation.VERTICAL,   
                true,                       
                true,                      
                false                       
        );

        org.jfree.chart.plot.CategoryPlot plot = grafico.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        ChartPanel painelGrafico = new ChartPanel(grafico);

        painelGrafico.setPreferredSize(new java.awt.Dimension(400, 300));

        return painelGrafico;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnGraficoContainer = new javax.swing.JPanel();
        pnGrafico2 = new javax.swing.JPanel();
        pnGrafico3 = new javax.swing.JPanel();
        pnGrafico4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        pnGraficoContainer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnGraficoContainer.setLayout(new java.awt.BorderLayout());

        pnGrafico2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnGrafico2.setLayout(new java.awt.BorderLayout());

        pnGrafico3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnGrafico3.setLayout(new java.awt.BorderLayout());

        pnGrafico4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnGrafico4.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnGraficoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pnGrafico2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                        .addComponent(pnGrafico4, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(415, Short.MAX_VALUE)
                    .addComponent(pnGrafico3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(59, 59, 59)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(pnGraficoContainer, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnGrafico4, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(pnGrafico2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(110, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(pnGrafico3, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                    .addGap(372, 372, 372)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> new UserControl().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel pnGrafico2;
    private javax.swing.JPanel pnGrafico3;
    private javax.swing.JPanel pnGrafico4;
    private javax.swing.JPanel pnGraficoContainer;
    // End of variables declaration//GEN-END:variables
}
