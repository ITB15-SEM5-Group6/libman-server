package at.fhv.itb.sem5.team6.libman.server.RMI;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibraryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;

@Service
public class LibraryFactoryImpl implements ILibraryFactory {

    @Autowired
    private LibraryController libraryController;

    public ILibrary create() throws RemoteException {
        return new LibraryImpl(libraryController);
    }
}
