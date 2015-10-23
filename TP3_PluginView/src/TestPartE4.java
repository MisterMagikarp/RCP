 

import javax.inject.Inject;
import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class TestPartE4 {
	
	private TableViewer viewer;
	
	@Inject
	public TestPartE4() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider());
		viewer.setInput(new String[] {"One","Two","Three"});
	}
	
	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
		System.out.println("TestPartE4.setFocus()");
	}
	
	
	
	
}