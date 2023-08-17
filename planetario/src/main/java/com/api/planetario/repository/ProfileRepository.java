package com.api.planetario.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.planetario.model.Profile;
import com.api.planetario.model.User;

public interface ProfileRepository extends JpaRepository<Profile, Long>{


}
