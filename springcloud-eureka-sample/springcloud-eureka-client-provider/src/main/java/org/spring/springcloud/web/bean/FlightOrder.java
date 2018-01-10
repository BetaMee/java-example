package org.spring.springcloud.web.bean;


import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import java.util.List;

@Entity
public class FlightOrder {

    @Id
    @GeneratedValue
    private Integer id;

    private String orderId; // 订单ID

    private String date; // 航段序号 如2017-10-01

    private String flightName; // 航班名称MU5040

    private String DCity; // 出发城市

    private String ACity; // 达到城市

    private Integer segmentPrice; // 价格

    private String passenger; // 乘客姓名

    public FlightOrder() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDCity() {
        return DCity;
    }

    public void setDCity(String DCity) {
        this.DCity = DCity;
    }

    public String getACity() {
        return ACity;
    }

    public void setACity(String ACity) {
        this.ACity = ACity;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public Integer getSegmentPrice() {
        return segmentPrice;
    }

    public void setSegmentPrice(Integer segmentPrice) {
        this.segmentPrice = segmentPrice;
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger;
    }

}
