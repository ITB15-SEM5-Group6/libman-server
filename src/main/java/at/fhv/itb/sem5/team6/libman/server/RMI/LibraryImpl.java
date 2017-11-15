package at.fhv.itb.sem5.team6.libman.server.RMI;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
import at.fhv.itb.sem5.team6.libman.shared.enums.LendingState;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LibraryImpl extends UnicastRemoteObject implements ILibrary {

    private final LibraryController libraryController;

    protected LibraryImpl(LibraryController libraryController) throws RemoteException {
        this.libraryController = libraryController;
    }

    protected LibraryImpl(int port, LibraryController libraryController) throws RemoteException {
        super(port);
        this.libraryController = libraryController;
    }

    protected LibraryImpl(int port, RMIClientSocketFactory csf, RMIServerSocketFactory ssf, LibraryController libraryController) throws RemoteException {
        super(port, csf, ssf);
        this.libraryController = libraryController;
    }

    @Override
    public List<MediaDTO> findMedias() throws RemoteException {
        return libraryController.findMedias();
    }

    @Override
    public List<MediaDTO> findMedias(String s, Genre genre, MediaType mediaType, Availability availability) throws RemoteException {
        return libraryController.findMedias(s, genre, mediaType, availability);
    }

    @Override
    public List<CustomerDTO> findCustomers() throws RemoteException {
        return libraryController.findCustomers();
    }

    @Override
    public List<CustomerDTO> findCustomers(String s) throws RemoteException {
        return libraryController.findCustomers(s);
    }

    @Override
    public List<PhysicalMediaDTO> findPhysicalMedias() throws RemoteException {
        return libraryController.findPhysicalMedias();
    }

    @Override
    public List<PhysicalMediaDTO> findPhysicalMediasByMedia(String s) throws RemoteException {
        return libraryController.findPhysicalMediasByMedia(s);
    }

    @Override
    public List<ReservationDTO> findReservations() throws RemoteException {
        return libraryController.findReservations();
    }

    @Override
    public List<ReservationDTO> findReservationsByMedia(String s) throws RemoteException {
        return libraryController.findReservationsByMedia(s);
    }

    @Override
    public List<ReservationDTO> findReservationsByCustomer(String s) throws RemoteException {
        return libraryController.findReservationsByCustomer(s);
    }

    @Override
    public List<ReservationDTO> findReservationsByMediaAndCustomer(String s, String s1) throws RemoteException {
        return libraryController.findReservationsByMediaAndCustomer(s, s);
    }

    @Override
    public List<LendingDTO> findLendings() throws RemoteException {
        return libraryController.findLendings();
    }

    @Override
    public List<LendingDTO> findLendings(LendingState lendingState) throws RemoteException {
        return libraryController.findLendings(lendingState);
    }

    @Override
    public List<LendingDTO> findLendingsByPhysicalMedia(String s) throws RemoteException {
        return libraryController.findLendingsByPhysicalMedia(s);
    }

    @Override
    public List<LendingDTO> findLendingsByPhysicalMedia(String s, LendingState lendingState) throws RemoteException {
        return libraryController.findLendingsByPhysicalMedia(s, lendingState);
    }

    @Override
    public List<LendingDTO> findLendingsByCustomer(String s) throws RemoteException {
        return libraryController.findLendingsByCustomer(s);
    }

    @Override
    public List<LendingDTO> findLendingsByCustomer(String s, LendingState lendingState) throws RemoteException {
        return libraryController.findLendingsByCustomer(s, lendingState);
    }

    @Override
    public List<LendingDTO> findLendingsByPhysicalMediaAndCustomer(String s, String s1) throws RemoteException {
        return libraryController.findLendingsByPhysicalMediaAndCustomer(s, s1);
    }

    @Override
    public List<LendingDTO> findLendingsByPhysicalMediaAndCustomer(String s, String s1, LendingState lendingState) throws RemoteException {
        return libraryController.findLendingsByPhysicalMediaAndCustomer(s, s1, lendingState);
    }

    @Override
    public ReservationDTO reserve(String s, String s1) throws RemoteException {
        return libraryController.reserve(s, s1);
    }

    @Override
    public void cancelReservation(String s) throws RemoteException {
        libraryController.cancelReservation(s);
    }

    @Override
    public LendingDTO lend(String s, String s1) throws RemoteException {
        return libraryController.lend(s, s1);
    }

    @Override
    public void returnLending(String s) throws RemoteException {
        libraryController.returnLending(s);
    }

    @Override
    public LendingDTO extendLending(String s) throws RemoteException {
        return libraryController.extendLending(s);
    }
}
