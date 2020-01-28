package com.thekitchenfridge.pantry.repository;

import com.thekitchenfridge.pantry.entity.PantryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryRepository extends JpaRepository<PantryItem, Long> {
}
