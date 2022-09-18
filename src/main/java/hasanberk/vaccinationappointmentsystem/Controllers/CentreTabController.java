package hasanberk.vaccinationappointmentsystem.Controllers;

import hasanberk.vaccinationappointmentsystem.ADT.Centre;
import hasanberk.vaccinationappointmentsystem.App;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class CentreTabController {

    public ListView<String> listView;
    public TextField nameTextField;
    public TextField addressTextField;
    public TextField eirCodeTextField;
    public Spinner<Integer> parkingSpotsSpinner;

    public void initialize() {
        parkingSpotsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 500));
        parkingSpotsSpinner.setEditable(true);
    }

    public void addCentreClicked(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String eirCode = eirCodeTextField.getText();
        int parking = parkingSpotsSpinner.getValue();
        try {
            Centre c = new Centre(name, address, eirCode, parking);
            if(!App.store.addCentre(c)){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Centre already exists", ButtonType.OK);
                alert.showAndWait(); //waits for confirmation before proceeding
            }
            BoothTabController.instance.update();
            AppointmentTabController.instance.update();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
            e.printStackTrace();
        }
        updateListView();
    }

    public void deleteCentreClicked(ActionEvent actionEvent) {
        try {
            String selected = listView.getSelectionModel().getSelectedItem();
            for(Centre c : App.store.getCentres()){
                if(c.toString().equals(selected)){
                    App.store.deleteCentre(c);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Centre deleted successfully.", ButtonType.OK);
                    alert.showAndWait();
                }
            }
            BoothTabController.instance.update();
            AppointmentTabController.instance.update();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait(); //waits for confirmation before proceeding
        }
        updateListView();
    }

    public void updateListView() {
        listView.getItems().clear();
        for (Centre c : App.store.getCentres()) {
            listView.getItems().add(c.toString());
        }
    }
}
