package com.ebarch.financeManager.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Ed
 */
public class BoldCenteredCellRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(new LineBorder(Color.BLACK));
        setFont((new Font("Tahoma", Font.BOLD, 12)));
        setText(value.toString());
        
        return this;
    }
    
}
