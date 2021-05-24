package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			// Creamos el contenedor
			AnchorPane anchor = new AnchorPane();
			// Establecemos la imagen de fondo
			anchor.setStyle("-fx-background-image: url(file:///C:/Users/paula/Downloads/mangoFondo.jpg)");

			// Creamos un checkBox
			CheckBox pincha = new CheckBox("Márcame si Sí.");

			// Le otorgamos un evento al checkBox cuando sea pulsado
			pincha.setOnMouseClicked(value -> {

				// Creamos un objeto de la clase Alert que sea de tipo confirmacion
				Alert alert = new Alert(AlertType.CONFIRMATION);
				// Establecemos el texto de la cabecera
				alert.setHeaderText("Confirma la acción");
				// Texto del cuerpo
				alert.setContentText("¿De verdad deseas abandonar esta ventana?");

				// Creamos un objeto opcional de tipo Button,
				Optional<ButtonType> result = alert.showAndWait();
				// Si resultado es pulsado en OK, se abre una nueva ventana
				if (result.get() == ButtonType.OK) {
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
					cerrar.setOnMouseEntered(value3 -> {

						// Hacemos que cuando pase sobre el boton de cerrar se cambie su color
						cerrar.setBackground(
								new Background(new BackgroundFill(Color.CORAL, new CornerRadii(20.0), Insets.EMPTY)));

						// Si pinchamos el boton, se cerrara la ventana
						cerrar.setOnMousePressed(value4 -> {
							mangoStg.close();
						});

					});

					// Indicamos que los objetos del vbox iran en el centro
					vbox.setAlignment(Pos.CENTER);

					// Anadimos los nodos
					vbox.getChildren().addAll(mango, cerrar);

					// Creamos una escena, se la establecemos al stage y lo mostramos
					Scene escenaMango = new Scene(vbox, 750, 600);
					mangoStg.setScene(escenaMango);
					mangoStg.show();

				} else {
					alert.close();
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
