package com.ingressosjogos.bd.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPostgres {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // public ConnectionPostgres(){
    //     this.URL = "jdbc:postgresql://localhost:5432/postgres";
    //     this.USER = "postgres";
    //     this.PASSWORD = "1234";
    // }

    public static Connection getConection()throws Exception{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
