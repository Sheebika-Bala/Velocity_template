package org.example;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Load XML
            InputStream xml = Main.class.getResourceAsStream("/application.xml");
            if (xml == null) {
                System.err.println("Could not find application.xml in resources.");
                return;
            }

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml);

            List<Map<String, Object>> fields = new ArrayList<>();
            NodeList fieldNodes = doc.getElementsByTagName("field");
            for (int i = 0; i < fieldNodes.getLength(); i++) {
                Element fieldElem = (Element) fieldNodes.item(i);
                Map<String, Object> field = new HashMap<>();
                field.put("name", fieldElem.getElementsByTagName("name").item(0).getTextContent());
                field.put("type", fieldElem.getElementsByTagName("type").item(0).getTextContent());
                field.put("label", fieldElem.getElementsByTagName("label").item(0).getTextContent());
                if ("radio".equals(field.get("type"))) {
                    NodeList options = fieldElem.getElementsByTagName("option");
                    List<String> opts = new ArrayList<>();
                    for (int j = 0; j < options.getLength(); j++) {
                        opts.add(options.item(j).getTextContent());
                    }
                    field.put("options", opts);
                }
                fields.add(field);
            }

            VelocityEngine ve = new VelocityEngine();
            ve.setProperty("resource.loader", "class");
            ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            ve.init();

            Scanner scanner = new Scanner(System.in);
            Map<String, String> answers = new HashMap<>();

            for (int step = 0; step < fields.size(); step++) {
                Map<String, Object> field = fields.get(step);
                Template t = ve.getTemplate("templates/form_step.vm");
                VelocityContext context = new VelocityContext();
                context.put("field", field);

                StringWriter writer = new StringWriter();
                t.merge(context, writer);
                System.out.println(writer.toString());

                if ("message".equals(field.get("type"))) {
                    break;
                }

                System.out.print("Enter your response and press submit: ");
                String input = scanner.nextLine();
                answers.put((String) field.get("name"), input);
            }

            System.out.println("Form submitted. Thank you!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}