package org.spring.springcloud.web.repo;

import org.spring.springcloud.web.bean.FlightOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightOrderRepo extends JpaRepository<FlightOrder, Integer> {

    // 根据用户查航班信息
    public List<FlightOrder> findByPassenger(String passenger);
    // 根据OrderID来查询
    public FlightOrder findByOrderId(String orderId);
    // 根据OrderID来删除数据
    public void deleteByOrderId(String orderId);
}
