package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectionAddressBook implements AddressBook{

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonList(){return personList;}

    private static int NEXTID = 4;

    @Override
    public void add(Person person) {
        person.setID(NEXTID);
        personList.add(person);
        NEXTID++;
    }

    @Override
    public void update(Person person) {
        personList.set(person.getId(), person);
    }

    @Override
    public void delete(Person person) {
        personList.remove(person);
    }

    public void print(){
        int number = 0;
        System.out.println();
        for(Person person: personList){
            number++;
            System.out.println(number+") ПІП: "+ person.getPIP()+"; Телефон: "+person.getPhone());
        }
    }
    public void fillTestData(){
        personList.add(new Person(0,"Yulia", "1234211"));
        personList.add(new Person(1,"Oksana", "0989024"));
        personList.add(new Person(2,"Bogdan", "5274952"));
    }

    public ObservableList<Person> search(String search){
        ObservableList<Person> list = FXCollections.observableArrayList();
         list = personList.stream().filter(person -> person.getPIP().contains(search) || person.getPhone().contains(search)).collect(Collectors.toCollection(FXCollections::observableArrayList));
        return list;
    }
}
