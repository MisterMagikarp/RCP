import javax.inject.Inject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.launch.Framework;

import etudiants.Etudiant;
import etudiants.ModelProvider;
import etudiants.View6EditingAdmisSupport;
import etudiants.View6EditingNomSupport;
import etudiants.View6EditingPrenomSupport;

public class TestPartE4 {

	private TableViewer viewer;
	private Image IMAGE_CHECK;
	private Image IMAGE_UNCHECK;
	private String imageDir = "images/";
	Text searchText;
	private String[] titles = {"Prenom", "Nom", "Admis"};
	private HashMap<String,ViewerComparator> sorters;

	@Inject
	public TestPartE4() {

	}

	@PostConstruct
	public void postConstruct(Composite parent) throws IOException {
		
		parent.setLayout(new GridLayout(3, false));
		
		Label searchLabel = new Label(parent, SWT.NONE);
		searchLabel.setText("Rechercher : ");
		
		searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
		
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		viewer.setContentProvider(new ArrayContentProvider());
		List<Etudiant> etudiants = ModelProvider.INSTANCE.getEtudiants();
		viewer.setInput(etudiants);
		
		for (Etudiant etu: etudiants) {
			etu.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent e) {
					viewer.refresh();
				}
			});
			
			etu.addPropertyChangeListener(Etudiant.ADMIS_PROP, new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent evt) {
					String msg = evt.getSource() + "a été " ;
					if (((Boolean)evt.getNewValue()).booleanValue() ){
						msg += "admis";
					} else {
						msg += "recalé";
					}
					showMessage(msg);
					}
			});
		}
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = gridData.FILL;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = gridData.FILL;
		viewer.getControl().setLayoutData(gridData);
		
		IMAGE_CHECK = getImage("12077488_757489774350453_375702691_n.jpg");
		IMAGE_UNCHECK = getImage("12086883_757489757683788_330762523_n.jpg");
		
		createSorters();
		addViewerListener();
		addFilter();
	}

	@Focus
	public void setFocus() {
		viewer.getControl().setFocus();
		System.out.println("TestPartE4.setFocus()");
	}
	
	
	
	private void createColumns(final Composite parent, final TableViewer viewer) {
		String[] titles = { "Prenom", "Nom", "Admis" };
		int[] bounds =  { 150, 100, 40 };
		
		TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Etudiant p = (Etudiant) element;
				return p.getPrenom();
			}
		});
		col.setEditingSupport(new View6EditingPrenomSupport(viewer));
		
		col = createTableViewerColumn(titles[1], bounds[1], 1);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Etudiant p = (Etudiant) element;
				return p.getNom();
			}
		});
		col.setEditingSupport(new View6EditingNomSupport(viewer));
		
		col = createTableViewerColumn(titles[2], bounds[2], 2);
		col.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				return null;
			}
			
			@Override
			public Image getImage(Object element) {
				if(((Etudiant) element).isAdmis()) {
					return IMAGE_CHECK;
				} else {
					return IMAGE_UNCHECK;
				}
			}
		});
		col.setEditingSupport(new View6EditingAdmisSupport(viewer));
	}
	
	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		return viewerColumn;
	}


	private Image getImage(String imageName) throws IOException {
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(FileLocator
				.find(FrameworkUtil.getBundle(getClass()), new Path(imageDir + imageName), null));
		return descriptor.createImage();
	}
	
	private void addFilter() {
		ViewerFilter filter = new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				String search = searchText.getText().toLowerCase();
				Etudiant e = (Etudiant)element;
				return e.getPrenom().toLowerCase().contains(search) || e.getNom().toLowerCase().contains(search);
			}
		};
		viewer.addFilter(filter);
	}

	private void addViewerListener() {

		TableColumn[] columns = viewer.getTable().getColumns();
		for(TableColumn c : columns) {
			c.addListener(SWT.Selection, new Listener() {
				public void handleEvent(Event event) {
					TableColumn tc = (TableColumn)event.widget;
					String cTitle = tc.getText();
					if(sorters.keySet().contains(cTitle)) {
						viewer.setComparator(sorters.get(cTitle));
					}
				}
			});
		}
		searchText.addKeyListener(new KeyListener() {
			public void keyReleased(KeyEvent e) {
				viewer.refresh();
			}
			public void keyPressed(KeyEvent e) {
				
			}
		});
	}
	
	public void createSorters() {
		sorters = new HashMap<>();
		sorters.put(titles[0], new ViewerComparator() {
			public int compare(Viewer viewer, Object e1, Object e2) {
				String prenom1, prenom2;
				prenom1 = ((Etudiant)e1).getPrenom().toLowerCase();
				prenom2 = ((Etudiant)e2).getPrenom().toLowerCase();
				return prenom1.compareTo(prenom2);
			}
		});
		
		sorters.put(titles[1], new ViewerComparator() {
			public int compare(Viewer viewer, Object e1, Object e2) {
				String nom1, nom2;
				nom1 = ((Etudiant)e1).getNom().toLowerCase();
				nom2 = ((Etudiant)e2).getNom().toLowerCase();
				return nom1.compareTo(nom2);
			}
		});
	}
	
	private void showMessage(String message) {
		MessageDialog.openInformation(
			viewer.getControl().getShell(),
			"Etudiant View",
			message);
	}

}