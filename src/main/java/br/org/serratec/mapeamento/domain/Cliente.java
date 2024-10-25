package br.org.serratec.mapeamento.domain;

import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="cliente")
public class Cliente {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;
	
	@NotBlank(message = "Preencha o campo nome")
	@Size(max = 60, message = "O campo nome deve ter no máximo {max} caracteres")
	@Column(name = "nome", nullable = false, length = 40)
	private String nome;
	
	@NotBlank(message = "Preencha o campo cpf")
	@CPF(message = "Insira um CPF válido")
	@Column(name = "cpf", nullable = false, unique = true, length = 11)
	private String cpf;
	
	@NotBlank(message = "Preencha o campo email")
	@Email(regexp = "[a-z0-9.-_]+@[a-z._]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Insira um email válido")
	@Column(name = "email", nullable = false, unique = true, length = 50)
	private String email;
	
	@Column(name = "data_nascimento", nullable = false)
	@Temporal(TemporalType.DATE)
	private LocalDate dataNascimento;
	
	@Embedded
	private Endereco endereco;

	public Cliente(Long id_cliente, String nome, String cpf, String email, LocalDate dataNascimento, Endereco endereco) {
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.endereco = endereco;
	}
	
	public Cliente() {}
	
	@Override
	public int hashCode() {
		return Objects.hash(id_cliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id_cliente, other.id_cliente);
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
