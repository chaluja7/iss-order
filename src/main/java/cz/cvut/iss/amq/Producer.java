/**
 *
 */
package cz.cvut.iss.amq;


import javax.jms.*;
import javax.xml.bind.JAXBException;
import java.util.Random;
import cz.cvut.iss.amq.util.XmlConverter;
import cz.cvut.iss.model.Order;
import cz.cvut.iss.model.ResolvedOrder;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author jknetl
 *
 */
public class Producer {

	private static final String DESTINATION_NAME = "expedition";
//	private static final String BROKER_URL = "tcp://localhost:49500";
	private static final String BROKER_URL = "discovery:(fabric:masterslave)";
	public static final String USERNAME = "admin";
	public static final String PASSWORD = "admin";

	private ConnectionFactory connectionFactory;
	private String destinationName;


	public Producer(ConnectionFactory connectionFactory, String destinationName) {
		super();
		this.connectionFactory = connectionFactory;
		this.destinationName = destinationName;
	}

	public Producer() {
		this.connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
		this.destinationName = DESTINATION_NAME;
	}

	/**
	 * Produces messages with. Each message contains job with random duration.
	 *
	 * @param id created Order
	 * @throws JMSException
	 */
	public void produceOrder(long id) throws JMSException, JAXBException {
		Connection connection = null;
		Session session;
		try {
			// create connection
			connection = connectionFactory.createConnection();
			// create nontransacted session with auto acknowledgement
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// get destination object based on name
			Destination destination = session.createQueue(destinationName);
			MessageProducer producer = session.createProducer(destination);

			// set persistent delivery mode
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			// start connection
			connection.start();

//			Message message = session.createTextMessage(XmlConverter.toXml(ResolvedOrder.class, order));
			Message message = session.createTextMessage("Order with id='" + id + "' sent to expedition.");
//				System.out.println("Producer: sending: " + job.toString() + " to destination " + destinationName);

			// synchronously send message
			producer.send(message);

			producer.close();
			session.close();
		} finally {

			if (connection != null) {
				connection.close();
			}
		}


	}
}
