package ni.edu.uam.examenc1;

import javafx.application.Application;
import ni.edu.uam.examenc1.application.RegistroApplication;

public class Launcher {
    public static void main(String[] args) {
        // punto de entrada: evita problemas de modulos al correr desde el IDE
        Application.launch(RegistroApplication.class, args);
    }
}
