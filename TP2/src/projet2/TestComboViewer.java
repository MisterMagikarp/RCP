package projet2;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import Test03TableViewer.Person;

public class TestComboViewer {

	protected Display display;
	protected Shell shell;
	protected ComboViewer viewer;
	
	public TestComboViewer(){
		init();
		buildUI();
		addListeners();
	
	viewer.setContentProvider(ArrayContentProvider.getInstance());
	Person[] persons = new Person[] {
			new Person("Clark","Kent"),
			new Person("Bruce","Banner"),
			new Person("Peter","Parker")};
	viewer.setInput(persons);
	
	}

	protected void init(){
		display = new Display();
		shell = new Shell(display);
}
	protected void buildUI(){
		shell.setLayout(new FormLayout());
		FormData fd;
		
		int margin = 10;
		
		Label label = new Label(shell,SWT.NONE);
		label.setText("Selectionnez un prénom: ");
		fd=new FormData();
		fd.top = new FormAttachment(0,margin);
		fd.left= new FormAttachment(0,margin);
		label.setLayoutData(fd);
		
		viewer = new ComboViewer(shell, SWT.READ_ONLY);
		viewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element){
				if (element instanceof Person){
					Person person = (Person) element;
					return person.getFirstName();
				}
				return super.getText(element);
			}
		});
		
		fd=new FormData();
		fd.top = new FormAttachment(label,0,SWT.CENTER);
		fd.left = new FormAttachment(label,0);
		fd.right = new FormAttachment(100,-margin);
		viewer.getCombo().setLayoutData(fd);
		
		shell.setSize(300,60);


Rectangle screenSize = display.getPrimaryMonitor().getBounds();
shell.setLocation((screenSize.width - shell.getBounds().width) / 2, (screenSize.height - shell.getBounds().height) / 2);


	}
	
	private void addListeners(){
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if(selection.size()>0){
					MessageBox box = new MessageBox(shell, SWT.OK);
					String nom = ((Person) selection.getFirstElement()).getLastName();
					box.setMessage("Vous avez sélectionné Mr" + nom);
					box.open();
				}
			}
		});}
		
		
		protected void startEventLoop(){
			shell.open();
			while(!shell.isDisposed()){
				if(!display.readAndDispatch()){
					display.sleep();
					}
				}
		}
		
		public static void main(String[] args) {
			new TestComboViewer().startEventLoop();
		}
		
		
	}
