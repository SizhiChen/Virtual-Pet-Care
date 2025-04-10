import controller.PetController;
import pet.Pet;
import pet.PetInterface;
import view.PetView;

public class GUIPetMain {
  public static void main(String[] args) {
    PetInterface model = new Pet();
    PetView view = new PetView();
    PetController controller = new PetController(model, view);
    controller.startView();
  }
}
