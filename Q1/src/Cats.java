/**
 * The Cats class take care of all kinds of cats and contain properties and methods shared by all cats.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */


public abstract class Cats extends Animal{

    public Cats(String name, String color, String age) {
        super(name, color, age);
    }

    /* abstract unique methods for all kinds of cats */
    public abstract void toRun();
    public abstract void toJump();



}