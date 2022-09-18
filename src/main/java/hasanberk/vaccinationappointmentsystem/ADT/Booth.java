package hasanberk.vaccinationappointmentsystem.ADT;

import hasanberk.vaccinationappointmentsystem.App;
import hasanberk.vaccinationappointmentsystem.DataStructure.MyLinkedList;
import hasanberk.vaccinationappointmentsystem.Utilities.Utilities;


public class Booth {

    private String boothIdentifier;
    private int floorLevel;
    private boolean accessibility;
    private MyLinkedList<Appointment> appointments;
    private MyLinkedList<Appointment> completedAppointments;

    public Booth(String boothIdentifier, int floorLevel, boolean accessibility) throws Exception {
        appointments = new MyLinkedList<>();
        completedAppointments = new MyLinkedList<>();
        setBoothIdentifier(boothIdentifier);
        setFloorLevel(floorLevel);
        setAccessibility(accessibility);
    }

    public MyLinkedList<Appointment> getAppointments() {
        return appointments;
    }
    public MyLinkedList<Appointment> getCompletedAppointments() {
        return completedAppointments;
    }

    public boolean addAppointment(Appointment a){
        for(Appointment x : appointments){ //No other appointments
            if(x.getPps().equals(a.getPps()) && (x.getPps()!= null)){
                throw new IllegalArgumentException("Appointment already exists");
            }
        }
        for(Patient p : App.store.getPatients()){ //Only registered patients
            if(!a.getPps().equals(p.getPps())){
                throw new IllegalArgumentException("PPS not registered.");
            }
        }
        return appointments.add(a);
    }

    public boolean deleteAppointment(Appointment a){
        for(Appointment x : appointments){
            if(x.getPps().equals(a.getPps())){
                return appointments.remove(a);
            }
        }
        return false;
    }

    public boolean completeAppointment(Appointment a){
        completedAppointments.add(a);
        appointments.remove(a);
        return true;
    }

    public boolean deleteCompletedAppointment(Appointment a){
        return completedAppointments.remove(a);
    }

    //Getters
    public String getBoothIdentifier() {
        return boothIdentifier;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public boolean getAccessibility() {
        return accessibility;
    }


    //Setters
    public void setBoothIdentifier(String boothIdentifier) {
        if (Utilities.validBoothIdentifier(boothIdentifier)) {
            this.boothIdentifier = boothIdentifier.toUpperCase(); //prevents a1 and A1 being added
        } else{
            throw new IllegalArgumentException("Please enter one letter followed by a number e.g. A2");
        }
    }

    public void setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
    }

    public void setAccessibility(boolean accessibility) {
        this.accessibility = accessibility;
    }

    @Override
    public String toString(){
        String wheelchair = accessibility ? "Yes" : "No"; //returns yes or no for boolean value
        return "Booth Identifier: " + boothIdentifier +
                ", Floor Level: " + floorLevel +
                ", Wheelchair Accessibility: " + wheelchair;
    }
}
