public class Doctor {

    private Specialty specialty;
    private String name;

    public Doctor(Specialty specialty, String name) {
        this.specialty = specialty;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("{%s, Specialty: %s}", name, specialty);
    }
}
