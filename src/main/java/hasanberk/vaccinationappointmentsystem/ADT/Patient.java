package hasanberk.vaccinationappointmentsystem.ADT;

import hasanberk.vaccinationappointmentsystem.DataStructure.MyLinkedList;
import hasanberk.vaccinationappointmentsystem.Utilities.Utilities;

import java.time.LocalDate;

public class Patient {
    private String pps, name, eirCode, telephone, email;
    private LocalDate dob;
    private boolean accessibility;
    private MyLinkedList<VaccinationRecord> vaccinationRecords;

    public Patient(String pps, String name, String eirCode, String telephone, String email, LocalDate dob, boolean accessibility) throws Exception {
        setPps(pps);
        setName(name);
        setEirCode(eirCode);
        setTelephone(telephone);
        setEmail(email);
        setDob(dob);
        setAccessibility(accessibility);
        vaccinationRecords = new MyLinkedList<>();
    }

    public void addVaccinationRecord(VaccinationRecord newRecord){
        vaccinationRecords.add(newRecord);
    }

    // Getters

    public String getPps() {
        return pps;
    }

    public String getName() {
        return name;
    }

    public String getEirCode() {
        return eirCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public boolean isAccessibility() {
        return accessibility;
    }

    public MyLinkedList<VaccinationRecord> getVaccinationRecord() {
        return vaccinationRecords;
    }

    // Setters

    public void setPps(String pps) {
        if(Utilities.validPPS(pps)){
            this.pps = pps;
        } else{
            throw new IllegalArgumentException("Invalid PPS Number.");
        }
    }

    public void setName(String name) {
        if(Utilities.max30Chars(name)){
            this.name = name;
        } else{
            throw new IllegalArgumentException("Invalid name length.");
        }
    }

    public void setEirCode(String eirCode) {
        if(Utilities.validEirCode(eirCode)){
            this.eirCode = eirCode;
        } else {
            throw new IllegalArgumentException("Invalid EirCode.");
        }
    }

    public void setTelephone(String telephone) {
        if(Utilities.validTelephone(telephone)){
            this.telephone = telephone;
        } else {
            throw new IllegalArgumentException("Invalid telephone number.");
        }
    }

    public void setEmail(String email) {
        if(Utilities.validEmail(email)){
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid email address.");
        }
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public String toString() {
        String wheelchair = accessibility ? "Yes" : "No"; //returns yes or no for boolean value
        return "PPS: " + pps +
                ", Name: " + name +
                ", EirCode: " + eirCode +
                ", Telephone: " + telephone +
                ", Email: " + email +
                ", DOB: " + dob +
                ", Wheelchair Accessibility: " + wheelchair;
    }
}
