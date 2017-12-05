import java.io.FileWriter;
import java.io.Writer;

import org.apache.jena.rdf.model.AnonId;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.util.FileManager;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.DC_11;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.VOID;

	public class MetadataGeneration {
	    
		// Address of the 2 Turtle files : our initial graph and the destination Turtle file
		public static String graph = "/home/slaanaroth/Documents/TARQL/WebSemantic/rdf/graphSameAsWithRDFS.ttl";
		public static String finalGraph = "/home/slaanaroth/Documents/TARQL/WebSemantic/rdf/graphFinal.ttl";
		
	    public static void main (String args[]) throws Exception {
	    	  
	    	// Loading our initial model
	    	Model data = FileManager.get().loadModel(graph,"TTL");
	        
	    	// Creating a Person to set as dataset creator
	    	Resource MerlinBarzilai = data.createResource(new AnonId("MerlinBarzilai"))
	    			.addProperty(RDF.type, FOAF.Person)
	    			.addProperty(FOAF.name, "Merlin Barzilai")
	    			.addProperty(FOAF.mbox, "merlin.barzilai@etu.univ-nantes.fr");
	    	
	    	// Creating a second Person to set as dataset creator
	    	Resource CorentinMarionneau = data.createResource(new AnonId("CorentinMarionneau"))
	    			.addProperty(RDF.type, FOAF.Person)
	    			.addProperty(FOAF.name, "Corentin Marionneau")
	    			.addProperty(FOAF.mbox, "corentin.marionneau@etu.univ-nantes.fr");
	    	
	    	// Creating the dataset metadata node
	    	Resource WebSemDataset = data.createResource(new AnonId("WebSemDataset"))
	    			.addProperty(RDF.type, VOID.Dataset)
	    			.addProperty(VOID.feature, "http://www.w3.org/ns/formats/Turtle")
	    			.addLiteral(VOID.triples, 13000)
	    			.addLiteral(VOID.classes, 8)
	    			.addLiteral(VOID.properties, 15)
	    			.addProperty(DCTerms.source, "https://data.enseignementsup-recherche.gouv.fr/explore/dataset/fr-esr-pes-pedr-beneficiaires/")
	    			.addProperty(DC_11.title, "Bénéficiaires de la prime d'excellence scientifique")
	    			.addProperty(DC_11.description, "Graphe rdf en format Turtle généré depuis le dataset csv sur les primes d'excellence scientifique fourni par l'ESR")
	    			.addProperty(DC_11.creator, MerlinBarzilai)
	    			.addProperty(DC_11.creator, CorentinMarionneau);

	    	// Adding the metadata to the model
	    	data.add(WebSemDataset.listProperties());
	        
	        // Writing the result into our destination file
	        Writer wr = new FileWriter(finalGraph);
	        data.write(wr,"TTL");
	        System.out.println("Metadata generated.");
	    }
	}
