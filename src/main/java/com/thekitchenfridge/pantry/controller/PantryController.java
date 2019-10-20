package com.thekitchenfridge.pantry.controller;

import com.thekitchenfridge.pantry.entity.PantryItem;
import com.thekitchenfridge.pantry.service.PantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class PantryController {

    private PantryService pantryService;

    @Autowired
    private void getPantryService(PantryService pantryService){
        this.pantryService = pantryService;
    }

    public ResponseEntity<List<PantryItem>> getPantryList(){return null;}
    public ResponseEntity<PantryItem> getPantryItem() {return null;}

    public ResponseEntity<String> addItemToPantry(@RequestBody PantryItem pantryItem) {
        this.pantryService.saveItem(pantryItem);
        return null;}

    public ResponseEntity<String> removeItemFromPantry() {return null;}
    public ResponseEntity<String> editPantryItem() {return null;}
    public ResponseEntity<String> editPantry() {return null;}

}
