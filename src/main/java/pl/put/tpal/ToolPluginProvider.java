package pl.put.tpal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import pl.put.tpal.tool.Tool;

public class ToolPluginProvider {
    
    private static final String TYPE_SEPARATOR = ".";
    private static final String PLUGINS_PATH = "C:/Users/Asus/workspace/Paint/src/main/resources/plugins/";
    
    public ToolPluginProvider() {
    }
    
    private static List<Tool> getTools() throws MalformedURLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        
        Map<String, URL> pathsByName = new HashMap<>();
        
        try (Stream<Path> paths = Files.walk(Paths.get(PLUGINS_PATH))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    
                    try {
                        pathsByName.put(filePath.getFileName().toString(), filePath.toUri().toURL());
                        System.out.println("Wczytano plugin: " + filePath.getFileName().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<Tool> elements = new LinkedList<>();
        for (String name: pathsByName.keySet()) {
            
            URL[] urlsToLoadFrom = new URL[] {pathsByName.get(name)};
            URLClassLoader loader1 = new URLClassLoader(urlsToLoadFrom);
            
            String onlyName = name.substring(0, name.indexOf(TYPE_SEPARATOR));
            
            if (onlyName.contains("Tool")) {
                Class<?> klasa = Class.forName(onlyName, true, loader1);
                System.out.println("Zainicjowano plugin: " + klasa.getSimpleName());
                Object newClassInstance = klasa.newInstance();
                elements.add((Tool) newClassInstance);
            }
        }
        
        return elements;
    }
    
    public static List<Tool> getPlugedItems() throws MalformedURLException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        return getTools();
    }
}
