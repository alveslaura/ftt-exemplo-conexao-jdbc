package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ControllerList implements Initializable {
    @FXML
    public TableView listaCliente;
    private ObservableList<ObservableList> data;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listaCliente = new TableView();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/clientesjdbc", "root", "");

            String SQL = "SELECT * from clientes";
            //ResultSet
            ResultSet rs = con.createStatement().executeQuery(SQL);

            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                listaCliente.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            listaCliente.setItems(data);
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(Exception erro){
            erro.printStackTrace();
        }



    }
}
