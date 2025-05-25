module com.una.proyecto_ii_progra_ii {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    opens com.una.proyecto_ii_progra_ii to javafx.fxml;
    exports com.una.proyecto_ii_progra_ii;
}
