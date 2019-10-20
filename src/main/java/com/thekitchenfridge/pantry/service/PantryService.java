package com.thekitchenfridge.pantry.service;

import com.thekitchenfridge.pantry.entity.PantryItem;
import com.thekitchenfridge.pantry.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PantryService {

    @Autowired
    PantryRepository pantryRepository;

    public void saveItem(PantryItem pantryItem) {
        pantryRepository.save(pantryItem);
    }
}
