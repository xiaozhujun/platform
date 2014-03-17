package org.whut.platform.fundamental.jxl.utils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.whut.platform.fundamental.config.FundamentalConfigProvider;
import org.whut.platform.fundamental.jxl.model.ExcelMap;
import org.whut.platform.fundamental.logger.PlatformLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * JXL版本的Excel导入导出工具，修改自刘玲的代码.
 * 
 * @author yangjie01
 * 
 */
public final class JxlExportImportUtils {

	private static final PlatformLogger JXL_LOGGER = PlatformLogger
			.getLogger(JxlExportImportUtils.class);

	private JxlExportImportUtils() {
		throw new Error("Utility classes should not instantiated!");
	}

	public static ExcelMap analysisExcel(File file) {

		JXL_LOGGER.debug("file.exists is " + file.exists());

		Workbook wb = null;
		try {
			// 构造Workbook（工作薄）对象
			wb = Workbook.getWorkbook(file);
		} catch (BiffException e) {
			JXL_LOGGER.debug("Get workbook throw BiffException:{}", e);
		} catch (IOException e) {
			JXL_LOGGER.debug("Get workbook throw IOException:{}", e);
		}
		if (wb == null) {
			return null;
		}
		Sheet sheet = wb.getSheet(0);
		// 得到当前工作表的行数
		int rowNum = sheet.getRows();
		List<String> listTitle = new ArrayList<String>();
		List<List<String>> listContent = new ArrayList<List<String>>();
		// 循环每行
		for (int j = 0; j < rowNum; j++) {
			// 每行开始时，新建一个list，用来存放从第2行开始的每行数据
			List<String> listRow = new ArrayList<String>();
			// 得到当前行的所有单元格
			Cell[] cells = sheet.getRow(j);
			if (cells != null && cells.length > 0) {
				// 对每个单元格进行循环
				for (int k = 0; k < cells.length; k++) {
					// 读取当前单元格的值
					String cellValue = cells[k].getContents();
					if (j == 0) {
						listTitle.add(cellValue);
					} else {
						listRow.add(cellValue);
					}
				}
			}
			if (listRow.size() != 0) {
				listContent.add(listRow);
			}

		}
		// 最后关闭资源，释放内存
		wb.close();
		ExcelMap em = new ExcelMap();
		em.setHeads(listTitle);
		em.setContents(listContent);
		return em;
	}
    /*
      通过字节流解析Excel
     */
    public static ExcelMap analysisExcel(InputStream inputStream) {
       /* JXL_LOGGER.debug("file.exists is " + inputStream.);*/
        Workbook wb = null;
        try {
            // 构造Workbook（工作薄）对象
            wb = Workbook.getWorkbook(inputStream);
        } catch (BiffException e) {
            JXL_LOGGER.debug("Get workbook throw BiffException:{}", e);
        } catch (IOException e) {
            JXL_LOGGER.debug("Get workbook throw IOException:{}", e);
        }
        if (wb == null) {
            return null;
        }
        Sheet sheet = wb.getSheet(0);
        // 得到当前工作表的行数
        int rowNum = sheet.getRows();
        List<String> listTitle = new ArrayList<String>();
        List<List<String>> listContent = new ArrayList<List<String>>();
        // 循环每行
        for (int j = 0; j < rowNum; j++) {
            // 每行开始时，新建一个list，用来存放从第2行开始的每行数据
            List<String> listRow = new ArrayList<String>();
            // 得到当前行的所有单元格
            Cell[] cells = sheet.getRow(j);
            if (cells != null && cells.length > 0) {
                // 对每个单元格进行循环
                for (int k = 0; k < cells.length; k++) {
                    // 读取当前单元格的值
                    String cellValue = cells[k].getContents();
                    if (j == 0) {
                        listTitle.add(cellValue);
                    } else {
                        listRow.add(cellValue);
                    }
                }
            }
            if (listRow.size() != 0) {
                listContent.add(listRow);
            }

        }
        // 最后关闭资源，释放内存
        wb.close();
        ExcelMap em = new ExcelMap();
        em.setHeads(listTitle);
        em.setContents(listContent);
        return em;
    }

