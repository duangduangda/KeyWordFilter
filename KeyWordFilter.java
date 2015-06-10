
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description �����֣��ؼ��֣�����
 * @author duangduangda
 * @since 2014��12��30��
 */
public class KeyWordFilter {
	private static Pattern pattern = null;

	/**
	 * 
	 * @Description: ��ʽ������ʿ�
	 * @author duangduangda
	 * @since 2014��12��30��
	 */
	private static void initPattern() {
		StringBuffer patternBuf = new StringBuffer();
		InputStream in = null;
		BufferedReader bf = null;
		String filepath = "/util/keywords.properties";
		try {
			in = KeyWordFilter.class.getResourceAsStream(filepath);
			bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			Properties properties = new Properties();
			properties.load(in);
			Enumeration<?> enu = properties.propertyNames();
			while (enu.hasMoreElements()) {
				patternBuf.append((String) enu.nextElement()).append("|"); 
			}
			patternBuf.deleteCharAt(patternBuf.length() - 1);
			pattern = Pattern.compile(new String(patternBuf.toString()));
			if (null != in) {
				in.close();
			}
			if (null != bf) {
				bf.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Description: �Թؼ��ֻ����дʽ��й���
	 * @author duangduangda
	 * @since 2014��12��30��
	 * @param str
	 * @return
	 */
	public static String doFilter(String str) {
		try {
			if (StringUtils.isBlank(str)) {
				return str;
			}
			initPattern();
			if (null != pattern) {
				Matcher m = pattern.matcher(str);
				str = m.replaceAll("**");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 
	 * @Description: ���дʻ��߹ؼ����Ƿ��ڴʿ���
	 * @author duangduangda
	 * @since 2014��12��30��
	 * @param str
	 * @return
	 */
	public static boolean hasKeywords(String str) {
		try {
			initPattern();
			if (null != pattern) {
				Matcher m = pattern.matcher(str);
				return m.find();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
