import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public static void main(String[] args) {
        // Specialty.getAbbreviation();
        System.out.println(createDoctors(5));
    }

    private static List<Doctor> createDoctors(int doctorAmount) {

        List<Doctor> doctors = new ArrayList<>();
        String[] fakeNames = { "Latrell", "Adan", "Rhys", "Arabella", "Anakin", "Izabelle", "Ronin", "Olivia", "Tripp",
                "Evan", "Jailyn", "Kalen", "Kensley", "Fabiola", "Bryson", "Savanna", "Tatiana", "Kya", "Sailor",
                "Niko", "Kaden", "Kenlee", "Willis", "Shepherd", "Haddie" };

        if (doctorAmount > fakeNames.length)
            doctorAmount = fakeNames.length;

        if (doctorAmount <= 0)
            doctorAmount = 5;

        Set<Integer> visitedIndex = new HashSet<>();

        Random random = new Random();

        while (visitedIndex.size() < doctorAmount) {
            int index = random.nextInt(fakeNames.length);
            if (visitedIndex.contains(index))
                continue;
            visitedIndex.add(index);
            doctors.add(new Doctor(Specialty.getRandomSpecialty(random), fakeNames[index]));
        }

        return doctors;
    }
}
