package com.example.cs2336asg6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.stage.Stage;
//extra essential imports
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import java.io.IOException;
import javafx.beans.binding.Bindings;


public class CS2336Asg6_AAG190007 extends Application
{
    //variables which will hold the 2 values you want to perform computation on and the answer
    double val1, val2, ans = 0;

    //create label and text field for the first and second values to be entered and for the answer and error to be displayed
    Label value1 = new Label("1st Number: ");
    TextField tvalue1 = new TextField();
    Label value2 = new Label("2nd Number: ");
    TextField tvalue2 = new TextField();
    Label answer = new Label("Answer: ");
    Label error = new Label("Error: ");

    @Override
    public void start(Stage stage) throws IOException
    {
        //grid pane (numPane), center position, spacing of 10
        GridPane numPane = new GridPane();
        numPane.setAlignment(Pos.CENTER);

        //number pane to add all elements into
        numPane.getChildren().addAll(value1, value2, answer, tvalue1, tvalue2, error);

        //create 4 basic operations buttons (add, subtract, multiply, divide)
        Button AddButton = new Button("Add");
        Button SubtractButton = new Button("Subtract");
        Button MultiplyButton = new Button("Multiply");
        Button DivideButton = new Button("Divide");
        Button clear = new Button("Clear");
        //set the grid pane
        GridPane.setConstraints(value1, 1,0);
        GridPane.setConstraints(value2, 2,0);
        GridPane.setConstraints(tvalue1, 1,5);
        GridPane.setConstraints(tvalue2, 2,5);
        GridPane.setConstraints(answer, 1,15);
        GridPane.setConstraints(AddButton, 2,5);
        GridPane.setConstraints(SubtractButton, 3,5);
        GridPane.setConstraints(MultiplyButton, 4,5);
        GridPane.setConstraints(DivideButton, 5,5);
        GridPane.setConstraints(clear, 6, 5);
        GridPane.setConstraints(error, 1, 20);
        //try catch block for error handling (will let user know to not enter unwanted values)
        try
        {
            if (!(tvalue1.getText().matches("\\d")) || (!(tvalue2.getText().matches("\\d"))))
            {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e)
        {
            error.textProperty().bind(Bindings.createStringBinding(() -> "Please input numerical values only"));
        }
        //add
        AddButton.setOnAction(e -> { ans = Double.parseDouble(tvalue1.getText()) + Double.parseDouble(tvalue2.getText()); answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: " + ans));});
        //subtract
        SubtractButton.setOnAction(e -> { ans = Double.parseDouble(tvalue1.getText()) - Double.parseDouble(tvalue2.getText()); answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: " + ans));});
        //multiply
        MultiplyButton.setOnAction(e -> { ans = Double.parseDouble(tvalue1.getText()) * Double.parseDouble(tvalue2.getText()); answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: " + ans));});
        //divide
        //check for error if dividing by 0 occurs
            DivideButton.setOnAction(e -> {
            {
                if (tvalue2.getText().matches("0"))
                {
                    //error.textProperty().bind(Bindings.createStringBinding(() -> "Error, cannot divide by 0"));
                    answer.setText("Error, cannot divide by 0");
                }

            }});

        DivideButton.setOnAction(e -> { ans = Double.parseDouble(tvalue1.getText()) / Double.parseDouble(tvalue2.getText()); answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: " + ans));});
        //button to clear text fields
        clear.setOnAction(event -> {tvalue1.clear();tvalue2.clear();answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: 0" ));});

        //pane for the user (GUI user will see), contains all buttons on it
        GridPane userPane = new GridPane();
        userPane.setAlignment(Pos.CENTER);
        userPane.getChildren().addAll(AddButton, SubtractButton, MultiplyButton, DivideButton, clear);

        //border pane for the overall border of the calculator
        BorderPane borderPane = new BorderPane(numPane);
        BorderPane.setMargin(userPane, new Insets(20, 20, 20, 20));
        borderPane.setBottom(userPane);
        BorderPane.setMargin(userPane, new Insets(20, 20, 20, 20));
        //set the stage
        stage.setScene(new Scene(borderPane, borderPane.getPrefWidth(), borderPane.getPrefWidth()));
        stage.setTitle("Basic Calculator");
        stage.show();
    }
    //main method, not really required unless needed in JavaFX
    public static void main(String[] args)
    {
        Application.launch(args);
    }
}

/* 
Notes:
DivideButton.setOnAction(e -> {try
            {
                double var = Double.parseDouble(tvalue1.getText())/Double.parseDouble(tvalue2.getText());
                if ((var == Double.POSITIVE_INFINITY || var == Double.NEGATIVE_INFINITY))
                {
                    throw new ArithmeticException();
                }
                else
                    answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: " + var));
            }
            catch (ArithmeticException a)
            {
                error.textProperty().bind(Bindings.createStringBinding(() -> "Error, cannot divide by 0"));
                answer.textProperty().bind(Bindings.createStringBinding(() -> "Answer: "));
            }});
 */
