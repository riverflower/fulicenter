package cn.ucai.fulicenter.bean;

/**
 * Created by sks on 2016/4/15.
 */
public class ColorBean {

    /**
     * catId : 262
     * colorId : 1
     * colorName : 灰色
     * colorCode : #959595
     * colorImg : http://121.197.1.20/images/201309/1380064809234134935.jpg
     */

    private int catId;
    private int colorId;
    private String colorName;
    private String colorCode;
    private String colorImg;

    public ColorBean(int catId, int colorId, String colorName, String colorCode, String colorImg) {
        this.catId = catId;
        this.colorId = colorId;
        this.colorName = colorName;
        this.colorCode = colorCode;
        this.colorImg = colorImg;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorImg() {
        return colorImg;
    }

    public void setColorImg(String colorImg) {
        this.colorImg = colorImg;
    }

    @Override
    public String toString() {
        return "ColorBean{" +
                "catId=" + catId +
                ", colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", colorImg='" + colorImg + '\'' +
                '}';
    }
}
