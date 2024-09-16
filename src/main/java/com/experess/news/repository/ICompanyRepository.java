package com.experess.news.repository;

import com.experess.news.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyRepository extends JpaRepository<Company,String> {
}
