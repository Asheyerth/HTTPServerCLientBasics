function llamadoHTTP() {

    const http = require('http');
    const options = {
        hostname: '127.0.0.1',
        port: 5001,
        path: '/numero',
        method: 'GET',
        headers:
            { 'Content-Type': 'text/html; charset=utf-8' }
    };

    const req = http.request(
    /*Parametro 1*/options,
    /*Parametro 2*/(res) => {
            console.log('Pasa 1');
            let data = '';
            // A chunk of data has been received.  
            res.on('data', (chunk) => {
                console.log('Pasa 2');
                data += chunk;
            });
            console.log('1.1')
            // The whole response has been received.
            res.on('end', () => {
                console.log('Pasa 3');
                // console.log('statusCode:', res.statusCode);
                // console.log('headers:', res.headers);
                // Access response headers    // console.log('content');
                //console.log(JSON.parse(JSON.stringify(data)));
                console.log(data);
            });
            console.log('1.2')
        }
    );
    req.on('error', (error) => {
        console.error(error);
    });
    console.log('Pasa 4');
    req.end();
    console.log('Pasa 5');

    function trabajar() {
        console.log('Trabajar');
    }

    var trabajar2 = () => {
        console.log('Trabajar');
    }
    
    
}

var numero_llamada = llamadoHTTP()
 
var suma = 1 + numero_llamada
console.log("suma = ",suma)


