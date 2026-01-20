import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class ClientHTTP {
    // Simple-web-server do not have a module for client, just for server -AW

    public static void main(String[] args) throws Exception {
        // 1. Create an HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // 2. Build an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:5001/numero")) // url
                .GET() // specify the type of request
                .header("Content-Type", "text/html;charset=utf-8") // headers format
                .build(); // to construct objects step by step.
        // It takes all the settings you’ve specified (like URI, method, headers) and
        //  creates an immutable HttpRequest instance that you can use to send the request.
        // Without .build() Your code would just be setting up a builder, but not
        //  actually creating the request object.

        // 3. Send the request synchronously (blocking)
        HttpResponse<String> response = client.send(request,
        HttpResponse.BodyHandlers.ofString());

        // Print response details
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Response Body: " + response.body());

        // Or send the request asynchronously

        CompletableFuture<String> futureResponse = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body);
        futureResponse.thenAccept(body -> System.out.println("Async Response Body: " + body));

        // Keep the program running long enough for the async call to complete
        Thread.sleep(2000); // Only for testing; remove in real apps
        //I don't have idea how I'm gonna do that in real apps. 

    }
}
