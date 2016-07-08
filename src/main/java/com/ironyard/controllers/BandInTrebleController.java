package com.ironyard.controllers;

import com.ironyard.entities.BandManager;
import com.ironyard.entities.Musician;
import com.ironyard.entities.User;
import com.ironyard.services.BandManagerRepository;
import com.ironyard.services.MusicianRepository;
import com.ironyard.services.UserRepository;
import com.ironyard.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jeffryporter on 7/7/16.
 */


@RestController
public class BandInTrebleController
{

    public static final String  MUSICIAN_FILENAME = "musicians.csv";
    public static final String  GIG_FILENAME = "bandManager.csv";

    @Autowired
    UserRepository users;

    @Autowired
    MusicianRepository musicians;

    @Autowired
    BandManagerRepository band_managers;

    @PostConstruct
    public void init() throws SQLException
    {
        Server.createWebServer().start();
        migrateMusicianCSVfile(MUSICIAN_FILENAME);
        migrateBandManagerCSVfile(GIG_FILENAME);
    }



    // login POST route
    // requires User object
    // returns a User
    //
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user, HttpSession session) throws Exception
    {
        User userFromDb = users.findFirstByName(user.getName());
        if(userFromDb == null)
        {
            user.setPassword(PasswordStorage.createHash(user.getPassword()));
            //set avatar if available
            if (user.getAvatar() != null)
            {
                File dir = new File("public/avatars");
                dir.mkdirs();

                File uploadedFile = File.createTempFile("file", user.getAvatar(), dir);
                FileOutputStream fos = new FileOutputStream(uploadedFile);

                fos.write(user.getAvatar().getBytes());

                user.setAvatar(uploadedFile.getName());
            }
            users.save(user);
        }
        else if (!PasswordStorage.verifyPassword(user.getPassword(), userFromDb.getPassword()))
        {
            throw new Exception("Wrong Password!");
        }

        session.setAttribute("userId", user.getId());
        return user;
    }


    // logout POST route
    // requires a session
    // returns nothing
    //
    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public void logout(HttpSession session) throws Exception
    {
        session.invalidate();
    }

    // band-manager GET route
    // requires bandManager object
    // returns a list of musicians fitting the
    //
    //THE STRING RETURNED HAS TO MATCH THE VARIABLE EXACTLY SO I CAN MANIPULATE IT PROPERLY
    @RequestMapping(path = "/band-manager", method = RequestMethod.GET)
    public Iterable<Musician> getMusician(@RequestBody BandManager bandManager)
    {
        switch (bandManager.getInstrumentNeeded())
        {
            case "all":
                return musicians.findAll();
            case "drummer":
                return musicians.findByDrummer(true);
            case "leadGuitarist":
                return musicians.findByLeadGuitarist(true);
            case "backupGuitarist":
                return musicians.findByBackupGuitarist(true);
            case "leadSinger":
                return musicians.findByLeadSinger(true);
            case "backupSinger":
                return musicians.findByBackupSinger(true);
            case "bassist":
                return musicians.findByBassist(true);
            case "tambourine":
                return musicians.findByTambourine(true);
            case "cowBellPlayer":
                return musicians.findByCowBellPlayer(true);
            case "pianist":
                return musicians.findByPianist(true);
            default:
                System.err.printf("Error in Controller '/band-manager' GET route.  BandManager recieved a value of: %s", bandManager.getInstrumentNeeded());
                return null;
        }
    }

    // band-manager POST route
    // requires bandManager object and session
    // returns user
    //
    @RequestMapping(path = "/band-manager", method = RequestMethod.POST)
    public BandManager postGig(HttpSession session, @RequestBody BandManager bandManager)
    {
        User user = users.findOne((Integer) session.getAttribute("userId"));
        band_managers.save(bandManager);
        return bandManager;
    }

    // band-manager PUT route
    // requires bandManager object and session
    // returns user
    //
    @RequestMapping(path = "/band-manager", method = RequestMethod.PUT)
    public BandManager putGig(HttpSession session, @RequestBody BandManager bandManager)
    {
        User user = users.findOne((Integer) session.getAttribute("userId"));
        band_managers.save(bandManager);
        return bandManager;
    }

    // band-manager DELETE route
    // requires bandManager object and session
    // returns user
    //
    @RequestMapping(path = "/band-manager", method = RequestMethod.DELETE)
    public User deleteGig(HttpSession session, @RequestBody BandManager bandManager)
    {
        User user = users.findOne((Integer) session.getAttribute("userId"));
        band_managers.delete(bandManager);
        return user;
    }


    // musician GET route
    // requires musician object
    // returns an ArrayList of available gigs
    //
    @RequestMapping(path = "/musician", method = RequestMethod.GET)
    public Iterable<BandManager> getGig(@RequestBody Musician musician)
    {
        List<BandManager> gigs = new ArrayList<>();

        //using in-line conditionals to save space.  just filling the array with the appropriate info
        if (musician.getDrummer()){gigs.addAll(band_managers.findByInstrumentNeeded("drummer"));}
        if (musician.getLeadGuitarist()){gigs.addAll(band_managers.findByInstrumentNeeded("leadGuitarist"));}
        if (musician.getBackupGuitarist()){gigs.addAll(band_managers.findByInstrumentNeeded("backupGuitarist"));}
        if (musician.getLeadSinger()){gigs.addAll(band_managers.findByInstrumentNeeded("leadSinger"));}
        if (musician.getBackupSinger()){gigs.addAll(band_managers.findByInstrumentNeeded("backupSinger"));}
        if (musician.getBassist()){gigs.addAll(band_managers.findByInstrumentNeeded("bassist"));}
        if (musician.getTambourine()){gigs.addAll(band_managers.findByInstrumentNeeded("tambourine"));}
        if (musician.getCowBellPlayer()){gigs.addAll(band_managers.findByInstrumentNeeded("cowBellPlayer"));}
        if (musician.getPianist()){gigs.addAll(band_managers.findByInstrumentNeeded("pianist"));}

        if (gigs.isEmpty())
        {
            System.err.printf("Error in Controller '/musicians' GET route.  Musician recieved an invalid boolean set.");
            return null;
        }
        else
        {
            return gigs;
        }
    }

    // musician POST route
    // requires Musician object and session
    // returns user
    //
    @RequestMapping(path = "/musician", method = RequestMethod.POST)
    public Musician postMusician(HttpSession session, @RequestBody Musician musician)
    {
        User user = users.findOne((Integer) session.getAttribute("userId"));
        musicians.save(musician);
        return musician;
    }

    // musician PUT route
    // requires Musician object and session
    // returns user
    //
    @RequestMapping(path = "/musician", method = RequestMethod.PUT)
    public Musician putMusician(HttpSession session, @RequestBody Musician musician)
    {
        User user = users.findOne((Integer) session.getAttribute("userId"));
        musicians.save(musician);
        return musician;
    }

    // musician DELETE route
    // requires Musician object and session
    // returns user
    //
    @RequestMapping(path = "/musician", method = RequestMethod.DELETE)
    public User deleteMusician(HttpSession session, @RequestBody Musician musician)
    {
        User user = users.findOne((Integer) session.getAttribute("userId"));
        musicians.delete(musician);
        return user;
    }

    public void migrateMusicianCSVfile(String filename) throws FileNotFoundException
    {
        ArrayList<Musician> countries = new ArrayList<>();
        File f = new File(filename);
        Scanner fileScanner =  new Scanner(f);
        while (fileScanner.hasNext())
        {
            String line = fileScanner.nextLine();
            String[] fields = line.split(",");
            Musician musician = new Musician();


            musicians.save(musician);
        }
    }

    public void migrateBandManagerCSVfile(String filename) throws FileNotFoundException
    {
        ArrayList<Musician> countries = new ArrayList<>();
        File f = new File(filename);
        Scanner fileScanner =  new Scanner(f);
        while (fileScanner.hasNext())
        {
            String line = fileScanner.nextLine();
            String[] fields = line.split(",");
            BandManager bandManager = new BandManager();


            band_managers.save(bandManager);
        }
    }

}


