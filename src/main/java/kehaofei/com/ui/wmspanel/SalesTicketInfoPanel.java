package kehaofei.com.ui.wmspanel;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.ui_model.EditTable;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.MathUtil;

/**
 * 选项卡面板界面
 * @author XCCD
 *
 */
public class SalesTicketInfoPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable table;
	public static Integer width=0;
	ScrollPaneLayout gbl_contentPane = new ScrollPaneLayout();
	private JPopupMenu m_popupMenu;
	
	private SalesTicketInfoModel salesTicketInfo;
	private int rowIndex;
	private JTable tableButtom;
	
	public static int row = 0;
	public static int column = 0;
	//EventListenerList:保存EventListener 列表的类。
	public static EventListenerList listenerList = new EventListenerList();

	/**
	 * Create the panel.
	 * @param salesTicketInfo 
	 */
	public SalesTicketInfoPanel(SalesTicketInfoModel salesTicketInfo) {
//		ControlFactory.CUSTOMER_INFO.LoadCustomerInfoVector();
		
		this.salesTicketInfo = salesTicketInfo;//界面数据实体
		
		if(ContextValue.SalesTicketInfoListData.size() == 0){
			ContextValue.SalesTicketInfoListData.add(new Object[]{"","","",1,Boolean.FALSE,"","","","","","","","","",""});
			ContextValue.SalesTicketInfoListData.add(new Object[]{"","","","合计：",Boolean.FALSE,"","","","","","","","","0.00",""});
		}
		
		
//		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("SendReceiveState")));
		getVerticalScrollBar().setUnitIncrement(20);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		table = new EditTable(ContextValue.SalesTicketInfoListObj).getTable(ContextValue.SalesTicketInfoListData);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(0);
		table.getColumnModel().getColumn(1).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setWidth(0);
		table.getColumnModel().getColumn(1).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		// 设置表格第二列的列宽
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		
		
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setWidth(0);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
		
		// 设置名称列的列宽
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setWidth(150);
		table.getColumnModel().getColumn(6).setMinWidth(150);
		
		// 设置名称列的列宽
		table.getColumnModel().getColumn(14).setPreferredWidth(150);
		table.getColumnModel().getColumn(14).setWidth(150);
		table.getColumnModel().getColumn(14).setMinWidth(150);
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getButton() == MouseEvent.BUTTON3) {
					
					//通过点击位置找到点击为表格中的行  
					int focusedRowIndex = table.rowAtPoint(e.getPoint());
					if (focusedRowIndex == -1) {
						return;
					}
					// 将表格所选项设为当前右键点击的行
					table.setRowSelectionInterval(focusedRowIndex,focusedRowIndex);
					// 弹出菜单
				}
				
				if (e.getButton() == MouseEvent.BUTTON1) {//左键单击
					
					//通过点击位置找到点击为表格中的行  
					final int focusedRowIndex = table.rowAtPoint(e.getPoint());
					final int focusedColIndex = table.columnAtPoint(e.getPoint());
					if (focusedRowIndex == -1) {
						return;
					}
					
					if(focusedColIndex == 3 && focusedRowIndex<table.getRowCount()-1){
						table.setRowSelectionAllowed(true);						
						table.setRowSelectionInterval(focusedRowIndex,focusedRowIndex);
					}else{
						table.setRowSelectionAllowed(false);
					}
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override  
					    public void run() {
							
							table.repaint();
							table.revalidate();
							
						}
					});					
				}
			}
		});
	
		setViewportView(table);
		
		setVisible(true);
		
//		tableButtom = new JTable();
//		setViewportView(table);		
//		setVisible(true);
	}

}
