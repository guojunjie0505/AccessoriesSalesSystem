package kehaofei.com.ui.wmspanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.beans.PropertyVetoException;
import java.text.MessageFormat;

import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.ui.optionCustomer.EditCustomerInfoDialog;
import kehaofei.com.ui.panel.BtnMenuGroupPanel;
import kehaofei.com.utils.Constant_Properties;
import kehaofei.com.utils.ControlFactory;

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
public class CustomerQueryPanel extends JPanel implements ActionListener,KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(CustomerQueryPanel.class.getSimpleName());
	
	
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JTextField textField_1;
	private JLabel label_2;
	private JTextField textField_2;
	private JLabel label_3;
	private JTextField textField_3;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton button_5;
	private JButton btnExcel;
	private JButton button_6;
	
	
	/**
	 * Create the panel.
	 * @param up 
	 * @param down 
	 */
	public CustomerQueryPanel() {
		
		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("FunctionBlock")));
		
		
		GridBagLayout gbl_btnPanel = new GridBagLayout();
		gbl_btnPanel.columnWidths = new int[]{10, 70, 70, 70, 70, 70, 70, 70, 40, 40, 40, 40, 40, 40, 60, 0, 0};
		gbl_btnPanel.rowHeights = new int[]{50, 50, 0};
		gbl_btnPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_btnPanel.rowWeights = new double[]{0.3, 0.3, Double.MIN_VALUE};
		setLayout(gbl_btnPanel);
	
		
		button_1 = new JButton(
				Constant_Properties.myResource.getString("Add"),
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/add.jpg")));
		button_1.setVerticalTextPosition(JButton.BOTTOM);
		button_1.setHorizontalTextPosition(JButton.CENTER);
//		button_1.setBorderPainted(false);
		button_1.setName("ADD");
		button_1.addActionListener(this);
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.insets = new Insets(5, 0, 10, 5);
		gbc_button_1.gridx = 1;
		gbc_button_1.gridy = 0;
		add(button_1, gbc_button_1);

		
		button_2 = new JButton(
				Constant_Properties.myResource.getString("Update"),
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/update.jpg")));
		button_2.setVerticalTextPosition(JButton.BOTTOM);
		button_2.setHorizontalTextPosition(JButton.CENTER);
		button_2.setName("UPDATE");
		button_2.addActionListener(this);
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.fill = GridBagConstraints.BOTH;
		gbc_button_2.insets = new Insets(5, 0, 10, 5);
		gbc_button_2.gridx = 2;
		gbc_button_2.gridy = 0;
		add(button_2, gbc_button_2);
		
		button_3 = new JButton(
				Constant_Properties.myResource.getString("Delete"),
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
		
		btnExcel = new JButton("EXCEL",
				new ImageIcon(this.getClass().getResource("/kehaofei/com/img/excel.jpg")));
		btnExcel.setVerticalTextPosition(JButton.BOTTOM);
		btnExcel.setHorizontalTextPosition(JButton.CENTER);
		GridBagConstraints gbc_btnExcel = new GridBagConstraints();
		gbc_btnExcel.fill = GridBagConstraints.BOTH;
		gbc_btnExcel.insets = new Insets(5, 0, 10, 5);
		gbc_btnExcel.gridx = 6;
		gbc_btnExcel.gridy = 0;
		add(btnExcel, gbc_btnExcel);
		
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
		
		
		label = new JLabel("ƴ����");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		textField = new JTextField();
		textField.setName("PINYIN");
		textField.addKeyListener(this);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		label_1 = new JLabel("���ƣ�");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 0, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 1;
		add(label_1, gbc_label_1);
		
		textField_1 = new JTextField();
		textField_1.setName("PINYIN");
		textField_1.addKeyListener(this);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 1;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		label_2 = new JLabel("��ϵ�ˣ�");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 0, 5);
		gbc_label_2.gridx = 7;
		gbc_label_2.gridy = 1;
		add(label_2, gbc_label_2);
		
		textField_2 = new JTextField();
		textField_2.setName("PINYIN");
		textField_2.addKeyListener(this);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 8;
		gbc_textField_2.gridy = 1;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		label_3 = new JLabel("�绰��");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 0, 5);
		gbc_label_3.gridx = 10;
		gbc_label_3.gridy = 1;
		add(label_3, gbc_label_3);
		
		textField_3 = new JTextField();
		textField_3.setName("PINYIN");
		textField_3.addKeyListener(this);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.gridwidth = 2;
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 11;
		gbc_textField_3.gridy = 1;
		add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);
		
		button = new JButton(Constant_Properties.myResource.getString("Query"));
		button.setName("QUERY");
		button.addActionListener(this);
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(10, 0, 10, 5);
		gbc_button.gridx = 14;
		gbc_button.gridy = 1;
		add(button, gbc_button);
		
	}
	
	/**
	 * ��ȡ��ѯ����
	 * @author XCCD
	 */
	private CustomerInfoModel getTextInfo(){
		CustomerInfoModel customerInfo = new CustomerInfoModel();
		if(textField.getText() != null && !textField.getText().trim().equals("") ){
			customerInfo.setKehu_py(textField.getText());
		}
		if(textField_1.getText() != null && !textField_1.getText().trim().equals("") ){
			customerInfo.setKehu_name(textField_1.getText());
		}
		if(textField_2.getText() != null && !textField_2.getText().trim().equals("") ){
			customerInfo.setLxr(textField_2.getText());
		}
		if(textField_3.getText() != null && !textField_3.getText().trim().equals("") ){
			customerInfo.setTel(textField_3.getText());//�ͻ��绰
		}		
		return customerInfo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String className = e.getSource().getClass().getName();
		
		if(className.equals("javax.swing.JCheckBox")){
			JCheckBox jcb = (JCheckBox) e.getSource();
			int rowCount = CustomerInfoPanel.table.getRowCount();
			if(jcb.isSelected()){				
				for(int i = 0; i < rowCount; i++){
					 CustomerInfoPanel.table.setValueAt(true, i, 0);
					 
				}
				CustomerInfoPanel.table.repaint();
				CustomerInfoPanel.table.setVisible(true);
			}
			if(!jcb.isSelected()){
				for(int i = 0; i < rowCount; i++){
					CustomerInfoPanel.table.setValueAt(false, i, 0);
				}
				CustomerInfoPanel.table.repaint();
				CustomerInfoPanel.table.setVisible(true);
			}
		}
		if(className.equals("javax.swing.JButton")){
			JButton bt = (JButton)e.getSource();
			String name = bt.getName();
			
			if("ADD".equals(name)){
				try {
					CustomerInfoModel customerInfo = null;//�������ݴ���ղ���
					
					EditCustomerInfoDialog egid = new EditCustomerInfoDialog(customerInfo);
					egid.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					log.info("|------ �������ݲ��� ------|\t �������ݳ�ʼ��ʧ��"+e2);
				}
		
			}
			
			if("UPDATE".equals(name)){//����ѡ�е�����
				try {
					CustomerInfoModel customerInfo = new CustomerInfoModel();
					
					int index = CustomerInfoPanel.table.getSelectedRow();
					
					if(index == -1){
						JOptionPane.showMessageDialog(this, "û��ѡ������");
						return;
					}else{
						customerInfo = ControlFactory.customerInfoController.queryById(
								(String) CustomerInfoPanel.table.getValueAt(index, 0));
					}
					
					EditCustomerInfoDialog egid = new EditCustomerInfoDialog(customerInfo);
					egid.setVisible(true);
				} catch (Exception e2) {
					// TODO: handle exception
					log.info("|------ ��Ʒ��Ϣ ------|\t �������ݳ�ʼ��ʧ��"+e2);
				}
			}
			
			if("DELETE".equals(name)){//ɾ��ָ������
				try {
					
					int index = CustomerInfoPanel.table.getSelectedRow();
					
					if(index == -1){
						JOptionPane.showMessageDialog(this, "û��ѡ������");
						return;
					}else{
						int flag = JOptionPane.showConfirmDialog(this, "�Ƿ�ɾ����ѡ����", "ȷ�Ͽ�", JOptionPane.OK_CANCEL_OPTION);
						switch(flag){
							case JOptionPane.OK_OPTION:
							{
								boolean option = ControlFactory.customerInfoController.delete(
										(String) CustomerInfoPanel.table.getValueAt(index, 0));
								SwingUtilities.invokeLater(new Runnable() {//����UI��ʾ����
									@Override
									public void run() {
										
										ControlFactory.customerInfoController.LoadCustomerInfoVector();
										CustomerInfoPanel.table.repaint();
										CustomerInfoPanel.table.revalidate();
										
									}
								});
								JOptionPane.showMessageDialog(this, "ɾ���ɹ�");								
							}break;
							case JOptionPane.CANCEL_OPTION:
							{
								
							}break;
						}
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
					log.info("|------ ��Ʒ��Ϣ ------|\t �������ݳ�ʼ��ʧ��"+e2);
				}
			}
			
			if("CANCEL".equals(name)){
				try {
					BtnMenuGroupPanel.customerInfoWindow.setClosed(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			//��ӡ��ť
			if("PRINT".equals(name)){
				int flag = JOptionPane.showConfirmDialog(this, "�Ƿ��ӡ", "ȷ�Ͽ�", JOptionPane.OK_CANCEL_OPTION);
				switch(flag){
					case JOptionPane.OK_OPTION:
					{
						try {
							MessageFormat headerFormat = new MessageFormat("Page {0}");
							MessageFormat footerFormat = new MessageFormat("- {0} -");
							
							CustomerInfoPanel.table.print(JTable.PrintMode.FIT_WIDTH,
									headerFormat, footerFormat, true, null, false);
						} catch (PrinterException pe) {
							pe.printStackTrace();
							log.error("Error printing: " + pe.getMessage());
						}
					}break;
					case JOptionPane.CANCEL_OPTION:
					{
						
					}break;
				}
				
			}
			
			if("QUERY".equals(name)){
				CustomerInfoModel customerInfo = getTextInfo();
				ControlFactory.customerInfoController.queryBySome(customerInfo);
				
				SwingUtilities.invokeLater(new Runnable() {//����UI��ʾ����
					@Override
					public void run() {
//						CustomerInfoPanel.table.print();
						CustomerInfoPanel.table.revalidate();
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
			CustomerInfoModel customerInfo = getTextInfo();
			ControlFactory.customerInfoController.queryBySome(customerInfo);

			SwingUtilities.invokeLater(new Runnable() {// ����UI��ʾ����
						@Override
						public void run() {
							CustomerInfoPanel.table.revalidate();
						}
					});
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
