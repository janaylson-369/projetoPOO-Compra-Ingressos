/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ingressosjogos.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class FXMLingressosController implements Initializable {

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoEmail;
    @FXML
    private TextField campoCPF;
    @FXML
    private Label labelMensagem;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicializações, se necessário
    }    

    @FXML
    private void salvarTorcedor(ActionEvent event) {
        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String cpf = campoCPF.getText().trim();

        // 1. Validação simples
        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty()) {
            labelMensagem.setText("Por favor, preencha todos os campos!");
            labelMensagem.setStyle("-fx-text-fill: red;");
            return;
        } 
        Torcedor novoTorcedor = new Torcedor(email, nome, cpf);
        labelMensagem.setText("Torcedor" + novoTorcedor.getNome()+ "Pronto para comprar!");
        labelMensagem.setStyle("-fx-text-fill:green;");
        campoEmail.clear();
        campoNome.clear();
        campoCPF.clear();
    }
    }