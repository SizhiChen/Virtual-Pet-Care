import controller.PetController;
import pet.Pet;
import pet.PetInterface;
import view.PetView;

/**
 * The {@code GuiPetMain} class is the entry point for launching the virtual pet game with a GUI.
 * <p>
 * It sets up the Model-View-Controller (MVC) structure by initializing the {@link Pet} model,
 * the {@link PetView} GUI, and the {@link PetController} to handle user interactions and logic.
 */
public class GuiPetMain {

  /**
   * Main method that starts the virtual pet game.
   * <p>
   * Initializes the model, view, and controller, then launches the view to begin the game.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    PetInterface model = new Pet();
    PetView view = new PetView();
    PetController controller = new PetController(model, view);
    controller.startView();
  }
}
