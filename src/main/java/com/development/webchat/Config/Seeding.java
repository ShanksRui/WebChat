package com.development.webchat.Config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.StatusUser;
import com.development.webchat.model.entities.User;
import com.development.webchat.model.entities.DTO.AuthorMsg;
import com.development.webchat.model.entities.DTO.MenssageDTO;
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
		
		User u1 = new User(null, "luiz", StatusUser.ONLINE);
		User u2 = new User(null, "lisa", StatusUser.ONLINE);
		userRepo.saveAll(Arrays.asList(u1,u2));
		MenssageDTO msg = new MenssageDTO(new AuthorMsg(u1), "opa mano", Instant.parse("2003-03-06T22:10:22Z"));
		MenssageDTO msg1 = new MenssageDTO(new AuthorMsg(u2), "eai", Instant.parse("2003-03-06T23:10:22Z"));
		Chat c1 = new Chat(null);
		c1.joinUser(u1, u2);
		c1.getMenssages().addAll(Arrays.asList(msg,msg1));
		c1.setLastActivity(msg1.getMommentMsg());
		c1.setFirstChat(msg.getMommentMsg());
        
		u1.getChats().add(c1);
		u2.getChats().add(c1);
		chatRepo.save(c1);
		userRepo.saveAll(Arrays.asList(u1,u2));
		
	}

}
