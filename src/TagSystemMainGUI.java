import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.JTable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DropMode;
import javax.swing.UIManager;
import java.awt.Label;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TagSystemMainGUI extends JFrame {

	private static TagSystemMainGUI frame;
	private JPanel contentPane;
	private JTextField textField;
	private JButton btnEdit;
	private JButton btnClear;
	private JButton btnNewButton_2;
	private JLabel root_dir_Label;
	private JTable table;
	private DefaultTableModel tmodel;
	
	TagSystem tagSystem = new TagSystem();
	ArrayList<Data> tableData;	//Data List that used in current table 
	int selectedRow = -1;
	private JMenuBar menuBar;
	private JMenuItem mntmAbout;
	private Component glue;
	private JLabel lblNewLabel_1;
	
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				tagSystem.flieManager.putAllBackToDB(tagSystem.data,tagSystem.tags);
				System.exit(0);
			}
		});
		init();
		
		setTitle("QTag");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		lblNewLabel_1 = new JLabel("Account:");
		menuBar.add(lblNewLabel_1);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalGlue.setBackground(UIManager.getColor("MenuItem.background"));
		menuBar.add(horizontalGlue);
		
		
		mntmAbout = new JMenuItem("Logout");
		menuBar.add(mntmAbout);
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
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setDropMode(DropMode.INSERT);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode( ) == KeyEvent.VK_ENTER){
					tagSystem.scanUptate();
					refreshTable(tagSystem.search(textField.getText()));
				}					
			}
		});
		textField.addMouseListener(new MouseAdapter() {
 			@Override
 			public void mouseClicked(MouseEvent e) { 				
 				selectedRow = -1;
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
			  if(selectedRow>=0 && tableData.get(selectedRow).isExist){
				  EditTagsGUI edit = new EditTagsGUI(frame);
				  edit.setLocationRelativeTo(frame);
				  edit.setModal(true);
				  edit.setVisible(true);
				  if(edit.OK()){
				    tagSystem.setConnected(tableData.get(selectedRow),
				    		tagSystem.parseTags(edit.getText(),","));
				  }
				  tagSystem.scanUptate();
				  refreshTable(tableData);
			  }		
			  selectedRow = -1;
		  }
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnEdit, gbc_btnNewButton);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
			  //if select a file
			  if(selectedRow>=0){
				  //if file exist
				  if(tableData.get(selectedRow).isExist){
					  int yes_no=JOptionPane.showConfirmDialog(null,"Are you sure you want to clear this file all tags? ", "Clear All Tags", JOptionPane.YES_NO_OPTION);
					  //confirm to clear all tags
					  if(yes_no==0){
						  tagSystem.setConnected(tableData.get(selectedRow),
						    		new ArrayList<Tag>());
						  tagSystem.scanUptate();
						  refreshTable(tableData);
					  }
				  }
				  //if file not exist
				  else{
					  int yes_no=JOptionPane.showConfirmDialog(null,"Are you sure you want to clear this file all tags?\nthe origin file don't exist, if you clear all tags will cause this file(in database record) be deleted", "Clear All Tags", JOptionPane.YES_NO_OPTION);
					  //confirm to clear all tags
					  if(yes_no==0){
						  tableData.remove(selectedRow);
						  tagSystem.scanUptate();
						  refreshTable(tableData);
					  }
				  }
			  }
			  //if not select a file
			  else{
				  JOptionPane.showMessageDialog(null,"not select any file, Please select one.", "Warning", JOptionPane.ERROR_MESSAGE);
			  }
			  selectedRow = -1;
		  }
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 2;
		gbc_btnNewButton_1.gridy = 0;
		contentPane.add(btnClear, gbc_btnNewButton_1);
		
		btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(selectedRow>=0 && !tableData.get(selectedRow).isExist &&
						JOptionPane.showConfirmDialog((Component) null, "Are you sure?",
						        "Remove file record don't exist", JOptionPane.OK_CANCEL_OPTION)
						        	== 0){
					tableData.remove(selectedRow);
					tagSystem.scanUptate();
					refreshTable(tableData);
				}
				selectedRow = -1;
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTH;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 3;
		gbc_btnNewButton_2.gridy = 0;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		root_dir_Label = new JLabel("Root Directory : " + tagSystem.flieManager.getRootDirectory().getAbsolutePath().toString());
		root_dir_Label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int diatemp=JOptionPane.showConfirmDialog(null,"Are you sure you want to reset Root Directory? ", "Reset Root Directory ", JOptionPane.YES_NO_OPTION);
				if(diatemp== 0){
					String input=JOptionPane.showInputDialog("Please enter new Root Directory");
					if(input!=null && input.length()!=0){
						if(new File(input).isDirectory()){
							if(tagSystem.flieManager.setRootDirectory(input))
								root_dir_Label.setText("Root Directory : " + tagSystem.flieManager.getRootDirectory().getAbsolutePath().toString());
							tagSystem.scanUptate();
							refreshTable(tableData);
						}
						else{
							JOptionPane.showMessageDialog(null,"Invalid Directory, Please enter again.", "Warning", JOptionPane.ERROR_MESSAGE);
						}
					}				
				}								
			}					
		});
		
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.weighty = 10.0;
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblNewLabel.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(root_dir_Label, gbc_lblNewLabel);
		
		tmodel = new DefaultTableModel(){
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells can't be edit
		       return false;
		    }
		};
		tmodel.addColumn("File Path"); 
		tmodel.addColumn("Tags"); 
		refreshTable(tagSystem.data);
		table = new JTable(tmodel);		
	    
		table.setCellSelectionEnabled(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  selectedRow = table.getSelectedRow();
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
	
	//refresh table with input data
	void refreshTable(ArrayList<Data> data){
		tmodel.setRowCount(0);
		tableData = data;
		for(Data d : data){
			String tags="";
			for(Tag t : d.tags)
				tags += t.name+", ";
			tmodel.addRow(new Object[]{d.getPath(), tags});
		}		
	}
	
	String getTableData(int row, int col){
		return (String)tmodel.getValueAt(row, col);
	}

	
	public class MyRenderer extends DefaultTableCellRenderer  
	{ 
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) 
		{ 
		    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
	
		    if(!tableData.get(row).isExist)
		        c.setBackground(new java.awt.Color(255, 0, 0));
		    else if(tableData.get(row).isEmptyTags)
		    	c.setBackground(new java.awt.Color(255, 255, 0));
		    else
		        c.setBackground(table.getBackground());
	
		    return c; 
		} 

	} 
}
