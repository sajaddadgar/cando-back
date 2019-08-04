package com.rahnema.service;

import com.rahnema.model.Auction;
import com.rahnema.model.User;
import com.rahnema.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserService userService;


    public List<Auction> getAllAuction(){
        return (List<Auction>) auctionRepository.findAll();
    }

    public Optional<Auction> getOneAuction(long id){
        return auctionRepository.findById(id);
    }

    public void addAuction(Auction auction, long id){
        Optional<User> user =userService.getOneUser(id);
//        auction.setDate(new Date());

        user.get().getAuctions().add(auction);
        userService.addUser(user.get());
    }

}
