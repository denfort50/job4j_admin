package ru.job4j.job4j_admin.service;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.job4j.job4j_admin.model.Dish;

import java.util.List;

@Service
@AllArgsConstructor
public class DishServiceImpl implements DishService {

    private static final String URL = "http://localhost:8081/dish/";

    private final RestTemplate client;

    public void save(Dish dish) {
        client.postForEntity(URL + "save", dish, Dish.class);
    }

    public boolean update(Dish dish) {
        return client.exchange(
                        URL + "update", HttpMethod.POST, new HttpEntity<>(dish), Void.class)
                .getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public boolean delete(int id) {
        return client.exchange(
                        URL + "delete/" + id, HttpMethod.DELETE, HttpEntity.EMPTY, Void.class)
                .getStatusCode() != HttpStatus.NOT_FOUND;
    }

    public List<Dish> findAll() {
        return client.exchange(
                URL + "findAll", HttpMethod.GET, null, new ParameterizedTypeReference<List<Dish>>() {
                }).getBody();
    }

    public Dish findById(int id) {
        return client.exchange(
                URL + "findById/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<Dish>() {
                }).getBody();
    }

    public Dish findByName(String name) {
        return client.exchange(
                URL + "findByName/" + name, HttpMethod.GET, null, new ParameterizedTypeReference<Dish>() {
                }).getBody();
    }
}
