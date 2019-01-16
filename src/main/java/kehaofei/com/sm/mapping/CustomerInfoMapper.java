package kehaofei.com.sm.mapping;

import java.util.List;

import kehaofei.com.sm.model.CustomerInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	mybatis ��Ӧ�ڿͻ���Ϣ����   ӳ��ӿ�
 * <li>2016-11-16 ����3:10:10
 * <li>{@link CustomerInfoMapper}
 */
public interface CustomerInfoMapper {
	
	/**
	 * ��ѯ���е���Ʒ��Ϣ
	 * @return
	 */
	List<CustomerInfoModel> queryAll();
	
	/**
	 * 
	 * <li>TODO	����
	 * @param customerInfo
	 */
	void save(CustomerInfoModel customerInfo);
	
	/**
	 * 
	 * <li>TODO	��������
	 * @param socketConnInfo
	 * @return
	 */
	int update(CustomerInfoModel customerInfo);
	
	/**
	 * 
	 * <li>TODO	���ݿͻ����ɾ���ͻ���Ϣ���߼�ɾ�����޸�ɾ�����
	 * @param kehu_no
	 * @return
	 */
	int delete(CustomerInfoModel customerInfo);
	
	/**
	 * 
	 * <li>TODO	����id��ѯ��Ӧ������
	 * @param kehu_no
	 * @return
	 */
	CustomerInfoModel queryById(String kehu_no);

	/**
	 * <li>����������ѯ���
	 * @author XCCD
	 * @param customerInfo
	 * @return
	 */
	List<CustomerInfoModel> queryBySome(CustomerInfoModel customerInfo);

	/**
	 * <li>��ȡ���һ������
	 * @author XCCD
	 * @return
	 */
	CustomerInfoModel queryLast();

	List<CustomerInfoModel> queryRepeat(CustomerInfoModel customerInfo);

}
