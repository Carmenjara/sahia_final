package ec.edu.utpl.smp.app.smpaplication.models.entities;

public class CodeGenerator {

	// Variable estática para mantener el estado del contador
	private static int contador = 0;
	private static final int longitud = 7; // Longitud fija del código

	// Método para generar el siguiente código secuencial
	public synchronized String generarCodigo(String prefijo) {
		contador++;
		// Formatear el código con ceros a la izquierda y agregar el prefijo
		return prefijo + String.format("%0" + longitud + "d", contador);
	}
}