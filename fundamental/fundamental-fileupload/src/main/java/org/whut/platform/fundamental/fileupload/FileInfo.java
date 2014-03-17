package org.whut.platform.fundamental.fileupload;

import java.io.InputStream;

public class FileInfo {
	
	private String name;
	
	private String path;

    private InputStream inputStream;

	public FileInfo() {

	}

	public FileInfo(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
