package hasanberk.vaccinationappointmentsystem.Controllers;

import hasanberk.vaccinationappointmentsystem.ADT.Appointment;
import hasanberk.vaccinationappointmentsystem.ADT.Booth;
import hasanberk.vaccinationappointmentsystem.ADT.Centre;
import hasanberk.vaccinationappointmentsystem.App;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AppointmentTabController {
    public ListView<String> listView;
    public DatePicker dateDatePicker;
    public ComboBox<String> timeComboBox;
    public ComboBox<String> typeComboBox;
    public TextField vaccineIdentifierTextField;
    public TextField vaccinatorDetailsTextField;
    public TextField ppsTextField;
    public ComboBox<String> centreComboBox;
    public ComboBox<String> boothComboBox;
    public static AppointmentTabController instance;
    public ListView listViewCompleted;
    private Centre selectedCentre = null;
    private Booth selectedBooth = null;

    public void initialize() {
        instance = this;
        timeComboBox.getItems().addAll("9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");
        typeComboBox.getItems().addAll("Pfizer/BioNTech", "Janssen", "Moderna");
    }

    public void addAppointmentClicked(ActionEvent actionEvent) {
        LocalDate date = dateDatePicker.getValue();
        String time = timeComboBox.getValue();
        String type = typeComboBox.getValue();
        String vaccineIdentifier = vaccineIdentifierTextField.getText();
        String vaccinatorDetails = vaccinatorDetailsTextField.getText();
        String pps = ppsTextField.getText();
        Booth b = getSelectedBooth();

        try {
            Appointment a = new Appointment(date, time, type, vaccineIdentifier, vaccinatorDetails, pps);
            if (!b.addAppointment(a)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error adding appointment.", ButtonType.OK);
                alert.showAndWait(); //waits for confirmation before proceeding
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView(null);
    }

    public void deleteAppointmentClicked(ActionEvent actionEvent) {
        Booth b = getSelectedBooth();

        try {
            String selected = listView.getSelectionModel().getSelectedItem();
            for (Appointment a : b.getAppointments()) {
                if (a.toString().equals(selected)) {
                    b.deleteAppointment(a);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment deleted successfully.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView(null);
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

    public Booth getSelectedBooth() {
        String boothIdentifier = boothComboBox.getSelectionModel().getSelectedItem();

        Booth booth = null;
        Centre centre = getSelectedCentre();
        for (Booth b : centre.getBooths()) {
            if (b.getBoothIdentifier().equals(boothIdentifier)) {
                booth = b;
            }
        }
        if (booth != null) {
            selectedBooth = booth;
        }
        return selectedBooth;
    }

    public void updateListView(Event event) {
        Booth b = getSelectedBooth();
        listView.getItems().clear();
        if(b!=null){
            for (Appointment a : b.getAppointments()) {
                listView.getItems().add(a.toString());
            }
        }
        listViewCompleted.getItems().clear();
        if(b!=null) {
            for (Appointment a : b.getCompletedAppointments()) {
                listViewCompleted.getItems().add(a.toString());
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

    public void updateBoothComboBox() {
        boothComboBox.getItems().clear();
        Centre centre = getSelectedCentre();
        if (centre != null) {
            for (Booth b : centre.getBooths()) {
                boothComboBox.getItems().add(b.getBoothIdentifier());
            }
        }
        boothComboBox.getSelectionModel().selectFirst();
        updateListView(null);
    }


    public void completeAppointmentClicked(ActionEvent actionEvent) {
        Booth b = getSelectedBooth();
        try {
            String selected = listView.getSelectionModel().getSelectedItem();
            for (Appointment a : b.getAppointments()){
                if(a.toString().equals(selected)){
                    b.completeAppointment(a);
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView(null);
    }

    public void deleteCompletedAppointment(ActionEvent actionEvent) {
        Booth b = getSelectedBooth();
        try {
            String selected = listView.getSelectionModel().getSelectedItem();
            for (Appointment a : b.getCompletedAppointments()) {
                if (a.toString().equals(selected)) {
                    b.deleteCompletedAppointment(a);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Appointment deleted successfully.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView(null);
    }

}
