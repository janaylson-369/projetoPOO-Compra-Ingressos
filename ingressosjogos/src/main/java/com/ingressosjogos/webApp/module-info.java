module com.ingressosjogos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.ingressosjogos to javafx.fxml;
    exports com.ingressosjogos.webApp;
}
