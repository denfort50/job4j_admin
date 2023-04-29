package ru.job4j.job4j_admin.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_admin.model.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class KafkaListeningService {

    private boolean updateResponse;
    private boolean deleteResponse;
    private List<Dish> findAllResponse;
    private Dish findByIdResponse;
    private Dish findByNameResponse;

    @KafkaListener(topics = "save-responses-topic", groupId = "group-id")
    public void receiveSaveResponse(String response) {
        System.out.println(response);
    }

    @KafkaListener(topics = "update-responses-topic", groupId = "group-id")
    public void receiveUpdateResponse(String response) {
        updateResponse = Objects.equals(response, ResponseEntity.ok().build().toString());
    }

    @KafkaListener(topics = "delete-responses-topic", groupId = "group-id")
    public void receiveDeleteResponse(String response) {
        deleteResponse = Objects.equals(response, ResponseEntity.ok().build().toString());
    }

    @KafkaListener(topics = "findAll-responses-topic", groupId = "group-id")
    public void receiveFindAllResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("dishes");
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonChildObject = jsonArray.getJSONObject(i);
            Dish dish = new Dish(
                    jsonChildObject.getInt("id"),
                    jsonChildObject.getString("name"),
                    jsonChildObject.getString("description"));
            dishes.add(dish);
        }
        findAllResponse = dishes;
    }

    @KafkaListener(topics = "findById-responses-topic", groupId = "group-id")
    public void receiveFindByIdResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        findByIdResponse = new Dish(
                jsonObject.getInt("id"),
                jsonObject.getString("name"),
                jsonObject.getString("description"));
    }

    @KafkaListener(topics = "findByName-responses-topic", groupId = "group-id")
    public void receiveFindByNameResponse(String response) {
        JSONObject jsonObject = new JSONObject(response);
        findByNameResponse = new Dish(
                jsonObject.getInt("id"),
                jsonObject.getString("name"),
                jsonObject.getString("description"));
    }

    public boolean isUpdateResponse() {
        return updateResponse;
    }

    public boolean isDeleteResponse() {
        return deleteResponse;
    }

    public List<Dish> getFindAllResponse() {
        return findAllResponse;
    }

    public Dish getFindByIdResponse() {
        return findByIdResponse;
    }

    public Dish getFindByNameResponse() {
        return findByNameResponse;
    }
}
