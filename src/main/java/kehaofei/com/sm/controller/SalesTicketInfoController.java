package kehaofei.com.sm.controller;

import java.util.List;
import java.util.Vector;

import kehaofei.com.sm.Service.ICustomerInfoService;
import kehaofei.com.sm.Service.ISalesTicketDetailsService;
import kehaofei.com.sm.Service.ISalesTicketInfoService;
import kehaofei.com.sm.model.CustomerInfoModel;
import kehaofei.com.sm.model.GoodsInfoModel;
import kehaofei.com.sm.model.SalesTicketDetailsModel;
import kehaofei.com.sm.model.SalesTicketInfoModel;
import kehaofei.com.utils.ByteUtils;
import kehaofei.com.utils.ContextValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 
 * @author XCCD
 * <li>TODO	���۵�������
 * <li>2016-11-16 ����3:18:18
 * <li>
 */
//@Component("salesTicketInfoController")
public class SalesTicketInfoController {
    
//	@Autowired
    private ISalesTicketInfoService salesTicketInfoService;
		
//	@Autowired
    private ISalesTicketDetailsService salesTicketDetailsService;
	
    
    private TransactionTemplate transactionTemplate;
	
//	private Vector<Object[]> vector;
	
	
	public ISalesTicketInfoService getSalesTicketInfoService() {
		return salesTicketInfoService;
	}

	/**
	 * @return the transactionTemplate
	 */
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	/**
	 * @param transactionTemplate the transactionTemplate to set
	 */
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setSalesTicketInfoService(
			ISalesTicketInfoService salesTicketInfoService) {
		this.salesTicketInfoService = salesTicketInfoService;
	}

	public ISalesTicketDetailsService getSalesTicketDetailsService() {
		return salesTicketDetailsService;
	}

	public void setSalesTicketDetailsService(
			ISalesTicketDetailsService salesTicketDetailsService) {
		this.salesTicketDetailsService = salesTicketDetailsService;
	}

	public void LoadSalesTicketInfoVector(SalesTicketInfoModel salesTicketInfo) {
		// TODO Auto-generated method stub
		
		List<SalesTicketInfoModel> salesTicketInfoList = salesTicketInfoService.queryBySome(salesTicketInfo);
        
        ContextValue.SelectSalesTicketInfoListData.clear();
        for(int i=0; i<salesTicketInfoList.size(); i++){
        	Object[] obj = new Object[]{
        			salesTicketInfoList.get(i).getXs_hao(),
            		i+1,
            		Boolean.FALSE, 
            		salesTicketInfoList.get(i).getXs_hao(),
            		salesTicketInfoList.get(i).getKehu_name(),
            		salesTicketInfoList.get(i).getXs_date(),
            		salesTicketInfoList.get(i).getXs_moneyall()==null?"":salesTicketInfoList.get(i).getXs_moneyall(),
            		"",
            		salesTicketInfoList.get(i).getKehu_tel()==null?"":salesTicketInfoList.get(i).getKehu_tel(),
            	
        	};
        	ContextValue.SelectSalesTicketInfoListData.add(obj);
        }
		
	}
	
	public List<SalesTicketInfoModel> getInfoById(SalesTicketInfoModel salesTicketInfo){
		return salesTicketInfoService.getInfoById(salesTicketInfo);
	}
	
		
	/**
	 * ���������۵��ݵ�����
	 * @author XCCD
	 * @return
	 */
	public String generateXSKey(){
		//�������һ������
		SalesTicketInfoModel lastOne = salesTicketInfoService.getXSKeyList();
		if(lastOne == null){
			return "xsd-"+ByteUtils.getNowTimeStr("yyyyMMdd")+"-0001";
		}else{
			String[] keyArr = lastOne.getXs_hao().split("-");
			int index = 1+ Integer.parseInt(keyArr[2]);
			String indexStr = "0000".substring(0,4-String.valueOf(index).length())+ index;
			
			return keyArr[0]+"-"+keyArr[1]+"-"+indexStr;
		}
		
	}

//	@Transactional(rollbackFor = { Exception.class })
	public SalesTicketInfoModel add(final SalesTicketInfoModel salesTicketInfo) {
		// TODO Auto-generated method stub
		transactionTemplate.execute(new TransactionCallback<SalesTicketInfoModel>() {

			@Override
			public SalesTicketInfoModel doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				try {
					
					salesTicketInfoService.addSalesTicketInfo(salesTicketInfo);
					
					salesTicketDetailsService.batchSave(salesTicketInfo.getSalesTicketDetails());//����������ϸ����
				} catch (Exception e) {
					// TODO: handle exception
					status.setRollbackOnly();
				}
				
				return salesTicketInfo;
			}
		});
		/*try {
			
			salesTicketInfoService.addSalesTicketInfo(salesTicketInfo);
			
			salesTicketDetailsService.batchSave(salesTicketInfo.getSalesTicketDetails());//����������ϸ����
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�쳣�ع�
		}*/
		
		return salesTicketInfoService.getInfoById(salesTicketInfo).get(0);
	}


	/**
	 * ����������
	 * @author XCCD
	 * @param salesTicketInfo
	 * @return 
	 */
//	@Transactional(rollbackFor = { Exception.class })
	public SalesTicketInfoModel update(final SalesTicketInfoModel salesTicketInfo, final Object[] xs_noArr) {
		// TODO Auto-generated method stub
		
		transactionTemplate.execute(new TransactionCallback<SalesTicketInfoModel>() {

			@Override
			public SalesTicketInfoModel doInTransaction(TransactionStatus status) {
				// TODO Auto-generated method stub
				try {
					
					salesTicketInfoService.update(salesTicketInfo);//�������۵�
					
					if(xs_noArr.length != 0){
						salesTicketDetailsService.batchDelete( xs_noArr);//����ɾ����ϸ
					}
					
					for(int i=0; i<salesTicketInfo.getSalesTicketDetails().size(); i++){
						salesTicketInfo.getSalesTicketDetails().get(i).setXs_hao(salesTicketInfo.getXs_hao());
					}
					
					salesTicketDetailsService.saveAndUpdate(salesTicketInfo.getSalesTicketDetails());//��ϸ���������������޸�
				} catch (Exception e) {
					// TODO: handle exception
					status.setRollbackOnly();
				}
				
				return salesTicketInfo;
			}
		});
		/*try {
			salesTicketInfoService.update(salesTicketInfo);//�������۵�
			
			if(xs_noArr.length != 0){
				salesTicketDetailsService.batchDelete( xs_noArr);//����ɾ����ϸ
			}
			
			for(int i=0; i<salesTicketInfo.getSalesTicketDetails().size(); i++){
				salesTicketInfo.getSalesTicketDetails().get(i).setXs_hao(salesTicketInfo.getXs_hao());
			}
			
			salesTicketDetailsService.saveAndUpdate(salesTicketInfo.getSalesTicketDetails());//��ϸ���������������޸�
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//�쳣�ع�
		}*/
		
		return salesTicketInfoService.getInfoById(salesTicketInfo).get(0);
	}


	/**
	 * ��ѯ������ݱ�
	 * @author XCCD
	 * @param dataStr
	 * @return
	 */
	/*public List<String> queryDate(String dataStr){
		return salesTicketInfoService.queryDate(dataStr);
		
	}*/
	
	
}
