import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditTagsGUI extends JDialog {
	private JTextField textField;
	private boolean OK=false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditTagsGUI dialog = new EditTagsGUI();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EditTagsGUI() {
		setTitle("Edit Tags");
		setBounds(100, 100, 450, 160);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EmptyBorder(20, 20, 10, 20));
			getContentPane().add(buttonPane, BorderLayout.CENTER);
			GridBagLayout gbl_buttonPane = new GridBagLayout();
			gbl_buttonPane.columnWidths = new int[]{198, 0, 0};
			gbl_buttonPane.rowHeights = new int[]{0, 0, 0, 0};
			gbl_buttonPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			gbl_buttonPane.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			buttonPane.setLayout(gbl_buttonPane);
			{
				textField = new JTextField();
				GridBagConstraints gbc_textField = new GridBagConstraints();
				gbc_textField.gridwidth = 2;
				gbc_textField.insets = new Insets(0, 0, 5, 0);
				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
				gbc_textField.gridx = 0;
				gbc_textField.gridy = 0;
				buttonPane.add(textField, gbc_textField);
				textField.setColumns(10);
			}
			{
				JButton btn_OK = new JButton("OK");
				btn_OK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						OK=true;
						dispose();
					}
				});
				GridBagConstraints gbc_btn_OK = new GridBagConstraints();
				gbc_btn_OK.insets = new Insets(0, 0, 0, 5);
				gbc_btn_OK.gridx = 0;
				gbc_btn_OK.gridy = 2;
				buttonPane.add(btn_OK, gbc_btn_OK);
			}
			{
				JButton btn_Cancel = new JButton("Cancel");
				btn_Cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				GridBagConstraints gbc_btn_Cancel = new GridBagConstraints();
				gbc_btn_Cancel.gridx = 1;
				gbc_btn_Cancel.gridy = 2;
				buttonPane.add(btn_Cancel, gbc_btn_Cancel);
			}
		}
	}
	
	String getText(){	return textField.getText();	}
	
	boolean OK(){	return OK;	}

}
