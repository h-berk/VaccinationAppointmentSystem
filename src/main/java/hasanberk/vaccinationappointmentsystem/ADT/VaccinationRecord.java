package hasanberk.vaccinationappointmentsystem.ADT;

import hasanberk.vaccinationappointmentsystem.App;
import hasanberk.vaccinationappointmentsystem.Utilities.Utilities;

import java.time.LocalDate;

public class VaccinationRecord {
    private LocalDate date;
    private String time, type, vaccineIdentifier, vaccinatorDetails, pps;

    // Getters

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getVaccineIdentifier() {
        return vaccineIdentifier;
    }

    public String getVaccinatorDetails() {
        return vaccinatorDetails;
    }

    public String getPps() {
        return pps;
    }


    // Setters

    public void setDate(LocalDate date) {
        this.date=date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVaccineIdentifier(String vaccineIdentifier) {
        this.vaccineIdentifier = vaccineIdentifier;
    }

    public void setVaccinatorDetails(String vaccinatorDetails) {
        this.vaccinatorDetails = vaccinatorDetails;
    }

    public void setPps(String pps) {
        for(Patient p : App.store.getPatients()){
            if(pps.equalsIgnoreCase(p.getPps())){
                this.pps = pps.toUpperCase();
            } else{
                throw new IllegalArgumentException("PPS not associated with a registered patient.");
            }
        }
    }

    @Override
    public String toString() {
        return "Date: " + date +
                ", Time: " + time +
                ", Type: " + type + '\'' +
                ", Vaccine Identifier: " + vaccineIdentifier + '\'' +
                ", Vaccinator Details: " + vaccinatorDetails + '\'' +
                ", PPS: " + pps;
    }

}
