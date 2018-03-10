package negocio;

import java.util.ArrayList;

import entidades.Contato;

public class Agenda {
	ArrayList<Contato> contatos = new ArrayList<>();
	
	public void cadastrar(Contato c){
		contatos.add(c);
	}
	
	public void editar(){
		
	}
}
