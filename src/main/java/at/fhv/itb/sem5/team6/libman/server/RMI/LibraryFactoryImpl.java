package at.fhv.itb.sem5.team6.libman.server.RMI;

import at.fhv.itb.sem5.team6.libman.server.application.LibraryController;
import at.fhv.itb.sem5.team6.libman.shared.enums.UserRole;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibrary;
import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibraryFactory;
import com.google.common.collect.ImmutableSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.rmi.RemoteException;
import java.util.Hashtable;

@Service
public class LibraryFactoryImpl implements ILibraryFactory {

    // TODO insert user names
    private final ImmutableSet<String> admins = ImmutableSet.of("franz", "hans", "günther");
    private final ImmutableSet<String> operators = ImmutableSet.of("franz", "hans", "günther");
    private final ImmutableSet<String> employees = ImmutableSet.of("franz", "hans", "günther");

    private final LibraryController libraryController;

    @Autowired
    public LibraryFactoryImpl(LibraryController libraryController) {
        this.libraryController = libraryController;
    }

    public ILibrary create(String username, String password) throws RemoteException {
        //LDAP
        try {
            authenticate(username, password);
        } catch (NamingException e) {
            throw new RemoteException(e.getMessage());
        }

        //check user role
        UserRole userRole = UserRole.GUEST;
        if (admins.contains(username)) {
            userRole = UserRole.ADMIN;
        } else if (operators.contains(username)) {
            userRole = UserRole.OPERATOR;
        } else if (employees.contains(username)) {
            userRole = UserRole.EMPLOYEE;
        }

        return new LibraryImpl(libraryController, userRole);
    }

    public void authenticate(String username, String password) throws NamingException {
        String base = "ou=people,o=fhv.at";
        String dn = "uid=" + username + "," + base;
        String ldapURL = "ldap://openldap.fhv.at";

        Hashtable<String, String> environment =
                new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, ldapURL);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, dn);
        environment.put(Context.SECURITY_CREDENTIALS, password);

        DirContext authContext = new InitialDirContext(environment);
    }
}
