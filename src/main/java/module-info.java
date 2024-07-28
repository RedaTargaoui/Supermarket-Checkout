module com.supermarketcheckout {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.logging;

    exports com.supermarketcheckout;
    exports com.supermarketcheckout.Controller;

    opens com.supermarketcheckout.View to javafx.fxml;
    opens com.supermarketcheckout.Controller to javafx.fxml;
}
