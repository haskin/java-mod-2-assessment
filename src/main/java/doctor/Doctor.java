package doctor;

import java.util.ArrayList;
import java.util.List;

import patient.Patient;
import util.Specialty;

public class Doctor {

    private Specialty specialty;
    private String name;
    List<Patient> patients = new ArrayList<>();

    public Doctor(Specialty specialty, String name) {
        this.specialty = specialty;
        this.name = name;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public String getName() {
        return name;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public static void printDoctors(List<Doctor> doctors) {
        doctors.stream().forEach(System.out::println);
    }

    @Override
    public String toString() {
        return String.format("{ %s, Specialty: %s }", name, specialty);
    }
}
