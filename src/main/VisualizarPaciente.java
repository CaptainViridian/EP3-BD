package main;

import java.awt.List;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;
import consulta.dao.ConsultaDAO;
import paciente.Paciente;

@SuppressWarnings("serial")
public class VisualizarPaciente extends JFrame {

	private JPanel contentPane;

	private Consulta[] consultas;

	public VisualizarPaciente(Paciente p) {
		try {
			consultas = new ConsultaDAO().getAllConsultasDePaciente(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(36, 24, 70, 15);
		contentPane.add(lblNome);

		JLabel lblNomeText = new JLabel("New label");
		lblNomeText.setBounds(118, 24, 184, 15);
		contentPane.add(lblNomeText);
		lblNomeText.setText(p.getNome());

		JLabel lblTelefoneText = new JLabel("New label");
		lblTelefoneText.setBounds(118, 51, 184, 15);
		contentPane.add(lblTelefoneText);
		lblTelefoneText.setText(p.getTelefone());

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(36, 51, 70, 15);
		contentPane.add(lblTelefone);

		JLabel lblDiagnsticos = new JLabel("Diagnósticos");
		lblDiagnsticos.setBounds(36, 89, 101, 15);
		contentPane.add(lblDiagnsticos);

		List list = new List();
		list.setBounds(36, 122, 524, 236);
		contentPane.add(list);

		Arrays.sort(consultas, (a, b) -> a.getData().compareTo(b.getData()));

		for (Consulta consulta : consultas) {
			list.add(consulta.getData()
					.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
					+ "Médico: " + consulta.getMedico().getNome()
					+ (consulta.getDiagnostico() == null ? ""
							: " - Diagnóstico: "
									+ consulta.getDiagnostico().getDoenca()
											.getNome()
									+ " - Tratamento: " + consulta
											.getDiagnostico().getTratamento()));
		}
	}

}
