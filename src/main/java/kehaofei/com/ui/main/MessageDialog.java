package kehaofei.com.ui.main;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import kehaofei.com.ui.optionGoods.EditGoodsInfoDialog;
import kehaofei.com.ui.wmspanel.SalesTicketInfoPanel;
import kehaofei.com.ui_model.MyTextEditor;
import org.apache.log4j.Logger;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

import javax.swing.JLabel;

public class MessageDialog extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	public static Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
	int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�

	public static JButton btn_confirm;
	
	public static ImplAWTEventListener listener;

	/**
	 * Create the dialog.
	 * @param applicationModal 
	 * @param over 
	 */
	public MessageDialog(Object param) {
		
		
		super(WMSMainFrame.window, ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 350, 120);
		setResizable(false);
		setLocation(screenWidth/2-getWidth()/2, screenHeight/2-getHeight()/2);//���ô��ھ�����ʾ
		setTitle("��Ϣ��ʾ��");
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{50, 50, 50, 50, 50, 50, 50, 0};
		gbl_contentPane.rowHeights = new int[]{30, 0, 30, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gbl_contentPane);
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		btn_confirm = new JButton("\u786E\u5B9A");
		btn_confirm.setName("btn_confirm");
		btn_confirm.addActionListener(this);
		btn_confirm.requestFocus();
		
		JLabel message = new JLabel(param.toString());
		GridBagConstraints gbc_message = new GridBagConstraints();
		gbc_message.gridwidth = 5;
		gbc_message.insets = new Insets(0, 0, 5, 5);
		gbc_message.gridx = 1;
		gbc_message.gridy = 1;
		getContentPane().add(message, gbc_message);
		GridBagConstraints gbc_btn_confirm = new GridBagConstraints();
		gbc_btn_confirm.anchor = GridBagConstraints.NORTH;
		gbc_btn_confirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_btn_confirm.insets = new Insets(0, 0, 0, 5);
		gbc_btn_confirm.gridx = 3;
		gbc_btn_confirm.gridy = 2;
		getContentPane().add(btn_confirm, gbc_btn_confirm);
		
		setModalityType(ModalityType.APPLICATION_MODAL);//�Ի���򿪺����Ƹ����ڲ���	
		listener = new ImplAWTEventListener();
		
		this.kit.addAWTEventListener(listener, AWTEvent.KEY_EVENT_MASK);
		
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
	private class ImplAWTEventListener implements AWTEventListener {  
        @Override  
        public void eventDispatched(AWTEvent event) {  
              if (event.getClass() == KeyEvent.class) {  
                // ��������¼��Ǽ����¼�.  
                KeyEvent keyEvent = (KeyEvent) event;  
                if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {  
                    //����ʱ��Ҫ��������  
                	keyReleased(keyEvent);  
                } 
            }  
        }
	}
	
	
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Component component = e.getComponent();
		String name = component.getName();		
		
		
		int k = e.getKeyCode();
		switch(k){
			case KeyEvent.VK_ENTER:
			{
				log.info("�����س��¼���������");
				btn_confirm.requestFocus();
				this.dispose();//������ɺ�رնԻ���	
				MessageDialog.kit.removeAWTEventListener(MessageDialog.listener);
			}break;
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String className = e.getSource().getClass().getName();
		
		if(className.equals("javax.swing.JButton")){
			JButton bt = (JButton)e.getSource();
			String name = bt.getName();
			if("btn_confirm".equals(name)){//ȷ�ϸ��»�������				
				
				this.dispose();//������ɺ�رնԻ���
				MessageDialog.kit.removeAWTEventListener(MessageDialog.listener);
			}
		}
		
	}
}
