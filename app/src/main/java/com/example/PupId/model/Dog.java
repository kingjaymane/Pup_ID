package com.example.PupId.model;

public class Dog {
    public int id;
    public String breedOne;
    public String breedTwo;
    public String breedThree;
    public String percentageBreedOne;
    public String percentageBreedTwo;
    public String percentageBreedThree;
    public String selectedBreedStr;
    public int selectedBreed;
    public String uriImage;
    public int breedOneId;
    public int breedTwoId;
    public int breedThreeId;

    public Dog(int id,String breedOne,String breedTwo,String breedThree,String percentageBreedOne,
               String percentageBreedTwo, String percentageBreedThree,String uriImage,
               String selectedBreedStr,int selectedBreed,int breedOneId,int breedTwoId,
               int breedThreeId){

        this.id = id;
        this.breedOne = breedOne;
        this.breedTwo = breedTwo;
        this.breedThree = breedThree;
        this.percentageBreedOne = percentageBreedOne;
        this.percentageBreedTwo = percentageBreedTwo;
        this.percentageBreedThree = percentageBreedThree;
        this.uriImage = uriImage;
        this.selectedBreedStr = selectedBreedStr;
        this.selectedBreed = selectedBreed;
        this.breedOneId = breedOneId;
        this.breedTwoId = breedTwoId;
        this.breedThreeId = breedThreeId;
    }

    public int getBreedOneId() {
        return breedOneId;
    }

    public void setBreedOneId(int breedOneId) {
        this.breedOneId = breedOneId;
    }

    public int getBreedTwoId() {
        return breedTwoId;
    }

    public void setBreedTwoId(int breedTwoId) {
        this.breedTwoId = breedTwoId;
    }

    public int getBreedThreeId() {
        return breedThreeId;
    }

    public void setBreedThreeId(int breedThreeId) {
        this.breedThreeId = breedThreeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBreedOne() {
        return breedOne;
    }

    public void setBreedOne(String breedOne) {
        this.breedOne = breedOne;
    }

    public String getBreedTwo() {
        return breedTwo;
    }

    public void setBreedTwo(String breedTwo) {
        this.breedTwo = breedTwo;
    }

    public String getBreedThree() {
        return breedThree;
    }

    public void setBreedThree(String breedThree) {
        this.breedThree = breedThree;
    }

    public String getPercentageBreedOne() {
        return percentageBreedOne;
    }

    public void setPercentageBreedOne(String percentageBreedOne) {
        this.percentageBreedOne = percentageBreedOne;
    }

    public String getPercentageBreedTwo() {
        return percentageBreedTwo;
    }

    public void setPercentageBreedTwo(String percentageBreedTwo) {
        this.percentageBreedTwo = percentageBreedTwo;
    }

    public String getPercentageBreedThree() {
        return percentageBreedThree;
    }

    public void setPercentageBreedThree(String percentageBreedThree) {
        this.percentageBreedThree = percentageBreedThree;
    }

    public String getSelectedBreedStr() {
        return selectedBreedStr;
    }

    public void setSelectedBreedStr(String selectedBreedStr) {
        this.selectedBreedStr = selectedBreedStr;
    }

    public int getSelectedBreed() {
        return selectedBreed;
    }

    public void setSelectedBreed(int selectedBreed) {
        this.selectedBreed = selectedBreed;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }




}