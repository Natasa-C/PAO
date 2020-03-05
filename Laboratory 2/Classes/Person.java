package Classes;

public class Person {
    private String name;
    private String surname;
    private String age;
    private long identityNumber;
    private String type;

    public Person(String name, String surname, String age, long identityNumber, String type) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.identityNumber = identityNumber;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setIdentityNumber(long identityNumber) {
        this.identityNumber = identityNumber;
    }

    public void seType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getAge() {
        return this.age;
    }

    public long getIdentityNumber() {
        return this.identityNumber;
    }

    public String getType() {
        return this.type;
    }

    public String getPersonData() {
        return this.getName() + " " + this.getSurname() + " " + this.getAge() + " " + this.getIdentityNumber() + " " + this.getType();
    }

    public boolean changeType(String newType) {
        boolean changed = false;
        if (this.type.equals("teacher")) {
            this.type = newType;
            changed = true;
        }
        return changed;
    }
}
