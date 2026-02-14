package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.ingressosjogos.bd.model.Torcedor;
import com.ingressosjogos.bd.util.ConnectionPostgres;

public class TorcedorDAO {

    public void salvar(Torcedor torcedor) throws Exception{
        String sql = "INSERT INTO Torcedor \" + \"(nome,email,cpf) \" + \"VALUES (?,?,?)";

        try (Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)){
            
                ps.setString(1, torcedor.getNome());
                ps.setString(2, torcedor.getEmail());
                ps.setString(3, torcedor.getCpf());
                ps.executeUpdate();
        }

    } 

    public List<Torcedor> listar() throws Exception{
        List<Torcedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Torcedor";

        try(Connection c = ConnectionPostgres.getConection();
            Statement st = c.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Torcedor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf")
                ));            
            }
        }
        return lista;
    }

    public Torcedor buscar(int id) throws Exception{
        String sql = "SELECT * FROM Torcedor WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Torcedor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cpf")
                );
            }
        }
        return null;
    }

    public void atualizar(Torcedor torcedor)throws Exception{
        String sql = "UPDATE Torcedor SET nome=?,N_camisa=?,id_time=? WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, torcedor.getNome());
            ps.setString(2,torcedor.getEmail());
            ps.setString(3, torcedor.getCpf());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Jogador WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    
}
