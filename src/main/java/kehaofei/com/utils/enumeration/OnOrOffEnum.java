package kehaofei.com.utils.enumeration;

/**
 * 
 * @author XCCD
 * <li>TODO	��������״̬
 * <li>2017-1-13 ����1:53:10
 * <li>
 */
public enum OnOrOffEnum{
	
	onOff1(0x01,  "��"),onOff2(0x02, "��");

	
	public Integer key;
	public String value;
	//���췽��
	private OnOrOffEnum(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
	
	// ��ͨ����  
    public static String getValue(int key) {  
        for (OnOrOffEnum rt : OnOrOffEnum.values()) {  
            if (rt.getKey() == key) {  
                return rt.value;  
            }  
        }  
        return null;  
    }
    //��ȡkeyֵ
    public static Integer getKey(String value) {  
        for (OnOrOffEnum rt : OnOrOffEnum.values()) {  
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
