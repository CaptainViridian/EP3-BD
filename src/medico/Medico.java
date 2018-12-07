package medico;

import java.util.ArrayList;
import java.util.List;

import medico.disponibilidade.Disponibilidade;
import medico.especialidade.Especialidade;
import medico.pagamento.Pagamento;

public class Medico {

	private String nome;
	
	private String codRegistro;
	
	private double porcentagemRecebimento;
	
	private List<Especialidade> especialidades = new ArrayList<>();
	
	private List<Disponibilidade> disponibilidades = new ArrayList<>();
	
	private List<Pagamento> pagamentos = new ArrayList<>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodRegistro() {
		return codRegistro;
	}

	public void setCodRegistro(String codRegistro) {
		this.codRegistro = codRegistro;
	}

	public double getPorcentagemRecebimento() {
		return porcentagemRecebimento;
	}

	public void setPorcentagemRecebimento(double porcentagemRecebimento) {
		this.porcentagemRecebimento = porcentagemRecebimento;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public List<Disponibilidade> getDisponibilidades() {
		return disponibilidades;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}
	
	
	
}
