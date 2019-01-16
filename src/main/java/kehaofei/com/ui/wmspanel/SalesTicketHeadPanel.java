package kehaofei.com.ui.wmspanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterJob;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.sm.model.SalesTicketDetailsModel;
import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.ui.optionSalesTicket.OpenSalesTicketDialog;
import kehaofei.com.ui.optionSalesTicket.PrintUIComponent;
import kehaofei.com.ui.optionSalesTicket.SelectCustomerDialog;
import kehaofei.com.ui.optionSalesTicket.SelectGoodsDialog;
import kehaofei.com.ui.panel.BtnMenuGroupPanel;
import kehaofei.com.ui_model.MyTextEditor;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.Constant_Properties;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.ControlFactory;
import kehaofei.com.utils.MathUtil;
import kehaofei.com.utils.WriteParam;

import javax.swing.JLabel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;


/**
 * 
 * @author XCCD
 * <li>TODO	��Ʒ��Ϣ�Ĳ�ѯ����
 * <li>2017-6-1 ����1:48:27
 * <li>
 */
public class SalesTicketHeadPanel extends JPanel implements ActionListener,KeyListener/*,Printable*/ {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(SalesTicketHeadPanel.class.getSimpleName());
	
	
	private JLabel label;
	private JTextField text_pinyin;
	private JLabel label_1;
	private JTextField text_name;
	private JLabel label_2;
	private JTextField text_xs_hao;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton btn_import;
	private JButton button_6;
	private JLabel label_msg;
	public static JTextField text_message;
	private JLabel lblNewLabel;
	private JTextField text_tel;
	private JLabel label_3;
	private JTextField text_totalMoney;
	private JLabel label_4;
	private JTextField text_xs_date;
	private Object[] xs_noArr;
	List<Integer> list_arr = new ArrayList<Integer>();//��Ҫɾ������ϸid����
	public WriteParam wp = new WriteParam();
	
	
	public static SalesTicketInfoModel salesTicketInfo;
	PrintUIComponent print;
	
	
	//����������������������������������������������������������ӡ���ݡ�������������������������������������������������������������������
//	private PrinterJob job;
//	private int[] pageBreaks;// array of page break line positions
	
