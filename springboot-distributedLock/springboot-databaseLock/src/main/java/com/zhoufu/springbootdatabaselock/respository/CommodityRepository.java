package com.zhoufu.springbootdatabaselock.respository;


import com.zhoufu.springbootdatabaselock.model.Commodity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommodityRepository extends JpaRepository<Commodity, Integer> {

    Commodity findByCommodityName(String name);
}
