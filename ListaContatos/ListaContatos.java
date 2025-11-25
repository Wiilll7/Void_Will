package ListaContatos;

import java.util.List;
import java.util.ArrayList;

public class ListaContatos {

	private List<Contato> listaContatos = new ArrayList<Contato>();

	
	public ListaContatos(List<Contato> listaContatos) {
		setListaContatos(listaContatos);
	}
	public ListaContatos() {
		
	}
	
	
	public List<Contato> getListaContatos() {
		return listaContatos;
	}
	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}
	
	
	public boolean addContato(Contato contato) {
		if(listaContatos.add(contato)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean removeContato(Contato contato) {
		if (listaContatos.remove(contato)) {
			return true;
		} else {
			return false;
		}
	}
}
