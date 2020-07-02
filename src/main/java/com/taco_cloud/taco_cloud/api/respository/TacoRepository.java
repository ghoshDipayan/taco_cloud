package com.taco_cloud.taco_cloud.api.respository;

import com.taco_cloud.taco_cloud.api.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@Repository
public interface TacoRepository extends CrudRepository<Taco,Long> {

}
