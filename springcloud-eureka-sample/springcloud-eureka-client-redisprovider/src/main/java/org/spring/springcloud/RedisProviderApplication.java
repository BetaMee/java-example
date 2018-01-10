package org.spring.springcloud;

import org.spring.springcloud.bean.Person;
import org.spring.springcloud.bean.Person.Gender;
import org.spring.springcloud.repo.PersonRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;





@EnableEurekaClient
@SpringBootApplication
public class RedisProviderApplication implements CommandLineRunner {
    @Autowired
    private PersonRepo personRepo;


    public void testHash(){
        Map<Object, Object> personMatrixMap = personRepo.findAll();
        System.out.println("@@当前Redis存储的所有用户：" + personMatrixMap);

        Person person = new Person();
        person.setId(1);
        person.setAge(55);
        person.setGender(Gender.Female);
        person.setName("Oracle");

        personRepo.save(person);

        Person person2 = new Person();
        person2.setId(2);
        person2.setAge(60);
        person2.setGender(Gender.Male);
        person2.setName("TheArchitect");

        personRepo.save(person2);

        Person person3 = new Person();
        person3.setId(3);
        person3.setAge(25);
        person3.setGender(Gender.Male);
        person3.setName("TheOne");

        personRepo.save(person3);

        System.out.println("查找ID为3的用户 : " + personRepo.find("3"));

        personMatrixMap = personRepo.findAll();

        System.out.println("当前Redis存储的所有用户：" + personMatrixMap);

        personRepo.delete("2");

        personMatrixMap = personRepo.findAll();

        System.out.println("删除ID为2的用户后，剩余的所有用户: " + personMatrixMap);
    }



    @Override
    public void run(String... args) throws Exception {
//        this.testHash();

    }

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(RedisProviderApplication.class,args);

    }
}