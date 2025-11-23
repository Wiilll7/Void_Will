
public class GerenciadorUsuario {

	public Usuario criarUsuario(String nome, String senha, TipoUsuario tipo) {
		
		Usuario user = new Usuario(nome, senha, tipo);
		
		return user;
	}
	
	public void modificarUsuario(String nome, String senha, TipoUsuario tipo) {
		
	}
	
	public void deletarUsuario() {
		
	}
	
}
