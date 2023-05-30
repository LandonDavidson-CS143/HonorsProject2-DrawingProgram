package com.example.honorsprojectdrawingprogram;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class DrawingProgram extends Application {
  private static final BorderPane root = new BorderPane();
  private static final DrawingBoard canvas = new DrawingBoard();

  private static ArrayList<Button> createButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    Button clear = new Button("_Clear");
    clear.setTooltip(new Tooltip("Clear canvas (Hotkey: Alt+C)"));
    clear.setOnAction(event -> canvas.clear());
    buttons.add(clear);

    Button brushColor = new Button("_Brush Color");
    brushColor.setTooltip(new Tooltip("Change Brush Color (Hotkey: Alt+B)"));
    brushColor.setOnAction(event -> canvas.showBrushPicker());
    buttons.add(brushColor);

    Button addBox = new Button("_Square");
    addBox.setTooltip(new Tooltip("Create a square, cancel with ESC (Hotkey: Alt+S)"));
    addBox.setOnAction(event -> canvas.addBox());
    buttons.add(addBox);

    Button addCircle = new Button("C_ircle");
    addCircle.setTooltip(new Tooltip("Add a circle, cancel with ESC (Hotkey: Alt+I)"));
    addCircle.setOnAction(event -> canvas.addCircle());
    buttons.add(addCircle);

    Button delete = new Button("_Delete");
    delete.setTooltip(new Tooltip("Delete a shape by clicking it, exit with ESC (Hotkey: Alt+D)"));
    delete.setOnAction(event -> canvas.delete());
    buttons.add(delete);

    Button customShape = new Button("C_ustom Shape");
    customShape.setTooltip(new Tooltip("Create Custom Shape, exit with ESC (Hotkey: Alt+U)"));
    customShape.setOnAction(event -> canvas.addCustomShape());
    buttons.add(customShape);

    Button save = new Button("B_ackground");
    save.setTooltip(new Tooltip("Save your drawing (Hotkey: Alt+A)"));
    save.setOnAction(event -> canvas.showBackgroundPicker());
    buttons.add(save);

    return buttons;
  }
  @Override
  public void start(Stage stage) {
    root.setCenter(canvas.getRoot());

    ToolBar toolbar = new ToolBar();
    toolbar.getItems().addAll(createButtons());
    toolbar.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
    root.setBottom(toolbar);

    Scene scene = new Scene(root, 600, 400);
    stage.setTitle("Drawing!");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}