import java.util.Arrays;

public enum Specialty {
    DERMATOLOGY("D"),
    PEDIATRICS("P"),
    RADIOLOGY("R");

    Specialty(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    private String abbreviation;

    public String getAbbreviation() {
        return abbreviation;
    }

    public static void printSpecialties() {
        System.out.println("\n ---- Specialities Available ----");
        Arrays.stream(Specialty.values()).forEach(specialty -> System.out
                .println(String.format("%-4sFor %s enter %s", "", specialty, specialty.getAbbreviation())));
        System.out.println(" --------------------------------\n");
    }
}
