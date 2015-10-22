package tp1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ChatShell03 {

	protected Display display;
	protected Shell shell;
	protected Text msg, textDisplay;
	protected ScrolledComposite scroll;
	
	public ChatShell03(){
		init();
		buildUI();
		startEventLoop();	
		
	}
	
	
	
	private void addListeners() {
		// TODO Auto-generated method stub
		msg.addSelectionListener(new SelectionAdapter(){
			public void widgetDefaultSelected(SelectionEvent e){
				textDisplay.append(msg.getText() + SWT.CR);
				msg.setText("");
				scroll.setMinSize(textDisplay.computeSize(SWT.DEFAULT, SWT.DEFAULT));
				 //scroll.setOrigin(scroll.getOrigin().x, size.y); //scroll automatique
			}
		});
	}



	private void buildUI() {
		FormLayout fl = new FormLayout();
		shell.setLayout(fl);
		
		FormData fd;
		int margin = 10, padding =3;
		
		Label invite = new Label(shell, SWT.NONE);
		invite.setText("Message: ");
		fd = new FormData();
		fd.left = new FormAttachment(0, margin);
		fd.bottom = new FormAttachment(100,-margin );
		
		invite.setLayoutData(fd);
	
		
		 msg = new Text(shell, SWT.NONE);
		fd = new FormData();
		fd.left = new FormAttachment(invite);
		fd.right = new FormAttachment(100, -margin);
		fd.bottom = new FormAttachment(invite, 0 , SWT.CENTER);
		msg.setLayoutData(fd);
		
		
	    
	    Group messages = new Group(shell, SWT.FLAT); 
		messages.setLayout(new FillLayout());
		fd = new FormData();
		fd.top = new FormAttachment(0,margin);
		fd.left = new FormAttachment(0,margin);
		fd.right = new FormAttachment(100, -margin);
		fd.bottom = new FormAttachment(invite, -padding);
		messages.setLayoutData(fd);
		
		scroll = new ScrolledComposite(messages, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		textDisplay = new Text(scroll, SWT.NONE | SWT.MULTI);
		textDisplay.setEditable(false);
		textDisplay.setText("Messages :\n");
		scroll.setContent(textDisplay);
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		scroll.setMinSize(textDisplay.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		addListeners();
		
		shell.setSize(300,200);
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
		new ChatShell03();
	}
}