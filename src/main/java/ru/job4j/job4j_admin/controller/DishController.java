package ru.job4j.job4j_admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.job4j_admin.model.Dish;
import ru.job4j.job4j_admin.service.DishService;
import ru.job4j.job4j_admin.service.KafkaHandlingService;

@Controller
@AllArgsConstructor
@RequestMapping("dish")
public class DishController {

    private final KafkaHandlingService dishService;

    @GetMapping("")
    public String getAllDishes(Model model) {
        model.addAttribute("dishes", dishService.findAll());
        return "allDishes";
    }

    @GetMapping("/addDishForm")
    public String addDishForm(Model model) {
        model.addAttribute("dish", new Dish(0, "Name", "Description"));
        return "addDishForm";
    }

    @PostMapping("/add")
    public String addDish(@ModelAttribute Dish dish) {
        dishService.save(dish);
        return "redirect:/dish";
    }

    @GetMapping("/updateDishForm")
    public String updateDishForm(Model model, @RequestParam("id") int id) {
        model.addAttribute("dish", dishService.findById(id));
        return "updateDishForm";
    }

    @PostMapping("/update")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.update(dish);
        return "redirect:/dish";
    }

    @GetMapping("/delete")
    public String deleteDish(@RequestParam("id") int id) {
        dishService.delete(id);
        return "redirect:/dish";
    }
}
