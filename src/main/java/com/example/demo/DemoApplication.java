package com.example.demo;

import io.javalin.Javalin;

public class DemoApplication {

	public static void main(String[] args) {
		//instancia do javali
		Javalin app = Javalin.create().start(7000);
		UserController userController = new UserController();
		app.get("/users", userController::getAllUsers);
		app.get("/user/:id", userController::getUserById);
		app.post("/user", userController::createUser);
		app.put("/user/:id", userController::updateUser);
		app.delete("/user/:id", userController::deleteUser);
	}

}
