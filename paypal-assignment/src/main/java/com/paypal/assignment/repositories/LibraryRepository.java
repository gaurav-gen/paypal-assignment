/**
 * 
 */
package com.paypal.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paypal.assignment.entities.Library;

/**
 * To perform DB operations on Library entity
 * 
 * @author gaurav
 *
 */
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
