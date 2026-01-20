const http = require('http');

async function get_page() {
    const options = {
        hostname: '127.0.0.1',
        port: 5001,
        path: '/numero',
        method: 'GET',
        headers: { 'Content-Type': 'text/html; charset=utf-8' }
    };

    return new Promise((resolve, reject) => {
        let data = '';

        const req = http.request(options, (res) => {
            res.on('data', (chunk) => { data += chunk; });
            res.on('end', () => {
                try {
                    resolve(do_awesome_things_with_data(data));
                } catch (err) {
                    reject(err);
                }
            });
        });

        req.on('error', (err) => {
            reject(err);
        });

        req.end();
    });
}

//Code done by MistralAI. Hybrid from starckoverflow + options





// Source - https://stackoverflow.com/a
// Posted by steadweb, modified by community. See post 'Timeline' for change history
// Retrieved 2026-01-20, License - CC BY-SA 4.0

const https = require('https')

async function get_page() {
    const url = 'https://example.com'

    return new Promise((resolve) => {
        let data = ''

        https.get(url, res => {

            res.on('data', chunk => { data += chunk }) 

            res.on('end', () => {

               resolve(do_awesome_things_with_data(data));

            })
        }) 
    })
}

// usage

(async () => await get_page())()



//another information
async function obtenerDatos() {
  try {
    // await pausa la función hasta que fetch() resuelva la promesa
    const response = await fetch('https://jsonplaceholder.typicode.com/todos/1');
    const data = await response.json(); // await para la promesa de .json()
    console.log(data);
  } catch (error) {
    console.error("Error:", error);
  }
}

//el main que inicia todo. 
obtenerDatos();