package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.models.BlackCard;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface BlackCardRepository extends JpaRepository<BlackCard, Long> {

}
