package at.fhv.itb.sem5.team6.libman.server;

import at.fhv.itb.sem5.team6.libman.shared.interfaces.ILibraryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiServiceExporter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	RmiServiceExporter exporter(ILibraryFactory implementation) {
		Class<ILibraryFactory> serviceInterface = ILibraryFactory.class;
		RmiServiceExporter exporter = new RmiServiceExporter();
		exporter.setServiceInterface(serviceInterface);
		exporter.setService(implementation);
		exporter.setServiceName("LibraryFactory");
		exporter.setRegistryPort(1099);
		return exporter;
	}
}
