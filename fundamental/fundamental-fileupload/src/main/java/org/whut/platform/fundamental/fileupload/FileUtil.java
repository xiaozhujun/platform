package org.whut.platform.fundamental.fileupload;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.whut.platform.fundamental.config.Constants;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.exception.BusinessException;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public final class FileUtil {
	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(FileUtil.class);
	private static BufferedReader reader;

	private static final String FILE_PREFIX = "File ";

	/**
	 * 增加对文件下载路径中特殊字符的过滤
	 * 
	 * @param path
	 * @return
	 */
	public static String filterFilePath(String path) {
		if (StringUtils.isNotBlank(path)) {
			return path.replaceAll("\\.\\./", "");
		}
		return path;
	}

	private FileUtil() {
		throw new Error("Utility classes should not instantiated!");
	}

	public static void zipCompressOutput(OutputStream o, String[] files)
			throws IOException {
		FileInputStream fi = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		ZipOutputStream zos = null;
		try {
			if (o != null) {
				String baseDir = defaultPath();
				zos = new ZipOutputStream(o);
				out = new BufferedOutputStream(zos);
				for (String file : files) {
					File f = new File(baseDir + file);
					if (f.isFile() && f.exists()) {
						fi = new FileInputStream(f);
						in = new BufferedInputStream(fi);
						zos.putNextEntry(new ZipEntry(file));
						int c;
						while ((c = in.read()) != -1) {
							out.write(c);
						}
						out.flush();
						zos.closeEntry();
					}
				}
				out.flush();
			}
		} finally {
			IOUtils.closeQuietly(fi);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(zos);

		}

	}

	/**
	 * 格式化重复文件名 a.txt a.txt to a.txt a(1).txt
	 * 
	 * @date 2012-3-27下午10:15:34
	 * @param list
	 * @return
	 */
	public static List<String> formatDuplicate(List<String> list) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		return formatDuplicateListNotEmpty(list);
	}

	private static List<String> formatDuplicateListNotEmpty(List<String> list) {
		List<String> resultList = Lists.newArrayList();
		Map<String, Integer> nameCountMap = Maps.newHashMap();
		for (String fileName : list) {
			Integer fileNameCount = nameCountMap.get(fileName);
			modifyFileName(fileName, fileNameCount, resultList, nameCountMap);
		}
		return resultList;
	}

	private static void modifyFileName(String fileName, Integer fileNameCount,
			List<String> resultList, Map<String, Integer> nameCountMap) {
		if (fileNameCount != null) {
			resultList.add(modifyDuplicateFileName(fileName, fileNameCount));
			nameCountMap.put(fileName, fileNameCount + 1);
		} else {
			resultList.add(fileName);
			nameCountMap.put(fileName, NumberUtils.INTEGER_ONE);
		}
	}

	private static String modifyDuplicateFileName(String oldName, Integer count) {
		return new StringBuilder(StringUtils.substringBeforeLast(oldName, "."))
				.append("(").append(count).append(").")
				.append(StringUtils.substringAfterLast(oldName, "."))
				.toString();
	}

	/**
	 * 根据文件完全路径名list和输出流，将文件集压缩
	 * 
	 * @date 2011-7-11下午04:36:41
	 * @param o
	 * @param files
	 * @throws Exception
	 */
	public static void zipCompressOutputByList(OutputStream o,
			List<String> files, List<String> fileNameList) throws Exception {
		ZipArchiveOutputStream zos = null;
		BufferedOutputStream out = null;
		FileInputStream fi = null;
		BufferedInputStream in = null;
		List<String> fileNames = fileNameList;
		try {
			if (o != null) {
				fileNames = formatDuplicate(fileNames);
				zos = new ZipArchiveOutputStream(o);
				zos.setEncoding("gbk");
				out = new BufferedOutputStream(zos);
				for (int i = 0; i < files.size(); i++) {
					String file = files.get(i);
					String fileName;
					if (i < fileNames.size()) {
						fileName = fileNames.get(i);
					} else {
						fileName = getFileFullName(file);
					}
					File f = new File(file);
					if (f.isFile() && f.exists()) {
						fi = new FileInputStream(f);
						in = new BufferedInputStream(fi);
						zos.putArchiveEntry(new ZipArchiveEntry(fileName));
						int c;
						while ((c = in.read()) != -1) {
							out.write(c);
						}
						out.flush();
						zos.closeArchiveEntry();
					}
				}
				out.flush();
			}
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(fi);
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(zos);
		}

	}

	public static void serializeObjectToFile(String filePath, Object object) {
		File file = null;
		FileOutputStream f = null;
		ObjectOutputStream s = null;
		try {
			file = new File(filePath);
			if (!file.getParentFile().exists()
					&& !file.getParentFile().mkdirs()) {
				processCreateFailure();
			}
			f = new FileOutputStream(filePath);
			s = new ObjectOutputStream(f);
			s.writeObject(object);
			s.flush();
		} catch (IOException ioe) {
			LOGGER.error("", ioe);
		} finally {
			IOUtils.closeQuietly(s);
			IOUtils.closeQuietly(f);
		}
	}

	public static Object serializeObjectFromFile(String filePath) {
		FileInputStream f = null;
		ObjectInputStream in = null;

		File file = new File(filePath);
		try {
			if (file.exists()) {
				f = new FileInputStream(filePath);
				in = new ObjectInputStream(f);
				return in.readObject();
			}
			return null;
		} catch (Exception ex) {
			LOGGER.error("{}", ex);
			FileUtils.deleteQuietly(file);
			return null;
		} finally {
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(f);
		}
	}

	public static void closeInputStream(InputStream inputStream) {
		IOUtils.closeQuietly(inputStream);
	}

	/**
	 * 获取主文件名
	 * 
	 * @param inFileName
	 * @return
	 */
	public static String getFileNameMain(String inFileName) {
		int lastIndex = inFileName.lastIndexOf('.');
		if (lastIndex > 0) {
			return inFileName.substring(0, lastIndex);
		} else {
			return inFileName;
		}
	}

	/**
	 * 获取主文件名
	 * 
	 * @param inFileName
	 * @return
	 */
	public static String getMainFileName(String inFileName) {
		return getFileNameMain(inFileName);
	}

	/**
	 * 取扩展名
	 * 
	 * @param inFileName
	 * @return
	 */
	public static String getFileNameSuffix(String inFileName) {
		String fileName = inFileName;
		int lastIndex = fileName.lastIndexOf('.');
		if (lastIndex > 0) {
			return fileName.substring(lastIndex + 1);
		} else {
			return "";
		}
	}

	/**
	 * 获取文件名，文件名地址截取，防止ie浏览器附带url
	 */
	public static String getFileFullName(String fileNameInputForPass) {
		String fileNameInput = fileNameInputForPass.replace("\\", "/");
		String fileNameOutput = fileNameInput.substring(
				fileNameInput.lastIndexOf('/') + 1, fileNameInput.length());

		return fileNameOutput;
	}

	/**
	 * 把指定文本写入文件
	 * 
	 * @param fileName
	 * @throws java.io.IOException
	 */
	public static void writeToFile(String outputContentForPass,
			String fileName, String charsetName) throws IOException {
		String outputContent = StringUtils.defaultString(outputContentForPass,
				StringUtils.EMPTY);
		File outputFile = new File(fileName);
		if (outputFile != null
				&& (outputFile.exists() || outputFile.createNewFile())) {

			FileUtil.writeToFile(outputContent, outputFile, charsetName);
		} else {
			throw new IOException(FILE_PREFIX + fileName
					+ " not exist or can not be create.");
		}
	}

	public static void writeToFile(String outputContent, File inFile,
			String charsetName) throws IOException {
		FileOutputStream fos = null;
		Writer writer = null;
		try {
			fos = new FileOutputStream(inFile);
			writer = new OutputStreamWriter(fos, charsetName);
			writer.write(outputContent);
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * 检查文件或目录可用性
	 * 
	 * @param fileName
	 *            文件或目录
	 * @param write
	 *            是否检查可写，否则仅检查可读
	 * @param makeDirIfNotExist
	 *            如果目录不存在是否建立
	 * @throws java.io.FileNotFoundException
	 * @throws java.io.IOException
	 */
	public static void checkFile(String fileName, boolean write,
			boolean makeDirIfNotExist) throws IOException {
		FileUtil.checkFile(new File(fileName), write, makeDirIfNotExist);
	}

	private static void processCreateFailure() {
		throw new BusinessException("File Not Exists,But Create Failure",
				new IOException("File Not Exists,But Create Failure"));
	}

	/**
	 * 检查文件或目录可用性
	 * 
	 * @param file
	 *            文件或目录
	 * @param write
	 *            是否检查可写，否则仅检查可读
	 * @param makeDirIfNotExist
	 *            如果目录不存在是否建立
	 * @throws java.io.FileNotFoundException
	 *             ,IOException
	 */
	public static void checkFile(File file, boolean write,
			boolean makeDirIfNotExist) throws IOException {

		if (file == null) {
			throw new FileNotFoundException("File not exist.");
		}

		// TODO 这个感觉有问题啊
		if (!file.exists()) {
			if (makeDirIfNotExist && !file.mkdirs()) {
				// makeDirs
				processCreateFailure();
			} else {
				throw new FileNotFoundException(FILE_PREFIX + file.getPath()
						+ " not exist.");
			}
		}
		if (write) {
			if (!file.canWrite()) {
				throw new IOException(FILE_PREFIX + file.getPath()
						+ " can not write.");
			}
		} else {
			if (!file.canRead()) {
				throw new IOException(FILE_PREFIX + file.getPath()
						+ " can not read.");
			}
		}
	}

	/**
	 * 替换文件扩展名。 如文件无扩展名则添加新扩展名
	 * 
	 * @param newSuffixName
	 * @return
	 */
	public static String replaceSuffixName(String fileNameForPass,
			String newSuffixName) {
		String fileName = fileNameForPass;
		if (StringUtils.isBlank(fileName)) {
			return StringUtils.EMPTY;
		}
		fileName = StringUtils.trim(fileName);
		int pos = fileName.indexOf('.');
		if (pos < 0) {
			return fileName + "." + newSuffixName;
		} else {
			return FileUtil.getMainFileName(fileName) + "." + newSuffixName;
		}

	}

	public static String readTxtFile(String fileName, String charsetForPass) {
		File inputFile = new File(fileName);
		if (!inputFile.exists() || !inputFile.canRead()) {
			return null;
		}
		String charset = StringUtils.defaultString(charsetForPass, "ISO8859-1");
		InputStreamReader read = null;
		try {
			read = new InputStreamReader(new FileInputStream(inputFile),
					charset);
		} catch (Exception e1) {
			LOGGER.error("", e1);
		}
		reader = new BufferedReader(read);

		StringBuilder sb = new StringBuilder("");
		try {
			String tmp = null;
			while ((tmp = reader.readLine()) != null) {
				sb.append(tmp);
				sb.append("\n\r");
			}
		} catch (IOException e) {
			LOGGER.error("", e);
		}
		return sb.toString();
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFileByName(String sPath) {
		return deleteFile(new File(sPath));
	}

	public static boolean deleteFile(File file) {
		return FileUtils.deleteQuietly(file);
	}

	/**
	 * 拷贝文件
	 * 
	 * @param a
	 * @param b
	 */
	public static boolean copyFiles(String a, String b) {
		File file = new File(a);
		if (!file.exists()) {
			LOGGER.error("拷贝索引文件报错，文件不存在！" + a);
			return false;
		}
		File fileb = new File(b);
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			if (file.isFile()) {
				fis = new FileInputStream(file);
				fos = new FileOutputStream(fileb);
				byte[] bb = new byte[(int) file.length()];
				fos.write(bb);
			} else if (file.isDirectory()) {
				if (!fileb.exists() && !fileb.mkdir()) {
					processCreateFailure();
				}
				String[] fileList;
				fileList = file.list();
				for (int i = 0; i < fileList.length; i++) {
					copyFiles(a + "/" + fileList[i], b + "/" + fileList[i]);
				}
			}
			return true;
		} catch (IOException e) {
			LOGGER.error("拷贝索引文件报错，文件不存在！" + e.getMessage());
			LOGGER.error("", e);
			return false;
		} finally {
			IOUtils.closeQuietly(fis);
			IOUtils.closeQuietly(fos);
		}
	}

	/**
	 * 文件上传默认的上传路径
	 * 
	 * @return
	 */
	public static String defaultPath() {
		return FundamentalConfigProvider
				.get(Constants.FUNDAMENTAL_DEFAULT_UPLOAD_PATH);
	}
}
