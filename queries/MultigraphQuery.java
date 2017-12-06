import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;

public class MultigraphQuery {

	public static String graph = "/home/slaanaroth/Documents/TARQL/WebSemantic/rdf/graphFinal.ttl";
	public static String their_graph = "/home/slaanaroth/Documents/TARQL/WebSemantic/rdf/their_graph.ttl";
	
	
	public static void main(String[] args) throws IOException {
		
		Model data = FileManager.get().loadModel(graph,"TTL");
		Model their_data = FileManager.get().loadModel(their_graph,"TTL");
		Dataset dataset = DatasetFactory.create() ;
		dataset.addNamedModel("our_graph",data) ;
		dataset.addNamedModel("their_graph", their_data) ;
		
		
		String queryString = readFile("multigraph_query.sparql", StandardCharsets.UTF_8);
		
		Query query = QueryFactory.create(queryString) ;
		ResultSet results = null;
		try (QueryExecution qexec = QueryExecutionFactory.create(queryString, dataset)) {
		    results = qexec.execSelect() ;
		    results = ResultSetFactory.copyResults(results) ;
		}
		
		ResultSetFormatter.out(System.out, results, query) ;

	}
	
	static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}

}
