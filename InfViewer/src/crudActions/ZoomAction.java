package crudActions;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.sun.xml.internal.ws.api.pipe.ThrowableContainerPropertySet;

import gui.MyApp;
import model.Row;
import model.Table;

/**
 * Klasa {@link ZoomAction} uredjuje strane kljuceve.
 * Ona nasledjuje klasu {@link AbstractAction}
 * @see AbstractAction
 * @see HashMap
 * @see ArrayList
 *
 */

@SuppressWarnings("serial")
public class ZoomAction extends AbstractAction {
	
	private String parentTab;
	private String childTab;
	private String parentCol;
	private String childCol;
	private Connection connection;
	private PreparedStatement prep_st;
	private JTextField polje;
	private JTable tabelaStranogKljuca;
	private static ArrayList<String> koloneSlozenogKljuca = new ArrayList<>();
	private HashMap<String, Object> svaTextPolja;
	private ArrayList<String> referenciraneKoloneSlozenogKljuca = new ArrayList<>();

	/**
	 * Konstuktor sa parametrima klase {@link ZoomAction}
	 * @param parentTab
	 * @param childTab
	 * @param parentCol
	 * @param childCol
	 * @param polje
	 * @param svaTextPolja
	 */
	
	public ZoomAction(String parentTab, String childTab, String parentCol, String childCol, JTextField polje,
			HashMap<String, Object> svaTextPolja) {

		putValue(NAME, "Zoom");
		putValue(SHORT_DESCRIPTION, "Select forgein key");
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
		putValue(SMALL_ICON, new ImageIcon("src/images/rsz_zoom.png"));
		this.parentTab = parentTab;
		this.childTab = childTab;
		this.parentCol = parentCol;
		this.childCol = childCol;
		this.polje = polje;
		tabelaStranogKljuca = new JTable();
		connection = MyApp.getConnection();
		this.svaTextPolja = svaTextPolja;

	}

	/**
	 * Sluzi za dodavanje u listu u koje se cuvaju sve vrednosti referencirajucih kolona.
	 */
	
	public void napuniReferencirajuceKolone() {
		for (int i = 0; i < MyApp.getInstance().getListaStranihKljuceva().size(); i++) {
			for (int j = 0; j < koloneSlozenogKljuca.size(); j++) {
				if (MyApp.getInstance().getListaStranihKljuceva().get(i).getParentColumn()
						.equals(koloneSlozenogKljuca.get(j))
						&& MyApp.getInstance().getListaStranihKljuceva().get(i).getParentTable().equals(parentTab)
						&& MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable().equals(childTab)) {
					referenciraneKoloneSlozenogKljuca
							.add(MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn());
				}
			}
		}
	}

	/**
	 * Proverava da li postoji slozeni kljuc.
	 * @param kolona
	 * @return
	 */
	
