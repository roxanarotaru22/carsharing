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

public class Elimina {

	protected Shell shell;

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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		List list = new List(shell, SWT.BORDER);
		list.setBounds(10, 33, 104, 171);

		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					 database.Elimina(a.get(list.getSelectionIndex()).getTarga());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				MessageDialog.openInformation(shell, "Avviso" , "Ai seguenti soci con la seguente auto "+a.get(list.getSelectionIndex()).getTarga()+" è stato tolto il noleggio");
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
		btnNewButton.setBounds(120, 179, 75, 25);
		btnNewButton.setText("Elimina");
		
		Button btnCaricaNoleggi = new Button(shell, SWT.NONE);
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
		btnCaricaNoleggi.setBounds(120, 148, 117, 25);
		btnCaricaNoleggi.setText("Carica noleggi");
		
	

	}
}
