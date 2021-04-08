/**
 * The class Eagle contain unique methods and properties that special to eagles and overrides general ones.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */


public class Eagle extends Poultry implements Predator{

    public Eagle(String name, String color, String age) {
        super(name, color, age);
    }

    /* unique methods */
    @Override
    public void toFly(){
        System.out.println(this.getName()+" the Eagle is flying");
    }

    @Override
    public void toIncubate() {
        System.out.println(this.getName()+" the Eagle is incubating");
    }


    @Override
    public void toEat() {
        System.out.println(this.getName()+" the Eagle is eating");
    }

    @Override
    public void toSleep() {
        System.out.println(this.getName()+" the Eagle is sleeping");
    }

    @Override
    public void toPrey() {
        System.out.println(this.getName()+" the Eagle is preying");
    }

    @Override
    public boolean equals(Animal animal) {
        return  super.equals(animal) && animal instanceof Eagle;
    }

    /* override toString */
    @Override
    public String toString() {
        return "The animal is an Eagle\n" + super.toString();
    }

    /* The clone method take the properties for Animal */
    @Override
    public Animal clone(String name, String color, String age) {
        return new Eagle(name, color, age);
    }
}
