package com.thekitchenfridge.pantry.controller;

import com.thekitchenfridge.pantry.entity.PantryItem;
import com.thekitchenfridge.pantry.service.PantryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class PantryController {

    private PantryService pantryService;

    @Autowired
    private void getPantryService(PantryService pantryService){
        this.pantryService = pantryService;
    }

    public HttpResponse<List<PantryItem>> getPantryList(){return null;}
    public HttpResponse<PantryItem> getPantryItem() {return null;}

    public HttpResponse<String> addItemToPantry(@RequestBody PantryItem pantryItem) {
        this.pantryService.saveItem(pantryItem);
        return null;}

    public HttpResponse<String> removeItemFromPantry() {return null;}
    public HttpResponse<String> editPantryItem() {return null;}
    public HttpResponse<String> editPantry() {return null;}

}
