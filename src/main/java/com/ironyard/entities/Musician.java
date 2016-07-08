package com.ironyard.entities;

import javax.persistence.*;

/**
 * Created by jeffryporter on 7/7/16.
 */

@Entity
@Table(name = "musicians")
public class Musician
{
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    Boolean drummer;

    @Column(nullable = false)
    Boolean leadGuitarist;

    @Column(nullable = false)
    Boolean backupGuitarist;

    @Column(nullable = false)
    Boolean leadSinger;

    @Column(nullable = false)
    Boolean backupSinger;

    @Column(nullable = false)
    Boolean bassist;

    @Column(nullable = false)
    Boolean tambourine;

    @Column(nullable = false)
    Boolean cowBellPlayer;

    @Column(nullable = false)
    Boolean pianist;

    @OneToOne
    User user;

    public Musician()
    {
    }

    public Musician(Boolean drummer, Boolean leadGuitarist, Boolean backupGuitarist, Boolean leadSinger, Boolean backupSinger, Boolean bassist, Boolean tambourine, Boolean cowBellPlayer, Boolean pianist, User user)
    {
        this.drummer = drummer;
        this.leadGuitarist = leadGuitarist;
        this.backupGuitarist = backupGuitarist;
        this.leadSinger = leadSinger;
        this.backupSinger = backupSinger;
        this.bassist = bassist;
        this.tambourine = tambourine;
        this.cowBellPlayer = cowBellPlayer;
        this.pianist = pianist;
        this.user = user;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Boolean getDrummer()
    {
        return drummer;
    }

    public void setDrummer(Boolean drummer)
    {
        this.drummer = drummer;
    }

    public Boolean getLeadGuitarist()
    {
        return leadGuitarist;
    }

    public void setLeadGuitarist(Boolean leadGuitarist)
    {
        this.leadGuitarist = leadGuitarist;
    }

    public Boolean getBackupGuitarist()
    {
        return backupGuitarist;
    }

    public void setBackupGuitarist(Boolean backupGuitarist)
    {
        this.backupGuitarist = backupGuitarist;
    }

    public Boolean getLeadSinger()
    {
        return leadSinger;
    }

    public void setLeadSinger(Boolean leadSinger)
    {
        this.leadSinger = leadSinger;
    }

    public Boolean getBackupSinger()
    {
        return backupSinger;
    }

    public void setBackupSinger(Boolean backupSinger)
    {
        this.backupSinger = backupSinger;
    }

    public Boolean getBassist()
    {
        return bassist;
    }

    public void setBassist(Boolean bassist)
    {
        this.bassist = bassist;
    }

    public Boolean getTambourine()
    {
        return tambourine;
    }

    public void setTambourine(Boolean tambourine)
    {
        this.tambourine = tambourine;
    }

    public Boolean getCowBellPlayer()
    {
        return cowBellPlayer;
    }

    public void setCowBellPlayer(Boolean cowBellPlayer)
    {
        this.cowBellPlayer = cowBellPlayer;
    }

    public Boolean getPianist()
    {
        return pianist;
    }

    public void setPianist(Boolean pianist)
    {
        this.pianist = pianist;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
