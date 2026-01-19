#include <simple-web-server/client_http.hpp> // Client examples
using HttpClient = SimpleWeb::Client<SimpleWeb::HTTP>;

int main(int argc, char *argv[])
{
    HttpClient client("127.0.0.1:5001");
    // Synchronous request examples
    try
    {
        auto r1 = client.request("GET", "/tilde");
        std::cout << r1->content.rdbuf() << std::endl;
        // Alternatively, use the convenience function
        r1->content.string();
    }
    catch (const SimpleWeb::system_error &e)
    {
        std::cerr << "Client request error: " << e.what() << std::endl;
    }

    client.io_service->run();
    return 0;
}