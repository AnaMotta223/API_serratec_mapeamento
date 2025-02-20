package br.org.serratec.mapeamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.org.serratec.mapeamento.injecao.Consulta;
import br.org.serratec.mapeamento.injecao.Exame;
import br.org.serratec.mapeamento.injecao.Pagamento;

@SpringBootApplication
public class MapeamentoApplication implements CommandLineRunner {

	@Autowired
	private Pagamento pagamento;
	
	public static void main(String[] args) {
		SpringApplication.run(MapeamentoApplication.class, args);
	}

	//commandlinerunner evita que precisemos criar um controller para executar as coisas
	@Override
	public void run(String... args) throws Exception {
		//Consulta consulta = new Consulta();
		//Exame exame =  new Exame();
		//Pagamento pagamento = new Pagamento(consulta,exame);
		System.out.println("Total a pagar: " + pagamento.calcularProcedimento(200.0, 80.0));
	}

}
