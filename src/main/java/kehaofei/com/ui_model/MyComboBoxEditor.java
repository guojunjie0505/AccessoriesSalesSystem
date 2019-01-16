package kehaofei.com.ui_model;

import java.awt.Component;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

import kehaofei.com.ui.optionGoods.EditGoodsInfoDialog;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.utils.enumeration.GetContextSelect;

import org.apache.log4j.Logger;

public class MyComboBoxEditor extends JComboBox implements TableCellEditor, KeyListener, FocusListener{
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	//editor show
    private JComboBox comboBox = null;
	private JTable table;
    
	//ChangeEvent用于通知感兴趣的参与者事件源中的状态已发生更改。
    private static ChangeEvent changeEvent = new ChangeEvent(MyComboBoxEditor.class);

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"getCellEditorValue\t"+
				table.getEditingRow()+","+table.getEditingColumn());
		return comboBox.getSelectedItem().toString();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"isCellEditable\t");
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
		comboBox.requestFocus();//获取焦点数据
		log.info("|------表格单元格编辑模型------|\t"+"shouldSelectCell\t"+
				table.getEditingRow()+","+table.getEditingColumn());
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"stopCellEditing\t"+
				table.getEditingRow()+","+table.getEditingColumn());
//		table.setValueAt(comboBox.getSelectedItem().toString(), row, column);
		return true;
	}
	
	public static void fireEditingStopped(){
        CellEditorListener listener;
        Object[] listeners = SalesTicketInfoPanel.listenerList.getListenerList();
        for(int i = 0; i < listeners.length; i++){
             if(listeners[i]== CellEditorListener.class){
                  //之所以是i+1，是因为一个为CellEditorListener.class（Class对象），
                  //接着的是一个CellEditorListener的实例
                  listener= (CellEditorListener)listeners[i+1];
                  //让changeEvent去通知编辑器已经结束编辑
                  //在editingStopped方法中，JTable调用getCellEditorValue()取回单元格的值，
                  //并且把这个值传递给TableValues(TableModel)的setValueAt()
                  listener.editingStopped(changeEvent);
             }
        }
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"cancelCellEditing\t");
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"addCellEditorListener\t"+
				table.getEditingRow()+","+table.getEditingColumn());
		
//		table.setValueAt(comboBox.getSelectedItem().toString(), row, column);
		SalesTicketInfoPanel.listenerList.add(CellEditorListener.class,l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"removeCellEditorListener");
		SalesTicketInfoPanel.listenerList.remove(CellEditorListener.class,l);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if(table != null && table.getEditorComponent() != null){
			table.getEditorComponent().transferFocus();
		}	
		MyTextEditor.fireEditingStopped();
		fireEditingStopped();
		table.clearSelection();
		
		this.table = SalesTicketInfoPanel.table;
		
		
		
		SalesTicketInfoPanel.row = row;
		SalesTicketInfoPanel.column = column;
		// TODO Auto-generated method stub
		comboBox = GetContextSelect.getCom_Unit();
//		comboBox.setBorder(new EmptyBorder(0,0,0,0));
		comboBox.setForeground(table.getForeground());             
		comboBox.setBackground(table.getBackground());
		comboBox.setSelectedItem((value == null) ? "" : value.toString());
		comboBox.setEditable(false);
		comboBox.addKeyListener(this);
		comboBox.addFocusListener(this);
		return comboBox;
	}

	/**
	 * @author XCCD
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch(e.getKeyCode()){
			
			case KeyEvent.VK_LEFT://键盘←方向键事件
			{
				if(SalesTicketInfoPanel.column <= 5){
					return;
				}
				table.getEditorComponent().transferFocus();
				fireEditingStopped();//请求终止编辑操作从JTable获得
				table.changeSelection(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column-1, true, true);
				table.editCellAt(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column-1);
				table.getEditorComponent().requestFocus();
				
				SwingUtilities.invokeLater(new Runnable(){
					@Override  
				    public void run() {						
						table.repaint();
						table.revalidate();
						
					}
				});
			}break;
			
			case KeyEvent.VK_RIGHT://键盘→方向键事件
			{
				if(SalesTicketInfoPanel.column == table.getColumnCount()-1){
					return;
				}
				table.getEditorComponent().transferFocus();
				fireEditingStopped();//请求终止编辑操作从JTable获得
				
				table.changeSelection(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column+1, true, true);
				table.editCellAt(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column+1);
				table.getEditorComponent().requestFocus();
				
				SwingUtilities.invokeLater(new Runnable(){
					@Override  
				    public void run() {						
						table.repaint();
						table.revalidate();						
					}
				});
			}break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------失去编辑焦点-------|\t"+
				table.getEditingRow()+","+table.getEditingColumn()
				+"\n"+e.getSource());
		
		if(e.getSource().getClass().equals("javax.swing.JComboBox")){
			table.getEditorComponent().transferFocus();
			fireEditingStopped();
		}
	}

}
