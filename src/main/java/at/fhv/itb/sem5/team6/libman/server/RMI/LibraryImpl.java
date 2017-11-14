package at.fhv.itb.sem5.team6.libman.server.RMI;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.*;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.Genre;
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
    public List<PhysicalMediaDTO> findPhysicalMedias(MediaDTO mediaDTO) throws RemoteException {
        return libraryController.findPhysicalMedias(mediaDTO);
    }

    @Override
    public List<ReservationDTO> findReservations() throws RemoteException {
        return libraryController.findReservations();
    }

    @Override
    public List<ReservationDTO> findReservations(MediaDTO mediaDTO) throws RemoteException {
        return libraryController.findReservations(mediaDTO);
    }

    @Override
    public List<ReservationDTO> findReservations(CustomerDTO customerDTO) throws RemoteException {
        return libraryController.findReservations();
    }

    @Override
    public List<ReservationDTO> findReservations(MediaDTO mediaDTO, CustomerDTO customerDTO) throws RemoteException {
        return libraryController.findReservations(mediaDTO, customerDTO);
    }

    @Override
    public List<LendingDTO> findLendings() throws RemoteException {
        return libraryController.findLendings();
    }

    @Override
    public List<LendingDTO> findLendings(PhysicalMediaDTO physicalMediaDTO) throws RemoteException {
        return libraryController.findLendings(physicalMediaDTO);
    }

    @Override
    public List<LendingDTO> findLendings(CustomerDTO customerDTO) throws RemoteException {
        return libraryController.findLendings(customerDTO);
    }

    @Override
    public List<LendingDTO> findLendings(PhysicalMediaDTO physicalMediaDTO, CustomerDTO customerDTO) throws RemoteException {
        return libraryController.findLendings(physicalMediaDTO, customerDTO);
    }

    @Override
    public ReservationDTO reserve(MediaDTO mediaDTO, CustomerDTO customerDTO) throws RemoteException {
        return libraryController.reserve(mediaDTO, customerDTO);
    }

    @Override
    public void cancelReservation(ReservationDTO reservationDTO) throws RemoteException {
        libraryController.cancelReservation(reservationDTO);
    }

    @Override
    public LendingDTO lend(PhysicalMediaDTO physicalMediaDTO, CustomerDTO customerDTO) throws RemoteException {
        return libraryController.lend(physicalMediaDTO, customerDTO);
    }

    @Override
    public void returnLending(LendingDTO lendingDTO) throws RemoteException {
        libraryController.returnLending(lendingDTO);
    }

    @Override
    public LendingDTO extendLending(LendingDTO lendingDTO) throws RemoteException {
        return libraryController.extendLending(lendingDTO);
    }

}
