package bus.domian;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Message {
    private String giftnumber;
    private String giftname;
    private String gifttype;
    private String color;
    private double buyprice;
    private double rentprice;
    private String describr;
    private Integer s_number;
    private String carimg;
    @JsonFormat(pattern ="yyyy-MM-dd-HH:mm:ss",timezone = "GMT+8")
    private Date createtime;
    public String getGiftnumber() {
        return giftnumber;
    }

    public void setGiftnumber(String giftnumber) {
        this.giftnumber = giftnumber;
    }

    public String getGifttype() {
        return gifttype;
    }

    public void setGifttype(String gifttype) {
        this.gifttype = gifttype;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(double buyprice) {
        this.buyprice = buyprice;
    }

    public double getRentprice() {
        return rentprice;
    }

    public void setRentprice(double rentprice) {
        this.rentprice = rentprice;
    }


    public Integer getS_number() {
        return s_number;
    }

    public void setS_number(Integer s_number) {
        this.s_number = s_number;
    }

    public String getCarimg() {
        return carimg;
    }

    public void setCarimg(String carimg) {
        this.carimg = carimg;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getGiftname() {
        return giftname;
    }

    public void setGiftname(String giftname) {
        this.giftname = giftname;
    }

    public String getDescribr() {
        return describr;
    }

    public void setDescribr(String describr) {
        this.describr = describr;
    }
}
