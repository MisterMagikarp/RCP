package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;

public class Test05DateTime extends Test01SWT {
	
	protected void buildUI(){
		
		shell.setLayout(new RowLayout());
		
		Composite parent = new Composite(shell, SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns =1;
		parent.setLayout(gridLayout);
		
		
		final DateTime calendar = new DateTime(parent, SWT.CALENDAR);
		DateTime date= new DateTime(parent, SWT.DATE);
		DateTime time= new DateTime(parent, SWT.TIME);
		DateTime dateD= new DateTime(parent, SWT.DATE | SWT.DROP_DOWN);
		
		Button button = new Button(parent, SWT.PUSH);
		button.setText("Print");
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				System.out.println("Selection : " + calendar.getDay() + " / " + (calendar.getMonth() +1) + " / " + (calendar.getYear()));
			}
		});
		
		shell.pack();
	}

	public static void main(String[] args){
		new Test05DateTime().startEventLoop();
	}
}
