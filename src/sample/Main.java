package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("screenMain.fxml"));
        primaryStage.setTitle("Cadastro de Clientes.");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        { launch(args);}

    }
}
