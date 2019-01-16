package kehaofei.com.sm.mapping;

import java.util.List;

import kehaofei.com.sm.model.SalesTicketInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	mybatis ��Ӧ�����۵�������Ϣ   ӳ��ӿ�
 * <li>2016-11-16 ����3:10:10
 * <li>{@link SalesTicketInfoMapper}
 */
public interface SalesTicketInfoMapper {
	
	/**
	 * ��ѯ���еĶ�����Ϣ
	 * @return
	 */
	List<SalesTicketInfoModel> queryAll();
	
	/**
	 * 
	 * <li>TODO	����
	 * @param salesTicketInfo
	 */
	void save(SalesTicketInfoModel salesTicketInfo);
	
	/**
	 * 
	 * <li>TODO	��������
	 * @param salesTicketInfo
	 * @return
	 */
	int update(SalesTicketInfoModel salesTicketInfo);
	
	
	/**
	 * 
	 * <li>TODO	����id��ѯ��Ӧ������
	 * @param xs_hao
	 * @return
	 */
	List<SalesTicketInfoModel> queryBySome(SalesTicketInfoModel model);


	/**
	 * <li>��ȡ���һ������
	 * @author XCCD
	 * @return
	 */
	SalesTicketInfoModel queryLast();
	
	
	/**
	 * ���ӽṹһ�������ѯ
	 * @author XCCD
	 * @param xs_hao
	 * @return
	 */
	List<SalesTicketInfoModel> getInfoById(SalesTicketInfoModel salesTicketInfo);

	/**
	 * ��ѯ�������۶����б�
	 * @author XCCD
	 * @param model
	 * @return
	 */
	List<SalesTicketInfoModel> getXSKeyList(SalesTicketInfoModel model);
	
	/**
	 * ��ѯ���������е�������ݱ�
	 * @author XCCD
	 * @param dataStr
	 * @return
	 */
	List<String> queryDate(String dataStr);

}
