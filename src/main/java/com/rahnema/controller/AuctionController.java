package com.rahnema.controller;

import com.rahnema.model.Auction;
import com.rahnema.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @PostMapping("/add/{type}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String addAuction(@RequestBody Auction auction, @PathVariable String type, @PathVariable long id){
        auctionService.addAuction(auction, id);
        return "a auction added";
    }

    @GetMapping
    public List<Auction> getAllAuction(){
        return auctionService.getAllAuction();
    }

    @GetMapping("/getOne/{id}")
    public Optional<Auction> getOneAuction(long id){
        return auctionService.getOneAuction(id);
    }
}
