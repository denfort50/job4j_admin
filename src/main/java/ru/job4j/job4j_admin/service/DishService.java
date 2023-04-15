package ru.job4j.job4j_admin.service;

import ru.job4j.job4j_admin.model.Dish;

import java.util.List;

public interface DishService {

    void save(Dish dish);

    boolean update(Dish dish);

    boolean delete(int id);

    List<Dish> findAll();

    Dish findById(int id);

    Dish findByName(String name);
}
