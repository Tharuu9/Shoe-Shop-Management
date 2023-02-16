package lk.ijse.NiharaShoe.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Tharuu") && txtPassword.getText().equalsIgnoreCase("12345")) {
            Parent parent = FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Loging Failed,Invalid User Name or Password...!").show();

        }
    }
}
