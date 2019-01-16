package kehaofei.com.ui.optionSalesTicket;

import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.ui.wmspanel.SalesTicketHeadPanel;
import kehaofei.com.ui_model.SelectTable;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.ContextValue;

/**
 * 选项卡面板界面
 * @author XCCD
 *
 */
public class SelectCustomerInfoPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable table;
	public static Integer width=0;
	ScrollPaneLayout gbl_contentPane = new ScrollPaneLayout();
	private JPopupMenu m_popupMenu;
	
	public List<CustomerInfoModel> customerInfoList;

	/**
	 * Create the panel.
	 * @param customerInfoList 
	 * @param selectCustomerDialog 
	 */
	public SelectCustomerInfoPanel(final List<CustomerInfoModel> customerInfoList, final SelectCustomerDialog selectCustomerDialog) {
//		ControlFactory.CUSTOMER_INFO.LoadCustomerInfoVector();
		this.customerInfoList = customerInfoList;
//		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("SendReceiveState")));

//		setVerticalScrollBar(new JScrollBar());
		getVerticalScrollBar().setUnitIncrement(20);

		table = new SelectTable(ContextValue.CustomerInfoListObj).getTable(ContextValue.CustomerInfoListData);
		
		// 设置表格第二列的列宽
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		
		//隐藏第一列ID列
		table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		table.setRowSelectionAllowed(true);
		
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
				
				int clickTimes = e.getClickCount();
				if (clickTimes == 2) {//double clicked be used
					System.out.println("Doublc Clicked!");
					
					//通过点击位置找到点击为表格中的行  
					int focusedRowIndex = table.rowAtPoint(e.getPoint());
					System.out.println(customerInfoList);
					SalesTicketHeadPanel.salesTicketInfo.setKehu_no(customerInfoList.get(focusedRowIndex).getKehu_no());
					SalesTicketHeadPanel.salesTicketInfo.setKehu_name(customerInfoList.get(focusedRowIndex).getKehu_name());
					SalesTicketHeadPanel.salesTicketInfo.setKehu_pinyin(customerInfoList.get(focusedRowIndex).getKehu_py());
					SalesTicketHeadPanel.salesTicketInfo.setKehu_tel(customerInfoList.get(focusedRowIndex).getTel()/*.trim()==null?customerInfoList.get(focusedRowIndex).getSj():customerInfoList.get(focusedRowIndex).getTel()*/);
					SalesTicketHeadPanel.salesTicketInfo.setXs_date(ByteUtils.getNowTimeStr("yyyy-MM-dd"));
					
					selectCustomerDialog.dispose();
					SelectCustomerDialog.kit.removeAWTEventListener(SelectCustomerDialog.listener);
				}
			}
		});
		
		setViewportView(table);
		
		setVisible(true);
	}

}
