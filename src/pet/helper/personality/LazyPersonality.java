package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

public class LazyPersonality implements PersonalityInterface {

  @Override
  public void modifyStep(PetInterface pet, MoodEnum mood) {
    int step = (mood == MoodEnum.HAPPY) ? -2 : -3;
    pet.adjustNeeds(
        step,
        step,
        - 1,
        step - 1);
  }

  @Override
  public void applyPersonalityInteract(PetInterface pet, MoodEnum mood, Action action) {
    switch (action){
      case FEED -> pet.adjustNeeds(40, -2, -2, -2);
      case PLAY -> pet.adjustNeeds(-2, -2, 20, -2);
      case CLEAN -> pet.adjustNeeds(-2, 40, -2, -2);
      case SLEEP -> pet.adjustNeeds(-2, -2, -2, 80);
      default -> pet.adjustNeeds(0, 0, 0, 0);
    }  }
}
