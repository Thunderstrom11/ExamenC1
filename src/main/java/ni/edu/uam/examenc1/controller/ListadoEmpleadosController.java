package ni.edu.uam.examenc1.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListadoEmpleadosController {

    //  el formulario 1 agrega aqui y este formulario la muestra
    public static final ObservableList<Empleado> empleados = FXCollections.observableArrayList();

    @FXML
    private TableView<Empleado> tbvEmpleados;
    @FXML
    private TableColumn<Empleado, String> colNombres;
    @FXML
    private TableColumn<Empleado, String> colApellidos;
    @FXML
    private TableColumn<Empleado, String> colCargo;
    @FXML
    private TableColumn<Empleado, String> colSalario;
    @FXML
    private Label lblTotal;
    @FXML
    private Button btnVolver;

    @FXML
    private void initialize() {
        // enlace de columnas con los datos del empleado
        colNombres.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().nombres()));
        colApellidos.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().apellidos()));
        colCargo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().cargo()));
        colSalario.setCellValueFactory(d -> new SimpleStringProperty(String.format("$%,.2f", d.getValue().salario())));

        // carga de datos y actualizacion del total
        tbvEmpleados.setItems(empleados);
        actualizarTotal();
        empleados.addListener((ListChangeListener<Empleado>) change -> actualizarTotal());
    }

    private void actualizarTotal() {
        lblTotal.setText("Total de empleados: " + empleados.size());
    }

    //  cierra esta ventana y regresa al formulario anterior
    @FXML
    private void volver() {
        ((Stage) btnVolver.getScene().getWindow()).close();
    }

    // Dato minimo del empleado (nombres, apellidos, cargo, salario)
    public record Empleado(String nombres, String apellidos, String cargo, double salario) {
    }
}
