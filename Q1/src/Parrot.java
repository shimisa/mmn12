/**
 * The class Parrot contain unique methods and properties that special to parrots and overrides general ones.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */


public class Parrot extends Poultry {

    private Owner parrotOwner; // parrot has an Owner

    public Parrot(String name, String color, String age, String OwnerName, String OwnerPhoneNumber) {
        super(name, color, age);
        this.parrotOwner = new Owner(OwnerName, OwnerPhoneNumber); // creates the Owner
    }

    /* Geters and Seters for the Owner */
    public String getOwnerName() {
        return parrotOwner.getName();
    }

    public String getPhoneNum() {
        return parrotOwner.getPhoneNumber();
    }

    public void setOwnerName(String name){
        parrotOwner.setName(name);
    }

    public void setOwnerPhoneNum(String phoneNum){
        parrotOwner.setPhoneNumber(phoneNum);
    }

    /* unique methods */
    @Override
    public void toFly() {
        System.out.println(this.getName()+" the Parrot is flying");
    }

    @Override
    public void toIncubate() {
        System.out.println(this.getName()+" the Parrot is incubating");
    }

    @Override
    public void toEat() {
        System.out.println(this.getName()+" the Parrot is eating");
    }

    @Override
    public void toSleep() {
        System.out.println(this.getName()+" the Parrot is sleeping");
    }

    public void toSpeak() {
        System.out.println(this.getName()+" the Parrot is speaking");
    }

    @Override
    public boolean equals(Animal animal) {
        return  super.equals(animal) && animal instanceof Parrot;
    }

    /* override toString */
    @Override
    public String toString() {
        return "The animal is a Parrot\n" + super.toString() + "\n" + parrotOwner.toString();
    }

    /* The clone method takes the properties for Animal and live the Owner prop undefined */
    @Override
    public Animal clone(String name, String color, String age) {
        return new Parrot(name, color, age, null, null);
    }
}
