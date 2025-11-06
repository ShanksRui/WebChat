package com.development.webchat.Config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.development.webchat.model.entities.Chat;
import com.development.webchat.model.entities.StatusUser;
import com.development.webchat.model.entities.User;
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
		Chat c1 = new Chat(null,Instant.parse("2002-03-02T22:12:33Z"), Instant.parse("2002-03-02T23:12:33Z"));
		c1.setIdUser0(u1.getId());
		c1.setIdUser1(u2.getId());
		u1.getChats().add(c1);
		u2.getChats().add(c1);
		chatRepo.save(c1);
		userRepo.saveAll(Arrays.asList(u1,u2));
		
	}

}
