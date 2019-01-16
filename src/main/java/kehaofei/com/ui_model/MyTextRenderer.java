package kehaofei.com.ui_model;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class MyTextRenderer extends JTextField implements TableCellRenderer {

	//editor show
    private JTextField textField = null;
	@Override
	public Component getTableCellRendererComponent( JTable table, Object value,
			boolean isSelected, boolean hasFocus,  int row,  int column) {
		// TODO Auto-generated method stub
//		table.editCellAt(row, column);
		textField = new JTextField();
		
		textField.setText((value == null) ? "" : value.toString());
//		textField.setHorizontalAlignment(JTextField.CENTER);
		switch(column){
			case 7:
			case 8:
			case 9:{
				textField.setEditable(false);
				textField.setBackground(table.getBackground());
			}break;
			case 11:
			{
				textField.setEditable(true);
				textField.setHorizontalAlignment(JTextField.RIGHT);
	//			textField.setBackground(table.getBackground());
			}break;
			case 12:
			{
				textField.setEditable(true);
				textField.setHorizontalAlignment(JTextField.CENTER);
	//			textField.setBackground(table.getBackground());
			}break;
			case 13:
			{
				textField.setEditable(false);
				textField.setHorizontalAlignment(JTextField.RIGHT);
	//			textField.setBackground(table.getBackground());
			}break;
			default :
			{
				textField.setEditable(true);
			}break;
		}
		
		textField.setBorder(new EmptyBorder(0,0,0,0));
		textField.setForeground(table.getForeground());             
		textField.setBackground(table.getBackground());
		textField.setSelectionColor(table.getSelectionForeground());
		return textField;
	}

	
	/**
	 * @author XCCD
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