	public boolean postoji(String kolona) {
		for (int i = 0; i < koloneSlozenogKljuca.size(); i++) {
			if (koloneSlozenogKljuca.get(i).equals(kolona)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Proverava da li roditeljska tabela ima slozen kljuc.
	 * @param parent
	 * @return
	 */
	
	public static boolean daLiJeSlozenStraniKljuc(String parent) {													
		int brojac = 0;
		Table t = MyApp.getInstance().getTableById(parent);
		for (int i = 0; i < t.getListOfColumns().size(); i++) {
			if (t.getListOfColumns().get(i).isPrimaryKey()) {
				koloneSlozenogKljuca.add(t.getListOfColumns().get(i).getIdLabelColmun());
				brojac++;
			}
		}

		if (brojac > 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Kliknite na zoom dugme da bi se otvorila tabela svih vrednosti
	 * stranog kljuca koje su tu dozvoljene, da bi se ispostovalo ogranicenje
     * stranog kljuca koje nalaze da vrednosti za ovu kolonu mogu biti samo podskup
	 * vrednosti iz roditeljske kolone. Pokriva se i slucaj slozenog stranog kljuca.
	 * @throws SQLException ako je slozen strani kljuc
	 * @throws NullPointerException
	 * @throws java.lang.IndexOutOfBoundsException
	 */
	
	@Override
	public void actionPerformed(ActionEvent arg0){
		JDialog zoomDijalog = new JDialog();
		ArrayList<ArrayList<String>> listaParova = new ArrayList<>();
		ResultSet rezultat = null;
		koloneSlozenogKljuca = new ArrayList<>();
		referenciraneKoloneSlozenogKljuca = new ArrayList<>();
		BorderLayout layout = new BorderLayout();
		zoomDijalog.setLayout(layout);
		zoomDijalog.setVisible(true);
		zoomDijalog.setTitle("Izaberite vrednost za " + childTab + " iz " + parentTab);
		zoomDijalog.setSize(500, 500);
		zoomDijalog.setLocationRelativeTo(null);
		zoomDijalog.setResizable(false);
		zoomDijalog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		ResourceBundle rb = ResourceBundle.getBundle("MessageResources.MessageResources", Locale.getDefault());
		DefaultTableModel model = new DefaultTableModel();
		tabelaStranogKljuca = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};

		tabelaStranogKljuca.setFillsViewportHeight(true);
		tabelaStranogKljuca.getTableHeader().setReorderingAllowed(false);

		JScrollPane scrollPane = new JScrollPane(tabelaStranogKljuca);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		zoomDijalog.add(scrollPane);

		JPanel donji = new JPanel();
		JButton okZoom = new JButton("Add");
		JButton cancelZoom = new JButton("Cancel");
		donji.add(okZoom);
		donji.add(cancelZoom);
		zoomDijalog.add(donji, BorderLayout.SOUTH);

		// ucitavanje svih vrednosti iz roditeljske tabele,select upit nad bazom
		// nad tom kolonom
		boolean slozenKljuc = daLiJeSlozenStraniKljuc(parentTab);

		if (!slozenKljuc) {
			model.addColumn(childCol);
			Table t = MyApp.getInstance().getTableById(parentTab);

			String upit = "SELECT * FROM ";
			String tabela = t.getIdLabelTable();
			upit += tabela;
			upit += ";";

			try {
				prep_st = connection.prepareStatement(upit);
				rezultat = prep_st.executeQuery();

				while (rezultat.next()) {
					Row row = new Row();
					row.getSadrzajPolja().add(rezultat.getString(childCol));
					model.addRow(row.getSadrzajPolja().toArray());

				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje27"));
			}

			catch (NullPointerException ee) {

			} catch (java.lang.IndexOutOfBoundsException eee) {
			}

			cancelZoom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					zoomDijalog.dispose();

				}
			});

			okZoom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int column = 0;
					int row = tabelaStranogKljuca.getSelectedRow();
					String value = tabelaStranogKljuca.getModel().getValueAt(row, column).toString();
					if (value.equals("")) {
						JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje28"));
					} else {
						polje.setText(value);
						zoomDijalog.dispose();
					}

				}
			});

		} else {// ako je slozen kljuc, naci iz roditeljske koje kolone
				// odgovaraju detetu
			napuniReferencirajuceKolone();
			for (int i = 0; i < MyApp.getInstance().getListaStranihKljuceva().size(); i++) {
				if (MyApp.getInstance().getListaStranihKljuceva().get(i).getParentTable().equals(parentTab)
						&& MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable().equals(childTab)
						&& MyApp.getInstance().getListaStranihKljuceva().get(i).getParentColumn().equals(parentCol)
						&& MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn().equals(childCol)
						&& postoji(MyApp.getInstance().getListaStranihKljuceva().get(i).getParentColumn())) {

					Table t = MyApp.getInstance().getTableById(parentTab);
					
					for (int j = 0; j < t.getListOfColumns().size(); j++) {
						model.addColumn(t.getListOfColumns().get(j).getIdLabelColmun());
					}

					String selectCommand = "SELECT * FROM " + parentTab + ";";

					try {
						PreparedStatement prep_stnovo = connection.prepareStatement(selectCommand);
						rezultat = prep_stnovo.executeQuery();
						ArrayList<String> parovi = new ArrayList<>();
						System.out.println();
						while (rezultat.next()) {

							
							for (int j = 0; j < t.getListOfColumns().size(); j++) {
								String s = rezultat.getString(t.getListOfColumns().get(j).getIdLabelColmun());
								System.out.println("KOLONE" + t.getListOfColumns().get(j).getIdLabelColmun());
								parovi.add(s);

							}
							listaParova.add(parovi);
							parovi = new ArrayList<>();
						}
					} catch (SQLException e) {
						System.out.println("ovde je problem");

					}

					int brojac = 0;
					for (int k = 0; k < listaParova.size(); k++) {
						Row row = new Row();
						for (int r = 0; r < listaParova.get(k).size(); r++) {
							row.getSadrzajPolja().add(listaParova.get(k).get(r));
							brojac++;
						
							if (brojac == t.getListOfColumns().size()) {
								model.addRow(row.getSadrzajPolja().toArray());

								brojac = 0;
							}
						}

					}

				}

			}

			dodajDugmice(zoomDijalog, cancelZoom, okZoom);

		}

	}

	/**
	 * Dodavanje dugmica na graficki interfejs.
	 * Metoda koja obezbedjuje punjenje vrednosti na GUI-ju u zavisnoti od toga koji red je selektovan u tabeli, 
	 * koja se otvorila prilikom klika na zoom dugme.
	 * @param zoomDijalog
	 * @param cancelZoom
	 * @param okZoom
	 * @throws Exception
	 */
	
	private void dodajDugmice(JDialog zoomDijalog, JButton cancelZoom, JButton okZoom) {
		cancelZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				zoomDijalog.dispose();

			}
		});

		okZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					Table t = MyApp.getInstance().getTableById(parentTab);
					int row = tabelaStranogKljuca.getSelectedRow();
					ArrayList<Integer> listaIndeksaIzTabele = mapirajIndeksePoTabeli(t);
					System.out.println("INDEKSI"+listaIndeksaIzTabele);
					int brojac = referenciraneKoloneSlozenogKljuca.size()-1;
					for (int j = 0; j < referenciraneKoloneSlozenogKljuca.size(); j++) {
						System.out.println("OVO ME ZANIMA"+referenciraneKoloneSlozenogKljuca.get(j));
					
						
						int column = listaIndeksaIzTabele.get(brojac);
						String value = tabelaStranogKljuca.getModel().getValueAt(row, column).toString();
						System.out.println("VREDNOSTI" + value);
						brojac--;
						if (value.equals("")) {
							JOptionPane.showMessageDialog(new JFrame(), MyApp.getInstance().getResourceBundle().getString("upozorenje28"));
						} else {
							for (Entry<String, Object> entry : svaTextPolja.entrySet()) {
								if (entry.getKey().equals(referenciraneKoloneSlozenogKljuca.get(j))) {
									System.out.println("KOJI PROBLEM"+entry.getKey());
									((JTextField) entry.getValue()).setText(value);
									
								}
							}

						}
					}
					zoomDijalog.dispose();



			}catch(Exception ee){
				}
			}
		});

	}
	
	/**
	 * Uzima vrednosti roditelja da bi napunila decu.
	 * @param t
	 * @return
	 */
	
	public ArrayList<Integer> mapirajIndeksePoTabeli(Table t){
		System.out.println("TABELA:"+t.getIdLabelTable());
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int i=0;i<t.getListOfColumns().size();i++){
			for(int j=0;j<referenciraneKoloneSlozenogKljuca.size();j++){
				System.out.println("KOGA PROVERAVAM"+referenciraneKoloneSlozenogKljuca.get(j));
				String parent = nadjiRoditelja(referenciraneKoloneSlozenogKljuca.get(j),t);
				if(t.getListOfColumns().get(i).getIdLabelColmun().equals(parent)){
				ret.add(i);
				System.out.println(ret);
			}
		}}
		
		
		return ret;
	}
	
	/**
	 * Trazi roditelja za prosledjeno dete.
	 * @param dete
	 * @param t
	 * @return
	 */
	
	public String nadjiRoditelja(String dete, Table t){
		String ret = new String();
		for(int i=0;i<MyApp.getInstance().getListaStranihKljuceva().size();i++){
			if(MyApp.getInstance().getListaStranihKljuceva().get(i).getChildColumn().equals(dete) &&
					MyApp.getInstance().getListaStranihKljuceva().get(i).getParentTable().equals(t.getIdLabelTable()) &&
					MyApp.getInstance().getListaStranihKljuceva().get(i).getChildTable().equals(childTab)){
					ret = MyApp.getInstance().getListaStranihKljuceva().get(i).getParentColumn();
			}
		}
		
		return ret;
	}


}
