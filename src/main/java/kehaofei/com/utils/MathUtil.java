package kehaofei.com.utils;


import java.math.BigDecimal;

public class MathUtil {

	/**
	 * �ṩ��ȷ�ļӷ����㡣
	 *
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������ĺ�
	 */

	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	public static BigDecimal add_BigDecimal(Object v1, Object v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(0.00));
		BigDecimal b2 = new BigDecimal(Double.toString(0.00));
		if(v1 instanceof BigDecimal){
			b1 = (BigDecimal)v1;
		}
		if(v1 instanceof Double){
			b1 = new BigDecimal(Double.toString((Double)v1));
		}
		if(v1 instanceof Integer){
			b1 = new BigDecimal(Integer.toString((Integer)v1));
		}
		if(v1 instanceof String){
			b1 = new BigDecimal(String.valueOf(v1));
		}
		
		if(v2 instanceof BigDecimal){
			b2 = (BigDecimal)v2;
		}
		if(v2 instanceof Double){
			b2 = new BigDecimal(Double.toString((Double)v2));
		}
		if(v2 instanceof Integer){
			b2 = new BigDecimal(Integer.toString((Integer)v2));
		}
		if(v2 instanceof String){
			b2 = new BigDecimal(String.valueOf(v2));
		}
		
		return b1.add(b2);
	}

	/**
	 * �ṩ��ȷ�ļ������㡣
	 *
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������Ĳ�
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	public static BigDecimal sub_BigDecimal(Object v1, Object v2) {
		BigDecimal b1 = null;
		BigDecimal b2 = null;
		try {
			if(v1 instanceof BigDecimal){
				b1 = (BigDecimal)v1;
			}
			if(v1 instanceof Double){
				b1 = new BigDecimal(Double.toString((Double)v1));
			}
			if(v1 instanceof Integer){
				b1 = new BigDecimal(Integer.toString((Integer)v1));
			}
			if(v1 instanceof String ){
				b1 = new BigDecimal((String.valueOf(v1)).trim().equals("")?"0":(String.valueOf(v1)).trim());
			}
			
			if(v2 instanceof BigDecimal){
				b2 = (BigDecimal)v2;
			}
			if(v2 instanceof Double){
				b2 = new BigDecimal(Double.toString((Double)v2));
			}
			if(v2 instanceof Integer){
				b2 = new BigDecimal(Integer.toString((Integer)v2));
			}
			if(v2 instanceof String){
				b2 = new BigDecimal((String.valueOf(v2)).trim().equals("")?"0":(String.valueOf(v2)).trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		return b1.subtract(b2);
	}

	/**
	 * �ṩ��ȷ�ĳ˷����㡣
	 *
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������Ļ�
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	public static float mul_float(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).floatValue();
	}
	
	public static BigDecimal mul_BigDecimal(Object v1, Object v2) {
		BigDecimal b1 = null;
		BigDecimal b2 = null;
		try {
			if(v1 instanceof BigDecimal){
				b1 = (BigDecimal)v1;
			}
			if(v1 instanceof Double){
				b1 = new BigDecimal(Double.toString((Double)v1));
			}
			if(v1 instanceof Integer){
				b1 = new BigDecimal(Integer.toString((Integer)v1));
			}
			if(v1 instanceof String ){
				b1 = new BigDecimal(((String)v1).trim().equals("")?"0":((String)v1).trim());
			}
			
			if(v2 instanceof BigDecimal){
				b2 = (BigDecimal)v2;
			}
			if(v2 instanceof Double){
				b2 = new BigDecimal(Double.toString((Double)v2));
			}
			if(v2 instanceof Integer){
				b2 = new BigDecimal(Integer.toString((Integer)v2));
			}
			if(v2 instanceof String){
				b2 = new BigDecimal(((String)v2).trim().equals("")?"0":((String)v2).trim());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}		
		
		return b1.multiply(b2);
	}


	public static int mul_int(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).intValue();
	}

	/**
	 * �ṩ��ȷ�ĳ������㡣
	 *
	 * @param v1
	 *            ������
	 * @param v2
	 *            ����
	 * @return ���������ĳ������
	 */
	public static double div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2).doubleValue();
	}


	/**
	 * �������㣨ÿ���ֽڼ���0x33��
	 * @author XCCD
	 * @param bt
	 * @return
	 */
	public static byte[] DataSum33(byte[] bt){
		byte[] returnBt = new byte[bt.length];
		for(int i=0; i<bt.length; i++){
			returnBt[i] = (byte) ((bt[i]+0x33) & 0xff);
		}
		return returnBt;
	}

	/**
	 * �����������㻹ԭ��ÿ���ֽڼ�ȥ0x33��
	 * @author XCCD
	 * @param bt
	 * @return
	 */
	public static byte[] DataSub33(byte[] bt){
		byte[] returnBt = new byte[bt.length];
		for(int i=0; i<bt.length; i++){
			returnBt[i] = (byte) ((bt[i]-0x33) & 0xff);
		}
		return returnBt;
	}

	/**
	 * @author XCCD
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(div(123456,1243));
	}

}
