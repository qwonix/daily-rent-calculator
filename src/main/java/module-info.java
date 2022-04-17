module ru.qwonix.cutesuite.calculatior {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens ru.qwonix.cutesuite.calculator to javafx.fxml;
    exports ru.qwonix.cutesuite.calculator;
}