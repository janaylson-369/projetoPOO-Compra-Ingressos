package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.ingressosjogos.bd.model.Ingresso;
import com.ingressosjogos.bd.util.ConnectionPostgres;

public class IngressoDAO {
    
    public void salvar(Ingresso ingresso) throws Exception {
        String sql = "INSERT INTO Ingresso " + "(preco,assento,status,id_jogo,id_torcedor) " + "VALUES (?,?,?,?,?)";

        try (Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)){
            
                ps.setInt(1, ingresso.getId());
                ps.setDouble(2, ingresso.getPreco());
                ps.setString(3, ingresso.getAssento());
                ps.setString(4, ingresso.getStatus());
                ps.setInt(4,ingresso.getIdJogo());
                ps.setInt(5, ingresso.getIdTorcedor());
                ps.executeUpdate();
        }
    }

    public List<Ingresso> listar() throws Exception{
        List<Ingresso> lista = new ArrayList<>();
        String sql = "SELECT * FROM Ingresso";

        try (Connection c = ConnectionPostgres.getConection();
            Statement st = c.createStatement()) {
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    lista.add(new Ingresso(
                        rs.getInt("id"),
                        rs.getDouble("preco"),
                        rs.getString("assento"),
                        rs.getString("status"),
                        rs.getObject("id_jogador",Integer.class),
                        rs.getObject("id_torcedor",Integer.class)
                    ));
                    
                }
        }
        return lista;
    }

    public Ingresso buscar(int id) throws Exception{
        String sql = "SELECT * FROM Ingresso WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Ingresso(
                    rs.getInt("id"),
                    rs.getDouble("preco"),
                    rs.getString("assento"),
                    rs.getString("status"),
                    rs.getObject("id_jogador",Integer.class),
                    rs.getObject("id_torcedor",Integer.class)
                );
            }
        }
        return null;
    }

    public void atualizar(Ingresso ingresso)throws Exception{
        String sql = "UPDATE Jogador SET preco=?,assento=?,"+
        "status=?,id_jogo=?,id_torcedor=?  WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setDouble(1,ingresso.getPreco());
            ps.setString(2,ingresso.getAssento());
            ps.setString(3, ingresso.getStatus());
            ps.setObject(4, ingresso.getIdJogo());
            ps.setObject(5, ingresso.getIdTorcedor());
            ps.execute();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Ingresso WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
