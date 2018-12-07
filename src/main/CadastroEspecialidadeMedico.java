package main;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import medico.Medico;
import medico.dao.MedicoDAO;
import medico.especialidade.Especialidade;
import medico.especialidade.dao.EspecialidadeDAO;

import javax.swing.JLabel;

public class CadastroEspecialidadeMedico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	List listMedicos;
	List listEspecialidades;
	
	private Medico[] medicos;
	private Especialidade[] especialidades;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CadastroEspecialidadeMedico dialog = new CadastroEspecialidadeMedico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CadastroEspecialidadeMedico() {
		try {
			medicos = new MedicoDAO().getAllMedicos();
			especialidades = new EspecialidadeDAO().getAllEspecialidades();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		getContentPane().setLayout(null);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		listMedicos = new List();
		listMedicos.setMultipleMode(true);
		listMedicos.setBounds(48, 74, 139, 72);
		
		for (Medico medico : medicos) {
			listMedicos.add(medico.getNome());
		}
		
		contentPanel.add(listMedicos);
		{
			JLabel lblMdicos = new JLabel("Médicos");
			lblMdicos.setBounds(48, 41, 70, 15);
			contentPanel.add(lblMdicos);
		}
		{
			listEspecialidades = new List();
			listEspecialidades.setBounds(255, 74, 139, 72);
			contentPanel.add(listEspecialidades);
			
			for (Especialidade especialidade : especialidades) {
				listEspecialidades.add(especialidade.getNome());
			}
		}
		{
			JLabel lblEspecialidades = new JLabel("Especialidades");
			lblEspecialidades.setBounds(255, 41, 106, 15);
			contentPanel.add(lblEspecialidades);
		}
		{
			JPanel buttonPane = new JPanel();
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
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener((ActionEvent e) -> this.dispose());
				buttonPane.add(cancelButton);
			}
		}
	}

	private void cadastrarEspecialidade() {
		Medico m = medicos[listMedicos.getSelectedIndex()];
		Especialidade e = especialidades[listEspecialidades.getSelectedIndex()];
		
		try {
			new MedicoDAO().salvarEspecialidade(m, e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
