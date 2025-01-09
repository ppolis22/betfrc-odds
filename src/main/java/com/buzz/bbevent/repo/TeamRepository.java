package com.buzz.bbevent.repo;

import com.buzz.bbevent.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> { }
