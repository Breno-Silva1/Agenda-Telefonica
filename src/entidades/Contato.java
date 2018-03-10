package entidades;

public class Contato {
	private String nome;
	private String email;
	private String numero;
	private Endereco endereco;
	
	public Contato() {
		// TODO Auto-generated constructor stub
	}

	public Contato(String nome, String email, String numero, Endereco endereco) {
		super();
		this.nome = nome;
		this.email = email;
		this.numero = numero;
		this.endereco = endereco;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
