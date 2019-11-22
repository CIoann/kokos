package kokos;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;



public class SimpleProducer {

	@Value("${kafka.bootstrap.servers}")
	private String kafkaBootstrapServers;
	@Value("${kafka.topic.thetechcheck}")
	public String thetechchechTopicName;
	Properties props;
	KafkaProducer<String,String> producer;
	public SimpleProducer() {
		props = new Properties();
		props.put("bootstrap.servers", "172.25.0.3:29092");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16348);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
	}
	//comm
	

	public void sendMessage(String payload, KafkaProducer<String,String> producer,String topic){
		
		System.out.println("sending to kafka " + payload);
		producer.send(new ProducerRecord<String, String>(topic, payload));
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("sTART");
		
		SimpleProducer p = new SimpleProducer();
		for (int i=0; i<10; i++){
			p.sendMessage("msg", p.producer, "thetechcheck");
		}
	
	}
}
