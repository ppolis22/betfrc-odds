package com.buzz.betfrcodds.repo;

import com.buzz.betfrcodds.entity.PropValue;
import com.buzz.betfrcodds.entity.PropValueId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropValueRepository extends JpaRepository<PropValue, PropValueId> {
    void deleteByPropId(String id);
}
