package com.thekitchenfridge.recipes.entities;

import com.thekitchenfridge.audit.Auditor;

public class Ingredient extends Auditor<String> {


    private long id;
    private String name;
    private String brand;
    private long upc;

}
