package org.whut.platform.fundamental.fileupload;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.whut.platform.fundamental.exception.BusinessException;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.io.*;
import java.util.Locale;

public class FileService {
	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(FileService.class);
	private static final int BUFFER_SIZE = 1024;
	private String allowedUpdateFileSuffix = "doc,pdf,jpg,docx,txt,xls,ppt,xlsx,pptx,zip,rar,png";

	public FileService() {
		super();
	}

	public FileService(String allowedUpdateFileSuffix) {
		super();
		if (allowedUpdateFileSuffix != null) {
			this.allowedUpdateFileSuffix = allowedUpdateFileSuffix;
		}
	}

	/**
	 * 获得根目录
	 */
	public String getRootPath(String rootPath) {
		return ObjectUtils.toString(rootPath, FileUtil.defaultPath());
	}

	/**
	 * 保存文件到默认位置 需指定 pathtype为简历或笔试答案的路径 -1为默认上传文件路径
	 */
	private String save(File src, String userFileName, String rootPathForPass) {
		if ((src == null) || !src.exists()) {
			return null;
		}

		String rootPath = rootPathForPass;
		if (rootPath == null) {
			rootPath = FileUtil.defaultPath();
		}

		File dstFile = getNewFile(userFileName, rootPath);
		LOGGER.debug("dst filename:" + dstFile.getAbsolutePath());

		try {
			InputStream in = null;
			OutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dstFile),
						BUFFER_SIZE);

				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;

				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} finally {
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}
		} catch (Exception e) {
			LOGGER.debug("Save File Error:{}" + e.getMessage());
			return null;
		}

		String dstFileName = dstFile.getAbsolutePath().replace(
				File.separatorChar, '/');

		return dstFileName
				.substring(dstFileName.indexOf(FileUtil.defaultPath())
						+ FileUtil.defaultPath().length());
	}

	public String getFileNameSuffix(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			return fileName.substring(fileName.lastIndexOf('.') + 1);
		}

		return "";
	}

	public String getFileNameMain(String fileName) {
		if (StringUtils.isNotBlank(fileName)) {
			return fileName.substring(0, fileName.lastIndexOf('.'));
		}

		return "";
	}

	/**
	 * 保存上传的临时文件到指定位置 文件名使用时间戳避免重复，扩展名不变
	 * 
	 * @param src
	 * @param userFileName
	 *            原文件名全名
	 * @return 返回新文件完整路径从webContent之后的部分，即userfiles/...
	 */
	public String save(File src, String userFileName) {
		return save(src, userFileName, FileUtil.defaultPath());
	}

	private void processCreateFailure() {
		throw new BusinessException("File Not Exists,But Create Failure",
				new IOException("File Not Exists,But Create Failure"));
	}

	/**
	 * 保存字符到指定绝对路径
	 * 
	 * @param inContent
	 * @param absolutePath
	 * @return
	 */
	public File saveStringToFile(String inContent, String absolutePath) {
		FileOutputStream fos = null;
		try {
			byte[] pom = Hexs.hexString2Bytes("EFBBBF");
			byte[] content = inContent.getBytes("UTF-8");
			File file = new File(absolutePath);

			if (!file.exists() && !file.createNewFile()) {
				this.processCreateFailure();
			}

			if (!file.canWrite()) {
				return null;
			}

			fos = new FileOutputStream(file.getAbsolutePath());
			fos.write(pom);
			fos.write(content);

			return file;
		} catch (Exception ex) {
			LOGGER.debug(ex.getLocalizedMessage());
			return null;
		} finally {
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * read file and set content to String alias to getFileContent
	 * 
	 * @param absolutePath
	 * @return
	 */
	public String read(String absolutePath) {
		return getFileContent(absolutePath);
	}

	public String getFileContent(String absolutePath) {
		File file = new File(absolutePath);

		if (!file.exists() || !file.canRead()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		InputStreamReader reader = null;

		try {
			reader = new InputStreamReader(new FileInputStream(absolutePath),
					"UTF-8");

			int tempchar;

			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，rn这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
				sb.append((char) tempchar);
			}
		} catch (Exception e) {
			LOGGER.error("Get File Content Error:{}" + e.getMessage());
		} finally {
			IOUtils.closeQuietly(reader);
		}

		return sb.toString();
	}

	public String getFileContent(File inFile) {
		return getFileContent(inFile.getAbsolutePath());
	}

	/**
	 * 获取新目标文件 文件按照月份分目录，文件名使用时间戳，扩展名不变
	 * @return 返回完整文件路径
	 */
	public File getNewFile(String fileName, String rootPathForPass) {
		String subdir = null;
		String rootPath = rootPathForPass;

		/**
		 * 避免单一目录文件过多,按月份日期分隔
		 */
		if (null == rootPath) {
			rootPath = FileUtil.defaultPath();
		}

		subdir = getTodayDirectory(rootPath);

		File file = new File(subdir);

		if (!file.exists() && !file.mkdirs()) {
			this.processCreateFailure();
		}

		String newFileName = "";
		newFileName = subdir + File.separator + System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf('.'));
		file = new File(newFileName);

		if (file.exists()) {
			file = getNewFile(fileName, rootPath);
		}

		return file;
	}

	/**
	 * 获取新目标文件 文件按照月份分目录，文件名使用时间戳，扩展名不变
	 * @return 返回完整文件路径
	 */
	public File getNewFileWithRelativePath(String fileName,
			String rootPathForPass, String relativePathForPass) {
		String subdir = null;
		String relativePath = relativePathForPass;
		if (relativePath == null) {
			throw new BusinessException("文件相对路径不能为空！", new Exception(
					"文件相对路径不能为空！"));
		}

		String rootPath = rootPathForPass;
		/**
		 * 避免单一目录文件过多,按月份日期分隔
		 */
		if (null == rootPath) {
			rootPath = FileUtil.defaultPath();
		}

		if (rootPath.endsWith("/")) {
			rootPath = rootPath.substring(0, rootPath.length() - 1);
		}

		if (relativePath.startsWith("/")) {
			relativePath = relativePath.substring(1);
		}

		rootPath = rootPath + "/" + relativePath;

		subdir = getTodayDirectory(rootPath);

		File file = new File(subdir);

		if (!file.exists() && !file.mkdirs()) {
			this.processCreateFailure();
		}

		String newFileName = "";
		newFileName = subdir + File.separator + System.currentTimeMillis()
				+ fileName.substring(fileName.lastIndexOf('.'));
		file = new File(newFileName);

		if (file.exists()) {
			file = getNewFile(fileName, rootPath);
		}

		return file;
	}

	/**
	 * 取得指定文件名的File对象
	 */
	@SuppressWarnings("unused")
	private File getNewFile(String fileNameSuffix, String fileNamePrefix,
			String rootPath) {
		if (StringUtils.isBlank(fileNameSuffix)
				|| StringUtils.isBlank(fileNamePrefix)) {
			return null;
		}

		String subdir = null;
		/**
		 * 避免单一目录文件过多,按月份日期分隔
		 */
		subdir = getTodayDirectory(rootPath);

		File file = new File(subdir);

		if (!file.exists() && !file.mkdirs()) {
			this.processCreateFailure();
		}

		StringBuilder fileName = new StringBuilder(subdir);
		fileName.append(File.separator).append(fileNamePrefix).append(".")
				.append(fileNameSuffix.toLowerCase(Locale.CHINA));
		file = new File(fileName.toString());

		if (file.exists()) {
			file = getNewFile(fileNameSuffix, fileNamePrefix, rootPath);
		}

		return file;
	}

	/**
	 * 根据当前日期，返回YYYY/MM/DD格式文本，用于创建文件存储路径
	 */
	public String getTodayDirectory(String rootPath) {
		return getFileSavetoDirectory(rootPath);
	}

	/**
	 * 获取昨天文件目录，用于凌晨处理昨天上传文件
	 * 
	 * @return
	 */
	public String getYesterdayDirectory(String rootPath) {
		DateTime dateTime = new DateTime().minusDays(NumberUtils.INTEGER_MINUS_ONE);
		return getFileSavetoDirectory(dateTime, rootPath);
	}

	private String getFileSavetoDirectory(DateTime dateTime, String rootPath) {
		return rootPath + File.separator + dateTime.toString("yyyy") + File.separator
				+ dateTime.toString("MM") + File.separator
				+ dateTime.toString("dd");
	}

	private String getFileSavetoDirectory(String rootPath) {
		DateTime dateTime = new DateTime();
		return this.getFileSavetoDirectory(dateTime, rootPath);

	}

	/**
	 * 判断此简历文件是否允许上传
	 */
	public boolean isMagazineUploadable(String fileName) {
		boolean uploadAble = false;
		String[] suffixes = allowedUpdateFileSuffix.split(",");

		for (String suffix : suffixes) {
			if (getFileNameSuffix(fileName).equalsIgnoreCase(suffix)) {
				uploadAble = true;
				break;
			}
		}
		return uploadAble;
	}

	public boolean removeFile(String filepath) {
		File file = new File(filepath);
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}

	public String saveProjectAttachment(File src, String userFileName) {
		String rootPath = FileUtil.defaultPath();

		if ((src == null) || !src.exists()) {
			return null;
		}

		File dstFile = getNewAttachment(userFileName, rootPath);
		LOGGER.debug("dst filename:" + dstFile.getAbsolutePath());

		try {
			InputStream in = null;
			OutputStream out = null;

			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dstFile),
						BUFFER_SIZE);

				byte[] buffer = new byte[BUFFER_SIZE];
				int len = 0;

				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} finally {
				IOUtils.closeQuietly(in);
				IOUtils.closeQuietly(out);
			}
		} catch (Exception e) {
			LOGGER.debug("Save project attachment error:{}" + e.getMessage());
			return null;
		}

		String dstFileName = dstFile.getAbsolutePath().replace('\\', '/');

		return dstFileName
				.substring(dstFileName.indexOf(FileUtil.defaultPath())
						+ FileUtil.defaultPath().length());
	}

	public File getNewAttachment(String fileName, String rootPath) {
		String subdir = null;
		/**
		 * 避免单一目录文件过多,按月份日期分隔
		 */
		subdir = getTodayDirectory(rootPath);
		subdir += (File.separator + System.currentTimeMillis());

		File file = new File(subdir);

		if (!file.exists() && !file.mkdirs()) {
			return null;
		}

		String newFileName = "";
		newFileName = subdir + File.separator + fileName;
		file = new File(newFileName);

		if (file.exists()) {
			file = getNewAttachment(fileName, rootPath);
		}

		return file;
	}
	
	private static final int PREFIX_END=30;
	private static final int NEW_PATH_PREFIX=13;
	private static final int OLD_PATH_PREFIX=25;

	/**
	 * 保存上传的文件，将其从temp目录转移，若以前有文件存在，删除并保存新的文件
	 */
	public String saveFileFromTemp(String tempPathForPass, String relativePath,
			String oldPathForPass) {
		String tempPath = tempPathForPass;
		String oldPath = oldPathForPass;
		String prefix = tempPath.substring(0, tempPath.lastIndexOf('.') - PREFIX_END);
		tempPath = FileUtil.defaultPath()
				+ tempPath.substring(tempPath.lastIndexOf('.') - PREFIX_END);
		File tempFile = new File(tempPath);
		String newPath = this.save(tempFile,
				tempPath.substring(tempPath.lastIndexOf('.') - NEW_PATH_PREFIX),
				FileUtil.defaultPath() + "/" + relativePath);
		if (tempFile.isFile() && tempFile.exists() && !tempFile.delete()) {
			throw new BusinessException(new IOException(
					"delete tempFile failure"));
		}
		if (oldPath != null) {
			oldPath = oldPath.substring(oldPath.lastIndexOf('.') - OLD_PATH_PREFIX);
			oldPath = FileUtil.defaultPath() + "/" + relativePath + oldPath;
			File oldFile = new File(oldPath);
			if (oldFile.isFile() && oldFile.exists() && !oldFile.delete()) {
				throw new BusinessException("Delete dest file failure",
						new IOException("delete file" + oldFile.getName()
								+ " failure!"));
			}
		}
		return prefix + newPath;
	}

}
