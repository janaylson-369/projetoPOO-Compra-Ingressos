module com.ingressosjogos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.ingressosjogos to javafx.fxml;
    exports com.ingressosjogos;
}
