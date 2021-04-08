/**
 * The class Owner take care of owner details for some animal that have owner.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */

public class Owner {

    private String name;
    private String phoneNumber;

    /* Constructor */
    public Owner(String name, String phoneNumber){

        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /* Geters and Seters */
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "The Owner name is: " + name +"\n"+
                "Owner phone number: " + phoneNumber;
    }
}
