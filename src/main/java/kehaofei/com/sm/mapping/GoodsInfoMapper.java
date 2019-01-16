package kehaofei.com.sm.mapping;

import java.util.List;
import java.util.Map;

import kehaofei.com.sm.model.GoodsInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	mybatis ӳ��ӿ�
 * <li>2016-11-16 ����3:10:10
 * <li>{@link GoodsInfoMapper}
 */
public interface GoodsInfoMapper {
	
	/**
	 * ��ѯ���е���Ʒ��Ϣ
	 * @return
	 */
	List<GoodsInfoModel> queryAll();
	
	/**
	 * ��ѯ��ȡ���һ������
	 * @return
	 */
	GoodsInfoModel queryLast();
	
	/**
	 * 
	 * <li>TODO	����
	 * @param goodsInfo
	 */
	void save(GoodsInfoModel goodsInfo);
	
	/**
	 * 
	 * <li>TODO	��������
	 * @param socketConnInfo
	 * @return
	 */
	int update(GoodsInfoModel goodsInfo);
	
	/**
	 * 
	 * <li>TODO	������Ʒ���ɾ����Ʒ��Ϣ���߼�ɾ�����޸�ɾ�����
	 * @param peij_no
	 * @return
	 */
	int delete(GoodsInfoModel goodsInfo);
	
	/**
	 * 
	 * <li>TODO	����id��ѯ��Ӧ������
	 * @param peij_no
	 * @return
	 */
	GoodsInfoModel queryById(String peij_no);

	/**
	 * <li>����������ѯ���
	 * @author XCCD
	 * @param goodsInfo
	 * @return
	 */
	List<GoodsInfoModel> queryBySome(GoodsInfoModel goodsInfo);
	
	/**
	 * <li>��ѯ�Ƿ������������
	 * @author XCCD
	 * @param goodsInfo
	 * @return
	 */
	List<GoodsInfoModel> queryRepeat(GoodsInfoModel goodsInfo);
	
	/**
	 * 
	 * @author kehaofei
	 * @return �������ݼ���
	 */
	List<String> getUnitList();

	/**
	 * 
	 * @author XCCD
	 * @return
	 */
	List<String> getTypeList();
}
