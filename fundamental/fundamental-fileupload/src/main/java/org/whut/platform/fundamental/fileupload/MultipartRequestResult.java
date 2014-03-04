
package org.whut.platform.fundamental.fileupload;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;


/**
 * author Lingo
 */
public class MultipartRequestResult {
	private Map<String, String> params = Maps.newHashMap();
	private List<FileInfo> fileInfos = Lists.newArrayList();

	public Map<String, String> getParams() {
		return params;
	}

	public List<FileInfo> getFileInfos() {
		return fileInfos;
	}
}
