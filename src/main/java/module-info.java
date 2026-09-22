module ni.edu.uam.examenc1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ni.edu.uam.examenc1 to javafx.fxml;
    exports ni.edu.uam.examenc1;
}