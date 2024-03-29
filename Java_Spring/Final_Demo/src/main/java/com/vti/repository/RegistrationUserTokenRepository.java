package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.RegistrationUserToken;

public interface RegistrationUserTokenRepository extends JpaRepository<RegistrationUserToken, Integer> {

	public RegistrationUserToken findByToken(String token);

	public boolean existsByToken(String token);

	@Query("SELECT token FROM RegistrationUserToken WHERE user_id = :accountID")
	public String findByUserId(int accountID);

	@Transactional
	@Modifying
	@Query("DELETE FROM RegistrationUserToken WHERE user_id = :userId")
	public void deleteByUserId(int userId);

}
