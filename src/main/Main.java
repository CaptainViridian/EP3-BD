package main;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNovo = new JMenu("Novo");
		menuBar.add(mnNovo);

		JMenuItem mntmMdico = new JMenuItem("Médico");
		mnNovo.add(mntmMdico);
		mntmMdico.addActionListener(e -> new CadastroMedico().setVisible(true));

		JMenuItem mntmPaciente = new JMenuItem("Paciente");
		mnNovo.add(mntmPaciente);
		mntmPaciente.addActionListener((e -> new CadastroPaciente().setVisible(true) ));

		JMenuItem mntmEspecialidade = new JMenuItem("Especialidade");
		mnNovo.add(mntmEspecialidade);
		mntmEspecialidade.addActionListener(e -> new CadastroEspecialidade().setVisible(true));

		JMenuItem mntmDoenca = new JMenuItem("Doenca");
		mnNovo.add(mntmDoenca);
		mntmDoenca.addActionListener(e -> new CadastroDoenca().setVisible(true));

		JMenu mnMdico = new JMenu("Médico");
		menuBar.add(mnMdico);

		JMenuItem mntmCadastrarEspecialidade = new JMenuItem("Cadastrar especialidade");
		mnMdico.add(mntmCadastrarEspecialidade);
		mntmCadastrarEspecialidade.addActionListener(e -> new CadastroEspecialidadeMedico().setVisible(true));

		JMenuItem mntmRegistrarPagamento = new JMenuItem("Registrar pagamento");
		mnMdico.add(mntmRegistrarPagamento);
		mntmRegistrarPagamento.addActionListener(e -> new RegistrarPagamentoMedico().setVisible(true));
		
		frame.getContentPane().setLayout(null);
		
		JButton btnAgendarConsulta = new JButton("Agendar Consulta");
		btnAgendarConsulta.addActionListener(e -> new AgendamentoConsulta().setVisible(true));
		btnAgendarConsulta.setBounds(63, 33, 161, 25);
		frame.getContentPane().add(btnAgendarConsulta);
		
		JButton btnConsultasDoDia = new JButton("Consultas do dia");
		btnConsultasDoDia.setBounds(63, 70, 161, 25);
		frame.getContentPane().add(btnConsultasDoDia);
		btnConsultasDoDia.addActionListener(e -> new ConsultasDia().setVisible(true));

	}
}
