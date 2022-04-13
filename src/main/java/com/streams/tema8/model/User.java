package com.streams.tema8.model;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

@AllArgsConstructor
@Setter
@Getter
@Entity
public class User implements Comparable<User>{
    @Id
    @GeneratedValue
    private Integer id;     //Incremental ID
    private String name;
    private Integer age;

    public void createUser(){
        IntStream a = new Random().ints(1, 0, 101); //random age
        OptionalInt first = a.findFirst();
        this.age = first.getAsInt();

        Faker faker = new Faker();
        this.name = faker.name().fullName();//random name
    }

    public User() {
        this.createUser();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", age=" + age + '}';
    }

    @Override
    public int compareTo(User o) {
        return this.age - o.age;
    }
}
