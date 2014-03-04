package org.whut.platform.fundamental.jxl.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.io.File;
import java.io.IOException;

/**
 * 在本机上创建临时存放excel表格的目录
 * 
 * @author v_liuling
 * 
 */
public final  class FileCreator {

	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(FileCreator.class);
	
	private FileCreator(){
		throw new Error("Utility classes should not instantiated!");
	}

	/**
	 * 创建单个文件
	 * 
	 * @param destFileName
	 *            文件名
	 * @return 创建成功返回true，否则返回false
	 */
	public static boolean createFile(String destFileName) {
		File file = new File(destFileName);
		if (file.exists()) {
			LOGGER.debug("创建单个文件 {} 失败，目标文件已存在！", destFileName);
			return false;
		}
		if (StringUtils.endsWithIgnoreCase(destFileName, File.separator)) {
			LOGGER.debug("创建单个文件 {} 失败，目标不能是目录！", destFileName);
			return false;
		}
		if (!file.getParentFile().exists()) {
			LOGGER.debug("目标文件所在路径不存在，准备创建。。。");
			if (!file.getParentFile().mkdirs()) {
				LOGGER.debug("创建目录文件所在的目录失败！");
				return false;
			}
		}

		// 创建目标文件
		try {
			if (file.createNewFile()) {
				LOGGER.debug("创建单个文件 {} 成功！", destFileName);
				return true;
			} else {
				LOGGER.debug("创建单个文件 {} 失败！", destFileName);
				return false;
			}
		} catch (IOException e) {
			LOGGER.debug("创建单个文件 {} 失败:{}", destFileName, e);
			return false;
		}
	}

	/**
	 * 创建目录
	 * 
	 * @param destDirName
	 *            目标目录名
	 * @return 目录创建成功返回true，否则返回false
	 */
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		return dir.mkdirs();
	}

	/**
	 * 创建临时文件
	 * 
	 * @param prefix
	 *            临时文件的前缀
	 * @param suffix
	 *            临时文件的后缀
	 * @param dirName
	 *            临时文件所在的目录，如果输入null，则在用户的文档目录下创建临时文件
	 * @return 临时文件创建成功返回抽象路径名的规范路径名字符串，否则返回null
	 */
	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		try {
			if (StringUtils.isBlank(dirName)) {
				// 在默认文件夹下创建临时文件
				return File.createTempFile(prefix, suffix).getCanonicalPath();
			} else {
				File dir = FileUtils.getFile(dirName);
				// 如果临时文件所在目录不存在，首先创建
				if (!dir.exists() && !FileCreator.createDir(dirName)) {
					LOGGER.debug("创建临时文件失败，不能创建临时文件所在目录！");
					return null;
				}
				return File.createTempFile(prefix, suffix, dir)
						.getCanonicalPath();
			}
		} catch (IOException e) {
			LOGGER.debug("create temp file failure:{}", e);
			return null;
		}
	}
}
