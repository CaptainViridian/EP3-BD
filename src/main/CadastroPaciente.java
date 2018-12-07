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

import paciente.Paciente;
import paciente.dao.PacienteDAO;

public class CadastroPaciente extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTf;
	private JTextField rgTf;
	private JTextField telefoneTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroPaciente dialog = new CadastroPaciente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroPaciente() {
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
		
		JLabel lblCdigoDeRegistro = new JLabel("RG");
		lblCdigoDeRegistro.setBounds(33, 74, 151, 15);
		contentPanel.add(lblCdigoDeRegistro);
		
		rgTf = new JTextField();
		rgTf.setBounds(82, 72, 114, 19);
		contentPanel.add(rgTf);
		rgTf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Telefone");
		lblNewLabel.setBounds(33, 118, 212, 15);
		contentPanel.add(lblNewLabel);
		
		telefoneTf = new JTextField();
		telefoneTf.setBounds(113, 116, 114, 19);
		contentPanel.add(telefoneTf);
		telefoneTf.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener((ActionEvent e) -> {
					cadastrarPaciente(); 
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
	
	private void cadastrarPaciente() {
		Paciente p = new Paciente();
		p.setNome(nomeTf.getText());
		p.setRG(rgTf.getText());
		p.setTelefone(telefoneTf.getText());
		
		try {
			new PacienteDAO().salvar(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
