package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Test07Table extends Test01SWT{
	
	protected void buildUI(){
		
		shell.setLayout(new GridLayout());
		
		Table table = new Table(shell,SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 300;
		table.setLayoutData(data);
		
		String[] titles = { "Prenom", "Nom", "Age"};
		for (int i=0; i< titles.length;i++){
			TableColumn column = new TableColumn(table, SWT.NONE);
			column.setText(titles[i]);
			table.getColumn(i).pack();
		}
		
		
		for(int i=0; i<=50; i++){
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0,"Prenom" + i);
			item.setText(1, "NOM" + i);
			item.setText(2, String.valueOf(i));
		}
		
		for (int i=0; i <titles.length; i++){
			table.getColumn(i).pack();
		}
		
		shell.pack();
	}
	
	public static void main(String[] args){
		new Test07Table().startEventLoop();
	}

}
