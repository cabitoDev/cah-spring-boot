package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Game;

import org.springframework.data.jpa.repository.JpaRepository;;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}
