package tp1;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;


public class Test06Image extends Test01SWT{

	protected void buildUI(){
		
		shell.setLayout(new RowLayout());
		shell.setText("Photo Application");
		
		Composite parent = new Composite(shell,SWT.NONE);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		parent.setLayout(gridLayout);
		
		List<Image> imageList = new ArrayList<Image>();
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_WARNING));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_WORKING));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_QUESTION));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_INFORMATION));
		imageList.add(Display.getDefault().getSystemImage(SWT.ICON_ERROR));
		
		for (Image image : imageList){
			Label label = new Label(parent,SWT.NONE);
			label.setImage(image);
		}
		
		shell.pack();
		
	}
	
	public static void main(String[] args){
		new Test06Image().startEventLoop();
	}
}
