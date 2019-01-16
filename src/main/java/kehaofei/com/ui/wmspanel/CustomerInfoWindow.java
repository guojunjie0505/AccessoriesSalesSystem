package kehaofei.com.ui.wmspanel;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
/**
 * 
 * @author XCCD
 * <li>TODO	商品信息管理
 * <li>2017-6-2 上午11:35:07
 * <li>
 */
public class CustomerInfoWindow extends JInternalFrame {

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
					CustomerInfoWindow frame = new CustomerInfoWindow();
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
	public CustomerInfoWindow() {
		setIconifiable(true);
		
//		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		String src = "/kehaofei/com/img/logo.jpg"; // 图片路径
		ImageIcon icon=new ImageIcon(this.getClass().getResource(src));// 读取图标
		setFrameIcon(icon);    // 设置图标
		setTitle("客户信息管理");
		setResizable(true);
		setMaximizable(true);
		setRootPaneCheckingEnabled(false);
		setClosable(true);
		/*try {
			setSelected(true);
			
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		setBounds(25, 25, 1125, 510);
		
		getContentPane().add(new CustomerTabControlPanel());	
		
		setVisible(true);
	}

}
