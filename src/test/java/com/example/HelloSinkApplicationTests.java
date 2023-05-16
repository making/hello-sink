package com.example;

import com.example.HelloSinkApplication.Tweet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Import(TestChannelBinderConfiguration.class)
@ExtendWith(OutputCaptureExtension.class)
class HelloSinkApplicationTests {
	@Autowired
	InputDestination destination;

	@Test
	void contextLoads(CapturedOutput capture) {
		final Tweet tweet = new Tweet("Hello");
		final Message<Tweet> message = MessageBuilder.withPayload(tweet).build();
		this.destination.send(message, "hello");
		assertThat(capture.toString()).contains("Received Hello" + System.lineSeparator());
	}

}