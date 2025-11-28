package bo;

import java.util.List;

import dao.UsuarioDAO;
import dto.Usuario;

public class UsuarioBO {
	public boolean create(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.create(usuario);
    }

	public Usuario readBySenha(String nome, String senha) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.readBySenha(nome,senha);
    }
	
    public Usuario readById(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.readById(id);
    }

    public List<Usuario> readAll() {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.readAll();
    }

    public boolean update(Usuario usuario) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.update(usuario);
    }

    public boolean delete(int id) {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.delete(id);
    }
}
