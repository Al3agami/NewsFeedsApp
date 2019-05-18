package com.agamidev.newsfeedsapp.Models;

public class DrawerItemModel {
    int image_id;
    String title;
    Boolean isSelected;

    public DrawerItemModel(int image_id, String title, Boolean isSelected){
        this.image_id = image_id;
        this.title = title;
        this.isSelected = isSelected;

    }

    public int getImage_id() {
        return image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public Boolean getSelected() {
        return isSelected;
    }
}
