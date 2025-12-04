package com.development.webchat.Config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.Status;
import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.AuthorMsg;
import com.development.webchat.model.entities.DTO.Message;
import com.development.webchat.repositories.ChatRepository;
import com.development.webchat.repositories.UserRepository;

@Configuration
@Profile("test")
public class Seeding implements CommandLineRunner{


   private final ChatRepository chatRepo;
   private final UserRepository userRepo;
   
	public Seeding(ChatRepository chatRepo,UserRepository userRepo) {
		this.chatRepo = chatRepo;
		this.userRepo = userRepo;
	}
	
	@Override
	public void run(String... args) throws Exception {
		userRepo.deleteAll();
		chatRepo.deleteAll();
		
		User u1 = new User(null, "luiz","2312424", Status.ONLINE);
		User u2 = new User(null, "lisa","73522335", Status.DO_NOT_DISTURB);
		userRepo.saveAll(Arrays.asList(u1,u2));
		Message msg = new Message(new AuthorMsg(u1), "opa mano", Instant.parse("2003-03-06T22:10:22Z"));
		Message msg1 = new Message(new AuthorMsg(u2), "eai", Instant.parse("2003-03-06T23:10:22Z"));
		Chat c1 = new Chat();
		c1.setUser0Id(u1.getId());
		c1.setUser1Id(u2.getId());
		c1.getMessages().addAll(Arrays.asList(msg,msg1));
		c1.setLastActivity(msg1.getMommentMsg());
		c1.setFirstChat();   
		u1.getChats().add(c1);
		u2.getChats().add(c1);
		chatRepo.save(c1);
		userRepo.saveAll(Arrays.asList(u1,u2));
		
	}

}
