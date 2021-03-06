package com.ayadykin.ggstars.test.library.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ayadykin.ggstars.test.library.entity.AppFlag;

@Repository
public interface AppFlagRepository extends CrudRepository<AppFlag, String> {

}
