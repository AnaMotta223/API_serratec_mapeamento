package br.org.serratec.mapeamento.injecao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//diz que a classe vai ser gerenciada pelo spring - ele mesmo faz a instancia
@Component
public class Pagamento {
	
	@Autowired
	private Consulta consulta;
	
	@Autowired
	private Exame exame;

	//se quiser facilidade no teste unitario = construtor, se quiser praticidade no dia a dia = autowired
	
	//	
//	public Pagamento(Consulta consulta, Exame exame) {
//		this.consulta = consulta;
//		this.exame = exame;
//	}
	
	public Double calcularProcedimento(Double valorConsulta, Double valorExame) {
		return consulta.calcularConsulta(valorConsulta) + exame.calcularExame(valorExame);
	}
}
