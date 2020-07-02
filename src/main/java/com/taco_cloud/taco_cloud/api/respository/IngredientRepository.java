package com.taco_cloud.taco_cloud.api.respository;

import com.taco_cloud.taco_cloud.api.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient,String> {
}
