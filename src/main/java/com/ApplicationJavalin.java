package com;

import io.javalin.Javalin;

public class ApplicationJavalin {

	public static void main(String[] args) {
		//instancia do javalin
		Javalin app = Javalin.create().start(7000);
		UserController userController = new UserController();
		//Get todos
		app.get("/users", userController::getAllUsers);
		//Get por id
		app.get("/user/:id", userController::getUserById);
		//Incluir
		app.post("/user", userController::createUser);
		//Atualizar por nome ou id
		app.put("/user/:id", userController::updateUser);
		//Deletar por id
		app.delete("/user/:id", userController::deleteUser);
	}

}
