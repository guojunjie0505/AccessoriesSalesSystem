package kehaofei.com.sm.mapping;

import java.util.List;

import kehaofei.com.sm.model.SalesTicketDetailsModel;

/**
 * 
 * @author XCCD
 * <li>TODO	mybatis ӳ��ӿ�
 * <li>2016-11-16 ����3:10:10
 * <li>{@link SalesTicketDetailsMapper}
 */
public interface SalesTicketDetailsMapper {
	
	/**
	 * ��ѯ���е���Ʒ��Ϣ
	 * @return
	 */
	List<SalesTicketDetailsModel> queryAll();
	
	/**
	 * 
	 * <li>TODO	����
	 * @param salesTicketDetails
	 * @return 
	 */
	void save(SalesTicketDetailsModel salesTicketDetails);

	void batchSave(List<SalesTicketDetailsModel> salesTicketDetails);

	/**
	 * ������ϸ����
	 * @author XCCD
	 * @param model
	 */
	void update(SalesTicketDetailsModel model);

	void batchDelete(Integer[] xs_noArr);


}
