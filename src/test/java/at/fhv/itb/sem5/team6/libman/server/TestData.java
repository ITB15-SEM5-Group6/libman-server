package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.server.model.*;
import at.fhv.itb.sem5.team6.libman.server.persistence.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestData {
    public static void media(MediaRepository mediaRepository) {
        mediaRepository.deleteAll();

        List<at.fhv.itb.sem5.team6.libman.server.model.Media> mediasToAdd = new ArrayList<>();

        // add medias
        at.fhv.itb.sem5.team6.libman.server.model.Media media = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media.setTitle("Harry Potter und der Stein der Weisen");
        media.setType(MediaType.BOOK);
        media.setIsbn("9783551354013");
        media.setAuthor("J.K.Rowling");
        media.setPublisher("Carlsen");
        media.setReleaseDate(Date.valueOf("2005-01-23"));
        media.setTags("Zauberei, Abenteuer, Schule, Hogwarts,jugendbuch, magie ,orden, ron, schule, tod, voldemort ,zauberei,zauberer");
        media.setGenre(Genre.FANTASY);

        at.fhv.itb.sem5.team6.libman.server.model.Media media1 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media1.setTitle("City of Bones");
        media1.setType(MediaType.BOOK);
        media1.setIsbn("9783401502601");
        media1.setAuthor("Cassandra Clare");
        media1.setPublisher("Arena");
        media1.setReleaseDate(Date.valueOf("2011-01-05"));
        media1.setTags("Magie,liebe ,new york ,schattenjäger ,schattenwesen, simon, unterwelt, urban, fantasy, valentin, vampire, werwölfe");
        media1.setGenre(Genre.FANTASY);

        at.fhv.itb.sem5.team6.libman.server.model.Media media2 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media2.setTitle("Die Tribute von Panem");
        media2.setType(MediaType.BOOK);
        media2.setIsbn("9783841501349");
        media2.setAuthor("Suzanne Collins");
        media2.setPublisher("Oetinger Taschenbuch");
        media2.setReleaseDate(Date.valueOf("2012-10-01"));
        media2.setTags("Kampf, Revolution, Spiele, Hungerspiele,Katniss,jugendbuch,kapitol,katniss,liebe,panem,peeta,spiele,suzanne collins,tod,tribute,überleben,zukunft");
        media2.setGenre(Genre.FANTASY);

        at.fhv.itb.sem5.team6.libman.server.model.Media media3 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media3.setTitle("Das Reich der Sieben Höfe – Flammen und Finsternis");
        media3.setType(MediaType.BOOK);
        media3.setIsbn(" 9783423761826");
        media3.setAuthor(" 9783423761826");
        media3.setPublisher("dtv Verlagsgesellschaft");
        media3.setReleaseDate(Date.valueOf("2017-08-04"));
        media3.setTags("high lady, high lord, hof der nacht,hybern, kessel, krieg, liebe, magie, rhysand, sarah j. maas, seelengefährte, tamlin");
        media3.setGenre(Genre.FANTASY);

        at.fhv.itb.sem5.team6.libman.server.model.Media media4 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media4.setTitle("Origin");
        media4.setType(MediaType.BOOK);
        media4.setIsbn("9783431039993");
        media4.setAuthor("Dan Brown");
        media4.setPublisher("Ehrenwirth");
        media4.setReleaseDate(Date.valueOf("2017-10-04"));
        media4.setTags("atheismus, barcelona, bilbao, dan brown, gaudi, geschichte, glaube, kirche, langdon, origin, rätsel, religion, robert langdon, spanien, supercomputer, thriller, ursuppe, winston, wissenschaft");
        media4.setGenre(Genre.THRILLER);

        at.fhv.itb.sem5.team6.libman.server.model.Media media5 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media5.setTitle("Die Rückkehr der Wale");
        media5.setType(MediaType.BOOK);
        media5.setIsbn("9783426521809");
        media5.setAuthor("Ehrenwirth");
        media5.setPublisher("Knaur Taschenbuch");
        media5.setReleaseDate(Date.valueOf("2017-11-02"));
        media5.setTags("die rückkehr der wale, gefühle, insel, isabel, isabel morland, knaur, landschaft, legende, liebe, morlan, mystisch, natur, rückkehr, schottland, sehnsucht, selkie, wale, zweifel");
        media5.setGenre(Genre.ROMANTIC);

        at.fhv.itb.sem5.team6.libman.server.model.Media media6 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media6.setTitle("Träume, die ich uns stehle");
        media6.setType(MediaType.BOOK);
        media6.setIsbn("9783426518977");
        media6.setAuthor("Lily Oliver");
        media6.setPublisher("Knaur Taschenbuch");
        media6.setReleaseDate(Date.valueOf("2017-11-02"));
        media6.setTags("gefühl,hoffnung,intensivstation,koma, krankenhaus, liebe, psychiatrie, psychische krankheit, traurigkeit, unterdrückung ,verzweiflung");
        media6.setGenre(Genre.ROMANTIC);

        at.fhv.itb.sem5.team6.libman.server.model.Media media7 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media7.setTitle("Nachtspiel");
        media7.setType(MediaType.BOOK);
        media7.setIsbn("3944676092");
        media7.setAuthor("Catherine Shepherd ");
        media7.setPublisher("Kafel Verlag");
        media7.setReleaseDate(Date.valueOf("2017-10-24"));
        media7.setTags("Medizin, Julia , Tod, Frauenmörder, Leiche, Selbstmord, Kriminalkommisar, Serienkiller");
        media7.setGenre(Genre.HORROR);

        at.fhv.itb.sem5.team6.libman.server.model.Media media8 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media8.setTitle("Die Androidin - Auf der Flucht");
        media8.setType(MediaType.BOOK);
        media8.setIsbn(" 9783596297283");
        media8.setAuthor("Joel Shepherd ");
        media8.setPublisher("FISCHER Tor");
        media8.setReleaseDate(Date.valueOf("2017-04-27"));
        media8.setTags("androidin,flucht, joel, shepherd, tor");
        media8.setGenre(Genre.SCIFI);

        at.fhv.itb.sem5.team6.libman.server.model.Media media9 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media9.setTitle("Der letzte erste Kuss");
        media9.setType(MediaType.BOOK);
        media9.setIsbn("9783736304147");
        media9.setAuthor("Bianca Iosivoni");
        media9.setPublisher("LYX ein Imprint der Bastei Lübbe AG");
        media9.setReleaseDate(Date.valueOf("2017-10-26"));
        media9.setTags("alltag,anziehung, beste freunde, college, familie, freunde, freundschaft, gefühlschaos, große leidenschaft, high societey, kuss, liebe, liebesgeschichte, politik, roman ,studium");
        media9.setGenre(Genre.ROMANTIC);

        at.fhv.itb.sem5.team6.libman.server.model.Media media10 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media10.setTitle("Niemals");
        media10.setType(MediaType.BOOK);
        media10.setIsbn("9783518427569");
        media10.setAuthor("Andreas Pflüger");
        media10.setPublisher("Suhrkamp");
        media10.setReleaseDate(Date.valueOf("2017-10-09"));
        media10.setTags("james bond,jenny,aaron,marokko,marrakesch,menschen mit behinderungen,milliarden-erbe,schuld, spannung,thriller,vertrauen");
        media10.setGenre(Genre.THRILLER);

        at.fhv.itb.sem5.team6.libman.server.model.Media media11 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media11.setTitle("Unlocked");
        media11.setType(MediaType.DVD);
        media11.setIsbn("-");
        media11.setAuthor("Michael Apted"); //Regie
        media11.setPublisher("SquareOne"); //Studio
        media11.setReleaseDate(Date.valueOf("2017-01-01"));
        media11.setTags("Verschwörung, Politthriller, Terrorismus, Deutschland-Premiere, CIA, London, Anschlag");
        media11.setGenre(Genre.THRILLER);

        at.fhv.itb.sem5.team6.libman.server.model.Media media12 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media12.setTitle("PLÖTZLICH PAPA");
        media12.setType(MediaType.DVD);
        media12.setIsbn("-");
        media12.setAuthor("Hugo Gélin");
        media12.setPublisher("Universum Film");
        media12.setReleaseDate(Date.valueOf("2017-05-10"));
        media12.setTags("Frankreich, Single, Kristin, Gloria, Tochter, allein, London,Stuntman");
        media12.setGenre(Genre.COMEDY);

        at.fhv.itb.sem5.team6.libman.server.model.Media media13 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media13.setTitle("DIE GLORREICHEN SIEBEN");
        media13.setType(MediaType.DVD);
        media13.setIsbn("-");
        media13.setAuthor("Antoine Fuqua");
        media13.setPublisher("Sony Pictures");
        media13.setReleaseDate(Date.valueOf("2016-01-01"));
        media13.setTags("Rose Creek, Kopfgeldjäger, Revolverheld, Klassiker, Söldner, Bogue, Geld, gloreich, showdown");
        media13.setGenre(Genre.WESTERN);

        at.fhv.itb.sem5.team6.libman.server.model.Media media14 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media14.setTitle("Wüstenblume");
        media14.setType(MediaType.DVD);
        media14.setIsbn("-");
        media14.setAuthor("Sherry Hormann");
        media14.setPublisher("20th Century Fox");
        media14.setReleaseDate(Date.valueOf("2010-03-05"));
        media14.setTags("Afrika, Schicksal, Wüste, Waris, überleben, Vater, Zwangsheirat, Somalien, Nomadenmädchen,  ");
        media14.setGenre(Genre.DRAMA);

        at.fhv.itb.sem5.team6.libman.server.model.Media media15 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media15.setTitle("Ice Age 5");
        media15.setType(MediaType.DVD);
        media15.setIsbn("-");
        media15.setAuthor("Galen T. Chu, Mike Thurmeier");
        media15.setPublisher("20th Century Fox");
        media15.setReleaseDate(Date.valueOf("2016-11-10"));
        media15.setTags("Scrats,Eichel, Sid, Manni, Buck, Diego, Familie");
        media15.setGenre(Genre.ADVENTURE);

        at.fhv.itb.sem5.team6.libman.server.model.Media media16 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media16.setTitle("Poltergeist");
        media16.setType(MediaType.DVD);
        media16.setIsbn("-");
        media16.setAuthor("Gil Kenan");
        media16.setPublisher("Gil Kenan");
        media16.setReleaseDate(Date.valueOf("2015-10-22"));
        media16.setTags("Familie, Neuverfilmung, fürchten, Attacke, Tochter, Erscheinungen, Madison");
        media16.setGenre(Genre.HORROR);

        at.fhv.itb.sem5.team6.libman.server.model.Media media17 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media17.setTitle("TRANSFORMERS 5 - THE LAST KNIGHT");
        media17.setType(MediaType.DVD);
        media17.setIsbn("-");
        media17.setAuthor("Michael Bay");
        media17.setPublisher("Paramount");
        media17.setReleaseDate(Date.valueOf("2017-11-02"));
        media17.setTags("Spezies, Krieg, Menschen, Transformers, Optimus Prime, Erde, Rettung, Vergangenheit, Bumblebee, Lord Edmund Burton, Vivien, Izabella");
        media17.setGenre(Genre.ACTION);

        at.fhv.itb.sem5.team6.libman.server.model.Media media18 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media18.setTitle("DIE BUCHT");
        media18.setType(MediaType.DVD);
        media18.setIsbn("-");
        media18.setAuthor("Louie Psihoyos");
        media18.setPublisher("EuroVideo");
        media18.setReleaseDate(Date.valueOf("2010-03-05"));
        media18.setTags("Undercover-Mission, Geheimnis, Team, Spezialisten, Tauchern, Surfern, Bucht, Taiji, High-Tech");
        media18.setGenre(Genre.DOCUMENTATION);

        at.fhv.itb.sem5.team6.libman.server.model.Media media19 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media19.setTitle("Mädelstrip");
        media19.setType(MediaType.DVD);
        media19.setIsbn("-");
        media19.setAuthor("Jonathan Levine");
        media19.setPublisher("20th Century Fox");
        media19.setReleaseDate(Date.valueOf("2017-10-26"));
        media19.setTags("Urlaub, Emily, Linda, Mutter, Tochter, Weg, Dschungel");
        media19.setGenre(Genre.COMEDY);

        at.fhv.itb.sem5.team6.libman.server.model.Media media20 = new at.fhv.itb.sem5.team6.libman.server.model.Media();
        media20.setTitle("MAZE RUNNER 1");
        media20.setType(MediaType.DVD);
        media20.setIsbn("-");
        media20.setAuthor("Wes Ball");
        media20.setPublisher("20th Century Fox");
        media20.setReleaseDate(Date.valueOf("2015-02-26"));
        media20.setTags("Die Auserwählten im Labyrinth, Erinnerung, Thomas, Labyrinth, Weg, Geheimnis");
        media20.setGenre(Genre.SCIFI);

        Media media21 = new Media();
        media21.setTitle("Fünf Freunde und die goldene Maske des Pharao");
        media21.setType(MediaType.CD);
        media21.setIsbn("887254412229");
        media21.setAuthor("-");
        media21.setPublisher("SONY MUSIC ENTERTAINMENT");
        media21.setReleaseDate(Date.valueOf("2013-06-07"));
        media21.setTags("Südküste, Quentin, Böse");
        media21.setGenre(Genre.ADVENTURE);

        Media media22 = new Media();
        media22.setTitle("Alles was du brauchst - Seine größten Hits");
        media22.setType(MediaType.CD);
        media22.setIsbn("888751133020"); //EAN
        media22.setAuthor("Nik P.");
        media22.setPublisher("Sony Music Entertainment Germany ");
        media22.setReleaseDate(Date.valueOf("2015-09-25"));
        media22.setTags("Schlager");
        media22.setGenre(Genre.ALL);

        Media media23 = new Media();
        media23.setTitle("Entspannungsmusik");
        media23.setType(MediaType.CD);
        media23.setIsbn("4012897210260");
        media23.setAuthor("Wellness Pur");
        media23.setPublisher("Media Sound Art,");
        media23.setReleaseDate(Date.valueOf("2011-03-01"));
        media23.setTags("Instrumental, Wohlfühlen");
        media23.setGenre(Genre.DOCUMENTATION);

        Media media24 = new Media();
        media24.setTitle("Feuerwehrmann Sam - Abenteuer im Schnee");
        media24.setType(MediaType.CD);
        media24.setIsbn("4260264434331");
        media24.setAuthor("-");
        media24.setPublisher("Justbridge Entertainment ");
        media24.setReleaseDate(Date.valueOf("2015-11-13"));
        media24.setTags("Lawine, Sarah, Schnee");
        media24.setGenre(Genre.ADVENTURE);

        Media media25 = new Media();
        media25.setTitle("Trolls - Finde dein Glück");
        media25.setType(MediaType.CD);
        media25.setIsbn("4029759111764");
        media25.setAuthor("-");
        media25.setPublisher("Edel Germany");
        media25.setReleaseDate(Date.valueOf("2016-10-21"));
        media25.setTags("Fantasie, Regenbogen, Poppy, ");
        media25.setGenre(Genre.FANTASY);



        mediasToAdd.add(media);
        mediasToAdd.add(media1);
        mediasToAdd.add(media2);
        mediasToAdd.add(media3);
        mediasToAdd.add(media4);
        mediasToAdd.add(media5);
        mediasToAdd.add(media6);
        mediasToAdd.add(media7);
        mediasToAdd.add(media8);
        mediasToAdd.add(media9);
        mediasToAdd.add(media10);
        mediasToAdd.add(media11);
        mediasToAdd.add(media12);
        mediasToAdd.add(media13);
        mediasToAdd.add(media14);
        mediasToAdd.add(media15);
        mediasToAdd.add(media16);
        mediasToAdd.add(media17);
        mediasToAdd.add(media18);
        mediasToAdd.add(media19);
        mediasToAdd.add(media20);
        mediasToAdd.add(media21);
        mediasToAdd.add(media22);
        mediasToAdd.add(media23);
        mediasToAdd.add(media24);
        mediasToAdd.add(media25);


        mediaRepository.save(mediasToAdd);
    }

    public static void customer(CustomerRepository customerRepository) {
        customerRepository.deleteAll();

        List<Customer> list = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setFirstName("Alex");
        customer1.setLastName("Dengg");
        customer1.setAddress("Steinebach 13, 6850 Dornbirn");
        customer1.setEmail("alex.dengg@gmx.at");
        customer1.setPhoneNumber("069924739773");
        customer1.setBic("-");
        customer1.setIban("-");

        Customer customer2 = new Customer();
        customer2.setFirstName("Stefan");
        customer2.setLastName("Müller");
        customer2.setAddress("Sägerstraße 3, 6850 Dornbin");
        customer2.setEmail("stefan.müller@gmx.at");
        customer2.setPhoneNumber("06641639874");
        customer2.setBic("-");
        customer2.setIban("-");

        Customer customer3 = new Customer();
        customer3.setFirstName("Klaus");
        customer3.setLastName("Bechter");
        customer3.setAddress("Bregenzerstraße 2, 6900 Bregenz");
        customer3.setEmail("klaus.bechter@hotmail.com");
        customer3.setPhoneNumber("069937284995");
        customer3.setBic("-");
        customer3.setIban("-");

        Customer customer4 = new Customer();
        customer4.setFirstName("Stadtbücherei Bregenz");
        customer4.setLastName("-");
        customer4.setAddress("Gerberstraße 4, 6900 Bregenz");
        customer4.setEmail("stadtbuecherei@bregenz.at");
        customer4.setBic("SPBRAT2BXXX");
        customer4.setIban("AT64 2060 1000 0000 1800");

        Customer customer5 = new Customer();
        customer5.setFirstName("Peter");
        customer5.setLastName("Draxler");
        customer3.setAddress("Ritterstraße 6, 6900 Bregenz");
        customer3.setEmail("peter.draxler@hotmail.com");
        customer3.setPhoneNumber("069923484733");
        customer3.setBic("-");
        customer3.setIban("-");

        Customer customer6 = new Customer();
        customer6.setFirstName("Michael");
        customer6.setLastName("Sturm");
        customer3.setAddress("Hofstraße 109, 6900 Bregenz");
        customer3.setEmail("michael.sturm@gmx.at");
        customer3.setPhoneNumber("069929390445");
        customer3.setBic("-");
        customer3.setIban("-");


        Customer customer7 = new Customer();
        customer7.setFirstName("Sarah");
        customer7.setLastName("Greber");
        customer3.setAddress("Thal 39, 6934 Sulzberg");
        customer3.setEmail("sarah.greber@aon.at");
        customer3.setPhoneNumber("066419233443");
        customer3.setBic("-");
        customer3.setIban("-");

        Customer customer8 = new Customer();
        customer8.setFirstName("Öffentliche Bücherei der Gemeinde Kennelbach");
        customer8.setLastName("-");
        customer3.setAddress("Kirchstraße 17, 6921 Kennelbach");
        customer3.setEmail("direktion@vskb.snv.at");
        customer3.setPhoneNumber("0557471732");
        customer3.setBic("SPBAAT4BXXX");
        customer3.setIban("AT64 2050 1000 0000 1800");

        Customer customer9 = new Customer();
        customer9.setFirstName("Daniela");
        customer9.setLastName("Schwärzler");
        customer3.setAddress("Zellerweg 2, 6850 Dornbin ");
        customer3.setEmail("daniela.schwaerzler@gmx.at");
        customer3.setPhoneNumber("055724938");
        customer3.setBic("-");
        customer3.setIban("-");

        Customer customer10 = new Customer();
        customer10.setFirstName("Julia");
        customer10.setLastName("Giselbrecht");
        customer3.setAddress("Feldstraße 40, 6850 Dornbin ");
        customer3.setEmail("julia.gisbelbrecht@gmx.at");
        customer3.setPhoneNumber("069923998746");
        customer3.setBic("");
        customer3.setIban("-");

        Customer customer11 = new Customer();
        customer11.setFirstName("Michaela");
        customer11.setLastName("Knapp");
        customer3.setAddress("Buchsstraße 1,6850 Dornbin ");
        customer3.setEmail("michaela.knapp@hotmail.com");
        customer3.setPhoneNumber("069928374475");
        customer3.setBic("-");
        customer3.setIban("-");

        Customer customer12 = new Customer();
        customer12.setFirstName("Bücherei Höchst");
        customer12.setLastName("-");
        customer3.setAddress("Franz-Reiter-Straße 19, 6973 Höchst");
        customer3.setEmail("bvoe@bvoe.at ");
        customer3.setPhoneNumber("0557876898");
        customer3.setBic("SPBBAT3BXXX");
        customer3.setIban("AT64 2020 1000 0000 1800");

        Customer customer13 = new Customer();
        customer13.setFirstName("Alexandra");
        customer13.setLastName("Schöffel");
        customer3.setAddress("Baumstraße 8, 6973 Höchst");
        customer3.setEmail("alexandra@schöffel.at ");
        customer3.setPhoneNumber("0557876348");
        customer3.setBic("-");
        customer3.setIban("-");

        list.add(customer1);
        list.add(customer2);
        list.add(customer3);
        list.add(customer4);
        list.add(customer5);
        list.add(customer6);
        list.add(customer7);
        list.add(customer8);
        list.add(customer9);
        list.add(customer10);
        list.add(customer11);
        list.add(customer12);
        list.add(customer13);

        customerRepository.save(list);

    }

    public static void physicalMedia(PhysicalMediaRepository physicalMediaRepository, MediaRepository mediaRepository, int number) {
        Random r = new Random();
        physicalMediaRepository.deleteAll();

        List<Media> m = mediaRepository.findAll();

        List<PhysicalMedia> list = new ArrayList<>(m.size() * number);
        //relation to media
        m.forEach(media -> {
            for (int i = 1; i <= number; i++) {
                PhysicalMedia item = new PhysicalMedia();

                item.setAvailability(r.nextBoolean() ? Availability.AVAILABLE : Availability.NOT_AVAILABLE);
                item.setMedia(media);
                item.setIndex(media.getIsbn() + "-" + i);

                list.add(item);
            }
        });

        physicalMediaRepository.save(list);
    }

    public static void reservation(ReservationRepository reservationRepository, MediaRepository mediaRepository, CustomerRepository customerRepository, int number) {
        Random r = new Random();
        reservationRepository.deleteAll();

        List<Reservation> list = new ArrayList<>();

        //relation to customer and media
        List<Customer> c = customerRepository.findAll();
        List<Media> m = mediaRepository.findAll();

        for (int i = 0; i < number; i++) {
            Reservation item = new Reservation();

            int randomCustomer = r.nextInt(c.size());
            int randomMedia = r.nextInt(m.size());
            Date date = new Date(System.currentTimeMillis());
            Date newDate = new Date(date.getTime() - r.nextLong());

            item.setCustomer(c.get(randomCustomer));
            item.setMedia(m.get(randomMedia));
            item.setDate(newDate);

            list.add(item);
        }

        reservationRepository.save(list);
    }

    public static void lending(LendingRepository lendingRepository, PhysicalMediaRepository physicalMediaRepository, CustomerRepository customerRepository, int number) {
        Random r = new Random();
        lendingRepository.deleteAll();

        List<Lending> list = new ArrayList<>();

        //relation to customer and physicalMedia
        List<Customer> c = customerRepository.findAll();
        List<PhysicalMedia> p = physicalMediaRepository.findAll();

        for (int i = 0; i < number; i++) {
            Lending item = new Lending();

            int randomCustomer = r.nextInt(c.size());
            int randomPhysicalMedia = r.nextInt(p.size());

            item.setCustomer(c.get(randomCustomer));
            item.setPhysicalMedia(p.get(randomPhysicalMedia));
            item.setLendDate(new Date(System.currentTimeMillis() - i));
            item.setExtensions(r.nextInt(3));
            item.setInfo("Info =)");
            item.setState(r.nextBoolean() ? LendingState.LENT : LendingState.RETURNED);

            list.add(item);
        }

        lendingRepository.save(list);
    }
}
