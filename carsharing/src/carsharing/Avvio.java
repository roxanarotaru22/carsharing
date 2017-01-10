package carsharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class Avvio {

	protected Shell shell;
	Connection cn;
	List list;
	Statement st;
	ResultSet rs;
	String sql;
	String s;
	Database database;
	static Avvio window;
	ArrayList <Socio> t;
	ArrayList <Noleggio> n;
	ArrayList <Auto> a;
	String inizio;
	String fine;
	Aggiungi aggiungi;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			window = new Avvio();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		
		DateTime dateTime_inizio = new DateTime(shell, SWT.BORDER);
		dateTime_inizio.setBounds(67, 209, 80, 24);
		
		DateTime dateTime_fine = new DateTime(shell, SWT.BORDER);
		dateTime_fine.setBounds(67, 238, 80, 24);
		
		Button btnInvia = new Button(shell, SWT.NONE);
		btnInvia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				inizio = (dateTime_inizio.getYear()+"-" + (dateTime_inizio.getMonth()+1) + "-" + dateTime_inizio.getDay());
				fine = (dateTime_fine.getYear() +"-" + (dateTime_fine.getMonth()+1) + "-" + dateTime_fine.getDay());
				System.out.println(inizio);
			}
		});
		
		List list_2 = new List(shell, SWT.BORDER);
		list_2.setBounds(126, 31, 251, 53);
		List list_1 = new List(shell, SWT.BORDER);
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				System.out.println("oggetto selezionato");
				inizio = dateTime_inizio.getYear() + "-" +(dateTime_inizio.getMonth()+1) + "-" + dateTime_inizio.getDay();
				fine = dateTime_fine.getYear() + "-" +(dateTime_fine.getMonth()+1) + "-" + dateTime_fine.getDay();
				try {
					n = database.CaricaNoleggio(t.get(list_1.getSelectionIndex()).getCf(), inizio, fine);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for(int i=0; i<n.size(); i++){
					list_2.add(n.get(i).getAuto() + " - " + n.get(i).getInizio() + " - " + n.get(i).getFine());
				}	
			}
		});
		list_1.setBounds(10, 31, 100, 138);
	
		
		
		
	
	
		Label lblSoci = new Label(shell, SWT.NONE);
		lblSoci.setBounds(10, 10, 55, 15);
		lblSoci.setText("soci");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e0) {
				t = new ArrayList<Socio>();
				
				try {
					t = database.CaricaSoci();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0;i<t.size();i++){
				list_1.add(t.get(i).getCognome());
				}
			}
			
		});
		btnNewButton.setBounds(10, 178, 75, 25);
		btnNewButton.setText("carica soci");
		
		
		
		Label lblAutoNoleggiate = new Label(shell, SWT.NONE);
		lblAutoNoleggiate.setBounds(130, 10, 118, 15);
		lblAutoNoleggiate.setText("auto noleggiate");
		
		
		
		Label lblInizio = new Label(shell, SWT.NONE);
		lblInizio.setBounds(10, 218, 55, 15);
		lblInizio.setText("Inizio");
		
		Label lblFine = new Label(shell, SWT.NONE);
		lblFine.setBounds(10, 247, 55, 15);
		lblFine.setText("Fine");
		
		
		btnInvia.setBounds(155, 238, 55, 25);
		btnInvia.setText("Invia");
		
		Button btnAggiungiNoleggio = new Button(shell, SWT.NONE);
		btnAggiungiNoleggio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aggiungi =  new Aggiungi(window,database);
				aggiungi.open();
			}
		});
		btnAggiungiNoleggio.setBounds(126, 126, 111, 25);
		btnAggiungiNoleggio.setText("Aggiungi noleggio");
		
		List list_3 = new List(shell, SWT.BORDER);
		list_3.setBounds(306, 117, 111, 116);
		
		Label lblAuto = new Label(shell, SWT.NONE);
		lblAuto.setBounds(305, 96, 55, 15);
		lblAuto.setText("auto");
		
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
				list_3.add(a.get(i).getTarga());
				}
			}
			
		});
		btnCaricaNoleggi.setBounds(302, 237, 122, 25);
		btnCaricaNoleggi.setText("Carica Noleggi");
		

		database = new Database(window);

	}
}

