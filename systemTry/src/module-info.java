module systemTry {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires com.rabbitmq.client;
    requires com.rabbitmq.jms;
    requires java.sql;

    opens sample;
}