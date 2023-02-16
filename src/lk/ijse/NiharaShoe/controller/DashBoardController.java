package lk.ijse.NiharaShoe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DashBoardController {

    public JFXButton btnHome;
    public JFXButton btnCustomer;
    public JFXButton btnSupplier;
    public JFXButton btnItem;
    public JFXButton btnEmployee;
    public JFXButton btnLogout;
    public JFXTextField txtSearchBar;
    public AnchorPane contest;
    public JFXButton btnAllOrder;
    public Label lblTime;
    public Label lblDate;
    public ImageView closeImg;
    public Label lblCustomerCount;
    public Label lblItemCount;
    public Label lblSupplierCount;
    public Label lblEmployeeCount;
    public JFXButton btnOrder;

    public void loadDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
    public void initialize() throws SQLException, ClassNotFoundException {
        RunningTime();
        loadDate();
        loadLabel();
    }
    private void loadLabel() throws SQLException, ClassNotFoundException {
        ResultSet rs=CrudUtil.executeQuery("SELECT COUNT(custID) FROM Customer");
        rs.next();
        lblCustomerCount.setText(String.valueOf(rs.getString(1)));

        ResultSet rs2=CrudUtil.executeQuery("SELECT COUNT(itemID) FROM Item");
        rs2.next();
        lblItemCount.setText(String.valueOf(rs2.getString(1)));

        ResultSet rs3=CrudUtil.executeQuery("SELECT COUNT(supplierID) FROM Supplier");
        rs3.next();
        lblSupplierCount.setText(String.valueOf(rs3.getString(1)));

        ResultSet rs4=CrudUtil.executeQuery("SELECT COUNT(empID) FROM Employee");
        rs4.next();
        lblEmployeeCount.setText(String.valueOf(rs4.getString(1)));
    }

    private void RunningTime() {
        final Thread thread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    lblTime.setText(time);
                });
            }
        });
        thread.start();
    }
    public void HomeOnAction(ActionEvent actionEvent) throws IOException {
    Stage stage=(Stage) contest.getScene().getWindow();
       stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"))));
    }

    public void EmployeeOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/EmployeeDetailsForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void LogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contest.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
    }

    public void CustomerOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/CustomerForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }
    public void SupplierOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/SupplierForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }
    public void ItemOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/ItemForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }
    public void OrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/OrderForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }
    public void AllOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/PlaceOrderForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }

    public void CloseOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) contest.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/LoginForm.fxml"))));
        stage.show();
    }

    public void SearchBarOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent = FXMLLoader.
                load(getClass().getResource("../view/ItemForm.fxml"));
        contest.getChildren().clear();
        contest.getChildren().add(parent);
    }
}
