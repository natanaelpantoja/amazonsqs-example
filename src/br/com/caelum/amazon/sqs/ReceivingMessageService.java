package br.com.caelum.amazon.sqs;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class ReceivingMessageService {
	public static void main(String[] args) {
		System.out.println("Recebendo msg da Fila do Pantoja.\n");
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest("https://sqs.us-east-1.amazonaws.com/403828569551/caelum-queue-jms");
		AWSCredentials awsCredentials = new AWSCredentials() {
			@Override
			public String getAWSSecretKey() {
				return "SUA SECRET KEY";
			}
			@Override
			public String getAWSAccessKeyId() {
				return "SUA ACCESS KEY";
			}
		};
		AmazonSQSClient sqsClient = new AmazonSQSClient(awsCredentials);
		List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
		for (Message message : messages) {
		    System.out.println("  MSG");
		    System.out.println("    MsgID:     " + message.getMessageId());
		    System.out.println("    Identificador Único MSG: " + message.getReceiptHandle());
		    System.out.println("    MD5 do Corpo da MSG:     " + message.getMD5OfBody());
		    System.out.println("    Corpo:          " + message.getBody());
		    for (Entry<String, String> entry : message.getAttributes().entrySet()) {
		        System.out.println("  Atributos");
		        System.out.println("    Nome:  " + entry.getKey());
		        System.out.println("    Valor: " + entry.getValue());
		    }
		}
	}
}
