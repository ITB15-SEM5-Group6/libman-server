package at.fhv.itb.sem5.team6.libman.server.RMI;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.MediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.DTOs.PhysicalMediaDTO;
import at.fhv.itb.sem5.team6.libman.shared.enums.Availability;
import at.fhv.itb.sem5.team6.libman.shared.enums.MediaType;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class LibraryImpl extends UnicastRemoteObject implements ILibrary {

    private LibraryController libraryController;

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

// Search

    @Override
    public List<MediaDTO> findAllMedia() throws RemoteException {
        return libraryController.findAllMedia();
    }

    @Override
    public List<MediaDTO> findAllMedia(String text) throws RemoteException {
        return libraryController.findAllMedia(text);
    }

    @Override
    public List<MediaDTO> findAllMedia(MediaType type) throws RemoteException {
        return libraryController.findAllMedia(type);
    }

    @Override
    public List<MediaDTO> findAllMedia(Availability availability) throws RemoteException {
        return libraryController.findAllMedia(availability);
    }

    @Override
    public List<MediaDTO> findAllMedia(String text, MediaType type, Availability availability) throws RemoteException {
        return libraryController.findAllMedia(text, type, availability);
    }

    @Override
    public List<PhysicalMediaDTO> findAllPhysicalMedia() {
        return libraryController.findAllPhysicalMedia();
    }

    @Override
    public List<PhysicalMediaDTO> getPhysicalMedia(MediaDTO media) {
        return libraryController.getPhysicalMedia(media);
    }

// Reservation

// Lending
}
