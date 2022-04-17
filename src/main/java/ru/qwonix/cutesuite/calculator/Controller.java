package ru.qwonix.cutesuite.calculator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    public Spinner<Integer> costPerFourPersonSP;
    @FXML
    public Spinner<Integer> percentPerFourPersonSP;
    @FXML
    public Spinner<Integer> costPerThreePersonSP;
    @FXML
    public Spinner<Integer> percentPerThreePersonSP;
    @FXML
    public Spinner<Integer> costPerTwoPersonSP;
    @FXML
    public Spinner<Integer> percentPerTwoPersonSP;
    @FXML
    public Spinner<Integer> costPerOnePersonSP;
    @FXML
    public Spinner<Integer> percentPerOnePersonSP;

    @FXML
    public Spinner weeklyDiscountSP;


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

    private final Model model = new Model();

    @FXML
    public void initialize() {
        onCostPerTwoChanged();

    }

    @FXML
    protected void onDailyValueChanged() {
        model.setWeekdaysCount(weekdaysSP.getValue());
        model.setWeekdaysPercent(weekdaysPercentSP.getValue() / 100d + 1);

        model.setWeekendsCount(weekendsSP.getValue());
        model.setWeekendsPercent(weekendsPercentSP.getValue()/ 100d + 1);

        model.setHolidaysCount(holidaysSP.getValue());
        model.setHolidaysPercent(holidaysPercentSP.getValue()/ 100d + 1);

        makeOrder();
    }

    private void makeOrder() {
        List<String> order = model.getOrder();
        listView.setItems(FXCollections.observableList(order));
    }


    @FXML
    protected void onCostPerOneChanged() {
        int costPerOnePerson = costPerOnePersonSP.getValue();
        Guest.One.costPerNight = costPerOnePerson;

        double percentPerOnePerson = ((costPerOnePerson / (double)Guest.Two.costPerNight) - 1) * 100;
        Guest.One.percent = percentPerOnePerson;

        percentPerOnePersonSP.getValueFactory()
                .setValue((int) Math.round(percentPerOnePerson));
        makeOrder();
    }

    @FXML
    protected void onCostPerTwoChanged() {
        int costPerTwoPerson = costPerTwoPersonSP.getValue();

        Guest.Two.costPerNight = costPerTwoPerson;

        onPercentPerOneChanged();
        onPercentPerTwoChanged();
        onPercentPerThreeChanged();
        onPercentPerFourChanged();
        makeOrder();
    }

    @FXML
    protected void onCostPerThreeChanged() {
        int costPerThreePerson = costPerThreePersonSP.getValue();
        Guest.Three.costPerNight = costPerThreePerson;

        double percentPerThreePerson = ((costPerThreePerson / (double)Guest.Two.costPerNight) - 1) * 100;
        Guest.Three.percent = percentPerThreePerson;

        percentPerThreePersonSP.getValueFactory()
                .setValue((int) Math.round(percentPerThreePerson));
        makeOrder();
    }

    @FXML
    protected void onCostPerFourChanged() {
        int costPerFourPerson = costPerFourPersonSP.getValue();
        Guest.Four.costPerNight = costPerFourPerson;

        double percentPerFourPerson = ((costPerFourPerson / (double)Guest.Two.costPerNight) - 1) * 100;
        Guest.Four.percent = percentPerFourPerson;

        percentPerFourPersonSP.getValueFactory()
                .setValue((int) Math.round(percentPerFourPerson));
        makeOrder();
    }


    @FXML
    protected void onPercentPerOneChanged() {
        double percentPerOnePerson = percentPerOnePersonSP.getValue() / 100d + 1;
        int costPerOnePerson = (int) (Guest.Two.costPerNight * percentPerOnePerson);

        Guest.One.costPerNight = costPerOnePerson;
        Guest.One.percent = percentPerOnePerson;

        costPerOnePersonSP.getValueFactory().setValue(costPerOnePerson);
        makeOrder();
    }

    @FXML
    protected void onPercentPerTwoChanged() {
        double percentPerTwoPerson = percentPerTwoPersonSP.getValue() / 100d + 1;

        int costPerTwoPerson = (int) (Guest.Two.costPerNight * percentPerTwoPerson);

        Guest.Two.costPerNight = costPerTwoPerson;
        Guest.Two.percent = percentPerTwoPerson;

        costPerTwoPersonSP.getValueFactory().setValue(costPerTwoPerson);
        makeOrder();
    }

    @FXML
    protected void onPercentPerThreeChanged() {
        double percentPerThreePerson = percentPerThreePersonSP.getValue() / 100d + 1;
        int costPerThreePerson = (int) (Guest.Two.costPerNight * percentPerThreePerson);

        Guest.Three.costPerNight = costPerThreePerson;
        Guest.Three.percent = percentPerThreePerson;

        costPerThreePersonSP.getValueFactory().setValue(costPerThreePerson);
        makeOrder();
    }

    @FXML
    protected void onPercentPerFourChanged() {
        double percentPerFourPerson = percentPerFourPersonSP.getValue() / 100d + 1;
        int costPerFourPerson = (int) (Guest.Two.costPerNight * percentPerFourPerson);

        Guest.Four.costPerNight = costPerFourPerson;
        Guest.Four.percent = percentPerFourPerson;

        costPerFourPersonSP.getValueFactory().setValue(costPerFourPerson);
        makeOrder();
    }



    @FXML
    public void onSelectFourPeople(ActionEvent actionEvent) {
        String userData = (String)
                ((RadioButton) (actionEvent.getSource()))
                        .getUserData();
        model.setGuest(Guest.valueByCount(Integer.parseInt(userData)));
        makeOrder();
    }

    @FXML
    public void onWeeklyDiscountValueChanged(MouseEvent mouseEvent) {
        Spinner<Integer> source = (Spinner<Integer>) (mouseEvent.getSource());
        model.setWeeklyDiscountPercent(source.getValue() / 100d + 1);
        makeOrder();
    }
}