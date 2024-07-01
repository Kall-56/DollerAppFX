README
  DollderAppFx es un sistema de control de reporte y manejo de reportes mediante una 
  base de datos creada para la compañía Especialidades Dollder.
  Esta fue creada con los visitadores medicos y los gerentes regionales en mente, para
  facilitar el registro de actividades promocionales realizadas, automatizar el proceso
  de reporte y facilitar a la compañía datos relativos al desempeño.

Indicaciones de Uso
  Los usuarios predeterminados son:

  Administrador:
    Usuario: LiaUCAB
    Contraseña: greetings__12

  ATM:
    Usuario: Frank Flores
    Contraseña: clave1234Buena
    Usuario: Yaleida Paez
    Contraseña: saludos_12


Requerimientos
  Para compilar y correr esta aplicación las siguientes dependencias deben estar instaladas

JDK 21 + FX
  El Java Development Kit, en su version 21.0 o posteriores debe estar instalado y configurado en NetBeans.
  Pagina del JDK:
  	https://www.azul.com/downloads/?version=java-21-lts&os=windows&package=jdk-fx#zulu
  Tutorial para utilizarlo en Netbeans:
	https://www.youtube.com/watch?v=pZM4T-Ay7d0&t=244s

JavaFX y Scenebuilder
  La plataforma Scenbuilder y JavaFX es necesario para el manejo de archivos ".fxml".
  (JavaFX se instala junto con SceneBuilder)
  Pagina de Descarga:
	https://gluonhq.com/products/scene-builder/#download
  Tutorial para Vincularlo con NetBeans:
	https://youtu.be/3rwcw6Kl8Ys?si=9GbX9g3fKUhGN0Iz

    El patron de diseño MVC tiene un modelo distinto en JavaFX, Vista se refleja en los distintos archivos ".fxml"
      y Controlador se refleja en los controladores de los ".fxml", estos se vinculan mediante la carpeta ".com".
