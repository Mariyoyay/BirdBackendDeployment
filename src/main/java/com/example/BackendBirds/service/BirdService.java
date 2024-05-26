package com.example.BackendBirds.service;

import com.example.BackendBirds.model.Bird;
import com.example.BackendBirds.model.Egg;
import com.example.BackendBirds.repository.IBirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BirdService implements IService{

    @Autowired
    private IBirdRepository birdRepository;

    @Override
    public boolean saveBird(Bird bird) {
        Bird birdSaved = birdRepository.save(bird);
        return bird.equals(birdSaved);
    }

    @Override
    public List<Bird> getAllBirds() {
        return birdRepository.findAll();
    }

    @Override
    public Bird getBirdById(int id) {
        return birdRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteBird(int id) {
        try{
            birdRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateBird(int id, Bird updatedBird) {
        Bird bird = birdRepository.getReferenceById(id);
        bird.setName(updatedBird.getName());
        bird.setAge(updatedBird.getAge());
        bird.setColor(updatedBird.getColor());
        bird.setEggs(updatedBird.getEggs());
        Bird birdSaved = birdRepository.save(bird);
        updatedBird.setId(id);
        return birdSaved.equals(updatedBird);
    }

    @Override
    public List<Egg> getEggsByBirdId(int birdId) {
        Bird bird = birdRepository.findById(birdId).orElse(null);
        if (bird == null) return null;
        return bird.getEggs();
    }

    @Override
    public Egg getEggById(int birdId, int eggId) {
        Bird bird = birdRepository.findById(birdId).orElse(null);
        if (bird == null) return null;
        List<Egg> eggs = bird.getEggs();
        if (eggs == null) return null;
        return eggs.stream().filter(e -> e.getId() == eggId).findFirst().orElse(null);
    }

    @Override
    public boolean addEgg(int birdId, Egg egg) {
        Bird bird = birdRepository.findById(birdId).orElse(null);
        if (bird == null) return false;
        bird.getEggs().add(egg);
        birdRepository.save(bird);
        return true;
    }

    @Override
    public boolean deleteEgg(int birdId, int eggId) {
        Bird bird = birdRepository.findById(birdId).orElse(null);
        if (bird == null) return false;
        List<Egg> eggs = new ArrayList<>();
        for (Egg egg : bird.getEggs()) {
            if (egg.getId() != eggId) {
                eggs.add(egg);
            }
        }
        bird.setEggs(eggs);
        birdRepository.save(bird);
        return true;
    }

    @Override
    public boolean updateEgg(int birdId, int eggId, Egg updatedEgg) {
        Bird bird = birdRepository.findById(birdId).orElse(null);
        if (bird == null) return false;
        Egg egg = bird.getEggs().stream().filter(e -> e.getId() == eggId).findFirst().orElse(null);
        if (egg == null) return false;
        egg.setName(updatedEgg.getName());
        egg.setWeight(updatedEgg.getWeight());
        egg.setDiameter(updatedEgg.getDiameter());
        birdRepository.save(bird);
        return true;
    }

    @Override
    public boolean otherUser(String username, int birdId) {
        return !getBirdById(birdId).getApplicationUser().getUsername().equals(username);
    }

    @Override
    public List<Bird> getAllBirdsByUser(String username) {
        return getAllBirds().stream().filter(bird -> Objects.equals(bird.getApplicationUser().getUsername(), username)).toList();
    }

    @Override
    public Bird getBirdByIdByUser(String username, int birdId) {
        Bird bird = getBirdById(birdId);
        if (bird.getApplicationUser().getUsername().equals(username)) return bird;
        return null;
    }

    @Override
    public boolean updateBirdByUser(String username, int birdId, Bird updatedBird) {
        if (otherUser(username, birdId)) return false;
        return updateBird(birdId, updatedBird);
    }

    @Override
    public boolean deleteBirdByUser(String username, int id) {
        if (otherUser(username, id)) return false;
        return deleteBird(id);
    }

    @Override
    public List<Egg> getEggsByBirdIdByUser(String username, int birdId) {
        if (otherUser(username, birdId)) return new ArrayList<>();
        return getEggsByBirdId(birdId);
    }

    @Override
    public Egg getEggByIdByUser(String username, int birdId, int eggId) {
        if (otherUser(username, birdId)) return null;
        return getEggById(birdId, eggId);
    }

    @Override
    public boolean addEggByUser(String username, int birdId, Egg egg) {
        if (otherUser(username, birdId)) return false;
        return addEgg(birdId, egg);
    }

    @Override
    public boolean updateEggByUser(String username, int birdId, int eggId, Egg egg) {
        if (otherUser(username, birdId)) return false;
        return updateEgg(birdId, eggId, egg);
    }

    @Override
    public boolean deleteEggByUser(String username, int birdId, int eggId) {
        if (otherUser(username, birdId)) return false;
        return deleteEgg(birdId, eggId);
    }


}
