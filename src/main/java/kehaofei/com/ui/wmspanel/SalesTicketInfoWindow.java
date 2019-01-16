package kehaofei.com.ui.wmspanel;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
/**
 * 
 * @author XCCD
 * <li>TODO	销售订单管理
 * <li>2017-6-2 上午11:35:07
 * <li>
 */
public class SalesTicketInfoWindow extends JInternalFrame {

	public static Toolkit kit = Toolkit.getDefaultToolkit();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalesTicketInfoWindow frame = new SalesTicketInfoWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalesTicketInfoWindow() {
		setIconifiable(true);
		
//		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		String src = "/kehaofei/com/img/logo.jpg"; // 图片路径
		ImageIcon icon=new ImageIcon(this.getClass().getResource(src));// 读取图标
		setFrameIcon(icon);    // 设置图标
		setTitle("销售订单信息");
		setResizable(true);
		setRootPaneCheckingEnabled(false);
		setMaximizable(true);
		setClosable(true);
		
		
		setBounds(45, 45, 1125, 510);
		
		getContentPane().add(new SalesTicketTabControlPanel());			
		setVisible(true);
		
		
		/*try {						
//			setMaximum(true);
			setSelected(true);			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
