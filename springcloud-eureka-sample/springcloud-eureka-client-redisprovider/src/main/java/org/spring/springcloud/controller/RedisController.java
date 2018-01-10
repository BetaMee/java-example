package org.spring.springcloud.controller;

import org.spring.springcloud.bean.Person;
import org.spring.springcloud.repo.PersonRepo;
import org.spring.springcloud.utils.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class RedisController {
    @Autowired
    private PersonRepo personRepo;


    @RequestMapping("/person")
    @ResponseBody
    public Object testHash(){
        Map<Object, Object> personMatrixMap = personRepo.findAll();
        System.out.println("@@当前Redis存储的所有用户：" + personMatrixMap);

        Person person = new Person();
        person.setId(1);
        person.setAge(55);
        person.setGender(Person.Gender.Female);
        person.setName("Oracle");

        personRepo.save(person);

        Person person2 = new Person();
        person2.setId(2);
        person2.setAge(60);
        person2.setGender(Person.Gender.Male);
        person2.setName("TheArchitect");

        personRepo.save(person2);

        Person person3 = new Person();
        person3.setId(3);
        person3.setAge(25);
        person3.setGender(Person.Gender.Male);
        person3.setName("TheOne");

        personRepo.save(person3);

        System.out.println("查找ID为3的用户 : " + personRepo.find("3"));

        personMatrixMap = personRepo.findAll();

        System.out.println("当前Redis存储的所有用户：" + personMatrixMap);

        personRepo.delete("2");

        personMatrixMap = personRepo.findAll();

        System.out.println("删除ID为2的用户后，剩余的所有用户: " + personMatrixMap);
        return person;
    }

    @GetMapping(value = "/person")
    public Object getPersonFromCache() {
        return personRepo.findAll();
    }

    @PostMapping(value = "/person")
    public String addPersonToCache(@RequestBody String req) {
        Map<String, Object> reqMap = null;
        // 解析数据
        try {
            reqMap = JsonMapper.getObjectMapper().readValue(req, Map.class);
            // 写入缓存
            Person person = new Person();

            person.setId((Integer) reqMap.get("id"));
            person.setAge((Integer) reqMap.get("age"));
            person.setGender(Person.Gender.Female);
            person.setName((String) reqMap.get("name"));

            personRepo.save(person);
            return "Cache successfully";

        } catch (IOException e) {
            return "Cache Error";
        }

    }


}
