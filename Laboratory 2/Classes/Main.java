package Classes;

public class Main {
    public static void main(String[] args) {
        Person pers1 = new Person("John", "Doe", "24", 1123444, "student");
        Person pers2 = new Person("Jane", "Roe", "56", 2233444, "teacher");

        System.out.println(pers1.getPersonData());
        System.out.println(pers2.getPersonData());

        pers2.changeType("doctor");
        System.out.println(pers2.getPersonData());
        System.out.println();

        Room room1 = new Room("12A", "normal", (byte) 3);
        Room room2 = new Room("12B", "tech", (byte) 7);

        System.out.println(room1.getRoomData());
        System.out.println(room2.getRoomData());
        System.out.println();

        Subject subj1 = new Subject(room1, 12, pers1);
        Subject subj2 = new Subject(room2, 23, pers2);

        subj1.getSubjectData();
        subj2.getSubjectData();
    }
}
