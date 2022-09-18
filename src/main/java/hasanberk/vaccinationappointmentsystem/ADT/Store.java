package hasanberk.vaccinationappointmentsystem.ADT;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import hasanberk.vaccinationappointmentsystem.DataStructure.MyLinkedList;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Store {

    private MyLinkedList<Centre> centres = new MyLinkedList<>();
    private MyLinkedList<Patient> patients = new MyLinkedList<>();


    public boolean addCentre(Centre c){
        for(Centre x : centres){
            if(x.getName().equals(c.getName())){
                return false;
            }
        }
        centres.add(c);
        return true;
    }

    public boolean deleteCentre(Centre c){
        for(Centre x : centres){
            if(x.getName().equals(c.getName())){
                centres.remove(x);
                return true;
            }
        }
        return false;
    }

    public boolean addPatient(Patient p){
        for(Patient x : patients){
            if(x.getPps().equals(p.getPps())){
                return false;
            }
        }
        patients.add(p);
        return true;
    }

    public boolean deletePatient(Patient p){
        for(Patient x : patients){
            if(x.getPps().equals(p.getPps())){
                patients.remove(x);
                return true;
            }
        }
        return false;
    }

    public MyLinkedList<Centre> getCentres() {
        return this.centres;
    }

    public MyLinkedList<Patient> getPatients() {
        return this.patients;
    }

    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("vaccinationSystem.xml"));
        centres = (MyLinkedList<Centre>) is.readObject();
        patients = (MyLinkedList<Patient>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("vaccinationSystem.xml"));
        out.writeObject(centres);
        out.writeObject(patients);
        out.close();
    }
}
