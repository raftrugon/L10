
package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import utilities.internal.FactoryObject;
import utilities.internal.FactoryObject.Attribute;
import utilities.internal.FactoryObject.Input;
import utilities.internal.FactoryObject.Query;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Group16Factory {

	public static void main(final String[] args) throws Exception {
		Gson gson = new Gson();
		String json = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/factoryjson")), "UTF-8");
		//Get Types for deserialization
		Type entityType = new TypeToken<List<FactoryObject>>() {
		}.getType();
		//Deserialize
		List<FactoryObject> entities = gson.fromJson(json, entityType);
		//LoadTemplateFiles
		final String domainTemplate = "src/main/resources/templates/domainTemplate.txt";
		final String domainStart = new String(Files.readAllBytes(Paths.get(domainTemplate)), "UTF-8");

		//CreateFiles
		for (FactoryObject entity : entities) {
			Group16Factory.createDomain(entity, domainStart);
			Group16Factory.createRepository(entity);
			Group16Factory.createService(entity);
			Group16Factory.createController(entity);
		}
		Group16Factory.createAuxiliaryStructure(entities);
	}

	private static void createDomain(FactoryObject entity, String domainStart) throws FileNotFoundException {
		String file = "src/main/java/domain/" + entity.getName() + ".java";
		PrintWriter printer = new PrintWriter(file);
		printer.print(domainStart);
		if (entity.getExt() != null)
			printer.print("import domain." + entity.getExt() + ";" + Group16Factory.NL);
		printer.print(Group16Factory.NL + Group16Factory.DOMAIN_START_ANNOTATIONS);
		if (entity.getAbs())
			printer.print(Group16Factory.DOMAIN_ABS);
		printer.print("public ");
		if (entity.getAbs())
			printer.print("abstract ");
		printer.print("class " + entity.getName() + " extends ");
		if (entity.getExt() != null)
			printer.print(entity.getExt() + " {" + Group16Factory.NL);
		else
			printer.print("DomainEntity {" + Group16Factory.NL);
		printer.print(Group16Factory.NLT + "//Attributes----------------" + Group16Factory.NLT);
		Group16Factory.generateGettersAndSetters(entity.getAttributes(), printer);
		printer.print(Group16Factory.NLT + "//Relationships----------------" + Group16Factory.NLT);
		Group16Factory.generateGettersAndSetters(entity.getRelationships(), printer);

		printer.print(Group16Factory.NL + "}");
		printer.close();
	}

	private static void createRepository(FactoryObject entity) throws FileNotFoundException {
		String file = "src/main/java/repositories/" + entity.getName() + "Repository.java";
		PrintWriter printer = new PrintWriter(file);
		printer.print(Group16Factory.REPOSITORY_START);
		printer.print("import domain." + entity.getName() + ";" + Group16Factory.NL + Group16Factory.NL + "@Repository" + Group16Factory.NL);
		printer.print("public interface " + entity.getName() + "Repository extends JpaRepository<" + entity.getName() + ", Integer> {" + Group16Factory.NL + Group16Factory.NL);
		//Queries
		if (entity.getQueries() != null) {
			for (Query query : entity.getQueries()) {
				if (query.getComment() != null)
					printer.print("\t//" + query.getComment() + Group16Factory.NL);
				printer.print("\t@Query(\"" + query.getQueryString() + "\")" + Group16Factory.NL);
				printer.print("\t" + query.getOutput() + " " + query.getName() + "(");
				if (query.getInputs() != null) {
					int i = 1;
					for (Input input : query.getInputs()) {
						printer.print(input.getType() + " " + input.getName());
						if (i != query.getInputs().size())
							printer.print(", ");
						i++;
					}
				}
				printer.print(");");
			}
		}
		printer.print(Group16Factory.NL + Group16Factory.NL + "}");
		printer.close();
	}

	private static void createService(FactoryObject entity) throws FileNotFoundException {
		String file = "src/main/java/services/" + entity.getName() + "Service.java";
		PrintWriter printer = new PrintWriter(file);
		printer.print(Group16Factory.SERVICE_START);
		printer.print("import domain." + entity.getName() + ";" + Group16Factory.NL);
		printer.print("import repositories." + entity.getName() + "Repository;" + Group16Factory.NL + Group16Factory.NL);
		printer.print("@Service" + Group16Factory.NL + "@Transactional" + Group16Factory.NL);
		printer.print("public class " + entity.getName() + "Service {" + Group16Factory.NL + Group16Factory.NLT);
		printer.print("@Autowired" + Group16Factory.NLT + "private " + entity.getName() + "Repository\t\t" + Group16Factory.firstLower(entity.getName()) + "Repository;" + Group16Factory.NL + Group16Factory.NLT);
		printer.print("//Supporting Services -------------------" + Group16Factory.NL + Group16Factory.NL + Group16Factory.NLT);
		printer.print("//CRUD Methods -------------------------" + Group16Factory.NL + Group16Factory.NLT);
		if (entity.getServiceMethods().contains("create")) {
			printer.print("public " + entity.getName() + " create() {" + Group16Factory.NLTT + entity.getName() + " res = new " + entity.getName() + "();" + Group16Factory.NLTT + Group16Factory.NLTT + "return res;" + Group16Factory.NLT + "}"
				+ Group16Factory.NLT + Group16Factory.NLT);
		}
		if (entity.getServiceMethods().contains("findOne")) {
			printer.print("public " + entity.getName() + " findOne(int " + Group16Factory.firstLower(entity.getName()) + "Id) {" + Group16Factory.NLTT);
			printer.print("Assert.isTrue(" + Group16Factory.firstLower(entity.getName()) + "Id != 0);" + Group16Factory.NLTT);
			printer.print(entity.getName() + " res = " + Group16Factory.firstLower(entity.getName()) + "Repository.findOne(" + Group16Factory.firstLower(entity.getName()) + "Id);" + Group16Factory.NLTT);
			printer.print("Assert.notNull(res);" + Group16Factory.NLTT + "return res;" + Group16Factory.NLT + "}" + Group16Factory.NLT + Group16Factory.NLT);
		}
		if (entity.getServiceMethods().contains("findAll")) {
			printer.print("public Collection<" + entity.getName() + "> findAll() {" + Group16Factory.NLTT + "return " + Group16Factory.firstLower(entity.getName()) + "Repository.findAll();" + Group16Factory.NLT + "}" + Group16Factory.NLT
				+ Group16Factory.NLT);
		}
		if (entity.getServiceMethods().contains("save")) {
			printer.print("public " + entity.getName() + " save(final " + entity.getName() + " " + Group16Factory.firstLower(entity.getName()) + ") {" + Group16Factory.NLTT);
			printer.print("Assert.notNull(" + Group16Factory.firstLower(entity.getName()) + ");" + Group16Factory.NLTT);
			printer.print(Group16Factory.NLTT + Group16Factory.NLTT + "return " + Group16Factory.firstLower(entity.getName()) + "Repository.save(" + Group16Factory.firstLower(entity.getName()) + ");" + Group16Factory.NLT + "}" + Group16Factory.NLT
				+ Group16Factory.NLT);
		}
		if (entity.getServiceMethods().contains("delete")) {
			printer.print("public void delete(final int " + Group16Factory.firstLower(entity.getName()) + "Id) {" + Group16Factory.NLTT);
			printer.print("Assert.isTrue(" + Group16Factory.firstLower(entity.getName()) + "Id != 0);" + Group16Factory.NLTT);
			printer.print(Group16Factory.firstLower(entity.getName()) + "Repository.delete(" + Group16Factory.firstLower(entity.getName()) + "Id);" + Group16Factory.NLT + "}" + Group16Factory.NLT + Group16Factory.NLT);
		}
		printer.print(Group16Factory.NL + "}");
		printer.close();
	}

	private static void createAuxiliaryStructure(List<FactoryObject> entities) throws UnsupportedEncodingException, IOException {

		String converterList = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/converterListTemplate.txt")), "UTF-8");
		String tilesList = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/tilesListTemplate.txt")), "UTF-8");
		String messagesList = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/messagesListTemplate.txt")), "UTF-8");

		for (FactoryObject entity : entities) {
			//Converters
			String converter1 = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/converter1Template.txt")), "UTF-8");
			String converter2 = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/converter2Template.txt")), "UTF-8");
			String fileConverter1 = "src/main/java/converters/" + entity.getName() + "ToStringConverter.java";
			String fileConverter2 = "src/main/java/converters/StringTo" + entity.getName() + "Converter.java";
			PrintWriter printer1 = new PrintWriter(fileConverter1);
			PrintWriter printer2 = new PrintWriter(fileConverter2);
			converter1 = converter1.replace("%ent%", entity.getName());
			converter1 = converter1.replace("%ea%", Group16Factory.firstLower(entity.getName()));
			converter2 = converter2.replace("%ent%", entity.getName());
			converter2 = converter2.replace("%ea%", Group16Factory.firstLower(entity.getName()));
			printer1.print(converter1);
			printer2.print(converter2);
			printer1.close();
			printer2.close();
			//ConverterList part1
			converterList += Group16Factory.NLTT + "<bean class=\"converters.StringTo" + entity.getName() + "Converter\"/>";
			converterList += Group16Factory.NLTT + "<bean class=\"converters." + entity.getName() + "ToStringConverter\"/>";
			//Tiles File Part1
			String tiles = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/tilesTemplate.txt")), "UTF-8");
			String tilesEs = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/tilesTemplate.txt")), "UTF-8");

			//WebappFolder
			String folder = "src/main/webapp/views/" + Group16Factory.firstLower(entity.getName());
			new File(folder).mkdirs();
			if (entity.getList() != null) {
				Group16Factory.createListView(entity, folder);
				tiles += Group16Factory.NLT + "<definition name=\"misc/panic\" extends=\"master.page\">" + Group16Factory.NLTT + "<put-attribute name=\"title\" value=\"" + entity.getName() + " List\" />" + Group16Factory.NLTT
					+ "<put-attribute name=\"body\" value=\"/views/" + Group16Factory.firstLower(entity.getName()) + "/list.jsp\" />" + Group16Factory.NLT + "</definition>";
				tilesEs += Group16Factory.NLT + "<definition name=\"misc/panic\" extends=\"master.page\">" + Group16Factory.NLTT + "<put-attribute name=\"title\" value=\"Listado de " + entity.getSpanishName() + "\" />" + Group16Factory.NLTT
					+ "<put-attribute name=\"body\" value=\"/views/" + Group16Factory.firstLower(entity.getName()) + "/list.jsp\" />" + Group16Factory.NLT + "</definition>";
			}
			if (entity.getEdit() != null) {
				Group16Factory.createEditView(entity, folder);
				tiles += Group16Factory.NLT + "<definition name=\"misc/panic\" extends=\"master.page\">" + Group16Factory.NLTT + "<put-attribute name=\"title\" value=\"" + entity.getName() + " Edit\" />" + Group16Factory.NLTT
					+ "<put-attribute name=\"body\" value=\"/views/" + Group16Factory.firstLower(entity.getName()) + "/list.jsp\" />" + Group16Factory.NLT + "</definition>";
				tilesEs += Group16Factory.NLT + "<definition name=\"misc/panic\" extends=\"master.page\">" + Group16Factory.NLTT + "<put-attribute name=\"title\" value=\"Edicion de " + entity.getSpanishName() + "\" />" + Group16Factory.NLTT
					+ "<put-attribute name=\"body\" value=\"/views/" + Group16Factory.firstLower(entity.getName()) + "/edit.jsp\" />" + Group16Factory.NLT + "</definition>";
			}
			if (entity.getDisplay() != null) {
				Group16Factory.createDisplayView(entity, folder);
				tiles += Group16Factory.NLT + "<definition name=\"misc/panic\" extends=\"master.page\">" + Group16Factory.NLTT + "<put-attribute name=\"title\" value=\"" + entity.getName() + " Display\" />" + Group16Factory.NLTT
					+ "<put-attribute name=\"body\" value=\"/views/" + Group16Factory.firstLower(entity.getName()) + "/list.jsp\" />" + Group16Factory.NLT + "</definition>";
				tilesEs += Group16Factory.NLT + "<definition name=\"misc/panic\" extends=\"master.page\">" + Group16Factory.NLTT + "<put-attribute name=\"title\" value=\"Visualizacion de " + entity.getSpanishName() + "\" />" + Group16Factory.NLTT
					+ "<put-attribute name=\"body\" value=\"/views/" + Group16Factory.firstLower(entity.getName()) + "/display.jsp\" />" + Group16Factory.NLT + "</definition>";
			}
			//Tiles File Part2
			if (entity.getList() != null || entity.getEdit() != null || entity.getDisplay() != null) {
				tiles += "</tiles-definitions>";
				tilesEs += "</tiles-definitions>";
				String tilesFile = folder + "/tiles.xml";
				String tilesEsFile = folder + "/tiles_es.xml";
				PrintWriter printerTiles = new PrintWriter(tilesFile);
				printerTiles.print(tiles);
				printerTiles.close();
				PrintWriter printerTilesEs = new PrintWriter(tilesEsFile);
				printerTilesEs.print(tilesEs);
				printerTilesEs.close();
			}
			//i18n-l10n
			Group16Factory.createMessages(entity, folder);
			//TilesList part1
			tilesList += Group16Factory.NLTT + "<value>/views/" + Group16Factory.firstLower(entity.getName()) + "/tiles.xml</value>";
			//MessagesList part1
			messagesList += Group16Factory.NLTT + "<value>/views/" + Group16Factory.firstLower(entity.getName()) + "/messages</value>";
		}

		//Converter List part2
		converterList += Group16Factory.NLT + "</util:list>" + Group16Factory.NL + "</beans>";
		String fileConverterList = "src/main/resources/spring/config/converters.xml";
		PrintWriter printerConverterList = new PrintWriter(fileConverterList);
		printerConverterList.print(converterList);
		printerConverterList.close();
		//Tiles List part2
		tilesList += Group16Factory.NLT + "</util:list>" + Group16Factory.NL + "</beans>";
		String fileTilesList = "src/main/resources/spring/config/tiles.xml";
		PrintWriter printerTilesList = new PrintWriter(fileTilesList);
		printerTilesList.print(tilesList);
		printerTilesList.close();
		//Messages List part2
		messagesList += Group16Factory.NLT + "</util:list>" + Group16Factory.NL + "</beans>";
		String fileMessagesList = "src/main/resources/spring/config/i18n-l10n.xml";
		PrintWriter printerMessagesList = new PrintWriter(fileMessagesList);
		printerMessagesList.print(messagesList);
		printerMessagesList.close();

	}

	private static void createDisplayView(FactoryObject entity, String folder) throws UnsupportedEncodingException, IOException {
		String file = folder + "/display.jsp";
		PrintWriter printer = new PrintWriter(file);
		String view = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/displayViewTemplate.txt")), "UTF-8");
		printer.print(view + Group16Factory.NLT);
		printer.close();
	}

	private static void createEditView(FactoryObject entity, String folder) throws UnsupportedEncodingException, IOException {
		String file = folder + "/edit.jsp";
		PrintWriter printer = new PrintWriter(file);
		String view = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/editViewTemplate.txt")), "UTF-8");
		printer.print(view + Group16Factory.NLT);
		printer.print("<form:form action=\"" + Group16Factory.firstLower(entity.getName()) + "/edit.do\" modelAttribute=\"" + Group16Factory.firstLower(entity.getName()) + "\">" + Group16Factory.NLT);
		printer.print("<jstl:set var='model' value='" + Group16Factory.firstLower(entity.getName()) + "' scope='request'/>");
		for (Attribute attr : entity.getAttributes()) {
			printer.print(Group16Factory.NLTT + "<lib:input type=\"text\" name='" + attr.getName() + "'/>");
		}
		printer.print(Group16Factory.NL + "</form:form>" + Group16Factory.NL + "</div>");
		printer.close();
	}

	private static void createListView(FactoryObject entity, String folder) throws UnsupportedEncodingException, IOException {
		String file = folder + "/list.jsp";
		PrintWriter printer = new PrintWriter(file);
		String view = new String(Files.readAllBytes(Paths.get("src/main/resources/templates/listViewTemplate.txt")), "UTF-8");
		printer.print(view + Group16Factory.NLT);
		printer.print("<jstl:set var='model' value='" + Group16Factory.firstLower(entity.getName()) + "' scope='request'/>");
		for (Attribute attr : entity.getAttributes()) {
			printer.print(Group16Factory.NLTT + "<lib:column name='" + attr.getName() + "'/>");
		}
		printer.print(Group16Factory.NL + "</display:table>" + Group16Factory.NL + "</div>");
		printer.close();
	}

	private static void createMessages(FactoryObject entity, String folder) throws FileNotFoundException {
		String file = folder + "/messages.properties";
		String fileEs = folder + "/messages_es.properties";
		PrintWriter printer = new PrintWriter(file);
		PrintWriter printerEs = new PrintWriter(fileEs);
		String keys = "";
		for (Attribute attr : entity.getAttributes()) {
			keys += Group16Factory.firstLower(entity.getName()) + "." + attr.getName() + "=" + Group16Factory.NL;
		}
		keys += Group16Factory.firstLower(entity.getName()) + ".save=" + Group16Factory.NL;
		keys += Group16Factory.firstLower(entity.getName()) + ".cancel=" + Group16Factory.NL;
		keys += Group16Factory.firstLower(entity.getName()) + ".edit=" + Group16Factory.NL;
		keys += Group16Factory.firstLower(entity.getName()) + ".delete=" + Group16Factory.NL;
		keys += Group16Factory.firstLower(entity.getName()) + ".commitError=" + Group16Factory.NL;

		printer.print(keys);
		printer.close();
		printerEs.print(keys);
		printerEs.close();
	}

	private static void createController(FactoryObject entity) throws UnsupportedEncodingException, IOException {
		//Uri de Archivo a crear
		String doc = "src/main/java/controllers/";
		//Uri de template a leer
		String templateFile;
		//Es con authority o no?
		String authority = entity.getAuth();
		if (authority != null) {
			doc = doc.concat(authority + "/");
			templateFile = "src/main/java/utilities/internal/templateWithAuthority.txt";
			//Crear directorio si no existe
			new File(doc).mkdirs();
			doc = doc.concat(authority);
		} else {
			templateFile = "src/main/java/utilities/internal/template.txt";
			//Crear directorio si no existe
			new File(doc).mkdirs();
		}
		//Añadir nombre de archivo a crear a la uri
		doc = doc.concat(entity.getName() + "Controller.java");
		//Crear printer
		PrintWriter printer = new PrintWriter(doc);
		//Leer template y convertir a string con codificación UTF-8
		String template = new String(Files.readAllBytes(Paths.get(templateFile)), "UTF-8");
		//Insertar nombres de entidad y entidad como atributo
		template = template.replace("%ent%", entity.getName());
		template = template.replace("%ea%", Group16Factory.firstLower(entity.getName()));
		//Insertar nombre de autoridad si procede
		if (authority != null) {
			String subAuthority = Character.toLowerCase(authority.charAt(0)) + authority.substring(1);
			template = template.replace("%auth%", authority);
			template = template.replace("%autha%", subAuthority);
		}
		template = template.replace("%aux%", "");
		template = template.replace("%aux2%", "");
		//Imprimir a archivo y cerrar printer
		printer.print(template);
		printer.close();
	}

	private static void generateGettersAndSetters(List<Attribute> attributes, PrintWriter printer) {
		if (attributes == null)
			return;
		//Attribute List
		for (Attribute attribute : attributes) {
			if (!attribute.getIsCollection())
				printer.print("private " + attribute.getType() + " " + attribute.getName() + ";" + Group16Factory.NLT);
			else
				printer.print("private Collection<" + attribute.getType() + "> " + attribute.getName() + "s;" + Group16Factory.NLT);
		}
		printer.print(Group16Factory.NLT);

		//Getters and setters
		for (Attribute attribute : attributes) {
			//Hibernate Annotations
			for (String annotation : attribute.getAnnotations()) {
				printer.print("@" + annotation + Group16Factory.NLT);
			}
			//Getter
			if (!attribute.getIsCollection())
				printer.print("public " + attribute.getType() + " get" + Group16Factory.firstUpper(attribute.getName()) + "() {" + Group16Factory.NLT);
			else
				printer.print("public Collection<" + attribute.getType() + "> get" + Group16Factory.firstUpper(attribute.getName()) + "s() {" + Group16Factory.NLT);
			if (!attribute.getIsCollection())
				printer.print("\treturn " + attribute.getName() + ";" + Group16Factory.NLT);
			else
				printer.print("\treturn " + attribute.getName() + "s;" + Group16Factory.NLT);
			printer.print("}" + Group16Factory.NLT + Group16Factory.NLT);

			//Setter
			if (!attribute.getIsCollection())
				printer.print("public void set" + Group16Factory.firstUpper(attribute.getName()) + "(" + attribute.getType() + " " + attribute.getName() + ") {" + Group16Factory.NLT);
			else
				printer.print("public void set" + Group16Factory.firstUpper(attribute.getName()) + "s(Collection<" + attribute.getType() + "> " + attribute.getName() + "s) {" + Group16Factory.NLT);
			if (!attribute.getIsCollection())
				printer.print("\tthis." + attribute.getName() + " = " + attribute.getName() + ";" + Group16Factory.NLT);
			else
				printer.print("\tthis." + attribute.getName() + "s = " + attribute.getName() + "s;" + Group16Factory.NLT);
			printer.print("}" + Group16Factory.NLT + Group16Factory.NLT);
		}
	}

	private static String firstUpper(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	}

	private static String firstLower(String input) {
		return input.substring(0, 1).toLowerCase() + input.substring(1);
	}


	private static final String	NL							= System.lineSeparator();
	private static final String	NLT							= System.lineSeparator() + "\t";
	private static final String	NLTT						= System.lineSeparator() + "\t\t";
	private static final String	DOMAIN_START_ANNOTATIONS	= "@Entity" + Group16Factory.NL + "@Access(AccessType.PROPERTY)" + Group16Factory.NL;
	private static final String	DOMAIN_ABS					= "@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)" + Group16Factory.NL;
	private static final String	REPOSITORY_START			= "package repositories;" + Group16Factory.NL + Group16Factory.NL + "import org.springframework.data.jpa.repository.JpaRepository;" + Group16Factory.NL
																+ "import org.springframework.data.jpa.repository.Query;" + Group16Factory.NL + "import org.springframework.stereotype.Repository;" + Group16Factory.NL;
	private static final String	SERVICE_START				= "package services;" + Group16Factory.NL + "import org.springframework.beans.factory.annotation.Autowired;" + Group16Factory.NL + "import org.springframework.stereotype.Service;"
																+ Group16Factory.NL + "import org.springframework.transaction.annotation.Transactional;" + Group16Factory.NL + "import org.springframework.util.Assert;" + Group16Factory.NL
																+ "import java.util.Collection;" + Group16Factory.NL;
}
