package com.mainapp.model;

import org.springframework.data.repository.CrudRepository;

//@Repository
public interface StatusUpdateDao extends CrudRepository<StatusUpdate, Long> { // Status Update data access object
	StatusUpdate findFirstByOrderByAddedDesc();
}

// StatusUpdateDao has methods from the CrudRepository