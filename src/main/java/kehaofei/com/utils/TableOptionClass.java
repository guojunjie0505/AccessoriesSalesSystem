package kehaofei.com.utils;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class TableOptionClass {
	
	/**
	 * ������Ҫ���ض�Ӧ��table��
	 * @author XCCD
	 * @param table
	 * @param column
	 */
	public static void HiddenTableColumn(JTable table, Integer column){
		// ���ñ���һ�е��п�
		TableColumn firsetColumn = table.getColumnModel().getColumn(column);
		firsetColumn.setPreferredWidth(0);
		firsetColumn.setMaxWidth(0);
		firsetColumn.setWidth(0);
		firsetColumn.setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
	}
	
	/**
	 * ����tableָ���пɱ༭
	 * @param table
	 * @param column
	 */
	public static void SetEditTableColumn(JTable table, Integer column){
		// ���ñ���һ�е��п�
		TableColumn firsetColumn = table.getColumnModel().getColumn(column);
		firsetColumn.setPreferredWidth(0);
		firsetColumn.setMaxWidth(0);
		firsetColumn.setWidth(0);
		firsetColumn.setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
	}

}
