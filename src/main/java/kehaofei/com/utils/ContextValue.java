package kehaofei.com.utils;

import java.util.Vector;

public class ContextValue {
	
	public static boolean TextEditFlag = false;
	

	/**
	 * ��Ʒ��Ϣ�б���Ϣ����
	 */
	public static Object[] GoodsInfoListObj = 
			new Object[]{"ID", "���", "ѡ��", "ƴ��", "��Ʒ����", "���", "���", "Ʒ��", "��λ", "����", "���ۼ�һ", "���ۼ۶�", "���ۼ���", "���ҵ�ַ", "�Ƿ����", "��ע"};	
	public static Vector<Object[]> GoodsInfoListData = new Vector<Object[]>();
	
	
	/**
	 * �ͻ���Ϣ�б�����
	 */
	public static Object[] CustomerInfoListObj = 
			new Object[]{"ID", "���", "ѡ��", "ƴ��", "�ͻ�����", "��ϵ��", "�绰", "�ֻ���", "�ͻ����", "��ַ", "��վ", "����", "����", "�����˺�", "ʡ��", "����", "��ע"};
	public static Vector<Object[]> CustomerInfoListData = new Vector<Object[]>();
	
	/**
	 * ���۵��б�����
	 */
	public static Object[] SalesTicketInfoListObj = 
			new Object[]{"ID","XS_HAO","GOODS_ID", "���", "ѡ��", "ƴ��", "��Ʒ����", "���", "Ʒ��", "���", "��λ", "�ۼ�", "��������", "�ܽ��", "��ע"};
	public static Vector<Object[]> SalesTicketInfoListData = new Vector<Object[]>();
	
	
	
	
	/**
	 * ���۵�ѡ�����Ʒ��Ϣ����
	 */
	public static Object[] STI_GoodsListObj = 
			new Object[]{"ID", "���", "ѡ��", "ƴ��", "��Ʒ����", "���", "���", "Ʒ��", "��λ", "����", "���ۼ�һ", "���ۼ۶�", "���ۼ���", "���ҵ�ַ", "�Ƿ����", "��ע"};
	public static Vector<Object[]> STI_GoodsListData = new Vector<Object[]>();
	
	public static Object[] STI_SelectedGoodsObj = 
			new Object[]{"ID", "���", "ѡ��", "ƴ��", "��Ʒ����", "���", "���", "Ʒ��", "��λ", "����", "���ۼ�һ", "���ۼ۶�", "���ۼ���"};
	public static Vector<Object[]> STI_SelectedGoodsData = new Vector<Object[]>();
	
	
	/**
	 * ���۵��б�����
	 */
	public static Object[] SelectSalesTicketInfoListObj = 
			new Object[]{"���۵���","���", "ѡ��", "���۵���", "�ͻ�����", "����ʱ��", "���۽��", "��ϵ��", "��ϵ�绰"};
	public static Vector<Object[]> SelectSalesTicketInfoListData = new Vector<Object[]>();
	
	
	

}
