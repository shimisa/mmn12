/**
 * The Main program will introduce the functionality of the application:
 * 1.introduce the polymorphism property
 * 2.show the duplication functionality
 *
 * @author  Shimi Sadaka
 * @version 1.0
 * @since   2020-12-01
 */

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        /* initialized a new ArrayList of Animals */
        ArrayList<Animal> animals = new ArrayList<Animal>();

        /* create new instances of all kinds of animals that we have */
        animals.add(new Tiger("Tony", "orange", "3"));
        animals.add(new Eagle("Dodo", "black", "2"));
        animals.add(new Parrot("Polo", "yellow", "1", "David", "054525543"));
        animals.add(new HouseCat("Mitzi", "white", "5", "Yuli", "054558543"));

        /* Activates the methods of each kind of animal (in the list) */
        for (int i = 0; i < animals.size(); i++) {
            System.out.println(animals.get(i).toString());

            if(animals.get(i) instanceof Poultry) { // if it's a bird then fly and incubate
                ((Poultry) animals.get(i)).toFly();
                ((Poultry) animals.get(i)).toIncubate();

                if (animals.get(i) instanceof Parrot) // if it's a parrot bird then speak
                    ((Parrot) animals.get(i)).toSpeak();
            }

            if(animals.get(i) instanceof Cats) { // if it's a cat then jump and run
                ((Cats) animals.get(i)).toRun();
                ((Cats) animals.get(i)).toJump();

                if(animals.get(i) instanceof HouseCat) // if it's an house cat then scratch
                    ((HouseCat) animals.get(i)).toScratch();
            }

            if(animals.get(i) instanceof Predator) // if it's a predator then prey
                ((Predator) animals.get(i)).toPrey();

            System.out.println("\n");
        }

        System.out.println("Creates new Parrot and clone it with another details: \n");

        /* initialized  a new instance of a Parrot and clone it with another details */
        Animal p = new Parrot("Tuki", "red", "4", "Yaron", "052525543");
        Animal q = p.clone("Shuki", "green", "6");

        /* prints the two Parrots details before set Owner details*/
        System.out.println(p.toString()); // the details of the first parrot
        System.out.println("\n");
        System.out.println(q.toString()); // the details of the cloned parrot
        System.out.println("\n");

        /* change Owner details */
        System.out.println("Update the Owner details of the cloned Parrot: \n");
        ((Parrot)q).setOwnerName("Ilan");
        ((Parrot)q).setOwnerPhoneNum("058945543");

        /* prints the two Parrots details after set Owner details*/
        System.out.println(p.toString()); // the details of the first parrot
        System.out.println("\n");
        System.out.println(q.toString()); // the details of the cloned parrot

    }
}