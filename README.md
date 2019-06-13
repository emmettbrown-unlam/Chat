# Chat

Chat TCP hecho en Java utilizando sus implementaciones de ClientSocket y ServerSocket. El mismo cuenta con una GUI manejada por Swing, la cual utiliza SwingWorkers para evitar que el EDT sea bloqueado
por el método asíncrono inputStream.readObject(). 

El chat admite múltiples usuarios, y además utiliza uno de los conceptos bases de la POO, el polimorfismo, para el enviar mensajes y decidir el curso de acción a realizar al recibir un mensaje (esto puede ser aprovechado, por ejemplo, 
cuando uno necesita enviar un comando al servidor).