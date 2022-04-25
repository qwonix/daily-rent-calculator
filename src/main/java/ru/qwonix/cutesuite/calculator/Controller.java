package ru.qwonix.cutesuite.calculator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import ru.qwonix.cutesuite.calculator.entity.Day;
import ru.qwonix.cutesuite.calculator.entity.Guest;

import java.util.List;

public class Controller {

    @FXML
    public Spinner<Integer> weeklyDiscountSP;
    private Model model;
    @FXML
    private Spinner<Integer> costPerOnePersonSP, costPerTwoPersonSP, costPerThreePersonSP, costPerFourPersonSP;

    @FXML
    private Spinner<Integer> percentPerOnePersonSP, percentPerTwoPersonSP, percentPerThreePersonSP, percentPerFourPersonSP;

    @FXML
    private Spinner<Integer> weekdaysSP, holidaysSP, weekendsSP;

    @FXML
    private Spinner<Integer> weekdaysPercentSP, holidaysPercentSP, weekendsPercentSP;

    @FXML
    private ListView<String> listView;

    private void makeOrder() {
        List<String> order = model.getOrder();
        listView.setItems(FXCollections.observableList(order));
    }

    @FXML
    public void initialize() {
        model = new Model();

        weeklyDiscountSP.getValueFactory()
                .setValue(model.getWeeklyDiscountPercent());

        costPerOnePersonSP.getValueFactory()
                .setValue(Guest.ONE.getCost());
        costPerTwoPersonSP.getValueFactory()
                .setValue(Guest.TWO.getCost());
        costPerThreePersonSP.getValueFactory()
                .setValue(Guest.THREE.getCost());
        costPerFourPersonSP.getValueFactory()
                .setValue(Guest.FOUR.getCost());

        percentPerOnePersonSP.getValueFactory()
                .setValue(Guest.ONE.getPercent());
        percentPerTwoPersonSP.getValueFactory()
                .setValue(Guest.TWO.getPercent());
        percentPerThreePersonSP.getValueFactory()
                .setValue(Guest.THREE.getPercent());
        percentPerFourPersonSP.getValueFactory()
                .setValue(Guest.FOUR.getPercent());

        weekdaysSP.getValueFactory()
                .setValue(Day.WEEKDAY.getCount());
        weekdaysPercentSP.getValueFactory()
                .setValue(Day.WEEKDAY.getPercent());
        weekendsSP.getValueFactory()
                .setValue(Day.WEEKEND.getCount());
        weekendsPercentSP.getValueFactory()
                .setValue(Day.WEEKEND.getPercent());
        holidaysSP.getValueFactory()
                .setValue(Day.HOLIDAY.getCount());
        holidaysPercentSP.getValueFactory()
                .setValue(Day.HOLIDAY.getPercent());

        makeOrder();
    }

    @FXML
    protected void onDailyValueChanged() {
        Day.WEEKDAY.setCount(weekdaysSP.getValue());
        Day.WEEKDAY.setPercent(weekdaysPercentSP.getValue());

        Day.WEEKEND.setCount(weekendsSP.getValue());
        Day.WEEKEND.setPercent(weekendsPercentSP.getValue());

        Day.HOLIDAY.setCount(holidaysSP.getValue());
        Day.HOLIDAY.setPercent(holidaysPercentSP.getValue());

        makeOrder();
    }


    @FXML
    protected void onCostPerOnePersonChanged() {
        int costPerOnePerson = costPerOnePersonSP.getValue();

        Guest.ONE.setCost(costPerOnePerson);

        percentPerOnePersonSP.getValueFactory()
                .setValue(Guest.ONE.getPercent());
        makeOrder();
    }

    @FXML
    protected void onCostPerTwoPersonsChanged() {
        int costPerTwoPerson = costPerTwoPersonSP.getValue();

        Guest.TWO.setCost(costPerTwoPerson);

        onPercentPerOneChanged();
        onPercentPerTwoChanged();
        onPercentPerThreeChanged();
        onPercentPerFourChanged();
        makeOrder();
    }

    @FXML
    protected void onCostPerThreePersonsChanged() {
        int costPerThreePerson = costPerThreePersonSP.getValue();

        Guest.THREE.setCost(costPerThreePerson);

        percentPerThreePersonSP.getValueFactory()
                .setValue(Guest.THREE.getPercent());
        makeOrder();
    }

    @FXML
    protected void onCostPerFourPersonsChanged() {
        int costPerFourPerson = costPerFourPersonSP.getValue();

        Guest.FOUR.setCost(costPerFourPerson);

        percentPerFourPersonSP.getValueFactory()
                .setValue(Guest.FOUR.getPercent());
        makeOrder();
    }


    @FXML
    protected void onPercentPerOneChanged() {
        int percentPerOnePerson = percentPerOnePersonSP.getValue();
        Guest.ONE.setPercent(percentPerOnePerson);

        costPerOnePersonSP.getValueFactory()
                .setValue(Guest.ONE.getCost());
        makeOrder();
    }

    @FXML
    protected void onPercentPerTwoChanged() {
        int percentPerTwoPerson = percentPerTwoPersonSP.getValue();
        Guest.TWO.setPercent(percentPerTwoPerson);

        costPerTwoPersonSP.getValueFactory()
                .setValue(Guest.TWO.getCost());
        makeOrder();
    }

    @FXML
    protected void onPercentPerThreeChanged() {
        int percentPerThreePerson = percentPerThreePersonSP.getValue();
        Guest.THREE.setPercent(percentPerThreePerson);

        costPerThreePersonSP.getValueFactory()
                .setValue(Guest.THREE.getCost());
        makeOrder();
    }

    @FXML
    protected void onPercentPerFourChanged() {
        int percentPerFourPerson = percentPerFourPersonSP.getValue();
        Guest.FOUR.setPercent(percentPerFourPerson);

        costPerFourPersonSP.getValueFactory()
                .setValue(Guest.FOUR.getCost());
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