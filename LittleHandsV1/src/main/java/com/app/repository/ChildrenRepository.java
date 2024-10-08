package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entity.Children;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, Long> {

}