	/**
	 * 根据标题确定及顺序确定的list列表生成excel表格返回
	 * 
	 * @param listTitles
	 * @return
	 */
	public static File createExcel(List<String> listTitles,
			List<List<String>> listContents) {
		// 将标题行与内容行合到同一个list中
		List<List<String>> list = new ArrayList<List<String>>();
		list.add(listTitles);
		if (listContents != null && listContents.size() > 0) {
			for (List<String> l : listContents) {
				list.add(l);
			}
		}
		WritableWorkbook wwb = null;
		// 指定文件生成的路径，可用"testCreate.xls"将文件生成在当前项目的要路径下
		String filePath = createFilePath();
        System.out.print(filePath+"000009");
		File file = new File(filePath);
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(file);
			if (wwb != null) {
				// 创建一个可写入的工作表
				// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
				WritableSheet ws = wwb.createSheet("sheet1", 0);
				int rowSize = list.size();
				int columnSize = list.get(0).size();
				writeData(ws, list, rowSize, columnSize);
				// 从内存中写入文件中
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
			}
		} catch (IOException e) {
			JXL_LOGGER.debug("create work book throw io exception:{}", e);
		} catch (RowsExceededException e) {
			JXL_LOGGER.debug("write excel row throw RowsExceededException:{}",
					e);
		} catch (WriteException e) {
			JXL_LOGGER.debug("write excel throw WriteException:{}", e);
		}
		return file;
	}
    /**
     * 根据标题确定及顺序确定的list列表生成excel表格返回
     *
     * @param listTitles
     * @return
     * 新加入的 2014/3/17 zhuzhenhua
     */
    public static File createExcel(List<String> listTitles,
                                   List<List<String>> listContents,String fileName) {
        // 将标题行与内容行合到同一个list中
        List<List<String>> list = new ArrayList<List<String>>();
        list.add(listTitles);
        if (listContents != null && listContents.size() > 0) {
            for (List<String> l : listContents) {
                list.add(l);
            }
        }
        WritableWorkbook wwb = null;
        // 指定文件生成的路径，可用"testCreate.xls"将文件生成在当前项目的要路径下
        String filePath = createFilePath(fileName);
        File file = new File(filePath);
        try {
            // 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
            wwb = Workbook.createWorkbook(file);
            if (wwb != null) {
                // 创建一个可写入的工作表
                // Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
                WritableSheet ws = wwb.createSheet("sheet1", 0);
                int rowSize = list.size();
                int columnSize = list.get(0).size();
                writeData(ws, list, rowSize, columnSize);
                // 从内存中写入文件中
                wwb.write();
                // 关闭资源，释放内存
                wwb.close();
            }
        } catch (IOException e) {
            JXL_LOGGER.debug("create work book throw io exception:{}", e);
        } catch (RowsExceededException e) {
            JXL_LOGGER.debug("write excel row throw RowsExceededException:{}",
                    e);
        } catch (WriteException e) {
            JXL_LOGGER.debug("write excel throw WriteException:{}", e);
        }
        return file;
    }

	private static void writeData(WritableSheet ws, List<List<String>> list,
			int rowSize, int columnSize) throws WriteException {
		for (int i = 0; i < rowSize; i++) {
			writeRowData(ws, list, i, columnSize);
		}
	}

	private static void writeRowData(WritableSheet ws, List<List<String>> list,
			int rowIndex, int columnSize) throws WriteException {
		List<String> row = list.get(rowIndex);
		for (int j = 0; j < columnSize; j++) {
			writeColumnData(ws, row, rowIndex, j);
		}
	}

	private static void writeColumnData(WritableSheet ws, List<String> row,
			int rowIndex, int columnIndex) throws WriteException {
		ws.addCell(new Label(columnIndex, rowIndex, row.get(columnIndex)));
	}

	/**
	 * 创建包含指定数目sheet的Excel表格
	 * 
	 * @param titlesBySheet
	 * @param contentBySheet
	 * @param nameBySheet
	 * @param sheetCount
	 * @return
	 */
	public static File createExcelWithMultipleSheet(List<String> titlesBySheet,
			List<String> contentBySheet, List<String> nameBySheet,
			int sheetCount) {
		WritableWorkbook wwb = null;
		String filePath = createFilePath();
		File file = new File(filePath);
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(file);
			if (wwb != null) {
				for (int i = 0; i < sheetCount; i++) {
					WritableSheet ws = wwb.createSheet(nameBySheet.get(i), i);
					List<List<String>> list = new ArrayList<List<String>>();
					String[] titlesArray = titlesBySheet.get(i).split(",");
					List<String> titles = Arrays.asList(titlesArray);
					list.add(titles);
					String[] sheetContext = contentBySheet.get(i).split(";");
					for (int j = 0; j < sheetContext.length; j++) {
						String[] contextRecord = sheetContext[j].split(",");
						List<String> record = Arrays.asList(contextRecord);
						list.add(record);
					}
					for (int p = 0; p < list.size(); p++) {
						for (int q = 0; q < list.get(p).size(); q++) {
							Label labelC = new Label(q, p, list.get(p).get(q));
							ws.addCell(labelC);
						}
					}

				}
				// 从内存中写入文件中
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
			}
		} catch (IOException e) {
			JXL_LOGGER.debug("write excel throw IOException:{}", e);
		} catch (WriteException e) {
			JXL_LOGGER.debug("write excel throw WriteException:{}", e);
		}
		return file;
	}

	/**
	 * 根据接收的ExcelMap对象，生成空的Excel模板，此时Excel属性的contents是为空的，只有标题行
	 */
	public static File exportExcel(ExcelMap excelMap) {
		List<String> heads = excelMap.getHeads();
		List<List<String>> content = excelMap.getContents();
		return createExcel(heads, content);
	}

	/**
	 * 生成临时目录
	 * 
	 * @return
	 */
	private static String createFilePath() {
		String dirName = FundamentalConfigProvider.get("tempDir");
       /* String dirName="c:/template";*/
		FileCreator.createDir(dirName);
		String prefix = "temp";
		String suffix = ".xls";
		return FileCreator.createTempFile(prefix, suffix, dirName);
	}
    /*
       新加入的 2014/3/17 zhuzhenhua
     */
    private static String createFilePath(String fileName) {
        String dirName = FundamentalConfigProvider.get("tempDir");
       /* String dirName="c:/template";*/
        FileCreator.createDir(dirName);
        String prefix = SeparateFileFullNameToGetFileName(fileName)+"_Error";
        String suffix = "."+SeparateFileFullNameToGetFileSuffix(fileName);
        return FileCreator.createTempFile(prefix, suffix, dirName);
    }
    /*
      zhuzhenhua 2014/3/17
     */
    public static String SeparateFileFullNameToGetFileName(String fileFullName){
        String[] str=fileFullName.split("\\.");
        return str[0];
    }
    public static String SeparateFileFullNameToGetFileSuffix(String fileFullName){
        String[] str=fileFullName.split("\\.");
        return str[1];
    }
    /**
	 * 不存储于硬盘上，直接在内存中返回数据给用户，在前台自己构造excel
	 * 
	 * @param listTitles
	 * @param listContents
	 * @return
	 */
	public static InputStream createExcelWithoutFileCreate(
			List<String> listTitles, List<List<String>> listContents) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 将标题行与内容行合到同一个list中

		List<List<String>> list = new ArrayList<List<String>>();
		if (ObjectUtils.notEqual(listTitles, null)) {
			list.add(listTitles);
		}
		if (ObjectUtils.notEqual(listContents, null) && listContents.size() > 0) {
			list.addAll(listContents);
		}
		WritableWorkbook wwb = null;
		try {
			// 首先要使用Workbook类的工厂方法创建一个可写入的工作薄(Workbook)对象
			wwb = Workbook.createWorkbook(baos);
		} catch (IOException e) {
			JXL_LOGGER.debug("Create Workbook throw IOException:{}", e);
		}
		if (ObjectUtils.notEqual(wwb, null)) {
			// 创建一个可写入的工作表
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			writeContentToSheet(list, ws);
			try {
				wwb.write();
				// 关闭资源，释放内存
				wwb.close();
				return new ByteArrayInputStream(baos.toByteArray());
			} catch (IOException e) {
				JXL_LOGGER.debug("write excel throw IOException:{}", e);
			} catch (WriteException e) {
				JXL_LOGGER.debug("write excel throw WriteException:{}", e);
			} finally {
				IOUtils.closeQuietly(baos);
			}
		}
		return null;
	}

	private static void writeLineToRow(List<String> line, WritableSheet ws,
			int row, int column) {
		for (int j = 0; j < column; j++) {
			writeRowToCell(ws, new Label(j, row, line.get(j)));
		}
	}

	private static void writeRowToCell(WritableSheet ws, Label labelC) {
		try {
			ws.addCell(labelC);
		} catch (RowsExceededException e) {
			JXL_LOGGER.debug("write excel row throw RowsExceededException:{}",
					e);
		} catch (WriteException e) {
			JXL_LOGGER.debug("write excel row throw WriteException:{}", e);
		}
	}

	private static void writeContentToSheet(List<List<String>> list,
			WritableSheet ws) {
		int row = list.size();
		int column = list.get(0).size();
		for (int i = 0; i < row; i++) {
			writeLineToRow(list.get(i), ws, i, column);
		}
	}
}
