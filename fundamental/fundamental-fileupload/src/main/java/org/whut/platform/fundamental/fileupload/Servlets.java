package org.whut.platform.fundamental.fileupload;

import com.google.common.net.HttpHeaders;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Http与Servlet工具类.
 * 
 */
public final class Servlets {

	private Servlets() {
		throw new Error("Utility classes should not instantiated!");
	}

	// -- Content Type 定义 --//
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";
	public static final String EXCEL_2007_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml";
	public static final String HTML_TYPE = "text/html";
	public static final String JS_TYPE = "text/javascript";
	public static final String JSON_TYPE = "application/json";
	public static final String XML_TYPE = "text/xml";
	public static final String TEXT_TYPE = "text/plain";
	public static final String CSV_TYPE = "text/csv;charset=UTF-8";

	// -- Header 定义 --//
	public static final String AUTHENTICATION_HEADER = "Authorization";

	// -- 常用数值定义 --//
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;
	private static final int ONE_SECOND =1000;
	public static final String FIREFOX_ATTACHMENT_FILE_PREFIX = "=?UTF-8?B?";
	public static final String FIREFOX_ATTACHMENT_FILE_SUFFIX = "?=";

	/**
	 * 设置客户端缓存过期时间 的Header.
	 */
	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		// Http 1.0 header, set a fix expires date.
		response.setDateHeader(HttpHeaders.EXPIRES, System.currentTimeMillis()
				+ expiresSeconds * ONE_SECOND);
		// Http 1.1 header, set a time after now.
		response.setHeader(HttpHeaders.CACHE_CONTROL, "private, max-age="
				+ expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存的Header.
	 */
	public static void setNoCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader(HttpHeaders.EXPIRES, 1L);
		response.addHeader(HttpHeaders.PRAGMA, "no-cache");
		// Http 1.1 header
		response.setHeader(HttpHeaders.CACHE_CONTROL,
				"no-cache, no-store, max-age=0");
	}

	/**
	 * 设置LastModified Header.
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader(HttpHeaders.LAST_MODIFIED, lastModifiedDate);
	}

	/**
	 * 设置Etag Header.
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader(HttpHeaders.ETAG, etag);
	}
	

	/**
	 * 根据浏览器If-Modified-Since Header, 计算文件是否已被修改.
	 * 
	 * 如果无修改, checkIfModify返回false ,设置304 not modify status.
	 * 
	 * @param lastModified
	 *            内容的最后修改时间.
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request
				.getDateHeader(HttpHeaders.IF_MODIFIED_SINCE);
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + ONE_SECOND)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效.
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param etag
	 *            内容的ETag.
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader(HttpHeaders.IF_NONE_MATCH);
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader(HttpHeaders.ETAG, etag);
				return false;
			}
		}
		return true;
	}

	public static void setFileDownloadHeader(HttpServletResponse response,
			String agent, String fileNameSrc, String fileNameCharset) {
		try {
			String fileName = fileNameSrc;
			// FF是个奇葩,需要特殊处理.
			if (agent != null && agent.indexOf("MSIE") == -1)// FF
			{
				fileName = new StringBuilder()
						.append(FIREFOX_ATTACHMENT_FILE_PREFIX)
						.append((new String(Base64.encodeBase64(fileNameSrc
								.getBytes(fileNameCharset)))))
						.append(FIREFOX_ATTACHMENT_FILE_SUFFIX).toString();
			} else// 非FF其他浏览器
			{
				fileName = URLEncoder.encode(fileNameSrc, fileNameCharset);
				if (fileName.length() > 150)// 解决IE 6.0 bug
				{
					fileName = new String(
							fileNameSrc.getBytes(fileNameCharset), "ISO-8859-1");
				}

			}
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
					"attachment;filename=" + fileName);
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters, copy from spring WebUtils.
	 * 
	 * 返回的结果的Parameter名已去除前缀.
	 */
	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefixForPass) {
		Validate.notNull(request, "Request must not be null");
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		String prefix = StringUtils.defaultString(prefixForPass,
				StringUtils.EMPTY);
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values != null && values.length > 0) {
					if (values.length > 1) {
						params.put(unprefixed, values);
					} else {
						params.put(unprefixed, values[0]);
					}
				}
			}
		}
		return params;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.如果是有中文文件名,此方法将导致中文文件名无法显示.请使用同名方法:
	 * {@link #setFileDownloadHeader(HttpServletRequest, HttpServletResponse, String)}
	 * <br/>
	 * 如果fileName中包含空格。空格将被_替换。
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	@Deprecated
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		setFileDownloadHeader(null, response, fileName);
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header.本方法可以解决下载文件中文文件名的乱码问题。 如果fileName中包含空格。空格将被_替换。
	 * 
	 * @param fileName
	 *            下载后的文件名.
	 */
	public static void setFileDownloadHeader(HttpServletRequest request,
			HttpServletResponse response, String fileName) {
		String agent = null;
		if (request != null) {
			agent = (String) request.getHeader(HttpHeaders.USER_AGENT);
		}
		fileName = StringUtils.replace(fileName, " ", "_");
		setFileDownloadHeader(response, agent, fileName, "UTF-8");
	}
}
