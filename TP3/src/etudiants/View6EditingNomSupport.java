package etudiants;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;

public class View6EditingNomSupport  extends EditingSupport {
	
	private TableViewer viewer;
	private TextCellEditor editor;
	
	public View6EditingNomSupport(TableViewer viewer){
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
		return ((Etudiant)o).getNom();
	}
	
	@Override
	protected void setValue(Object o, Object value){
		((Etudiant)o).setNom(String.valueOf(value));
		viewer.update(o, null);
	}

}
