#include <iostream>
#include <string>
#include <cstring>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>
#include <thread>
#include <chrono> //for the sleep

/*
Initialize the Socket: socket() - Pick up the phone.
Configure the Socket: setsockopt() - Adjust settings, like allowing address reuse.
Bind the Socket: bind() - Assign a phone number.
Listen for Connections: listen() - Wait for calls.
Accept Connections: accept() - Answer the call.
Send/Receive Data: read() and write() - Chat with the caller.
Close Connections: close() - Hang up when done.
*/

void handle_client(int client_fd) {
    // Read request
        char buffer[1024] = {0};
        read(client_fd, buffer, sizeof(buffer) - 1); //Reads data from the client into buffer
        std::string request(buffer); //Convert to strings

        // Parse path (simplified)
        std::string path = "/"; // Assume root for simplicity
        if (request.find("GET / ") != std::string::npos) { //for the root URL
            path = "/";
        //for other URLs PERSONAL_NOTE: I think this is fine to fix the number to look for because the other URL are also fixed in code. 
        } else if (request.find("GET /") != std::string::npos) {
            size_t start = request.find("GET /") + 4; //Get the index of the initial of the path with /
            size_t end = request.find(" ", start); //Get the index of the final of the path 
            path = request.substr(start, end - start); //Get the path with /
        }

        // Send response
        std::string response;
        //URLs Routes
        if (path =="/") {
            response ="HTTP/1.1 200 OK\r\n"
            "Content-Type: text/html;charset=utf-8\r\n"
            "\r\n<h1>Hola célula!</h1>";
        }else if (path =="/sintilde") {
            response ="HTTP/1.1 200 OK\r\n"
            "Content-Type: text/html;charset=utf-8\r\n"
            "\r\n<h1>Esta es la primera pagina sin tilde</h1>";
        }else if (path =="/contilde") {
            response ="HTTP/1.1 200 OK\r\n"
            "Content-Type: text/html;charset=utf-8\r\n"
            "\r\n<h1>Esta es la segunda página</h1>"
            "\r\n<h2>Esta si contiene tíldes.</h2>";
        }else {
            response ="HTTP/1.1 404 Not Found\r\n"
            "Content-Type: text/html;charset=utf-8\r\n"
            "\r\n<h1>404 Not Found</h1>";
        }

        std::this_thread::sleep_for(std::chrono::seconds(5)); //sleep 5 seconds

        write(client_fd, response.c_str(), response.length());

        // Close client socket
        close(client_fd);
}



int main() {
    // Create socket
    int server_fd = socket(AF_INET, SOCK_STREAM, 0); //Creates a TCP socket for IPv4 with default protocol 
    if (server_fd == -1) { //If it fails, show a message
        std::cerr << "Socket creation failed\n";
        return 1;
    }

    // Allow address reuse. This is a problem with the port use in C++
    int opt = 1;
    setsockopt(server_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    // Set up server address
    struct sockaddr_in server_addr; //Structure for internet socket addresses
    server_addr.sin_family = AF_INET; //IPv4
    server_addr.sin_addr.s_addr = INADDR_ANY; //Bind to all available interfaces. I stil don't understand this 
    server_addr.sin_port = htons(4221); //Port number (converted to network byte order)

    // Bind socket. bind: Associates the socket with the specified address and port. 
    if (bind(server_fd, (struct sockaddr *)&server_addr, sizeof(server_addr)) < 0) {
        std::cerr << "Bind failed\n"; //If it fails, show a message
        return 1;
    }

    // Listen for connections. Marks the socket as passive (ready to accept connections with a max number. This has to be upgraded
    if (listen(server_fd, 5) < 0) {
        std::cerr << "Listen failed\n"; //If it fails, show a message
        return 1;
    }

    std::cout << "Server listening on port 4221...\n";

    while (true) { //Infinite loop to handle client connections. I don't like this. 
        // Accept client connection
        struct sockaddr_in client_addr; 
        socklen_t client_addr_len = sizeof(client_addr);
        int client_fd = accept(server_fd, (struct sockaddr *)&client_addr, &client_addr_len); //- aits for and accepts a new connection. Returns a new socket descriptor for the client.
        if (client_fd < 0) {
            std::cerr << "Accept failed\n"; //If it fails, show a message
            continue;
        }

        //With only calling it one time
        //handle_client(client_fd);

        //Threads
        // Handle client in a new thread.
        std::thread client_thread(handle_client, client_fd);
        client_thread.detach(); // Detach to avoid blocking
    }

    // Close server socket (unreachable in this loop because of the while. A break might do)
    close(server_fd);
    return 0;
}