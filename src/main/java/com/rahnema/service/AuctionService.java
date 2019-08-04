package com.rahnema.service;

import com.rahnema.model.Auction;
import com.rahnema.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;


    public void addAuction(Auction auction){
        auctionRepository.save(auction);
    }

    public List<Auction> getAllAuction(){
        return (List<Auction>) auctionRepository.findAll();
    }

    public Optional<Auction> getOneAuction(long id){
        return auctionRepository.findById(id);
    }

}
