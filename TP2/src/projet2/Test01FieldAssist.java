package projet2;

import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

//import jface et swt
public class Test01FieldAssist {
	
	
	protected Display display;
	protected Shell shell;
	protected Text text;
	protected ControlDecoration deco;
	
	public Test01FieldAssist(){
		init();
		buildUI();
		startEventLoop();
	}
	
	protected void init(){
		display = new Display();
		shell = new Shell();
	}
protected void buildUI(){
	shell.setLayout(new GridLayout(2,false));
	
	Label label = new Label(shell,SWT.NONE);
	label.setText("Entrez un texte à compléter : ");
	
	text = new Text(shell,SWT.BORDER);
	GridData gd = new GridData(SWT.FILL, SWT.CENTER, true, false);
	text.setLayoutData(gd);
	text.pack();
	
	deco = new ControlDecoration(text,SWT.TOP | SWT.LEFT);
	Image image = FieldDecorationRegistry.getDefault().getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage();
	deco.setDescriptionText("CTRL+ESPACE pour auto complétion ...");
	deco.setImage(image);
	deco.setShowOnlyOnFocus(false);
	
	shell.setSize(300,60);
	addListeners();
	
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

private void addListeners(){
	
	text.addModifyListener(new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e){
			Text text = (Text) e.getSource();
			if(!text.getText().isEmpty()){
				deco.hide();
			}
			else {
				deco.show();
			}
		}
	});
	
	char[] autoCompleteCharacters = new char[] {'.','#'};
	KeyStroke keyStroke;
	
	try{
		keyStroke = KeyStroke.getInstance("Ctrl+Space");
		ContentProposalAdapter adapter = new ContentProposalAdapter(text, new TextContentAdapter(), new SimpleContentProposalProvider(new String[] { "option1", "option2", "option3"}),keyStroke,autoCompleteCharacters);
	} catch(ParseException ex) {
		ex.printStackTrace();
			}
		}

public static void main(String[] args) {
	new Test01FieldAssist();
}
}
