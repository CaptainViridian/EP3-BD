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

import medico.Medico;
import medico.dao.MedicoDAO;

public class CadastroMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomeTf;
	private JTextField registroTf;
	private JTextField porcentagemTf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroMedico dialog = new CadastroMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroMedico() {
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
		
		JLabel lblCdigoDeRegistro = new JLabel("Código de Registro");
		lblCdigoDeRegistro.setBounds(33, 74, 151, 15);
		contentPanel.add(lblCdigoDeRegistro);
		
		registroTf = new JTextField();
		registroTf.setBounds(190, 72, 114, 19);
		contentPanel.add(registroTf);
		registroTf.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Porcentagem de Recebimento");
		lblNewLabel.setBounds(33, 118, 212, 15);
		contentPanel.add(lblNewLabel);
		
		porcentagemTf = new JTextField();
		porcentagemTf.setBounds(263, 116, 114, 19);
		contentPanel.add(porcentagemTf);
		porcentagemTf.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener((ActionEvent e) -> {
					cadastrarMedico(); 
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
	
	private void cadastrarMedico() {
		Medico m = new Medico();
		m.setNome(nomeTf.getText());
		m.setCodRegistro(registroTf.getText());
		m.setPorcentagemRecebimento(Double.parseDouble(porcentagemTf.getText()) / 100);
		
		try {
			new MedicoDAO().salvar(m);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
