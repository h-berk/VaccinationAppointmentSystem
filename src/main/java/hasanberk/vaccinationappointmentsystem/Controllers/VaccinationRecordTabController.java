package hasanberk.vaccinationappointmentsystem.Controllers;

import hasanberk.vaccinationappointmentsystem.ADT.*;
import hasanberk.vaccinationappointmentsystem.App;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class VaccinationRecordTabController {
    public ListView<String> listView;
    public ComboBox patientComboBox;

    public void update() {
        patientComboBox.getItems().clear();
        for (Patient p : App.store.getPatients()){
            patientComboBox.getItems().add(p.getName());
        }
        patientComboBox.getSelectionModel().selectFirst();
    }

    public void getSelectedPatient(ActionEvent actionEvent) {
    }
}
