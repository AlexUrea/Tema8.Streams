package com.streams.tema8;

import com.streams.tema8.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class Tema8Application implements Comparator<User> {

    static List<User> users = new ArrayList<>();
    private static Integer id = 0;

    public static void main(String[] args) {
        User user1 = new User();
        user1.setId(++id);
        User user2 = new User();
        user2.setId(++id);
        User user3 = new User();
        user3.setId(++id);

        users.add(user1);
        users.add(user2);
        users.add(user3);


        Supplier<Stream<User>> streamSupplier = () -> Stream.of(user1, user2, user3);

        //prints all users
        System.out.println("All users:\n");
        streamSupplier.get().forEach(System.out::println);

        //Prints users with age < 18
        System.out.println("\nUsers with age < 18:\n");
        streamSupplier.get().filter(n -> n.getAge() < 18).forEach(System.out::println);

        //Prints users with double their age:
        System.out.println("\nUsers with double their age:\n");
        streamSupplier.get().map(x -> x.getAge() * 2).forEach(System.out::println);

        //Prints the last element of the stream:
        System.out.println("\nLast element of the stream:\n");
        System.out.println(streamSupplier.get().reduce((first, second) -> second).orElse(null));

        //Prints the user with the smallest age and the one with the greatest age:
        System.out.println("\nUsers with the smallest age and greatest age:\n");
        System.out.println(streamSupplier.get().min(Comparator.comparing(User::getAge)).orElseThrow(NoSuchElementException::new));
        System.out.println(streamSupplier.get().max(Comparator.comparingInt(User::getAge)).orElseThrow(NoSuchElementException::new));

        //Prints all users without duplicates:
        System.out.println("\nUsers without duplicates:\n");
        streamSupplier.get().distinct().forEach(System.out::println);

        //Prints users with age >30, names transformed in uppercase and sorted descending by age:
        System.out.println("\nUsers with age >30, names transformed in uppercase and sorted descending by age:\n");
        streamSupplier.get().filter(n -> n.getAge() > 30).map(x -> x.getName().toUpperCase()).sorted( Comparator.reverseOrder()).forEach(System.out::println);


        SpringApplication.run(Tema8Application.class, args);
    }

    @Override
    public int compare(User u1, User u2){
        if(u1.getAge() == null || u2.getAge() == null){
            throw new IllegalArgumentException("No age found");
        }
        return u1.getAge().compareTo(u2.getAge());
    }

}
