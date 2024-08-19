package com.example.demo;

import io.javalin.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserControllerList {
    private final List<User> users = new ArrayList<>();
    private int currentId = 1;

    public void getAllUsers(Context ctx) {
        ctx.json(users);
    }

    public void getUserById(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);

        if (user != null) {
            ctx.json(user);
        } else {
            ctx.status(404).result("User not found");
        }
    }

    public void createUser(Context ctx) {
        String name = ctx.formParam("name");
        int age = Integer.parseInt(Objects.requireNonNull(ctx.formParam("age")));

        User user = new User(currentId++, name, age);
        users.add(user);

        ctx.status(201).json(user);
    }

    public void updateUser(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        String updatedName = ctx.formParam("name");
        int updatedAge = Integer.parseInt(Objects.requireNonNull(ctx.formParam("age")));

        User user = users.stream().filter(u -> u.getId() == userId).findFirst().orElse(null);

        if (user != null) {
            user.setName(updatedName);
            user.setAge(updatedAge);
            ctx.json(user);
        } else {
            ctx.status(404).result("User not found");
        }
    }

    public void deleteUser(Context ctx) {
        int userId = Integer.parseInt(ctx.pathParam("id"));
        boolean removed = users.removeIf(user -> user.getId() == userId);

        if (removed) {
            ctx.status(204).result("User deleted");
        } else {
            ctx.status(404).result("User not found");
        }
    }
}
