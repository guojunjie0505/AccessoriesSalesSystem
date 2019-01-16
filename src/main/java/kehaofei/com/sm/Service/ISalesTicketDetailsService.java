package kehaofei.com.sm.Service;

import java.util.List;

import kehaofei.com.sm.model.SalesTicketDetailsModel;
import kehaofei.com.sm.model.SalesTicketInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	 ��Ʒ��Ϣservice��ӿ�
 * <li>2017-5-31 ����4:20:25
 * <li>
 */
public interface ISalesTicketDetailsService {

	/**
	 * ���ݲ�ͬ������ѯ����
	 * @author XCCD
	 * @param customerInfo
	 * @return
	 */
	List<SalesTicketDetailsModel> getInfoById(SalesTicketDetailsModel salesTicketDetails);
	
	SalesTicketDetailsModel addSalesTicketDetails(SalesTicketDetailsModel salesTicketDetails);

	void batchSave(List<SalesTicketDetailsModel> salesTicketDetails);

	void saveAndUpdate(List<SalesTicketDetailsModel> salesTicketDetails);

	void batchDelete(Object[] xs_noArr);

}
