import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

// The system should create a Hospital with a name property provided by the user.
// The system should create 3 doctors, each with a name and specialty provided by the user.
// The specialty is user-definable, but good examples are:
// Dermatology
// Pediatrics
// Radiology
// ...
// The system should establish that the doctors work at the system's hospital.
// The system should ask the user to create 5 patients and match each patient to a doctor based on the specialty they need.
// When the system is done collecting input from the user and building the world, it should "display" the world to the user (text-based display on the command line).
// The system must handle invalid input from the user (at all points where input is gathered from the user).

public class HospitalWorld {
    private static final int PATIENT_SIZE = 1;
    private static final int DOCTOR_SIZE = 6;
    private static Map<Specialty, List<Patient>> specialtyToPatients = new HashMap<>();

    static {
        Arrays.stream(Specialty.values()).forEach(specialty -> specialtyToPatients.put(specialty, new ArrayList<>()));
    }

    public static void main(String[] args) {
        // Specialty.getAbbreviation();
        System.out.println(createDoctors(50));

        List<Patient> patients = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n---- Hospital System ----");

                System.out.println("\n---- Docters On Call ----");
                List<Doctor> doctors = createDoctors(DOCTOR_SIZE);
                Doctor.printDoctors(doctors);

                while (patients.size() < PATIENT_SIZE) {
                    System.out.println(String.format("%n ---- Adding Patients ---- (Patients Left: %s)",
                            PATIENT_SIZE - patients.size()));
                    String patientName = getUserPatient(scanner);
                    Patient patient = new Patient(patientName);
                    Specialty specialty = getUserSpecialty(scanner);
                    patients.add(patient);
                    specialtyToPatients.get(specialty).add(patient);

                }

                System.out.println("\n---- Hospital World ----");
                for (Specialty specialty : Specialty.values()) {
                    List<Doctor> specialtyDoctors = doctors.stream()
                            .filter(doctor -> specialty.equals(doctor.getSpecialty()))
                            .collect(Collectors.toList());
                    System.out.println(String.format("%s, Doctors: %s, Patients: %s", specialty, specialtyDoctors,
                            specialtyToPatients.get(specialty)));
                }

                return;
            }
        } catch (Exception e) {

        }
    }

    private static Specialty getUserSpecialty(Scanner scanner) {

        try {
            while (true) {
                System.out.print(
                        "\nPlease enter a Specialty abbreviation or I for specialty abbreviation information (Default chosen at random): ");
                String specialtyAbbreviation = scanner.nextLine();
                if (specialtyAbbreviation.equalsIgnoreCase("I")) {
                    Specialty.printSpecialties();
                    continue;
                }
                return Specialty.getSpecialtyFromAbbreviation(specialtyAbbreviation);
            }

        } catch (Exception e) {
            Specialty specialty = Specialty.getRandomSpecialty();
            System.out.println(String.format(
                    "ERROR: This specialty abbreviation was invalid. The specialty \"%s\" will be used.", specialty));
            return specialty;
        }
    }

    private static String getUserPatient(Scanner scanner) {
        System.out.print("\nPlease enter patient name (Default is random name): ");
        String patientName = "";
        try {
            patientName = scanner.nextLine();
            if (patientName.isBlank())
                throw new Exception();

        } catch (Exception e) {
            patientName = getRandomName();
            System.out.println(String.format("ERROR: This name is invalid. The name \"%s\" will be used", patientName));

        }

        return patientName;
    }

    private static List<Doctor> createDoctors(int doctorAmount) {

        List<Doctor> doctors = new ArrayList<>();
        String[] fakeNames = { "Latrell", "Adan", "Rhys", "Arabella", "Anakin", "Izabelle", "Ronin", "Olivia", "Tripp",
                "Evan", "Jailyn", "Kalen", "Kensley", "Fabiola", "Bryson", "Savanna", "Tatiana", "Kya", "Sailor",
                "Niko", "Kaden", "Kenlee", "Willis", "Shepherd", "Haddie" };

        if (doctorAmount > fakeNames.length) // Upper bound edge case
            doctorAmount = fakeNames.length;

        if (doctorAmount <= 0) // Lower bound edge case
            doctorAmount = 5;

        Set<Integer> visitedIndex = new HashSet<>();

        Random random = new Random();

        while (visitedIndex.size() < doctorAmount) {
            int index = random.nextInt(fakeNames.length);
            if (visitedIndex.contains(index))
                continue;
            visitedIndex.add(index);
            doctors.add(new Doctor(Specialty.getRandomSpecialty(), fakeNames[index]));
        }

        return doctors;
    }

    /**
     * Returns a random name
     * 
     * @return
     */
    private static String getRandomName() {
        String[] fakeNames = { "Alfredo", "Lexi", "Kelsie", "Dakota", "Myron", "Ximena", "Luka", "Koda", "Clara",
                "Harper", "Oskar", "Gianluca", "Kylie", "Carsyn", "Aubrey", "Gillian", "Denisse", "Lyla", "Romina",
                "Audrey", "Gilberto", "Sailor", "Jad", "Aliyana", "Harlee" };
        Random random = new Random();
        return fakeNames[random.nextInt(fakeNames.length)];
    }
}
