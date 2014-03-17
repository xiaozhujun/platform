package org.whut.platform.fundamental.fileupload;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.whut.platform.fundamental.config.Constants;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.logger.PlatformLogger;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Locale;

/**
 * author Lingo
 */
public class MultipartRequestParser {
	private static final String UTF8 = "UTF-8";
	private static final PlatformLogger LOGGER = PlatformLogger
			.getLogger(MultipartRequestParser.class);

	private static final long MEGA_SIZE = 1024l;

	private static final int MAX_FILE_NAME_LENGTH = 80;

	private static final int EMPTY_DATA_SIZE = 0;

	@SuppressWarnings("unchecked")
	private List<FileItem> parseRequest(HttpServletRequest request)
			throws Exception {
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		return upload.parseRequest(request);
	}

	/**
	 * 
	 * @param request
	 * @param rootPath
	 * @param maxSize
	 * @param fileService
	 * @return
	 * @throws Exception
	 */
	public MultipartRequestResult parse(HttpServletRequest request,
			String rootPath, int maxSize, FileService fileService)
			throws Exception {

		long byteMaxSize = MEGA_SIZE * MEGA_SIZE * maxSize;

		MultipartRequestResult result = new MultipartRequestResult();
		if (!ServletFileUpload.isMultipartContent(request)) {
			LOGGER.debug("upload request is not multipart.");
			return result;
		}

		List<FileItem> items = this.parseRequest(request);

		if (items == null) {
			LOGGER.debug("after parse, there is no FileItem, just return.");
			return result;
		}

		for (FileItem item : items) {
			String fileItemName = item.getName();
			if (!item.isFormField() && StringUtils.isNotBlank(fileItemName)) {
				String fileName = FileUtil.getFileFullName(fileItemName);

				this.fileValidate(fileService, fileName, item.getSize(),
						byteMaxSize);

				File dest = fileService.getNewFile(
						fileName.toLowerCase(Locale.CHINA), rootPath);
				item.write(dest);

				String filePath = this.parseFilePath(dest.getAbsolutePath()
						.replace(File.separatorChar, '/'), rootPath.replace(
						File.separatorChar, '/'));

				result.getFileInfos().add(new FileInfo(fileName, filePath));
			} else {
				result.getParams().put(item.getFieldName(),
						item.getString(UTF8));
			}
		}
		return result;
	}
    /*
      新加入的 zhuzhenhua
     */
    public FileInfo parseRequest(HttpServletRequest request,int maxSize, FileService fileService)
            throws Exception {

        long byteMaxSize = MEGA_SIZE * MEGA_SIZE * maxSize;

        FileInfo fileInfo=null;

        if (!ServletFileUpload.isMultipartContent(request)) {
            LOGGER.debug("upload request is not multipart.");
            return fileInfo;
        }

        List<FileItem> items = this.parseRequest(request);

        if (items == null) {
            LOGGER.debug("after parse, there is no FileItem, just return.");
            return fileInfo;
        }
        for (FileItem item : items) {
            String fileItemName = item.getName();
            if (!item.isFormField() && StringUtils.isNotBlank(fileItemName)) {
                String fileName = FileUtil.getFileFullName(fileItemName);

                this.fileValidate(fileService, fileName, item.getSize(),
                        byteMaxSize);
                InputStream inputStream=item.getInputStream();
                fileInfo=new FileInfo();
                fileInfo.setInputStream(inputStream);
                fileInfo.setName(fileName);
            } else {

            }
        }
        return fileInfo;
    }
	private String parseFilePath(String destFileAbsolutePath,
			String dealedRootPath) {
		return StringUtils.substringAfter(destFileAbsolutePath, dealedRootPath);
	}

