package hasanberk.vaccinationappointmentsystem.ADT;


import hasanberk.vaccinationappointmentsystem.DataStructure.MyLinkedList;
import hasanberk.vaccinationappointmentsystem.Utilities.Utilities;


public class Centre {
    private String name, address, eirCode;
    private int noOfParkingSpots;
    private MyLinkedList<Booth> booths;


    public Centre(String name, String address, String eirCode, int noOfParkingSpots) throws Exception {
        booths = new MyLinkedList<>();
        setName(name);
        setAddress(address);
        setEirCode(eirCode);
        setNoOfParkingSpots(noOfParkingSpots);
    }

    public MyLinkedList<Booth> getBooths() {
        return booths;
    }

    public boolean addBooth(Booth b){
        for(Booth x : booths){
            if(x.getBoothIdentifier().toUpperCase().equals(b.getBoothIdentifier().toUpperCase())){
                return false;
            }
        }
        booths.add(b);
        return true;
    }

    public boolean deleteBooth(Booth b){
        for(Booth x : booths){
            if(x.getBoothIdentifier().equals(b.getBoothIdentifier())){
                for(Booth y : booths){
                    if(x.getAppointments().size() > y.getAppointments().size()){
                        x.getAppointments().addAll(y.getAppointments());
                    }
                }
                booths.remove(b);
                return true;
            }
        }
        return false;
    }

    //Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEirCode() {
        return eirCode;
    }

    public int getNoOfParkingSpots() {
        return noOfParkingSpots;
    }

    //Setters
    public void setName(String name) {
        if (Utilities.max30Chars(name)) {
            this.name = name;
        } else{
            throw new IllegalArgumentException("Please enter a name of 30 characters or less.");
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEirCode(String eirCode) throws Exception {
        if (Utilities.validEirCode(eirCode)) {
            this.eirCode = eirCode;
        } else {
            throw new IllegalArgumentException("Invalid EirCode");
        }
    }

    public void setNoOfParkingSpots(int noOfParkingSpots) {
        this.noOfParkingSpots = noOfParkingSpots;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                ", Address: " + address +
                ", EirCode: " + eirCode +
                ", Number of Parking Spots: " + noOfParkingSpots;
    }

}