	/**
	 * Create the panel.
	 * @param salesTicketInfo 
	 * @param up 
	 * @param down 
	 */
	public SalesTicketHeadPanel(SalesTicketInfoModel salesTicketInfo) {
		this.salesTicketInfo = salesTicketInfo;
		print = new PrintUIComponent();
		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("FunctionBlock")));
		
		
		GridBagLayout gbl_btnPanel = new GridBagLayout();
		gbl_btnPanel.columnWidths = new int[]{10, 70, 70, 70, 70, 70, 70, 70, 40, 40, 40, 40, 40, 40, 40, 0, 0, 0};
		gbl_btnPanel.rowHeights = new int[]{60, 0, 50, 0, 0};
		gbl_btnPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_btnPanel.rowWeights = new double[]{0.3, 0.0, 0.3, 0.0, Double.MIN_VALUE};
		setLayout(gbl_btnPanel);
	
		
		button_1 = new JButton(
				Constant_Properties.myResource.getString("Add"),
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/add.jpg")));
		button_1.setVerticalTextPosition(JButton.BOTTOM);
		button_1.setHorizontalTextPosition(JButton.CENTER);
		button_1.setName("ADD");
		button_1.addActionListener(this);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.insets = new Insets(5, 0, 10, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		add(button_1, gbc_button_1);
		
		button_2 = new JButton("�򿪵���",
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/update.jpg")));
		button_2.setVerticalTextPosition(JButton.BOTTOM);
		button_2.setHorizontalTextPosition(JButton.CENTER);
		button_2.setName("OPEN_TICKET");
		button_2.addActionListener(this);
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.fill = GridBagConstraints.BOTH;
		gbc_button_2.insets = new Insets(5, 0, 10, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 0;
		add(button_2, gbc_button_2);
		
		button_3 = new JButton(Constant_Properties.myResource.getString("Delete"),
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/delete.jpg")));
		button_3.setVerticalTextPosition(JButton.BOTTOM);
		button_3.setHorizontalTextPosition(JButton.CENTER);
		button_3.setName("DELETE");
		button_3.addActionListener(this);
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.fill = GridBagConstraints.BOTH;
		gbc_button_3.insets = new Insets(5, 0, 10, 5);
		gbc_button_3.gridx = 3;
		gbc_button_3.gridy = 0;
		add(button_3, gbc_button_3);
		
		button_4 = new JButton("Ԥ��",
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/preview.jpg")));
		button_4.setVerticalTextPosition(JButton.BOTTOM);
		button_4.setHorizontalTextPosition(JButton.CENTER);
		button_4.setName("PREVIEW");
		button_4.addActionListener(this);
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.fill = GridBagConstraints.BOTH;
		gbc_button_4.insets = new Insets(5, 0, 10, 5);
		gbc_button_4.gridx = 4;
		gbc_button_4.gridy = 0;
		add(button_4, gbc_button_4);
		
		button_5 = new JButton("��ӡ",
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/print.jpg")));
		button_5.setVerticalTextPosition(JButton.BOTTOM);
		button_5.setHorizontalTextPosition(JButton.CENTER);
		button_5.setName("PRINT");
		button_5.addActionListener(this);
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.fill = GridBagConstraints.BOTH;
		gbc_button_5.insets = new Insets(5, 0, 10, 5);
		gbc_button_5.gridx = 5;
		gbc_button_5.gridy = 0;
		add(button_5, gbc_button_5);
		
		btn_import = new JButton("\u5BFC\u5165",
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/excel.jpg")));
		btn_import.setVerticalTextPosition(JButton.BOTTOM);
		btn_import.setHorizontalTextPosition(JButton.CENTER);
		btn_import.setName("IMPORT");
		btn_import.addActionListener(this);
		GridBagConstraints gbc_btn_import = new GridBagConstraints();
		gbc_btn_import.fill = GridBagConstraints.BOTH;
		gbc_btn_import.insets = new Insets(5, 0, 10, 5);
		gbc_btn_import.gridx = 6;
		gbc_btn_import.gridy = 0;
		add(btn_import, gbc_btn_import);
		
		button_6 = new JButton("�˳�",
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/close.jpg")));
		button_6.setVerticalTextPosition(JButton.BOTTOM);
		button_6.setHorizontalTextPosition(JButton.CENTER);
		button_6.setName("CANCEL");
		button_6.addActionListener(this);
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.fill = GridBagConstraints.BOTH;
		gbc_button_6.insets = new Insets(5, 0, 10, 5);
		gbc_button_6.gridx = 7;
		gbc_button_6.gridy = 0;
		add(button_6, gbc_button_6);
		
		label_msg = new JLabel("\u901A\u77E5\uFF1A");
		GridBagConstraints gbc_label_msg = new GridBagConstraints();
		gbc_label_msg.anchor = GridBagConstraints.EAST;
		gbc_label_msg.insets = new Insets(0, 0, 5, 5);
		gbc_label_msg.gridx = 1;
		gbc_label_msg.gridy = 1;
		add(label_msg, gbc_label_msg);
		
		text_message = new JTextField();
		text_message.setText(wp.getParam("message"));
		text_message.setName("message");
		text_message.addKeyListener(this);
				//"�������ֻ�-15169932566��ũ��-6228411840001105918.�������-6236994681000010313.������-6223191311792118");
		GridBagConstraints gbc_text_message = new GridBagConstraints();
		gbc_text_message.gridwidth = 14;
		gbc_text_message.insets = new Insets(0, 0, 5, 5);
		gbc_text_message.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_message.gridx = 2;
		gbc_text_message.gridy = 1;
		add(text_message, gbc_text_message);
		text_message.setColumns(10);
		
		
		label = new JLabel("\u5BA2\u6237\u62FC\u97F3\uFF1A");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		add(label, gbc_label);
		
		text_pinyin = new JTextField();
		text_pinyin.setName("PINYIN");
		text_pinyin.addKeyListener(this);
		GridBagConstraints gbc_text_pinyin = new GridBagConstraints();
		gbc_text_pinyin.gridwidth = 2;
		gbc_text_pinyin.insets = new Insets(0, 0, 5, 5);
		gbc_text_pinyin.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_pinyin.gridx = 2;
		gbc_text_pinyin.gridy = 2;
		add(text_pinyin, gbc_text_pinyin);
		text_pinyin.setColumns(10);
		
		label_1 = new JLabel("\u5BA2\u6237\u540D\u79F0\uFF1A");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		text_name = new JTextField();
//		text_name.setName("PINYIN");
//		text_name.addKeyListener(this);
		GridBagConstraints gbc_text_name = new GridBagConstraints();
		gbc_text_name.gridwidth = 2;
		gbc_text_name.insets = new Insets(0, 0, 5, 5);
		gbc_text_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_name.gridx = 5;
		gbc_text_name.gridy = 2;
		add(text_name, gbc_text_name);
		text_name.setColumns(10);
		
		label_2 = new JLabel("\u9500\u552E\u5355\u53F7\uFF1A");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 7;
		gbc_label_2.gridy = 2;
		add(label_2, gbc_label_2);
		
		text_xs_hao = new JTextField();
//		text_xs_hao.setName("PINYIN");
//		text_xs_hao.addKeyListener(this);
		GridBagConstraints gbc_text_xs_hao = new GridBagConstraints();
		gbc_text_xs_hao.gridwidth = 5;
		gbc_text_xs_hao.insets = new Insets(0, 0, 5, 5);
		gbc_text_xs_hao.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_xs_hao.gridx = 8;
		gbc_text_xs_hao.gridy = 2;
		add(text_xs_hao, gbc_text_xs_hao);
		text_xs_hao.setColumns(10);
		text_xs_hao.setEditable(false);
		
		//�˰�ť���ڱ������۵�����
		button = new JButton(Constant_Properties.myResource.getString("Save"));
		button.setName("SAVE");
		button.addActionListener(this);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.gridwidth = 2;
		gbc_button.gridheight = 2;
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(10, 0, 10, 5);
		gbc_button.gridx = 13;
		gbc_button.gridy = 2;
		add(button, gbc_button);
		
		lblNewLabel = new JLabel("\u7535\u8BDD\uFF1A");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 3;
		add(lblNewLabel, gbc_lblNewLabel);
		
		text_tel = new JTextField();
		GridBagConstraints gbc_text_tel = new GridBagConstraints();
		gbc_text_tel.gridwidth = 2;
		gbc_text_tel.insets = new Insets(0, 0, 0, 5);
		gbc_text_tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_tel.gridx = 2;
		gbc_text_tel.gridy = 3;
		add(text_tel, gbc_text_tel);
		text_tel.setColumns(10);
		
		label_3 = new JLabel("\u603B\u91D1\u989D\uFF1A");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 0, 5);
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 3;
		add(label_3, gbc_label_3);
		
		text_totalMoney = new JTextField();
		GridBagConstraints gbc_text_totalMoney = new GridBagConstraints();
		gbc_text_totalMoney.gridwidth = 2;
		gbc_text_totalMoney.insets = new Insets(0, 0, 0, 5);
		gbc_text_totalMoney.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_totalMoney.gridx = 5;
		gbc_text_totalMoney.gridy = 3;
		add(text_totalMoney, gbc_text_totalMoney);
		text_totalMoney.setColumns(10);
		
		label_4 = new JLabel("\u9500\u552E\u65E5\u671F\uFF1A");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.EAST;
		gbc_label_4.insets = new Insets(0, 0, 0, 5);
		gbc_label_4.gridx = 7;
		gbc_label_4.gridy = 3;
		add(label_4, gbc_label_4);
		
		text_xs_date = new JTextField(ByteUtils.getNowTimeStr("yyyy-MM-dd"));
		GridBagConstraints gbc_text_xs_date = new GridBagConstraints();
		gbc_text_xs_date.gridwidth = 3;
		gbc_text_xs_date.insets = new Insets(0, 0, 0, 5);
		gbc_text_xs_date.fill = GridBagConstraints.HORIZONTAL;
		gbc_text_xs_date.gridx = 8;
		gbc_text_xs_date.gridy = 3;
		add(text_xs_date, gbc_text_xs_date);
		text_xs_date.setColumns(10);
		text_xs_date.setEditable(false);
		
		initData();
		
	}
	
	/**
	 * ��ʼ����������
	 * @author XCCD
	 */
	@SuppressWarnings("static-access")
	public void initData(){
		if(this.salesTicketInfo != null && this.salesTicketInfo.getXs_hao() != null){//��ʾ�޸���������
			
			//��ѯ�����Ŀͻ���Ϣ
			CustomerInfoModel customer = ControlFactory.customerInfoController.queryById(this.salesTicketInfo.getKehu_no());
			
			text_name.setText(customer.getKehu_name());
			text_pinyin.setText(customer.getKehu_py());
			text_xs_hao.setText(this.salesTicketInfo.getXs_hao());
			text_tel.setText(customer.getTel());
			text_totalMoney.setText(
					this.salesTicketInfo.getXs_moneyall()==null?
							"0.00":String.valueOf(this.salesTicketInfo.getXs_moneyall().doubleValue()));
			
			text_xs_date.setText(this.salesTicketInfo.getXs_date()/*ByteUtils.dateFormat(this.salesTicketInfo.getXs_date(),"yyyy/M/d", "yyyy-MM-dd")*/);
			
		}else if(this.salesTicketInfo != null 
				&& this.salesTicketInfo.getXs_hao() ==null 
				&& this.salesTicketInfo.getKehu_no() != null){//��������
			text_name.setText(this.salesTicketInfo.getKehu_name());
			text_pinyin.setText(this.salesTicketInfo.getKehu_pinyin());
			text_xs_hao.setText(this.salesTicketInfo.getXs_hao());
			text_tel.setText(this.salesTicketInfo.getKehu_tel());
			text_totalMoney.setText(
					this.salesTicketInfo.getXs_moneyall()==null?
							"0.00":String.valueOf(this.salesTicketInfo.getXs_moneyall().doubleValue()));
			
			text_xs_date.setText(this.salesTicketInfo.getXs_date()/*ByteUtils.dateFormat(this.salesTicketInfo.getXs_date(),"yyyy/M/d", "yyyy-MM-dd")*/);
		}
		else{
			this.salesTicketInfo = new SalesTicketInfoModel();
			
			text_name.setText(this.salesTicketInfo.getKehu_name());
			text_pinyin.setText(this.salesTicketInfo.getKehu_pinyin());
			text_xs_hao.setText(this.salesTicketInfo.getXs_hao());
			text_tel.setText(this.salesTicketInfo.getKehu_tel());
			text_totalMoney.setText(
					this.salesTicketInfo.getXs_moneyall()==null?
							"0.00":String.valueOf(this.salesTicketInfo.getXs_moneyall().doubleValue()));
			
			text_xs_date.setText(ByteUtils.getNowTimeStr("yyyy-MM-dd"));
		}
	}

	/**
	 * ���±������
	 * @author XCCD
	 */
	public void updateTableData(){

		ContextValue.SalesTicketInfoListData.clear();
		int index=1;
		int i=0;
		for( ; i<salesTicketInfo.getSalesTicketDetails().size(); i++){
			
			Object[] objData = new Object[]{
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_no(),//������ϸid
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_hao(),//���۵���Id
					salesTicketInfo.getSalesTicketDetails().get(i).getPeij_no(),//��Ʒid
					index,
					Boolean.FALSE,
					salesTicketInfo.getSalesTicketDetails().get(i).getCk_name(),
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_name(),
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_pjlb(),
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_brand()==null?"":salesTicketInfo.getSalesTicketDetails().get(i).getXs_brand(),
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_code()==null?"":salesTicketInfo.getSalesTicketDetails().get(i).getXs_code(),
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_unit(),					
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_price(),//����
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_num(),
					salesTicketInfo.getSalesTicketDetails().get(i).getXs_money(),//�����ǵ�����Ʒ�ܼ�
					salesTicketInfo.getSalesTicketDetails().get(i).getComment()==null?"":salesTicketInfo.getSalesTicketDetails().get(i).getComment()};
			ContextValue.SalesTicketInfoListData.add(objData);
			index ++;
		}
		
		
		BigDecimal total = new BigDecimal(Double.toString(0.00));
		for(Object[] obArr:ContextValue.SalesTicketInfoListData){
			total = MathUtil.add_BigDecimal(obArr[13], total);
		}
		
		ContextValue.SalesTicketInfoListData.add(new Object[]{"","","","�ϼƣ�",null,"","","","","","","","",total,""});
		
	}
	/**
	 * ��ȡ��ѯ����
	 * @author XCCD
	 */
	private CustomerInfoModel getTextInfo(){
		CustomerInfoModel customerInfo = new CustomerInfoModel();
		if(text_pinyin.getText() != null && !text_pinyin.getText().trim().equals("") ){
			customerInfo.setKehu_py(text_pinyin.getText());
		}
//		if(text_name.getText() != null && !text_name.getText().trim().equals("") ){
//			customerInfo.setKehu_name(text_name.getText());
//		}
//		if(text_xs_hao.getText() != null && !text_xs_hao.getText().trim().equals("") ){
//			customerInfo.setLxr(text_xs_hao.getText());
//		}
//		if(text_message.getText() != null && !text_message.getText().trim().equals("") ){
//			customerInfo.setTel(text_message.getText());
//		}		
		return customerInfo;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String className = e.getSource().getClass().getName();
		
		if (SalesTicketInfoPanel.table.isEditing()){ 
			MyTextEditor.fireEditingStopped();
		}
		
		//�˶Դ��¼�������
		autoCalculate();

		if(className.equals("javax.swing.JButton")){
			JButton bt = (JButton)e.getSource();
			String name = bt.getName();
			
			if("ADD".equals(name)){
				try {
					this.salesTicketInfo = new SalesTicketInfoModel();
					ContextValue.SalesTicketInfoListData.clear();
					ContextValue.SalesTicketInfoListData.add(new Object[]{"","","",1,Boolean.FALSE,"","","","","","","","","",""});
					ContextValue.SalesTicketInfoListData.add(new Object[]{"","","","�ϼƣ�",Boolean.FALSE,"","","","","","","","",0.00,""});
					initData();
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							
							repaint();
							revalidate();
							SalesTicketInfoPanel.table.updateUI();
							SalesTicketInfoPanel.table.repaint();
							SalesTicketInfoPanel.table.revalidate();
						}
					});
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					log.info("|------ �������ݲ��� ------|\t �������ݳ�ʼ��ʧ��"+e2);
				}
		
			}
			
			if("DELETE".equals(name)){//ɾ��ѡ�е�����
				try {
					
					int  selectRow = SalesTicketInfoPanel.table.getSelectedRow();
					System.out.println("ѡ����У�"+selectRow);
					if(selectRow == -1 || selectRow == SalesTicketInfoPanel.table.getRowCount()-1){
						JOptionPane.showMessageDialog(this, "��ѡ������һ������");
						return;
					}
					
					if(ContextValue.SalesTicketInfoListData.get(selectRow)[2].equals("")){
						JOptionPane.showMessageDialog(this, "��ѡ�����������ݽ���ɾ��");
						return;
					}
					
					if(ContextValue.SalesTicketInfoListData.size()==2){
						ContextValue.SalesTicketInfoListData.set(selectRow, new Object[]{"","","",1,Boolean.FALSE,"","","","","","","","","",""});
						ContextValue.SalesTicketInfoListData.set(ContextValue.SalesTicketInfoListData.size()-1,new Object[]{"","","","�ϼƣ�",Boolean.FALSE,"","","","","","","","",0.00,""});
						SwingUtilities.invokeLater(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								SalesTicketInfoPanel.table.repaint();
								SalesTicketInfoPanel.table.revalidate();
							}
						});
						return;
					}

					int f = JOptionPane.showConfirmDialog(this, "ȷ��ɾ��ѡ������", "ȷ����ʾ��",JOptionPane.YES_NO_OPTION);
					if(f == JOptionPane.YES_OPTION){
						
						Object xs_no = (Object) SalesTicketInfoPanel.table.getValueAt(selectRow, 0);						
						if(xs_no !=null && !"".equals(xs_no)){
							list_arr.add(Integer.valueOf(xs_no.toString()));
							System.out.println(Arrays.toString(list_arr.toArray()));
						}else{
							
						}

						BigDecimal total = new BigDecimal(String.valueOf(ContextValue.SalesTicketInfoListData.get(ContextValue.SalesTicketInfoListData.size()-1)[13]));
						
						total = MathUtil.sub_BigDecimal(total, ContextValue.SalesTicketInfoListData.get(selectRow)[13]);
						ContextValue.SalesTicketInfoListData.remove(selectRow);//���б����Ƴ�����
						
						
						for(int i=0; i<ContextValue.SalesTicketInfoListData.size()-1; i++){
							ContextValue.SalesTicketInfoListData.get(i)[3]=i+1;
						}						
						
						SalesTicketInfoPanel.table.setValueAt(total, SalesTicketInfoPanel.table.getRowCount()-1, 13);
					}else{
						return;
					}
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							SalesTicketInfoPanel.table.repaint();
							SalesTicketInfoPanel.table.revalidate();
						}
					});
					initData();
				} catch (Exception e2) {
					// TODO: handle exception
					log.info("|------ ɾ�����۵���ϸ ------|\t �������ݳ�ʼ��ʧ��"+e2);
				}
			}
			
			if("CANCEL".equals(name)){
				try {
					BtnMenuGroupPanel.salesTicketInfoWindow.setClosed(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if("IMPORT".equals(name)){
				SalesTicketInfoModel salesTicketInfoModel = null;
				SelectGoodsDialog sgd = new SelectGoodsDialog(salesTicketInfoModel);
				sgd.setVisible(true);
			}
			
			
			//���۵���ӡ����
			if("PRINT".equals(name)){
				if(this.salesTicketInfo.getXs_hao() == null || ContextValue.TextEditFlag){
					JOptionPane.showMessageDialog(this, "���ȱ�����Ϣ");
					return;
				}
				
				for(int i=0; i<ContextValue.SalesTicketInfoListData.size()-1; i++){
					if(ContextValue.SalesTicketInfoListData.get(i)[0]==null 
							|| "".equals(ContextValue.SalesTicketInfoListData.get(i)[0].toString())){
						JOptionPane.showMessageDialog(this, "���ȱ�����Ϣ");
						return;
					}
				}
				
				print.excuteJob(name);
								
//				pageBreaks = null;// reset pagination
//				boolean ok = print.job.printDialog();
//				if (ok) {
//					try {
//						 print.job.print();
//					} catch (PrinterException ex) {
//						/* The job did not successfully complete */
//						ex.printStackTrace();
//					}
//				}
			}
			
			if("PREVIEW".equals(name)){
				if(this.salesTicketInfo.getXs_hao() == null || ContextValue.TextEditFlag){
					JOptionPane.showMessageDialog(this, "���ȱ�����Ϣ");
					return;
				}
				
				for(int i=0; i<ContextValue.SalesTicketInfoListData.size()-1; i++){
					if(ContextValue.SalesTicketInfoListData.get(i)[0]==null 
							|| "".equals(ContextValue.SalesTicketInfoListData.get(i)[0].toString())){
						JOptionPane.showMessageDialog(this, "���ȱ�����Ϣ");
						return;
					}
				}

				if(list_arr.size() !=0){
					JOptionPane.showMessageDialog(this, "��ɾ������δ�ύ,���ȱ�����Ϣ");
					return;
				}
				
				print.excuteJob(name);

//				try {
//					 print.createAndShowPreviewDialog();
//				} catch (Exception ex) {
//					/* The job did not successfully complete */
//					ex.printStackTrace();
//				}

			}
			
			//�����еĵ�����Ϣ
			if("OPEN_TICKET".equals(name)){
				//OpenSalesTicketDialog
				SalesTicketInfoModel salesTicketInfoModel= new SalesTicketInfoModel();
				salesTicketInfoModel.setXs_hao(ByteUtils.getNowTimeStr("yyyyMMdd"));
				OpenSalesTicketDialog ostd = new OpenSalesTicketDialog(salesTicketInfoModel);
				ostd.setVisible(true);
				
				//���رռ���
				ostd.addWindowListener(new WindowListener() {
					
					@Override
					public void windowOpened(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowOpened");
					}
					
					@Override
					public void windowIconified(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowIconified");
					}
					
					@Override
					public void windowDeiconified(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowDeiconified");
					}
					
					@Override
					public void windowDeactivated(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowDeactivated");
					}
					
					@Override
					public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowClosing");
					}
					
					@Override
					public void windowClosed(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowClosed");
						initData();
						updateTableData();
					}
					
					@Override
					public void windowActivated(WindowEvent e) {
						// TODO Auto-generated method stub
						log.debug("windowActivated");
					}
				});
				
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						SalesTicketInfoPanel.table.repaint();
						SalesTicketInfoPanel.table.revalidate();
					}
				});
			
			}
			
			
			//��������
			if("SAVE".equals(name)){
				try {
					
					System.out.println(ContextValue.SalesTicketInfoListData.get(0)[13]);
					
					
					if(this.salesTicketInfo.getKehu_no() == null || "".equals(this.salesTicketInfo.getKehu_no())){
						JOptionPane.showMessageDialog(this, "��ѡ��ͻ���Ϣ");
						return;
					}
					
					if(ContextValue.SalesTicketInfoListData.size() ==2 && ContextValue.SalesTicketInfoListData.get(0)[2].equals("")){
						JOptionPane.showMessageDialog(this, "�������Ʒ��Ϣ");
						return;
					}
					
//					//�ύǰ����ɱ��ı༭״̬
//					if (SalesTicketInfoPanel.table.isEditing()){ 
//						SalesTicketInfoPanel.table.getCellEditor().stopCellEditing();
//					}
//					if(SalesTicketInfoPanel.table.getEditorComponent() != null){
//						JOptionPane.showMessageDialog(this, "���۵���ϸ���ڱ༭�У�����ɱ༭�ٱ���");
//						return;
//					}
					
					this.salesTicketInfo.setXs_moneyall(
							new BigDecimal(String.valueOf( ContextValue.SalesTicketInfoListData.get(ContextValue.SalesTicketInfoListData.size()-1)[13])));
					List<SalesTicketDetailsModel> salesTicketDetails = new ArrayList<SalesTicketDetailsModel>();
					//��ȡ�б���ϸ��Ϣ
					for(int i=0; i<ContextValue.SalesTicketInfoListData.size()-1; i++){
						
						if(ContextValue.SalesTicketInfoListData.get(i)[2].equals("")){
							continue;
						}						
						SalesTicketDetailsModel salesTicketDetailsModel= new SalesTicketDetailsModel();
//						new Object[]{"ID"0,"XS_HAO"1,"GOODS_ID"2, "���"3, "ѡ��"4, "ƴ��"5, 
//						"��Ʒ����"6, "���"7, "Ʒ��"8, "���"9, "��λ"10, "�ۼ�"11, "��������"12, "�ܽ��"13, "��ע"14};
						
						String xs_no = String.valueOf(ContextValue.SalesTicketInfoListData.get(i)[0]);
						if(xs_no !=null && !"".equals(xs_no)){
							salesTicketDetailsModel.setXs_no(Integer.valueOf(xs_no));
						}
						String xs_hao = String.valueOf(ContextValue.SalesTicketInfoListData.get(i)[1]);
						if(xs_hao !=null && !"".equals(xs_hao)){
							salesTicketDetailsModel.setXs_hao(xs_hao);
						}
						String peij_no = (String) ContextValue.SalesTicketInfoListData.get(i)[2];
						if(peij_no !=null && !"".equals(peij_no)){
							salesTicketDetailsModel.setPeij_no(peij_no);
						}
						String ck_name = (String) ContextValue.SalesTicketInfoListData.get(i)[5];
						if(ck_name !=null && !"".equals(ck_name)){
							salesTicketDetailsModel.setCk_name(ck_name);
						}
						String xs_name = (String) ContextValue.SalesTicketInfoListData.get(i)[6];
						if(xs_name !=null && !"".equals(xs_name)){
							salesTicketDetailsModel.setXs_name(xs_name);
						}
						String xs_pjlb = (String) ContextValue.SalesTicketInfoListData.get(i)[7];
						if(xs_pjlb !=null && !"".equals(xs_pjlb)){
							salesTicketDetailsModel.setXs_pjlb(xs_pjlb);
						}
						
						salesTicketDetailsModel.setXs_brand((String) ContextValue.SalesTicketInfoListData.get(i)[8]);
						salesTicketDetailsModel.setXs_code((String) ContextValue.SalesTicketInfoListData.get(i)[9]);
						salesTicketDetailsModel.setXs_unit((String) ContextValue.SalesTicketInfoListData.get(i)[10]);
						salesTicketDetailsModel.setXs_price(BigDecimal.valueOf(Double.valueOf(String.valueOf(ContextValue.SalesTicketInfoListData.get(i)[11]))));
						salesTicketDetailsModel.setXs_num(Integer.valueOf(ContextValue.SalesTicketInfoListData.get(i)[12].toString()));
						salesTicketDetailsModel.setXs_money(BigDecimal.valueOf(Double.valueOf(String.valueOf(ContextValue.SalesTicketInfoListData.get(i)[13]))));
						salesTicketDetailsModel.setComment(String.valueOf(ContextValue.SalesTicketInfoListData.get(i)[14]));											
						salesTicketDetails.add(salesTicketDetailsModel);
					}
					this.salesTicketInfo.setSalesTicketDetails(salesTicketDetails);
											
					if(this.salesTicketInfo.getXs_hao() == null){//����
						String KEY_ID = ControlFactory.salesTicketInfoController.generateXSKey();
						this.salesTicketInfo.setXs_hao(KEY_ID);
						
						for(SalesTicketDetailsModel s :salesTicketInfo.getSalesTicketDetails()){
							s.setXs_hao(KEY_ID);
						}
						
						try {
							salesTicketInfo = ControlFactory.salesTicketInfoController.add(salesTicketInfo);
//							JOptionPane.showMessageDialog(this, "����ɹ�");
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "����ʧ��");
							return;
						}
					}else if(this.salesTicketInfo.getXs_hao() != null){//����
//						return;
						try {
							xs_noArr = (Object[])list_arr.toArray();
							
							salesTicketInfo = ControlFactory.salesTicketInfoController.update(salesTicketInfo, xs_noArr);
							
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
							JOptionPane.showMessageDialog(this, "����ʧ��");
							return;
						}
					}
					
					log.info(this.salesTicketInfo);
					
					initData();
					updateTableData();
					
					log.info( " \n\n\n"+Arrays.toString(ContextValue.SalesTicketInfoListData.get(0)));
					
					SwingUtilities.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							SalesTicketInfoPanel.table.repaint();
							SalesTicketInfoPanel.table.revalidate();
						}
					});
					ContextValue.TextEditFlag = false;
					list_arr.clear();//����ɹ�����������б�����
					JOptionPane.showMessageDialog(this, "����ɹ�");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
		
		Component component = e.getComponent();
		String name = component.getName();
		if("PINYIN".equals(name)){
			int k = e.getKeyCode();
			if (k == e.VK_ENTER) {// �س��¼�,���ؿͻ���Ϣ
				// �س�
				CustomerInfoModel customerInfo = getTextInfo();
				List<CustomerInfoModel> customerInfoList = ControlFactory.customerInfoController.queryBySome(customerInfo);
				
				if(customerInfoList.size() == 0){
					JOptionPane.showMessageDialog(this, "û�д˿ͻ���Ϣ");
					return;
				}else if(customerInfoList.size() == 1){//ֻ��һ������ֱ����д������
					this.salesTicketInfo.setKehu_no(customerInfoList.get(0).getKehu_no());
					this.salesTicketInfo.setKehu_name(customerInfoList.get(0).getKehu_name());
					this.salesTicketInfo.setKehu_pinyin(customerInfoList.get(0).getKehu_py());
					this.salesTicketInfo.setXs_date(ByteUtils.getNowTimeStr("yyyy-MM-dd"));
					initData();
					
					SalesTicketInfoPanel.table.changeSelection(0, 5, true, true);
					SalesTicketInfoPanel.table.editCellAt(0, 5);
					SalesTicketInfoPanel.table.getEditorComponent().requestFocus();
				}else{
					SelectCustomerDialog sgd = new SelectCustomerDialog(customerInfoList);
					
					sgd.requestFocus();
//					sgd.setFocusable(true);
					//���رռ���
					sgd.addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowOpened");
							
							
						}
						
						@Override
						public void windowIconified(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowIconified");
						}
						
						@Override
						public void windowDeiconified(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowDeiconified");
						}
						
						@Override
						public void windowDeactivated(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowDeactivated");
						}
						
						@Override
						public void windowClosing(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowClosing");
						}
						
						@Override
						public void windowClosed(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowClosed");
							initData();
							
							SalesTicketInfoPanel.table.changeSelection(0, 5, true, true);
							SalesTicketInfoPanel.table.editCellAt(0, 5);
							SalesTicketInfoPanel.table.getEditorComponent().requestFocus();
							
							SelectCustomerDialog.kit.removeAWTEventListener(SelectCustomerDialog.listener);
						}
						
						@Override
						public void windowActivated(WindowEvent e) {
							// TODO Auto-generated method stub
							log.debug("windowActivated");
						}
					});
				
					sgd.setVisible(true);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		Component component = e.getComponent();
		String name = component.getName();
		
		if("message".equals(name)){//�༭�¼�
			JTextField jf = (JTextField)component;
			wp.setParam(name, jf.getText().trim());
		}
	}
	
	
	/**
	 * �˶Դ��¼���������ݵ��ܼ�
	 * @author XCCD
	 */
	public void autoCalculate(){
		try {
			
			for(int row=0; row<SalesTicketInfoPanel.table.getRowCount()-1;row++){
							
				BigDecimal mun = MathUtil.mul_BigDecimal(SalesTicketInfoPanel.table.getValueAt(row,11), SalesTicketInfoPanel.table.getValueAt(row, 12));
				SalesTicketInfoPanel.table.setValueAt(mun, row, 13);
			}

			BigDecimal total = new BigDecimal(Double.toString(0.00));
			
			for(int i=0; i<SalesTicketInfoPanel.table.getRowCount()-1; i++){
				if(!SalesTicketInfoPanel.table.getValueAt(i, 2).equals("")){
					Object price = SalesTicketInfoPanel.table.getValueAt(i, 13).toString();
					total = MathUtil.add_BigDecimal(price, total);
				}
			}
			
			SalesTicketInfoPanel.table.setValueAt(total, SalesTicketInfoPanel.table.getRowCount()-1, 13);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
	}

}
