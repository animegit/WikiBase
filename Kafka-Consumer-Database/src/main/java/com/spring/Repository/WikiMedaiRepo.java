package com.spring.Repository;

import com.spring.Entity.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiMedaiRepo extends JpaRepository<WikimediaData,Long> {

}
