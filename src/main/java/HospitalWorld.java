import java.util.Scanner;

import doctor.Doctor;
import hospital.Hospital;
import patient.Patient;
import util.Specialty;
import util.UserInput;

// x The system should create a Hospital with a name property provided by the user.
// x The system should create 3 doctors, each with a name and specialty provided by the user.
// The specialty is user-definable, but good examples are:
//      Dermatology
//      Pediatrics
//      Radiology
//      ...
// x The system should establish that the doctors work at the system's hospital.
// x The system should ask the user to create 5 patients and match each patient to a doctor based on the specialty they need.
// x When the system is done collecting input from the user and building the world, it should "display" the world to the user (text-based display on the command line).
// x The system must handle invalid input from the user (at all points where input is gathered from the user).

public class HospitalWorld {
    private static final int PATIENT_SIZE = 5;
    private static final int DOCTOR_SIZE = 3;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {

                // Create Hospital
                System.out.println("\n---- Hospital Creation System ----");

                Hospital hospital = UserInput.getUserHospital(scanner);

                while (hospital.getDoctorsSize() < DOCTOR_SIZE) {
                    System.out.println(String.format("%n ---- Adding Doctor(s) ---- (Doctors Left: %s)",
                            DOCTOR_SIZE - hospital.getDoctorsSize()));
                    String doctorName = UserInput.getName(scanner, "doctor");
                    Specialty specialty = UserInput.getUserSpecialty(scanner);
                    hospital.addDoctor(new Doctor(specialty, doctorName));
                }

                while (hospital.getPatientsSize() < PATIENT_SIZE) {
                    System.out.println(String.format("%n ---- Adding Patient(s) ---- (Patients Left: %s)",
                            PATIENT_SIZE - hospital.getPatientsSize()));
                    String patientName = UserInput.getName(scanner, "patient");
                    Specialty specialty = UserInput.getUserSpecialty(scanner);
                    hospital.addPatient(
                            new Patient(patientName, hospital.getDoctorForSpecialty(specialty).orElse(null)));
                }

                System.out.println(String.format("%n---- Hospital %s World ----",
                        hospital.getName()));
                hospital.getPatients().stream()
                        .forEach(patient -> System.out.println(
                                String.format("Patient: %s, Doctor: %s", patient.getName(), patient.getDoctor())));

                System.out.print("\nWould you like to create another Hospital World? (Y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("n"))
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
