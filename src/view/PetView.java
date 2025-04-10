package view;

import controller.PetController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import pet.helper.Action;
import pet.helper.HealthStatus;
import pet.helper.mood.MoodEnum;

public class PetView extends JFrame {

  private boolean usingShowAdapter = true;
  private final JLabel imageLabel = new JLabel();
  private final JLabel gameStart = new JLabel();
  private final JLabel moodLabel = new JLabel();
  private final JLabel characterLabel = new JLabel();
  private final JProgressBar progressBar = new JProgressBar();
  private final JLayeredPane layeredPane = new JLayeredPane();
  private final JPanel buttonPane = new JPanel(new GridLayout(1, 4, 30, 0));
  private final ImageIcon[] defaultImages = new ImageIcon[5];
  private final ImageIcon[] bathImages = new ImageIcon[3];
  private final ImageIcon[] feedImages = new ImageIcon[3];
  private final ImageIcon[] playImages = new ImageIcon[3];
  private final ImageIcon[] sleepImages = new ImageIcon[3];
  private final JButton showerButton = new JButton("Shower!!");
  private final JButton feedButton = new JButton("Feed!!");
  private final JButton playButton = new JButton("Play!!");
  private final JButton sleepButton = new JButton("Sleep!!");
  private final JButton restartButton = new JButton("RESET");
  private final JButton statusButton = new JButton("SHOW");
  private final JButton stepButton = new JButton("STEP");
  private final JLabel statusInfo = new JLabel();
  private final JLabel wantShowerLabel = new JLabel();
  private final JLabel hungerLabel = new JLabel();
  private final JLabel wantPlayLabel = new JLabel();
  private final JLabel sleepyLabel = new JLabel();
  private final Color nightSkyBlue = new Color(8, 35, 62);
  private final Color buttonColor = new Color(160, 82, 45);
  private final Color progressColor = new Color(160, 82, 45);
  private final Color barColor = new Color(235, 200, 160);
  private final Color backgroundColor = new Color(255, 244, 230);
  private final Font buttonFont = new Font("Serif", Font.BOLD, 15);
  private final MouseAdapter showAdapter = new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
      statusButton.removeMouseListener(this);
      statusButton.addMouseListener(hideAdapter);
      statusButton.setText("HIDE");
      usingShowAdapter = false;
      statusInfo.setVisible(true);
      statusTimer.start();
    }
  };
  private final MouseAdapter hideAdapter = new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
      statusButton.removeMouseListener(this);
      statusButton.addMouseListener(showAdapter);
      statusButton.setText("SHOW");
      usingShowAdapter = true;
      statusInfo.setVisible(false);
      statusTimer.stop();
    }
  };
  private int defaultImageIndex = 0;
  private final Timer defaultTimer = new Timer(1000, e -> {
    defaultImageIndex = (defaultImageIndex + 1) % defaultImages.length;
    imageLabel.setIcon(defaultImages[defaultImageIndex]);
  });
  private final Timer statusTimer = new Timer(10, e -> displayStatus());
  private final Timer moodTimer = new Timer(10, e -> moodLabel.setText(getMood()));
  private final Timer stepTimer = new Timer(10000, e -> step()); // every 10 secs
  private int interactImageIndex = 0;
  private int progressBarValue;
  private final ActionListener resetListener = e -> reset();
  private final ActionListener stepListener = e -> step();
  private final ActionListener showerListener = e -> interactEvent(bathImages);
  private final ActionListener feedListener = e -> interactEvent(feedImages);
  private final ActionListener playListener = e -> interactEvent(playImages);
  private final ActionListener sleepListener = e -> interactEvent(sleepImages);
  private PetController controller;

  public void playGame(PetController controller) {
    this.controller = controller;

    // Set the path
    setImages(defaultImages, "default");
    setImages(bathImages, "interact/bath");
    setImages(feedImages, "interact/feed");
    setImages(playImages, "interact/playing");
    setImages(sleepImages, "interact/sleep");

    // Set the view
    initFrame();
    setView();
    this.setVisible(true);
  }

  public Action getAction(ImageIcon[] images) {
    if (images == bathImages) {
      return Action.CLEAN;
    } else if (images == feedImages) {
      return Action.FEED;
    } else if (images == playImages) {
      return Action.PLAY;
    } else {
      return Action.SLEEP;
    }
  }

  private void initFrame() {
    this.setSize(600, 780);
    this.setResizable(false);
    this.getContentPane().setBackground(backgroundColor);
    this.setTitle("Pet Game!!!");
    this.setAlwaysOnTop(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void setView() {
    this.getContentPane().removeAll();

    // set layeredPane
    layeredPane.setPreferredSize(new Dimension(600, 600));
    // set imageLabel
    imageLabel.setBounds(0, 80, 600, 600);
    imageLabel.setIcon(defaultImages[0]);
    layeredPane.add(imageLabel, Integer.valueOf(0)); // bottom
    // set wantShowerLabel
    wantShowerLabel.setIcon(getFitLabel("res/images/sad/dirty-bubble.png"));
    wantShowerLabel.setBounds(0, 590, 100, 100);
    wantShowerLabel.setVisible(false);
    layeredPane.add(wantShowerLabel, Integer.valueOf(3));
    // set hungryLabel
    hungerLabel.setIcon(getFitLabel("res/images/sad/hungry-bubble.png"));
    hungerLabel.setBounds(140, 590, 100, 100);
    hungerLabel.setVisible(false);
    layeredPane.add(hungerLabel, Integer.valueOf(3));
    // set wantPlayLabel
    wantPlayLabel.setIcon(getFitLabel("res/images/sad/play-bubble.png"));
    wantPlayLabel.setBounds(270, 590, 100, 100);
    wantPlayLabel.setVisible(false);
    layeredPane.add(wantPlayLabel, Integer.valueOf(3));
    // set sleepyLabel
    sleepyLabel.setIcon(getFitLabel("res/images/sad/sleepy-bubble.png"));
    sleepyLabel.setBounds(410, 590, 100, 100);
    sleepyLabel.setVisible(false);
    layeredPane.add(sleepyLabel, Integer.valueOf(3));
    // set statusButton
    statusButton.setBounds(15, 10, 100, 30);
    statusButton.setBackground(buttonColor);
    statusButton.setForeground(backgroundColor);
    statusButton.setFont(buttonFont);
    statusButton.setBorderPainted(false);
    layeredPane.add(statusButton, Integer.valueOf(1));
    // set moodLabel
    moodLabel.setBounds(15, 50, 100, 30);
    moodLabel.setText("MOOD");
    setInfoLabel(moodLabel);
    layeredPane.add(moodLabel, Integer.valueOf(2));
    // set characterLabel
    characterLabel.setBounds(130, 50, 120, 30);
    characterLabel.setText("CHARACTER");
    setInfoLabel(characterLabel);
    layeredPane.add(characterLabel, Integer.valueOf(3));
    // set restartButton
    restartButton.setBounds(470, 10, 100, 30);
    restartButton.setBackground(buttonColor);
    restartButton.setForeground(backgroundColor);
    restartButton.setFont(buttonFont);
    restartButton.setBorderPainted(false);
    layeredPane.add(restartButton, Integer.valueOf(1));
    // set stepButton
    stepButton.setBounds(470, 50, 100, 30);
    stepButton.setBackground(buttonColor);
    stepButton.setForeground(backgroundColor);
    stepButton.setFont(buttonFont);
    stepButton.setBorderPainted(false);
    layeredPane.add(stepButton, Integer.valueOf(1));
    // set statusInfo
    statusInfo.setBounds(30, 10, 530, 30);
    statusInfo.setHorizontalAlignment(SwingConstants.CENTER);
    statusInfo.setVerticalAlignment(SwingConstants.CENTER);
    statusInfo.setBackground(backgroundColor);
    statusInfo.setBorder(BorderFactory.createLineBorder(buttonColor, 3));
    statusInfo.setForeground(buttonColor);
    statusInfo.setFont(new Font("Serif", Font.BOLD, 14));
    statusInfo.setOpaque(true);
    statusInfo.setVisible(false);
    layeredPane.add(statusInfo, Integer.valueOf(1));

    // set progressBar
    progressBar.setMinimum(0);
    progressBar.setMaximum(100);
    progressBar.setStringPainted(true);
    progressBar.setBounds(100, 650, 400, 15);
    progressBar.setForeground(progressColor);
    progressBar.setBackground(barColor);
    progressBar.setBorderPainted(false);
    progressBar.setVisible(false);
    layeredPane.add(progressBar, Integer.valueOf(2)); // top
    // set gameStart button
    setGameStartButton();

    // add layeredPane
    this.getContentPane().add(layeredPane);

    // set buttonPane
    showerButton.setBackground(buttonColor);
    feedButton.setBackground(buttonColor);
    playButton.setBackground(buttonColor);
    sleepButton.setBackground(buttonColor);
    showerButton.setForeground(backgroundColor);
    feedButton.setForeground(backgroundColor);
    playButton.setForeground(backgroundColor);
    sleepButton.setForeground(backgroundColor);
    showerButton.setFont(buttonFont);
    feedButton.setFont(buttonFont);
    playButton.setFont(buttonFont);
    sleepButton.setFont(buttonFont);
    showerButton.setBorderPainted(false);
    feedButton.setBorderPainted(false);
    playButton.setBorderPainted(false);
    sleepButton.setBorderPainted(false);

    // add buttons
    buttonPane.add(showerButton);
    buttonPane.add(feedButton);
    buttonPane.add(playButton);
    buttonPane.add(sleepButton);

    buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 40, 15, 40));
    this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

    buttonPane.setOpaque(false);
  }

  private void setInfoLabel(JLabel moodLabel) {
    moodLabel.setHorizontalAlignment(SwingConstants.CENTER);
    moodLabel.setVerticalAlignment(SwingConstants.CENTER);
    moodLabel.setBackground(backgroundColor);
    moodLabel.setBorder(BorderFactory.createLineBorder(buttonColor, 3));
    moodLabel.setForeground(buttonColor);
    moodLabel.setFont(buttonFont);
    moodLabel.setOpaque(true);
  }

  private void gameStart() {
    gameStart.setVisible(false);
    controller.startGame();
    characterLabel.setText(controller.getPersonality().getName());
    statusButton.addMouseListener(showAdapter);
    restartButton.addActionListener(resetListener);
    showerButton.addActionListener(showerListener);
    feedButton.addActionListener(feedListener);
    playButton.addActionListener(playListener);
    sleepButton.addActionListener(sleepListener);
    stepButton.addActionListener(stepListener);
    setAllButtonsCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    defaultImageAnimation();
    moodTimer.start();
    stepTimer.restart();
  }

  private void setGameStartButton() {
    ImageIcon rowIcon = new ImageIcon("res/images/start.png");
    Image scaledImage = rowIcon.getImage().getScaledInstance(
        400, 150, Image.SCALE_SMOOTH);
    gameStart.setBounds(100, 250, 400, 150);
    ImageIcon image = new ImageIcon(scaledImage);
    gameStart.setIcon(image);
    gameStart.setCursor(new Cursor(Cursor.HAND_CURSOR));
    gameStart.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        gameStart();
      }
    });
    layeredPane.add(gameStart, Integer.valueOf(3));
  }

  private void setImages(ImageIcon[] images, String path) {
    for (int i = 0; i < images.length; i++) {
      ImageIcon rawIcon = new ImageIcon("res/images/" + path + "/" + i + ".png");
      Image scaledImage = rawIcon.getImage().getScaledInstance(
          600, 600, Image.SCALE_SMOOTH);
      images[i] = new ImageIcon(scaledImage);
    }
  }

  private void defaultImageAnimation() {
    imageLabel.setIcon(defaultImages[0]);

    defaultImageIndex = 0;
    // Every one sec, delay 1000 =  1 sec
    defaultTimer.start();
  }

  private void interactEvent(ImageIcon[] images) {
    if (images == sleepImages) {
      this.getContentPane().setBackground(nightSkyBlue);
    }
    setSadLabelDisabled();
    defaultTimer.stop();
    stepTimer.stop();
    interactImageIndex = 0;
    imageLabel.setIcon(images[0]);
    final int[] tickCount = {0};

    Timer interactTimer = new Timer(500, e -> {
      tickCount[0]++;
      interactImageIndex = (interactImageIndex + 1) % images.length;
      imageLabel.setIcon(images[interactImageIndex]);

      if (tickCount[0] >= 10) {
        ((Timer) e.getSource()).stop();
      }
    });
    displayProgressBar(images);
    interactTimer.start();
  }

  private void displayProgressBar(ImageIcon[] images) {
    progressBarValue = 0;
    progressBar.setVisible(true);
    progressBar.setValue(progressBarValue);
    startHungerTimer(images);
  }

  private void startHungerTimer(ImageIcon[] images) {
    setAllButtonsEnabled(false);
    // Total load time = 50*100 / 1000 = 5 sec
    Timer hungerTimer = new Timer(50, e -> {
      if (progressBarValue < 100) {
        progressBarValue += 1; // add 1 for each 0.05 s
        progressBar.setValue(progressBarValue);
      } else {
        controller.interactPet(images);
        ((Timer) e.getSource()).stop(); // stop the timer
        progressBar.setVisible(false); // make invisible when finished
        imageLabel.setIcon(defaultImages[0]);
        this.getContentPane().setBackground(backgroundColor);
        defaultTimer.start();
        stepTimer.start();
        afterInteract();
      }
    });
    hungerTimer.start();
  }

  private void setAllButtonsEnabled(boolean enabled) {
    showerButton.setEnabled(enabled);
    feedButton.setEnabled(enabled);
    playButton.setEnabled(enabled);
    sleepButton.setEnabled(enabled);
    restartButton.setEnabled(enabled);
    stepButton.setEnabled(enabled);
  }

  private void setAllButtonsCursor(Cursor cursor) {
    statusButton.setCursor(cursor);
    restartButton.setCursor(cursor);
    showerButton.setCursor(cursor);
    feedButton.setCursor(cursor);
    playButton.setCursor(cursor);
    sleepButton.setCursor(cursor);
    stepButton.setCursor(cursor);
  }

  private void reset() {
    gameStart.setVisible(true);
    if (usingShowAdapter) {
      statusButton.removeMouseListener(showAdapter);
    } else {
      statusButton.removeMouseListener(hideAdapter);
    }
    characterLabel.setText("CHARACTER");
    statusButton.setText("SHOW");
    statusInfo.setVisible(false);
    restartButton.removeActionListener(resetListener);
    showerButton.removeActionListener(showerListener);
    feedButton.removeActionListener(feedListener);
    playButton.removeActionListener(playListener);
    sleepButton.removeActionListener(sleepListener);
    stepButton.removeActionListener(stepListener);
    setAllButtonsCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    defaultTimer.stop();
    imageLabel.setIcon(defaultImages[0]);
    moodTimer.stop();
    moodLabel.setText("MOOD");
  }

  private void displayStatus() {
    HealthStatus health = controller.getHealth();
    String healthText = "HYGIENE: %d  HUNGER: %d  SOCIAL: %d  SLEEP: %d".formatted(
        health.getHygiene(), health.getHunger(), health.getSocial(), health.getSleep());
    statusInfo.setText(healthText);
  }

  private ImageIcon getFitLabel(String path) {
    ImageIcon rawIcon = new ImageIcon(path);
    Image scaledImage = rawIcon.getImage().getScaledInstance(
        100, 100, Image.SCALE_SMOOTH);
    return new ImageIcon(scaledImage);
  }

  private void afterInteract() {
    if (!controller.isAlive()) {
      gameOverModeSetting();
    } else {
      if (controller.getMood() == MoodEnum.HAPPY) {
        happyModeSetting();
      } else {
        sadModeSetting();
      }
    }
  }

  private void gameOverModeSetting() {
    setAllButtonsEnabled(true);
    ImageIcon rawIcon = new ImageIcon("res/images/gameOver.png");
    Image scaledImage = rawIcon.getImage().getScaledInstance(
        600, 600, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(scaledImage);
    defaultTimer.stop();
    stepTimer.stop();
    imageLabel.setIcon(image);
    showerButton.removeActionListener(showerListener);
    feedButton.removeActionListener(feedListener);
    playButton.removeActionListener(playListener);
    sleepButton.removeActionListener(sleepListener);
    restartButton.setEnabled(true);
  }

  private void happyModeSetting() {
    setAllButtonsEnabled(true);
  }

  private void sadModeSetting() {
    setAllButtonsEnabled(true);
    if (controller.needShower()) {
      wantShowerLabel.setVisible(true);
    }
    if (controller.needFeed()) {
      hungerLabel.setVisible(true);
    }
    if (controller.needPlay()) {
      wantPlayLabel.setVisible(true);
    }
    if (controller.needSleep()) {
      sleepyLabel.setVisible(true);
    }
  }

  private void step() {
    setSadLabelDisabled();
    setAllButtonsEnabled(false);
    controller.step();
    afterInteract();
  }

  private void setSadLabelDisabled() {
    wantShowerLabel.setVisible(false);
    hungerLabel.setVisible(false);
    wantPlayLabel.setVisible(false);
    sleepyLabel.setVisible(false);
  }

  private String getMood() {
    if (controller.getMood() == MoodEnum.HAPPY) {
      return "HAPPY";
    } else {
      return "SAD";
    }
  }
}
