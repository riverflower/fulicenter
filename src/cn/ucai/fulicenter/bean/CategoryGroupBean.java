package cn.ucai.fulicenter.bean;

/**
 * Created by sks on 2016/4/15.
 */
public class CategoryGroupBean {

    /**
     * id : 334
     * name : 配饰
     * imageUrl : muying/Jewelry.png
     */

    private int id;
    private String name;
    private String imageUrl;

    public CategoryGroupBean(int id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "CategoryGroupBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
