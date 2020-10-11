package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

import javax.swing.*;
import java.sql.*;
import java.io.IOException;

public class ControllerMain {

    @FXML
    public TextField txtNome;
    @FXML
    public TextField txtEndereco;
    @FXML
    public TextField txtCPF;


    public void saveClient(MouseEvent event) throws IOException {

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/clientesjdbc", "root", "");

            String query = "INSERT INTO clientes (nome, endereco, cpf) VALUES(?,?,?)";

            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, txtNome.getText());
            stmt.setString(2, txtEndereco.getText());
            stmt.setString(3, txtCPF.getText());

            stmt.executeUpdate();

            stmt.close();
            con.close();

        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possível encontrar a classe");
        } catch (SQLException ex) {
            System.out.println("Ocorreu um erro de SQL.");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Salvo com sucesso!");
        alert.showAndWait();
    }
}