package com.movienetscape.usermanagementservice.service.impl;

import com.movienetscape.usermanagementservice.service.contract.IWatchListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class WatchListServiceImpl implements IWatchListService {

    @Autowired
    public WatchListServiceImpl(){

    }
}
