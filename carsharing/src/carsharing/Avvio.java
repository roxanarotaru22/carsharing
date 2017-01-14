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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class Avvio {

	protected Shell shlCarSharing;
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
	Elimina elimina;
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
		shlCarSharing.open();
		shlCarSharing.layout();
		while (!shlCarSharing.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCarSharing = new Shell();
		shlCarSharing.setImage(SWTResourceManager.getImage(Avvio.class, "/carsharing/noleggio-ore.jpg"));
		shlCarSharing.setBackground(SWTResourceManager.getColor(211, 211, 211));
		shlCarSharing.setSize(592, 431);
		shlCarSharing.setText("Car Sharing");
		
		DateTime dateTime_inizio = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime_inizio.setBounds(338, 145, 80, 24);
		
		DateTime dateTime_fine = new DateTime(shlCarSharing, SWT.BORDER);
		dateTime_fine.setBackground(SWTResourceManager.getColor(211, 211, 211));
		dateTime_fine.setBounds(338, 175, 80, 24);
		
		Button btnInvia = new Button(shlCarSharing, SWT.NONE);
		btnInvia.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		btnInvia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				inizio = (dateTime_inizio.getYear()+"-" + (dateTime_inizio.getMonth()+1) + "-" + dateTime_inizio.getDay());
				fine = (dateTime_fine.getYear() +"-" + (dateTime_fine.getMonth()+1) + "-" + dateTime_fine.getDay());
				System.out.println(inizio);
			}
		});
		
		List list_2 = new List(shlCarSharing, SWT.BORDER);
		list_2.setBounds(10, 268, 251, 53);
		List list_1 = new List(shlCarSharing, SWT.BORDER);
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
		list_1.setBounds(10, 83, 100, 106);
	
		
		
		
	
	
		Label lblSoci = new Label(shlCarSharing, SWT.NONE);
		lblSoci.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblSoci.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 12, SWT.NORMAL));
		lblSoci.setBounds(10, 62, 55, 15);
		lblSoci.setText("SOCI :");
		
		Button btnNewButton = new Button(shlCarSharing, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
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
				list_1.add(t.get(i).getCognome()+" "+t.get(i).getNome());
				}
			}
			
		});
		btnNewButton.setBounds(10, 195, 100, 33);
		btnNewButton.setText("carica soci");
		
		
		
		Label lblAutoNoleggiate = new Label(shlCarSharing, SWT.NONE);
		lblAutoNoleggiate.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblAutoNoleggiate.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 12, SWT.NORMAL));
		lblAutoNoleggiate.setBounds(10, 234, 191, 28);
		lblAutoNoleggiate.setText("AUTO IN NOLEGGIO:");
		
		
		
		Label lblInizio = new Label(shlCarSharing, SWT.NONE);
		lblInizio.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblInizio.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblInizio.setBounds(282, 154, 50, 15);
		lblInizio.setText("Inizio:");
		
		Label lblFine = new Label(shlCarSharing, SWT.NONE);
		lblFine.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblFine.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		lblFine.setBounds(282, 184, 50, 15);
		lblFine.setText("Fine:");
		
		
		btnInvia.setBounds(444, 174, 55, 25);
		btnInvia.setText("Invia");
		
		Button btnAggiungiNoleggio = new Button(shlCarSharing, SWT.NONE);
		btnAggiungiNoleggio.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		btnAggiungiNoleggio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aggiungi =  new Aggiungi(window,database);
				aggiungi.open();
			}
		});
		btnAggiungiNoleggio.setBounds(10, 358, 154, 25);
		btnAggiungiNoleggio.setText("Aggiungi noleggio");
		
		Button btnEliminaNoleggio = new Button(shlCarSharing, SWT.NONE);
		btnEliminaNoleggio.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 11, SWT.NORMAL));
		btnEliminaNoleggio.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				elimina = new Elimina(window,database);
				elimina.open();
			}
		});
		btnEliminaNoleggio.setBounds(191, 358, 142, 25);
		btnEliminaNoleggio.setText("Elimina noleggio");
		
		Label lblInserisciIlPeriodo = new Label(shlCarSharing, SWT.NONE);
		lblInserisciIlPeriodo.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblInserisciIlPeriodo.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 12, SWT.NORMAL));
		lblInserisciIlPeriodo.setBounds(283, 114, 283, 25);
		lblInserisciIlPeriodo.setText("Inserisci il periodo del noleggio:");
		
		Label label = new Label(shlCarSharing, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 231, 414, -6);
		
		Label lblAltreOpzioni = new Label(shlCarSharing, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblAltreOpzioni.setText("Altre opzioni:");
		lblAltreOpzioni.setBounds(10, 340, 530, 0);
		
		Label label_1 = new Label(shlCarSharing, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setForeground(SWTResourceManager.getColor(255, 0, 0));
		label_1.setBackground(SWTResourceManager.getColor(211, 211, 211));
		label_1.setBounds(10, 327, 556, 15);
		
		Label lblBenvenuti = new Label(shlCarSharing, SWT.NONE);
		lblBenvenuti.setBackground(SWTResourceManager.getColor(211, 211, 211));
		lblBenvenuti.setForeground(SWTResourceManager.getColor(221, 160, 221));
		lblBenvenuti.setFont(SWTResourceManager.getFont("Tekton Pro Ext", 19, SWT.BOLD));
		lblBenvenuti.setBounds(71, 24, 450, 53);
		lblBenvenuti.setText("Benvenuti nell nostro Car Sharing");
		

		database = new Database(window);

	}
}

