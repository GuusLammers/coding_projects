import Model.Model;

public class App {

    public static void main(String[] args) throws Exception {
        Model model = new Model();
        View view = new View();
        new Controller(model, view);

        view.setVisible(true);
    }

}
