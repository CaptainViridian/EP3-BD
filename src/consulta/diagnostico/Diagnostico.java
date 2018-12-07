package consulta.diagnostico;

import consulta.diagnostico.doenca.Doenca;

public class Diagnostico {

	private int id;

	private String tratamento;

	private Doenca doenca;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTratamento() {
		return tratamento;
	}

	public void setTratamento(String tratamento) {
		this.tratamento = tratamento;
	}

	public Doenca getDoenca() {
		return doenca;
	}

	public void setDoenca(Doenca doenca) {
		this.doenca = doenca;
	}

}
