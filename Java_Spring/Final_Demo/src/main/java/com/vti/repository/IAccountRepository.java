package com.vti.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Account;
import com.vti.entity.AccountStatus;

public interface IAccountRepository extends JpaRepository<Account, Short>, JpaSpecificationExecutor<Account> {
	public Account findByUsername(String name);

	public Account findByEmail(String email);

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);

	@Query("SELECT status FROM Account WHERE email = :email")
	public AccountStatus findStatusByEmail(@Param("email") String email);

	@Modifying
	@Transactional
	@Query("DELETE FROM Account WHERE id IN(:ids)")
	public void deleteByIds(@Param("ids") List<Short> ids);
}
