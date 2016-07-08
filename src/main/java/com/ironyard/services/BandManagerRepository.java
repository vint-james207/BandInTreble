package com.ironyard.services;

import com.ironyard.entities.BandManager;
import org.hibernate.mapping.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jeffryporter on 7/7/16.
 */
public interface BandManagerRepository extends CrudRepository<BandManager, Integer>
{
    public List findByInstrumentNeeded(String instrumentNeeded);
}
