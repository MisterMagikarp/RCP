package Test03TableViewer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class View {

	private Display display;
	private Shell shell;
	private Image IMAGE_CHECK;
	private Image IMAGE_UNCHECK;
	private TableViewer viewer;
	private Button bouton;
	public View(){
		init();
		buildUI();

	}
	private HashMap <String , ViewerComparator> sorters;
	protected void init(){
		display = new Display();
		shell = new Shell(display);

		IMAGE_CHECK = getImage("12077488_757489774350453_375702691_n.jpg");

		IMAGE_UNCHECK = getImage("12086883_757489757683788_330762523_n.jpg");

	}
	
	private void addFilter(){
		ViewerFilter filter = new ViewerFilter(){ // appelé à chaque refresh()
			public boolean select(Viewer viewer, Object parent, Object elem){
				String search = searchText.getText().toLowerCase();
				Etudiant  e =(Etudiant)elem;
				return e.getPrenom().toLowerCase().contains(search) || e.getNom().toLowerCase().contains(search);
			}
		};
		viewer.addFilter(filter);
	}
 private Text searchText;
	protected void buildUI(){
		shell.setLayout(new GridLayout(2, false));

		Label searchLabel = new Label(shell, SWT.NONE);
		searchLabel.setText("Rechercher : ");

		 searchText = new Text(shell, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));

		searchText.addKeyListener(new KeyListener() {
			
			
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				viewer.refresh();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		bouton= new Button(shell, SWT.PUSH);
		bouton.setText("Go");
		bouton.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				switch (e.type) {
				case SWT.Selection:
					Etudiant etu = ModelProvider.INSTANCE.getEtudiants().get(0);
					etu.setAdmis(false); 


					break;
				}
			}
		});
		createViewer(shell);
		addFilter();
		createSorters();
		addViewerListener();
		shell.pack();

	}



	private void createViewer(Composite parent) {
		viewer = new TableViewer(
				parent,
				SWT.MULTI | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		//viewer.setInput(ModelProvider.INSTANCE.getEtudiants());
		List<Etudiant> etudiants = ModelProvider.INSTANCE.getEtudiants();		        	
		viewer.setInput(etudiants);
		for(Etudiant et : etudiants){
			et.addPropertyChangeListener(new PropertyChangeListener(){   	
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					viewer.refresh();						
				}		    		   
			}); 		    	   


			et.addPropertyChangeListener(Etudiant.ADMIS_PROP, new PropertyChangeListener(){

				@Override
				public void propertyChange(PropertyChangeEvent evt) {

					MessageBox  box = new MessageBox(shell, SWT.OK);
					Etudiant etu = (Etudiant) evt.getSource();
					String msg = etu.getPrenom() +" "+ etu.getNom() + " a été ";
					if( ((Boolean)evt.getNewValue()).booleanValue()){
						msg +="admis";
					}else{
						msg += "recalé";
					}
					box.setMessage( msg);
					box.open(); 
				}

			});
		}
	


		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		viewer.getControl().setLayoutData(gridData);

	}
		private void addViewerListener(){
			viewer.addDoubleClickListener(new IDoubleClickListener()
			{
				@Override
				public void doubleClick(DoubleClickEvent event) {
					try {

						IStructuredSelection selection= (IStructuredSelection) event.getSelection();
						Etudiant etudiant = (Etudiant) selection.getFirstElement();
						etudiant.setAdmis(!etudiant.isAdmis());

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			TableColumn[] columns = viewer.getTable().getColumns();
			for(TableColumn c : columns){
				c.addListener(SWT.Selection, new Listener(){
					public void handleEvent(Event evt){
						TableColumn tc = (TableColumn)evt.widget;
						String cTitle = tc.getText();
						if(sorters.keySet().contains(cTitle)){
							viewer.setComparator(sorters.get(cTitle));
						}
					}
				});
			}
		}

	private void createSorters(){
		sorters = new HashMap<>();
		sorters.put(titles[0], new ViewerComparator(){
			public int compare(Viewer viewer, Object e1, Object e2){
				String s1, s2;
				s1 = ((Etudiant)e1).getPrenom().toLowerCase();
				s2 = ((Etudiant)e2).getPrenom().toLowerCase();
				return s1.compareTo(s2);
			}
		});
		sorters.put(titles[1], new ViewerComparator(){
			public int compare(Viewer viewer, Object e1, Object e2){
				String s1,s2;
				s1 =((Etudiant)e1).getNom().toLowerCase();
				s2 =((Etudiant)e2).getNom().toLowerCase();
				return s1.compareTo(s2);
			}
		});
	}

	@SuppressWarnings("unused")
	private void addSorter(final String title){
		viewer.setComparator(new ViewerComparator(){
			public int compare(Viewer viewer, Object e1, Object e2){
				if(title.equals("Nom")){
					String nom1, nom2;
					nom1 = ((Etudiant)e1).getNom().toLowerCase();
					nom2 = ((Etudiant)e2).getNom().toLowerCase();
					return nom1.compareTo(nom2);}
				else if(title.equals("Prenom")){
					String Prenom1, Prenom2;
					Prenom1 = ((Etudiant)e1).getPrenom().toLowerCase();
					Prenom2 = ((Etudiant)e2).getPrenom().toLowerCase();
					return Prenom1.compareTo(Prenom2);
				}
				else if(title.equals("Admis")){
					Boolean Prenom1 = new Boolean(((Etudiant)e1).isAdmis());
					Boolean Prenom2 = new Boolean(((Etudiant)e2).isAdmis());				
					return Prenom1.compareTo(Prenom2);
				}
				return 0;
			}

		});
	}


	public TableViewer getViewer(){
		return viewer;
	}


	String[] titles ={"Prenom", "Nom", "Admis"};
	private void createColumns(final Composite parent, final TableViewer viewer){
		
		int[] bounds ={400, 250, 50};

		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				Etudiant p = (Etudiant) element;
				return p.getPrenom();
			}
		});

		col.setEditingSupport(new View6EditingPrenomSupport(viewer)); // ajout d'un support à l'édition
		
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				Etudiant p = (Etudiant) element;
				return p.getNom();
			}
		});
		
		col.setEditingSupport(new View6EditingNomSupport(viewer));

		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider(){
			public String getText(Object element){
				return null;
			}


			public Image getImage(Object element){
				if(((Etudiant) element).isAdmis()){
					return IMAGE_CHECK;				
				}else{
					return IMAGE_UNCHECK;
				}
			}
		});
		
		col.setEditingSupport(new View6EditingAdmisSupport(viewer));
	}



	private Image getImage(String string) {	
		String path= System.getProperty("user.dir")+"/images/";
		return new Image(display, path + string);

	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber){
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);	
		column.setResizable(true);
		column.setMoveable(true);

		/*column.addSelectionListener(new SelectionListener() { 

			public void widgetSelected(SelectionEvent e) {
				// TODO Auto-generated method stub 
				System.out.println(title); 
				addSorter(title);
			} 

			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
			} 
		});*/


		return viewerColumn;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new View().startEventLoop();
	}

}