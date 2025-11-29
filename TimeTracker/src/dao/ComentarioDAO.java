package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.Conexao;
import dto.Comentario;

public class ComentarioDAO {

    final String TABELA = "Comentario";

    // Create
    public boolean create(Comentario comentario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "INSERT INTO " + TABELA + " (id_tarefa, descricao) VALUES (?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, comentario.getTarefaId());
            ps.setString(2, comentario.getDescricao());
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
    public Comentario readById(int id) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            List<Comentario> lista = createListFromSelect(rs);
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

    public List<Comentario> readAll() {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + ";";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Comentario> lista = createListFromSelect(rs);
            ps.close();
            conn.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Comentario> readByTarefaId(int tarefaId) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "SELECT * FROM " + TABELA + " WHERE id_tarefa = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tarefaId);
            ResultSet rs = ps.executeQuery();
            List<Comentario> lista = createListFromSelect(rs);
            ps.close();
            conn.close();
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    public List<Comentario> createListFromSelect(ResultSet rs) {
        List<Comentario> lista = new ArrayList<Comentario>();
        try {
            while (rs.next()) {
                Comentario comentario = new Comentario(
                    rs.getInt("id"),
                    rs.getInt("id_tarefa"),
                    rs.getString("descricao")
                );
                lista.add(comentario);
            }
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update
    public boolean update(Comentario comentario) {
        try {
            Connection conn = Conexao.conectar();
            String sql = "UPDATE " + TABELA + " SET id_tarefa = ?, descricao = ? WHERE id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, comentario.getTarefaId());
            ps.setString(2, comentario.getDescricao());
            ps.setInt(3, comentario.getId());
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
    public boolean deleteById(int id) {
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
}
