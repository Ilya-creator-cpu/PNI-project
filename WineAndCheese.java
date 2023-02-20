import org.apache.jena.graph.Capabilities;
import org.apache.jena.graph.Graph;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.InfGraph;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerException;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.vocabulary.RDFS;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.apache.jena.ontology.OntModelSpec.OWL_MEM;

public class WineAndCheese {


    public static void main(String[] args) throws IOException {
        String NS = "http://www.w3.org/TR/2003/m#";
        Model model = ModelFactory.createDefaultModel();
        OntModel ontology = ModelFactory.createOntologyModel(OWL_MEM,model);
        OntClass wine = ontology.createClass(NS +"Wine");
        OntClass cheese = ontology.createClass(NS + "cheese");
        Individual abrauDurso = wine.createIndividual(NS + "AbrauDurso");
        Individual usWine1974 = wine.createIndividual(NS + "usWine1974");
        Individual BeaulieuVineyard = wine.createIndividual(NS + "BeaulieuVineyard");
        Individual dorBlue = cheese.createIndividual(NS + "DorBlue");
        ObjectProperty redWine = ontology.createObjectProperty(NS + "RedWine");
        ObjectProperty whiteWine = ontology.createObjectProperty(NS + "WhiteWine");
        ObjectProperty oldCheese = ontology.createObjectProperty(NS + "OldCheese");
        ObjectProperty youngCheese = ontology.createObjectProperty(NS + "YoungCheese");
        abrauDurso.addProperty(redWine,"Red");
        BeaulieuVineyard.addProperty(whiteWine,"White");
        redWine.addInverseOf(oldCheese);
        youngCheese.addInverseOf(whiteWine);
        dorBlue.addProperty(youngCheese," YoungCheese");
        System.out.println(dorBlue.getProperty(youngCheese));
        DatatypeProperty yellowCheese = ontology.createDatatypeProperty(NS + "yellowCheese");
        dorBlue.addProperty(yellowCheese, " YellowCheese");
        wine.addProperty(redWine, "redWine");
        wine.addProperty(whiteWine, "whiteWine");
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner.bindSchema(model);
        reasoner.supportsProperty(oldCheese);
















        OutputStream outputStream = new FileOutputStream("src/main/resources/wine1.owl");
        ontology.write(outputStream,"RDF/XML-ABBREV");
        outputStream.close();
    }

    public ObjectProperty setProperty(OntModel ontology,String propName) {

        ObjectProperty property = ontology.createObjectProperty("http://www.w3.org/TR/2003/PR-owl-guide-20031209"
        + propName);

        return property;

    }
}
