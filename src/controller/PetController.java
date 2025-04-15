package controller;

import javax.swing.ImageIcon;
import pet.PetInterface;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;
import pet.helper.personality.Personality;
import view.PetView;

/**
 * The {@code PetController} class acts as the intermediary between the Pet model and the view.
 * It handles user interactions from the view, updates the model accordingly, and retrieves
 * updated data to be reflected in the view.
 * This controller follows the MVC (Model-View-Controller) architecture, ensuring separation
 * of logic between data (model), UI (view), and control flow (controller).
 */
public class PetController {

  private final PetInterface model;
  private final PetView view;

  /**
   * Constructs a {@code PetController} with the given model and view.
   *
   * @param model the Pet model implementing {@code PetInterface}
   * @param view the view responsible for rendering UI and handling display logic
   */
  public PetController(PetInterface model, PetView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * Starts the view and initializes the game UI.
   */
  public void startView() {
    view.playGame(this);
  }

  /**
   * Starts or resets the game logic in the model.
   */
  public void startGame() {
    model.startGame();
  }

  /**
   * Retrieves the current health status of the pet.
   *
   * @return the pet's health status
   */
  public HealthStatus getHealth() {
    return model.getHealth();
  }

  /**
   * Sends user-selected interaction (based on image buttons) to the model.
   *
   * @param images an array of image icons representing user actions
   */
  public void interactPet(ImageIcon[] images) {
    model.interactWith(view.getAction(images));
  }

  /**
   * Gets the current mood of the pet.
   *
   * @return the pet's mood
   */
  public MoodEnum getMood() {
    return model.getMood();
  }

  /**
   * Gets the personality type of the pet.
   *
   * @return the pet's personality
   */
  public Personality getPersonality() {
    return model.getPersonality();
  }

  /**
   * Checks if the pet is currently alive.
   *
   * @return {@code true} if the pet is alive, {@code false} otherwise
   */
  public boolean isAlive() {
    return model.isAlive();
  }

  /**
   * Advances the game state by one step. Typically called by a timer.
   */
  public void step() {
    model.step();
  }

  /**
   * Checks whether the pet currently needs a shower.
   *
   * @return {@code true} if the pet needs a shower
   */
  public boolean needShower() {
    return model.needShower();
  }

  /**
   * Checks whether the pet currently needs to be fed.
   *
   * @return {@code true} if the pet is hungry
   */
  public boolean needFeed() {
    return model.needFeed();
  }

  /**
   * Checks whether the pet currently needs to play.
   *
   * @return {@code true} if the pet wants to play
   */
  public boolean needPlay() {
    return model.needPlay();
  }

  /**
   * Checks whether the pet currently needs to sleep.
   *
   * @return {@code true} if the pet is sleepy
   */
  public boolean needSleep() {
    return model.needSleep();
  }
}