	/**
	 * 通过根目录+相对目录存储上传的文件，根目录可以为null，相对目录不能为null
	 * 
	 * @param request
	 * @param relativePath
	 * @param maxSize
	 * @param fileService
	 * @return
	 * @throws Exception
	 */
	public MultipartRequestResult parse(HttpServletRequest request,
			String rootPathForPass, String relativePath, int maxSize,
			FileService fileService) throws Exception {

		long byteMaxSize = MEGA_SIZE * MEGA_SIZE * maxSize;

		MultipartRequestResult result = new MultipartRequestResult();
		if (!ServletFileUpload.isMultipartContent(request)) {
			LOGGER.debug("upload request is not multipart.");
			return result;
		}

		List<FileItem> items = this.parseRequest(request);

		if (items == null) {
			LOGGER.debug("after parse, there is no FileItem, just return.");
			return result;
		}
		String rootPath = rootPathForPass;
		for (FileItem item : items) {
			String fileItemName = item.getName();
			if (!item.isFormField() && StringUtils.isNotBlank(fileItemName)) {
				String fileName = FileUtil.getFileFullName(fileItemName);

				this.fileValidate(fileService, fileName, item.getSize(),
						byteMaxSize);

				File dest = this.getNewFileWithRelativePath(fileService,
						fileName, rootPath, relativePath);

				rootPath = this.getRealRootPath(fileService, rootPath);

				item.write(dest);

				String destFileAbsolutePath = dest.getAbsolutePath().replace(
						File.separatorChar, '/');

				String filePath = StringUtils.substringAfter(
						destFileAbsolutePath, rootPath);

				result.getFileInfos().add(new FileInfo(fileName, filePath));
			} else {
				String name = item.getFieldName();
				String value = item.getString(UTF8);
				result.getParams().put(name, value);
			}
		}
		return result;
	}

	public MultipartRequestResult parse2(HttpServletRequest request,
			String rootPathForPass, String relativePath, int maxSize,
			FileService fileService) throws Exception {

		long byteMaxSize = MEGA_SIZE * MEGA_SIZE * maxSize;

		MultipartRequestResult result = new MultipartRequestResult();
		if (!ServletFileUpload.isMultipartContent(request)) {
			LOGGER.debug("upload request is not multipart.");
			return result;
		}

		List<FileItem> items = this.parseRequest(request);

		if (items == null) {
			LOGGER.debug("after parse, there is no FileItem, just return.");
			return result;
		}
		String rootPath = rootPathForPass;
		
		for (int i = 0; i < items.size(); i++) {
			FileItem item = (FileItem) items.get(i);
			String fileItemName = item.getName();
			if (!item.isFormField() && StringUtils.isNotBlank(fileItemName)) {
				String fileName = FileUtil.getFileFullName(fileItemName);
				this.fileValidate(fileService, fileName, item.getSize(),
						byteMaxSize);

				File dest = this.getDestFile(fileService, fileName, rootPath,
						relativePath, i);

				rootPath = this.getRealRootPath(fileService, rootPath);

				item.write(dest);
				fileName = dest.getName();
				String destFileAbsolutePath = dest.getAbsolutePath().replace(
						File.separatorChar, '/');

				String filePath = StringUtils.substringAfter(
						destFileAbsolutePath, rootPath);

				result.getFileInfos().add(new FileInfo(fileName, filePath));
			} else {
				String name = item.getFieldName();
				String value = item.getString(UTF8);
				result.getParams().put(name, value);
			}
		}
		return result;
	}

	private String getRealRootPath(FileService fileService, String rootPath) {
		return fileService.getRootPath(rootPath)
				.replace(File.separatorChar, '/').replace("//", "/");
	}

	/**
	 * 文件
	 * 
	 * @param fileService
	 * @param fileName
	 * @param itemSize
	 * @param byteMaxSize
	 */
	private void fileValidate(FileService fileService, String fileName,
			long itemSize, long byteMaxSize) {

		// 如果文件为空就停止处理
		if (itemSize <= EMPTY_DATA_SIZE) {
			throw new MultipartRequestException(fileName + "文件内容为空");
		}

		// 如果文件超过20m就停止处理
		if (itemSize > byteMaxSize) {
			throw new MultipartRequestException(fileName + "文件上传太大");
		}

		// 简单类型验证
		if (!fileService.isMagazineUploadable(fileName)) {
			throw new MultipartRequestException(fileName + "文件类型不允许");
		}

		// 简单类型验证
		if (fileName.length() > MAX_FILE_NAME_LENGTH) {
			throw new MultipartRequestException(fileName + "文件名称太长，不能超过80个字符");
		}
	}

