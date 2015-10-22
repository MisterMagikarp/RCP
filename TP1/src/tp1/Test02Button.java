package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;

public class Test02Button extends Test01SWT {
	
	protected void buildUI(){
		
		super.buildUI(); //super car on réutilise les widgets du premier
		
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Bouton");
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				System.out.println("CLICK!");
			}
		});
		
		shell.pack();
		
	}
	
	public static void main(String[] args){
		new Test02Button().startEventLoop();
	}
}
