package Test03TableViewer;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;


public class View6EditingPrenomSupport extends EditingSupport {
	
	private TableViewer viewer;
	private TextCellEditor editor;
	
	public View6EditingPrenomSupport(TableViewer viewer){
		super(viewer);
		this.viewer = viewer;
		this.editor = new TextCellEditor(viewer.getTable());
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
		return ((Etudiant)o).getPrenom();
	}
	
	@Override
	protected void setValue(Object o, Object value){
		((Etudiant)o).setPrenom(String.valueOf(value));
		viewer.update(o, null);
	}

}
