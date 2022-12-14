import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Een klasse om informatie over de servicediensten te lezen
 *
 * @author Marc De Caluwé
 * @version 2020-11-13
 */
public class ServiceReader {
    // de list bevat alle ontdekte services
    private ArrayList<Service> services;

    public ServiceReader() throws Exception {
        this("services.ini");
    }
    
    /**
     * Creeert een ServiceReader en leest het bestand in om er de services in te zoeken en te bewaren in de services-list
     * @param inifile Het servicebestand.
     */
    public ServiceReader(String inifile) throws Exception {
        services = new ArrayList<>();
        HashMap<String, String> entries = new HashMap<>();
        try{
            File pFile = new File("");
            File serviceFile = new File(pFile.getAbsolutePath()+"/data/"+inifile);
            Scanner scanFile = new Scanner(serviceFile);
            // Lees het servicebestand tot de laatste lijn
            boolean serviceFound = false;
            while(scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                if (!line.equals("[service]") && serviceFound) {
                    String[] splitLine = line.split("=");
                    if (splitLine.length==2) {
                        entries.put(splitLine[0], splitLine[1]);
                    }
                }
                if (line.equals("[service]")) {
                    serviceFound = true;
                    add(entries);
                }
            }
            scanFile.close();
            add(entries);
        } catch(FileNotFoundException e) {
            //System.out.println("Er dook een probleem op: " + e.toString());
            throw e;
        }
    }

    private void add(HashMap<String, String> entries) {
        if (!entries.isEmpty()) {
            try {
                services.add(new Service(entries));
            } catch(Exception e) {
                System.out.println(e.toString());
            }
            entries.clear();
        }
    }

    /**
     * Retourneert een list met alle gevonden services
     * @return ArrayList<Service> service
     */
    public ArrayList<Service> getServices() {
        return services;
    }

    /**
     * Bestaat de service voor een softwarebedrijf?
     * @param company naam softwarebedrijf
     * @return true als bestaand, anders false
     */
    public boolean hasService(String company) {
        for(Service service : services) {
            if (service.getValue(Service.COMPANY).equals(company)) return true;
        }
        return false;
    }

    /**
     * Haal de service voor een softwarebedrijf
     * @param company naam softwarebedrijf
     * @return Service
     */
    public Service getService(String company) throws Exception {
        for(Service service : services) {
            if (service.getValue(Service.COMPANY).equals(company)) return service;
        }
        throw new Exception("De service voor het bedrijf " + company + " bestaat niet");
    }

    /**
     * Print de services.
     */    
    public void printServices() {
        for(Service service : services) {
            System.out.println(service);
        }
    }
}
