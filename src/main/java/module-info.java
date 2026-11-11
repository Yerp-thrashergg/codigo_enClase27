module cl.duoc.veterinaria {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens cl.duoc.veterinaria to javafx.fxml;
    opens cl.duoc.veterinaria.controller to javafx.fxml;
    opens cl.duoc.veterinaria.model to javafx.base;
    exports cl.duoc.veterinaria;
    exports cl.duoc.veterinaria.controller;
}