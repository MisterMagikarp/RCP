package tp1;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class Test12GlobalListener extends Test01SWT{
	
	Label label;
	
	public Test12GlobalListener(){
		super();
		addListeners();
	}
	
	@Override
	protected void buildUI(){
		shell.setSize(500,200);
		shell.setLayout(new FillLayout());
		
		label = new Label(shell, SWT.BORDER);
		label.setText("Dynamic string : ");
		
	}
	
	private void addListeners(){
		display.addFilter(SWT.KeyDown, new Listener(){
			@Override
			public void handleEvent(Event event){
				char c = event.character;
				label.setText(label.getText() + c);
			}
		});
	}
	
	public static void main(String[] args){
		new Test12GlobalListener().startEventLoop();
	}
	

}
