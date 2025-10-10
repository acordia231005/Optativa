package com.daw.persistence.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.daw.persistence.entity.task;

public interface taskRepository extends ListCrudRepository<task, Integer>{

}
