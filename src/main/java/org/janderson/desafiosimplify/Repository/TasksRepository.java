package org.janderson.desafiosimplify.Repository;

import org.janderson.desafiosimplify.entities.Tasks;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TasksRepository extends MongoRepository<Tasks, String> {
}
