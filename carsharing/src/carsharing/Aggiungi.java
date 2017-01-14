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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Aggiungi {

	protected Shell shlAggiungiNoleggio;
	private Avvio avvio;
	Database database;
	ArrayList<Auto> auto1 ;
	ArrayList<Socio> socio1 ;
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
		shlAggiungiNoleggio.open();
		shlAggiungiNoleggio.layout();
		while (!shlAggiungiNoleggio.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlAggiungiNoleggio = new Shell();
		shlAggiungiNoleggio.setImage(SWTResourceManager.getImage(Aggiungi.class, "/carsharing/noleggio-ore.jpg"));
		shlAggiungiNoleggio.setSize(536, 300);
		shlAggiungiNoleggio.setText("Aggiungi noleggio");
		
		List list = new List(shlAggiungiNoleggio, SWT.BORDER);
		list.setBounds(174, 56, 101, 122);
		
		Label lblNewLabel = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblNewLabel.setBounds(10, 10, 55, 15);
		lblNewLabel.setText("Auto");
		
		Label lblSocio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblSocio.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblSocio.setBounds(10, 59, 55, 15);
		lblSocio.setText("Socio");
		
		DateTime inizio = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		inizio.setBounds(10, 141, 80, 24);
		
		DateTime fine = new DateTime(shlAggiungiNoleggio, SWT.BORDER);
		fine.setBounds(10, 207, 80, 24);
		
		Label lblInizio = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblInizio.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblInizio.setBounds(10, 120, 55, 15);
		lblInizio.setText("Inizio");
		
		Label lblFine = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblFine.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblFine.setBounds(10, 186, 55, 15);
		lblFine.setText("Fine");
		
		Combo combo = new Combo(shlAggiungiNoleggio, SWT.NONE);
		combo.setBounds(10, 30, 76, 23);
		
		Combo combo_1 = new Combo(shlAggiungiNoleggio, SWT.NONE);
		combo_1.setBounds(10, 80, 80, 23);
		//auto1 = new ArrayList<Auto>();
		/*try {
			inizio1 = inizio.getYear() + "-" +(inizio.getMonth()+1) + "-" + inizio.getDay();
			fine1 = fine.getYear() + "-" +(fine.getMonth()+1) + "-" + fine.getDay();
			auto1 = database.CaricaAuto(inizio1,fine1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<auto1.size();i++){
			
		list.add(auto1.get(i).targa);
		} */
		
		try {
			socio1 = database.CaricaSoci();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<socio1.size();i++){
		combo_1.add(socio1.get(i).cf);
		}
		
		try {
			auto1 = database.CaricaAuto2();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<auto1.size();i++){
		combo.add(auto1.get(i).targa);
		}
		
		Button btnAggiungi = new Button(shlAggiungiNoleggio, SWT.NONE);
		btnAggiungi.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
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
					auto1 = combo.getText();
				
					socio1 = combo_1.getText();
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
		btnAggiungi.setBounds(329, 91, 151, 44);
		btnAggiungi.setText(" Aggiungi noleggio");
		
		
		
		Label lblAuto = new Label(shlAggiungiNoleggio, SWT.NONE);
		lblAuto.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblAuto.setBounds(174, 35, 151, 15);
		lblAuto.setText("Auto disponibili:");
		
		Button btnCaricaAuto = new Button(shlAggiungiNoleggio, SWT.NONE);
		btnCaricaAuto.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
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
				combo.removeAll();
				for(int i=0;i<auto1.size();i++){
				list.add(auto1.get(i).targa);
				combo.add(auto1.get(i).targa);
				}
			}
		});
		btnCaricaAuto.setBounds(174, 183, 101, 25);
		btnCaricaAuto.setText("Carica auto");
		
		
		
	
		
		
	}
}
