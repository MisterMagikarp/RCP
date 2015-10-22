package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;

public class Test10CTab extends Test01SWT {
	
	protected void buildUI(){
		
		shell.setLayout(new GridLayout());
		
		// SWT.BOTTOM to show at the bottom
		CTabFolder folder = new CTabFolder(shell, SWT.BOTTOM);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true, 2,1);
		folder.setLayoutData(data);
		
		CTabItem cTabItem1 = new CTabItem(folder, SWT.NONE);
		cTabItem1.setText("Tab1");
		
		CTabItem cTabItem2 = new CTabItem(folder, SWT.NONE);
		cTabItem2.setText("Tab2");
		
		CTabItem cTabItem3 = new CTabItem(folder, SWT.NONE);
		cTabItem3.setText("Tab3");
		
		Text text = new Text(folder, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		text.setText("Hello");
		
		cTabItem1.setControl(text);
		
		shell.setSize(300,200);
	}
	
	public static void main(String[] args) {
		new Test10CTab().startEventLoop();
	}

}
