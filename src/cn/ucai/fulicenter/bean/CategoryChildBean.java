package cn.ucai.fulicenter.bean;

/**
 * Created by sks on 2016/4/15.
 */
public class CategoryChildBean {

    /**
     * id : 348
     * parentId : 344
     * name : 败姐推荐
     * imageUrl : cat_image/256_4.png
     */

    private int id;
    private int parentId;
    private String name;
    private String imageUrl;

    public CategoryChildBean() {
    }

    public CategoryChildBean(int id, int parentId, String name, String imageUrl) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CategoryChildBean{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
