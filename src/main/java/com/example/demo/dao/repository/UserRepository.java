package com.example.demo.dao.repository;

import com.example.demo.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    /* @Query(nativeQuery = true, value = "SELECT * FROM users u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
     List<UserEntity> findAllByName(@Param("name") String name);
 */
    @Query("SELECT u FROM UserEntity u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<UserEntity> findAllByName(@Param("name") String name);

    List<UserEntity> findAllByActiveAccountIsFalse();


}
