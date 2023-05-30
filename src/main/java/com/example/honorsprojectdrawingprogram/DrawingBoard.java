package com.example.honorsprojectdrawingprogram;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DrawingBoard {
  private final Pane root = new Pane();
  private Color brushColor = Color.BLACK;
  private final ColorPicker brushPicker = new ColorPicker(Color.BLACK);
  private final ColorPicker backgroundPicker = new ColorPicker(Color.WHITE);
  public DrawingBoard() {
    brushPicker.setVisible(false);
    backgroundPicker.setVisible(false);

    brushPicker.setOnAction(event -> brushColor = brushPicker.getValue());
    backgroundPicker.setOnAction(event -> root.setBackground(new Background(new BackgroundFill(backgroundPicker.getValue(), CornerRadii.EMPTY, Insets.EMPTY))));

    root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    root.getChildren().addAll(brushPicker, backgroundPicker);
  }
  public void clear() {
    brushColor = Color.BLACK;
    brushPicker.setValue(Color.BLACK);

    backgroundPicker.setValue(Color.WHITE);
    root.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

    root.getChildren().clear();
    root.getChildren().addAll(brushPicker, backgroundPicker);

    root.setOnMouseClicked(null);
    root.setOnMouseMoved(null);
    root.getParent().setOnKeyPressed(null);
  }
  public void delete() {
    root.getParent().setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() != KeyCode.ESCAPE) {return;}
      root.setOnMouseClicked(null);
      root.getParent().setOnKeyPressed(null);
    });
    root.setOnMouseClicked(mouseEvent -> {
      if (!(mouseEvent.getTarget() instanceof Shape)) {return;}
      root.getChildren().remove((Shape) mouseEvent.getTarget());
    });
  }
  public void addBox() {
    Rectangle rect = new Rectangle();
    rect.setFill(brushColor);
    rect.setVisible(false);
    root.getChildren().add(rect);

    root.setOnMouseClicked(mouseEvent -> {
      rect.setX(mouseEvent.getX());
      rect.setY(mouseEvent.getY());
      rect.setVisible(true);
      root.getParent().setOnKeyPressed(keyEvent -> {
        if (keyEvent.getCode() != KeyCode.ESCAPE) {return;}
        root.getChildren().remove(rect);
        root.setOnMouseMoved(null);
        root.setOnMouseClicked(null);
        root.getParent().setOnKeyPressed(null);
      });
      root.setOnMouseMoved(mEvent -> {
        rect.setWidth(mEvent.getX() - rect.getX());
        rect.setHeight(mEvent.getY() - rect.getY());
      });
      root.setOnMouseClicked(e -> {
        root.setOnMouseMoved(null);
        root.setOnMouseClicked(null);
        root.getParent().setOnKeyPressed(null);
      });
    });
  }
  public void addCircle() {
    Circle circle = new Circle();
    circle.setFill(brushColor);
    circle.setVisible(false);
    root.getChildren().add(circle);

    root.setOnMouseClicked(mouseEvent -> {
      circle.setCenterX(mouseEvent.getX());
      circle.setCenterY(mouseEvent.getY());
      circle.setVisible(true);
      root.getParent().setOnKeyPressed( keyEvent -> {
        if (keyEvent.getCode() != KeyCode.ESCAPE) {return;}
        root.getChildren().remove(circle);
        root.setOnMouseMoved(null);
        root.setOnMouseClicked(null);
        root.getParent().setOnKeyPressed(null);
      });
      root.setOnMouseMoved(mEvent -> {
        double xDist = Math.abs(mEvent.getX() - circle.getCenterX());
        double yDist = Math.abs(mEvent.getY() - circle.getCenterY());
        circle.setRadius(Math.sqrt(xDist * xDist + yDist * yDist));
      });
      root.setOnMouseClicked(e -> {
        root.setOnMouseMoved(null);
        root.setOnMouseClicked(null);
        root.getParent().setOnKeyPressed(null);
      });
    });
  }
  public void addCustomShape() {
    final Polygon shape = new Polygon();
    shape.setFill(brushColor);
    root.getChildren().add(shape);

    root.setOnMouseClicked(mouseEvent -> shape.getPoints().addAll(mouseEvent.getX(), mouseEvent.getY()));
    root.getParent().setOnKeyPressed(keyEvent -> {
      if (keyEvent.getCode() != KeyCode.ESCAPE) {return;}
      root.setOnMouseClicked(null);
      root.getParent().setOnKeyPressed(null);
    });

  }
  public void showBackgroundPicker() {backgroundPicker.show();}
  public void showBrushPicker() {brushPicker.show();}
  public Pane getRoot() {return root;}
}
