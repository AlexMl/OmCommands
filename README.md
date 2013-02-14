OmCommands
==========

This is a project meant to decouple the classes I use in my Bukkit plugins from any one plugin. This project can be compiled into a jar that can be used as a dependency for a plugin. Alternatively it could be included in the plugin's source using git submodules. Or if you are using Maven you could try using the JarJar plugin to repackage the class files in your jar:
http://sonatype.github.com/jarjar-maven-plugin/


These classes originate from some I pulled out of:
https://github.com/mung3r/ecoCreature
Which I believe were themselves pulled out of one of DThielke's plugins:
http://dev.bukkit.org/profiles/DThielke/

I made additional improvements that helped improved the reusability of the classes as I was using them.

Compilation
-----------

This plugin has a Maven 3 pom.xml and uses Maven to compile. Dependencies are 
therefore managed by Maven. You should be able to build it with Maven by running

    mvn package

a jar will be generated in the target folder. For those unfa1milliar with Maven
it is a build system, see http://maven.apache.org/ for more information.
