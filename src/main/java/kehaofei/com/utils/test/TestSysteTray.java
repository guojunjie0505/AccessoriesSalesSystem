package kehaofei.com.utils.test;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * swing������С����ϵͳ����
 * 
 * @author seara
 */
public class TestSysteTray {
	public static void main(String args[]) {

		TrayIcon trayIcon = null;
		if (SystemTray.isSupported()) // �ж�ϵͳ�Ƿ�֧��ϵͳ����
		{
			SystemTray tray = SystemTray.getSystemTray(); // ����ϵͳ����
			Image image = Toolkit.getDefaultToolkit().getImage("D:\\j2EE\\test\\src\\images\\smile.gif");// ����ͼƬ,����Ҫд���ͼ��·��Ŷ

			ActionListener listener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame frame = new JFrame();
					frame.setBounds(400, 400, 200, 200);
					JLabel label = new JLabel("welcome JDK1.6");
					frame.add(label);
					frame.setVisible(true);
				}

			};
			// ���������˵�
			PopupMenu popup = new PopupMenu();
			// ������ѡ��
			MenuItem mainFrameItem = new MenuItem("������");
			mainFrameItem.addActionListener(listener);

			// �˳�����ѡ��
			MenuItem exitItem = new MenuItem("�˳�����");
			exitItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (JOptionPane.showConfirmDialog(null, "ȷ���˳�ϵͳ") == 0) {
						System.exit(0);
					}
				}
			});

			popup.add(mainFrameItem);
			popup.add(exitItem);

			trayIcon = new TrayIcon(image, "seara", popup);// ����trayIcon
			trayIcon.addActionListener(listener);
			try {
				tray.add(trayIcon);
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
		}
	}
}