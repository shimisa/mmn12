/**
 * The class Tiger contain unique methods and properties that special to tigers and overrides general ones.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */


public class Tiger extends Cats implements Predator {

    public Tiger(String name, String color, String age) {
        super(name, color, age);
    }

    /* unique methods */
    @Override
    public void toRun() {
        System.out.println(this.getName()+" the Tiger is running");
    }

    @Override
    public void toJump() {
        System.out.println(this.getName()+" the Tiger is jumping");
    }

    @Override
    public void toEat() {
        System.out.println(this.getName()+" the Tiger is eating");
    }

    @Override
    public void toSleep() {
        System.out.println(this.getName()+" the Tiger is sleeping");
    }

    @Override
    public void toPrey() {
        System.out.println(this.getName()+" the Tiger is preying");
    }

    @Override
    public boolean equals(Animal animal) {
        return  super.equals(animal) && animal instanceof Tiger;
    }

    /* override toString */
    @Override
    public String toString() {
        return "The animal is a Tiger\n" + super.toString();
    }

    /* The clone method take the properties for Animal */
    @Override
    public Animal clone(String name, String color, String age) {
        return new Tiger(name, color, age);
    }
}
