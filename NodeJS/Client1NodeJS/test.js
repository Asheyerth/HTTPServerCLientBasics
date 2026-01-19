// 1. Una función que retorna una promesa (la tarea que queremos esperar)
function tareaPesada() {
    return new Promise((a, r) => {
        const exito = true;
        if (exito) {
            a("✅ Datos procesados con éxito");
        } else {
            r("❌ Error en el proceso");
        }
    });
}
 
// 2. Función asincrónica para usar 'await'
async function ejecutarProceso() {
    console.log("1. Inicio del proceso...");
 
    try {
        // El 'await' detiene la ejecución AQUÍ hasta que la promesa termine
        const resultado = await tareaPesada();
        // Esta línea NO se ejecuta hasta que la anterior haya terminado
        console.log("2. Resultado recibido:", resultado);
    } catch (error) {
        // Si la promesa falla (reject), el error cae aquí
        console.error("Hubo un problema:", error);
    }
 
    console.log("3. Fin del proceso.");
}
 
// 3. Llamada a la función
ejecutarProceso();
console.log("4. Yo soy código externo y no me detengo por el await");