package com.tzxylao.repository;

import com.tzxylao.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author laoliangliang
 * @since 19/4/20 上午11:00
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {
    /**
     * 根据年龄查找用户
     */
    Flux<User> findByAgeBetween(int start, int end);

    @Query("{'age':{'$gte':20,'$lte':30}}")
    Flux<User> oldUser();
}
