module com.brendondugan.command_generator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.brendondugan.command_generator to javafx.fxml;
    exports com.brendondugan.command_generator;
}