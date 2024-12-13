package todolist;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class GTodoList extends Application{
    @Override
	public  void start(Stage primaryStage)
	{
    	Label n=new Label("Name: ");
    	TextField name=new TextField("Name");
    	Label p=new Label("Password: ");
    	TextField password=new TextField("Password");
		Button btn=new Button("Submit");
		Button signIn=new Button("SignIn");
		GridPane root=new GridPane();
		root.addRow(0, n,name);  
		root.addRow(1, p,password); 
		root.add(btn,1,2);
		root.add(signIn,2,2);
		root.setHgap(7);
		root.setVgap(5);
		Scene obj=new Scene(root,300,300);
		primaryStage.setScene(obj);
		primaryStage.show();
		
		
		signIn.setOnAction(new EventHandler<ActionEvent>() {  
            
            @Override  
            public void handle(ActionEvent arg0) {  
                 
            	GridPane root1=new GridPane();
            	root1.addRow(0, n,name);  
        		root1.addRow(1, p,password); 
        		root1.add(btn,1,2);
        		
        		root1.setHgap(7);
        		root1.setVgap(5);
            	Scene obj1=new Scene(root1,300,300);
            	primaryStage.setScene(obj1);
            	primaryStage.show();
            }  
        } );  
		
	}
	public static void main(String[] args) 
	{
		launch(args);

	}

}
