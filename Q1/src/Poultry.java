/**
 * The Poultry class take care of all kinds of cats and contain properties and methods shared by all poultry.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */


public abstract class Poultry extends Animal {


    public Poultry(String name, String color, String age) {
        super(name, color, age);
    }

    /* abstract unique methods for all kinds of Poultry */
    public abstract void toFly();
    public abstract void toIncubate();



}
