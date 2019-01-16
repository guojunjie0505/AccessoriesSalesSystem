package kehaofei.com.utils.enumeration;

/**
 * 
 * @author XCCD
 * <li>TODO	��Ʒ����ѡ��ö���б�
 * <li>2017-1-13 ����1:53:10
 * <li>
 */
public enum GoodsTypeEnum{
	
	type1(0x01,  "��"),type2(0x02, "�綯"),type3(0x03, "��"),
	type4(0x04, "��"),type5(0x05, "��"),type6(0x06, "����"),
	type7(0x07, "��ͷ"),type8(0x08, "����"),type9(0x09, "���"),
	type10(0x0A, "��ǹ"),type11(0x0B, "��ͷ"),type12(0x0C, "Ƥ��"),
	type13(0x0D, "��"),type14(0x0E, "����");

	
	public Integer key;
	public String value;
	//���췽��
	private GoodsTypeEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	// ��ͨ����  
    public static String getValue(int key) {  
        for (GoodsTypeEnum rt : GoodsTypeEnum.values()) {  
            if (rt.getKey() == key) {  
                return rt.value;  
            }  
        }  
        return null;  
    }
    //��ȡkeyֵ
    public static Integer getKey(String value) {  
        for (GoodsTypeEnum rt : GoodsTypeEnum.values()) {  
            if (rt.getValue().equals(value)) {  
                return rt.key;  
            }  
        }  
        return null;  
    } 
    
	/**
	 * @return the key
	 */
	public Integer getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(Integer key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}		
	
}
