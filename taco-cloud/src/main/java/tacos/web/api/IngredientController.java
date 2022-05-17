package tacos.web.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository ingredientRepo;
    @Autowired
    EntityLinks entityLinks;

    public IngredientController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping
    public Iterable<Ingredient> getAllIngredients() {
        return ingredientRepo.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient ingredientById(@PathVariable("id") String id) {
        Optional<Ingredient> optIngredient = ingredientRepo.findById(id);
        if (optIngredient.isPresent()) {
            return optIngredient.get();
        }
        return null;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient postIngredient(@RequestBody Ingredient
                                             ingredient) {
        return ingredientRepo.save(ingredient);
    }

    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId")
                                 Long ingredientId) {
        try {

            ingredientRepo.deleteById(ingredientId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

    @DeleteMapping("/{ingredientId}")
    public void deleteIngredient(@PathVariable("ingredientId")
                                 Long ingredientId) {
        try {

            ingredientRepo.deleteById(ingredientId);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}