import model.SuitDatabase;
import view.MainView;
import controller.SuitController;

public class MainApp {
    public static void main(String[] args) {
        SuitDatabase database = new SuitDatabase();
        MainView mainView = new MainView();
        SuitController controller = new SuitController(database, mainView);

        mainView.setVisible(true);
    }
}