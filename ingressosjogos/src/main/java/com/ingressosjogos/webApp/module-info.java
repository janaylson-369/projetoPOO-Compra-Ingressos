module com.ingressosjogos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens ingressosjogos to javafx.fxml;
    exports ingressosjogos;
}
