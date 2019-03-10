package io.tulaa.fileprocessor.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import io.tulaa.fileprocessor.models.User;


@Repository
public interface UserRepository  extends PagingAndSortingRepository<User, Integer> {
    
    User findByNationalId(String name);
    
}
