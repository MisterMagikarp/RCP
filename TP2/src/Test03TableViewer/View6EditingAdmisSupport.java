package Test03TableViewer;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;

public class View6EditingAdmisSupport extends EditingSupport {
	
	private TableViewer viewer;
	private CheckboxCellEditor editor;
	
	public View6EditingAdmisSupport(TableViewer viewer){
		super(viewer);
		this.viewer = viewer;
		this.editor = new CheckboxCellEditor(viewer.getTable());
	}
	
	@Override
	protected boolean canEdit(Object o){
		return true;
	}
	
	@Override 
	protected CellEditor getCellEditor(Object o){
		return editor;
	}
	
	@Override
	protected Object getValue(Object o){
		return ((Etudiant)o).isAdmis();
	}

	
	@Override
	protected void setValue(Object o, Object value){
		((Etudiant)o).setAdmis((boolean)value);
		viewer.update(o, null);
	}

}