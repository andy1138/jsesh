/**
 * @(#)QuaquaLazyActionMap.java
 *
 * Copyright (c) 2008 by the original authors of JHotDraw and all its
 * contributors. All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the 
 * license agreement you entered into with the copyright holders. For details
 * see accompanying license terms.
 */

package org.jhotdraw_7_6.gui.plaf.palette;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ActionMapUIResource;

/**
 * QuaquaLazyActionMap.
 *
 * @author Werner Randelshofer
 * @version $Id: PaletteLazyActionMap.java 717 2010-11-21 12:30:57Z rawcoder $
 */
public class PaletteLazyActionMap extends ActionMapUIResource {
    /**
     * Object to invoke <code>loadActionMap</code> on. This may be
     * a Class object.
     */
     private transient Object loader;

    /**
     * Installs an ActionMap that will be populated by invoking the
     * <code>loadActionMap</code> method on the specified Class
     * when necessary.
     * <p>
     * This should be used if the ActionMap can be shared.
     *
     * @param c JComponent to install the ActionMap on.
     * @param loaderClass Class object that gets loadActionMap invoked
     *                    on.
     * @param defaultsKey Key to use to defaults table to check for
     *        existing map and what resulting Map will be registered on.
     */
    static void installLazyActionMap(JComponent c, Class loaderClass,
                                     String defaultsKey) {
        ActionMap map = (ActionMap)UIManager.get(defaultsKey);
        if (map == null) {
            map = new PaletteLazyActionMap(loaderClass);
            UIManager.getLookAndFeelDefaults().put(defaultsKey, map);
        }
        SwingUtilities.replaceUIActionMap(c, map);
    }

    /**
     * Returns an ActionMap that will be populated by invoking the
     * <code>loadActionMap</code> method on the specified Class
     * when necessary.
     * <p>
     * This should be used if the ActionMap can be shared.
     *
     * @param loaderClass Class object that gets loadActionMap invoked
     *                    on.
     * @param defaultsKey Key to use to defaults table to check for
     *        existing map and what resulting Map will be registered on.
     */
    static ActionMap getActionMap(Class loaderClass,
                                  String defaultsKey) {
        ActionMap map = (ActionMap)UIManager.get(defaultsKey);
        if (map == null) {
            map = new PaletteLazyActionMap(loaderClass);
            UIManager.getLookAndFeelDefaults().put(defaultsKey, map);
        }
        return map;
    }


    private PaletteLazyActionMap(Class loader) {
        this.loader = loader;
    }

    public void put(Action action) {
        put(action.getValue(Action.NAME), action);
    }

    
    public void put(Object key, Action action) {
        loadIfNecessary();
        super.put(key, action);
    }

    
    public Action get(Object key) {
        loadIfNecessary();
        return super.get(key);
    }

    
    public void remove(Object key) {
        loadIfNecessary();
        super.remove(key);
    }

    
    public void clear() {
        loadIfNecessary();
        super.clear();
    }

    
    public Object[] keys() {
        loadIfNecessary();
        return super.keys();
    }

    
    public int size() {
        loadIfNecessary();
        return super.size();
    }

    
    public Object[] allKeys() {
        loadIfNecessary();
        return super.allKeys();
    }

    
    public void setParent(ActionMap map) {
        loadIfNecessary();
        super.setParent(map);
    }

    @SuppressWarnings("unchecked")
    private void loadIfNecessary() {
        if (loader != null) {
            Object ldr = loader;

            loader = null;
            Class klass = (Class)ldr;
            try {
                Method method = klass.getDeclaredMethod("loadActionMap",
                                      new Class[] { PaletteLazyActionMap.class  });
                method.invoke(klass, new Object[] { this });
            } catch (NoSuchMethodException nsme) {
                assert false : "LazyActionMap unable to load actions " +
                        klass;
            } catch (IllegalAccessException iae) {
                assert false : "LazyActionMap unable to load actions " +
                        iae;
            } catch (InvocationTargetException ite) {
                assert false : "LazyActionMap unable to load actions " +
                        ite;
            } catch (IllegalArgumentException iae) {
                assert false : "LazyActionMap unable to load actions " +
                        iae;
            }
        }
    }
}
