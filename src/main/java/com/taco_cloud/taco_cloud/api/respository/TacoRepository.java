package com.taco_cloud.taco_cloud.api.respository;

import com.taco_cloud.taco_cloud.api.model.Taco;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacoRepository extends CrudRepository<Taco,Long> {

}
