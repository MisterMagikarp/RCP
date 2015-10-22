package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class Test04TabOrder extends Test01SWT{
	
	protected void buildUI(){
		
		shell.setLayout(new RowLayout());
		Text t1 = new Text (shell, SWT.NONE);
		t1.setText("Texte 1");
		Text t2 = new Text(shell, SWT.NONE);
		t2.setText("Texte 2");
		Text t3 = new Text(shell,SWT.NONE);
		t3.setText("Texte 3");
		
		
		Control[] controls = new Control[] {t2, t1, t3};
		shell.setTabList(controls);
		
		shell.pack();
	}
	
	public static void main(String[] args){
		new Test04TabOrder().startEventLoop();
	}

}
