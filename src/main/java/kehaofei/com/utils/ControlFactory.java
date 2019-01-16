package kehaofei.com.utils;

import org.springframework.beans.factory.annotation.Autowired;

import kehaofei.com.mybatisfactory.SpringBeanFactory;
import kehaofei.com.sm.controller.CustomerInfoController;
import kehaofei.com.sm.controller.GoodsInfoController;
import kehaofei.com.sm.controller.SalesTicketInfoController;

/**
 * �ṩͳһ�����ݿ����ʵ��
 * @author XCCD
 * <li>TODO	
 * <li>2016-12-26 ����2:55:49
 * <li>
 */
public class ControlFactory {
	
	
	
	
	/**
	 * GoodsInfoList ��Ʒ��Ϣ����ʵ��
	 */
	//@Autowired
	public static GoodsInfoController goodsInfoController;
			//(GoodsInfoController) SpringBeanFactory.getInstance().getBean("goodsInfoController");
	
	/**
	 * CustomerInfo �ͻ���Ϣ����ʵ��
	 */
	
	/*@Autowired*/
	public static CustomerInfoController customerInfoController ;/*= 
			(CustomerInfoController) SpringBeanFactory.getInstance().getBean("customerInfoController");*/
	
	
	/**
	 * ���۵� ��Ϣ����ʵ��
	 */
	/*@Autowired*/
	public static SalesTicketInfoController salesTicketInfoController ;/*= 
			(SalesTicketInfoController) SpringBeanFactory.getInstance().getBean("salesTicketInfoController");*/
	static{
		
		goodsInfoController = (GoodsInfoController) SpringBeanFactory.applicationContext.getBean("goodsInfoController");
		customerInfoController = (CustomerInfoController) SpringBeanFactory.applicationContext.getBean("customerInfoController");
		salesTicketInfoController =(SalesTicketInfoController) SpringBeanFactory.applicationContext.getBean("salesTicketInfoController");
	}
	
}
