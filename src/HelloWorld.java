import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {

    private Map<Integer, String> modules = new HashMap<>();

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

    @GET
    @Path("/time")
    @Produces("text/plain")
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

    @GET
    @Path("/module")
    @Produces("text/plain")
    public String getModuleByID(@QueryParam("key") Integer id) {
        String csvFile = "C:/Users/dylan.montando/Desktop/GitDirectory/rest_glassfish_hello_world/src/modules.csv";
        String line = "";
        String cvsSplitBy = ",";


        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), "UTF8"));
            //avoid the first line (corresponding to the header)
            br.readLine();

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] modulesFromFile = line.split(cvsSplitBy);

                this.modules.put(Integer.parseInt(modulesFromFile[0]), modulesFromFile[1]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.modules.get(id);
    }
}
