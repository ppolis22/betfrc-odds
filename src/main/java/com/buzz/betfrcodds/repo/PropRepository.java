package com.buzz.betfrcodds.repo;

import com.buzz.betfrcodds.entity.Prop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropRepository extends JpaRepository<Prop, String> {
    List<Prop> findByParentId(String parentId);
    Boolean existsByParentIdAndType_Id(String parentId, String typeId);
}
