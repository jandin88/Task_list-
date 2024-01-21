package org.janderson.desafiosimplify.Repository;

import org.janderson.desafiosimplify.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,String> {
}
