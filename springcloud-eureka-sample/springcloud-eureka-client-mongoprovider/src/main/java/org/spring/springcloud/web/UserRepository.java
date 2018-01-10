package org.spring.springcloud.web;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
    // 通过学号来获取用户信息
    User findByStuId(String stuId);
    // 通过学号来删除用户信息
    void deleteByStuId(String stuId);
}
