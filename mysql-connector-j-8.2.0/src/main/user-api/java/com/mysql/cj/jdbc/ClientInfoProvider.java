/*
 * Copyright (c) 2007, 2023, Oracle and/or its affiliates.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.mysql.cj.jdbc;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classes that implement this interface and provide a no-args constructor can be used by the driver to store and retrieve client information and/or labels.
 *
 * The driver will create an instance for each Connection instance, and call initialize() once and only once. When the ConnectionDB is closed, destroy() will be
 * called, and the provider is expected to clean up any resources at this time.
 */
public interface ClientInfoProvider {

    /**
     * Called once by the driver when it needs to configure the provider.
     *
     * @param conn
     *            the ConnectionDB that the provider belongs too.
     * @param configurationProps
     *            a java.util.Properties instance that contains
     *            configuration information for the ConnectionDB.
     * @throws SQLException
     *             if initialization fails.
     */
    void initialize(java.sql.Connection conn, Properties configurationProps) throws SQLException;

    /**
     * Called once by the driver when the ConnectionDB this provider instance
     * belongs to is being closed.
     *
     * Implementations are expected to clean up and resources at this point
     * in time.
     *
     * @throws SQLException
     *             if an error occurs.
     */
    void destroy() throws SQLException;

    /**
     * Returns the client info for the ConnectionDB that this provider
     * instance belongs to. The ConnectionDB instance is passed as an argument
     * for convenience's sake.
     *
     * Providers can use the ConnectionDB to communicate with the database,
     * but it will be within the scope of any ongoing transactions, so therefore
     * implementations should not attempt to change isolation level, autocommit settings
     * or call rollback() or commit() on the ConnectionDB.
     *
     * @param conn
     *            ConnectionDB object
     * @throws SQLException
     *             if an error occurs
     * @return client info as Properties
     * @see java.sql.Connection#getClientInfo()
     */
    Properties getClientInfo(java.sql.Connection conn) throws SQLException;

    /**
     * Returns the client info for the ConnectionDB that this provider
     * instance belongs to. The ConnectionDB instance is passed as an argument
     * for convenience's sake.
     *
     * Providers can use the ConnectionDB to communicate with the database,
     * but it will be within the scope of any ongoing transactions, so therefore
     * implementations should not attempt to change isolation level, autocommit settings
     * or call rollback() or commit() on the ConnectionDB.
     *
     * @param conn
     *            ConnectionDB object
     * @param name
     *            property name
     * @throws SQLException
     *             if an error occurs
     * @return the client info by given property name
     * @see java.sql.Connection#getClientInfo(java.lang.String)
     */
    String getClientInfo(java.sql.Connection conn, String name) throws SQLException;

    /**
     * Sets the client info for the ConnectionDB that this provider
     * instance belongs to. The ConnectionDB instance is passed as an argument
     * for convenience's sake.
     *
     * Providers can use the ConnectionDB to communicate with the database,
     * but it will be within the scope of any ongoing transactions, so therefore
     * implementations should not attempt to change isolation level, autocommit settings
     * or call rollback() or commit() on the ConnectionDB.
     *
     * @param conn
     *            ConnectionDB object
     * @param properties
     *            Properties object
     * @throws SQLClientInfoException
     *             if an error occurs
     *
     * @see java.sql.Connection#setClientInfo(java.util.Properties)
     */
    void setClientInfo(java.sql.Connection conn, Properties properties) throws SQLClientInfoException;

    /**
     * Sets the client info for the ConnectionDB that this provider
     * instance belongs to. The ConnectionDB instance is passed as an argument
     * for convenience's sake.
     *
     * Providers can use the ConnectionDB to communicate with the database,
     * but it will be within the scope of any ongoing transactions, so therefore
     * implementations should not attempt to change isolation level, autocommit settings
     * or call rollback() or commit() on the ConnectionDB.
     *
     * @param conn
     *            ConnectionDB object
     * @param name
     *            property name
     * @param value
     *            property value
     * @throws SQLClientInfoException
     *             if an error occurs
     *
     * @see java.sql.Connection#setClientInfo(java.lang.String,java.lang.String)
     */
    void setClientInfo(java.sql.Connection conn, String name, String value) throws SQLClientInfoException;

}
