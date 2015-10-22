package tp1;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

public class Test03GridLayout extends Test01SWT {
	
	protected void buildUI(){
		
		GridLayout layout = new GridLayout(2,false);
		shell.setLayout(layout);
		
		Label label = new Label(shell,SWT.NONE);
		label.setText("Un label");
		Button button = new Button(shell, SWT.PUSH);
		button.setText("Cliquez ici");
		
		
		label = new Label(shell, SWT.BORDER);
		label.setText("Un label sur 2 colonnes");
		GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
		label.setLayoutData(data);
		
		label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		data = new GridData(SWT.FILL, SWT.TOP, true, false);
		data.horizontalSpan = 2;
		label.setLayoutData(data);
		
		Spinner spinner = new Spinner(shell, SWT.ALL);
		spinner.setMinimum(0);
		spinner.setMaximum(1000);
		spinner.setSelection(500);
		spinner.setIncrement(1);
		spinner.setPageIncrement(100);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		gridData.heightHint = SWT.DEFAULT;
		gridData.horizontalSpan = 2;
		spinner.setLayoutData(gridData);
		
		Composite composite = new Composite(shell,SWT.BORDER);
		gridData = new GridData(SWT.FILL,SWT.FILL,true,false);
		gridData.horizontalSpan = 2;
		composite.setLayoutData(gridData);
		composite.setLayout(new GridLayout(1,false));
		
		Text txt1 = new Text(composite,SWT.NONE);
		txt1.setText("test de texte 1");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		txt1.setLayoutData(gridData);
		
		Text txt2 = new Text(composite, SWT.NONE);
		txt2.setText("test de test 2");
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("Groupe de widgets");
		gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
		
		gridData.horizontalSpan = 2;
		group.setLayoutData(gridData);
		group.setLayout(new RowLayout(SWT.VERTICAL));
		
		Text txt3 = new Text(group, SWT.NONE);
		txt3.setText("texte numero 3");
		
		
		shell.pack();
	}
	
	public static void main(String[] args){
		new Test03GridLayout().startEventLoop();
	}

}
