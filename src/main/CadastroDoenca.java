package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import consulta.diagnostico.doenca.Doenca;
import consulta.diagnostico.doenca.dao.DoencaDAO;
import medico.especialidade.dao.EspecialidadeDAO;

public class CadastroDoenca extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroDoenca dialog = new CadastroDoenca();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroDoenca() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		nomeTf = new JTextField();
		nomeTf.setBounds(96, 30, 114, 19);
		contentPanel.add(nomeTf);
		nomeTf.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(33, 32, 70, 15);
		contentPanel.add(lblNome);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener((ActionEvent e) -> {
					cadastrarDoenca(); 
					this.dispose();
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener((ActionEvent e) -> this.dispose() );
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private void cadastrarDoenca() {
		Doenca d = new Doenca();
		d.setNome(nomeTf.getText());
		
		try {
			new DoencaDAO().salvar(d);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
