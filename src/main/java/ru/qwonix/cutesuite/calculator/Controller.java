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
                .setValue(Day.WEEKDAY.count);
        weekdaysPercentSP.getValueFactory()
                .setValue(Day.WEEKDAY.percent);
        weekendsSP.getValueFactory()
                .setValue(Day.WEEKEND.count);
        weekendsPercentSP.getValueFactory()
                .setValue(Day.WEEKEND.percent);
        holidaysSP.getValueFactory()
                .setValue(Day.HOLIDAY.count);
        holidaysPercentSP.getValueFactory()
                .setValue(Day.HOLIDAY.percent);

        makeOrder();
    }

    @FXML
    protected void onDailyValueChanged() {
        Day.WEEKDAY.count = weekdaysSP.getValue();
        Day.WEEKDAY.percent = weekdaysPercentSP.getValue();

        Day.WEEKEND.count = weekendsSP.getValue();
        Day.WEEKEND.percent = weekendsPercentSP.getValue();

        Day.HOLIDAY.count = holidaysSP.getValue();
        Day.HOLIDAY.percent = holidaysPercentSP.getValue();

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