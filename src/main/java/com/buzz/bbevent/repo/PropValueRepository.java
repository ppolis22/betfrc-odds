package com.buzz.bbevent.repo;

import com.buzz.bbevent.entity.PropValue;
import com.buzz.bbevent.entity.PropValueId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropValueRepository extends JpaRepository<PropValue, PropValueId> {
    void deleteByPropId(String id);
}
