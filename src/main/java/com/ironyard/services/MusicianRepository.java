package com.ironyard.services;

import com.ironyard.entities.Musician;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jeffryporter on 7/7/16.
 */
public interface MusicianRepository extends CrudRepository<Musician, Integer>
{
    public Iterable<Musician> findByDrummer(Boolean drummer);
    public Iterable<Musician> findByLeadGuitarist(Boolean leadGuitarist);
    public Iterable<Musician> findByBackupGuitarist(Boolean backupGuitarist);
    public Iterable<Musician> findByLeadSinger(Boolean leadSinger);
    public Iterable<Musician> findByBackupSinger(Boolean backupSinger);
    public Iterable<Musician> findByBassist(Boolean bassist);
    public Iterable<Musician> findByTambourine(Boolean tambourine);
    public Iterable<Musician> findByCowBellPlayer(Boolean cowBellPlayer);
    public Iterable<Musician> findByPianist(Boolean pianist);
}
