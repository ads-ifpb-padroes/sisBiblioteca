/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.padroes.projeto.sisbiblioteca.dao;

import br.edu.ifpb.padroes.projeto.sisbiblioteca.entities.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kieckegard
 */
public class LivroBdDao implements Dao<Livro, Long>
{

    @Override
    public void add(Livro obj)
    {
        String sql = "INSERT INTO livro VALUES(?,?,?,?)";
        
        try
        {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            int i = 1;
            
            pstm.setLong(i++, obj.getIsbn());
            pstm.setString(i++, obj.getTitulo());
            pstm.setString(i++, obj.getAutor());
            pstm.setString(i++, obj.getDescricao());
            
            pstm.executeUpdate();
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void rem(Livro obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Livro obj)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Livro get(Long obj)
    {
        String sql = "SELECT * FROM livro WHERE isbn = ?";      
        try{
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            pstm.setLong(1, obj);
            ResultSet rs = pstm.executeQuery();
            
            if(rs.next())
                return formaLivro(rs);
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Livro> list()
    {
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();
        try
        {
            PreparedStatement pstm = FactoryConnection.createConnection().prepareStatement(sql);
            
            ResultSet rs = pstm.executeQuery();
            
            while(rs.next())
                livros.add(formaLivro(rs));

            return livros;
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(LivroBdDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return livros;
    }
    
    private Livro formaLivro(ResultSet rs) throws SQLException{
        long isbn = rs.getLong("isbn");
        String titulo = rs.getString("titulo");
        String autor = rs.getString("autor");
        String descricao = rs.getString("descricao");
        
        Livro l = new Livro(isbn,titulo,autor,descricao);
        
        return l;
    }
    
}