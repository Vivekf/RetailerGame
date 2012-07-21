package vff.retailer;

import java.io.File;
import javax.swing.*;
import javax.swing.table.*;
import jxl.*;
import jxl.write.*;
import jxl.write.Number;
import jxl.CellView;


public class ExcelExporter {

	public ExcelExporter() {
		// TODO Auto-generated constructor stub
	}
	
	void fillData(JTable table, File file) {

        try {

            WritableWorkbook workbook1 = Workbook.createWorkbook(file);
            WritableSheet sheet1 = workbook1.createSheet("First Sheet", 0); 
            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                Label column = new Label(i, 0, model.getColumnName(i)); 
                sheet1.addCell(column);
                sheet1.setColumnView(i, model.getColumnName(i).length()+1);                
            }
            int j = 0;
            // In this TableModel the first row consists of nulls; so `i' starts at 1
            for (int i = 1; i < model.getRowCount(); i++) {
                for (j = 0; j < model.getColumnCount(); j++) {
                    
                	WritableFont numberFont = new WritableFont(WritableFont.ARIAL);
                	WritableCellFormat numberFormat = new WritableCellFormat(numberFont, new NumberFormat("#0.0"));
                	Number numberCell = new Number(0, 0, 25, numberFormat);
                	
                	Number row = new Number(j, i + 1, 
                            ((Integer)model.getValueAt(i, j)).intValue());
                    sheet1.addCell(row);
                }
            }
            workbook1.write();
            workbook1.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
