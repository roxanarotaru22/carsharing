package carsharing;

import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class Elimina {

	protected Shell shlEliminaNoleggio;

	private Avvio avvio;
	private Text auto;
	private Text socio;
	private Text restituita;
	Database database;
	
	ArrayList <Auto> a;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Elimina window = new Elimina(null,null);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Elimina(Avvio avvio,Database database){
		this.avvio = avvio;
		this.database = database;
	}
	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlEliminaNoleggio.open();
		shlEliminaNoleggio.layout();
		while (!shlEliminaNoleggio.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEliminaNoleggio = new Shell();
		shlEliminaNoleggio.setImage(SWTResourceManager.getImage(Elimina.class, "/carsharing/noleggio-ore.jpg"));
		shlEliminaNoleggio.setBackground(SWTResourceManager.getColor(211, 211, 211));
		shlEliminaNoleggio.setSize(247, 300);
		shlEliminaNoleggio.setText("Elimina noleggio");
		List list = new List(shlEliminaNoleggio, SWT.NONE);
		list.setBackground(SWTResourceManager.getColor(169, 169, 169));
		list.setBounds(46, 63, 130, 153);

		Button btnNewButton = new Button(shlEliminaNoleggio, SWT.NONE);
		btnNewButton.setForeground(SWTResourceManager.getColor(255, 0, 0));
		btnNewButton.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 12, SWT.NORMAL));
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					 database.Elimina(a.get(list.getSelectionIndex()).getTarga());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MessageDialog.openInformation(shlEliminaNoleggio, "Avviso" , "Ai seguenti soci con la seguente auto "+a.get(list.getSelectionIndex()).getTarga()+" è stato tolto il noleggio");
				list.removeAll();
				a = new ArrayList<Auto>();
				
				try {
					a = database.CaricaAuto2();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				for(int i=0;i<a.size();i++){
				list.add(a.get(i).getTarga());
				}
			}
			
		});
		btnNewButton.setBounds(146, 222, 75, 25);
		btnNewButton.setText("Elimina");
		
		Button btnCaricaNoleggi = new Button(shlEliminaNoleggio, SWT.NONE);
		btnCaricaNoleggi.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		btnCaricaNoleggi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e0) {
				a = new ArrayList<Auto>();
				
				try {
					a = database.CaricaAuto2();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0;i<a.size();i++){
				list.add(a.get(i).getTarga());
				}
			}
			
		});
		btnCaricaNoleggi.setBounds(10, 223, 130, 25);
		btnCaricaNoleggi.setText("Carica noleggi");
		
		Label lblAutoInNoleggio = new Label(shlEliminaNoleggio, SWT.NONE);
		lblAutoInNoleggio.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblAutoInNoleggio.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblAutoInNoleggio.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblAutoInNoleggio.setBounds(43, 26, 143, 31);
		lblAutoInNoleggio.setText("Auto in noleggio:");
		
	

	}
}
