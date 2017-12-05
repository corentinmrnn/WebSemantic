import java.io.FileWriter;
import java.io.Writer;

import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

	public class InferenceGeneration {
	    
		// Address of the 3 Turtle files : our original graph, our schema graph and the destination Turtle file
		public static String graph = "/home/slaanaroth/Documents/TARQL/WebSemantic/rdf/graph.ttl";
		public static String ontology = "/home/slaanaroth/Documents/TARQL/WebSemantic/inferences/ontology.ttl";
		public static String withOnto = "/home/slaanaroth/Documents/TARQL/WebSemantic/rdf/graphWithRDFS.ttl";
		
	    public static void main (String args[]) throws Exception {
	    	  
	    	// Loading our 2 models, the data and the schema
	    	Model data = FileManager.get().loadModel(graph,"TTL");
	        Model schema = FileManager.get().loadModel(ontology,"TTL");
	        
	        // Generating inferences from the schema on the data
	        InfModel infmodel = ModelFactory.createRDFSModel(schema, data);
	        
	        // Adding the generated triples to the data
	        data.add(infmodel);
	        
	        // Writing the result into our destination file
	        Writer wr = new FileWriter(withOnto);
	        infmodel.write(wr,"TTL");
      
	    }
	}
