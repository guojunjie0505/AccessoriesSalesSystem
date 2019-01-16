package kehaofei.com.ui.main;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.RowMapper;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.apache.log4j.Logger;

import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.ui.optionSalesTicket.OpenSalesTicketDialog;
import kehaofei.com.ui.optionSalesTicket.SelectSalesTicketInfoPanel;
import kehaofei.com.ui.wmspanel.SalesTicketHeadPanel;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.Constant_Properties;
import kehaofei.com.utils.ControlFactory;

/**
 * �����
 * @author XCCD
 *
 */
public class LeftView_TreePanel extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	private JTree meterTree;
	DefaultMutableTreeNode root = getRoot();
	public static Map<Integer, DefaultMutableTreeNode> map = new Hashtable<Integer, DefaultMutableTreeNode>();	    

	/**
	 * Create the panel.
	 */
	public LeftView_TreePanel() {
//		setBorder(BorderFactory.createTitledBorder(Constant_Properties.myResource.getString("ParamSet")));
//		setVerticalScrollBar(new JScrollBar());
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		getVerticalScrollBar().setUnitIncrement(20);
		
		meterTree = new JTree();

	    meterTree.setModel(new DefaultTreeModel(root));
	    meterTree.getSelectionModel().setSelectionMode(
		DefaultTreeSelectionModel.SINGLE_TREE_SELECTION);
		
//		meterTree.setToolTipText("\u8868\u6570\u636E\u5217\u8868");
		meterTree.setShowsRootHandles(true);

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//		DefaultTreeCellRenderer cellRenderer = new DefaultTreeCellRenderer();
		
		meterTree.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("��ǰѡ�е��У�����"+e.getPath());
				
				
				if(e.getPath().getPathCount()>1){//��׽�Ǹ�Ŀ¼����¼�
					String date="";//��ѯ����---����
					Object[] arrays = e.getPath().getPath();
					for(int i =1; i<arrays.length; i++){
						switch(i){
							case 1:{
								date += String.valueOf(arrays[i]).substring(0,4);
							}break;
							case 2:{
								date += (String.valueOf(arrays[i]).substring(0,2));
							}break;
							
						}
					}
					log.info("|----------------��ǰѡ�е�����-----------------|\t"+date);
					SalesTicketInfoModel ticket = new SalesTicketInfoModel();
					ticket.setXs_hao(date);
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
		});
		
		setViewportView(meterTree);
		
		setVisible(true);
	}
	
	/**
	 * �����ڵ���
	 * @author XCCD
	 * @return
	 */
	private DefaultMutableTreeNode getRoot()  {
		// TODO Auto-generated method stub
	    DefaultMutableTreeNode root = new DefaultMutableTreeNode("���۵������б�");
//	    List<String> list = ControlFactory.salesTicketInfoController.queryDate("2017");	    
	    /*for(String node : list){
	    	DefaultMutableTreeNode children = null;			
	    	children = new DefaultMutableTreeNode(node);
	    	root.add(children);
	    }*/
	    
	    for(int i=2017; i<=Integer.valueOf(ByteUtils.getNowTimeStr("yyyy")); i++){
	    	DefaultMutableTreeNode children = null;			
	    	children = new DefaultMutableTreeNode(i+"���");
	    	root.add(children);
	    	
	    	for(int j=1; j<=12; j++){
	    		DefaultMutableTreeNode children1 = null;
	    		String month = String.valueOf(j).length()%2==0?String.valueOf(j):"0"+j;
		    	children1 = new DefaultMutableTreeNode(month+"�·�");
		    	children.add(children1);
	    	}
	    }
//	    initTree1(list,map);
	    return root;
	}


//	public static void initTree1(List<String> list,
//			Map<Integer, DefaultMutableTreeNode> map) {
//		for (String node : list) {
//			DefaultMutableTreeNode children = null;
//				
//			children = new DefaultMutableTreeNode(node);
//
//			map.get(meterLibrary.getMeter_type()).add(children);
//						
//		}
//	}

}
