package com.rahnema.repository;

import com.rahnema.model.Auction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long> {

    Page<Auction> findByTitle(String title, Pageable pageable);

    Page<Auction> findAll(Pageable pageable);
}