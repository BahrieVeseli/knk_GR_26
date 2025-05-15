package Services;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import utils.SceneLocator;

public class SceneManager {
    private static SceneManager instance;
    private static final String DEFAULT_START_PAGE = SceneLocator.LOGIN_PAGE;

    private Scene scene;
    private String currentPath;
    private Pane currentPane;

    private SceneManager() {
        this.scene = initScene();
    }

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    private Scene initScene() {
        try {
            return new Scene(loadFXML(DEFAULT_START_PAGE));
        } catch (Exception e) {
            e.printStackTrace();
            return new Scene(new Pane());
        }
    }

    public static void load(String path) throws Exception {
        if (instance == null) throw new Exception("SceneManager not initialized.");
        instance.setRoot(path);
    }

    public static void load(String path, Pane pane) throws Exception {
        if (instance == null) throw new Exception("SceneManager not initialized.");
        instance.setRoot(path, pane);
    }

    private void setRoot(String path) throws Exception {
        Parent parent = loadFXML(path);
        this.currentPath = path;
        scene.setRoot(parent);
    }

    private void setRoot(String path, Pane pane) throws Exception {
        pane.getChildren().clear();
        Parent parent = loadFXML(path);
        pane.getChildren().add(parent);
        this.currentPane = pane;
        this.currentPath = path;
    }

    private Parent loadFXML(String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        return loader.load();
    }

    public static void reload() throws Exception {
        if (instance.currentPane != null) {
            load(instance.currentPath, instance.currentPane);
        } else {
            load(instance.currentPath);
        }
    }

    public Scene getScene() {
        return this.scene;
    }
}
