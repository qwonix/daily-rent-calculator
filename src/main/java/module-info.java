module ru.qwonix.cutesuite.calculatior {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.json;


    opens ru.qwonix.cutesuite.calculator to javafx.fxml;
    exports ru.qwonix.cutesuite.calculator;
    exports ru.qwonix.cutesuite.calculator.entity;
    opens ru.qwonix.cutesuite.calculator.entity to javafx.fxml;
}