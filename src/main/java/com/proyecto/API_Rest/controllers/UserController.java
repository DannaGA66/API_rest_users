package com.proyecto.API_Rest.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.proyecto.API_Rest.models.UserModel;
import com.proyecto.API_Rest.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ArrayList<UserModel> getUsers(){
        return userService.GetUsers();
    }

    @PostMapping()
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping( path = "/{id}")
    public Optional<UserModel>GetById(@PathVariable("id")Long id){
        return this.userService.GetById(id);
    }

    @GetMapping("/query")
    public ArrayList<UserModel>GetByPriority(@RequestParam("priority")Integer priority){
        return this.userService.GetByPriority(priority);
    }

    @DeleteMapping (path = "/{id}")
    public String deleteById(@PathVariable("id")Long id){

        boolean ok = this.userService.deleteUser(id);

        if(ok){

            return "Se ha eliminado el ususario correctamente" + id;

        }else{

            return "No se pudo eliminar el ususario con id" + id;
        
        }
    }

}
