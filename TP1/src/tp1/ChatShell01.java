package tp1;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ChatShell01{
	
	protected Display display;
	protected Shell shell;
	
	public ChatShell01(){
		init();
		buildUI();
	}
	
	protected void buildUI(){
		FormLayout fl = new FormLayout();
		shell.setLayout(fl);
		
		FormData fd;
		int margin = 10;
		
		Label invite = new Label(shell, SWT.NONE);
		invite.setText("Message : ");
		fd = new FormData();
		fd.left = new FormAttachment(0,margin);
		fd.top = new FormAttachment(0, margin);
		invite.setLayoutData(fd);
		
		Text msg = new Text(shell, SWT.NONE);
		fd = new FormData();
		fd.left = new FormAttachment(invite);
		fd.right = new FormAttachment(100, -margin);
		fd.top = new FormAttachment (invite, 0, SWT.CENTER);
		msg.setLayoutData(fd);
		
		shell.setSize(300, 200);
	}
	
	protected void init(){
		display = new Display();
		shell = new Shell(display);
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
		new ChatShell01().startEventLoop();
	}

}
