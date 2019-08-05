package com.rahnema.service;

import com.rahnema.domain.AuctionDomain;
import com.rahnema.model.Auction;
import com.rahnema.model.User;
import com.rahnema.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserService userService;


    public Optional<Auction> getAuction(long id){
        return auctionRepository.findById(id);
    }


    public Auction addAuction(AuctionDomain auctionDomain, long id) {
        Auction auction = new Auction(auctionDomain);
        System.out.println(auction);
        System.out.println(auctionDomain);
        Optional<User> user =userService.getOneUser(id);
        auctionRepository.save(auction);
        user.get().getCreatedAuction().add(auction);
        userService.addUser(user.get());
        return auction;
    }

    public void settWinner(long auction_id, long user_id){
        Auction auction = auctionRepository.findById(auction_id).get();
        User user = userService.getOneUser(user_id).get();
        auction.setWinner(user);
        userService.addUser(user);
    }

}
