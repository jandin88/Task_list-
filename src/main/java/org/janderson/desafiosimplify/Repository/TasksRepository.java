package org.janderson.desafiosimplify.Repository;

import org.janderson.desafiosimplify.entities.Tasks;
import org.janderson.desafiosimplify.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TasksRepository extends MongoRepository<Tasks, String> {

    @Query("{'user.id': ?0}")
    List<Tasks> findAllByUserId(String userEmail);


    Tasks findByTitleIgnoreCaseAndUser(String title, User user);

    void deleteByTitle(String title);
}
