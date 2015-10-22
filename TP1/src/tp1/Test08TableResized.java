package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class Test08TableResized extends Test01SWT{
	 private Table table;
	protected void buildUI(){
		
		shell.setLayout(new GridLayout());
		
		 table = new Table(shell,SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
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
		//taille total = client area, puis taille des colonnes
		
		/*int clientAreaWidth = shell.getSize().x;
		int totalBaseWidth = table.getColumn(0).getWidth() + table.getColumn(1).getWidth() + table.getColumn(2).getWidth();
		final int widthWithoutFirst =  table.getColumn(1).getWidth() + table.getColumn(2).getWidth();
		
		table.addListener(SWT.Resize, new Listener(){
			public void handleEvent(Event e){
				int newSize = table.getSize().x;
				table.getColumn(0).setResizable(true);
				table.getColumn(1).setResizable(false);
				table.getColumn(2).setResizable(false);
				table.setColumnOrder(0);
				table.getColumn(0).setWidth((newSize - widthWithoutFirst));
				System.out.println("Redimensionnement en cours");
			}
		});*/
		
		
		
		addListeners();
		shell.pack();
	}
	
	private void addListeners(){
		shell.addListener(SWT.Resize, new Listener(){
			@Override
			public void handleEvent(Event e){
				TableColumn[] colums = table.getColumns();
				int size =0;
				for (int i=1; i<colums.length;i++){
					colums[i].pack();
					size += colums[i].getWidth();
				}
				
				int remaining = table.getClientArea().width - size;
				colums[0].pack();
				if (colums[0].getWidth() < remaining){
					colums[0].setWidth(remaining);
				}
			}
		});
	}
	
	public static void main(String[] args){
		new Test08TableResized().startEventLoop();
	}

}

//addListener(SWT.RESIZE)