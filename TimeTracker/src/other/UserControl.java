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
    
        JPanel graficoPronto = criarGraficoBarras();

        pnGraficoContainer.add(graficoPronto, BorderLayout.CENTER);

        pnGraficoContainer.validate();
        pnGraficoContainer.repaint();
    }
    
    public JPanel criarGraficoBarras() {
    
        CategoryDataset dataset = Desempenho.getTempoTotalPorDia();
        

        JFreeChart grafico = ChartFactory.createBarChart(
                "Produtividade da Equipe",  
                "Funcionários",            
                "Quantidade de Tarefas",    
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

        pnGraficoContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnGraficoContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnGraficoContainer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnGraficoContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private javax.swing.JPanel pnGraficoContainer;
    // End of variables declaration//GEN-END:variables
}
