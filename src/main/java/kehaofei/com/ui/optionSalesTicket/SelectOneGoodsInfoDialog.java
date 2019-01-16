package kehaofei.com.ui.optionSalesTicket;

import java.awt.AWTEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import org.apache.log4j.Logger;

import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.sm.model.GoodsInfoModel;
import kehaofei.com.ui.main.MessageDialog;
import kehaofei.com.ui.main.WMSMainFrame;
import kehaofei.com.ui.wmspanel.SalesTicketHeadPanel;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.ui_model.MyTextEditor;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.ContextValue;

import javax.swing.JTable;


/**
 * 
 * @author XCCD
 * <li>TODO	�������۵���ѯѡ��ͻ���Ϣ
 * <li>2017-7-11 ����5:09:21
 * <li>
 */
public class SelectOneGoodsInfoDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(SelectOneGoodsInfoDialog.class.getSimpleName());
	public static Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
	int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
	public static SelectOneGoodsInfoPanel table;
	
	int tableIndex = -1;

	public static ImplAWTEventListener listener;

	/**
	 * Create the dialog.
	 * @param goodsInfoModel 
	 * @param applicationModal 
	 * @param over 
	 */
	public SelectOneGoodsInfoDialog(List<GoodsInfoModel> goodsInfoList) {
		super(WMSMainFrame.window, ModalityType.APPLICATION_MODAL);;
		setBounds(100, 100, 838, 468);
//		setResizable(false);
		setLocation(screenWidth/2-getWidth()/2, screenHeight/2-getHeight()/2);//���ô��ھ�����ʾ
		setTitle("\u7F16\u8F91\u5546\u54C1\u4FE1\u606F");
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 80, 0, 0, 50, 0, 50, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{30, 0, 30, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("\u6B64\u5904\u7F16\u8F91\u5546\u54C1\u57FA\u672C\u4FE1\u606F");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.gridwidth = 9;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridheight = 2;
		gbc_tabbedPane.gridwidth = 9;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 1;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		JPanel edit_panel = new JPanel();
		tabbedPane.addTab("\u57FA\u672C\u4FE1\u606F", null, edit_panel, null);
		GridBagLayout gbl_edit_panel = new GridBagLayout();
		gbl_edit_panel.columnWidths = new int[]{30, 0, 100, 0, 0, 100, 0, 0, 0, 30, 0};
		gbl_edit_panel.rowHeights = new int[]{30, 0, 30, 0, 40, 0, 20, 0, 20, 0, 0, 0, 0};
		gbl_edit_panel.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_edit_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		edit_panel.setLayout(gbl_edit_panel);
		
		table = new SelectOneGoodsInfoPanel(goodsInfoList, this);
		table.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		table.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
//		table.table.requestFocusInWindow();
//		table.table.getSelectionModel().setSelectionInterval(0, 0);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 12;
		gbc_table.gridwidth = 10;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		edit_panel.add(table, gbc_table);
		
		setModalityType(ModalityType.APPLICATION_MODAL);//�Ի���򿪺����Ƹ����ڲ���	
		
//		addKeyListener(this);
		
		
		listener = new ImplAWTEventListener();
		
		this.kit.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);


		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		
		tableIndex = 0;				
		table.table.setRowSelectionInterval(tableIndex,tableIndex);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String className = e.getSource().getClass().getName();
		/*
		if(className.equals("javax.swing.JButton")){
			JButton bt = (JButton)e.getSource();
			String name = bt.getName();
			if("btn_confirm".equals(name)){//ȷ�ϸ��»�������
				
				this.dispose();//������ɺ�رնԻ���
			}
			
			if("btn_cancel".equals(name)){//�رձ༭ҳ��
				this.dispose();//�رնԻ���
			}
		}*/
		
	}

