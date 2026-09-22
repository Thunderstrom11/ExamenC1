package ni.edu.uam.examenc1.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Formulario 1 (registro) como pantalla inicial
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroApplication.class.getResource("/ni/edu/uam/examenc1/fxml/registro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Gestion de Empleados - Registro");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
