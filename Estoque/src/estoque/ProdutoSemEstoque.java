/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estoque;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marlon Santos
 */
public class ProdutoSemEstoque extends javax.swing.JPanel {

    /**
     * Creates new form ProdutoSemEstoque
     */
    public ProdutoSemEstoque() {
        initComponents();
        Tabela();
    }
    
    public void Tabela(){
        ResultSet res;
        DefaultTableModel model = (DefaultTableModel) jTableProdutosSemEstoque.getModel();
        while(model.getRowCount()>0) {
            model.removeRow(0);
        }
        res = MySQL.ProdutosSemEstoque();
        if(res!=null) {
            try {
                while(res.next()) {
                    model.addRow(new Object[]{res.getString("nome"), res.getString("quantidade"), res.getString("data"), res.getString("Razao_Social"), res.getString("Razao_Social") });
		}
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Problema para exibir registros!");
            }
	} else {
            JOptionPane.showMessageDialog(null, "O Estoque esta Limpo!");
	}
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProdutosSemEstoque = new javax.swing.JTable();
        jButtonAtualiza = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 255, 204));
        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produto Sem Estoque", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jTableProdutosSemEstoque.setAutoCreateRowSorter(true);
        jTableProdutosSemEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome Do Produto", "Quantidade", "Valor Da Comprar", "Data Da Entrada", "Fornecedor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProdutosSemEstoque);

        jButtonAtualiza.setText("Atualiza");
        jButtonAtualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonAtualiza)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButtonAtualiza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAtualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizaActionPerformed
        // TODO add your handling code here:
        jTableProdutosSemEstoque.repaint();
        jTableProdutosSemEstoque.revalidate();
        Tabela();
    }//GEN-LAST:event_jButtonAtualizaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualiza;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProdutosSemEstoque;
    // End of variables declaration//GEN-END:variables
}
