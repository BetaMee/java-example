package org.spring.springcloud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springcloud.utils.JsonMapper;
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
public class MongoProviderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoProviderController.class);

    @Autowired
    private Registration registration;       // 服务注册

    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现客户端

    @RequestMapping("/provider")
    public String provider() {
        ServiceInstance instance = serviceInstance();
        LOGGER.info("provider service, host = " + instance.getHost()
                + ", service_id = " + instance.getServiceId());
        return "Hello,!";
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
    private UserRepository userRepository;

    // 获取所有用户数据
    @GetMapping(value = "/user")
    public Object getAllUsersList() {
        return userRepository.findAll();
    }

    // 添加用户数据
    @PostMapping(value = "/user")
    public User addUserToDB(@RequestBody String req) {
        User user  = new User();
        Map<String, Object> reqMap = null;
        // 添加数据
        // 解析数据
        try {
            reqMap = JsonMapper.getObjectMapper().readValue(req, Map.class);
            // 写入数据库
            user.setId((Integer) reqMap.get("id"));
            user.setUsername((String) reqMap.get("username"));
            user.setAge((Integer) reqMap.get("age"));
            user.setStuId((String) reqMap.get("stuId"));
            user.setProfession((String) reqMap.get("profession"));
            user.setGrade((String) reqMap.get("grade"));

            return userRepository.save(user);
        } catch (IOException e) {
            return null;
        }



    }

    // 通过stuId获取信息
    @GetMapping(value = "/user/{stuId}")
    public User getUserByStuId(@PathVariable("stuId") String stuId) {
        return userRepository.findByStuId(stuId);
    }

    // 通过stuId删除数据
    @PostMapping(value = "/user/delete")
    public String deleUserByStuId(@RequestBody String req) {

        Map<String, Object> reqMap = null;
        // 添加数据
        // 解析数据
        try {
            reqMap = JsonMapper.getObjectMapper().readValue(req, Map.class);
            // 删除数据
            userRepository.deleteByStuId((String) reqMap.get("stuId"));
            return "delete user successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "delete user error";
        }
    }

}