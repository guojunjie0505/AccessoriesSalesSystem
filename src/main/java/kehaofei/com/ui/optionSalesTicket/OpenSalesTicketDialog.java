package kehaofei.com.ui.optionSalesTicket;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JDialog;
import org.apache.log4j.Logger;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import javax.swing.JTextField;

import kehaofei.com.sm.model.GoodsInfoModel;
import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.ui.main.LeftView_TreePanel;
import kehaofei.com.ui.main.WMSMainFrame;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.ControlFactory;
import kehaofei.com.utils.DataChooser;
import kehaofei.com.utils.MathUtil;
import javax.swing.JSplitPane;

/**
 * �����۵�����
 * @author kehao
 *
 */
public class OpenSalesTicketDialog extends JDialog implements ActionListener, KeyListener  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(OpenSalesTicketDialog.class.getSimpleName());
	Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
	int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
	
	
	SalesTicketInfoModel salesTicketInfo;
	private JTextField text_date;
	private JTextField text_name;
	private JTextField text_leibie;

	private JButton button_insert;

	private JButton button_cancel;

	private JButton button_query;
	public static JSplitPane splitPane;

	/**
	 * Create the dialog.
	 * @param applicationModal 
	 * @param over 
	 */
	public OpenSalesTicketDialog(SalesTicketInfoModel salesTicketInfo) {
		super(WMSMainFrame.window, ModalityType.APPLICATION_MODAL);
		this.salesTicketInfo = salesTicketInfo;
		setBounds(100, 100, 1012, 640);
//		setResizable(false);
		setLocation(screenWidth/2-getWidth()/2, screenHeight/2-getHeight()/2);//���ô��ھ�����ʾ
		setTitle("��ѯ��ʷ���۵�����");
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {0, 50, 30, 50, 30, 30, 30, 80, 30, 30, 30, 30, 30, 30, 0, 0};
		gbl_contentPane.rowHeights = new int[]{30, 0, 0, 0, 0, 10, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0,Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("�˴�����ѡ���ѯ���۵�����Ϣ");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.gridwidth = 15;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel label = new JLabel("�������ڣ�");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		getContentPane().add(label, gbc_label);
		
		text_date = new JTextField(ByteUtils.getNowTimeStr("yyyy-MM-dd"));
		
		//����ѡ����
		text_date.setName("text_date");
		text_date.setColumns(15);	
//		text_date.setEditable(false);
		DataChooser ser = DataChooser.getInstance("yyyy-MM-dd",text_date);
		ser.register();
		
		text_date.addKeyListener(this);
		GridBagConstraints gbc_text_date = new GridBagConstraints();
		gbc_text_date.insets = new Insets(0, 0, 5, 5);
		gbc_text_date.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_date.gridx = 2;
		gbc_text_date.gridy = 2;
		getContentPane().add(text_date, gbc_text_date);
		text_date.setColumns(10);
		
		JLabel label_1 = new JLabel("\u540D\u79F0\uFF1A");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 2;
		getContentPane().add(label_1, gbc_label_1);
		
		text_name = new JTextField();
		text_name.addKeyListener(this);
		GridBagConstraints gbc_text_name = new GridBagConstraints();
		gbc_text_name.insets = new Insets(0, 0, 5, 5);
		gbc_text_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_name.gridx = 4;
		gbc_text_name.gridy = 2;
		getContentPane().add(text_name, gbc_text_name);
		text_name.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7C7B\u522B\uFF1A");
		label_2.setVisible(false);
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 5;
		gbc_label_2.gridy = 2;
		getContentPane().add(label_2, gbc_label_2);
		
		text_leibie = new JTextField();
		text_leibie.addKeyListener(this);
		text_leibie.setVisible(false);
		GridBagConstraints gbc_text_leibie = new GridBagConstraints();
		gbc_text_leibie.insets = new Insets(0, 0, 5, 5);
		gbc_text_leibie.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_leibie.gridx = 6;
		gbc_text_leibie.gridy = 2;
		getContentPane().add(text_leibie, gbc_text_leibie);
		text_leibie.setColumns(10);
		
		button_query = new JButton("��ѯ");
		button_query.setName("QUERY");
		button_query.addActionListener(this);
		GridBagConstraints gbc_button_query = new GridBagConstraints();
		gbc_button_query.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_query.insets = new Insets(0, 0, 5, 5);
		gbc_button_query.gridx = 7;
		gbc_button_query.gridy = 2;
		getContentPane().add(button_query, gbc_button_query);
		
		button_insert = new JButton("\u5BFC\u5165");
		button_insert.setName("button_insert");
		button_insert.setVisible(false);
		button_insert.addActionListener(this);
		GridBagConstraints gbc_button_insert = new GridBagConstraints();
		gbc_button_insert.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_insert.insets = new Insets(0, 0, 5, 5);
		gbc_button_insert.gridx = 12;
		gbc_button_insert.gridy = 3;
		getContentPane().add(button_insert, gbc_button_insert);
		
		button_cancel = new JButton("\u9000\u51FA");
		button_cancel.setName("button_cancel");
		button_cancel.setVisible(false);
		button_cancel.addActionListener(this);
		GridBagConstraints gbc_button_cancel = new GridBagConstraints();
		gbc_button_cancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_button_cancel.insets = new Insets(0, 0, 5, 5);
		gbc_button_cancel.gridx = 13;
		gbc_button_cancel.gridy = 3;
		getContentPane().add(button_cancel, gbc_button_cancel);
		
		splitPane = new JSplitPane();
		
		splitPane.setDividerLocation(200);
		splitPane.getRightComponent().setMinimumSize(splitPane.getPreferredSize());
		
		splitPane.setLeftComponent(new LeftView_TreePanel());//��������б�
		splitPane.setRightComponent(new SelectSalesTicketInfoPanel(salesTicketInfo, this));//�Ҳ��б����
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.gridwidth = 13;
		gbc_splitPane.insets = new Insets(0, 0, 5, 5);
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 1;
		gbc_splitPane.gridy = 4;
		getContentPane().add(splitPane, gbc_splitPane);
		
		setModalityType(ModalityType.APPLICATION_MODAL);//�Ի���򿪺����Ƹ����ڲ���	
		
	}
	
	/**
	 * ��ȡ��ѯ����
	 * @author XCCD
	 */
	private SalesTicketInfoModel getTextInfo(){
		SalesTicketInfoModel stim = new SalesTicketInfoModel();
		if(text_date.getText() != null && !text_date.getText().trim().equals("") ){
			stim.setXs_hao(text_date.getText().replaceAll("-", "")/*ByteUtils.dateFormat(text_date.getText(), "yyyy-MM-dd", "yyyyMMdd")*/);
		}
		if(text_name.getText() != null && !text_name.getText().trim().equals("") ){
			stim.setKehu_name(text_name.getText());
		}
		if(text_leibie.getText() != null && !text_leibie.getText().trim().equals("") ){
//			stim.setPeij_lb(text_leibie.getText());
		}
			
		return stim;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String className = e.getSource().getClass().getName();
		
		if(className.equals("javax.swing.JButton")){
			JButton bt = (JButton)e.getSource();
			String name = bt.getName();
			if("button_insert".equals(name)){//ȷ�ϸ��»�������
				
				SwingUtilities.invokeLater(new Runnable() {//����UI��ʾ����
					@Override
					public void run() {
						
						
						int index=1;
						if(ContextValue.SalesTicketInfoListData.size()==1){
							index =1;
						}else{
							index =1 + (Integer) ContextValue.SalesTicketInfoListData.get(ContextValue.SalesTicketInfoListData.size()-2)[3];
						}
						
						ContextValue.SalesTicketInfoListData.remove(ContextValue.SalesTicketInfoListData.size()-1);
						int i=0;
						for( ; i<ContextValue.STI_SelectedGoodsData.size(); i++){
							
							Object[] objData = new Object[]{
									"",//������ϸid
									"",//���۵���Id
									ContextValue.STI_SelectedGoodsData.get(i)[0],//��Ʒid
									index,
									Boolean.FALSE,
									ContextValue.STI_SelectedGoodsData.get(i)[3],
									ContextValue.STI_SelectedGoodsData.get(i)[4],
									ContextValue.STI_SelectedGoodsData.get(i)[5],
									ContextValue.STI_SelectedGoodsData.get(i)[6],
									ContextValue.STI_SelectedGoodsData.get(i)[7],
									ContextValue.STI_SelectedGoodsData.get(i)[8],
									ContextValue.STI_SelectedGoodsData.get(i)[10],//����
									1,
									ContextValue.STI_SelectedGoodsData.get(i)[10],//�����ǵ�����Ʒ�ܼ�
									""};
							ContextValue.SalesTicketInfoListData.add(objData);
							index ++;
						}
						
						
						BigDecimal total = new BigDecimal(Double.toString(0.00));
						for(Object[] obArr:ContextValue.SalesTicketInfoListData){
							total = MathUtil.add_BigDecimal(obArr[13], total);
						}
						
						
						if(i == ContextValue.STI_SelectedGoodsData.size()){
							ContextValue.SalesTicketInfoListData.add(new Object[]{"","","","�ϼƣ�",null,"","","","","","","","",total,""});
						}
						ContextValue.STI_SelectedGoodsData.clear();//��������ѡ����
						
						
						SwingUtilities.invokeLater(new Runnable(){
							@Override  
						    public void run() {
								
								SalesTicketInfoPanel.table.repaint();
								SalesTicketInfoPanel.table.revalidate();
								
							}
						});
					}
				});
				
				this.dispose();//������ɺ�رնԻ���
			}
			
			if("button_cancel".equals(name)){//�رձ༭ҳ��
				this.dispose();//�رնԻ���
			}
			
			if("QUERY".equals(name)){
				SalesTicketInfoModel ticket = getTextInfo();
				ControlFactory.salesTicketInfoController.LoadSalesTicketInfoVector(ticket);
				
				SwingUtilities.invokeLater(new Runnable() {//����UI��ʾ����
					@Override
					public void run() {
						SelectSalesTicketInfoPanel.table.repaint();
						SelectSalesTicketInfoPanel.table.revalidate();
					}
				});
			}
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		int k = e.getKeyCode();
		if (k == e.VK_ENTER) {// �س��¼�
			// �س�
//			SalesTicketInfoModel ticket = getTextInfo();
//			ControlFactory.salesTicketInfoController.LoadSalesTicketInfoVector(ticket);
//			
//			SwingUtilities.invokeLater(new Runnable() {//����UI��ʾ����
//				@Override
//				public void run() {
//					SelectSalesTicketInfoPanel.table.repaint();
//					SelectSalesTicketInfoPanel.table.revalidate();
//				}
//			});
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
