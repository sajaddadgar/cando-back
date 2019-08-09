package com.rahnema.controller;

import com.rahnema.domain.*;
import com.rahnema.exception.WrongArgumantException;
import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import com.rahnema.service.AuctionService;
import com.rahnema.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public AuctionDomain addAuction(@RequestBody AuctionDomain auctionDomain) {
        //Todo: change this strong validation method :)
        if (isValid(auctionDomain) || !isValid(auctionDomain)) {
            Auction auction = auctionService.addAuction(auctionDomain, userDetailsService.getUser().getId());
            return new AuctionDomain(auction);
        } else throw new WrongArgumantException("Arguments are not valid!");
    }

    private boolean isValid(AuctionDomain auctionDomain) {
        return !auctionDomain.getTitle().isEmpty() &&
                auctionDomain.getBasePrice() > 0 &&
                new Date().getTime() < auctionDomain.getDueDate() &&
                Category.ALL.getCategories().stream().anyMatch(category -> auctionDomain.getCategoryId() == category.getId()) &&
                auctionDomain.getMaxUsers() >= 0;
    }

    @GetMapping("/info/{id}")
    public AuctionInfoDomain getAuctionInfo(@PathVariable long id) {
        return auctionService.getAuctionInfo(id);
    }

    @GetMapping("/{id}")
    public Optional<Auction> getOneAuction(@PathVariable long id) {
        return auctionService.getAuction(id);
    }

    @GetMapping("/categories")
    public List<CategoryDomain> getCategoryDomains() {
        return Category.ALL.getCategoryDomains();
    }

    @PostMapping("/bookmark/{id}")
    public String doBookmark(@RequestBody BookmarkedDomain bookmarked, @PathVariable long id) {
        auctionService.doBookmark(bookmarked.isBookmarked(), id, userDetailsService.getUser().getId());
        return "bookmarked status changed";
    }

    @PostMapping("/homepage")
    public List<AuctionDomain> getHomepage(@RequestBody HomepageDomain homepageDomain) {
        if (isValid(homepageDomain)) {
            Page<Auction> homepageAuctions = auctionService.getHomepage(homepageDomain, userDetailsService.getUser().getId());
            Page<AuctionDomain> map = homepageAuctions.map(AuctionDomain::new);
            return map.get().collect(Collectors.toList());
        }
        throw new IllegalArgumentException("Arguments are invalid!");
    }

    @GetMapping("/myauctions")
    public List<Auction> getMyAuctions() {
        return auctionService.getMyAuctions(userDetailsService.getUser().getId());
    }

    private boolean isValid(HomepageDomain homepageDomain) {
        return homepageDomain.getCount() > 0 &&
                homepageDomain.getPage() >= 0;
    }

    @GetMapping("/mybookmarked")
    public List<Auction> getMyBookmarked(@PathVariable long userId) {
        return auctionService.getMyBookmarked(userId);
    }

}
