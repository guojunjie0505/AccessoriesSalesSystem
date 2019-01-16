package kehaofei.com.utils;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * @author XCCD
 * <li>TODO	�����ַ�ת������ʽ��ƴ��
 * <li>2017-7-20 ����5:40:56
 * <li>
 */
public class Pinyin4jUtil {

	/**
	 * ��ȡ�׸��ַ�������ĸ����������*
	 * 
	 * @param str
	 * @return String
	 */
	public static String getFirstPinYinHeadChar(String str) {

		StringBuffer pybf = new StringBuffer();
		char word = str.charAt(0);
		// ��ȡ���ֵ�����ĸ
		String[] pinyinArray;
		// �Ƿ�����Ϊ��ĸ
		if (word > 128) {
			pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			// ������Ǻ��֣��ͷ���ԭʼ����
			if (pinyinArray != null) {
				pybf.append(pinyinArray[0].charAt(0));
			} else {
				pybf.append(pinyinArray[0].toLowerCase());
			}
		} else {

			// ����ĸֱ�ӷ��أ����Ƿ���*
			if (Character.isLetter(word)) {
				pybf.append(word);
			} else {
				pybf.append("*");
			}
		}

		// ȫ�����ش�д
		return pybf.toString().toUpperCase();
	}

	/**
	 * ������ת��Ϊȫƴ
	 * 
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		// System.out.println(t1.length);
		String[] t2 = new String[t1.length];
		// System.out.println(t2.length);
		// ���ú���ƴ������ĸ�ʽ
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// �ж��Ƿ�Ϊ�����ַ�
				// System.out.println(t1[i]);
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// �����ֵļ���ȫƴ���浽t2������
					t4 += t2[0];// ȡ���ú���ȫƴ�ĵ�һ�ֶ��������ӵ��ַ���t4��
				} else {
					// ������Ǻ����ַ���ֱ��ȡ���ַ������ӵ��ַ���t4��
					t4 += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * ��ȡÿ�����ֵ�����ĸ
	 * 
	 * @param str
	 * @return String
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// ��ȡ���ֵ�����ĸ
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * ���ַ���ת����ASCII��
	 * 
	 * @param cnStr
	 * @return String
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		// ���ַ���ת�����ֽ�����
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// System.out.println(Integer.toHexString(bGBK[i] & 0xff));
			// ��ÿ���ַ�ת����ASCII��
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff) + " ");
		}
		return strBuf.toString();
	}

	/**
	 * ����ת��λ����ƴ������ĸ��Ӣ���ַ����䣬�����ַ���ʧ ֧�ֶ����֣����ɷ�ʽ�磨��ɳ�г�:cssc,zssz,zssc,cssz��
	 * 
	 * @param chines
	 *            ����
	 * @return ƴ��
	 */
	public static String converterToFirstSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// ȡ�õ�ǰ���ֵ�����ȫƴ
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							// ȡ����ĸ
							pinyinName.append(strs[j].charAt(0));
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
					// else {
					// pinyinName.append(nameChar[i]);
					// }
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		// return pinyinName.toString();
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/**
	 * ����ת��λ����ȫƴ��Ӣ���ַ����䣬�����ַ���ʧ
	 * ֧�ֶ����֣����ɷ�ʽ�磨�ص���:zhongdangcen,zhongdangcan,chongdangcen
	 * ,chongdangshen,zhongdangshen,chongdangcan��
	 * 
	 * @param chines
	 *            ����
	 * @return ƴ��
	 */
	public static String converterToSpell(String chines) {
		StringBuffer pinyinName = new StringBuffer();
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					// ȡ�õ�ǰ���ֵ�����ȫƴ
					String[] strs = PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat);
					if (strs != null) {
						for (int j = 0; j < strs.length; j++) {
							pinyinName.append(strs[j]);
							if (j != strs.length - 1) {
								pinyinName.append(",");
							}
						}
					}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName.append(nameChar[i]);
			}
			pinyinName.append(" ");
		}
		// return pinyinName.toString();
		return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
	}

	/**
	 * ȥ���������ظ�����
	 * 
	 * @param theStr
	 * @return
	 */
	private static List<Map<String, Integer>> discountTheChinese(String theStr) {
		// ȥ���ظ�ƴ�����ƴ���б�
		List<Map<String, Integer>> mapList = new ArrayList<Map<String, Integer>>();
		// ���ڴ���ÿ���ֵĶ����֣�ȥ���ظ�
		Map<String, Integer> onlyOne = null;
		String[] firsts = theStr.split(" ");
		// ����ÿ�����ֵ�ƴ��
		for (String str : firsts) {
			onlyOne = new Hashtable<String, Integer>();
			String[] china = str.split(",");
			// �����ִ���
			for (String s : china) {
				Integer count = onlyOne.get(s);
				if (count == null) {
					onlyOne.put(s, new Integer(1));
				} else {
					onlyOne.remove(s);
					count++;
					onlyOne.put(s, count);
				}
			}
			mapList.add(onlyOne);
		}
		return mapList;
	}

	/**
	 * ���������ƴ��������ϲ�����(�Ƽ�ʹ��)
	 * 
	 * @return
	 */
	private static String parseTheChineseByObject(
			List<Map<String, Integer>> list) {
		Map<String, Integer> first = null; // ����ͳ��ÿһ��,�����������
		// ����ÿһ�鼯��
		for (int i = 0; i < list.size(); i++) {
			// ÿһ�鼯������һ����ϵ�Map
			Map<String, Integer> temp = new Hashtable<String, Integer>();
			// ��һ��ѭ����firstΪ��
			if (first != null) {
				// ȡ���ϴ������˴μ��ϵ��ַ���������
				for (String s : first.keySet()) {
					for (String s1 : list.get(i).keySet()) {
						String str = s + s1;
						temp.put(str, 1);
					}
				}
				// ������һ���������
				if (temp != null && temp.size() > 0) {
					first.clear();
				}
			} else {
				for (String s : list.get(i).keySet()) {
					String str = s;
					temp.put(str, 1);
				}
			}
			// ������������Ա��´�ѭ��ʹ��
			if (temp != null && temp.size() > 0) {
				first = temp;
			}
		}
		String returnStr = "";
		if (first != null) {
			// ����ȡ������ַ���
			for (String str : first.keySet()) {
				returnStr += (str + ",");
			}
		}
		if (returnStr.length() > 0) {
			returnStr = returnStr.substring(0, returnStr.length() - 1);
		}
		return returnStr;
	}

}
