package animals;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Animal {
    private static Integer noAnimals = 0;
    private final LocalDate dateOfBirth;
    private final String id;
    private final boolean isMale;

    public Animal(String type, Integer id, boolean isMale) {
        noAnimals++;
        this.dateOfBirth = LocalDate.now();
        this.id = type + id;
        this.isMale = isMale;
    }

    public Animal(String type, Integer id, String dateOfBirth, boolean isMale) {
        noAnimals++;
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
        this.id = type + id;
        this.isMale = isMale;
    }

    public String getId() {
        return id;
    }

    public boolean isMale(){
        return isMale;
    }

    public void displayInfo(){}

    protected String getAnimalAge() {
        LocalDate currentDate = LocalDate.now();
        long months = ChronoUnit.MONTHS.between(dateOfBirth, currentDate);

        if (months == 0) {
            return ChronoUnit.DAYS.between(dateOfBirth, currentDate) + " days";
        } else {
            return months / 12 + " years and " + (months - (months / 12 * 12)) + " months";
        }
    }
}
