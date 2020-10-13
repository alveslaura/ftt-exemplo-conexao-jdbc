package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.io.IOException;

public class ControllerMain {

    @FXML
    public TextField txtNome;
    @FXML
    public TextField txtEndereco;
    @FXML
    public TextField txtCPF;
    @FXML
    public Label lblObgNome;
    @FXML
    public Label lblObgEnd;
    @FXML
    public Label lblObgCpf;
    @FXML
    public Label lblCpfInvalido;

        public void saveClient (MouseEvent event) throws IOException {
            validaDados();
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

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Salvo com sucesso!");
                alert.showAndWait();

            } catch (ClassNotFoundException ex) {
                System.out.println("Não foi possível encontrar a classe");
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro de SQL.");
            }
        }

    public void validaDados(){
        try {
            if (txtNome.getText().length() > 0) {
                lblObgNome.setVisible(false);
            } else {
                lblObgNome.setVisible(true);
            }
            if (txtEndereco.getText().length() > 0) {
                lblObgEnd.setVisible(false);
            } else {
                lblObgEnd.setVisible(true);
            }
            if (txtCPF.getText().length() > 0) {
                lblObgCpf.setVisible(false);
            } else {
                lblObgCpf.setVisible(true);
            }
            if (!txtCPF.getText().matches("[0-9]")) {
                lblCpfInvalido.setVisible(true);
            } else {
                lblCpfInvalido.setVisible(false);
            }
            if ((txtNome.getText().length() > 0)
                    && (txtEndereco.getText().length() > 0)
                    && (txtCPF.getText().length() > 0)) {
                JOptionPane.showMessageDialog(null, "Dados Validados!!");
            }

        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Confira os campos Obrigatórios!!");
        }
    }
}