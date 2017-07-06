package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pavlovskii-pc on 7/6/2017.
 */
@Repository
public class DataRepository {

    private List<String> data = new ArrayList<>();


    public void save(String string){
        data.add(string);
        System.out.println("new data is saved");
        System.out.println("current payload: ");
        data.stream().forEach(System.out::println);
    }

}
