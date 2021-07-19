package com.tennisscorer.repository;

import com.tennisscorer.model.TennisMatch;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

public  interface TennisRepository extends CrudRepository<TennisMatch, Long> {

}
