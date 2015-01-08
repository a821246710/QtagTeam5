import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class TagSystemMainGUI extends JFrame {

	private static TagSystemMainGUI frame;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnEdit;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;
	private JTable table;
	private DefaultTableModel tmodel;
	
	TagSystem tagSystem = new TagSystem();
	int selectedRow = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new TagSystemMainGUI();
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
		init();
		
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
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode( ) == KeyEvent.VK_ENTER){
					String tempT = textField.getText();
					tagSystem.flieManager.rootDirectory = new File(tempT);
					refreshTable();
				}					
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 50);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    System.out.print(selectedRow);
		    EditTagsGUI d = new EditTagsGUI();
		    d.setLocationRelativeTo(frame);
		    d.setModal(true);
		    d.setVisible(true);
		  }
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnEdit, gbc_btnNewButton);
		
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
		
		lblNewLabel = new JLabel("Root Directory : " + tagSystem.flieManager.rootDirectory.toString());
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.weighty = 10.0;
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		tmodel = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells can't be edit
		       return false;
		    }
		};
		tmodel.addColumn("File Path"); 
		tmodel.addColumn("Tags"); 
		refreshTable();
		table = new JTable(tmodel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//let we know which row user selected
				selectedRow = table.rowAtPoint(e.getPoint());
			}
		});
		MyRenderer myRenderer = new MyRenderer();
		table.setDefaultRenderer(Object.class, myRenderer);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.weighty = 100.0;
		gbc_table.gridwidth = 4;
		gbc_table.insets = new Insets(0, 0, 0, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 3;
		contentPane.add(new JScrollPane(table),gbc_table);
		table.getTableHeader().setReorderingAllowed(false); 	
	}
	
	void init(){
		//load data
		tagSystem.flieManager.fetchAllFromDB(tagSystem.data,tagSystem.tags);
		//update file
		tagSystem.scanUptate();
	}
	
	void refreshTable(){
		tmodel.setRowCount(0);
		for(Data d : tagSystem.data){
			String tags="";
			for(Tag t : d.tags)
				tags += t.name+", ";
			tmodel.addRow(new Object[]{d.getPath(), tags});
		}		
	}

	
	public class MyRenderer extends DefaultTableCellRenderer  
	{ 
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
		{ 
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	
		    if(!tagSystem.data.get(row).isExist)
		        c.setBackground(new java.awt.Color(255, 0, 0));
		    else if(tagSystem.data.get(row).isEmptyTags)
		    	c.setBackground(new java.awt.Color(255, 255, 0));
		    else
		        c.setBackground(table.getBackground());
	
		    return c; 
		} 

	} 
}
