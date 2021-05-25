package application;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// Creamos el contenedor
			AnchorPane anchor = new AnchorPane();
			// Establecemos la imagen de fondo
			anchor.setStyle("-fx-background-image: url(file:///C:/Users/paula/Downloads/mangoFondo.jpg)");

			// Creamos un boton
			Button pincha = new Button("Pinchame si te atreves ;) ");

			// Le otorgamos un evento al boton cuando este sea pulsado
			pincha.setOnMouseClicked(value -> {

				TextInputDialog dialog = new TextInputDialog("");
				dialog.setTitle("Palabra mágica");
				dialog.setHeaderText("Si al mango poderoso tu desear acceder, la palabra mágica has de tener.");
				dialog.setContentText("Palabra mágica:");

				Optional<String> result = dialog.showAndWait();

				if (result.get().equalsIgnoreCase("Barbara")) {

					// Creamos un stage nuevo
					Stage mangoStg = new Stage();
					// Creamos un contenedor vbox
					VBox vbox = new VBox();

					// Creamos un objeto de la clase ImageView con el que introduciremos una imagen
					ImageView mango = new ImageView("application/mango.gif");

					// Le damos un evento a la imagen cuando el raton pase por encima de ella
					mango.setOnMouseEntered(value1 -> {

						com.sun.javafx.application.PlatformImpl.startup(() -> {
						});

						// Creamos un objeto de la clase media con la cancion que debera de sonar cuando
						// el raton pase por encima de la imagen
						Media cancion = new Media("file:///C:/Users/paula/Downloads/mango.mp3");

						// Creamos un objeto MediaPlayer con el que haremos que la cancion suene
						// mediante la funcion play
						MediaPlayer sonar = new MediaPlayer(cancion);

						sonar.play();

						// Creamos otro evento para mango, en el que si el raton sale fuera de la
						// imagen, la musica parara. Esto es gracias al metodo stop.
						mango.setOnMouseExited(value2 -> {

							sonar.stop();

						});
					});
					// Creamos un boton llamado cerrar
					Button cerrar = new Button("Bye, mango");
					// Le damos un evento para cuando sea pulsado
					cerrar.setOnMousePressed(value3 -> {

						// Cosa curiosa que he visto por internet, hace que se espere los milisegundos
						// que le indicas para continuar la ejecucion
						/*
						 * try {
						 * 
						 * Thread.sleep(30 * 1000);
						 * 
						 * mangoStg.close(); } catch (Exception e) { System.out.println(e); }
						 */

						// Cerramos la ventana en donde sale la cancion
						mangoStg.close();

						// Creamos un objeto de tipo Desktop
						Desktop enlace = Desktop.getDesktop();
						try {
							// Hacemos que se ejecute la url que nos llevara al video 'Bye, Bye' de David
							// Civera
							enlace.browse(new URI("https://www.youtube.com/watch?v=Y1n50Ihx1rw"));
						} catch (IOException | URISyntaxException e) {
							e.getMessage();
						}

					});

					// Indicamos que los objetos del vbox iran en el centro
					vbox.setAlignment(Pos.CENTER);

					// Anadimos los nodos
					vbox.getChildren().addAll(mango, cerrar);

					// Creamos una escena, se la establecemos al stage y lo mostramos
					Scene escenaMango = new Scene(vbox, 750, 600);
					mangoStg.setScene(escenaMango);
					mangoStg.show();

				}
			});
			// Situamos el boton
			AnchorPane.setTopAnchor(pincha, 350.0);
			AnchorPane.setLeftAnchor(pincha, 313.0);

			// Anadimos el boton al anchorPane
			anchor.getChildren().add(pincha);

			// Creamos una escena
			Scene scene = new Scene(anchor, 750, 600);

			// Establecemos la escena
			stage.setScene(scene);
			// Le damos un titulo a la ventana
			stage.setTitle("Mangonada");
			// Mostramos el stage
			stage.show();

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
