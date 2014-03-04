package org.whut.platform.fundamental.jxl.model;

import java.util.List;

/**
 * 
 * Excel表格类
 * 
 * @author v_liuling
 * 
 */
public class ExcelMap {

	/**
	 * 按顺序，存放Excel表格标题行
	 */
	private List<String> heads;

	/**
	 * 存放Excel表格内容，多行
	 */
	private List<List<String>> contents;

	public List<String> getHeads() {
		return this.heads;
	}

	public void setHeads(List<String> heads) {
		this.heads = heads;
	}

	public List<List<String>> getContents() {
		return this.contents;
	}

	public void setContents(List<List<String>> contents) {
		this.contents = contents;
	}
}
