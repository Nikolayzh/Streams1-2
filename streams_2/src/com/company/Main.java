package com.company;

import java.util.*;
import java.util.stream.*;


public class Main {
  public static void main(String[] args) {
    List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
    List<String> families =
        Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
    Collection<Person> persons = new ArrayList<>();
    for (int i = 0; i < 10_000_000; i++) {
      persons.add(
          new Person(
              names.get(new Random().nextInt(names.size())),
              families.get(new Random().nextInt(families.size())),
              new Random().nextInt(100),
              Sex.values()[new Random().nextInt(Sex.values().length)],
              Education.values()[new Random().nextInt(Education.values().length)]));
    }
    String count = null;
    // Найти количество людей младше 18 лет.
    persons.stream().filter(p -> p.getAge() < 18).count();

    System.out.println("количество несовершеннолетних " + count);

    // Получить список фамилий призывников.
    persons.stream()
            .filter(person -> person.getAge() >= 18 && person.getAge() < 27)
            .map(Person::getName)
            .collect(Collectors.toList());

    //firstNames.forEach(System.out::println);

    // Отсортировать
    persons.stream()
            .filter(p -> Education.HIGHER.equals(p.getEducation()))
            .filter(p -> p.getAge() >= 18)
            .filter(
                    p ->
                            (Sex.MAN.equals(p.getSex()) && p.getAge() < 65)
                                    || (Sex.WOMAN.equals(p.getSex()) && p.getAge() < 60))
            .sorted(Comparator.comparing(Person::getFamily))
            .collect(Collectors.toList());


  }
}
