package kehaofei.com.ui_model;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigDecimal;
import java.util.EventObject;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.sm.model.GoodsInfoModel;
import kehaofei.com.ui.main.MessageDialog;
import kehaofei.com.ui.optionSalesTicket.SelectCustomerDialog;
import kehaofei.com.ui.optionSalesTicket.SelectOneGoodsInfoDialog;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.ContextValue;
import kehaofei.com.utils.ControlFactory;
import kehaofei.com.utils.MathUtil;

import org.apache.log4j.Logger;
/**
 * 
 * @author kehaofei
 * <li>TODO	��Ԫ��༭������
 * <li>2017-10-10 ����1:27:13
 * <li>
 */
public class MyTextEditor implements TableCellEditor, KeyListener, FocusListener, MouseListener{
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	//editor show
    private JTextField textField = null;
	private JTable table= SalesTicketInfoPanel.table;
	
    
	
    //ChangeEvent����֪ͨ����Ȥ�Ĳ������¼�Դ�е�״̬�ѷ������ġ�
    private static ChangeEvent changeEvent = new ChangeEvent(MyTextEditor.class);

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		log.info("|------���Ԫ��༭ģ��------|\t"+"getCellEditorValue\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		try {
			if(table.getValueAt(0, SalesTicketInfoPanel.column) instanceof BigDecimal){
				return new BigDecimal(textField.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(table, "���ݸ�ʽ����");
			return "0.0";
		}
		try{
			if(table.getValueAt(0, SalesTicketInfoPanel.column) instanceof Integer){
				return Integer.valueOf(textField.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(table, "���ݸ�ʽ����");
			return "1";
		}
			
		return textField.getText();		
		
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		log.info("|------���Ԫ��༭ģ��------|\t"+"isCellEditable\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
		
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
//		textField.requestFocus();//��ȡ��������
		log.info("|------���Ԫ��༭ģ��------|\t"+"shouldSelectCell\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		log.info("|------���Ԫ��༭ģ��------|\t"+"stopCellEditing\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
//		table.setValueAt(getCellEditorValue(), row, column);
		
		//����ע�͵������fireEditingStopped();��Ȼ����GenderEditor�Ĺ��캯���а�
        //addActionListener()��ע��ȥ������ʱ������ֹ�༭������JComboBox��ã���
//        System.out.println("�༭����һ����Ԫ���ٵ����һ����Ԫ��ʱ�����á�");
//        fireEditingStopped();//������ֹ�༭������JTable���
		
		if(textField.getKeyListeners().length == 0){
			textField.addKeyListener(this);
		}		
		return true;
	}
	
	public static void fireEditingStopped(){
        CellEditorListener listener;
        Object[] listeners = SalesTicketInfoPanel.listenerList.getListenerList();
        for(int i = 0; i < listeners.length; i++){
             if(listeners[i]== CellEditorListener.class){
                  //֮������i+1������Ϊһ��ΪCellEditorListener.class��Class���󣩣�
                  //���ŵ���һ��CellEditorListener��ʵ��
                  listener= (CellEditorListener)listeners[i+1];
                  //��changeEventȥ֪ͨ�༭���Ѿ������༭
                  //��editingStopped�����У�JTable����getCellEditorValue()ȡ�ص�Ԫ���ֵ��
                  //���Ұ����ֵ���ݸ�TableValues(TableModel)��setValueAt()
                  listener.editingStopped(changeEvent);
                  
             }
        }
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		log.info("|------���Ԫ��༭ģ��------|\t"+"cancelCellEditing\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		log.info("|------���Ԫ��༭ģ��------|\t"+"addCellEditorListener\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
//		table.setValueAt(getCellEditorValue(), row, column);
			
		SalesTicketInfoPanel.listenerList.add(CellEditorListener.class,l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		log.info("|------���Ԫ��༭ģ��------|\t"+"removeCellEditorListener");
		SalesTicketInfoPanel.listenerList.remove(CellEditorListener.class,l);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		log.info("|------���Ԫ��༭ģ��------|\t"+"getTableCellEditorComponent\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
		if(table != null && table.getEditorComponent() != null){
//			SalesTicketInfoPanel.row = table.getEditingRow();
//			SalesTicketInfoPanel.column = table.getEditingColumn();
			table.getEditorComponent().transferFocus();
		}	
		MyComboBoxEditor.fireEditingStopped();
		fireEditingStopped();
		table.clearSelection();
		
		this.table = SalesTicketInfoPanel.table;
		SalesTicketInfoPanel.row = row;
		SalesTicketInfoPanel.column = column;
		// TODO Auto-generated method stub
		
		textField = new JTextField();
		
		textField.setText((value == null) ? "" : value.toString());
//		textField.setBorder(new EmptyBorder(0,0,0,0));
//		textField.setForeground(table.getForeground());             
		textField.setBackground(table.getBackground());
//		textField.setSelectionColor(table.getSelectionBackground());
		switch(column){
			case 7:
			case 8:
			case 9:{
				textField.setEditable(false);
				textField.setBackground(table.getBackground());
			}break;
			case 11:
			{
				textField.setEditable(true);
				textField.setHorizontalAlignment(JTextField.RIGHT);
				textField.setName("PRICE");
				textField.setBackground(table.getBackground());
			}break;
			case 12:
			{
				textField.setEditable(true);
				textField.setHorizontalAlignment(JTextField.CENTER);
				textField.setName("NUMBER");
				textField.setBackground(table.getBackground());
			}break;
			case 13:
			{
				textField.setEditable(false);
				textField.setHorizontalAlignment(JTextField.RIGHT);
				textField.setBackground(table.getBackground());
			}break;
			default :
			{
				textField.setEditable(true);
			}break;
		}
		
		textField.setSelectionStart(0);
		textField.setSelectionEnd(textField.getText().length());
		textField.addKeyListener(this);
		textField.addFocusListener(this);
		textField.addMouseListener(this);
		
		log.info("|------���Ԫ��༭ģ��------|\t"+"getTableCellEditorComponent\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		return textField;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
//		Component component = e.getComponent();
//		String name = component.getName();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		Component component = e.getComponent();
		String name = component.getName();
		
		System.out.println("�༭���¼���������"+e.getKeyChar());
		log.info("|------��Ԫ��༭ʱ�������------|\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		switch(e.getKeyCode()){
			
			case KeyEvent.VK_DOWN://���̡�������¼�
			{
				
				if(SalesTicketInfoPanel.row == table.getRowCount()-2){
					log.info("�жϵ�ǰ��������һ��");	
					
					if(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2]!=null 
							&& !"".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2])){
//						Object[] total = ContextValue.SalesTicketInfoListData.get(ContextValue.SalesTicketInfoListData.size()-1);
						ContextValue.SalesTicketInfoListData.add(SalesTicketInfoPanel.row+1, new Object[]{"","","",SalesTicketInfoPanel.row+2,Boolean.FALSE,"","","","","","","","","",""});
						
						
						table.getEditorComponent().transferFocus();
						fireEditingStopped();//������ֹ�༭������JTable���
						table.changeSelection(SalesTicketInfoPanel.row+1, 5, true, true);
						table.editCellAt(SalesTicketInfoPanel.row+1, 5);
						table.getEditorComponent().requestFocus();
					}					
				}else{
					table.getEditorComponent().transferFocus();		
					fireEditingStopped();//������ֹ�༭������JTable���
					table.changeSelection(SalesTicketInfoPanel.row+1, SalesTicketInfoPanel.column, true, true);
					table.editCellAt(SalesTicketInfoPanel.row+1, SalesTicketInfoPanel.column);
					table.getEditorComponent().requestFocus();
					
					
				}
				SwingUtilities.invokeLater(new Runnable(){
					@Override  
				    public void run() {	
						table.repaint();
						table.revalidate();						
					}
				});
			}break;
			case KeyEvent.VK_UP://���̡�������¼�
			{
				
				log.info("�жϵ�ǰ���ݼ���һ��");	
				if(SalesTicketInfoPanel.row == 0){
					return;
				}
					
				//�жϵ�ǰ������Ƿ����
				if((ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2] ==null 
						|| "".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2]))){
					
					ContextValue.SalesTicketInfoListData.remove(SalesTicketInfoPanel.row);						
					
					table.getEditorComponent().transferFocus();
					fireEditingStopped();//������ֹ�༭������JTable���
					table.changeSelection(SalesTicketInfoPanel.row-1, SalesTicketInfoPanel.column, true, true);
					table.editCellAt(SalesTicketInfoPanel.row-1, SalesTicketInfoPanel.column);
					table.getEditorComponent().requestFocus();
					
				}else{
					table.getEditorComponent().transferFocus();
					fireEditingStopped();//������ֹ�༭������JTable���
					table.changeSelection(SalesTicketInfoPanel.row-1, SalesTicketInfoPanel.column, true, true);
					table.editCellAt(SalesTicketInfoPanel.row-1, SalesTicketInfoPanel.column);
					table.getEditorComponent().requestFocus();
				}
				SwingUtilities.invokeLater(new Runnable(){
					@Override  
				    public void run() {
						table.repaint();
						table.revalidate();
						
					}
				});
			}break;
			
			case KeyEvent.VK_LEFT://���̡�������¼�
			{
				if(SalesTicketInfoPanel.column <= 5){
					return;
				}
				table.getEditorComponent().transferFocus();
				fireEditingStopped();//������ֹ�༭������JTable���
				table.changeSelection(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column-1, true, true);
				table.editCellAt(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column-1);
				table.getEditorComponent().requestFocus();
				
				SwingUtilities.invokeLater(new Runnable(){
					@Override  
				    public void run() {						
						table.repaint();
						table.revalidate();
						
					}
				});
			}break;
			
			case KeyEvent.VK_RIGHT://���̡�������¼�
			{
				if(SalesTicketInfoPanel.column == table.getColumnCount()-1){
					return;
				}
				table.getEditorComponent().transferFocus();
				fireEditingStopped();//������ֹ�༭������JTable���
				
				table.changeSelection(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column+1, true, true);
				table.editCellAt(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column+1);
				table.getEditorComponent().requestFocus();
				
				SwingUtilities.invokeLater(new Runnable(){
					@Override  
				    public void run() {						
						table.repaint();
						table.revalidate();						
					}
				});
			}break;
			
			case KeyEvent.VK_ENTER:
			{// �س��¼�,���ؿͻ���Ϣ
				if(SalesTicketInfoPanel.column == 5 && !"".equals(textField.getText())){
					GoodsInfoModel goodsInfo = new GoodsInfoModel();
					goodsInfo.setPeij_py(textField.getText());
					List<GoodsInfoModel> goodsInfoList = ControlFactory.goodsInfoController.queryBySome(goodsInfo);
					
					if(goodsInfoList.size() == 0){

						if (SalesTicketInfoPanel.table.isEditing()){ 
							table.getEditorComponent().transferFocus();
							MyTextEditor.fireEditingStopped();
						}						

						MessageDialog messageDialog = new MessageDialog("û����Ʒ��Ϣ");
						
						messageDialog.addWindowListener(new WindowListener() {
							
							@Override
							public void windowOpened(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowIconified(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowDeiconified(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowDeactivated(WindowEvent e) {
								// TODO Auto-generated method stub
								log.info("windowDeactivated");
								
							}
							
							@Override
							public void windowClosing(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void windowClosed(WindowEvent e) {
								// TODO Auto-generated method stub
								log.info("windowClosed");
								if (SalesTicketInfoPanel.table.isEditing()){ 
//									textField.setText(
//											(String) ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[5]);
//									table.getEditorComponent().transferFocus();
									MyTextEditor.fireEditingStopped();	
									
								}
																
								SalesTicketInfoPanel.table.changeSelection(SalesTicketInfoPanel.row, 5, true, true);
								SalesTicketInfoPanel.table.editCellAt(SalesTicketInfoPanel.row,  5);		
								SalesTicketInfoPanel.table.getEditorComponent().requestFocus();								
								
								textField.removeKeyListener(MyTextEditor.this);
							}
							
							@Override
							public void windowActivated(WindowEvent e) {
								// TODO Auto-generated method stub
								
							}
						});
						messageDialog.requestFocus();
						messageDialog.setVisible(true);
						
//						JOptionPane.showMessageDialog(null, "û����Ʒ��Ϣ");

						return;
					}else if(goodsInfoList.size() == 1){//ֻ��һ������ֱ����д�����
						
						for(Object[] ob : ContextValue.SalesTicketInfoListData){
							if(ob[2].equals(goodsInfoList.get(0).getPeij_no())){	
								
								MessageDialog messageDialog = new MessageDialog("��Ʒ�Ѵ������б���");
								
								messageDialog.addWindowListener(new WindowListener() {
									
									@Override
									public void windowOpened(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowIconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowDeiconified(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowDeactivated(WindowEvent e) {
										// TODO Auto-generated method stub
										log.info("windowDeactivated");
										
									}
									
									@Override
									public void windowClosing(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
									
									@Override
									public void windowClosed(WindowEvent e) {
										// TODO Auto-generated method stub
										log.info("windowClosed");
										if (SalesTicketInfoPanel.table.isEditing()){ 
											MyTextEditor.fireEditingStopped();	
											
										}																	
										SalesTicketInfoPanel.table.changeSelection(SalesTicketInfoPanel.row, 5, true, true);
										SalesTicketInfoPanel.table.editCellAt(SalesTicketInfoPanel.row,  5);		
										SalesTicketInfoPanel.table.getEditorComponent().requestFocus();								
										
										textField.removeKeyListener(MyTextEditor.this);
									}
									
									@Override
									public void windowActivated(WindowEvent e) {
										// TODO Auto-generated method stub
										
									}
								});
								messageDialog.requestFocus();
								messageDialog.setVisible(true);
								return;
							}
						}

						Object[] objData = new Object[]{
								ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[0],//������ϸid
								ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[1],//���۵���Id
								goodsInfoList.get(0).getPeij_no(),//��Ʒid
								String.valueOf(SalesTicketInfoPanel.row+1),
								Boolean.FALSE,
								goodsInfoList.get(0).getPeij_py(),
								goodsInfoList.get(0).getPeij_name(),
								goodsInfoList.get(0).getPeij_lb(),
								goodsInfoList.get(0).getBrand(),
								goodsInfoList.get(0).getCode(),//���
								goodsInfoList.get(0).getUnit().trim(),//��λ
								goodsInfoList.get(0).getOut_price1().toString(),//����
								1,
								goodsInfoList.get(0).getOut_price1().toString(),//�����ǵ�����Ʒ�ܼ�
								""};
						ContextValue.SalesTicketInfoListData.set(SalesTicketInfoPanel.row, objData);
						
						BigDecimal total = new BigDecimal(Double.toString(0.00));
						for(int i=0; i<table.getRowCount()-1; i++){
							if(!"".equals(table.getValueAt(i, 2))){
								Object price = table.getValueAt(i, 13).toString();									
								total = MathUtil.add_BigDecimal(price, total);
							}									
						}
						table.setValueAt(total, table.getRowCount()-1, 13);
						
						if (SalesTicketInfoPanel.table.isEditing()){ 
							textField.setText(
									(String) ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[5]);
							MyTextEditor.fireEditingStopped();
							SalesTicketInfoPanel.table.changeSelection(SalesTicketInfoPanel.row, 12, true, true);
							SalesTicketInfoPanel.table.editCellAt(SalesTicketInfoPanel.row, 12);
							SalesTicketInfoPanel.table.getEditorComponent().requestFocus();
							
						}
						
						SwingUtilities.invokeLater(new Runnable(){
							@Override  
						    public void run() {						
								SalesTicketInfoPanel.table.repaint();
								SalesTicketInfoPanel.table.revalidate();						
							}
						});
						return;
					}else{
						SelectOneGoodsInfoDialog sogid = new SelectOneGoodsInfoDialog(goodsInfoList);				
						
						//���رռ���
						sogid.addWindowListener(new WindowListener() {
							
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
								log.info("windowClosing");
							}
							
							@Override
							public void windowClosed(WindowEvent e) {
								// TODO Auto-generated method stub
								log.info("windowClosed");
								
								
								if (SalesTicketInfoPanel.table.isEditing()){ 
									textField.setText(
											(String) ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[5]);
									MyTextEditor.fireEditingStopped();
									
									if("".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2])){
										SalesTicketInfoPanel.table.changeSelection(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column, true, true);
										SalesTicketInfoPanel.table.editCellAt(SalesTicketInfoPanel.row, SalesTicketInfoPanel.column);
										SalesTicketInfoPanel.table.getEditorComponent().requestFocus();
									}else{
										SalesTicketInfoPanel.table.changeSelection(SalesTicketInfoPanel.row, 12, true, true);
										SalesTicketInfoPanel.table.editCellAt(SalesTicketInfoPanel.row, 12);
										SalesTicketInfoPanel.table.getEditorComponent().requestFocus();
									}	
								}
								
								
								BigDecimal total = new BigDecimal(Double.toString(0.00));
								
								for(int i=0; i<table.getRowCount()-1; i++){
									if(!"".equals(table.getValueAt(i, 2))){
										Object price = table.getValueAt(i, 13).toString();									
										total = MathUtil.add_BigDecimal(price, total);
									}									
								}
								
								table.setValueAt(total, table.getRowCount()-1, 13);
								
								SwingUtilities.invokeLater(new Runnable(){
									@Override  
								    public void run() {						
										SalesTicketInfoPanel.table.repaint();
										SalesTicketInfoPanel.table.revalidate();						
									}
								});
								
								SelectOneGoodsInfoDialog.kit.removeAWTEventListener(SelectOneGoodsInfoDialog.listener);
							}
							
							@Override
							public void windowActivated(WindowEvent e) {
								// TODO Auto-generated method stub
								log.debug("windowActivated");
							}
						});
					
						sogid.requestFocus();
						sogid.setVisible(true);						

					}//if������
				}//if������				
			}	
			
			default://������ֵ
			{
				JTextField jf = (JTextField)component;//��ȡ������Ϣ
				
				
				if(SalesTicketInfoPanel.column >=6 &&(
						ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2] == null 
						|| "".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2]))){
					jf.setText("");
					JOptionPane.showMessageDialog(null, "��ѡ����Ʒ���ٽ��б༭");
					return;
				}
				if("NUMBER".equals(name)){
					
					String str = jf.getText().trim();					
					String exp = "^\\d+$";//"^[0-9]{0,6}+(.[0-9]{0,3})?$";//С��������ʽ
					Pattern pattern = Pattern.compile(exp);
					boolean flag = pattern.matcher(str).matches();
					if (flag) {//ͨ����֤
						
					} else {
						String number = str.replaceAll(String.valueOf(e.getKeyChar()), "");
						jf.setText(number.equals("")?"0":number);
//						jf.setText("0");
						JOptionPane.showMessageDialog(null, "����������");				
					}	
					
					autoCalculate_NUM(jf);					
					SwingUtilities.invokeLater(new Runnable(){
						@Override  
					    public void run() {						
							SalesTicketInfoPanel.table.repaint();
							SalesTicketInfoPanel.table.revalidate();						
						}
					});
				}
				
				if("PRICE".equals(name)){
					
					String str = jf.getText().trim();					
					String exp = "^[0-9]{0,6}+(.[0-9]{0,3})?$";//С��������ʽ
					Pattern pattern = Pattern.compile(exp);
					boolean flag = pattern.matcher(str).matches();
					if (flag) {//ͨ����֤
						
					} else {
						String number = str.replaceAll(String.valueOf(e.getKeyChar()), "");
						jf.setText(number.equals("")?"0.0":number);
//						jf.setText("0.0");
						JOptionPane.showMessageDialog(null, "��������Ч����");				
					}	
					
					autoCalculate_PRICE(jf);
					SwingUtilities.invokeLater(new Runnable(){
						@Override  
					    public void run() {						
							SalesTicketInfoPanel.table.repaint();
							SalesTicketInfoPanel.table.revalidate();						
						}
					});
				}
				ContextValue.TextEditFlag = true;//��ǵ�Ԫ��༭״̬
			}break;
		}		
		
	}
	
	/**
	 * �Զ�����ϼ�ֵ--������
	 * @author XCCD
	 * @param jf 
	 */
	private void autoCalculate_NUM(JTextField jf){
		try {
			
			table.setValueAt(((JTextField)table.getEditorComponent()).getText(),SalesTicketInfoPanel.row,SalesTicketInfoPanel.column);
			
			BigDecimal mun = MathUtil.mul_BigDecimal(table.getValueAt(SalesTicketInfoPanel.row, 11), ((JTextField)table.getEditorComponent()).getText());
			table.setValueAt(mun, SalesTicketInfoPanel.row, 13);
			
			BigDecimal total = new BigDecimal(Double.toString(0.00));
			
			for(int i=0; i<table.getRowCount()-1; i++){
				if(!table.getValueAt(i, 2).equals("")){
					Object price = table.getValueAt(i, 13).toString();					
					total = MathUtil.add_BigDecimal(price, total);
				}				
			}
			
			table.setValueAt(total, table.getRowCount()-1, 13);
											
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			jf.setText("0");
			autoCalculate_NUM(jf);
//			JOptionPane.showMessageDialog(null, "���ݸ�ʽ����");
		}
	}	
	private void autoCalculate_PRICE(JTextField jf){
		try {
			if(((JTextField)table.getEditorComponent()).getText()!=null && table.getValueAt(SalesTicketInfoPanel.row, 12)!=null){
				
				table.setValueAt(((JTextField)table.getEditorComponent()).getText(),SalesTicketInfoPanel.row,SalesTicketInfoPanel.column);
				
				BigDecimal mun = MathUtil.mul_BigDecimal(((JTextField)table.getEditorComponent()).getText(), table.getValueAt(SalesTicketInfoPanel.row, 12));
				table.setValueAt(mun, SalesTicketInfoPanel.row, 13);
				
				BigDecimal total = new BigDecimal(Double.toString(0.00));
				
				for(int i=0; i<table.getRowCount()-1; i++){
					if(!table.getValueAt(i, 2).equals("")){
						Object price = table.getValueAt(i, 13).toString();
						total = MathUtil.add_BigDecimal(price, total);
					}
				}
				
				table.setValueAt(total, table.getRowCount()-1, 13);
			}
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			jf.setText("0.0");
			autoCalculate_PRICE(jf);
//			JOptionPane.showMessageDialog(null, "���ݸ�ʽ����");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------��ñ༭����-------|\t"+
				SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------ʧȥ�༭����-------|\t"+
				table.getEditingRow()+","+table.getEditingColumn() 
				+"\n"+e.getSource());

		if(e.getSource().getClass().equals("javax.swing.JTextField")){
			table.getEditorComponent().transferFocus();
			fireEditingStopped();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------������¼�-------|\t"+"mouseClicked");
		switch(e.getButton()){	
			case MouseEvent.BUTTON1://�����������¼�
			{	
				
				
//				table.getEditorComponent().transferFocus();
//				fireEditingStopped();//������ֹ�༭������JTable���
				
//				row = table.rowAtPoint(e.getPoint());
//				column = table.columnAtPoint(e.getPoint());		
//				
//				table.changeSelection(row, column, true, true);
//				table.editCellAt(row, column);
//				table.getEditorComponent().requestFocus();
//				
//				log.info("|--------��ȡ�����Ԫ��-------|\t" + table.getEditingRow()+","+table.getEditingColumn());
//				
//				SwingUtilities.invokeLater(new Runnable(){
//					@Override  
//				    public void run() {						
//						table.repaint();
//						table.revalidate();
//						
//					}
//				});
			}break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------������¼�-------|\t"+"mousePressed");	
//		switch(e.getButton()){	
//			case MouseEvent.BUTTON1://�����������¼�
//			{	
//				table.getEditorComponent().transferFocus();
//				fireEditingStopped();//������ֹ�༭������JTable���
//			}
//		}
		
//		TableCellEditor editor = (TableCellEditor)table.getColumnModel().getColumn(column).getCellEditor();
//		editor.stopCellEditing();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------������¼�-------|\t"+"mouseReleased");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
//		log.info("|--------������¼�-------|\t"+"mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		log.info("|--------������¼�-------|\t"+"mouseExited");		
	}

}
