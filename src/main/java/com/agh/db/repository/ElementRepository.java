package com.agh.db.repository;

import com.agh.db.entity.ElementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Dawid on 11.06.2017.
 */
@Service
@Repository
public interface ElementRepository extends JpaRepository<ElementEntity, Long> {
}
