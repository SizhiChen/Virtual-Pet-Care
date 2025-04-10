package controller;

import javax.swing.ImageIcon;
import pet.PetInterface;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;
import pet.helper.personality.Personality;
import view.PetView;

public class PetController {

  private final PetInterface model;
  private final PetView view;

  public PetController(PetInterface model, PetView view) {
    this.model = model;
    this.view = view;
  }

  public void startView() {
    view.playGame(this);
  }

  public void startGame() {
    model.startGame();
  }

  public HealthStatus getHealth() {
    return model.getHealth();
  }

  public void interactPet(ImageIcon[] images) {
    model.interactWith(view.getAction(images));
  }

  public MoodEnum getMood() {
    return model.getMood();
  }

  public Personality getPersonality() {
    return model.getPersonality();
  }

  public boolean isAlive() {
    return model.isAlive();
  }

  public void step() {
    model.step();
  }

  public boolean needShower() {
    return model.needShower();
  }

  public boolean needFeed() {
    return model.needFeed();
  }

  public boolean needPlay() {
    return model.needPlay();
  }

  public boolean needSleep() {
    return model.needSleep();
  }
}
