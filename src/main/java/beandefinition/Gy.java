package beandefinition;

public class Gy {
    String name;
    String highSchool;
    String homeTown;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHighSchool() {
        return highSchool;
    }

    public void setHighSchool(String highSchool) {
        this.highSchool = highSchool;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    @Override
    public String toString() {
        return "Gy{" +
                "name='" + name + '\'' +
                ", highSchool='" + highSchool + '\'' +
                ", homeTown='" + homeTown + '\'' +
                '}';
    }
}
