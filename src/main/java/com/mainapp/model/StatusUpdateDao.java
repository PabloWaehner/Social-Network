package com.mainapp.model;

import org.springframework.data.repository.PagingAndSortingRepository;

//import org.springframework.data.repository.CrudRepository;

//@Repository
//public interface StatusUpdateDao extends CrudRepository<StatusUpdate, Long> { // Status Update data access object
public interface StatusUpdateDao extends PagingAndSortingRepository<StatusUpdate, Long> {
	StatusUpdate findFirstByOrderByAddedDesc();
}
