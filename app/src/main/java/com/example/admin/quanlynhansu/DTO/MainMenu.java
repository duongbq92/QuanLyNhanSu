package com.example.admin.quanlynhansu.DTO;

/**
 * Created by RPG_LOVER on 02/05/2017.
 */

public class MainMenu {
    private int image_id;
    private String menu_items;

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getMenu_items() {
        return menu_items;
    }

    public void setMenu_items(String menu_items) {
        this.menu_items = menu_items;
    }

    public MainMenu(int image_id, String menu_items) {
        this.image_id = image_id;
        this.menu_items = menu_items;
    }
}
