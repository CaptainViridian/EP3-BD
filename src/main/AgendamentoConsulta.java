package main;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;
import consulta.dao.ConsultaDAO;
import medico.Medico;
import medico.dao.MedicoDAO;
import medico.especialidade.Especialidade;
import medico.especialidade.dao.EspecialidadeDAO;
import paciente.Paciente;
import paciente.dao.PacienteDAO;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AgendamentoConsulta extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private List listMedicos;
	private List listEspecialidades;
	private List listPacientes;
	
	private Paciente[] pacientes;
	private Medico[] medicos;
	private Especialidade[] especialidades;
	
	MedicoDAO medicoDAO = new MedicoDAO();
	private JTextField txtDia;
	private JTextField txtMes;
	private JTextField txtAno;
	private JTextField txtHora;

	/**
	 * Create the dialog.
	 */
	public AgendamentoConsulta() {
		try {
			medicos = medicoDAO.getAllMedicos();
			pacientes = new PacienteDAO().getAllPacientes();
			especialidades = new EspecialidadeDAO().getAllEspecialidades();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		getContentPane().setLayout(null);

		setBounds(100, 100, 724, 630);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		listMedicos = new List();
		listMedicos.setBounds(262, 79, 158, 216);
		
		for (Medico medico : medicos) {
			listMedicos.add(medico.getNome() + " - " + medico.getCodRegistro());
		}
		
		contentPanel.add(listMedicos);
		{
			JLabel lblMdicos = new JLabel("Médico");
			lblMdicos.setBounds(262, 40, 70, 15);
			contentPanel.add(lblMdicos);
		}
		{
			listEspecialidades = new List();
			listEspecialidades.setBounds(496, 79, 144, 220);
			contentPanel.add(listEspecialidades);				
		}
		{
			JLabel lblEspecialidades = new JLabel("Especialidade");
			lblEspecialidades.setBounds(496, 40, 106, 15);
			contentPanel.add(lblEspecialidades);
		}
		{
			JLabel lblPaciente = new JLabel("Paciente");
			lblPaciente.setBounds(33, 40, 70, 15);
			contentPanel.add(lblPaciente);
		}
		{
			listPacientes = new List();
			listPacientes.setBounds(33, 79, 158, 216);
			contentPanel.add(listPacientes);
			
			for (Paciente p : pacientes) {
				listPacientes.add(p.getNome() + " - " + p.getRG());
			}
		}
		
		Button buttonBuscarEspecialidades = new Button("Buscar Especialidades");
		buttonBuscarEspecialidades.addActionListener((ActionEvent e) -> this.carregarEspecialidadesMedico());
		buttonBuscarEspecialidades.setBounds(262, 323, 158, 23);
		contentPanel.add(buttonBuscarEspecialidades);
		{
			txtDia = new JTextField();
			txtDia.setBounds(64, 422, 114, 19);
			contentPanel.add(txtDia);
			txtDia.setColumns(10);
		}
		{
			JLabel lblDia = new JLabel("Dia");
			lblDia.setHorizontalAlignment(SwingConstants.CENTER);
			lblDia.setBounds(87, 396, 70, 15);
			contentPanel.add(lblDia);
		}
		{
			txtMes = new JTextField();
			txtMes.setColumns(10);
			txtMes.setBounds(282, 422, 114, 19);
			contentPanel.add(txtMes);
		}
		{
			JLabel lblMes = new JLabel("Mês");
			lblMes.setHorizontalAlignment(SwingConstants.CENTER);
			lblMes.setBounds(302, 396, 70, 15);
			contentPanel.add(lblMes);
		}
		{
			JLabel lblAno = new JLabel("Ano");
			lblAno.setHorizontalAlignment(SwingConstants.CENTER);
			lblAno.setBounds(532, 396, 70, 15);
			contentPanel.add(lblAno);
		}
		{
			txtAno = new JTextField();
			txtAno.setColumns(10);
			txtAno.setBounds(512, 422, 114, 19);
			contentPanel.add(txtAno);
		}
		{
			txtHora = new JTextField();
			txtHora.setBounds(282, 492, 114, 19);
			contentPanel.add(txtHora);
			txtHora.setColumns(10);
		}
		{
			JLabel lblHorario = new JLabel("Horário");
			lblHorario.setHorizontalAlignment(SwingConstants.CENTER);
			lblHorario.setBounds(302, 463, 70, 15);
			contentPanel.add(lblHorario);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener((ActionEvent e) -> {
					agendarConsulta();
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

	private void agendarConsulta() {
		Consulta c = new Consulta();
		c.setMedico(medicos[listMedicos.getSelectedIndex()]);
		
		Especialidade e = especialidades[listEspecialidades.getSelectedIndex()];
		c.setEspecialidade(e);
		
		c.setPaciente(pacientes[listPacientes.getSelectedIndex()]);
		
		LocalDate data = LocalDate.of(Integer.parseInt(txtAno.getText()), Integer.parseInt(txtMes.getText()), Integer.parseInt(txtDia.getText()));
		c.setData(data);
		
		c.setValor(e.getValor());
		
		c.setHora(LocalTime.parse(txtHora.getText()));
		
		try {
			new ConsultaDAO().salvar(c);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	private void carregarEspecialidadesMedico() {
		Medico m = medicos[listMedicos.getSelectedIndex()];
		try {
			especialidades = medicoDAO.getEspecialidades(m);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		listEspecialidades.removeAll();
		for (Especialidade especialidade : especialidades) {
			listEspecialidades.add(especialidade.getNome());
		}
	}


}
