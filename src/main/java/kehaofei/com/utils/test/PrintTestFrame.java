package kehaofei.com.utils.test;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class PrintTestFrame extends JFrame implements ActionListener {

	public static void main(String[] args) {
		JFrame frame = new PrintTestFrame();
		frame.show();
	}

	public PrintTestFrame() {
		setTitle("PrintTest");
		setSize(300, 300);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container contentPane = getContentPane();
		canvas = new PrintPanel();
		contentPane.add(canvas, "Center");
		JPanel buttonPanel = new JPanel();
		printButton = new JButton("Print");
		buttonPanel.add(printButton);
		printButton.addActionListener(this);
		pageSetupButton = new JButton("Page  setup");
		buttonPanel.add(pageSetupButton);
		pageSetupButton.addActionListener(this);
		contentPane.add(buttonPanel, "North");
	}

	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		if (source == printButton) {
			PrinterJob printJob = PrinterJob.getPrinterJob();
			if (pageFormat == null)
				pageFormat = printJob.defaultPage();
			printJob.setPrintable(canvas, pageFormat);
			if (printJob.printDialog()) {
				try {
					printJob.print();
				} catch (PrinterException exception) {
					JOptionPane.showMessageDialog(this, exception);
				}
			}
		} else if (source == pageSetupButton) {
			PrinterJob printJob = PrinterJob.getPrinterJob();
			if (pageFormat == null)
				pageFormat = printJob.defaultPage();
			pageFormat = printJob.pageDialog(pageFormat);
		}
	}

	private JButton printButton;
	private JButton pageSetupButton;
	private PrintPanel canvas;
	private PageFormat pageFormat;
}

class PrintPanel extends JPanel implements Printable {
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		drawPage(g2);
	}

	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page >= 1)
			return Printable.NO_SUCH_PAGE;
		Graphics2D g2 = (Graphics2D) g;
		g2.setPaint(Color.black);
		g2.translate(pf.getImageableX(), pf.getImageableY());
		g2.draw(new Rectangle2D.Double(0, 0, pf.getImageableWidth(), pf
				.getImageableHeight()));
		drawPage(g2);
		return Printable.PAGE_EXISTS;
	}

	public void drawPage(Graphics2D g2) {
		FontRenderContext context = g2.getFontRenderContext();
		Font f = new Font("Serif", Font.PLAIN, 72);
		GeneralPath clipShape = new GeneralPath();
		TextLayout layout = new TextLayout("Hello", f, context);
		AffineTransform transform = AffineTransform.getTranslateInstance(0, 72);
		Shape outline = layout.getOutline(transform);
		clipShape.append(outline, false);
		layout = new TextLayout("World", f, context);
		transform = AffineTransform.getTranslateInstance(0, 144);
		outline = layout.getOutline(transform);
		clipShape.append(outline, false);
		g2.draw(clipShape);
		g2.clip(clipShape);
		final int NLINES = 50;
		Point2D p = new Point2D.Double(0, 0);
		for (int i = 0; i < NLINES; i++) {
			double x = (2 * getWidth() * i) / NLINES;
			double y = (2 * getHeight() * (NLINES - 1 - i)) / NLINES;
			Point2D q = new Point2D.Double(x, y);
			g2.draw(new Line2D.Double(p, q));
		}
	}
	
	/**
	02. * ��ӡģ��
	03. *
	04. * @param imgWidth ͼƬ�Ŀ��
	05. * @param imgHeight ͼƬ�ĸ߶�
	06. * @param x ��ʼx��
	07. * @param y ��ʼy��
	08. * @param rowHeight ÿ�еĸ߶�
	09. * @param dataStart ��������
	10. * @param firstWidth ��һ�м��x����
	11. * @param secondWidth �ڶ��м��x����
	12. * @param thirdWidth �����м��x����
	13. * @param fourWidth �����м��x����
	14. * @param tb ʵ�����
	15. * @return BufferedImage
	16. *(�����Լ������ҵ���340, 200, 0, 0, 20, 10, 60, 190, 250, 340, tb)
	17. */
	/*private BufferedImage createTbGraphics(int imgWidth, int imgHeight, int x, int y, int rowHeight,
	int dataStart, int firstWidth, int secondWidth, int thirdWidth, int fourWidth,
	Tb tb) {
	//�հ���� Ҳ������ͼƬ
	BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
	Graphics2D g = null;
	
	try {
	
	g = image.createGraphics();// �õ�ͼ��������
	g.setBackground(Color.WHITE);//���ñ���ɫ
	g.fillRect(x, y, imgWidth, imgHeight);//���������Ļ
	g.setColor(Color.BLACK);//���û�����ɫ
	// g.setFont(new Font("Arial", Font.LAYOUT_LEFT_TO_RIGHT, 12));// �������� �����Լ����ǹ����������� �а취����Ļ�Ҳ��Ϊʵ�ߣ� ����Ϊ��������Ϊ�����ӡ����Ե�ʣ�������Ҫ���ο�����ӡ����������ûҪ���ַ���
	g.setFont(new Font("΢���ź�", Font.TYPE1_FONT, 12));// �������� ��������ΪFont.ITALIC �������������ӡ����ӡ�����ĵ�����ʵ����
	// g.setFont(new Font("simsun", Font.TYPE1_FONT, 12));// jvm�� �뽫�����ʽsimsun����jvm��ֱ�ӵ��ã���ӡ����Ҳ����
	
	g.drawLine(x, y, imgWidth, y);// ��һ������
	
	 g.drawLine(x, y, x, rowHeight);//����
	// g.drawString(new String("����".getBytes("utf-8"),"utf-8"), dataStart, rowHeight - 5);
	 g.drawString("����", dataStart, rowHeight - 5);
	g.drawLine(firstWidth, y, firstWidth, rowHeight);
	g.drawString(tb.getName() == null ? "" : tb.getName() , firstWidth + dataStart, rowHeight - 5);
	g.drawLine(imgWidth - 1, y, imgWidth - 1, rowHeight);
	
	g.drawLine(x, rowHeight, imgWidth, rowHeight); //����
	g.drawLine(x, rowHeight, x, rowHeight * 2);
	 g.drawString("����", dataStart, rowHeight * 2 - 5); // * 2����ڶ���
	g.drawLine(firstWidth, rowHeight, firstWidth, rowHeight * 2);
	g.drawString(tb.getCode() == null ? "" : tb.getCode() , firstWidth + dataStart, rowHeight * 2 - 5);
	g.drawLine(secondWidth, rowHeight, secondWidth, rowHeight * 2);
	
	//��������....
	
	//���һ����
	g.drawLine(x, imgHeight - 1, imgWidth, imgHeight - 1);
	
	//��ָ������(198,61)�� д���ά�������ͼƬ
//	g.drawImage(ImageIO.read(getServletContext().getResourceAsStream(t.getPicUrl())), null, 198, 61);
	g.dispose();
	} catch (IOException e) {
	e.printStackTrace();
	}
	
	return image;
	} */

}