package org.janderson.desafiosimplify.Repository;

import org.janderson.desafiosimplify.entities.Tasks;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TasksRepository extends MongoRepository<Tasks, String> {
    @Query("{ 'title' : ?0 }")
    Tasks findByTitle(String title);

    @Query("{'user.id': ?0}")
    List<Tasks> findAllByUserId(String userEmail);
}
