public class Passenger {
    private int passengerId;
    private int survived;
    private int pClass;
    private String name;
    private String sex;
    private float age;
    private int sibSp;
    private int parch;
    private String ticket;
    private float fare;
    private String cabin;
    private String embarked;

    public Passenger(int passengerId, int survived, int pClass, String name, String sex, float age, int sibSp, int parch, String ticket, float fare, String cabin, String embarked) {
        this.passengerId = passengerId;
        this.survived = survived;
        this.pClass = pClass;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.sibSp = sibSp;
        this.parch = parch;
        this.ticket = ticket;
        this.fare = fare;
        this.cabin = cabin;
        this.embarked = embarked;
    }

    public float getAge() {
        return age;
    }

    public int getPassengerId() {
        return passengerId;
    }

    public int getSurvived() {
        return survived;
    }

    public int getpClass() {
        return pClass;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public float getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    public String getFormattedName(){
        String[] names = name.split(",");
        String lastName = names[0];
        String firstName = names[1].split("\\.")[1];
        return firstName + " " + lastName;
    }


}
