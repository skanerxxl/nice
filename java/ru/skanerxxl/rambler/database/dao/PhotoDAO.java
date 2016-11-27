package ru.skanerxxl.rambler.database.dao;

import org.springframework.http.ResponseEntity;

public interface PhotoDAO {

    ResponseEntity<byte[]> viewPhoto(long id);
}