//	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_ENTER) {// �س��¼�
			
			
			if(tableIndex !=-1){
				//ͨ�����λ���ҵ����Ϊ����е���  
				
				for(Object[] ob : ContextValue.SalesTicketInfoListData){
					if(ob[2].equals(table.goodsInfoList.get(tableIndex).getPeij_no())){
						
//						JOptionPane.showMessageDialog(null, 
//						"���Ϊ "+table.goodsInfoList.get(tableIndex).getPeij_lb()+
//						"��"+table.goodsInfoList.get(tableIndex).getPeij_name()+" �Ѿ����ڣ��벻Ҫ�ظ����!","",JOptionPane.WARNING_MESSAGE);	
						
						
//						SelectOneGoodsInfoDialog.kit.removeAWTEventListener(SelectOneGoodsInfoDialog.listener);
//						
//						String message = "���Ϊ"+table.goodsInfoList.get(tableIndex).getPeij_lb()+
//								"��"+table.goodsInfoList.get(tableIndex).getPeij_name()+"�Ѿ����ڣ��벻Ҫ�ظ����!";	
//						
//						
//						MessageDialog messageDialog = new MessageDialog(message);
//						messageDialog.setFont(new Font("΢���ź�",0,10));
//						messageDialog.addWindowListener(new WindowListener() {
//							
//							@Override
//							public void windowOpened(WindowEvent e) {
//								// TODO Auto-generated method stub
//								
//							}
//							
//							@Override
//							public void windowIconified(WindowEvent e) {
//								// TODO Auto-generated method stub
//								
//							}
//							
//							@Override
//							public void windowDeiconified(WindowEvent e) {
//								// TODO Auto-generated method stub
//								
//							}
//							
//							@Override
//							public void windowDeactivated(WindowEvent e) {
//								// TODO Auto-generated method stub
//								log.info("windowDeactivated");
//								
//							}
//							
//							@Override
//							public void windowClosing(WindowEvent e) {
//								// TODO Auto-generated method stub
//								
//							}
//							
//							@Override
//							public void windowClosed(WindowEvent e) {
//								// TODO Auto-generated method stub
//								log.info("MessageDialog----windowClosed");							
//								MessageDialog.kit.removeAWTEventListener(MessageDialog.listener);
//								SelectOneGoodsInfoDialog.kit.addAWTEventListener(SelectOneGoodsInfoDialog.listener, AWTEvent.KEY_EVENT_MASK);
//							}
//							
//							@Override
//							public void windowActivated(WindowEvent e) {
//								// TODO Auto-generated method stub
//								
//							}
//						});
//						messageDialog.requestFocus();
//						messageDialog.setVisible(true);						
						return;
					}
				}
				
				Object[] objData = new Object[]{
						ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[0],//������ϸid
						ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[1],//���۵���Id
						table.goodsInfoList.get(tableIndex).getPeij_no(),//��Ʒid
						SalesTicketInfoPanel.row+1,
						Boolean.FALSE,
						table.goodsInfoList.get(tableIndex).getPeij_py(),
						table.goodsInfoList.get(tableIndex).getPeij_name(),
						table.goodsInfoList.get(tableIndex).getPeij_lb(),
						table.goodsInfoList.get(tableIndex).getBrand(),
						table.goodsInfoList.get(tableIndex).getCode(),//���
						table.goodsInfoList.get(tableIndex).getUnit().trim(),//��λ
						table.goodsInfoList.get(tableIndex).getOut_price1().toString(),//����
						1,
						table.goodsInfoList.get(tableIndex).getOut_price1().toString(),//�����ǵ�����Ʒ�ܼ�
						""};
				ContextValue.SalesTicketInfoListData.set(SalesTicketInfoPanel.row, objData);					
				
				this.dispose();
				SelectOneGoodsInfoDialog.kit.removeAWTEventListener(SelectOneGoodsInfoDialog.listener);
			}
			return;
		}
		
		if(k == KeyEvent.VK_DOWN){//�·����
//			table.table.requestFocus();			
			if(tableIndex ==-1){				
				tableIndex = 0;	
			}else if(tableIndex < table.table.getRowCount()-1){	
				tableIndex ++;
			}else{
				
			}
			table.table.setRowSelectionInterval(tableIndex,tableIndex);
			System.out.println("��ǰѡ����"+table.table.getValueAt(tableIndex, 4));
			
			if(SelectOneGoodsInfoDialog.kit.getAWTEventListeners().length == 0){
				SelectOneGoodsInfoDialog.kit.addAWTEventListener(SelectOneGoodsInfoDialog.listener, AWTEvent.KEY_EVENT_MASK);
			}
		}
		
		if(k == KeyEvent.VK_UP){//�Ϸ����							
			table.table.requestFocus();			
			if(tableIndex ==-1 || tableIndex==0){
				
			}else{				
				tableIndex --;
			}
			table.table.setRowSelectionInterval(tableIndex,tableIndex);
			System.out.println("��ǰѡ����"+table.table.getValueAt(tableIndex, 4));
			
			if(SelectOneGoodsInfoDialog.kit.getAWTEventListeners().length == 0){
				SelectOneGoodsInfoDialog.kit.addAWTEventListener(SelectOneGoodsInfoDialog.listener, AWTEvent.KEY_EVENT_MASK);
			}
		}
		
	}

	private class ImplAWTEventListener implements AWTEventListener {  
        @Override  
        public void eventDispatched(AWTEvent event) {  
              if (event.getClass() == KeyEvent.class) {  
                // ��������¼��Ǽ����¼�.  
                KeyEvent keyEvent = (KeyEvent) event;  
                if (keyEvent.getID() == KeyEvent.KEY_RELEASED) {  
                    //����ʱ��Ҫ��������  
                	keyReleased(keyEvent);  
                } 
            }  
        }
	}

}
