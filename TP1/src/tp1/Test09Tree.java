package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class Test09Tree extends Test01SWT {

	protected void buildUI(){
		
	shell.setLayout(new FillLayout());
	
	final Tree tree = new Tree(shell, SWT.V_SCROLL);
	for (int i=0;i<5;i++){
		TreeItem item = new TreeItem(tree, SWT.NONE);
		item.setText("Noeud " + i);
		for (int j=0;j<3;j++){
			TreeItem subItem = new TreeItem(item,SWT.NONE);
			subItem.setText("Noeud " + i + "." +j);
			
		}
	}
	
	tree.pack();
	
	Menu menu = new Menu(tree);
	MenuItem menuItem = new MenuItem(menu, SWT.NONE);
	menuItem.setText("Print Element");
	menuItem.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event){
			System.out.println(tree.getSelection()[0].getText());
		}
	});
	tree.setMenu(menu);
	
	shell.pack();
	}
	
	public static void main(String[] args){
		new Test09Tree().startEventLoop();
	}

}
