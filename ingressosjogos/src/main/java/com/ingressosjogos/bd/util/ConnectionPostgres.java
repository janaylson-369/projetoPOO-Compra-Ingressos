package com.ingressosjogos.bd.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPostgres {

    private static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static String USER = "postgres";
    private static String PASSWORD = "1234";

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
