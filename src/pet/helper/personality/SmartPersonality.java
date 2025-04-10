package pet.helper.personality;

import java.util.Random;
import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

public class SmartPersonality implements PersonalityInterface {

  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    Random random = new Random();
    int step = (mood == MoodEnum.HAPPY) ? -1 : -2;
    pet.adjustNeeds(
        step + ((random.nextInt(100) < 20) ? 1 : 0),
        step + ((random.nextInt(100) < 20) ? 1 : 0),
        step + ((random.nextInt(100) < 20) ? 1 : 0),
        step + ((random.nextInt(100) < 20) ? 1 : 0));
  }

  @Override
  public void applyPersonalityInteract(PetInterface pet, MoodEnum mood, Action action) {
    switch (action){
      case FEED -> pet.adjustNeeds(40, -2, -2, -2);
      case PLAY -> pet.adjustNeeds(-2, -2, 40, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 40);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }
  }
}
