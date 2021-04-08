/**
 * The Animal class take care of all kinds of animals and contain properties and methods shared by all animals.
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */

public abstract class Animal {

    /* properties */
    private String name;
    private String color;
    private String  age;

    /* constructor */
    public Animal(String name, String color, String age){
        this.name = name;
        this.color = color;
        this.age = age;
    }

    /* abstract methods for all kinds of animals */
    public abstract void toEat();
    public abstract void toSleep();
    public abstract Animal clone(String name, String color, String  age);



    /* redefined Equals method */
    public boolean equals(Animal animal){
        return getName().equals(animal.getName()) && getColor().equals(animal.getColor()) && getAge().equals(animal.getAge());
    }

    /* Geters */
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getAge() {
        return age;
    }

    /* Seters */
    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void getAge(String age) {
        this.age = age;
    }

    /* redefined toString method for all kinds of animals */
    @Override
    public String toString() {
        return "Name: "+name+"\n" +"Color: "+color+"\n" +"Age: "+age;
    }
}
