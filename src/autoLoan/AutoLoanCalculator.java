package autoLoan;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.Scene;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.control.TextField;
//import javafx.scene.control.ToggleGroup;
//import javafx.scene.control.Label;
//import javafx.scene.control.RadioButton;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.CheckBox;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.*;

public class AutoLoanCalculator extends Application 
{
	public static void main(String[] args) 
	{
		launch(args);
	}

	private TextField txtBasePrice = new TextField();
	private TextField txtDownPayment = new TextField();
	private TextField txtSalesTax = new TextField();
	private RadioButton rbt24 = new RadioButton("24 Months");
	private RadioButton rbt36 = new RadioButton("36 Months");
	private RadioButton rbt48 = new RadioButton("48 Months");
	private RadioButton rbt60 = new RadioButton("60 Months");
	private ToggleGroup loanTermGroup = new ToggleGroup();
	private CheckBox cbAutoTransmission = new CheckBox("Auto Transmission: $1800");
	private CheckBox cbAntilockBrake = new CheckBox("Antilock Brake: $1200");
	private CheckBox cbSunRoof = new CheckBox("Sun Roof: $800");
	private CheckBox cbNavigationSystem = new CheckBox("Navigation System: $1350");
	private CheckBox cbAudioPackage = new CheckBox("Audio Package: $1550");
	private Button btnCalculate = new Button("Calculate");
	private Button btnReset = new Button("Reset");
	private Button btnExit = new Button("Exit");
	private Label lblTotalLoanAmount = new Label("0.0");
	private Label lblMonthlyPayment = new Label("0.0");
	private Label lblTotalPayment = new Label("0.0");
	private Label lblPaymentInfo = new Label("Payment Information");
	private Label lblLoanTerm = new Label("Loan Term");
	private Label lblFinancingInfo = new Label("Financing Information");
	private Label lblOptions = new Label("Price with Options");
	
	
	@Override
	public void start(Stage primaryStage) 
	{
		//set up default radio buttons
		rbt24.setToggleGroup(loanTermGroup);
		rbt24.setUserData(24);
		rbt36.setToggleGroup(loanTermGroup);
		rbt36.setUserData(36);
		rbt48.setToggleGroup(loanTermGroup);
		rbt48.setUserData(48);
		rbt60.setToggleGroup(loanTermGroup);
		rbt60.setUserData(60);
		rbt24.setSelected(true);
		
		//set up default check boxes
		cbAntilockBrake.setSelected(true);
		
		//set up default text fields
		txtBasePrice.setText("0.0");
		txtDownPayment.setText("0.0");
		txtSalesTax.setText("7.0");
		
		//set up button handlers
		btnCalculate.setOnAction(new BtnCalculateHandler());
		btnReset.setOnAction(new BtnResetHandler());
		btnExit.setOnAction(new BtnExitHandler());
		
		//set up loan term panel
		GridPane loanTermPane = new GridPane();
		lblLoanTerm.setStyle("-fx-font-weight:bold");
		loanTermPane.setStyle("-fx-border-color:black");
		loanTermPane.setHgap(5);
		loanTermPane.setVgap(5);
		loanTermPane.setPadding(new Insets(5, 5, 5, 5));
		loanTermPane.add(lblLoanTerm, 0, 0);
		loanTermPane.add(rbt24, 0, 1);
		loanTermPane.add(rbt36, 0, 2);
		loanTermPane.add(rbt48, 0, 3);
		loanTermPane.add(rbt60, 0, 4);
		loanTermPane.setAlignment(Pos.TOP_LEFT);
		
		//set up financing information panel
		GridPane financingInfoPane = new GridPane();
		lblFinancingInfo.setStyle("-fx-font-weight:bold");
		financingInfoPane.setStyle("-fx-border-color:black");
		financingInfoPane.setHgap(5);
		financingInfoPane.setVgap(5);
		financingInfoPane.setPadding(new Insets(5, 5, 5, 5));
		financingInfoPane.add(lblFinancingInfo, 0, 0);
		financingInfoPane.add(new Label("Base Price: $"), 0, 1);
		financingInfoPane.add(txtBasePrice, 1, 1);
		financingInfoPane.add(new Label("Down Payment: $"), 0, 2);
		financingInfoPane.add(txtDownPayment, 1, 2);
		financingInfoPane.add(new Label("Sales Tax: $"), 0, 3);
		financingInfoPane.add(txtSalesTax, 1, 3);
		financingInfoPane.setAlignment(Pos.TOP_LEFT);
		
		//set up options panel
		GridPane optionsPane = new GridPane();
		lblOptions.setStyle("-fx-font-weight:bold");
		optionsPane.setStyle("-fx-border-color:black");
		optionsPane.setHgap(5);
		optionsPane.setVgap(5);
		optionsPane.setPadding(new Insets(5, 5, 5, 5));
		optionsPane.add(lblOptions, 0, 0);
		optionsPane.add(cbAutoTransmission, 0, 1);
		optionsPane.add(cbAntilockBrake, 0, 2);
		optionsPane.add(cbSunRoof, 0, 3);
		optionsPane.add(cbNavigationSystem, 0, 4);
		optionsPane.add(cbAudioPackage, 0, 5);
		optionsPane.setAlignment(Pos.TOP_LEFT);
		
		//set up payment information panel
		GridPane paymentInfoPane = new GridPane();
		lblPaymentInfo.setStyle("-fx-font-weight:bold");
		paymentInfoPane.setStyle("-fx-border-color:black");
		paymentInfoPane.setHgap(5);
		paymentInfoPane.setVgap(5);
		paymentInfoPane.setPadding(new Insets(5, 5, 5, 5));
		paymentInfoPane.add(lblPaymentInfo, 0, 0);
		paymentInfoPane.add(new Label("Total Loan Amount: $"), 0, 1);
		paymentInfoPane.add(lblTotalLoanAmount, 1, 1);
		paymentInfoPane.add(new Label("Monthly Payment: $"), 0, 2);
		paymentInfoPane.add(lblMonthlyPayment, 1, 2);
		paymentInfoPane.add(new Label("Total Payment: $"), 0, 3);
		paymentInfoPane.add(lblTotalPayment, 1, 3);
		paymentInfoPane.setAlignment(Pos.TOP_LEFT);
		
		//set up button panel
		HBox buttonPane = new HBox();
		btnCalculate.setStyle("-fx-font-weight:bold");
		btnReset.setStyle("-fx-font-weight:bold");
		btnExit.setStyle("-fx-font-weight:bold");
		btnCalculate.setPrefWidth(80);
		btnReset.setPrefWidth(80);
		btnExit.setPrefWidth(80);
		buttonPane.setStyle("-fx-border-color:black");
		buttonPane.setPadding(new Insets(5, 5, 5, 5));
		buttonPane.setSpacing(20);
		buttonPane.getChildren().add(btnCalculate);
		buttonPane.getChildren().add(btnReset);
		buttonPane.getChildren().add(btnExit);
		buttonPane.setAlignment(Pos.CENTER);

		
		//set up main panel
		GridPane mainPane = new GridPane();
		mainPane.setStyle("-fx-border-color:black");
		mainPane.add(paymentInfoPane, 0, 0);
		mainPane.add(loanTermPane, 1, 0);
		mainPane.add(financingInfoPane, 0, 1);
		mainPane.add(optionsPane, 1, 1);
		mainPane.add(buttonPane, 0, 2, 4, 1);
		mainPane.setAlignment(Pos.CENTER);
		
		
		//set up scene and stage
		Scene scene = new Scene(mainPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Auto Loan Calculator");
		primaryStage.show();
	}
	
	class BtnCalculateHandler implements EventHandler<ActionEvent> 
	{
		@Override
		public void handle(ActionEvent event) 
		{
				double basePrice = Double.parseDouble(txtBasePrice.getText());
				double downPayment = Double.parseDouble(txtDownPayment.getText());
				double salesTaxRate = Double.parseDouble(txtSalesTax.getText());
				double salesTax = (basePrice - downPayment + getOptionsCost()) * (salesTaxRate/100);
				double totalLoanAmount = basePrice - downPayment + getOptionsCost() + salesTax;
				double annualInterestRate;
				
				if (rbt24.isSelected())
					annualInterestRate = 7.0/100;
				else if (rbt36.isSelected())
					annualInterestRate = 6.0/100;
				else if (rbt48.isSelected())
					annualInterestRate = 5.5/100;
				else
					annualInterestRate = 5.0/100;
				
				double monthlyInterest = annualInterestRate / 12;
				int months = Integer.parseInt(loanTermGroup.getSelectedToggle().getUserData().toString());
				double monthlyPayment = totalLoanAmount * (monthlyInterest * Math.pow(1 + monthlyInterest, months)) / (Math.pow(1 + monthlyInterest, months) - 1);
				double totalPayment = monthlyPayment * months + downPayment;
				lblTotalLoanAmount.setText(String.format("%.2f", totalLoanAmount));
				lblMonthlyPayment.setText(String.format("%.2f", monthlyPayment));
				lblTotalPayment.setText(String.format("%.2f", totalPayment));
		}
	}
	
	class BtnResetHandler implements EventHandler<ActionEvent> 
	{
		@Override
		public void handle(ActionEvent event) 
		{
			lblTotalLoanAmount.setText("0.0");
			lblMonthlyPayment.setText("0.0");
			lblTotalPayment.setText("0.0");
			rbt24.setSelected(true);
			txtBasePrice.setText("0.0");
			txtDownPayment.setText("0.0");
			txtSalesTax.setText("7.0");
			cbAntilockBrake.setSelected(true);
			cbAutoTransmission.setSelected(false);
			cbSunRoof.setSelected(false);
			cbNavigationSystem.setSelected(false);
			cbAudioPackage.setSelected(false);
		}
	}
	
	class BtnExitHandler implements EventHandler<ActionEvent> 
	{
		@Override
		public void handle(ActionEvent event) 
		{
			System.exit(0);
		}
	}
	
	private double getOptionsCost() 
	{
		double optionsCost = 0;
		if(cbAutoTransmission.isSelected())
			optionsCost += 1800;
		if(cbAntilockBrake.isSelected())
			optionsCost += 1200;
		if(cbSunRoof.isSelected())
			optionsCost += 800;
		if(cbNavigationSystem.isSelected())
			optionsCost += 1350;
		if(cbAudioPackage.isSelected())
			optionsCost += 1550;
		return optionsCost;
	}
}