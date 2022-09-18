package hasanberk.vaccinationappointmentsystem.Controllers;

import hasanberk.vaccinationappointmentsystem.ADT.Patient;
import hasanberk.vaccinationappointmentsystem.App;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.time.LocalDate;

public class PatientTabController {
    public ListView<String> listView;
    public DatePicker dobDatePicker;
    public TextField ppsTextField;
    public TextField eirCodeTextField;
    public TextField nameTextField;
    public TextField telephoneTextField;
    public TextField emailTextField;
    public CheckBox accessibilityCheckBox;

    public void addPatientClicked(ActionEvent actionEvent) {
        String pps = ppsTextField.getText();
        String name = nameTextField.getText();
        String eirCode = eirCodeTextField.getText();
        String telephone = telephoneTextField.getText();
        String email = emailTextField.getText();
        LocalDate dob = dobDatePicker.getValue();
        boolean accessibility = accessibilityCheckBox.isSelected();
        try {
            Patient p = new Patient(pps, name, eirCode, telephone, email, dob, accessibility);
            if (!App.store.addPatient(p)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Patient already exists", ButtonType.OK);
                alert.showAndWait(); //waits for confirmation before proceeding
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
            e.printStackTrace();
        }
        AppointmentTabController.instance.update();
        AppointmentTabController.instance.updateBoothComboBox();
        updateListView();
    }

    public void deletePatientClicked(ActionEvent actionEvent) {
        try {
            String selected = listView.getSelectionModel().getSelectedItem();
            for(Patient p : App.store.getPatients()){
                if(p.toString().equals(selected)){
                    App.store.deletePatient(p);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient deleted successfully.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView();
    }

    public void updateListView() {
        listView.getItems().clear();
        for (Patient p : App.store.getPatients()) {
            listView.getItems().add(p.toString());
        }
    }
}
