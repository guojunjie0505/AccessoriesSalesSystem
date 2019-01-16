package kehaofei.com.ui.optionSalesTicket;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.sm.model.GoodsInfoModel;
import kehaofei.com.ui.wmspanel.SalesTicketHeadPanel;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.ui_model.SelectTable;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.ContextValue;

/**
 * ѡ�������
 * @author XCCD
 *
 */
public class SelectOneGoodsInfoPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable table;
	public static Integer width=0;
	ScrollPaneLayout gbl_contentPane = new ScrollPaneLayout();
	private JPopupMenu m_popupMenu;
	
	public List<GoodsInfoModel> goodsInfoList;

	/**
	 * Create the panel.
	 * @param customerInfoList 
	 * @param selectCustomerDialog 
	 */
	public SelectOneGoodsInfoPanel(final List<GoodsInfoModel> goodsInfoList, final SelectOneGoodsInfoDialog selectOneGoodsInfoDialog) {

		this.goodsInfoList = goodsInfoList;

		setVerticalScrollBar(new JScrollBar());
		getVerticalScrollBar().setUnitIncrement(20);

		table = new SelectTable(ContextValue.GoodsInfoListObj).getTable(ContextValue.GoodsInfoListData);
		
		// ���ñ��ڶ��е��п�
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(0);
		table.getColumnModel().getColumn(2).setMaxWidth(0);
		table.getColumnModel().getColumn(2).setWidth(0);
		table.getColumnModel().getColumn(2).setMinWidth(0);	
		//���ص�һ��ID��
		table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
		table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
		
		// ���������е��п�
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setWidth(150);
		table.getColumnModel().getColumn(4).setMinWidth(150);
		
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
					
					
					//ͨ�����λ���ҵ����Ϊ����е���  
					int tableIndex = table.rowAtPoint(e.getPoint());

					for(Object[] ob : ContextValue.SalesTicketInfoListData){
						if(ob[2].equals(goodsInfoList.get(tableIndex).getPeij_no())){
							JOptionPane.showMessageDialog(null, 
									"���Ϊ"+goodsInfoList.get(tableIndex).getPeij_lb()+
									"��"+goodsInfoList.get(tableIndex).getPeij_name()+"�Ѿ����ڣ��벻Ҫ�ظ����!");	
							return;
						}
					}
					
					Object[] objData = new Object[]{
							ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[0],//������ϸid
							ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[1],//���۵���Id
							goodsInfoList.get(tableIndex).getPeij_no(),//��Ʒid
							SalesTicketInfoPanel.row+1,
							Boolean.FALSE,
							goodsInfoList.get(tableIndex).getPeij_py(),
							goodsInfoList.get(tableIndex).getPeij_name(),
							goodsInfoList.get(tableIndex).getPeij_lb(),
							goodsInfoList.get(tableIndex).getBrand(),
							goodsInfoList.get(tableIndex).getCode(),//���
							goodsInfoList.get(tableIndex).getUnit().trim(),//��λ
							goodsInfoList.get(tableIndex).getOut_price1().toString(),//����
							1,
							goodsInfoList.get(tableIndex).getOut_price1().toString(),//�����ǵ�����Ʒ�ܼ�
							""};
					ContextValue.SalesTicketInfoListData.set(SalesTicketInfoPanel.row, objData);
					
					selectOneGoodsInfoDialog.dispose();
					
					selectOneGoodsInfoDialog.kit.removeAWTEventListener(selectOneGoodsInfoDialog.listener);
				}
			}
		});
		
		setViewportView(table);
		
		setVisible(true);
	}

}
