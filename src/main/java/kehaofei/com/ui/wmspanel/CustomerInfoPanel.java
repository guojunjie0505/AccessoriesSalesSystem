package kehaofei.com.ui.wmspanel;

import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.ui.optionCustomer.EditCustomerInfoDialog;
import kehaofei.com.ui_model.SelectTable;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.ControlFactory;

/**
 * ѡ�������
 * @author XCCD
 *
 */
public class CustomerInfoPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	
	
	public static JTable table;
	public static Integer width=0;
	ScrollPaneLayout gbl_contentPane = new ScrollPaneLayout();
	private JPopupMenu m_popupMenu;

	/**
	 * Create the panel.
	 */
	public CustomerInfoPanel() {
		ControlFactory.customerInfoController.LoadCustomerInfoVector();
		
//		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("SendReceiveState")));

		setVerticalScrollBar(new JScrollBar());
		getVerticalScrollBar().setUnitIncrement(20);

		table = new SelectTable(ContextValue.CustomerInfoListObj).getTable(ContextValue.CustomerInfoListData);
		
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
		
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		for(int i =3; i<table.getColumnCount(); i++){
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
					try {
						CustomerInfoModel customerInfo = new CustomerInfoModel();
						
						int index = CustomerInfoPanel.table.getSelectedRow();
						
						if(index == -1){
							JOptionPane.showMessageDialog(null, "û��ѡ������");
							return;
						}else{
							customerInfo = ControlFactory.customerInfoController.queryById(
									(String) CustomerInfoPanel.table.getValueAt(index, 0));
						}
						
						EditCustomerInfoDialog egid = new EditCustomerInfoDialog(customerInfo);
						egid.setVisible(true);
					} catch (Exception e2) {
						// TODO: handle exception
						log.info("|------ �ͻ���Ϣ ------|\t ���ݳ�ʼ��ʧ��"+e2);
					}
				}else{
				
					if (e.getButton() == MouseEvent.BUTTON3) {
						
						//ͨ�����λ���ҵ����Ϊ����е���  
						int focusedRowIndex = table.rowAtPoint(e.getPoint());
						if (focusedRowIndex == -1) {
							return;
						}
						// �������ѡ����Ϊ��ǰ�Ҽ��������
						table.setRowSelectionInterval(focusedRowIndex,focusedRowIndex);
						// �����˵�
						
	//					createPopupMenu();
						
						m_popupMenu.show(table, e.getX(), e.getY());
					}
					
					if (e.getButton() == MouseEvent.BUTTON1) {
						
						//ͨ�����λ���ҵ����Ϊ����е���  
						int focusedRowIndex = table.rowAtPoint(e.getPoint());
						if (focusedRowIndex == -1) {
							return;
						}
						if(table.getSelectedColumn() == 2){
							return;
						}
						// �������ѡ����Ϊ��ǰ�Ҽ��������
						if((Boolean)table.getValueAt(focusedRowIndex, 2)){
							table.setValueAt(Boolean.FALSE, focusedRowIndex, 2);
						}else{
							table.setValueAt(Boolean.TRUE, focusedRowIndex, 2);
						}
						
						SwingUtilities.invokeLater(new Runnable(){
							@Override  
						    public void run() {
								 /*****��ʼ���¼�***/
								table.validate();
								table.updateUI();
								table.setRowHeight(25);
								table.revalidate();
								
							}
						});					
					}
				}
			}
		});
		
		setViewportView(table);
		
		setVisible(true);
	}
	
//	private void createPopupMenu() {  
//        m_popupMenu = new JPopupMenu();  
//          
//        JMenuItem saveMenItem = new JMenuItem();
//        saveMenItem.setText("�㳭����");  
//        saveMenItem.addActionListener(new java.awt.event.ActionListener() {  
//            public void actionPerformed(java.awt.event.ActionEvent evt) { 
//                //�ò�����Ҫ������  
//            }
//        });  
//        m_popupMenu.add(saveMenItem);
//    }

}
