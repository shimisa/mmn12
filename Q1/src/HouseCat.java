/**
 * The class HouseCat contain unique methods and properties that special to house Cats and overrides general ones.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */

public class HouseCat extends Cats {

    private Owner houseCatOwner; // houseCat has an Owner

    public HouseCat(String name, String color, String age, String OwnerName, String OwnerPhoneNumber) {
        super(name, color, age);
        this.houseCatOwner = new Owner(OwnerName, OwnerPhoneNumber); // creates the Owner
    }

    /* Geters and Seters for the Owner */
    public String getOwnerName() {
        return houseCatOwner.getName();
    }

    public String getPhoneNum() {
        return houseCatOwner.getPhoneNumber();
    }

    public void setOwnerName(String name){
        houseCatOwner.setName(name);
    }

    public void setOwnerPhoneNum(String phoneNum){
        houseCatOwner.setPhoneNumber(phoneNum);
    }

    /* unique methods */
    @Override
    public void toRun() {
        System.out.println(this.getName()+" the HouseCat is running");
    }

    @Override
    public void toJump() {
        System.out.println(this.getName()+" the HouseCat is jumping");
    }

    @Override
    public void toEat() {
        System.out.println(this.getName()+" the HouseCat is eating");
    }

    @Override
    public void toSleep() {
        System.out.println(this.getName()+" the HouseCat is sleeping");
    }

    public void toScratch() {
        System.out.println(this.getName()+" the HouseCat is scratching");
    }

    @Override
    public boolean equals(Animal animal) {
        return  super.equals(animal) && animal instanceof HouseCat;
    }

    /* override toString */
    @Override
    public String toString() {
        return "The animal is an House Cat\n" + super.toString() + "\n" + houseCatOwner.toString();
    }

    /* The clone method take the properties for Animal and live the Owner prop undefined */
    @Override
    public Animal clone(String name, String color, String age) {
        return new HouseCat(name, color, age, null, null);
    }
}