package de.hs.da.hskleinanzeigen.domain;

public class CategoryApi {

    private String name;
    private int parentId;

    public CategoryApi() {
    }

    public CategoryApi(String name, int parentId) {
        this.name = name;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
