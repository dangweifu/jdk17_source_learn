/*
 * Copyright (c) 2005, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package javax.tools;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import javax.tools.JavaFileObject.Kind;

/**
 * Forwards calls to a given file manager.  Subclasses of this class
 * might override some of these methods and might also provide
 * additional fields and methods.
 *
 * @param <M> the kind of file manager forwarded to by this object
 * @author Peter von der Ah&eacute;
 * @since 1.6
 */
public class ForwardingJavaFileManager<M extends JavaFileManager> implements JavaFileManager {

    /**
     * The file manager which all methods are delegated to.
     */
    protected final M fileManager;

    /**
     * Creates a new instance of {@code ForwardingJavaFileManager}.
     * @param fileManager delegate to this file manager
     */
    protected ForwardingJavaFileManager(M fileManager) {
        this.fileManager = Objects.requireNonNull(fileManager);
    }

    /**
     * @throws SecurityException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public ClassLoader getClassLoader(Location location) {
        return fileManager.getClassLoader(location);
    }

    /**
     * @throws IOException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public Iterable<JavaFileObject> list(Location location,
                                         String packageName,
                                         Set<Kind> kinds,
                                         boolean recurse)
        throws IOException
    {
        return fileManager.list(location, packageName, kinds, recurse);
    }

    /**
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public String inferBinaryName(Location location, JavaFileObject file) {
        return fileManager.inferBinaryName(location, file);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     */
    @Override
    public boolean isSameFile(FileObject a, FileObject b) {
        return fileManager.isSameFile(a, b);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public boolean handleOption(String current, Iterator<String> remaining) {
        return fileManager.handleOption(current, remaining);
    }

    @Override
    public boolean hasLocation(Location location) {
        return fileManager.hasLocation(location);
    }

    @Override
    public int isSupportedOption(String option) {
        return fileManager.isSupportedOption(option);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public JavaFileObject getJavaFileForInput(Location location,
                                              String className,
                                              Kind kind)
        throws IOException
    {
        return fileManager.getJavaFileForInput(location, className, kind);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location,
                                               String className,
                                               Kind kind,
                                               FileObject sibling)
        throws IOException
    {
        return fileManager.getJavaFileForOutput(location, className, kind, sibling);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public FileObject getFileForInput(Location location,
                                      String packageName,
                                      String relativeName)
        throws IOException
    {
        return fileManager.getFileForInput(location, packageName, relativeName);
    }

    /**
     * @throws IllegalArgumentException {@inheritDoc}
     * @throws IllegalStateException {@inheritDoc}
     */
    @Override
    public FileObject getFileForOutput(Location location,
                                       String packageName,
                                       String relativeName,
                                       FileObject sibling)
        throws IOException
    {
        return fileManager.getFileForOutput(location, packageName, relativeName, sibling);
    }

    @Override
    public void flush() throws IOException {
        fileManager.flush();
    }

    @Override
    public void close() throws IOException {
        fileManager.close();
    }

    /**
     * @since 9
     */
    @Override
    public Location getLocationForModule(Location location, String moduleName) throws IOException {
        return fileManager.getLocationForModule(location, moduleName);
    }

    /**
     * @since 9
     */
    @Override
    public Location getLocationForModule(Location location, JavaFileObject fo) throws IOException {
        return fileManager.getLocationForModule(location, fo);
    }

    /**
     * @since 9
     */
    @Override
    public <S> ServiceLoader<S> getServiceLoader(Location location, Class<S> service) throws  IOException {
        return fileManager.getServiceLoader(location, service);
    }

    /**
     * @since 9
     */
    @Override
    public String inferModuleName(Location location) throws IOException {
        return fileManager.inferModuleName(location);
    }

    /**
     * @since 9
     */
    @Override
    public Iterable<Set<Location>> listLocationsForModules(Location location) throws IOException {
        return fileManager.listLocationsForModules(location);
    }

    /**
     * @since 9
     */
    @Override
    public boolean contains(Location location, FileObject fo) throws IOException {
        return fileManager.contains(location, fo);
    }
}
