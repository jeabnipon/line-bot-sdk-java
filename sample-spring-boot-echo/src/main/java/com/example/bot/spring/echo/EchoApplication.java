/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.linecorp.bot.client.LineMessagingClient;
//import com.linecorp.bot.client.LineMessagingService;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.response.BotApiResponse;

import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;



@SpringBootApplication
@LineMessageHandler

public class EchoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    @Autowired
    private LineMessagingClient lineMessagingClient;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        //return new TextMessage(event.getMessage().getText());
	//String userId = event.getSource().getUserId();

	//String text = String text = message.getText();.getText();
	//return new TextMessage(event.getSource().getUserId());

	//TextMessageContent message = event.getMessage();
	
        //String replyToken = event.getReplyToken();

	//return new TextMessage(userId);

	String text = event.getMessage().getText();
	switch(text){
	  case "profile" :{
	    System.out.println("event: " + event);
	    break;
	  }
	  case "userid"  :{
            return new TextMessage(event.getSource().getUserId());
	    break;
	  }
	  default:
	    System.out.println("event: " + event);
            break;
	}
				
    }

/*    
    @EventMapping
    public void handleTextMessageEvent(MessageEvent<TextMessageContent> event) throws Exception {
        TextMessageContent message = event.getMessage();
//        handleTextContent(event.getReplyToken(), event, message);
//handleTextContent(String replyToken, Event event, TextMessageContent content)
	
        String replyToken = event.getReplyToken();
	String text = message.getText();
	String userId = event.getSource().getUserId();

	return new TextMessage(userId);

	//this.replyText(replyToken, userId);
        //return;

                if (userId != null) {
                    lineMessagingClient
                            .getProfile(userId)
                            .whenComplete((profile, throwable) -> {
                                if (throwable != null) {
                                    this.replyText(replyToken, throwable.getMessage());
                                    return;
                                }

                                this.reply(
                                        replyToken,
                                        Arrays.asList(new TextMessage(
                                                              "Display name: " + profile.getDisplayName()),
                                                      new TextMessage("Status message: "
                                                                      + profile.getStatusMessage()))
                                );

                            });
                } else {
                    this.replyText(replyToken, "Bot can't use profile API without user ID");
                }

    }


    private void reply(String replyToken, Message message) {
        reply(replyToken, Collections.singletonList(message));
    }

    private void reply(String replyToken, List<Message> messages) {
	try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages))
                    .get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            BotApiResponse apiResponse = lineMessagingClient
                    .replyMessage(new ReplyMessage(replyToken, messages))
                    .get();
            //log.info("Sent messages: {}", apiResponse);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    private void replyText(String replyToken,String message) {
        if (replyToken.isEmpty()) {
            throw new IllegalArgumentException("replyToken must not be empty");
        }
        if (message.length() > 1000) {
            message = message.substring(0, 1000 - 2) + "....";
        }
        this.reply(replyToken, new TextMessage(message));
    }

	
    @Autowired
    private LineMessagingService lineMessagingService;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        System.out.println("event: " + event);
        //return new TextMessage(event.getMessage().getText());
	//return new TextMessage("test");

	final BotApiResponse apiResponse = lineMessagingService
						.replyMessage(new ReplyMessage(event.getReplyToken(), 
				       Collections.singletonList(new TextMessage(event.getSource().getUserId()))));
		apiResponse.execute().body(); 				
    }
*/


    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        System.out.println("event: " + event);
    }
}
