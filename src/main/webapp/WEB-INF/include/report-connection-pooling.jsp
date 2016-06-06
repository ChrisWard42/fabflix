<tr>
    <td style="padding: 20px">
        <h3 style="text-align: center">Connection Pooling</h3><br><br>

        To use connection pooling on a webapp, first the <span style="font-weight:bold">context.xml</span> file needs to added in the <span style="font-weight:bold">META-INF</span> folder of the webapp. This XML file defines from what database to pool the connections from and also gives the context a name. The XML also sets some of the properties of connection pooling such as the maximum number of active connections and maximum number of idle connections. Once this XML file is set up, a <span style="font-weight:bold">DataSource</span> object can be made to pull from this pool.<br><br>

        This DataSource object can be instantiated in a pooling listener, which is a <span style="font-weight:bold">servletContextListener</span> that is always running in the webapp. This listener needs to be defined in the <span style="font-weight:bold">web.xml</span> for the webapp as a listener rather than a regular servlet. The listener instantiates the DataSource object and sets a servlet attribute containing the DataSource object for other servlets to grab and pull a connection from the pool.<br><br>

        In each servlet that needs database access, the <span style="font-weight:bold">init()</span> function gets the DataSource object from the attribute set by the listener. It is acquired from the init() function to ensure that a DataSource object is available from the beginning of the servlet’s life. Then, the servlet uses this DataSource object to pull a connection from the pool. Once the connection is set, all JDBC operations are ready to operate as normal. After all JDBC operations have finished, the connection is closed but it returns back to the pool for other JDBC operations to use.<br><br>

        In the case of a master-slave replication setup, the DataSource objects need to be defined to have access to the other servers in the mirror. This is done using a <span style="font-weight:bold">jdbc:mysql:replication</span> driver which defined multiple replication hosts and ports for interconnectivity. Additionally, there are a couple ways to restrict write access to the Master server. One is to have all JDBC code performing write queries use a separate DataSource which can only connect to the IP of the Master server. Another is to utilize <span style="font-weight:bold">Connection.setReadOnly([true | false])</span> to allow the connector to automatically transfer writes to the master and reads to the slaves.
    </td>
</tr>