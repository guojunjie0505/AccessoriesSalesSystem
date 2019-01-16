package kehaofei.com.ui.optionSalesTicket;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingUtilities;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import kehaofei.com.ui_model.SelectTable;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.ControlFactory;

/**
 * ѡ�������
 * @author XCCD
 *
 */
public class SelectGoodsInfoPanel extends JScrollPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JTable table;
	public static Integer width=0;
	ScrollPaneLayout gbl_contentPane = new ScrollPaneLayout();
	private JPopupMenu m_popupMenu;

	/**
	 * Create the panel.
	 */
	public SelectGoodsInfoPanel() {
		ControlFactory.goodsInfoController.LoadGoodsInfoVector();
		
//		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("SendReceiveState")));

		setVerticalScrollBar(new JScrollBar());
		getVerticalScrollBar().setUnitIncrement(20);

		table = new SelectTable(ContextValue.GoodsInfoListObj).getTable(ContextValue.GoodsInfoListData);
		
		// ���ñ��ڶ��е��п�
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMaxWidth(50);
		table.getColumnModel().getColumn(1).setWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setMaxWidth(40);
		table.getColumnModel().getColumn(2).setWidth(40);
		table.getColumnModel().getColumn(2).setMinWidth(40);
		
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
				}else{//single clicked be used 
				
					switch(e.getButton()){
					
						case MouseEvent.BUTTON3://�����Ҽ�
						{
							//ͨ�����λ���ҵ����Ϊ����е���  
							int focusedRowIndex = table.rowAtPoint(e.getPoint());
							if (focusedRowIndex == -1) {
								return;
							}
							// �������ѡ����Ϊ��ǰ�Ҽ��������
							table.setRowSelectionInterval(focusedRowIndex,focusedRowIndex);
							// �����˵�
							
							createPopupMenu();
							
							m_popupMenu.show(table, e.getX(), e.getY());
						}break;
						
						case MouseEvent.BUTTON1://�������
						{
							//ͨ�����λ���ҵ����Ϊ����е���  
							int focusedRowIndex = table.rowAtPoint(e.getPoint());
							if (focusedRowIndex == -1) {
								return;
							}
							if(table.getSelectedColumn() == 2){
								return;
							}
							// �������ѡ����Ϊ��ǰ�Ҽ��������
							if((Boolean)table.getValueAt(focusedRowIndex, 2)){//�Ƿ��Ѿ�ѡ��
								for(int i=0; i<ContextValue.STI_SelectedGoodsData.size(); i++){
									
									if(ContextValue.GoodsInfoListData.get(focusedRowIndex)[0]
											.equals(ContextValue.STI_SelectedGoodsData.get(i)[0])){
										ContextValue.STI_SelectedGoodsData.remove(i);
									}
								}
								table.setValueAt(Boolean.FALSE, focusedRowIndex, 2);
							}else{
								boolean flag = true;
								//��һ���ж����۵����Ƿ��Ѿ����ڴ���Ʒ
								for(int i=0; i<ContextValue.SalesTicketInfoListData.size(); i++){
									
									if(ContextValue.GoodsInfoListData.get(focusedRowIndex)[0]
											.equals(ContextValue.SalesTicketInfoListData.get(i)[2])){
										flag = false;
										JOptionPane.showMessageDialog(SelectGoodsInfoPanel.this, "����Ʒ�Ѿ����");
										return;
									}
								}
								
								if(flag){//���۵��в�������Ʒ���ж�ѡ���б����Ƿ������Ʒ
									for(int i=0; i<ContextValue.STI_SelectedGoodsData.size(); i++){
										
										if(ContextValue.GoodsInfoListData.get(focusedRowIndex)[0]
												.equals(ContextValue.STI_SelectedGoodsData.get(i)[0])){
											flag = false;
											JOptionPane.showMessageDialog(SelectGoodsInfoPanel.this, "����Ʒ�Ѿ����");
											return;
										}
									}
								}
								
								ContextValue.STI_SelectedGoodsData.add(ContextValue.GoodsInfoListData.get(focusedRowIndex));
								table.setValueAt(Boolean.TRUE, focusedRowIndex, 2);
							}
//							table.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
//							table.addRowSelectionInterval(focusedRowIndex, focusedRowIndex);
							SwingUtilities.invokeLater(new Runnable(){
								@Override  
							    public void run() {
									 /*****��ʼ���¼�***/
									table.repaint();
									table.revalidate();
									
									SelectedGoodsInfoPanel.table.repaint();
									SelectedGoodsInfoPanel.table.revalidate();
									
								}
							});	
						}break;
					
					}
				}
			}
		});
		
		
		setViewportView(table);
		
		setVisible(true);
	}
	
	private void createPopupMenu() {  
        m_popupMenu = new JPopupMenu();  
          
        JMenuItem saveMenItem = new JMenuItem();
        saveMenItem.setText("�㳭����");  
        saveMenItem.addActionListener(new java.awt.event.ActionListener() {  
            public void actionPerformed(java.awt.event.ActionEvent evt) { 
                //�ò�����Ҫ������  
            }
        });  
        m_popupMenu.add(saveMenItem);
    }

}
