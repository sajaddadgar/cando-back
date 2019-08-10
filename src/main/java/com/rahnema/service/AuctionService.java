package com.rahnema.service;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.rahnema.domain.AuctionDomain;
import com.rahnema.domain.AuctionInfoDomain;
import com.rahnema.domain.HomepageDomain;
import com.rahnema.model.Auction;
import com.rahnema.model.Category;
import com.rahnema.model.User;
import com.rahnema.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private UserService userService;


    public Optional<Auction> getAuction(long id) {
        return auctionRepository.findById(id);
    }

    public AuctionInfoDomain getAuctionInfo(long id) {
        Optional<Auction> auction = auctionRepository.findById(id);
        return new AuctionInfoDomain(auction.orElseThrow(IllegalArgumentException::new));
    }

    public Auction addAuction(AuctionDomain auctionDomain, long id) {
        Auction auction = auctionDomain.generate();
        Optional<User> user = userService.getOneUser(id);
        auctionRepository.save(auction);
        auction.setCreator(user.get());
        userService.addUser(user.get());
        return auction;
    }

    public Page<Auction> getHomepage(HomepageDomain homepageDomain, long id) {
        Optional<Category> optionalCategory = Arrays.stream(Category.values())
                .filter(category -> category.getId() == homepageDomain.getCategoryId()).findFirst();
        Category category = optionalCategory.orElseThrow(WrongArgumentException::new);

        User user = userService.getOneUser(id).orElseThrow(WrongArgumentException::new);
        Page<Auction> page;
        if (category.equals(Category.ALL))
            page = auctionRepository.findByTitleStartsWith(homepageDomain.getSearch(),
                    PageRequest.of(homepageDomain.getPage(), homepageDomain.getCount()));
        else
            page = auctionRepository.findByTitleStartsWithAndCategory(
                    homepageDomain.getSearch(),
                    category,
                    PageRequest.of(homepageDomain.getPage(), homepageDomain.getCount()));

        page.forEach(auction -> {
            if (user.getBookmarkAuction().contains(auction))
                auction.setBoookmarked(true);
            else
                auction.setBoookmarked(false);
        });
        return page;
    }

    public List<Auction> getMyAuctions(long userId) {
        List<Auction> auctions = (List<Auction>) auctionRepository.findAll();
        List<Auction> myAcution = new ArrayList<>();
        for (Auction auction : auctions) {
            if (auction.getCreator().getId() == userId) {
                myAcution.add(auction);
            }
        }
        return myAcution;
    }

    public Set<Auction> getMyBookmarked(long userId) {
        Optional<User> user = userService.getOneUser(userId);
        if (!user.isPresent())
            throw new UsernameNotFoundException("user not found.");
        return user.get().getBookmarkAuction();
    }

    public void doBookmark(boolean bookmarked, long auctionId, long user) {
        Optional<Auction> byId = auctionRepository.findById(auctionId);
        Optional<User> oneUser = userService.getOneUser(user);
        byId.ifPresent(auction -> {
            if (bookmarked) {
                auction.addBookmarkUser(oneUser.get());
                oneUser.get().addBookmarkedAuction(auction);

            } else {
                auction.removeBookmarkUser(oneUser.get());
                oneUser.get().removeBookmarkedAuction(auction);
            }
            auctionRepository.save(auction);
            userService.update(oneUser.get(), user);
        });
        byId.orElseThrow(WrongArgumentException::new);
    }
}
