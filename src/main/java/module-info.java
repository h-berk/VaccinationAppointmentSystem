module hasanberk.vaccinationappointmentsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens hasanberk.vaccinationappointmentsystem to javafx.fxml, xstream;
    opens hasanberk.vaccinationappointmentsystem.DataStructure to javafx.fxml, xstream;
    opens hasanberk.vaccinationappointmentsystem.ADT to javafx.fxml, xstream;
    opens hasanberk.vaccinationappointmentsystem.Controllers to javafx.fxml, xstream;

    exports hasanberk.vaccinationappointmentsystem;
    exports hasanberk.vaccinationappointmentsystem.DataStructure;
    exports hasanberk.vaccinationappointmentsystem.Controllers;
    exports hasanberk.vaccinationappointmentsystem.ADT;
}