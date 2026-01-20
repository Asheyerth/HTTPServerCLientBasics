const http = require('http')



function getData(options) {
    return new Promise((resolve, reject) => {
        let data = '';
        console.log('EntraPromise 0');
        const res = http.request(options, (res) => {
            console.log('EntraReq 1');
            res.on('data', (chunk) => {
                console.log('Pasa 2');
                data += chunk;
            });
            res.on('connect', () => {
                console.log("asdasda")
            });
            res.on('end', () => {
                try { //actions
                    console.log('Pasa 3');
                    console.log("data " + data);
                    resolve(data) //return
                } catch (err) { //error handler
                    console.log('EntraError3');
                    reject(err);
                }
            });
        });

        res.on('error', (err) => { //another <error handler
            reject(err);
        });

        console.log('CallReq');
        res.end();
    });
}



async function llamadoHTTP() {
    console.log("entra llamado");
    //Def http request
    const options = {
        hostname: '127.0.0.1',
        port: 5001,
        path: '/numero',
        method: 'GET',
        headers:
            { 'Content-Type': 'text/html; charset=utf-8' }
    };

    console.log("llamadoData");
    var num = await getData(options)
    //var num = getData(options)
    console.log("num" + num)

    return num

}


//main
llamadoHTTP().then((v) => {
    console.log("then")
    console.log(v);
    var a = parseInt(v)
    var suma = 1 + a
    console.log("suma = ", suma)
    console.log("numero " + v)
});
