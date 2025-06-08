package com.buzz.betfrcodds.repo;

import com.buzz.betfrcodds.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> { }
