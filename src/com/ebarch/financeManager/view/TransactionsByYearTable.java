package com.ebarch.financeManager.view;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ed
 */
public class TransactionsByYearTable extends JTable {
    public TransactionsByYearTable(DefaultTableModel tableModel) {
        super(tableModel);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component component = super.prepareRenderer(renderer, row, column);

        component.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
        
        if (column >= 1) {
            DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
            cellRenderer.setHorizontalAlignment(JLabel.RIGHT);
            this.getColumnModel().getColumn(column).setCellRenderer(cellRenderer);
        }

        return component;
    }
    
//    @Override
//    public void setTableHeader(JTableHeader tableHeader) {
//        super.setTableHeader(tableHeader);        
//    }
//    
//    @Override
//    public JTableHeader getTableHeader() {
//        return this.tableHeader;
//    }

    @Override
    public boolean isCellEditable(int row, int column) {
       // We don't want the user to edit the table.
       return false;
    }
}
