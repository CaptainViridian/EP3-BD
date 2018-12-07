package main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import consulta.Consulta;

@SuppressWarnings("serial")
public class VisualizarConsulta extends JFrame {

	private JPanel contentPane;
	public VisualizarConsulta(Consulta c) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 646, 542);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPaciente = new JLabel("Paciente:");
		lblPaciente.setBounds(42, 12, 70, 15);
		contentPane.add(lblPaciente);

		JLabel lblNomePaciente = new JLabel("New label");
		lblNomePaciente.setBounds(124, 12, 297, 15);
		contentPane.add(lblNomePaciente);
		lblNomePaciente.setText(c.getPaciente().getNome());

		JButton btnVisualizarPaciente = new JButton("Visualizar Paciente");
		btnVisualizarPaciente.setBounds(42, 39, 170, 25);
		contentPane.add(btnVisualizarPaciente);
		btnVisualizarPaciente.addActionListener(e -> new VisualizarPaciente(c.getPaciente()).setVisible(true));

		JLabel lblMdico = new JLabel("Médico:");
		lblMdico.setBounds(42, 88, 70, 15);
		contentPane.add(lblMdico);

		JLabel lblNomeMedico = new JLabel((String) null);
		lblNomeMedico.setBounds(124, 88, 297, 15);
		contentPane.add(lblNomeMedico);
		lblNomeMedico.setText(c.getMedico().getNome());

		JLabel lblEspecialidade = new JLabel("Especialidade:");
		lblEspecialidade.setBounds(42, 127, 107, 15);
		contentPane.add(lblEspecialidade);

		JLabel lblNomeEspecialidade = new JLabel((String) null);
		lblNomeEspecialidade.setBounds(161, 127, 260, 15);
		contentPane.add(lblNomeEspecialidade);
		lblNomeEspecialidade.setText(c.getEspecialidade().getNome());

		JLabel lblPago = new JLabel("Pago:");
		lblPago.setBounds(42, 168, 70, 15);
		contentPane.add(lblPago);

		JLabel lblIsPago = new JLabel((String) null);
		lblIsPago.setBounds(108, 168, 297, 15);
		contentPane.add(lblIsPago);
		lblIsPago.setText(c.isPago() ? "Sim" : "Não");
		
		JLabel lblFormaPagamento = new JLabel("Não");
		lblFormaPagamento.setBounds(219, 205, 186, 15);
		contentPane.add(lblFormaPagamento);
		lblFormaPagamento.setText(c.getFormaPagamento() == null ? "Não foi pago" : c.getFormaPagamento());
		
		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento:");
		lblFormaDePagamento.setBounds(42, 205, 165, 15);
		contentPane.add(lblFormaDePagamento);
		
		JLabel lblDiagnostico = new JLabel("Diagnostico:");
		lblDiagnostico.setBounds(42, 269, 107, 15);
		contentPane.add(lblDiagnostico);
		
		JLabel lblDoenca = new JLabel("<dynamic>");
		lblDoenca.setBounds(161, 269, 244, 15);
		contentPane.add(lblDoenca);
		lblDoenca.setText(c.getDiagnostico() == null ? "" : c.getDiagnostico().getDoenca().getNome());
		
		JButton btnRegistrarPagamento = new JButton("Registrar pagamento");
		btnRegistrarPagamento.setBounds(42, 228, 193, 25);
		contentPane.add(btnRegistrarPagamento);
		btnRegistrarPagamento.addActionListener(e -> new RegistrarPagamentoConsulta(c).setVisible(true));
		
		JLabel lblTratamento = new JLabel("Tratamento:");
		lblTratamento.setBounds(42, 303, 93, 15);
		contentPane.add(lblTratamento);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(149, 303, 346, 94);
		contentPane.add(textPane);
		textPane.setText(c.getDiagnostico() == null ? "" : c.getDiagnostico().getTratamento());
		textPane.setEditable(false);
		
		JButton btnRegistrarTratamento = new JButton("Registrar diagnóstico");
		btnRegistrarTratamento.setBounds(42, 426, 193, 25);
		contentPane.add(btnRegistrarTratamento);
		btnRegistrarTratamento.addActionListener(e -> new RegistrarDiagnostico(c).setVisible(true));

	}
}
