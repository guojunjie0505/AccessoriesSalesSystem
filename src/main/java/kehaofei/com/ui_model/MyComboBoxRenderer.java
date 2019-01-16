package kehaofei.com.ui_model;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

import kehaofei.com.utils.enumeration.GetContextSelect;

public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {

	private JComboBox comboBox;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		// TODO Auto-generated method stub
		
		comboBox = GetContextSelect.getCom_Unit();
//		setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(table.getForeground());
		comboBox.setBackground(table.getBackground());		
		comboBox.setSelectedItem((value == null) ? "" : value.toString());
		comboBox.setEditable(false);
		return comboBox;
	}

}
