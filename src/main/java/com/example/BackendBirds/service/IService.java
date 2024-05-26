package com.example.BackendBirds.service;

import com.example.BackendBirds.model.Bird;
import com.example.BackendBirds.model.Egg;

import java.util.List;

public interface IService {
    boolean saveBird(Bird bird);

    List<Bird> getAllBirds();

    Bird getBirdById(int id);

    boolean deleteBird(int id);

    boolean updateBird(int id, Bird updatedBird);

    List<Egg> getEggsByBirdId(int birdId);

    Egg getEggById(int birdId, int eggId);

    boolean addEgg(int birdId, Egg egg);

    boolean deleteEgg(int birdId, int eggId);

    boolean updateEgg(int birdId, int eggId, Egg updatedEgg);

    boolean otherUser(String username, int birdId);

    List<Bird> getAllBirdsByUser(String username);

    Bird getBirdByIdByUser(String username, int birdId);

    boolean updateBirdByUser(String username, int birdId, Bird bird);

    boolean deleteBirdByUser(String username, int id);

    List<Egg> getEggsByBirdIdByUser(String username, int birdId);

    Egg getEggByIdByUser(String username, int birdId, int eggId);

    boolean addEggByUser(String username, int birdId, Egg egg);

    boolean updateEggByUser(String username, int birdId, int eggId, Egg egg);

    boolean deleteEggByUser(String username, int birdId, int eggId);
}
