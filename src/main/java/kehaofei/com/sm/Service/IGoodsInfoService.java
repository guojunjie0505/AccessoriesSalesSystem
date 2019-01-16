package kehaofei.com.sm.Service;

import java.util.List;

import kehaofei.com.sm.model.GoodsInfoModel;

/**
 * 
 * @author XCCD
 * <li>TODO	 ��Ʒ��Ϣservice��ӿ�
 * <li>2017-5-31 ����4:20:25
 * <li>
 */
public interface IGoodsInfoService {

	List<GoodsInfoModel> loadGoodsInfoList();	
	
	GoodsInfoModel queryLast();
	
	void insert(GoodsInfoModel goodsInfo);
	
	boolean update(GoodsInfoModel goodsInfo);
	
	boolean delete(String peij_no);
	
	GoodsInfoModel queryById(String peij_no);

	/**
	 * ���ݲ�ͬ������ѯ����
	 * @author XCCD
	 * @param goodsInfo
	 * @return
	 */
	List<GoodsInfoModel> queryBySome(GoodsInfoModel goodsInfo);

	/**
	 * ��ѯ�Ƿ�����ظ�
	 * @author XCCD
	 * @param goodsInfo 
	 * @return
	 */
	List<GoodsInfoModel> queryRepeat(GoodsInfoModel goodsInfo);
	
	/**
	 * ��ȡ��λ����
	 * @author XCCD
	 * @return
	 */
	List<String> getUnitList();
	
	/**
	 * ��ȡϵͳ���е������б�
	 * @author XCCD
	 * @return
	 */
	List<String> getTypeList();

}
