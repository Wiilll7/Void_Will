package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import dto.Tarefa;
import dto.TipoUsuario;
import dto.Usuario;


public class UsuarioDAO {

    final String TABELA = "Usuario";

    // Create
    public boolean create(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + TABELA + " (nome, senha, id_tipo_usuario) VALUES (?,?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getTipo().getId());

            ps.executeUpdate();
            ps.close();
            conn.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addTarefa(Usuario usuario,Tarefa tarefa) {
    	try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO acesso (id_tarefa, id_usuario) VALUES (?,?);";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefa.getId());
            ps.setInt(2, usuario.getId());

            ps.executeUpdate();
            ps.close();
            conn.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read
    public Usuario readById(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            List<Usuario> lista = createListFromSelect(rs);

            ps.close();
            conn.close();

            if (lista.size() > 0) {
                return lista.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Usuario readBySenha(String nome,String senha) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE nome = ? AND senha = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            List<Usuario> lista = createListFromSelect(rs);

            ps.close();
            conn.close();

            if (lista.size() > 0) {
                return lista.get(0);
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<Usuario> readByTarefaId(int tarefa_id) {
        try {
            Connection conn = Conexao.conectar();
            
            String sql = "SELECT "
            		+ "usuario.id AS id,"
            		+ "usuario.nome AS nome,"
            		+ "usuario.senha AS senha,"
            		+ "usuario.id_tipo_usuario AS id_tipo_usuario"
            		+ " FROM acesso"
            		+ " JOIN usuario ON usuario.id = acesso.id_usuario"
            		+ " WHERE acesso.id_tarefa = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefa_id);
            ResultSet rs = ps.executeQuery();

            List<Usuario> lista = createListFromSelect(rs);
            ps.close();
            conn.close();
            if (lista.size() > 0) {
				return lista;
			} else {
				return null;
			}
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> readAll() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + ";";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Usuario> lista = createListFromSelect(rs);

            ps.close();
            conn.close();

            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private List<Usuario> createListFromSelect(ResultSet rs) {
        List<Usuario> lista = new ArrayList<>();

        try {
            while (rs.next()) {

                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("senha"),
                        TipoUsuario.getFromId(rs.getInt("id_tipo_usuario"))
                );

                lista.add(usuario);
            }

            return lista;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update
    public boolean update(Usuario usuario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + TABELA + " SET nome = ?, senha = ?, id_tipo_usuario = ? WHERE id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setInt(3, usuario.getTipo().getId());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();
            ps.close();
            conn.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // Delete
    public boolean delete(int id) {
        try {
            if (readById(id) != null) {
                Connection conn = Conexao.conectar();
                String sql = "DELETE FROM " + TABELA + " WHERE id = ?;";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();

                ps.close();
                conn.close();

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean removeTarefa(Usuario usuario,Tarefa tarefa) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "DELETE FROM acesso WHERE id_tarefa = ? AND id_usuario = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefa.getId());
            ps.setInt(2, usuario.getId());
            ps.executeUpdate();

            ps.close();
            conn.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

