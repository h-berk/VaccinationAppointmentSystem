package hasanberk.vaccinationappointmentsystem.ADT;

import hasanberk.vaccinationappointmentsystem.Utilities.Utilities;

import java.time.LocalDate;

public class Appointment {
    private LocalDate date;
    private String time, type, vaccineIdentifier, vaccinatorDetails, pps;

    public Appointment(LocalDate date, String time, String type, String vaccineIdentifier, String vaccinatorDetails, String pps) {
        setDate(date);
        setTime(time);
        setType(type);
        setVaccineIdentifier(vaccineIdentifier);
        setVaccinatorDetails(vaccinatorDetails);
        setPps(pps);
    }

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
        if(!Utilities.validPastDate(date)){
            this.date = date;
        } else{
            throw new IllegalArgumentException("Appointment date must be in the future");
        }
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
        if(Utilities.validPPS(pps)){
            this.pps = pps;
        } else{
            throw new IllegalArgumentException("Invalid PPS Number.");
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
