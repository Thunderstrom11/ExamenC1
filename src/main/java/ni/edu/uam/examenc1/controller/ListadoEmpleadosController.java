package ni.edu.uam.examenc1.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ni.edu.uam.examenc1.model.Empleado;

public class ListadoEmpleadosController {

    // lista que se carga desde Empleado.registrados cada vez que se abre la ventana
    private final ObservableList<Empleado> empleados = FXCollections.observableArrayList();

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
        // enlace de columnas con los datos del empleado (getters de Lombok)
        colNombres.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getNombres()));
        colApellidos.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getApellidos()));
        colCargo.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getCargo()));
        colSalario.setCellValueFactory(d -> new SimpleStringProperty(String.format("$%,.2f", d.getValue().getSalario())));

        // carga de datos desde el modelo y total (se refresca al abrir la ventana)
        empleados.setAll(Empleado.registrados);
        tbvEmpleados.setItems(empleados);
        lblTotal.setText("Total de empleados: " + empleados.size());
    }

    // cierra esta ventana y regresa al formulario anterior
    @FXML
    private void volver() {
        ((Stage) btnVolver.getScene().getWindow()).close();
    }
}
