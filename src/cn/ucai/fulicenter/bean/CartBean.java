package cn.ucai.fulicenter.bean;

/**
 * Created by sks on 2016/4/15.
 */
public class CartBean {

    /**
     * id : 7672
     * userName : 7672
     * goodsId : 7672
     *  count : 2
     *  checked : true
     * goods : GoodDetailsBean
     */

    private int id;
    private int userName;
    private int goodsId;
    private int count;
    private boolean checked;
    private String goods;

    public CartBean(int id, int userName, int goodsId, int count, boolean checked, String goods) {
        this.id = id;
        this.userName = userName;
        this.goodsId = goodsId;
        this.count = count;
        this.checked = checked;
        this.goods = goods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserName() {
        return userName;
    }

    public void setUserName(int userName) {
        this.userName = userName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public String toString() {
        return "CartBean{" +
                "id=" + id +
                ", userName=" + userName +
                ", goodsId=" + goodsId +
                ", count=" + count +
                ", checked=" + checked +
                ", goods='" + goods + '\'' +
                '}';
    }
}
