package com.taco_cloud.taco_cloud;

import com.taco_cloud.taco_cloud.api.model.Ingredient;
import com.taco_cloud.taco_cloud.api.model.Ingredient.Type;
import com.taco_cloud.taco_cloud.api.model.Taco;
import com.taco_cloud.taco_cloud.api.model.TacoOrder;
import com.taco_cloud.taco_cloud.api.model.User;
import com.taco_cloud.taco_cloud.api.respository.IngredientRepository;
import com.taco_cloud.taco_cloud.api.respository.TacoOrderRepository;
import com.taco_cloud.taco_cloud.api.respository.TacoRepository;
import com.taco_cloud.taco_cloud.api.respository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}


	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo, TacoRepository tacoRepo, TacoOrderRepository orderRepo, UserRepository userRepo){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
				repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
				repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
				repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE));


				Taco taco=new Taco();
				taco.setName("ExampleTaco");
				List<Ingredient> ing=new ArrayList<>();
				ing.add(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
				ing.add(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
				ing.add(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
				ing.add(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
				ing.add(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
				ing.add(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
				taco.setIngredients(ing);
				taco=tacoRepo.save(taco);

				List<Taco> tacos=new ArrayList<>();
				tacos.add(taco);

				TacoOrder order=new TacoOrder();
				order.setName("ExampleName");
				order.setCity("Delhi");
				order.setState("Delhi");
				order.setStreet("Random Street");
				order.setPincode("909099809");
				order.setCcNumber("4024007103939509");
				order.setMobile_number("90990940494");
				order.setCcCVV("898");
				order.setCcExpiration("12/22");
				order.setTacos(tacos);

				order=orderRepo.save(order);


				List<TacoOrder> orders=new ArrayList<>();
				orders.add(order);
				User user=new User();
				user.setUsername("username");
				user.setPassword("password");
				user.setEmail("xyz@xyz.com");
				user.setTaco_orders(orders);

				userRepo.save(user);

			}
		};
	}

}
