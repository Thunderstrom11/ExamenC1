module ni.edu.uam.examenc1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.examenc1 to javafx.fxml;
    exports ni.edu.uam.examenc1;
    exports ni.edu.uam.examenc1.controller;
    opens ni.edu.uam.examenc1.controller to javafx.fxml;
    exports ni.edu.uam.examenc1.application;
    opens ni.edu.uam.examenc1.application to javafx.fxml;
    exports ni.edu.uam.examenc1.model;
    opens ni.edu.uam.examenc1.model to javafx.base;
}