package kehaofei.com.sm.Service;

import java.util.List;

import kehaofei.com.sm.model.CustomerInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	 ��Ʒ��Ϣservice��ӿ�
 * <li>2017-5-31 ����4:20:25
 * <li>
 */
public interface ICustomerInfoService {

	List<CustomerInfoModel> loadCustomerInfoList();	
	
	void insert(CustomerInfoModel customerInfo);
	
	boolean update(CustomerInfoModel customerInfo);
	
	boolean delete(String kehu_no);
	
	CustomerInfoModel queryById(String kehu_no);

	/**
	 * ���ݲ�ͬ������ѯ����
	 * @author XCCD
	 * @param customerInfo
	 * @return
	 */
	List<CustomerInfoModel> queryBySome(CustomerInfoModel customerInfo);

	CustomerInfoModel queryLast();

	List<CustomerInfoModel> queryRepeat(CustomerInfoModel customerInfo);

}