	/**
	 * 通过根目录+相对目录存储上传的文件，根目录可以为null，相对目录不能为null
	 * 
	 * @param request
	 * @param relativePath
	 * @param maxSize
	 * @param fileService
	 * @return
	 * @throws Exception
	 */
	public MultipartRequestResult parse(HttpServletRequest request,
			String rootPathForPass, String relativePath, String prefixForPass,
			int maxSize, FileService fileService) throws Exception {

		long byteMaxSize = MEGA_SIZE * MEGA_SIZE * maxSize;

		MultipartRequestResult result = new MultipartRequestResult();
		if (!ServletFileUpload.isMultipartContent(request)) {
			LOGGER.debug("upload request is not multipart.");
			return result;
		}

		List<FileItem> items = this.parseRequest(request);

		if (items == null) {
			LOGGER.debug("after parse, there is no FileItem, just return.");
			return result;
		}

		String rootPath = rootPathForPass;
		String prefix = prefixForPass;
		for (FileItem item : items) {
			String fileItemName = item.getName();
			if (!item.isFormField() && StringUtils.isNotBlank(fileItemName)) {
				FileInfo fileInfo = this.getFileInfo(fileService, item,
						rootPath, relativePath, prefix, byteMaxSize);
				result.getFileInfos().add(fileInfo);
			} else {
				result.getParams().put(item.getFieldName(),
						item.getString(UTF8));
			}
		}
		return result;
	}

	private FileInfo getFileInfo(FileService fileService, FileItem item,
			String rootPath, String relativePath, String prefix,
			long byteMaxSize) throws Exception {
		String fileName = FileUtil.getFileFullName(item.getName());

		long itemSize = item.getSize();

		this.fileValidate(fileService, fileName, itemSize, byteMaxSize);

		File dest = this.getNewFileWithRelativePath(fileService, fileName,
				rootPath, relativePath);

		item.write(dest);

		String destFileAbsolutePath = dest.getAbsolutePath().replace(
				File.separatorChar, '/');

		String filePath = StringUtils.substringAfter(destFileAbsolutePath,
				this.getRealRootPath(fileService, rootPath));

		// 给返回的文件路径添加需要的前缀，这样在提供个性的化的文件下载服务的时候提供入口
		if (prefix != null) {
			String webroot = FundamentalConfigProvider
					.get(Constants.FUNDAMENTAL_WEBROOT_NAME);
			if (StringUtils.isNotBlank(webroot)) {
				filePath = webroot + this.formatPrefix(prefix) + filePath;
			} else {
				filePath = this.formatPrefix(prefix) + filePath;
			}
		}
		return new FileInfo(fileName, filePath);

	}

	private String formatPrefix(String prefix) {
		if (prefix.endsWith("/")) {
			return prefix.substring(0, prefix.lastIndexOf('/'));
		}
		return prefix;
	}

	private File getNewFileWithRelativePath(FileService fileService,
			String fileName, String rootPath, String relativePath) {
		return fileService.getNewFileWithRelativePath(
				fileName.toLowerCase(Locale.CHINA), rootPath, relativePath);
	}

    private String encodeMd5(String message) {
        String s = null;
        char hexDigits[] = {       // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',  'e', 'f'};
        try
        {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance( "MD5" );
            md.update( message.getBytes() );
            byte tmp[] = md.digest();          // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2];   // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0;                                // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {          // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i];                 // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];  // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];            // 取字节中低 4 位的数字转换
            }
            s = new String(str);                                 // 换后的结果转换为字符串

        }catch( Exception e )
        {
            e.printStackTrace();
        }
        return s;
    }

	/**
	 * 重新写的方法，获取文件保存路径。区别于原有方法的地方是，本方法不会按照日期创建很多级目录。
	 * 
	 * @param fileService
	 * @param fileName
	 * @param rootPath
	 * @param relativePath
	 * @param fileSeqNo
	 * @return
	 */
	private File getDestFile(FileService fileService, String fileName,
			String rootPath, String relativePath, int fileSeqNo) {
		String suffix = fileName.substring(fileName.indexOf("."),
				fileName.length());

		// 对文件名做MD5转义。
		// thread名+文件名+时间+文件序号
		fileName = encodeMd5(new StringBuilder(Thread.currentThread()
				.getName()).append(fileName).append(System.nanoTime())
				.append(fileSeqNo).toString())
				+ suffix;

		rootPath = fileService.getRootPath(rootPath);
		if (rootPath.endsWith(File.separator)) {
			rootPath = rootPath.substring(0, rootPath.length() - 1);
		}
		if (relativePath.startsWith(File.separator)) {
			relativePath = relativePath.substring(1, relativePath.length());
		}
		if (relativePath.endsWith(File.separator)) {
			relativePath = relativePath.substring(0, relativePath.length() - 1);
		}

		String filePath = new StringBuilder(rootPath).append(File.separator)
				.append(relativePath).append(File.separator).append(fileName)
				.toString();
		File file = new File(filePath);
		if (file.exists()) {
			file = getDestFile(fileService, fileName, rootPath, relativePath,
					fileSeqNo);
		}

		return file;
	}

}
