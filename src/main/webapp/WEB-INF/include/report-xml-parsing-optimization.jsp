<tr>
    <td style="padding: 20px">
        <h3 style="text-align: center">XML Parsing Optimization</h3><br><br>

        <b>Optimization Descriptions:</b><br><br>

        We implemented two optimizations to our parser to make it run faster,
        however only one of the optimizations made a noticeable difference to runtime.<br><br>

        The two optimizations were setting the auto commit parameter to false for the JDBC connection and
        using batch insert statements instead of executing the statements one by one.<br><br>

        We implemented a timer in our parser to keep track of the execution time. We ran it without either of the two optimizations, with only the first optimization, and with both optimizations.<br><br>

        <b>Results:</b><br><br>

        No batch statements, Auto commit on:<br>
        &nbsp;&nbsp;&nbsp;&nbsp;Runtime: 0 hours 5 minutes 43 seconds 680 milliseconds<br><br>

        No batch statements, Auto commit off:<br>
        &nbsp;&nbsp;&nbsp;&nbsp;Runtime: 0 hours 0 minutes 5 seconds 536 milliseconds<br><br>

        Batch statements, Auto commit off:<br>
        &nbsp;&nbsp;&nbsp;&nbsp;Runtime: 0 hours 0 minutes 5 seconds 541 milliseconds<br><br>

        <b>Observations:</b><br><br>

        Having auto commit on caused a massive amount of overhead compared to having it off and only commiting after executing the queries. After turning off auto commit the runtime was 68 times faster, dropping from 5 minutes and 43 seconds to only 5 seconds.<br><br>

        Utilizing batch statements instead did not improve the run time noticeably. The runtime with or without batch statements, and auto commit turned off, is identical. It should be noted that we didn't have time to test with batch statements and auto commit turned on, so it's likely that batch statements provide some benefit over no optimizations but that this is overshadowed by the enormous difference made by turning off auto commit.<br><br>
    </td>
</tr>