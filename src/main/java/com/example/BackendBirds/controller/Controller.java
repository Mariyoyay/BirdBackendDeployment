package com.example.BackendBirds.controller;

import com.example.BackendBirds.model.ApplicationUser;
import com.example.BackendBirds.model.Bird;
import com.example.BackendBirds.model.Egg;
import com.example.BackendBirds.service.BirdService;
import com.example.BackendBirds.service.IService;
import com.example.BackendBirds.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/bird")
public class Controller {

    @Autowired
    private IService birdService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addBird(@RequestBody Bird bird, Principal principal) {
        ApplicationUser applicationUser = (ApplicationUser) userService.loadUserByUsername(principal.getName());
        bird.setApplicationUser(applicationUser);
        if(birdService.saveBird(bird)) {
            return "New bird is added!";
        } else {
            return "Bird could not be added!";
        }
    }

    @GetMapping("/getAll")
    public List<Bird> getAllBird(Principal principal) {
        return birdService.getAllBirdsByUser(principal.getName());
    }

    @GetMapping("/get/{id}")
    public Bird getBirdById(@PathVariable int id, Principal principal) {
        return birdService.getBirdByIdByUser(principal.getName(), id);
    }

    @PutMapping("/update/{id}")
    public String updateBird(@PathVariable int id, @RequestBody Bird bird, Principal principal) {
        if(birdService.updateBirdByUser(principal.getName(), id, bird)) {
            return "Bird is updated!";
        }
        return "Bird could not be updated!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBird(@PathVariable int id, Principal principal) {
        if(birdService.deleteBirdByUser(principal.getName(), id)) {
            return "Bird is deleted!";
        }
        return "Bird could not be deleted!";
    }

    // EGGS

    @GetMapping("/{birdId}/egg/getAll")
    public List<Egg> getEggsByBirdId(@PathVariable int birdId, Principal principal) {
        return birdService.getEggsByBirdIdByUser(principal.getName(), birdId);
    }

    @GetMapping("/{birdId}/egg/get/{eggId}")
    public Egg getEggById(@PathVariable int birdId, @PathVariable int eggId, Principal principal) {
        return birdService.getEggByIdByUser(principal.getName(), birdId, eggId);
    }

    @PostMapping("/{birdId}/egg/add")
    public String addEgg(@PathVariable int birdId, @RequestBody Egg egg, Principal principal) {
        if (birdService.addEggByUser(principal.getName(), birdId, egg)) {
            return "Egg is added!";
        }
        return "Egg could not be added!";
    }

    @PutMapping("/{birdId}/egg/update/{eggId}")
    public String updateEgg(@PathVariable int birdId, @PathVariable int eggId, @RequestBody Egg egg, Principal principal) {
        if (birdService.updateEggByUser(principal.getName(), birdId, eggId, egg)) {
            return "Egg is updated!";
        }
        return "Egg could not be updated!";
    }

    @DeleteMapping("/{birdId}/egg/delete/{eggId}")
    public String deleteEgg(@PathVariable int birdId, @PathVariable int eggId, Principal principal) {
        if(birdService.deleteEggByUser(principal.getName(), birdId, eggId)) {
            return "Egg is deleted!";
        }
        return "Egg could not be deleted!";
    }
}
