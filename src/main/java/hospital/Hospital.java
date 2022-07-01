package hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import doctor.Doctor;
import patient.Patient;
import util.Specialty;

public class Hospital {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();

    public Hospital(String name) {
        this.name = name;
    }

    private String name;

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public int getDoctorsSize() {
        return doctors.size();
    }

    public int getPatientsSize() {
        return patients.size();
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * Gets a random doctor that was assigned to the specialty
     * 
     * @param specialty
     * @return
     */
    public Optional<Doctor> getDoctorForSpecialty(Specialty specialty) {
        Doctor[] specialtyDoctors = doctors.stream()
                .filter(doctor -> doctor.getSpecialty() == specialty)
                .toArray(Doctor[]::new);
        if (specialtyDoctors.length == 0)
            return Optional.empty();
        Random random = new Random();
        return Optional.of(specialtyDoctors[random.nextInt(specialtyDoctors.length)]);
    }
}
