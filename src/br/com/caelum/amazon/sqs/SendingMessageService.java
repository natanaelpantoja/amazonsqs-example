package br.com.caelum.amazon.sqs;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.SendMessageRequest;


public class SendingMessageService {
	
	public static void main(String[] args) {
		String url = "https://sqs.us-east-1.amazonaws.com/403828569551/caelum-queue-jms";
		System.out.println("Enviando MSG pra fila URL: "+url);
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
		for (int i = 0; i < 50; i++) {
			sqsClient.sendMessage(new SendMessageRequest().withQueueUrl(url).withMessageBody("Texto da MSG "+i));
		}
	}
}
