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
            server.createContext("/tilde", new MyHandler());
            server.createContext("/destilde", new MyHandler2());
            //server.createContext("/imagen", new MyHandler3());
            server.createContext("/numero", new MyHandler4());
            server.start();
            System.out.printf("ServerHttpOn: Port 5001");
        }catch (IOException e) {            
            System.out.println("Error starting the server: " + e.getMessage());
        }    
        }    // Define a custom HttpHandler    
    static class MyHandler implements HttpHandler {        
        @Override        
        public void handle(HttpExchange exchange){            
            try {                // Handle the request                
                Headers h = exchange.getResponseHeaders();
                h.add("Content-Type", "text/html;charset=utf-8");
                String response = "Hola, célula";
                Thread.sleep(5000);
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (Exception e) {                
                System.out.println("Error response: " + e.getMessage());
            }        
        }          
    }    
    static class MyHandler2 implements HttpHandler {        
        @Override        
        public void handle(HttpExchange exchange) {        
                try {            // Handle the request            
                Headers h = exchange.getResponseHeaders();
                h.add("Content-Type", "text/html;charset=utf-8");
                String response = "Hola, celula";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e) {            
                System.out.println("Error response: " + e.getMessage());
            }        
        }      
    }     

    static class MyHandler4 implements HttpHandler {        
        @Override        
        public void handle(HttpExchange exchange) {        
                try {            // Handle the request            
                Headers h = exchange.getResponseHeaders();
                h.add("Content-Type", "text/html;charset=utf-8");
                String response = "8";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException e) {            
                System.out.println("Error response: " + e.getMessage());
            }        
        }      
    }     
    
}