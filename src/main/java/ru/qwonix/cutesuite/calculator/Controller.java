package ru.qwonix.cutesuite.calculator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class Controller {

    private final Model model = new Model();
    @FXML
    private Spinner<Integer> costPerFourPersonSP;
    @FXML
    private Spinner<Integer> percentPerFourPersonSP;
    @FXML
    private Spinner<Integer> costPerThreePersonSP;
    @FXML
    private Spinner<Integer> percentPerThreePersonSP;
    @FXML
    private Spinner<Integer> costPerTwoPersonSP;
    @FXML
    private Spinner<Integer> percentPerTwoPersonSP;
    @FXML
    private Spinner<Integer> costPerOnePersonSP;
    @FXML
    private Spinner<Integer> percentPerOnePersonSP;

    @FXML
    public Spinner<Integer>  weeklyDiscountSP;

    @FXML
    private Spinner<Integer> weekdaysSP;
    @FXML
    private Spinner<Integer> weekdaysPercentSP;
    @FXML
    private Spinner<Integer> holidaysSP;
    @FXML
    private Spinner<Integer> holidaysPercentSP;
    @FXML
    private Spinner<Integer> weekendsSP;
    @FXML
    private Spinner<Integer> weekendsPercentSP;

    @FXML
    private ListView<String> listView;

    private void makeOrder() {
        List<String> order = model.getOrder();
        listView.setItems(FXCollections.observableList(order));
    }

    @FXML
    public void initialize() {

        weeklyDiscountSP.getValueFactory()
                .setValue(model.getWeeklyDiscountPercent());

        costPerOnePersonSP.getValueFactory()
                .setValue(Guest.One.getCost());
        costPerTwoPersonSP.getValueFactory()
                .setValue(Guest.Two.getCost());
        costPerThreePersonSP.getValueFactory()
                .setValue(Guest.Three.getCost());
        costPerFourPersonSP.getValueFactory()
                .setValue(Guest.Four.getCost());

        percentPerOnePersonSP.getValueFactory()
                .setValue(Guest.One.getPercent());
        percentPerTwoPersonSP.getValueFactory()
                .setValue(Guest.Two.getPercent());
        percentPerThreePersonSP.getValueFactory()
                .setValue(Guest.Three.getPercent());
        percentPerFourPersonSP.getValueFactory()
                .setValue(Guest.Four.getPercent());

        weekdaysSP.getValueFactory()
                .setValue(model.getWeekdaysCount());
        weekdaysPercentSP.getValueFactory()
                .setValue(model.getWeekdaysPercent());
        weekendsSP.getValueFactory()
                .setValue(model.getWeekendsCount());
        weekendsPercentSP.getValueFactory()
                .setValue(model.getWeekendsPercent());
        holidaysSP.getValueFactory()
                .setValue(model.getHolidaysCount());
        holidaysPercentSP.getValueFactory()
                .setValue(model.getHolidaysPercent());

        makeOrder();
    }

    @FXML
    protected void onDailyValueChanged() {
        model.setWeekdaysCount(weekdaysSP.getValue());
        model.setWeekdaysPercent(weekdaysPercentSP.getValue());

        model.setWeekendsCount(weekendsSP.getValue());
        model.setWeekendsPercent(weekendsPercentSP.getValue());

        model.setHolidaysCount(holidaysSP.getValue());
        model.setHolidaysPercent(holidaysPercentSP.getValue());

        makeOrder();
    }


    @FXML
    protected void onCostPerOnePersonChanged() {
        int costPerOnePerson = costPerOnePersonSP.getValue();

        Guest.One.setCost(costPerOnePerson);

        percentPerOnePersonSP.getValueFactory()
                .setValue(Guest.One.getPercent());
        makeOrder();
    }

    @FXML
    protected void onCostPerTwoPersonsChanged() {
        int costPerTwoPerson = costPerTwoPersonSP.getValue();

        Guest.Two.setCost(costPerTwoPerson);

        onPercentPerOneChanged();
        onPercentPerTwoChanged();
        onPercentPerThreeChanged();
        onPercentPerFourChanged();
        makeOrder();
    }

    @FXML
    protected void onCostPerThreePersonsChanged() {
        int costPerThreePerson = costPerThreePersonSP.getValue();

        Guest.Three.setCost(costPerThreePerson);

        percentPerThreePersonSP.getValueFactory()
                .setValue(Guest.Three.getPercent());
        makeOrder();
    }

    @FXML
    protected void onCostPerFourPersonsChanged() {
        int costPerFourPerson = costPerFourPersonSP.getValue();

        Guest.Four.setCost(costPerFourPerson);

        percentPerFourPersonSP.getValueFactory()
                .setValue(Guest.Four.getPercent());
        makeOrder();
    }


    @FXML
    protected void onPercentPerOneChanged() {
        int percentPerOnePerson = percentPerOnePersonSP.getValue();
        Guest.One.setPercent(percentPerOnePerson);

        costPerOnePersonSP.getValueFactory()
                .setValue(Guest.One.getCost());
        makeOrder();
    }

    @FXML
    protected void onPercentPerTwoChanged() {
        int percentPerTwoPerson = percentPerTwoPersonSP.getValue();
        Guest.Two.setPercent(percentPerTwoPerson);

        costPerTwoPersonSP.getValueFactory()
                .setValue(Guest.Two.getCost());
        makeOrder();
    }

    @FXML
    protected void onPercentPerThreeChanged() {
        int percentPerThreePerson = percentPerThreePersonSP.getValue();
        Guest.Three.setPercent(percentPerThreePerson);

        costPerThreePersonSP.getValueFactory()
                .setValue(Guest.Three.getCost());
        makeOrder();
    }

    @FXML
    protected void onPercentPerFourChanged() {
        int percentPerFourPerson = percentPerFourPersonSP.getValue();
        Guest.Four.setPercent(percentPerFourPerson);

        costPerFourPersonSP.getValueFactory()
                .setValue(Guest.Four.getCost());
        makeOrder();
    }


    @FXML
    public void onSelectedPeopleCountChange(ActionEvent actionEvent) {
        String userData = (String)
                ((RadioButton) (actionEvent.getSource()))
                        .getUserData();
        model.setGuest(Guest.valueByCount(Integer.parseInt(userData)));
        makeOrder();
    }

    @FXML
    public void onWeeklyDiscountValueChanged(MouseEvent mouseEvent) {
        Spinner<Integer> source = (Spinner<Integer>) (mouseEvent.getSource());
        model.setWeeklyDiscountPercent(source.getValue());
        makeOrder();
    }
}