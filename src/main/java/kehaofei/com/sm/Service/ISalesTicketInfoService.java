package kehaofei.com.sm.Service;

import java.util.List;

import kehaofei.com.sm.model.SalesTicketInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	 ��Ʒ��Ϣservice��ӿ�
 * <li>2017-5-31 ����4:20:25
 * <li>
 */
public interface ISalesTicketInfoService {

	/**
	 * ���ݲ�ͬ������ѯ����
	 * @author XCCD
	 * @param customerInfo
	 * @return
	 */
	List<SalesTicketInfoModel> getInfoById(SalesTicketInfoModel salesTicketInfo);

	/**
	 * ��ѯ�������µ�һ������
	 * @author XCCD
	 * @return
	 */
	SalesTicketInfoModel getXSKeyList();
	
	SalesTicketInfoModel addSalesTicketInfo(SalesTicketInfoModel salesTicketInfo);

	void update(SalesTicketInfoModel salesTicketInfo);

	List<SalesTicketInfoModel> queryBySome(SalesTicketInfoModel salesTicketInfo);

	List<String> queryDate(String dataStr);

}
