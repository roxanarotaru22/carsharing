package carsharing;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;

public class Aggiungi {

	protected Shell shell;
	private Avvio avvio;
	private Text auto;
	private Text socio;
	private Text restituita;
	Database database;
	ArrayList<Auto> auto1 ;
	String inizio1;
	String fine1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Aggiungi window = new Aggiungi(null,null);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Aggiungi(Avvio avvio,Database database){
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
		shell.setSize(536, 300);
		shell.setText("SWT Application");
		
		auto = new Text(shell, SWT.BORDER);
		auto.setBounds(10, 22, 76, 21);
		
		socio = new Text(shell, SWT.BORDER);
		socio.setBounds(10, 68, 76, 21);
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(171, 21, 339, 165);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(10, 1, 55, 15);
		lblNewLabel.setText("Auto");
		
		Label lblSocio = new Label(shell, SWT.NONE);
		lblSocio.setBounds(10, 47, 55, 15);
		lblSocio.setText("Socio");
		
		DateTime inizio = new DateTime(shell, SWT.BORDER);
		inizio.setBounds(10, 113, 80, 24);
		
		DateTime fine = new DateTime(shell, SWT.BORDER);
		fine.setBounds(6, 162, 80, 24);
		
		Label lblInizio = new Label(shell, SWT.NONE);
		lblInizio.setBounds(10, 95, 55, 15);
		lblInizio.setText("inizio");
		
		Label lblFine = new Label(shell, SWT.NONE);
		lblFine.setBounds(10, 143, 55, 15);
		lblFine.setText("fine");
		
		Label lblRestituita = new Label(shell, SWT.NONE);
		lblRestituita.setBounds(10, 203, 55, 15);
		lblRestituita.setText("restituita");
		
		restituita = new Text(shell, SWT.BORDER);
		restituita.setBounds(6, 231, 76, 21);
		
		Button btnAggiungi = new Button(shell, SWT.NONE);
		btnAggiungi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e0) {
				Connection cn;
				Statement st;
				ResultSet rs;
				String sql;
				String codice_noleggio1;
				String auto1;
				String socio1;
				
				String auto_restituita1;
				
				
				
	


				//	codice_noleggio1 = codice_noleggio.getText();
					auto1 = auto.getText();
					socio1 = socio.getText();
					inizio1 = inizio.getYear() + "-" +(inizio.getMonth()+1) + "-" + inizio.getDay();
					fine1 = fine.getYear() + "-" +(fine.getMonth()+1) + "-" + fine.getDay();
					
				
				
				try {
					database.AggiungiNoleggio(auto1,socio1,inizio1,fine1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
					
				
			}
		});
		btnAggiungi.setBounds(171, 231, 75, 25);
		btnAggiungi.setText("aggiungi");
		
		
		
		Label lblAuto = new Label(shell, SWT.NONE);
		lblAuto.setBounds(165, 1, 55, 15);
		lblAuto.setText("Auto");
		
		Button btnCaricaAuto = new Button(shell, SWT.NONE);
		btnCaricaAuto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e0) {
				list.removeAll();
				try {
					inizio1 = inizio.getYear() + "-" +(inizio.getMonth()+1) + "-" + inizio.getDay();
					fine1 = fine.getYear() + "-" +(fine.getMonth()+1) + "-" + fine.getDay();
					auto1 = database.CaricaAuto(inizio1,fine1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for(int i=0;i<auto1.size();i++){
				list.add(auto1.get(i).targa);
				}
			}
		});
		btnCaricaAuto.setBounds(171, 192, 75, 25);
		btnCaricaAuto.setText("Carica auto");
		
		
	}
}
