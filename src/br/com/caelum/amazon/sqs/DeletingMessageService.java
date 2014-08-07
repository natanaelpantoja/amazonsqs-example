package br.com.caelum.amazon.sqs;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class DeletingMessageService {
	
	public static void main(String[] args) {
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
		String url = "https://sqs.us-east-1.amazonaws.com/403828569551/caelum-queue-jms";
		AmazonSQSClient sqsClient = new AmazonSQSClient(awsCredentials);
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(url);
		List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();
		System.out.println("Deletando a MSG.\n" + messages.get(0).getBody());
		String messageReceiptHandle = messages.get(0).getReceiptHandle();
		sqsClient.deleteMessage(new DeleteMessageRequest().withQueueUrl(url).withReceiptHandle(messageReceiptHandle));
	}
}
