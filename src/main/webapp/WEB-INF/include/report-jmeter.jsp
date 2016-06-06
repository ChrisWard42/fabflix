<tr>
    <td style="padding: 20px">
        <h3 style="text-align: center">JMeter</h3><br><br>
        
        <table style="width:100%; table-layout: fixed; text-align: center">
          <tr style="font-weight:bold; font-color: white; background: #ff3434 !important;">
            <td style="width:10%">Single-Instance Version Cases</td>
            <td style="width:40%">Graph Results Screenshot</td>
            <td style="width:10%">Average Query Time (ms)</td>
            <td style="width:10%">Average Search Servlet Time (ms)</td>
            <td style="width:10%">Average JDBC Time (ms)</td>
            <td style="width:20%">Analysis</td>
          </tr>
          <tr style="border-top: 1px solid black;">
            <td>Case 1: HTTP/1 thread</td>
            <td><a href="resources/img/HTTP_1.png"><img src="resources/img/HTTP_1.png" alt="Graph Results Screenshot Case 1" style="width:100%"></a><br>Click Image to Expand</td>
            <td>61</td>
            <td>14.853206</td>
            <td>15.468881</td>
            <td>This case is the baseline value. One user accessing a single server and querying the database with both optimizations gets a response from the search servlet in a reasonable 14ms.</td>
          </tr>
          <tr style="border-top: 1px solid black;">
            <td>Case 2: HTTP/10 threads</td>
            <td><a href="resources/img/HTTP_10.png"><img src="resources/img/HTTP_10.png" alt="Graph Results Screenshot Case 2" style="width:100%"></a><br>Click Image to Expand</td>
            <td>156</td>
            <td>107.60589</td>
            <td>22.959246</td>
            <td>Ramping up to 10 users with the same infrastructure causes an increase in the time it takes for the servlet to respond to each request, though the jdbc access time remains relatively short. This may be due to concurrency handling which halts the processing outside the DB call but inside the servlet.</td>
          </tr>
          <tr style="border-top: 1px solid black;">
            <td>Case 3: HTTPS/10 threads</td>
            <td><a href="resources/img/HTTPS_10.png"><img src="resources/img/HTTPS_10.png" alt="Graph Results Screenshot Case 3" style="width:100%"></a><br>Click Image to Expand</td>
            <td>171</td>
            <td>107.14191</td>
            <td>26.837635</td>
            <td>Changing to HTTPS from HTTP makes no noticeable difference on the execution times. It increases very marginally, probably due to extra information carried with the packets for the SSL part of the protocol wjem returning a response, but it more or less remains the same.</td>
          </tr>
          <tr style="border-top: 1px solid black;">
            <td>Case 4: HTTP/10 threads/No prepared statements</td>
            <td><a href="resources/img/HTTP_10_NoPrepared.png"><img src="resources/img/HTTP_10_NoPrepared.png" alt="Graph Results Screenshot Case 4" style="width:100%"></a><br>Click Image to Expand</td>
            <td>183</td>
            <td>107.02299</td>
            <td>36.77036</td>
            <td>We see a slight performance degradation when we keep connection pooling but remove prepared statements. This extra time is the time it takes to compile the statements in MySQL before execution, whereas prepared statements utilize preexisting statements, saving some time.</td>
          </tr>
          <tr style="border-top: 1px solid black;">
            <td>Case 5: HTTP/10 threads/No connection pooling</td>
            <td><a href="resources/img/HTTP_10_NoPooling.png"><img src="resources/img/HTTP_10_NoPooling.png" alt="Graph Results Screenshot Case 4" style="width:100%"></a><br>Click Image to Expand</td>
            <td>608</td>
            <td>492.47267</td>
            <td>148.951771</td>
            <td>We see a massive performance degradation when we remove connection pooling, since the 10 users are all being bottlenecked by the single shared resource connection within the servlet. It's unclear why the JDBC access time increased as much as it did, but both numbers are noticeably worse.</td>
          </tr>

        </table>
    </td>
</tr>