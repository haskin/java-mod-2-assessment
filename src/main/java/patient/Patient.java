package patient;

import doctor.Doctor;

public class Patient {
    private String name;
    private Doctor doctor;

    public Patient(String name, Doctor doctor) {
        this.name = name;
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
