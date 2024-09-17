package org.example.thymeleafmvccrudandfileuploadandfiledownload.repository;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {


    Optional<UserEntity> findByEmailAndDeletedAtIsNull(String email);

    @Query(
            value = "select * from user_registration where deleted_at is null ",
            nativeQuery = true
    )
    List<UserEntity>userlar();

    @Query(
            value = "select u from UserEntity u where u.deletedAt is null and u.id=:id"
    )
    public UserEntity user(Integer id);

}
