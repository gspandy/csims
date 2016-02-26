package net.sweet.test.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.gtm.csims.utils.ExcelUtil;

public class ExcelLoopReader {
	public void loopRead(InputStream is, int endRowIndex) {
		HSSFWorkbook wb = null;
		try {
			POIFSFileSystem fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);

			HSSFSheet sheet = wb.getSheetAt(0);

			for (int i = 1; i <= endRowIndex; i++) {
				ExcelRow _excelRow = new ExcelRow();
				_excelRow.setRowA(ExcelUtil
						.getValue(sheet.getRow(i).getCell(0)));
				_excelRow.setRowB(ExcelUtil
						.getValue(sheet.getRow(i).getCell(1)));
				_excelRow.setRowC(ExcelUtil
						.getValue(sheet.getRow(i).getCell(2)));
				_excelRow.setRowD(ExcelUtil
						.getValue(sheet.getRow(i).getCell(3)));
				// 你把你的方法写道这
				System.out.println(_excelRow.getRowA() + _excelRow.getRowB()
						+ _excelRow.getRowC() + _excelRow.getRowD());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取Excel失败");
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	class ExcelRow {
		private String rowA;
		private String rowB;
		private String rowC;
		private String rowD;

		public String getRowA() {
			return rowA;
		}

		public void setRowA(String rowA) {
			this.rowA = rowA;
		}

		public String getRowB() {
			return rowB;
		}

		public void setRowB(String rowB) {
			this.rowB = rowB;
		}

		public String getRowC() {
			return rowC;
		}

		public void setRowC(String rowC) {
			this.rowC = rowC;
		}

		public String getRowD() {
			return rowD;
		}

		public void setRowD(String rowD) {
			this.rowD = rowD;
		}
	}
	
	
	public static void main(String[] a) {
		ExcelLoopReader reader = new ExcelLoopReader();
		try {
			FileInputStream is = new FileInputStream(new File("E:/机构表彰信息模板.xls"));
			reader.loopRead(is, 8);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
}