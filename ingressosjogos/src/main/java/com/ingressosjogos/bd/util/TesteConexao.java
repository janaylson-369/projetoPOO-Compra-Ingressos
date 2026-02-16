package com.ingressosjogos.bd.util;

import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try {
            Connection conn = ConnectionPostgres.getConection();
            System.out.println("Funcionou!");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
