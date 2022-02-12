/*
 * MIT License
 *
 * Copyright (c) 2020 mdashlw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package me.goodestenglish.streamer;

import lombok.Getter;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class StreamerConfig {

    private final Configuration configuration;
    @Getter private String mongoHost;

    public StreamerConfig(File file) {
        configuration = new Configuration(file);
        loadConfiguration();
    }

    private void updateConfiguration(boolean load) {
        Property property = configuration.get(Configuration.CATEGORY_GENERAL, "mongoHost", "");

        if (load) {
            mongoHost = property.getString();
        } else {
            property.set(mongoHost);
        }
    }

    private void loadConfiguration() {
        updateConfiguration(true);
    }

    public void saveConfiguration() {
        updateConfiguration(false);
        configuration.save();
    }




    public void setMongoHost(String host) {
        mongoHost = host;
        saveConfiguration();
    }

}
