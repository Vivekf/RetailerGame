package vff.retailer;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;


public class RetailerTableModel extends AbstractTableModel implements TableModelListener{

	private String[] columnNames = {"Current Week", "Price Level ($)", "Demand (units)", "Unsold Inventory"};
	private Object[][] data = {
			{null,null,null,null}
	};
	
	
	public RetailerTableModel() {
		// TODO Auto-generated constructor stub
	}

	
	public void addRow(Object[] new_row){
		
        Object[][] newData = new Object[data.length + 1][];

        // Copy old data to new data
        for (int i = 0; i < data.length; i++) { 
            newData[i] = data[i]; 
        } 

       // Append new row 
       newData[data.length] = new_row; 

       // Pass the new data to the table model
       this.data = newData;
       fireTableDataChanged(); 
	}
	
	
	public void clearData(){
		Object[][] newData = {
				{null,null,null,null}
		};
		this.data = newData; 
	}
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}
	
	public String getColumnName(int col) {
        return columnNames[col];
    }


	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return data[row][col];
	}
	
	public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
	
	public void tableChanged(TableModelEvent e) {
		setValueAt(new Integer(200),1,1);
		// Do something with the data...
    }
	

}
