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
 * <li>TODO	单元格编辑适配器
 * <li>2017-10-10 下午1:27:13
 * <li>
 */
public class MyTextEditor implements TableCellEditor, KeyListener, FocusListener, MouseListener{
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	//editor show
    private JTextField textField = null;
	private JTable table= SalesTicketInfoPanel.table;
	
    
	
    //ChangeEvent用于通知感兴趣的参与者事件源中的状态已发生更改。
    private static ChangeEvent changeEvent = new ChangeEvent(MyTextEditor.class);

	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"getCellEditorValue\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		try {
			if(table.getValueAt(0, SalesTicketInfoPanel.column) instanceof BigDecimal){
				return new BigDecimal(textField.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(table, "数据格式错误");
			return "0.0";
		}
		try{
			if(table.getValueAt(0, SalesTicketInfoPanel.column) instanceof Integer){
				return Integer.valueOf(textField.getText());
			}
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(table, "数据格式错误");
			return "1";
		}
			
		return textField.getText();		
		
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"isCellEditable\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
		
		return true;
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		// TODO Auto-generated method stub
//		textField.requestFocus();//获取焦点数据
		log.info("|------表格单元格编辑模型------|\t"+"shouldSelectCell\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"stopCellEditing\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
//		table.setValueAt(getCellEditorValue(), row, column);
		
		//可以注释掉下面的fireEditingStopped();，然后在GenderEditor的构造函数中把
        //addActionListener()的注释去掉（这时请求终止编辑操作从JComboBox获得），
//        System.out.println("编辑其中一个单元格，再点击另一个单元格时，调用。");
//        fireEditingStopped();//请求终止编辑操作从JTable获得
		
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
                  //之所以是i+1，是因为一个为CellEditorListener.class（Class对象），
                  //接着的是一个CellEditorListener的实例
                  listener= (CellEditorListener)listeners[i+1];
                  //让changeEvent去通知编辑器已经结束编辑
                  //在editingStopped方法中，JTable调用getCellEditorValue()取回单元格的值，
                  //并且把这个值传递给TableValues(TableModel)的setValueAt()
                  listener.editingStopped(changeEvent);
                  
             }
        }
	}

	@Override
	public void cancelCellEditing() {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"cancelCellEditing\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"addCellEditorListener\t"
				+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
//		table.setValueAt(getCellEditorValue(), row, column);
			
		SalesTicketInfoPanel.listenerList.add(CellEditorListener.class,l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		// TODO Auto-generated method stub
		log.info("|------表格单元格编辑模型------|\t"+"removeCellEditorListener");
		SalesTicketInfoPanel.listenerList.remove(CellEditorListener.class,l);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		
		log.info("|------表格单元格编辑模型------|\t"+"getTableCellEditorComponent\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		
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
		
		log.info("|------表格单元格编辑模型------|\t"+"getTableCellEditorComponent\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
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
		
		System.out.println("编辑框事件：：：："+e.getKeyChar());
		log.info("|------单元格编辑时候的坐标------|\t"+SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
		switch(e.getKeyCode()){
			
			case KeyEvent.VK_DOWN://键盘↓方向键事件
			{
				
				if(SalesTicketInfoPanel.row == table.getRowCount()-2){
					log.info("判断当前数据新增一行");	
					
					if(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2]!=null 
							&& !"".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2])){
//						Object[] total = ContextValue.SalesTicketInfoListData.get(ContextValue.SalesTicketInfoListData.size()-1);
						ContextValue.SalesTicketInfoListData.add(SalesTicketInfoPanel.row+1, new Object[]{"","","",SalesTicketInfoPanel.row+2,Boolean.FALSE,"","","","","","","","","",""});
						
						
						table.getEditorComponent().transferFocus();
						fireEditingStopped();//请求终止编辑操作从JTable获得
						table.changeSelection(SalesTicketInfoPanel.row+1, 5, true, true);
						table.editCellAt(SalesTicketInfoPanel.row+1, 5);
						table.getEditorComponent().requestFocus();
					}					
				}else{
					table.getEditorComponent().transferFocus();		
					fireEditingStopped();//请求终止编辑操作从JTable获得
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
			case KeyEvent.VK_UP://键盘↑方向键事件
			{
				
				log.info("判断当前数据减少一行");	
				if(SalesTicketInfoPanel.row == 0){
					return;
				}
					
				//判断当前行配件是否存在
				if((ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2] ==null 
						|| "".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2]))){
					
					ContextValue.SalesTicketInfoListData.remove(SalesTicketInfoPanel.row);						
					
					table.getEditorComponent().transferFocus();
					fireEditingStopped();//请求终止编辑操作从JTable获得
					table.changeSelection(SalesTicketInfoPanel.row-1, SalesTicketInfoPanel.column, true, true);
					table.editCellAt(SalesTicketInfoPanel.row-1, SalesTicketInfoPanel.column);
					table.getEditorComponent().requestFocus();
					
				}else{
					table.getEditorComponent().transferFocus();
					fireEditingStopped();//请求终止编辑操作从JTable获得
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
			
			case KeyEvent.VK_LEFT://键盘←方向键事件
			{
				if(SalesTicketInfoPanel.column <= 5){
					return;
				}
				table.getEditorComponent().transferFocus();
				fireEditingStopped();//请求终止编辑操作从JTable获得
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
			
			case KeyEvent.VK_RIGHT://键盘→方向键事件
			{
				if(SalesTicketInfoPanel.column == table.getColumnCount()-1){
					return;
				}
				table.getEditorComponent().transferFocus();
				fireEditingStopped();//请求终止编辑操作从JTable获得
				
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
			{// 回车事件,加载客户信息
				if(SalesTicketInfoPanel.column == 5 && !"".equals(textField.getText())){
					GoodsInfoModel goodsInfo = new GoodsInfoModel();
					goodsInfo.setPeij_py(textField.getText());
					List<GoodsInfoModel> goodsInfoList = ControlFactory.goodsInfoController.queryBySome(goodsInfo);
					
					if(goodsInfoList.size() == 0){

						if (SalesTicketInfoPanel.table.isEditing()){ 
							table.getEditorComponent().transferFocus();
							MyTextEditor.fireEditingStopped();
						}						

						MessageDialog messageDialog = new MessageDialog("没有商品信息");
						
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
						
//						JOptionPane.showMessageDialog(null, "没有商品信息");

						return;
					}else if(goodsInfoList.size() == 1){//只有一条数据直接填写到表格
						
						for(Object[] ob : ContextValue.SalesTicketInfoListData){
							if(ob[2].equals(goodsInfoList.get(0).getPeij_no())){	
								
								MessageDialog messageDialog = new MessageDialog("商品已存在于列表中");
								
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
								ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[0],//销售明细id
								ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[1],//销售单的Id
								goodsInfoList.get(0).getPeij_no(),//商品id
								String.valueOf(SalesTicketInfoPanel.row+1),
								Boolean.FALSE,
								goodsInfoList.get(0).getPeij_py(),
								goodsInfoList.get(0).getPeij_name(),
								goodsInfoList.get(0).getPeij_lb(),
								goodsInfoList.get(0).getBrand(),
								goodsInfoList.get(0).getCode(),//规格
								goodsInfoList.get(0).getUnit().trim(),//单位
								goodsInfoList.get(0).getOut_price1().toString(),//单价
								1,
								goodsInfoList.get(0).getOut_price1().toString(),//这里是单件商品总价
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
						
						//最画框关闭监听
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

					}//if语句结束
				}//if语句结束				
			}	
			
			default://其他键值
			{
				JTextField jf = (JTextField)component;//获取对象信息
				
				
				if(SalesTicketInfoPanel.column >=6 &&(
						ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2] == null 
						|| "".equals(ContextValue.SalesTicketInfoListData.get(SalesTicketInfoPanel.row)[2]))){
					jf.setText("");
					JOptionPane.showMessageDialog(null, "请选择商品后再进行编辑");
					return;
				}
				if("NUMBER".equals(name)){
					
					String str = jf.getText().trim();					
					String exp = "^\\d+$";//"^[0-9]{0,6}+(.[0-9]{0,3})?$";//小数正则表达式
					Pattern pattern = Pattern.compile(exp);
					boolean flag = pattern.matcher(str).matches();
					if (flag) {//通过验证
						
					} else {
						String number = str.replaceAll(String.valueOf(e.getKeyChar()), "");
						jf.setText(number.equals("")?"0":number);
//						jf.setText("0");
						JOptionPane.showMessageDialog(null, "请输入整数");				
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
					String exp = "^[0-9]{0,6}+(.[0-9]{0,3})?$";//小数正则表达式
					Pattern pattern = Pattern.compile(exp);
					boolean flag = pattern.matcher(str).matches();
					if (flag) {//通过验证
						
					} else {
						String number = str.replaceAll(String.valueOf(e.getKeyChar()), "");
						jf.setText(number.equals("")?"0.0":number);
//						jf.setText("0.0");
						JOptionPane.showMessageDialog(null, "请输入有效数据");				
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
				ContextValue.TextEditFlag = true;//标记单元格编辑状态
			}break;
		}		
		
	}
	
	/**
	 * 自动计算合计值--数量列
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
//			JOptionPane.showMessageDialog(null, "数据格式有误");
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
//			JOptionPane.showMessageDialog(null, "数据格式有误");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------获得编辑焦点-------|\t"+
				SalesTicketInfoPanel.row+","+SalesTicketInfoPanel.column);
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------失去编辑焦点-------|\t"+
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
		log.info("|--------鼠标点击事件-------|\t"+"mouseClicked");
		switch(e.getButton()){	
			case MouseEvent.BUTTON1://鼠标左键单击事件
			{	
				
				
//				table.getEditorComponent().transferFocus();
//				fireEditingStopped();//请求终止编辑操作从JTable获得
				
//				row = table.rowAtPoint(e.getPoint());
//				column = table.columnAtPoint(e.getPoint());		
//				
//				table.changeSelection(row, column, true, true);
//				table.editCellAt(row, column);
//				table.getEditorComponent().requestFocus();
//				
//				log.info("|--------获取点击单元格-------|\t" + table.getEditingRow()+","+table.getEditingColumn());
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
		log.info("|--------鼠标点击事件-------|\t"+"mousePressed");	
//		switch(e.getButton()){	
//			case MouseEvent.BUTTON1://鼠标左键单击事件
//			{	
//				table.getEditorComponent().transferFocus();
//				fireEditingStopped();//请求终止编辑操作从JTable获得
//			}
//		}
		
//		TableCellEditor editor = (TableCellEditor)table.getColumnModel().getColumn(column).getCellEditor();
//		editor.stopCellEditing();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		log.info("|--------鼠标点击事件-------|\t"+"mouseReleased");
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
//		log.info("|--------鼠标点击事件-------|\t"+"mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		log.info("|--------鼠标点击事件-------|\t"+"mouseExited");		
	}

}
