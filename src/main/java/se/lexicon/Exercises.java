package se.lexicon;

import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.function.Predicate;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        Predicate<Person> findErik = (person) -> person.getFirstName().equals("Erik");
        storage.findMany(findErik).
                forEach(System.out::println);

        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        Predicate<Person> findFemales = (person) -> person.getGender() == Gender.FEMALE;
        storage.findMany(findFemales).forEach(System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        Predicate<Person> findBornAfter2000 = (person) -> person.getBirthDate().isAfter(LocalDate.parse("2000-01-01"));
        storage.findMany(findBornAfter2000)
                .forEach(System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        int id = 123;
        Predicate<Person> findId = (person) -> person.getId() == id;
        System.out.println(storage.findOne(findId));
        //Write your code here

        System.out.println("----------------------");

    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        int id = 456;
        Predicate<Person> findId = (person) -> person.getId() == id;
        System.out.println(storage.findOneAndMapToString(findId));
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        Predicate<Person> findMaleNames = (person) -> (person.getGender() == Gender.MALE) && person.getFirstName().startsWith("E");
        storage.findManyAndMapEachToString(findMaleNames, (person) -> person.getFirstName() + " " + person.getLastName()).
                forEach(System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message) {
        System.out.println(message);
        Predicate<Person> findBelowAge10 = (person) -> LocalDate.now().getYear() - person.getBirthDate().getYear() < 10;
        storage.findManyAndMapEachToString(findBelowAge10, (person) -> person.getFirstName() + " " + person.getLastName() + " " + (LocalDate.now().getYear() - person.getBirthDate().getYear()) + " years").
                forEach(System.out::println);

        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        Predicate<Person> findFirstName = (person) -> person.getFirstName().equals("Ulf");
        storage.findAndDo(findFirstName, System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        Predicate<Person> findLastName = (person) -> person.getLastName().contains(person.getFirstName());
        storage.findAndDo(findLastName, System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        Predicate<Person> findPalindrome = (person) -> person.getFirstName().equalsIgnoreCase(new StringBuilder(person.getFirstName()).reverse().toString());
        storage.findAndDo(findPalindrome, (person) -> System.out.println(person.getFirstName() + " " + person.getLastName()));
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        Predicate<Person> findFirstNameStartsWithA = (person) -> person.getFirstName().startsWith("A");
        storage.findAndSort(findFirstNameStartsWithA, Comparator.comparing(Person::getBirthDate)).forEach(System.out::println);
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by latest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        LocalDate date = LocalDate.of(1950, 1, 1);
        Predicate<Person> findBirthDate = (person) -> person.getBirthDate().isBefore(date);
        storage.findAndSort(findBirthDate, (p1, p2) -> p2.getBirthDate().compareTo(p1.getBirthDate())).forEach(System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        storage.findAndSort((p1, p2) -> {
            int lastNameSort = p1.getLastName().compareTo(p2.getLastName());
            if (lastNameSort != 0) {
                return lastNameSort;
            }
            int firstNameSort = p1.getFirstName().compareTo(p2.getFirstName());
            if (firstNameSort != 0) {
                return firstNameSort;
            }
            return p1.getBirthDate().compareTo(p2.getBirthDate());
        }).forEach(System.out::println);
        //Write your code here

        System.out.println("----------------------");
    }
}
