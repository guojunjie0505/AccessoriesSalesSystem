package kehaofei.com.ui.optionSalesTicket;

import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.ui.wmspanel.SalesTicketHeadPanel;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.ui_model.SelectTable;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.ControlFactory;

/**
 * ���۵��б�
 * @author XCCD
 *
 */
public class SelectSalesTicketInfoPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable table;
	public static Integer width=0;
	ScrollPaneLayout gbl_contentPane = new ScrollPaneLayout();
	private JPopupMenu m_popupMenu;
	
//	private static OpenSalesTicketDialog openSalesTicketDialog;

	/**
	 * Create the panel.
	 * @param salesTicketInfo 
	 * @param openSalesTicketDialog 
	 */
	public SelectSalesTicketInfoPanel(SalesTicketInfoModel salesTicketInfo, final OpenSalesTicketDialog openSalesTicketDialog) {

		ControlFactory.salesTicketInfoController.LoadSalesTicketInfoVector(salesTicketInfo);
		
//		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("SendReceiveState")));

		setVerticalScrollBar(new JScrollBar());
		getVerticalScrollBar().setUnitIncrement(20);

		table = new SelectTable(ContextValue.SelectSalesTicketInfoListObj).getTable(ContextValue.SelectSalesTicketInfoListData);
		
		// ���ñ��ڶ��е��п�
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);
		table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setWidth(200);
		table.getColumnModel().getColumn(3).setMinWidth(200);
		
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		
		for(int i =4; i<table.getColumnCount(); i++){
			table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
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
					
					//ͨ�����λ���ҵ����Ϊ����е���  
					int focusedRowIndex = table.rowAtPoint(e.getPoint());
					
					SalesTicketInfoModel model = new SalesTicketInfoModel();
					Object xs_hao = ContextValue.SelectSalesTicketInfoListData.get(focusedRowIndex)[0];
					model.setXs_hao(xs_hao.toString());
					
					SalesTicketHeadPanel.salesTicketInfo = ControlFactory.salesTicketInfoController.getInfoById(model).get(0);
					
					SalesTicketInfoPanel.row = 0;
					SalesTicketInfoPanel.column = 0;
					openSalesTicketDialog.dispose();
				}else{//single clicked be used 

				}
			}
		});
		
		
		setViewportView(table);
		
		setVisible(true);
	}


}
