package pet.helper.personality;

import pet.PetInterface;
import pet.helper.Action;
import pet.helper.mood.MoodEnum;

public interface PersonalityInterface {
  void modifyStep(PetInterface pet, MoodEnum mood);

  void applyPersonalityInteract(PetInterface pet, MoodEnum mood, Action action);
}
