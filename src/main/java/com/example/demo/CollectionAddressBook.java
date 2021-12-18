package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class CollectionAddressBook implements AddressBook{

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonList(){return personList;}

    @Override
    public void add(Person person) {

    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

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
        personList.add(new Person("Yulia", "1234211"));
        personList.add(new Person("Oksana", "0989024"));
        personList.add(new Person("Bogdan", "5274952"));
    }
}
