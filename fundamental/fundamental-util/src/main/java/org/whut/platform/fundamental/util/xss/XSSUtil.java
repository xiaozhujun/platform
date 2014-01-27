package org.whut.platform.fundamental.util.xss;

public final class XSSUtil {

	private XSSUtil() {
		throw new Error("Utility classes should not instantiated!");
	}

	/**
	 * 做XSS字符串过滤
	 * 
	 * @param xssString
	 * @return
	 */
	public static String htmlEncode(String xssString) {
		if (xssString != null && !xssString.equals("")) {
			xssString = xssString.replaceAll("&", "&amp;")
					.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
					.replaceAll("'", "&#39;").replaceAll("\"", "&quot;");
		}
		return xssString;
	}
}
