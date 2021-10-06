package me.candiesjar.helium.utils;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import me.candiesjar.helium.Helium;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginAwareness;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.logging.Level;

/**
  @author  AlbeMiglio - PowerLib
  @since 1.0
*/

public class ConfigManager {

    private final HashMap<String, FileConfiguration> configs;

    public ConfigManager() {
        this.configs = new HashMap<>();
    }

    public FileConfiguration get(String file) {
        if (this.configs.containsKey(file)) {
            return this.configs.get(file);
        } else {
            return this.create(file);
        }
    }

    public FileConfiguration create(String file, String source) {
        File resourcePath = new File(Helium.getInstance().getDataFolder() + "/" + file);
        if (!resourcePath.exists()) {
            createYAML(resourcePath.getName(), source);
        } else this.reload(file);
        return this.configs.get(file);
    }

    public FileConfiguration create(String file) {
        return this.create(file, file);
    }

    public void save(String file) {
        FileConfiguration config = get(file);
        if (config == null) {
            throw new IllegalArgumentException("The specified configuration file doesn't exist!");
        }
        try {
            config.save(new File(Helium.getInstance().getDataFolder(), file));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.put(file, config);
    }

    public void reload(String file) {
        FileConfiguration conf = this.load(file);
        InputStream defStream = Helium.getInstance().getResource(file);
        if (defStream != null) {
            YamlConfiguration yamlConfig;
            if (!this.isStrictlyUTF8()) {
                yamlConfig = new YamlConfiguration();
                byte[] contents;
                try {
                    contents = ByteStreams.toByteArray(defStream);
                } catch (IOException e) {
                    Helium.getInstance().getLogger().log(Level.SEVERE, "Unexpected failure reading " + file, e);
                    return;
                }

                String text = new String(contents, Charset.defaultCharset());
                if (!text.equals(new String(contents, Charsets.UTF_8))) {
                    Helium.getInstance().getLogger().warning("Default system encoding has misread " + file + " from plugin jar");
                }

                try {
                    yamlConfig.loadFromString(text);
                } catch (InvalidConfigurationException e) {
                    Helium.getInstance().getLogger().log(Level.SEVERE, "Cannot load configuration from jar", e);
                }
            } else {
                yamlConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defStream, Charsets.UTF_8));
            }
            conf.setDefaults(yamlConfig);
            this.put(file, conf);
        }
    }

    public void reloadAll() {
        this.configs.keySet().forEach(this::reload);
    }

    private FileConfiguration load(String file) {
        return YamlConfiguration.loadConfiguration(new File(Helium.getInstance().getDataFolder() + "/" + file));
    }

    private void put(String file, FileConfiguration config) {
        this.configs.put(file, config);
    }

    private void createYAML(String resourcePath, String source) {
        if (resourcePath != null && !resourcePath.equals("")) {
            resourcePath = resourcePath.replace('\\', '/');
            InputStream in = Helium.getInstance().getResource(source);
            if (in == null) {
                throw new IllegalArgumentException("The embedded resource '" + source + "' cannot be found in " +
                        (new File(Helium.getInstance().getDataFolder() + "/" + source)));
            } else {
                File outFile = new File(Helium.getInstance().getDataFolder() + "/" + resourcePath);
                int lastIndex = resourcePath.lastIndexOf(47);
                File outDir = new File(Helium.getInstance().getDataFolder() + "/" + resourcePath.substring(0, Math.max(lastIndex, 0)));
                if (!outDir.exists()) {
                    outDir.mkdirs();
                }
                try {
                    if (outFile.exists()) {
                        Bukkit.getLogger().log(Level.WARNING, outFile.getName() + "loaded with success!");
                    } else {
                        OutputStream out = new FileOutputStream(outFile);
                        byte[] buf = new byte[1024];

                        int len;
                        while ((len = in.read(buf)) > 0) {
                            out.write(buf, 0, len);
                        }

                        out.close();
                        in.close();
                    }
                } catch (IOException var10) {
                    Bukkit.getLogger().log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, var10);
                }

            }
            this.reload(resourcePath);
        } else {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }
    }

    private boolean isStrictlyUTF8() {
        return Helium.getInstance().getDescription().getAwareness().contains(PluginAwareness.Flags.UTF8);
    }
}
