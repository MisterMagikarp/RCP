package tp1_11;

import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MyDropTargetListener implements DropTargetListener{
	
	private Composite parentComposite;
	private DropTarget target;
	
	public MyDropTargetListener(Composite parentComposite, DropTarget target){
		this.parentComposite = parentComposite;
		this.target = target;
		
	}
	
	public void dragEnter(DropTargetEvent event){}
	public void dragOver(DropTargetEvent event){}
	public void dragLeave(DropTargetEvent event){}
	public void dragAccept(DropTargetEvent event){}
	public void dragOperationChanged(DropTargetEvent event){}
	
	public void drop(DropTargetEvent event){
		
		int sourceIndex = Integer.valueOf(event.data.toString());
		
		Control targetControl = target.getControl();
		int targetIndex = -1;
		for (int i=0; i< parentComposite.getChildren().length; i++){
			if (parentComposite.getChildren()[i].equals(targetControl)){
				targetIndex=i;
				break;
			}
		}
		
		Control sourceControl = parentComposite.getChildren()[sourceIndex];
		if(targetIndex == sourceIndex)
			return;
		
		if (targetIndex > sourceIndex)
			sourceControl.moveBelow(targetControl);
		else
			sourceControl.moveAbove(targetControl);
		
		parentComposite.layout();
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub
		
	}
}

