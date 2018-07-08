package ru.gorabchev.lesson2.essence;

public class Product {

    private int id;
    private final int prodid;
    private String title;
    private float cost;

    public Product(int prodid, String title, float cost) {
        this.prodid = prodid;
        this.title = title;
        this.cost = cost;
    }

    public Product(int prodid) {
        this.prodid = prodid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProdid() {
        return prodid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
