package hasanberk.vaccinationappointmentsystem.Controllers;

import hasanberk.vaccinationappointmentsystem.ADT.Booth;
import hasanberk.vaccinationappointmentsystem.ADT.Centre;
import hasanberk.vaccinationappointmentsystem.App;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

public class BoothTabController {
    public ListView<String> listView;
    public TextField boothIdentifierTextField;
    public Spinner<Integer> floorLevelSpinner;
    public CheckBox accessibilityCheckBox;
    public static BoothTabController instance; //to reference booth tab controller from another controller
    public ComboBox<String> centreComboBox;
    private Centre selectedCentre = null;

    public void initialize() {
        instance = this;
        floorLevelSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500));
        floorLevelSpinner.setEditable(true);
        update();
    }

    public Centre getSelectedCentre() {
        String centreName = centreComboBox.getSelectionModel().getSelectedItem();

        Centre centre = null;
        for (Centre c : App.store.getCentres()) {
            if (c.getName().equals(centreName)) {
                centre = c;
            }
        }
        if (centre != null) {
            selectedCentre = centre;
        }
        return selectedCentre;
    }

    public void addBoothClicked(ActionEvent actionEvent) {
        String boothIdentifier = boothIdentifierTextField.getText();
        int floorLevel = floorLevelSpinner.getValue();
        boolean accessibility = accessibilityCheckBox.isSelected();
        Centre c = getSelectedCentre();

        try {
            Booth b = new Booth(boothIdentifier, floorLevel, accessibility);
            if(!c.addBooth(b)){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Booth already exists", ButtonType.OK);
                alert.showAndWait(); //waits for confirmation before proceeding
            }
            AppointmentTabController.instance.update();
            AppointmentTabController.instance.updateBoothComboBox();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView(null);
    }

    public void deleteBoothClicked(ActionEvent actionEvent) {
        try {
            String selected = listView.getSelectionModel().getSelectedItem();
            for(Booth b : getSelectedCentre().getBooths()){
                if(b.toString().equals(selected)){
                    getSelectedCentre().deleteBooth(b);
                    AppointmentTabController.instance.updateBoothComboBox();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Booth deleted successfully.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
            AppointmentTabController.instance.update();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView(null);
    }

    public void updateListView(Event event) {
        Centre c = getSelectedCentre();
        if(c!=null){
            listView.getItems().clear();
            for (Booth b : c.getBooths()) {
                listView.getItems().add(b.toString());
            }
        }
    }

    public void update() {
        centreComboBox.getItems().clear();
        for (Centre c : App.store.getCentres()){
            centreComboBox.getItems().add(c.getName());
        }
        centreComboBox.getSelectionModel().selectFirst();
    }
}
