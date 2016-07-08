package com.ironyard.services;

import com.ironyard.entities.BandManager;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jeffryporter on 7/7/16.
 */
public interface BandManagerRepository extends CrudRepository<BandManager, Integer>
{
    public List findByInstrumentNeeded(String instrumentNeeded);
}
