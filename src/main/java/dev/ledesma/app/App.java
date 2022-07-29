package dev.ledesma.app;

import com.google.gson.Gson;
import dev.ledesma.handlers.*;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        Javalin app = Javalin.create();
        Gson gson = new Gson();


        app.start();

    }
}
