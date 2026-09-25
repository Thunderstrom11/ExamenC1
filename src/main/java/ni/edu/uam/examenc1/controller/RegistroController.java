package ni.edu.uam.examenc1.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ni.edu.uam.examenc1.model.Empleado;

import java.io.IOException;
import java.util.Optional;

public class RegistroController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtCargo;
    @FXML
    private TextField txtSalario;

   @FXML
    public void initialize(){
       txtSalario.setTextFormatter(new TextFormatter<>(change ->
               change.getControlNewText().matches("\\d*([.,]\\d*)?") ? change : null));
   }

    @FXML
    private void guardar() {
        String nombres = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String cargo = txtCargo.getText().trim();
        String salarioTexto = txtSalario.getText().trim();
        if (nombres.isEmpty() || apellidos.isEmpty() || cargo.isEmpty() || salarioTexto.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos incompletos", "Debe completar todos los campos");
            return;
        }
        // validacion el salario debe ser un numero
        double salario;
        try {
            salario = Double.parseDouble(salarioTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Salario invalido", "El salario debe ser un numero");
            return;
        }

        // el salario debe ser mayor a cero
        if (salario <= 0) {
            mostrarAlerta(Alert.AlertType.ERROR, "Salario invalido", "El salario debe ser mayor a 0");
            return;
        }

        // crea el empleado y lo guarda en la lista compartida en model
        Empleado empleado = new Empleado(nombres, apellidos, cargo, salario);
        Empleado.registrados.add(empleado);

        mostrarAlerta(Alert.AlertType.INFORMATION, "Registro exitoso",
                "Empleado registrado: " + empleado.getNombreCompleto());
        limpiarCampos();
    }

    // sbre el Formulario 2 (listado de empleados)
    @FXML
    private void verListado() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    RegistroController.class.getResource("/ni/edu/uam/examenc1/fxml/listado-empleados.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load(), 720, 460));
            stage.setTitle("Gestion de Empleados - Listado");
            stage.initModality(Modality.APPLICATION_MODAL); // no deja editar el formulario anterior
            stage.showAndWait();
        } catch (IOException e) { //errores de lectura
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No fue posible abrir el listado de empleados");
        }
    }

    // Salir con confirmacion
    @FXML
    private void salir() {
        if (confirmar("Salir", "Desea salir del programa?")) {
            Platform.exit();
        }
    }

    // limpia el formulario y deja el cursor en el primer campo
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtCargo.clear();
        txtSalario.clear();
        txtNombre.requestFocus();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private boolean confirmar(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        Optional<ButtonType> respuesta = alert.showAndWait();
        return respuesta.isPresent() && respuesta.get() == ButtonType.OK;
    }
}
