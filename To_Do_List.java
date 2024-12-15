package todolist;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

class Components
{
	Label notification;
	Label n;
	TextField name;
	Label p;
	TextField phNo;
	Button btn;
	Button signIn;
	 Components()
	{
		 notification=new Label();
    	 n=new Label("Name: ");
    	 name=new TextField("Name");
    	 p=new Label("Password: ");
    	 phNo=new TextField("phNo");
		 btn=new Button("Submit");
		 signIn=new Button("SignIn");
	}	
}
public class GTodoList extends Application{
    @Override
	public  void start(Stage primaryStage)
	{
    	
    	Components comp=new Components();
    	
		GridPane root=new GridPane();
		root.add(comp.notification, 6, 1);
		root.addRow(20,comp.n,comp.name);  
		root.addRow(21,comp.p,comp.phNo); 
		root.add(comp.btn,2,22);
		root.add(comp.signIn,3,22);
		root.setHgap(7);
		root.setVgap(5);
		Scene obj=new Scene(root,1530,750);
		primaryStage.setScene(obj);
		primaryStage.show();
    	    	
    	 EventHandler<ActionEvent> logn_in_event=new EventHandler<ActionEvent>()
         {
         	public void handle(ActionEvent e)
         	{
         		 String name_value=comp.name.getText();
				 String phNo_value=comp.phNo.getText();
				 if(name_value.length()!=0 && phNo_value.length()!=0)
				 {
					 if(phNo_value.length()!=10)
			            {
						 comp.notification.setText("Please enter a valid phone number with size 10 \n");
						 return;
			            }
			            for(int i=0;i<10;i++)
			            {
			                if(phNo_value.charAt(i)>='0' && phNo_value.charAt(i)<='9')
			                {
			                continue;
			                }
			                else
			                {
			                	comp.notification.setText("Digits only Accepted\n");
			                	return;
			                }
			            }
			        try
			        {
			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root","Naidu@1234");
			            Statement stmt=con.createStatement();
			            ResultSet res=stmt.executeQuery("select * from todolistlogin where phNo='"+phNo_value+"' and name='"+name_value+"'");
			            if(res.next()!=false)
			            {
			            	comp.notification.setText("successfullt login...\n");
			            	taskManager(primaryStage);
			            }
			            else
			            {
			            	comp.notification.setText("Account not exists\n");			                
			            }
			        }
			        catch(Exception exception)
			        {			        	
			        	comp.notification.setText(String.valueOf(exception));
			        }
			    }
				 else
				 {
					 comp.notification.setText("Enter Valid Details"); 
				 }
         	}
         };
         comp.btn.setOnAction(logn_in_event);         
         
         EventHandler<ActionEvent> signup_page=new EventHandler<ActionEvent>()
        {
        	 public void handle(ActionEvent e)
        	 {
        		Components sign_up=new Components();
        		GridPane sign_up_layout=new GridPane();
        		sign_up_layout.add(sign_up.notification, 6, 1);
        		sign_up_layout.addRow(20,sign_up.n,sign_up.name);  
        		sign_up_layout.addRow(21,sign_up.p,sign_up.phNo); 
        		sign_up_layout.add(sign_up.signIn,3,22);
        		sign_up_layout.setHgap(7);
        		sign_up_layout.setVgap(5);
        		Scene sign_up_scene=new Scene(sign_up_layout,500,400);
        		primaryStage.setScene(sign_up_scene);
        		primaryStage.show();
        		
        		EventHandler<ActionEvent> create_account=new EventHandler<ActionEvent>()
        		{
        			public void handle(ActionEvent e)
        			{
        				String phNo_value=sign_up.phNo.getText();
         			    String name_value=sign_up.name.getText();
         	            if(phNo_value.length()!=10)
         	            {
         	            	sign_up.notification.setText("Please enter a valid phone number with size 10\n");
         	            	return;         	              
         	            }
         	            for(int i=0;i<10;i++)
         	            {
         	                if(phNo_value.charAt(i)>='0' && phNo_value.charAt(i)<='9')
         	                {
         	                continue;
         	                }
         	                else
         	                {
         	                sign_up.notification.setText("Digits only Accepted\n");
         	                return;
         	              
         	                }         	            
         	            }
   
         	        try
         	        {
         	            Class.forName("com.mysql.cj.jdbc.Driver");
         	            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root","Naidu@1234");
         	            Statement stmt=con.createStatement();
         	            int count=stmt.executeUpdate("insert into todolistlogin(phNo,name) values('"+phNo_value+"','"+name_value+"')");
         	            if(count!=0)
         	            {
         	            sign_up.notification.setText("Account created successfully ...\n");         	            
         	            }
         	            else
         	            {
         	            	sign_up.notification.setText("Account not created successfullt\n");         	              
         	            }
         	        }
         	        catch(Exception exception)
         	        {
         	        	sign_up.notification.setText(String.valueOf(exception.getMessage()));
         	        }
        			}        			
        		};
        		sign_up.signIn.setOnAction(create_account);        		
        	 } 
        };
        comp.signIn.setOnAction(signup_page);
	}
    
    public void taskManager(Stage taskStage)
    {
    	TextField taskData=new TextField("Add Task Data..");
    	GridPane taskManager_pane=new GridPane();
    	taskManager_pane.addRow(3, taskData);
    	Scene taskManager_scene=new Scene(taskManager_pane,300,400);
    	taskStage.setScene(taskManager_scene);
    	taskStage.show();
    	
    }
	public static void main(String[] args) 
	{
		launch(args);
	}
}
