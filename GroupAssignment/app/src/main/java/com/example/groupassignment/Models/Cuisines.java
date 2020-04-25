package com.example.groupassignment.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cuisines {
    @SerializedName("meals")
    @Expose
    private List<Cuisine> cuisines = null;

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public class Cuisine {

        @SerializedName("strArea")
        @Expose
        private String cuisine;

        public String getCuisine() {
            return cuisine;
        }

        public void setCuisine(String cuisine) {
            this.cuisine = cuisine;
        }

    }
}
