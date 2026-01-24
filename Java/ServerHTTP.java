import com.sun.net.httpserver.Headers;

import com.sun.net.httpserver.HttpExchange;

import com.sun.net.httpserver.HttpHandler;

import com.sun.net.httpserver.HttpServer;

import java.lang.Thread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServerHTTP {
    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(5001), 0);
            server.createContext("/tilde", new TildeHandler());
            server.createContext("/destilde", new DestildeHandler());
            server.createContext("/numero", new NumeroHandler());

            // server.setExecutor to let the program know the uncoming instruction to
            // handle the incoming requests.
            // newFixedThreadPool(4) create 4 threads in which the activity will be
            // held after the incoming requests.
            server.setExecutor(java.util.concurrent.Executors.newFixedThreadPool(4));

            server.start();
            System.out.printf("ServerHttpOn: Port 5001 ");
        } catch (IOException e) {
            System.out.println("Error starting the server: " + e.getMessage());
        }

        System.out.println("Esto debe imprimirse primero");

    } // Define a custom HttpHandler

    static class TildeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try { // Handle the request
                Headers h = exchange.getResponseHeaders();
                h.add("Content-Type", "text/html;charset=utf-8");
                String response = "Hola, célula";
                Thread.sleep(5000);
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                System.out.println("Error response: " + e.getMessage());
            }
        }
    }

    static class DestildeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try { // Handle the request
                Headers h = exchange.getResponseHeaders();
                h.add("Content-Type", "text/html;charset=utf-8");
                String response = "Hola, celula";
                Thread.sleep(3000);
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                System.out.println("Error response: " + e.getMessage());
            }
        }
    }

    static class NumeroHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) {
            try { // Handle the request
                Headers h = exchange.getResponseHeaders();
                h.add("Content-Type", "text/html;charset=utf-8");
                String response = "8";
                Thread.sleep(7000);
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } catch (Exception e) {
                System.out.println("Error response: " + e.getMessage());
            }
        }
    }

}