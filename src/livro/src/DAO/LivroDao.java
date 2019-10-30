/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livro.src.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import livro.src.Model.Livro;

/**
 *
 * @author 2017101680
 */
public class LivroDao {

    Connection banco;

    public LivroDao() throws ClassNotFoundException, SQLException {
        FabricaConexao fabricaConexao = new FabricaConexao();
        banco = fabricaConexao.Conexao();

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
        try {
            banco.close();
        } finally {
            super.finalize();
        }
    }

    public int create(Livro livro) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("Insert into livro (titulo, preco, "
                + "estoque, idGenero, idEdtora) values ( ? ? ? ? ?) ");

        stmt.setString(1, livro.getTitulo());
        stmt.setFloat(2, livro.getPreco());

        stmt.setInt(3, livro.getEstoque());

        stmt.setInt(4, livro.getIdGenero());

        stmt.setInt(5, livro.getIdEditora());

        return stmt.executeUpdate();
    }

    public ArrayList listar() throws SQLException {
        PreparedStatement stmt = banco.prepareStatement("Select * from livro");
        ArrayList<Livro> livros = new ArrayList<Livro>();
        ResultSet resultado = stmt.executeQuery();
        Livro livro;
        while (resultado.next()) {
            livro = new Livro();
            livro.setEstoque(resultado.getInt("estoque"));
            livro.setIdEditora(resultado.getInt("editora"));
            livro.setIdGenero(resultado.getInt("genero"));
            livro.setIdLivro(resultado.getInt("id"));
            livro.setPreco(resultado.getFloat("preco"));
            livro.setTitulo(resultado.getString("titulo"));
            livros.add(livro);
        }
        return livros;
    }

    public int delete(int id) throws SQLException {

        PreparedStatement stmt = banco.prepareStatement("Delete  from livro where nome ?");
        stmt.setInt(1, id);
        return stmt.executeUpdate();

    }

    public int update(Livro livro) throws ClassNotFoundException, SQLException {
        PreparedStatement stmt = banco.prepareStatement("update set livro (titulo, preco, "
                + "estoque, idGenero, idEdtora) values ( ? ? ? ? ?) where id = ?");

        stmt.setString(1, livro.getTitulo());
        stmt.setFloat(2, livro.getPreco());

        stmt.setInt(3, livro.getEstoque());

        stmt.setInt(4, livro.getIdGenero());

        stmt.setInt(5, livro.getIdEditora());
        stmt.setInt(6, livro.getIdLivro());

        return stmt.executeUpdate();
    }
}
