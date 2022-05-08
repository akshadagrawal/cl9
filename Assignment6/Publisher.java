import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Publisher {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    
    public static void main(String args[])throws JMSException, InterruptedException{

        ConnectionFactory conFact = new ActiveMQConnectionFactory(url);
        Connection con = conFact.createConnection();
        con.start();
        
        Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Topic topic = session.createTopic("PubSubMessage");
        
        MessageProducer producer = session.createProducer(topic);
        
        TextMessage message = session.createTextMessage();
        
        int runs = 0, wickets = 0;
        for(int i = 0; i < 120; i++) {
        	Thread.sleep(5000);
        	if(wickets == 10) break;
        	int[] score= {1,2,3,4,6,-1,0};
        	int index = (int)(Math.random()*6);
        	int ball = score[index];
        	if(ball == -1)
        	{
        		wickets++;
        	}
        	else
        	{
        		runs+=ball;
        	}
        	message.setText(runs+"/"+wickets);
	        producer.send(message);
	        System.out.println("Match score: " + message.getText());
	        
        }
        con.close();  
        
    }
    
}