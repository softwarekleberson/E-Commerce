package br.com.engenharia.projeto.ProjetoFinal.entidade.administrador;

import br.com.engenharia.projeto.ProjetoFinal.dtos.Administrador.DadosCadastroAdministrador;
import br.com.engenharia.projeto.ProjetoFinal.entidade.cliente.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "administradores")
@Entity(name = "Adminstrador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Administrador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Email email;
	private String senha;
	
	@Transient
	private String confirmar_Senha;
	
	public Administrador(DadosCadastroAdministrador dados) {
		setNome(dados.nome());
		setEmail(dados.email());
		setSenha(dados.senha());
		setConfirmar_Senha(dados.confirmarSenha());
	}
	
	public void CriptografarSenha(String senhaCriptografada) {
		this.senha = senhaCriptografada;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		if(nome.trim() == null) {
			throw new IllegalArgumentException("Nome não pode ser nulo");
		}
		this.nome = nome;
	}
	
	public void setEmail(String email) {
		this.email = new Email(email);
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setConfirmar_Senha(String confirmar_Senha) {
		this.confirmar_Senha = confirmar_Senha;
	}
}