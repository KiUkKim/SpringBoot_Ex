package com.kiuk.book.springboot.domain.collectinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectInfoRepository extends JpaRepository<CollectInfo, Long> {

    @Query("SELECT c FROM CollectInfo c ORDER BY c.id DESC")
    List<CollectInfo> findAllBookDesc();
}
