package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
	@Query(value = "select * from admin where username = ?1 and password = ?2", nativeQuery = true)
	public Admin login(String username, String password);

}
