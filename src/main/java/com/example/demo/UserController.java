package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserController {
    public static final String USER = "user:";
    private final RedisConfig redisConfig;
    private int currentId = 1;

    public UserController(){
        this.redisConfig = new RedisConfig();
    }

    //CRIAÇÃO DE USUÁRIOS NO REDIS
    public void createUser(Context ctx){
        try{
            // Inicializando usuário
            String name = getName(ctx);
            int age = getAge(ctx);
            int id = currentId++;
            User user = new User(id, name, age);
            // Redis
            String userJson = getObjectMapper().writeValueAsString(user);
            redisConfig.getJedis().set(USER + id, userJson);
            ctx.result("Usuário salvo com sucesso!");
            ctx.status (201).json(user);
        }catch (JsonProcessingException jsp){
            throw new RuntimeException(jsp);
        }
    }

    //LISTAR TODOS OS USUÁRIOS
    public void getAllUsers(Context ctx) {
        // Obtendo todas as chaves no Redis
        Set<User> users = new HashSet<>();
        // Recuperando os usuários e filtrando valores nulos
        for(String key : redisConfig.getJedis().keys("*")){
            String json = redisConfig.getJedis().get(key);
            if (json != null) {
                try {
                    users.add(getObjectMapper().readValue(json, User.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ctx.json(users);

    }

    //CRIAÇÃO DE USUÁRIO POR ID
    public void getUserById(Context ctx) {
        try{
            // Inicializando usuário
            int userId = getId(ctx);
            String userJson = getUserJson(userId);
            // Redis
            if (userJson != null) {
                User user = getObjectMapper().readValue(userJson, User.class);
                // Enviar o objeto User como JSON
                ctx.json(user);
            } else {
                ctx.status(404).result("Usuário não encontrado");
            }
        }catch (JsonProcessingException jsp){
            throw new RuntimeException(jsp);
        }catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    //UPDATE - ATUALIZAR UM USUÁRO EXISTENTE
    public void updateUser(Context ctx) {
        try{
            // Inicializando usuário
            int userId = getId(ctx);
            String name = getName(ctx);
            int age = getAge(ctx);
            // Redis
            if(getUserJson(userId) != null){
                User user = new User(userId, name, age);
                String userJson = getObjectMapper().writeValueAsString(user);
                redisConfig.getJedis().set(USER + userId, userJson);
                ctx.result("Usuário atualizado com sucesso!");
        }else{
            ctx.status(404).result("Usuário não encontrado");
        }  }catch (JsonProcessingException processingException){
            processingException.printStackTrace();
        }
    }

    //DELETE - REMOVER UM USUÁRIO POR ID
    public void deleteUser(Context ctx) {
        // Inicializando usuário
        int userId = getId(ctx);
        String redisKey = USER + userId; // Removi o espaço após "user"
        // Redis
        if(redisConfig.getJedis().get(redisKey) != null){
            redisConfig.getJedis().del(redisKey);
            ctx.result("Usuário removido com sucesso: "+ userId +"!");
        }else {
            ctx.status(404).result("Usuário não encontrado");
        }
    }

    private String getUserJson(int userId) {
        return redisConfig.getJedis().get(USER + userId);
    }

    private ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    private static int getId(Context ctx) {
        return Integer.parseInt(ctx.pathParam("id"));
    }

    private static int getAge(Context ctx) {
        return Integer.parseInt(Objects.requireNonNull(ctx.formParam("age")));
    }

    private static @Nullable String getName(Context ctx) {
        return ctx.formParam("name");
    }

}
