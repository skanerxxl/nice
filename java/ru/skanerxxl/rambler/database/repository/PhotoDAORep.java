package ru.skanerxxl.rambler.database.repository;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import ru.skanerxxl.rambler.database.dao.PhotoDAO;
import ru.skanerxxl.rambler.database.entity.Photo;
import ru.skanerxxl.rambler.exception.PhotoNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PhotoDAORep implements PhotoDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ResponseEntity<byte[]> viewPhoto(long id) throws PhotoNotFoundException {
        Photo photo = entityManager.find(Photo.class, id);
        byte[] bytes = photo.getBody();
        if (bytes == null)
            throw new PhotoNotFoundException();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
}
