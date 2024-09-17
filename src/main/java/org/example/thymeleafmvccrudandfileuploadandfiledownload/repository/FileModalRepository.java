package org.example.thymeleafmvccrudandfileuploadandfiledownload.repository;

import org.example.thymeleafmvccrudandfileuploadandfiledownload.entity.FileModalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileModalRepository extends JpaRepository<FileModalEntity,Integer> {
    @Query(
            value = "select * from file_modal where deleted_at is null ",
            nativeQuery = true
    )
    public List<FileModalEntity>getFaylModal();

    @Query(
            value = "select f from FileModalEntity f where id=:id and f.deletedAt is null "
    )
    public FileModalEntity getFile(@Param("id")Integer id);

}
