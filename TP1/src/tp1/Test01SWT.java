package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Test01SWT {
	protected Display display;
	protected Shell shell;
	
	public Test01SWT(){
		init();
		buildUI();
	}
	
	protected void init(){
		display = new Display();
		shell = new Shell(display);
	}
	
	protected void buildUI(){
		shell.setLayout(new FillLayout());
		
		Label label = new Label(shell, SWT.BORDER);
		label.setText("Un label");
		label.setToolTipText("Info Bulle sans info "); 
		Text text = new Text(shell, SWT.None);
		text.setText("Coucou les gens!!");
		text.setBackground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
		text.setForeground(display.getSystemColor(SWT.COLOR_DARK_MAGENTA));
		
		label.pack();
		text.pack();
		
		shell.pack(); 
		
		
	}
	protected void startEventLoop() {
	
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}
	
	public static void main(String[] args) {
	
		new Test01SWT().startEventLoop();
	}

	

}