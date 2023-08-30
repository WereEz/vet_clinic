module com.example.vet_clinic {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.vet_clinic to javafx.fxml, com.google.gson;
    exports com.example.vet_clinic;
}