package hasanberk.vaccinationappointmentsystem.Controllers;

import hasanberk.vaccinationappointmentsystem.ADT.Centre;
import hasanberk.vaccinationappointmentsystem.ADT.Patient;
import hasanberk.vaccinationappointmentsystem.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void loadFile(ActionEvent actionEvent) {
        try {
            App.store.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile(ActionEvent actionEvent) {
        try {
            App.store.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset(ActionEvent actionEvent) {
        for(Centre c : App.store.getCentres()){
            App.store.deleteCentre(c);
        }
        for(Patient p : App.store.getPatients()){
            App.store.deletePatient(p);
        }
    }
}