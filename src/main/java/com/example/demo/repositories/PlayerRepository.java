package com.example.demo.repositories;

import org.springframework.stereotype.Repository;

import com.example.demo.models.Player;

import org.springframework.data.jpa.repository.JpaRepository;;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

}
