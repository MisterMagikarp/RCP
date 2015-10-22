package tp1_11;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class SkillBar {

	protected Display display;
	protected Shell shell;
	
	public SkillBar(){
		init();
		buildUI();
	}
	
	protected void buildUI(){
		shell.setSize(520,200);
		shell.setLayout(new RowLayout());
		shell.setText("GW2 Mesmer Greatsword Skill Bar");
		
		Composite parent = new Composite(shell, SWT.NONE);
		GridLayout gridLayout =  new GridLayout();
		gridLayout.numColumns = 5;
		parent.setLayout(gridLayout);
		
		
		String path = System.getProperty("user.dir") + "/images/";
		String[] imgNames = new String[]{
				"Illusionary_Wave.png",
				"Mind_Stab.png",
				"Mirror_Blade.png",
				"Phantasmal_Berserker.png",
				"Spatial_Surge.png"
		};
		
		for(int i=0; i< imgNames.length; i++){
			Label label = new Label(parent, SWT.NONE);
			Image img = new Image(display, path + imgNames[i]);
			label.setImage(img);
			
			DragSource source = new DragSource(label, DND.DROP_NONE);
			source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
			source.addDragListener(new MyDragSourceListener(parent,source));
			
			DropTarget target = new DropTarget(label, DND.DROP_NONE);
			target.setTransfer(new Transfer[] { TextTransfer.getInstance() });
			target.addDropListener(new MyDropTargetListener(parent,target));
		}
	}
	
	protected void init(){
		display = new Display();
		shell = new Shell(display);
	}
	
	
	protected void startEventLoop(){
		shell.pack();
		shell.open();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch()){
				display.sleep();
			}
		}
		display.dispose();
	}
	
	public static void main(String[] args) {
		new SkillBar().startEventLoop();
	}
	
}
