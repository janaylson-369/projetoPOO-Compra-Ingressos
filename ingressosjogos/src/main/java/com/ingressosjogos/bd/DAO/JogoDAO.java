package com.ingressosjogos.bd.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.ingressosjogos.bd.model.Jogo;
import com.ingressosjogos.bd.model.Jogo;
import com.ingressosjogos.bd.util.ConnectionPostgres;

public class JogoDAO {
    
    public void salvar(Jogo jogo) throws Exception{
        String sql = "INSERT INTO Jogo(data_hora,id_estadio,id_time_casa,id_time_fora)"+
        "VALUES (?,?,?,?)";

        try (Connection c = ConnectionPostgres.getConection();
            PreparedStatement st = c.prepareStatement(sql)) {
                st.setTimestamp(1,jogo.getDataHora());
                st.setObject(2, jogo.getIdEstadio());
                st.setObject(3, jogo.getIdTimeCasa());
                st.setObject(4, jogo.getIdTimeFora());
                st.executeUpdate();
            
        }
    }

    public List<Jogo> listar() throws Exception{
        List<Jogo> lista = new ArrayList<>();
        String sql = "SELECT * FROM Jogo";

        try(Connection c = ConnectionPostgres.getConection();
            Statement st = c.createStatement()){
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                lista.add(new Jogo(
                    rs.getInt("id"),
                    rs.getTimestamp("data_hora"),
                    rs.getInt("id_estagio"),
                    rs.getInt("id_time_casa"),
                    rs.getInt("id_time_fora")
                ));            
            }
        }
        return lista;
    }

    public void excluir(int id) throws Exception {
        String sql = "DELETE FROM Jogo WHERE id=?";

        try(Connection c = ConnectionPostgres.getConection();
            PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
