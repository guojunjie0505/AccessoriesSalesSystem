package kehaofei.com.utils.enumeration;

/**
 * 
 * @author XCCD
 * <li>TODO	��Ʒ������λѡ��ö���б�
 * <li>2017-1-13 ����1:53:10
 * <li>
 */
public enum GoodsUnitEnum{
	
	unit1(0x01,  "��"),unit2(0x02, "��"),unit3(0x03, "��"),
	unit4(0x04, "��"),unit5(0x05, "̨"),unit6(0x06, "��"),
	unit7(0x07, "��"),unit8(0x08, "��"),unit9(0x09, "��");

	
	public Integer key;
	public String value;
	//���췽��
	private GoodsUnitEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	// ��ͨ����  
    public static String getValue(int key) {  
        for (GoodsUnitEnum rt : GoodsUnitEnum.values()) {  
            if (rt.getKey() == key) {  
                return rt.value;  
            }  
        }  
        return null;  
    }
    //��ȡkeyֵ
    public static Integer getKey(String value) {  
        for (GoodsUnitEnum rt : GoodsUnitEnum.values()) {  
            if (rt.getValue().equals(value)) {  
                return rt.key;  
            }  
        }  
        return null;  
    } 
    
	/**
	 * @return the key
	 */
	private Integer getKey() {
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
	private String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}		
	
}
