package ru.job4j.job4j_admin.service;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.job4j.job4j_admin.model.Dish;

import java.util.List;

@Service
@AllArgsConstructor
public class KafkaHandlingService implements DishService {

    private KafkaTemplate<String, Object> kafkaTemplate;

    private KafkaListeningService kafkaListeningService;

    @Override
    public void save(Dish dish) {
        JSONObject jsonDish = new JSONObject(dish);
        kafkaTemplate.send("save-requests-topic", jsonDish.toString());
    }

    @Override
    public boolean update(Dish dish) {
        JSONObject jsonDish = new JSONObject(dish);
        kafkaTemplate.send("update-requests-topic", jsonDish.toString());
        return kafkaListeningService.isUpdateResponse();
    }

    @Override
    public boolean delete(int id) {
        kafkaTemplate.send("delete-requests-topic", Integer.toString(id));
        return kafkaListeningService.isDeleteResponse();
    }

    @Override
    public List<Dish> findAll() {
        kafkaTemplate.send("findAll-requests-topic", "findAll");
        return kafkaListeningService.getFindAllResponse();
    }

    @Override
    public Dish findById(int id) {
        kafkaTemplate.send("findById-requests-topic", Integer.toString(id));
        return kafkaListeningService.getFindByIdResponse();
    }

    @Override
    public Dish findByName(String name) {
        kafkaTemplate.send("findByName-requests-topic", name);
        return kafkaListeningService.getFindByNameResponse();
    }
}
