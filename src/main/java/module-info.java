module com.example.honorsprojectdrawingprogram {
  requires javafx.controls;
  requires javafx.fxml;


  opens com.example.honorsprojectdrawingprogram to javafx.fxml;
  exports com.example.honorsprojectdrawingprogram;
}