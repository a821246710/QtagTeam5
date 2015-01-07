import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.GridLayout;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JTable;


public class TagSystemMainGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TagSystemMainGUI frame = new TagSystemMainGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TagSystemMainGUI() {
		setTitle("QTag");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(25, 25, 15, 25));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[] {2, 23, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 50);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Edit");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		btnNewButton_1 = new JButton("Clear");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		btnNewButton_2 = new JButton("Remove");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 0;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		lblNewLabel = new JLabel("Root Directory");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weighty = 10.0;
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		DefaultTableModel tmodel = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells can't be edit
		       return false;
		    }
		};
		tmodel.addColumn("File Path"); 
		tmodel.addColumn("Tags"); 
		//Simulation data
		tmodel.addRow(new Object[]{"Hot/the Walking Dead.mp4", "horrible, zombie"});
		tmodel.addRow(new Object[]{"Hot/the Goddather.mp4", "god"});
		tmodel.addRow(new Object[]{"Poster/Annabelle.jpg", "horrible, doll"});
		tmodel.addRow(new Object[]{"Poster/Blood Diamond.jpg", "documentary, diamond, rebel"});
		tmodel.addRow(new Object[]{"Poster/Toy Story.jpg", "cartoon"});
		tmodel.addRow(new Object[]{"Japan/Howl's Moving Castle.mp4", ""});
		tmodel.addRow(new Object[]{"StickDriver/Japanese gril.avi", "educational"});
		tmodel.addRow(new Object[]{"辦公用資料夾/MDF51DR/20150117.avi", "educational"});
		tmodel.addRow(new Object[]{"v1", "v2"});
		tmodel.addRow(new Object[]{"v1", "v2"});
		tmodel.addRow(new Object[]{"v1", "v2"});
		tmodel.addRow(new Object[]{"v1", "v2"});
		tmodel.addRow(new Object[]{"v1", "v2"});
		//Simulation data end
		table = new JTable(tmodel);		
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.weighty = 100.0;
		gbc_table.gridwidth = 4;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 3;
		contentPane.add(new JScrollPane(table),gbc_table);
		table.getTableHeader().setReorderingAllowed(false); //關閉拖動欄位功能
		
		
	}

}
