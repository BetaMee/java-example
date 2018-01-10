package org.spring.springcloud.web;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.management.ObjectName;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Customer HelloWorld 案例
 * <p>
 * Created by bysocket on 06/22/17.
 */
@RestController
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private RestTemplate restTemplate; // HTTP 访问操作类

    @RequestMapping("/customer")
    public String customer() {
        String providerMsg = restTemplate.getForEntity("http://PROVIDER-SERVICE/provider",
                String.class).getBody();

        return "Hello,Customer! msg from provider : <br/><br/> " + providerMsg;
    }
    // mysql-provider应用接口

    // 获取全部数据
    @GetMapping(value = "/getflightOrders")
    public Object getFlightOrders() {

        List flightOrders = restTemplate
                .getForEntity("http://PROVIDER-SERVICE/flightOrders", List.class)
                .getBody();
        return flightOrders;

    }

    // 添加数据
    @PostMapping(value = "/postflightOrders")
    public Object postFlightOrders(@RequestParam("orderId") String orderId,
                                   @RequestParam("date") String date,
                                   @RequestParam("flightName") String flightName,
                                   @RequestParam("DCity") String DCity,
                                   @RequestParam("ACity") String ACity,
                                   @RequestParam("segmentPrice") Integer segmentPrice,
                                   @RequestParam("passenger") String passenger) {

        String mysqlProviderUrl = "http://PROVIDER-SERVICE/flightOrders";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("date", date);
        map.put("flightName", flightName);
        map.put("DCity", DCity);
        map.put("ACity", ACity);
        map.put("segmentPrice", segmentPrice);
        map.put("passenger", passenger);
        HttpEntity entity = new HttpEntity<Object>(map);
        ResponseEntity<Object> resp = restTemplate.postForEntity(mysqlProviderUrl,entity,Object.class);

        return resp;
    }

    // 通过订单号查询
    @GetMapping(value = "/getflightOrders/{orderId}")
    public Object getFlightOrders(@PathVariable("orderId") String orderId) {

        Object flightOrders = restTemplate
                .getForEntity("http://PROVIDER-SERVICE/flightOrders/" + orderId, Object.class)
                .getBody();
        return flightOrders;

    }


    // MongoDB数据接口

    // 获取所有用户数据
    @GetMapping(value = "/getAllUsers")
    public Object getAllUsersList() {

        List Users = restTemplate
                .getForEntity("http://MONGODB-PROVIDER-SERVICE/user", List.class)
                .getBody();
        return Users;
    }

    // 添加用户数据
    @PostMapping(value = "/adduser")
    public Object addUser(
            @RequestParam("id") Integer id,
            @RequestParam("username") String username,
            @RequestParam("age") Integer age,
            @RequestParam("stuId") String stuId,
            @RequestParam("profession") String profession,
            @RequestParam("grade") String grade
    ) {

        String mongoProviderUrl = "http://MONGODB-PROVIDER-SERVICE/user";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("username", username);
        map.put("age", age);
        map.put("stuId", stuId);
        map.put("profession", profession);
        map.put("grade", grade);
        HttpEntity entity = new HttpEntity<Object>(map);
        ResponseEntity<Object> resp = restTemplate.postForEntity(mongoProviderUrl,entity,Object.class);
        return resp;
    }


    // 通过stuID获取数据
    @GetMapping(value = "/getUserbyId/{stuid}")
    public Object getUserByStuID(@PathVariable("stuid") String stuid) {

        Object User = restTemplate
                .getForEntity("http://MONGODB-PROVIDER-SERVICE/user/" + stuid, Object.class)
                .getBody();
        return User;
    }

    // 通过stuID删除数据
    @PostMapping(value = "/deleteuser")
    public Object deleteUserById(@RequestParam("stuId") String stuId) {
        String mongoProviderUrl = "http://MONGODB-PROVIDER-SERVICE/user/delete";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("stuId", stuId);
        HttpEntity entity = new HttpEntity<Object>(map);
        ResponseEntity<String> resp = restTemplate.postForEntity(mongoProviderUrl,entity,String.class);
        return resp;
    }

    // Redis 服务接口
    // 获取用户接口
    @GetMapping(value = "/getperson")
    public Object getPersonFromCache() {

        Object persons = restTemplate
                .getForEntity("http://REDIS-PROVIDER-SERVICE/person", Object.class)
                .getBody();
        return persons;
    }

    // 添加数据接口
    @PostMapping(value = "/addperson")
    public Object addPersonToCache(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("age") Integer age
    ) {
        String redisProviderUrl = "http://REDIS-PROVIDER-SERVICE/person";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Lists.newArrayList(MediaType.APPLICATION_JSON));
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("age", age);
        HttpEntity entity = new HttpEntity<Object>(map);
        ResponseEntity<String> resp = restTemplate.postForEntity(redisProviderUrl,entity,String.class);
        return resp;
    }


}