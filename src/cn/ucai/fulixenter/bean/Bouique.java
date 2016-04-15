package cn.ucai.fulixenter.bean;

/**
 * Created by sks on 2016/4/15.
 */
public class Bouique {

    /**
     * id : 262
     * title : 不一样的新妆，不一样的美丽
     * description : 快速增长修护预防脱发洗发水让头发健康快速生长更美丽
     * name : 拯救头发，美丽新妆
     * imageurl : cat_image/boutique1.jpg
     */

    private int id;
    private String title;
    private String description;
    private String name;
    private String imageurl;

    public Bouique(String imageurl, int id, String title, String description, String name) {
        this.imageurl = imageurl;
        this.id = id;
        this.title = title;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    @Override
    public String toString() {
        return "Bouique{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}