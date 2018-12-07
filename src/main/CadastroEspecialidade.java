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

import medico.especialidade.Especialidade;
import medico.especialidade.dao.EspecialidadeDAO;

public class CadastroEspecialidade extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTf;
	private JTextField valorTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroEspecialidade dialog = new CadastroEspecialidade();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroEspecialidade() {
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
		
		JLabel lblCdigoDeRegistro = new JLabel("Valor");
		lblCdigoDeRegistro.setBounds(33, 74, 48, 15);
		contentPanel.add(lblCdigoDeRegistro);
		
		valorTf = new JTextField();
		valorTf.setBounds(82, 72, 114, 19);
		contentPanel.add(valorTf);
		valorTf.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener((ActionEvent e) -> {
					cadastrarEspecialidade(); 
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
	
	private void cadastrarEspecialidade() {
		Especialidade e = new Especialidade();
		e.setNome(nomeTf.getText());
		e.setValor(Double.parseDouble(valorTf.getText()));
		
		try {
			new EspecialidadeDAO().salvar(e);
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
