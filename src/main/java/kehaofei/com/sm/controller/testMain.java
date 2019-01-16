package kehaofei.com.sm.controller;

import java.util.List;

import kehaofei.com.mybatisfactory.SpringBeanFactory;
import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.utils.ControlFactory;

public class testMain {

	/**
	 * @author XCCD
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		SalesTicketInfoModel salesTicketInfo = new SalesTicketInfoModel();
//		salesTicketInfo.setXs_hao("20170715");
		
		
		GoodsInfoController c = (GoodsInfoController) SpringBeanFactory.getInstance().getBean("goodsInfoController");
		
		List<String> list =  c.getUnitList();
		
		System.out.println(list);

	}

}
