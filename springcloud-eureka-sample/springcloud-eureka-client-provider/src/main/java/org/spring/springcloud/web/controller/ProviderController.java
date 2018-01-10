package org.spring.springcloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springcloud.web.bean.FlightOrder;
import org.spring.springcloud.web.repo.FlightOrderRepo;
import org.spring.springcloud.web.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Provider HelloWorld 案例
 * <p>
 * Created by bysocket on 06/22/17.
 */
@RestController
public class ProviderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    private Registration registration;       // 服务注册

    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现客户端

    @RequestMapping("/provider")
    public String provider() {
        ServiceInstance instance = serviceInstance();
        LOGGER.info("provider service, host = " + instance.getHost()
                + ", service_id = " + instance.getServiceId());
        return "Hello,Provider!";
    }

    /**
     * 获取当前服务的服务实例
     *
     * @return ServiceInstance
     */
    public ServiceInstance serviceInstance() {
        List<ServiceInstance> list = discoveryClient.getInstances(registration.getServiceId());
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }



    @Autowired
    private FlightOrderRepo flightOrderRepo;

    /**
     * 查询所有Order信息
     * */
    @GetMapping(value = "/flightOrders")
    public List<FlightOrder> getFlightOrdersList() {
        return flightOrderRepo.findAll();
    }

    /***
     * 添加数据库信息
     */
    @RequestMapping(value = "/flightOrders")
    public FlightOrder addFlightOrderToDB(@RequestBody String req) {
        FlightOrder flightOrder = new FlightOrder();
        Map<String, Object> reqMap = null;
        // 解析数据
        try {
            reqMap = JsonMapper.getObjectMapper().readValue(req, Map.class);
            // 写入数据库
            flightOrder.setOrderId((String) reqMap.get("orderId"));
            flightOrder.setDate((String)reqMap.get("date"));
            flightOrder.setFlightName((String)reqMap.get("flightName"));
            flightOrder.setDCity((String)reqMap.get("DCity"));
            flightOrder.setACity((String)reqMap.get("ACity"));
            flightOrder.setSegmentPrice((Integer) reqMap.get("segmentPrice"));
            flightOrder.setPassenger((String)reqMap.get("passenger"));

            return flightOrderRepo.save(flightOrder);
        } catch (IOException e) {
            return null;
        }



    }

    /**
     * 通过OrderId查询信息
     */
    @GetMapping(value = "/flightOrders/{orderId}")
    public FlightOrder findOrderByOrderId(@PathVariable("orderId") String orderId) {
        return flightOrderRepo.findByOrderId(orderId);
    }

    /**
     * 通过passenger来查询列表
     */
    @GetMapping(value = "/flightOrders/passenger/{passenger}")
    public List<FlightOrder> findOrderByPassenger(@PathVariable("passenger") String passenger) {
        return flightOrderRepo.findByPassenger(passenger);
    }

    /**
     * 通过ID更新数据
     */
    @PutMapping(value = "/flightOrders/update/{id}")
    public FlightOrder updateOrderById(
            @PathVariable("id") Integer id,
            @RequestParam("orderId") String orderId,
            @RequestParam("date") String date,
            @RequestParam("flightName") String flightName,
            @RequestParam("DCity") String DCity,
            @RequestParam("ACity") String ACity,
            @RequestParam("segmentPrice") Integer segmentPrice,
            @RequestParam("passenger") String passenger) {

        FlightOrder flightOrder = new FlightOrder();
        // 写入数据库
        flightOrder.setId(id);
        flightOrder.setOrderId(orderId);
        flightOrder.setDate(date);
        flightOrder.setFlightName(flightName);
        flightOrder.setDCity(DCity);
        flightOrder.setACity(ACity);
        flightOrder.setSegmentPrice(segmentPrice);
        flightOrder.setPassenger(passenger);
        // 会自动更新
        return flightOrderRepo.save(flightOrder);
    }


    /**
     * 通过OrderID来删除一条数据
     */
    @PostMapping(value = "/flightOrders/delete/")
    public String deleteOrderByOrderId(@RequestParam("id") Integer id) {
        try {
            flightOrderRepo.delete(id);
            return "this order has been deleted";
        } catch (Exception e) {
            System.out.print(e);
            return "Error";
        }

    }
}