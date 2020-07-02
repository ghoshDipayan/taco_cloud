package com.taco_cloud.taco_cloud.api.respository;

import com.taco_cloud.taco_cloud.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User,Long> {
}
