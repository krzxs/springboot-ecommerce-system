package com.bookerinio.ecommercesystem.repository;

import com.bookerinio.ecommercesystem.model.ConfirmationToken;
import com.bookerinio.ecommercesystem.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends MongoRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findByToken(String token);

    Optional<ConfirmationToken> findByUser(User user);

    void deleteByUser(User user);
}
