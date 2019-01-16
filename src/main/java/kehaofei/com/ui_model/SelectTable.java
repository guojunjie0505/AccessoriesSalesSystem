package kehaofei.com.ui_model;

import java.awt.Rectangle;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

/**
 * ��̬table�����ʼ��
 * @author XCCD
 *
 */
public class SelectTable extends JTable{  
    /** 
     * ���л� 
     */  
    private static final long serialVersionUID = 1L;  
    private int myRow = -1, myCol = -1;  
    TableCellEditor myEditor;  
    private Object[] columnNames;
    private TableModel model;
    private Object[] objs;
    private JTable table;
    
    public SelectTable(Object[] objs){   	
    	this.objs = objs;
    }
    public SelectTable(TableModel model){
    	super();
    	this.model=model;
    
    }
    public void setComboCell(int r, int c, TableCellEditor ce) {  
        this.myRow = r;  
        this.myCol = c;  
        this.myEditor = ce;  
    }  
  
    @Override  
    public TableCellEditor getCellEditor(int row, int column) {  
        if (row == myRow && column == myCol && myEditor != null)  
            return myEditor;  
        return super.getCellEditor(row, column);  
    }  
    
	@SuppressWarnings("serial")
	public JTable getTable(Vector<Object[]> rowData) {
		
		columnNames = objs;
		
		// ��ʼ����ģ�ͣ�����DefaultTableModel������TableModel�Ľӿ�
		model = new TableModelDefine(rowData, columnNames);

		// ��ʼ��JTable
		table = new JTable(model);
		table.setRowHeight(25);
		
		// ���ñ���һ�е��п�
		TableColumn firsetColumn = table.getColumnModel().getColumn(0);
		firsetColumn.setPreferredWidth(0);
		firsetColumn.setMaxWidth(0);
		firsetColumn.setMinWidth(0);
		
		//���ص�һ��ID��
		table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		
		table.putClientProperty(
		   "Quaqua.Table.style", "striped"
		);
		
		table.setBounds(new Rectangle(1, 1));
		
		// Ϊ�����Ӽ�����
		/*table.addMouseListener(new MouseAdapter()

		  {

		   public void mouseClicked(MouseEvent e)

		   {
		    if (e.getClickCount() == 2)

		    // ʵ��˫��
		    {

		     int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // �����λ��

		     int col = ((JTable) e.getSource()).columnAtPoint(e
		       .getPoint()); // �����λ��
		     String cellVal = (String) (model.getValueAt(row, col)); // ��õ����Ԫ������
//		     txtboxRow.setText((row + 1) + "");
//		     txtboxCol.setText((col + 1) + "");

//		     txtboxContent.setText(cellVal);

		    } else
		     return;
		   }
		  });*/
		
		return table;
   }
	
}  
